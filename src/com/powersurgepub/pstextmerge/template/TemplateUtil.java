package com.powersurgepub.pstextmerge.template;

  import com.powersurgepub.psdatalib.txbio.*;
  import com.powersurgepub.psdatalib.txbmodel.*;
  import com.powersurgepub.psdatalib.pstextio.*;
  import com.powersurgepub.psdatalib.psdata.*;
  import com.powersurgepub.psutils.*;
  import java.io.*;
  import java.net.*;

/**
   Persistent data needed by the TemplateLine class. <p>
  
   This code is copyright (c) 1999-2002 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
    2002/10/05 - Added the list item pending flag. <li>
    2002/03/23 - Added support for group processing. <li>
    2000/06/17 - Added global variables. <li>
    2000/05/28 - Created to prevent Template and TemplateLine from having
                 to both reference each other, and modified to be 
                 consistent with "The Elements of Java Style".</ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com">
           www.powersurgepub.com</a>)
  
   @version 
    2003/03/08 - Added code to the setGroup method. When a group break is now
                 detected, the method will reset lower level 
                 break values, forcing a new group if non-null. This fixed
                 a prior bug.
 */
public class TemplateUtil {
  
  /** Used to write events and data to a log file. */
  private    Logger      log;
  
  /** Work area for any event to be logged. */
  private    LogEvent    event;
  
  /** The name of the input template file being used. */
  private    String      templateFileName;
  
  /** The directory path to the template file. */
  private    String      templateFilePath;
  
  /** The folder enclosing the template file. */
  private    String      templateParent;
  
  /** First line of template file? */
  private    boolean     firstTemplateLine = true;
  
  /** The name of the input data file/folder, formatted for display to a user. */
  private    String      dataFileDisplay;

  /** The name of the input data file/folder, without any file extension. */
  private    String      dataFileBaseName;
  
  /** The folder enclosing the data file. */
  private    String      dataParent;
  
  /** Name of the output text file. */
  private    String      textFileOutName;
  
  /** Output text file where merged file is written. */
  private    TextLineWriter textFileOut;
  
  /** Is the output text file open? */
  private    boolean     textFileOutOpen = false;
  
  /** Number of lines written to the output text file so far. */
  private    int         textFileOutLineCount = 0;
  
  /** Number of times an output text file was successfully opened. */
  private    int         outputCommandCount = 0;
  
  /** Current settings for delimiters to indicate the start of a command. */
  private    String       nlStartCommand = "<<";
  
  /** Current settings for delimiters to indicate the end of a command. */
  private    String       nlEndCommand = ">>";
  
  /** Current settings for delimiters to indicate the start of a variable. */
  private    String       nlStartVariable = "<<";
  
  /** Current settings for delimiters to indicate the end of a variable. */
  private    String       nlEndVariable = ">>";
  
  /** Current setting for delimiters to indicate the start of variable modifiers. */
  private    String       nlStartModifiers = "&";
  
  /** Are we currently skipping template lines, due to an IF that returned false? */
  private    boolean      skippingData = false;
  
  /** Number of ENDIF commands we are looking for. */
  private    int          ifBypassDepth = 0;
  
  /** Last value of data tested by IFCHANGE command. */
  private    String       lastChangeData = "";
  
  /** Is there a non-blank list item pending? */
  private		 boolean			listItemPending = false;

  /** Are we producing an epub document. */
  private    boolean      epub = false;

  /** The site URL to use to replace relative references for an epub doc. */
  private    String       epubSite = "";
  
  /** Collection of global variables. */
  private    DataRecord   globals;
	
	/** Group data */
	public final static int MAX_GROUPS = 10;
	private int minorGroup = -1;
  private int listLevel = -1;
	private boolean[] endGroup    = new boolean [MAX_GROUPS];
	private boolean[] newGroup    = new boolean [MAX_GROUPS];
  private boolean[] newList     = new boolean [MAX_GROUPS];
  private boolean[] endList     = new boolean [MAX_GROUPS];
	private String[]  groupValue  = new String  [MAX_GROUPS];

  /** TextIO data for conversion of include file */
  private             TextIO              io;
  private             TextTree            tree;
  private             int                 tempCount = 0;
  
  private             StringConverter     noBreaksConverter = null;

  /**
     Constructs the utility collection.
   */
  public TemplateUtil () {
    this (new Logger (new LogOutput()));  
  }
  
  /**
     Constructs the utility collection with the passed Logger object.
    
     @param log Logger object to use for writing events to a log file.
   */
  public TemplateUtil (Logger log) {
    io = new TextIO ();
    this.log = log;
    event = new LogEvent();
    globals = new DataRecord ();
		resetGroupBreaks();
		resetGroupValues();
    // io.logTypes();
  }

  public void setEpub (boolean epub) {
    this.epub = epub;
  }

  public boolean isEpub () {
    return epub;
  }

  public void setEpubSite (String epubSite) {
    this.epubSite = epubSite;
  }

  public String getEpubSite () {
    return epubSite;
  }
  
  /**
     Tailor an event and send it to the log.
    
     @param severity Severity of the event.
    
     @param message Text description of the event.
    
     @param dataRelated Was the event related to some preceding data?
   */
  public void recordEvent (int severity, String message, boolean dataRelated) {
    tailorEvent (severity, message, dataRelated);
    // log.recordEvent (event); looks like bug
    recordEvent();
  }
  
  /**
     Tailor an event.
    
     @param severity Severity of the event.
    
     @param message Text description of the event.
    
     @param dataRelated Was the event related to some preceding data?
   */
  public void tailorEvent (int severity, String message, boolean dataRelated) {
    event.setSeverity (severity);
    event.setMessage (message);
    event.setDataRelated (dataRelated);
  }
  
  /**
     Send an event to the log. It should have been tailored previously.
   */
  public void recordEvent () {
    log.recordEvent (event);
  }
  
  /**
     Returns the name of the last output text file opened
     in the last GenerateOutput execution.
    
     @return Name of output text file.
   */
  public String getTextFileOutName() {
    return textFileOutName;
  }
  
  /**
     Returns the last output text file opened
     in the last GenerateOutput execution.
    
     @return Output text file.
   */
  public TextLineWriter getTextFileOut() {
    return textFileOut;
  }
  
  /** 
     Returns the name of the input template file. 
    
     @return Name of the input template file. 
   */
  public String getTemplateFileName() { return templateFileName; }
  
  /**
     Returns the name of the folder containing the template file. 
    
     @return Name of the folder containing the template file.
   */
  public String getTemplateParent() { return templateParent; }
  
  /** 
     Returns the directory path to the input template file. 
    
     @return Directory path to the input template file. 
   */
  public String getTemplateFilePath() { return templateFilePath; }
  
  public void setFirstTemplateLine (boolean first) {
    firstTemplateLine = first;
  }
  
  public boolean isFirstTemplateLine () {
    return firstTemplateLine;
  }
  
  /**
     Returns the number of lines written to the last output 
     file generated during the last GenerateOutput operation.</p>
     
   @return textFileOutLineCount
   */
  public int getTextFileOutLineCount() {
    return textFileOutLineCount;
  }
  
  /**
     Returns the name of the data file, formatted for display. 
    
     @return Name of the data file.
   */
  public String getDataFileDisplay() { return dataFileDisplay; }

  /**
   Returns the name of the data file base name, without any file extension.

   @return Name of the data file base name.
   */
  public String getDataFileBaseName() { return dataFileBaseName; }
  
  /**
     Returns the name of the folder containing the data file. 
    
     @return Name of the folder containing the data file.
   */
  public String getDataParent() { return dataParent; }
  
  /**
     Returns the skippingData variable.
    
     @return Are we skipping template lines until the next ENDIF? 
   */
  public boolean isSkippingData() { return skippingData; }
  
  /** 
     Returns the current delimiters used to start a command.
     
     @return Delimiters that identify the start of a command.
   */
  public String getNlStartCommand() { return nlStartCommand; } 
  
  /** 
     Returns the current delimiters used to end a command.
     
     @return Delimiters that identify the end of a command.
   */
  public String getNlEndCommand() { return nlEndCommand; }
  
  /** 
     Returns the current delimiters used to start a variable.
     
     @return Delimiters that identify the start of a variable.
   */
  public String getNlStartVariable() { return nlStartVariable; }
  
  /** 
     Returns the current delimiters used to end a variable.
     
     @return Delimiters that identify the end of a variable.
   */
  public String getNlEndVariable() { return nlEndVariable; }
  
  /** 
     Returns the current delimiters used to start the variable modifiers.
     
     @return Delimiters that identify the start the variable modifiers.
   */
  public String getNlStartModifiers() { return nlStartModifiers; }
  
  /** 
     Determines whether there is a non-blank list item pending.
     
     @return True if there is a non-blank list item pending.
   */
  public boolean isListItemPending() { return listItemPending; }
  
  /**
     Returns the collection of global variables.
    
     @return The global variables.
   */
  public DataRecord getGlobals() { return globals; }
  
	/**
	   Reset all group breaks to their off position.
	 */
	public void resetGroupBreaks() {
	  for (int i = 0; i < MAX_GROUPS; i++) {
			endGroup [i] = false;
			newGroup [i] = false;
      endList  [i] = false;
      newList  [i] = false;
		}
	}
	
	/**
	   Reset all group values to blanks.
	 */
	public void resetGroupValues() {
	  for (int i = 0; i < MAX_GROUPS; i++) {
			groupValue[i] = "";
		}
	}
	
	/**
	   Set a new group value from DEFINEGROUP command.
	 */
	public void setGroup (int groupNumber, String inputValue) {
		String nextValue = inputValue.trim();

		if (groupNumber > minorGroup) {
			minorGroup = groupNumber;
		}
		if (groupValue[groupNumber].equals(nextValue)) {
      // No change -- do nothing
		}
		else {
			setEndGroupsTrue (groupNumber);
			if (nextValue.length() > 0) {
				newGroup[groupNumber] = true;
        if (groupValue[groupNumber].length() == 0) {
          newList[groupNumber] = true;
        }
			} else {
        endList[groupNumber] = true;
      }
      // reset lower level break values, forcing a new group if non-null
      for (int i = groupNumber + 1; i <= minorGroup; i++) {
        if (groupValue[i].length() > 0) {
          endList[i] = true;
          groupValue[i] = "";
        }
      } 
			groupValue[groupNumber] = nextValue;
		} // end break condition
	}
	
	/**
	   Set end group breaks.
	  
	   @param majorGroup - Group number to start with. 
	 */
	public void setEndGroupsTrue (int majorGroup) {
		for (int i = majorGroup; i <= minorGroup; i++) {
			if (groupValue[i].length() > 0) {
				endGroup[i] = true;
			} // end if non-null prior group value
		} // end for loop through end group breaks
	}
	
	/**
	   Process an IFENDGROUP command.
	  
     @param groupNumber - index to group being processed.
   */
  public void setIfEndGroup (int groupNumber) { 
    setSkippingData 
      (! endGroup [groupNumber]);
  }
	
	/**
	   Process an IFNEWGROUP command.
	  
     @param groupNumber - index to group being processed.
   */
  public void setIfNewGroup (int groupNumber) { 
    setSkippingData 
      (! newGroup [groupNumber]);
  }
  
  public void setIfEndList (int groupNumber) {
    
    setSkippingData
        (! endList [groupNumber]);
  }
  
  public void setIfNewList (int groupNumber) {

    setSkippingData
        (! newList [groupNumber]);
  }
  
  /**
     Increment ifBypassDepth by +1.
     
     This method should be called every time an inactive IF command
     is encountered as part of the TemplateLine.generateOutput processing.
     
     In general, as we process template lines, we need to keep track of
     two state conditions relative to conditional processing. If we are within
     the scope of a conditional that has returned false, then we need to know
     to skip all ouput lines until we hit the matching ENDIF command or
     its equivalent (some sort of group command). The skippingData variable
     is used to indicate that we are within the scope of a negative 
     conditional.
     
     The second state variable we need to keep track of is the number of 
     nested conditionals we have run across while inside the scope of a
     conditional that has returned false. As we end conditional blocks, we
     need to decrement this counter so that we know when we have found the
     matching ENDIF command (or its equivalent). The ifBypassDepth variable
     is used to keep track of this number. 
   */
  public void anotherIf () {
    ifBypassDepth++;
  }
  
  /**
    Clear all pending conditionals.
    
    This method should be called for commands that should
    never be found within the scope of a conditional block.
   */
  public void clearIfs () {
    ifBypassDepth = 0;
    skippingData = false;
  }
  
  /**
   Process an else command. 
   */
  public void anElse () {
    boolean skippingBeforeElse = isSkippingData();
    if (ifBypassDepth > 0) {
      ifBypassDepth--;
    } else {
      setSkippingData (! skippingBeforeElse);
    }
  }
  
  /**
     Decrement ifBypassDepth by +1.
     
     This method should be called every time an ENDIF command
     is encountered as part of the TemplateLine.generateOutput processing.
   */
  public void anotherEndIf () {
    if (ifBypassDepth > 0) {
      ifBypassDepth--;
    } else {
      setSkippingData (false);
    }
  } // end method anotherEndIf
  
  /**
     Sets skippingData on if data has not changed since last time through.
     Sets lastChangeData to the latest value. 
    
     @param ifChangeData Data found as parameter of IFCHANGE command.
   */
  public void setIfChangeData (String ifChangeData) { 
    setSkippingData 
      (ifChangeData.equals (lastChangeData));
    lastChangeData = ifChangeData;
  }
  
  /**
     Sets the skippingData variable.
     
     This method should be called every time an active IF command
     is encountered as part of the TemplateLine.generateOutput processing.
    
     @param skippingData Should we skip subsequent template lines? 
                         Note that this value would be the logical 
                         opposite of the result of an if evaluation.
   */
  public void setSkippingData (boolean skippingData) {
    this.skippingData = skippingData;
  }
  
  /**
     Sets the name of the input template file.
    
     @param templateFileName Name of the input template file.
   */
  public void setTemplateFileName (String templateFileName) {
    this.templateFileName = templateFileName;
  }
  
  /**
     Sets the directory path to the input template file.
    
     @param templateFilePath Directory path to the input template file.
   */
  public void setTemplateFilePath (String templateFilePath) {
    this.templateFilePath = templateFilePath;
  }
  
  /**
     Sets the name of the folder containing the template file.
    
     @param templateParent Name of the folder containing the template file.
   */
  public void setTemplateParent (String templateParent) {
    this.templateParent = templateParent;
  }
  
  /**
     Sets the name of the input data file, formatted for display.
    
     @param dataFileDisplay Name of the input data file, formatted for display.
   */
  public void setDataFileDisplay (String dataFileDisplay) {
    this.dataFileDisplay = dataFileDisplay;
  }

  /**
   Sets the name of the input data file, without any file extension.

   @param dataFileBaseName Name of the input data file, without any
                           file extension. 
   */
  public void setDataFileBaseName (String dataFileBaseName) {
    this.dataFileBaseName = dataFileBaseName;
  }
  
  /**
     Sets the name of the folder containing the input data file.
    
     @param dataParent Name of the folder containing the input data file.
   */
  public void setDataParent (String dataParent) {
    this.dataParent = dataParent;
  }
  
  /**
     Sets the name of the output text file. Closes the last output
     text file, if one is open. Attempts to open the new output
     text file. If unsuccessful, writes an event to the log. If successful,
     increments outputCommandCount by 1. 
    
     @param textFileOutName New name for the output text file.
   */
  public void setTextFileOutName (String textFileOutName) {
    this.textFileOutName = textFileOutName;
    close();
    if (! textFileOutName.startsWith ("/")) {
      FileName path = new FileName (templateFilePath);
      this.textFileOutName = path.resolveRelative(textFileOutName);
    }
    textFileOut = new FileMaker
      (this.textFileOutName);
    outputCommandCount++;
    boolean ok = textFileOut.openForOutput();
    if (ok) {
      textFileOutOpen = true;
    } else {
      recordEvent (LogEvent.MAJOR, 
        "Attempt to Open File " + textFileOutName + " was unsuccessful", false);
    }
  } // end method setTextFileOutName
  
  public String noBreaks (String inStr) {
    if (noBreaksConverter == null) {
      noBreaksConverter = new StringConverter();
      noBreaksConverter.add(StringConverter.HTML_BREAK, "");
      noBreaksConverter.add(StringConverter.HTML_BREAK_2, "");
      noBreaksConverter.add(StringConverter.HTML_BREAK_3, "");
    }
    return (noBreaksConverter.convert(inStr));
  }
  
  /**
     Copies the contents of the include file into the output file stream. 
    
     @param includeFileName Name of the text file to be included.
   */
  public void includeFile (String includeFileNameStr, String includeParm) {

    // Make sure we have a complete file name
    FileLineReader includeFile;
    if (includeFileNameStr.startsWith("file:")) {
      includeFile = new FileLineReader (includeFileNameStr);
    }
    else
    if (includeFileNameStr.startsWith ("/")) {
      includeFile = new FileLineReader (includeFileNameStr);
    } else {
      FileName templatePathFileName = new FileName(templateFilePath);
      includeFile = new FileLineReader 
          (templatePathFileName.resolveRelative(includeFileNameStr));
    }

    // System.out.println("TemplateUtil.includeFile: " + includeFile.toString());
    recordEvent (LogEvent.NORMAL, "Including file " + includeFile.toString(), false);
    boolean converted = false;
    File incFile = includeFile.getFile();
    if (incFile != null && (! incFile.exists())) {
      recordEvent (LogEvent.MEDIUM, 
          "File " + includeFile.toString() + " not found", false);
    } else {

      // See if include file should be converted
      FileName includeFileName = new FileName (includeFile.toString());
      FileName textFileOutFileName = new FileName (textFileOut.getDestination());
      String inExt = includeFileName.getExt();
      String outExt = textFileOutFileName.getExt();
      TextIOType inType = io.getType (inExt, "Input", false);
      TextIOType outType = io.getType (outExt, "Output", true);
      if ((includeParm == null
          || includeParm.length() == 0
          || (! includeParm.equalsIgnoreCase("copy")))
          && inExt.length() > 0
          && outExt.length() > 0
          && inType != null
          && outType != null
          && ((! inExt.equalsIgnoreCase (outExt))
            || inExt.equalsIgnoreCase("html"))) {
        TextNode root = new TextNode(tree);
        tree = new TextTree (root);
        tree.getTextRoot().setType (TextType.LOCATION_FILE);
        tree.getTextRoot().setText (includeFile.toString());
        try {
          URL url = includeFile.toURL();
          // System.out.println("  Loading type = " + inType + ", " + url.toString());
          converted = io.load (tree, url, inType, includeParm);
          // System.out.println("  Loaded successfully? " + String.valueOf(converted));
          if (converted) {
            File temp
                = File.createTempFile
                  ("pstm_include_temp_" + String.valueOf (tempCount++),
                    "." + outExt);
            // Delete temp file when program exits.
            temp.deleteOnExit();
            FileMaker writer = new FileMaker (temp);
            // System.out.println("  Storing " + url.toString());
            converted = io.store (tree, writer, outType, epub, epubSite);
            // System.out.println("  Stored " + url.toString());
            if (converted) {
              recordEvent (LogEvent.NORMAL,
                  "Converted Include file "
                    + includeFile.toString()
                    + " from "
                    + inType.getLabel()
                    + " to "
                    + outType.getLabel(),
                    false);
              includeFile = new FileLineReader (temp);
            }
          }
        } catch (MalformedURLException e) {
          converted = false;
        } catch (IOException e) {
          converted = false;
        }
      }

      // Copy included file to output
      boolean inOK = includeFile.open();
      if (inOK) {
        String includeLine = includeFile.readLine(); 
        while (! includeFile.isAtEnd()) { 
          writeLine (includeLine); 
          includeLine = includeFile.readLine(); 
        } 
        includeFile.close(); 
      } else {
        recordEvent (LogEvent.MEDIUM, 
          "Attempt to Open Include File " + includeFileNameStr + " was unsuccessful",
            true);
      } 
    }
    
  } // end method setTextFileOutName
  
  /**
     Writes a String to the output text file, if one is open.
    
     @param outString Text to be written to the output text file.
   */
  public void write (String outString) {  
    if (textFileOutOpen) {
      boolean ok = textFileOut.write (outString);
      if (! ok) {
      } // end catch
    } // end if file is open
  } // end method write
  
  /**
     Writes a line to the output text file, if one is open.
    
     @param outString Line to be written to the output text file.
   */
  public void writeLine (String outString) {  
    if (textFileOutOpen) {
      boolean ok = textFileOut.writeLine (outString);
      if (! ok) {
      } // end catch
    } // end if file is open
  } // end method writeLine
  
  /**
     Closes the output text file, if one is open.
   */
  public void close() {
    if (textFileOutOpen) {
      // textFileOutLineCount = textFileOut.getLineNumber();
      boolean ok = textFileOut.close();
    } // end if open
  } // end method close
  
  /**
     Is an output text file currently open?
    
     @return True if an output text file is ready for output.
   */
  public boolean isTextFileOutOpen() { return textFileOutOpen; }
  
  /**
     Returns outputCommandCount.
    
     @return The number of output commands found in the template
             file.
   */
  public int getOutputCommandCount() { return outputCommandCount; }
  
  /**
     Sets the new delimiters used to start a command.
    
     @param newValue Delimiters used from this point on to identify the
                     start of a new command.
   */
  public void setNlStartCommand(String newValue) { nlStartCommand = newValue; } 
  
  /**
     Sets the new delimiters used to end a command.
    
     @param newValue Delimiters used from this point on to identify the
                     end of a command.
   */
  public void setNlEndCommand(String newValue) { nlEndCommand = newValue; }
  
  /**
     Sets the new delimiters used to start a variable.
    
     @param newValue Delimiters used from this point on to identify the
                     start of a new variable.
   */
  public void setNlStartVariable(String newValue) { nlStartVariable = newValue; }
  
  /**
     Sets the new delimiters used to end a variable.
    
     @param newValue Delimiters used from this point on to identify the
                     end of a variable.
   */
  public void setNlEndVariable(String newValue) { nlEndVariable = newValue; }
  
  /**
     Sets the new delimiters used to start the variable modifiers.
    
     @param newValue Delimiters used from this point on to identify the
                     start of variable modifiers.
   */
  public void setNlStartModifiers(String newValue) { nlStartModifiers = newValue; }
  
  /**
     Sets the list Item Pending flag.
    
     @param listItemPending Indicator that there is a non-blank list item pending.
   */
  public void setListItemPending (boolean listItemPending) { 
    this.listItemPending = listItemPending; 
  }

  /**
     Return this object as some kind of string.
    
     @return Name of class plus name of template file.
   */
  public String toString () {
    return ("TemplateUtil Text File Name is "
      + templateFileName.toString ());
  }
}

