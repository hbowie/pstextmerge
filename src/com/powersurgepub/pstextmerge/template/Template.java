package com.powersurgepub.pstextmerge.template;

import com.powersurgepub.psdatalib.tabdelim.TabDelimFile;
import com.powersurgepub.psdatalib.pstextio.TextLineWriter;
import com.powersurgepub.psdatalib.psdata.RecordDefinition;
import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.DataRecord;
import com.powersurgepub.psdatalib.psdata.DataDictionary;
import com.powersurgepub.psdatalib.psdata.DirectoryReader;
  import com.powersurgepub.psutils.*;
  import com.powersurgepub.xos2.*;
  import java.io.File;
  import java.io.IOException;
  import java.util.*;

/**
   A template to be used to create text file 
   output (such as an HTML file) from a tab-delimited text file. <p>
  
   This code is copyright (c) 1999-2000 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
      2001/02/28 - Corrected logic for reading tab-delimited data to make
                   sure that last good record is processed, and no more.
      2000/05/30 - Modified to be consistent with "The Elements of Java Style".
   </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com/software/">
           www.powersurgepub.com/software</a>)
  
   @version 2002/03/25 - Added support for group processing.
 */
public class Template {
  
  /** Used to write log messages. */
  public     Logger         log;
  
  /** Next template line to be processed. */
  private    String         nextLineString;
  
  /** Global variables stored within a data record. */
  private    DataRecord     globals;
  
  /** Definition of global variables record layout. */
  private    RecordDefinition globalDefs;
  
  /** Record number of the next template line. */
  private    int            nextRecordNumber;
  
  /** Next template line to be processed. */
  private    TemplateLine   nextLine;
  
  /** Work area for a template line. */
  private    TemplateLine   recLine;
  
  /** Collection of all the template lines that need to be processed repetitively. */
  private    Vector         recLines;
	
	/** Collection of all the ifendgroup lines. */
	private		 Vector					endGroupLines;
	
	/** Flag to indicate we are in an end group block. */
	private		 boolean				endGroupBlock = false;
  
  /** Template line work area for lines at the end of the template file. */
  private    TemplateLine   endLine;
  
  /** All the lines at the end of the template file. */
  private    Vector         endLines;

  /** Simple file name for the template file. */
  private    String         templateFileSimpleName;
  
  /** File name, including path, for the template file. */
  private    String         templateFilePathAndName;
  
  /** File definition for the template file. */
  private    File           templateFileSpec;
  
  /** Template file as a XTextFile. */
  private    XTextFile       templateFile;
  
  /** Did the template file open successfully? */
  private    boolean        templateFileOK;
  
  /** Number of records so far read from the template file. */
  private    int            templateFileLineCount = 0;
  
  /** File name for the tab-delimited data file. */
  private    String         dataFileName;
  
  /** Data file as a File object. */
  private    File           dataFileSpec;
  
  /** 
     Generic definition of input data file: could be 
     a XTextFile or a DirectoryReader. 
   */
  private    DataSource     dataFile;
  
  /** Was the data file opened successfully? */
  private    boolean        dataFileOK;
  
  /** Number of records so far read from the data file. */
  private    int            dataFileRecordCount = 0;
  
  /** Data dictionary for the input data file. */
  private    DataDictionary dataDict;
  
  /** Work area for data records. */
  private    DataRecord     dataRec;
  
  /** The last data record read. */
  private    DataRecord     lastRec;
  
  /** 
     A data record with no fields (to be passed to TemplateLine
     before there are any real data records available.
   */
  private    DataRecord     nullRec;
  
  /**
     Stuff that needs to be passed to TemplateLine, and shared
     between TemplateLine objects.
   */
  private    TemplateUtil   templateUtil;

  /**
     Constructs and initializes a Template object, 
     creating a new Logger occurrence.
   */
  public Template () {
    this (new Logger (new LogOutput()));  
  }
  
  /**
     Constructs and initializes a Template object, 
     using the passed Logger occurrence.
   */
  public Template (Logger log) {
    nullRec = new DataRecord();
    this.log = log;
    templateUtil = new TemplateUtil (log);
    globals = new DataRecord();
    globalDefs = new RecordDefinition();
  }
  
  /**
     Opens the input template file.
    
     @return A boolean value indicating the success of the operation.
    
     @param inTemplateFileSpec  A File object pointing to the input 
                                template file.
   */
  public boolean openTemplate (File inTemplateFileSpec) {
    templateFileSpec = inTemplateFileSpec;
    // templateUtil.setTemplateFileName (templateFileSpec.getAbsolutePath());
    templateFile = new XTextFile (templateFileSpec);
    try {
      templateFile.openForInput ();
      templateFileOK = true;
      templateFilePathAndName = templateFile.getAbsolutePath();
      templateFileSimpleName = templateFile.getName();
      templateUtil.setTemplateFileName (templateFileSimpleName);
      templateUtil.setTemplateFilePath (templateFilePathAndName.substring
        (0, (templateFilePathAndName.length() 
          - templateFileSimpleName.length())));
      templateUtil.setTemplateParent(templateFilePathAndName.substring 
        (0, (templateFilePathAndName.length() 
          - templateFileSimpleName.length() - 1)));
    } catch (IOException e) {
      templateFileOK = false;
    }
    return templateFileOK;
  }
  
  public void setTemplateFilePath (String path) {
    templateUtil.setTemplateFilePath (path);
  }
  
  /**
     Opens the input tab-delimited data file.
     
     @return a boolean value indicating the success of the operation.
    
     @param inDataFileSpec  A File object pointing to the input 
                            tab-delimited data file.
   */
  public boolean openData (File inDataFileSpec) {
    dataFileSpec = inDataFileSpec;
    dataFileName = dataFileSpec.getAbsolutePath();
    FileName workName = new FileName (dataFileName);
    if (dataFileSpec.isFile()) {
      dataFile = new TabDelimFile (dataFileName);
      templateUtil.setDataFileDisplay (workName.getFileName());
      templateUtil.setDataFileBaseName(workName.getBase());
    } else
    if (dataFileSpec.isDirectory()) {
      DirectoryReader dr = new DirectoryReader (dataFileSpec.getAbsolutePath());
      try {
        dataFile = dr.sorted();
      } catch (IOException e) {
      }
      templateUtil.setDataFileDisplay (workName.getFolder());
      templateUtil.setDataFileBaseName(workName.getFolder());
    }
    openDataSource();
    return dataFileOK;
  }
  
  /**
     Opens the input data source.
     
     @return a boolean value indicating the success of the operation.
    
     @param inDataFile  A source of data records.
   */
  public boolean openData (DataSource inDataFile, String inFileName) {
    dataFile = inDataFile;
    templateUtil.setDataFileDisplay (inFileName);
    FileName workName = new FileName (inFileName);
    templateUtil.setDataFileBaseName(workName.getBase());
    templateUtil.setDataParent (dataFile.getDataParent());
    openDataSource();
    return dataFileOK;
  }
  
  /**
     Open the input data, once it has been turned into a data source.
   */
  private void openDataSource () {
    dataFile.setLog (log);
    dataFile.setDataLogging (true);
    dataDict = new DataDictionary();
    try {
      dataFile.openForInput (dataDict);
      dataFileOK = true;
    } catch (IOException e) {
      dataFileOK = false;
    }
  } // end method openDataSource
  
  /**
     Reads the two input files and uses them to generate
     the output file(s) resulting from the merge operation.</p>
    
     @return A boolean value indicating the success of the operation.
   */
  public boolean generateOutput () 
    throws IOException {
  
    if ((! templateFileOK) || (! dataFileOK)) {
      return false;
    }
    
    // process lines up to the NEXTREC command
    templateUtil.setSkippingData (false);
    templateUtil.setFirstTemplateLine (true);
    do {
      nextLine = nextTemplateLine ();
      templateUtil.setFirstTemplateLine (false);
      if (! templateFile.isAtEnd()) {
        nextLine.generateOutput(nullRec);
      }
    } while ((! templateFile.isAtEnd()) 
      && (! nextLine.getCommand().equals (TemplateLine.NEXTREC)));
      
    // table lines between NEXTREC and LOOP
    recLines = new Vector();
		endGroupLines = new Vector();
    do {
      nextLine = nextTemplateLine ();
      if (! templateFile.isAtEnd()) {
        if (nextLine.isCommandLine()
          && nextLine.getCommand().equals (TemplateLine.DELIMS)) {
          nextLine.generateOutput(nullRec);
        } else {
          recLines.addElement (nextLine);
					if (nextLine.isCommandLine()
						&& nextLine.getCommand().equals (TemplateLine.IFENDGROUP)) {
						endGroupBlock = true;
					}
					if (endGroupBlock) {
						endGroupLines.addElement (nextLine);
					}
					if (nextLine.isCommandLine()
						&& nextLine.getCommand().equals (TemplateLine.IFNEWGROUP)) {
						endGroupBlock = false;
					}
        }
      }
    } while ((! templateFile.isAtEnd()) 
      && (! nextLine.getCommand().equals (TemplateLine.LOOP)));
      
    // table lines after loop
    endLines = new Vector();
    do {
      nextLine = nextTemplateLine ();
      if (! templateFile.isAtEnd()) {
        if (nextLine.isCommandLine()
          && nextLine.getCommand().equals (TemplateLine.DELIMS)) {
          nextLine.generateOutput(nullRec);
        } else {
          endLines.addElement (nextLine);
        }
      }
    } while (! templateFile.isAtEnd());
      
    // process tab delimited data file
    templateUtil.setSkippingData (false);
    do {
      lastRec = dataRec;
      if (! dataFile.isAtEnd()) {
        dataRec = dataFile.nextRecordIn ();
        if (dataRec != null) {
					templateUtil.resetGroupBreaks();
          Enumeration eRecLines = recLines.elements();
          while (eRecLines.hasMoreElements()) {
            recLine = (TemplateLine)eRecLines.nextElement ();
            if (recLine.getCommand().equals (TemplateLine.OUTPUT)) {
              if (! templateUtil.isSkippingData()) {
                if (templateUtil.isTextFileOutOpen()) {
                  writeEndLines();
                } // end if text file out open
              } // end skipping Data check
            } // end if next line is OUTPUT command
            recLine.generateOutput(dataRec);
          } // end while more template record lines in vector 
        } // end dataRec not null
      } // end if more data records
    } while (! dataFile.isAtEnd());
    
		// end of data file - end all groups
		templateUtil.setSkippingData (false);
		templateUtil.resetGroupBreaks();
		templateUtil.setEndGroupsTrue (0);
		Enumeration eEndGroupLines = endGroupLines.elements();
		while (eEndGroupLines.hasMoreElements()) {
			recLine = (TemplateLine)eEndGroupLines.nextElement();
			recLine.generateOutput(nullRec);
		} // end while more end group lines
		
    dataFileRecordCount = dataFile.getRecordNumber();
    dataFile.close();
    
    if (templateUtil.isTextFileOutOpen()) {
      writeEndLines();
      templateUtil.close();
    }
    templateFileLineCount = templateFile.getLineNumber();
    templateFile.close();
    if (templateUtil.getOutputCommandCount() == 0) {
      templateUtil.recordEvent (LogEvent.MINOR,
        "No OUTPUT Command Found", false);
    } else
    if (templateUtil.getOutputCommandCount() > 1) {
      templateUtil.recordEvent (LogEvent.MINOR,
        "More than one OUTPUT Command Found", false);
    }
    // templateUtil.recordEvent (LogEvent.NORMAL,
    //   "End of TabToTemplateMerge operation", false);
    return true;
      
  } // end GenerateOutput method
  
  /**
     Writes out the last lines in the template 
     (the ones following the LOOP command) each time that 
     an output file is closed.
   */
  public void writeEndLines () {
    templateUtil.setSkippingData (false);
    Enumeration eEndLines = endLines.elements();
    while (eEndLines.hasMoreElements()) {
      endLine = (TemplateLine)eEndLines.nextElement ();
      endLine.generateOutput(lastRec);
    } // end while more template end lines in vector 
  } // end writeEndLines method
  
  /**
     Gets the next line in the template file, and returns
     it as a TemplateLine.
     
     @return The next line of the template.
   */
  private TemplateLine nextTemplateLine() 
      throws IOException {
    String       nextString = "";
    TemplateLine  nextLine = null;
    if (! templateFile.isAtEnd()) {
      nextString = templateFile.readLine();
    }
    if (! templateFile.isAtEnd()) {
      nextLine = new TemplateLine (nextString, templateUtil);
    }
    return nextLine;
  } // end method nextTemplateLine
  
  /**
     Returns the name of the last output text file opened
     in the last GenerateOutput execution.</p>
     
     @return Name of the last output text file.
   */
  public String getTextFileOutName() {
    return templateUtil.getTextFileOutName();
  }
  
  /**
     Returns the last output text file opened
     in the last GenerateOutput execution.
    
     @return Output text file.
   */
  public TextLineWriter getTextFileOut() {
    return templateUtil.getTextFileOut();
  }

  /**
     Returns the number of lines found in the input 
     template file read during the last GenerateOutput operation.</p>
      
     @return templateFileLineCount
   */
  public int getTemplateFileLineCount() {
    return templateFileLineCount;
  }
  
  /**
     Returns the number of lines found in the input 
     tabbed data file read during the last GenerateOutput operation.
    
     @return dataFileRecordCount
   */
  public int getdataFileRecordCount() {
    return dataFileRecordCount;
  }
  
  /**
     Returns the number of lines written to the last output 
     file generated during the last GenerateOutput operation.</p>
     
   @return templateUtil.textFileOutLineCount
   */
  public int getTextFileOutLineCount() {
    return templateUtil.getTextFileOutLineCount();
  }

  /**
     Returns this object as some sort of String.
    
     @return Name of the class plus name of the template input file.
   */
  public String toString () {
    return ("Template Text File Name is "
      + templateUtil.getTemplateFileName());
  }
}
