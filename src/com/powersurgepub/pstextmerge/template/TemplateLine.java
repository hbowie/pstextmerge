package com.powersurgepub.pstextmerge.template;

  import com.powersurgepub.psdatalib.psdata.*;
  import com.powersurgepub.psutils.*;
  import java.util.*;
  import java.text.*;
  
/**
   One line in a template. <p>
  
   This code is copyright (c) 1999-2003 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
      2004/06/01 -- Added logic to allow the first operand in an IF command to be recognized
                    properly even when blank and unquoted. Also added code to display template
                    line when an invalid logical operator is detected. <li>
      2003/12/19 -- Added formatting codes to control word demarcation within
                    a variable. Also modified to recognize that, when a sequence 
                    of 3 less than signs exists, the rightmost 2 should be
                    treated as the beginning of the variable. <li>
      2003/07/26 -- Added the letter 'i' (upper- or lower-case) to indicate
                    that an associated case code ('u' or 'l') should be 
                    applied only to the initial character in the variable. <li>
      2003/03/12 -- Added logic to check length variable modifier to see if
                    it is greater than current replacement value: if it is,
                    then the value will be padded on the left with zeroes. <li>
      2003/02/13 -- Added an underscore character as a special variable
                    modifier that will cause spaces to be replaced with
                    underscores. Also modified separator logic to omit  
                    spaces after forwards or backwards slashes. <li>
      2002/11/02 -- Added "today" as a special variable that will be replaced with
                    today's date. Allowed a date format to be entered as a variable
                    modifier. This follows standard Java conventions. <li>
      2002/10/05 -- Added option to include list separator as a variable
                    modifier. <li>
      2002/09/21 -- Added additional logging to show results of commands. <br>
                    Corrected bug in SET command processing. <br>
                    Added a COMMENT command. <li>
      2002/03/21 -- Added group processing for control breaks. <li>
      2000/06/18 -- Added global variables and set command. <li>
      2000/05/28 -- Modified to be consistent with "The Elements of Java Style". <li>
      1999/10/03 -- Added ability to compare two operators for equality 
                    as part of the IF command. Added DELIMS command to change 
                    delimiters from standard << and >>. <li>
      1999/09/02 -- Initial release of class. </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com">
           www.powersurgepub.com</a>)
  
   @version 
      2004/06/01 -- Added logic to allow the first operand in an IF command to be recognized
                    properly even when blank and unquoted. Also added code to display template
                    line when an invalid logical operator is detected. 
 */
public class TemplateLine {

  /** Command for specifying new delimiters. */
  final static String DELIMS    = "delims";
  
  /** Command for specifying output file name. */
  final static String OUTPUT    = "output";
  
  /** Command for specifying beginning of loop for each data record. */
  final static String NEXTREC   = "nextrec";
	
	/** Command for specifying a group field to be used to control break logic */
	final static String DEFINEGROUP = "definegroup";
	
	/** Command for indicating the beginning of lines to be output at the end of a group */
	final static String IFENDGROUP = "ifendgroup";
	
	/** Commmand for indicating the beginning of lines to be output at the beginning of a group */
	final static String IFNEWGROUP   = "ifnewgroup";
  
  final static String IFENDLIST = "ifendlist";
  
  final static String IFNEWLIST = "ifnewlist";
  
	/** Commmand for a comment line */
	final static String COMMENT   = "*";
  
  /** Command for conditional logic. */
  final static String IF        = "if";
  
  /** Command for conditional logic based on whether a value has changed. */
  final static String IFCHANGE  = "ifchange";
  
  /** Command for conditional else logic. */
  final static String ELSE      = "else";
  
  /** Command for indicating end of conditional logic. */
  final static String ENDIF     = "endif";
  
  /** Command to include text lines from a separate file. */
  final static String INCLUDE   = "include";
  
  /** Command for indiciating end of NEXTREC loop. */
  final static String LOOP      = "loop";
  
  /** Command for setting a global variable to a new value. */
  final static String SET       = "set";

  /** Command to set the epub flag on. */
  final static String EPUB      = "epub";
  
  /** Indicator to write line out without a trailing line break. */
  final static String NO_LINE_BREAK = "nobr";
  
  /** Variable name that will be replaced with the name of the template file. */
  final static String TEMPLATE_FILE_NAME_VARIABLE = "templatefilename";
  
  /** Variable name that will be replaced with the parent folder for the
      template file. */
  final static String TEMPLATE_PARENT_NAME_VARIABLE = "templateparent";
  
  /** Variable name that will be replaced with the name of the input data file. */
  final static String DATA_FILE_NAME_VARIABLE = "datafilename";

  /** Variable name that will be replaced with the name of the input data file,
   without the extension. */
  final static String DATA_FILE_BASE_NAME_VARIABLE = "datafilebasename";
  
  /** Variable name that will be replaced with the parent folder for the 
      input data file. */
  final static String DATA_PARENT_NAME_VARIABLE = "dataparent";
  
  /** Variable name that will be replaced with today's date. */
  final static String TODAYS_DATE_VARIABLE = "today";
  
  /** Default formatting string for dates. */
  final static String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";
  
  /** Delimiters used for parsing a command line into a list of parameters. */
  final static String DELIMITERS = " =,;\t\r\n";
      
  /** The delimiter string indicating the beginning of a Template command. */
  private String       startCommand = "<<";
  
  /** The delimiter string indicating the end of a Template command. */
  private String       endCommand = ">>";
  
  /** The delimiter string indicating the beginning of a Template variable. */
  private String       startVariable = "<<";
  
  /** The delimiter string indicating the end of a Template variable. */
  private String       endVariable = ">>";
  
  /** 
     The delimiter string indicating the beginning of 
     one or more variable modifier characters. 
   */
  private String       startModifiers = "&";
  
  /** A complete NEXTREC command line. */
  private String       nextrecLine 
                         = startCommand + NEXTREC + endCommand;
                         
  /** The minimum length for a command line. */
  private int          minCommandLineLength 
                         = startCommand.length() + 1 + endCommand.length();
  
  /** 
     The collection of template utility data and methods 
     passed from the owning template. 
   */
  private  TemplateUtil      templateUtil;
  
  /**
     The collection of global variables, retrieved from templateUtil.
   */
  private  DataRecord        globals;
  
  /** The definition of the operand in a set command. */
  private  DataFieldDefinition operandDef;
  
  /** The template line itself, as a simple string. */
  private  String            lineString;
  
  /** The template line, if a command string. */
  private  String            commandString;
  
  /** The template line as a string tokenizer. */
  private  StringTokenizer   tokens;
  
  /** The next token retrieved from the template line. */
  private  String            nextToken;
  
  /** Is this a command line? */
  private  boolean           commandLine;
  
  /** 
     If this line is a command, then this contains the command without its 
     surrounding delimiters. 
   */
  private  String            command;

  private  CommonMarkup      htmlConverter     = new CommonMarkup ("txt", "html");
  
  private  boolean           lineBreak = true;

  /**
     Constructs a TemplateLine, determining the type of line
     it was handed (command or non-command) and extracting the command 
     itself, if it is a command line.
     
     @param inString   this is the text line itself
     
     @param inTemplate  this is the Template object to which this line
                        belongs.
   */
  public TemplateLine (String inString, TemplateUtil templateUtil) {
    String nonCommand = StringUtils.trimRight (inString);
    lineString = inString.trim();
    if (templateUtil.isFirstTemplateLine()
        && lineString.length() >= 2) {
      String firstTwo = lineString.substring (0, 2);
      if (firstTwo.equals ("<?")) {
        templateUtil.setNlStartCommand ("<?");
        templateUtil.setNlEndCommand ("?>");
        templateUtil.setNlStartVariable ("=$");
        templateUtil.setNlEndVariable ("$=");
        templateUtil.setNlStartModifiers ("&");
      }
    } 
    this.templateUtil = templateUtil;
    globals = templateUtil.getGlobals();
    operandDef  = new DataFieldDefinition ("operand");
    startCommand   = templateUtil.getNlStartCommand();
    endCommand     = templateUtil.getNlEndCommand();
    startVariable  = templateUtil.getNlStartVariable();
    endVariable    = templateUtil.getNlEndVariable();
    startModifiers = templateUtil.getNlStartModifiers();
    command = GlobalConstants.EMPTY_STRING;
    if ((lineString.length() >= minCommandLineLength)
      && (lineString.startsWith (startCommand))
      && (lineString.endsWith (endCommand))) {
      commandString = lineString.substring 
        (startCommand.length(), 
          (lineString.length() - endCommand.length()));
      tokens = new StringTokenizer (commandString, DELIMITERS, true);
      command = GlobalConstants.EMPTY_STRING;
      nextToken = " ";
      while (tokens.hasMoreTokens() 
        && (DELIMITERS.indexOf(nextToken) > -1)) {
        nextToken = tokens.nextToken();
      }
      nextToken = nextToken.toLowerCase ();
      if (nextToken.equals (NEXTREC)
        || nextToken.equals (LOOP)
        || nextToken.equals (OUTPUT) 
        || nextToken.equals (DELIMS)
        || nextToken.equals (INCLUDE)
        || nextToken.equals (EPUB)
        || nextToken.equals (IF) 
        || nextToken.equals (IFCHANGE)
        || nextToken.equals (ELSE)
        || nextToken.equals (ENDIF)
        || nextToken.equals (SET) 
				|| nextToken.equals (DEFINEGROUP)
				|| nextToken.equals (IFNEWGROUP)
				|| nextToken.equals (IFENDGROUP)
        || nextToken.equals (IFNEWLIST)
				|| nextToken.equals (IFENDLIST)
        || nextToken.equals (COMMENT)) {
        commandLine = true;
        command = nextToken;
      } // end if valid command
    } // end if possible command
    if (! commandLine) {
      lineString = nonCommand;
    }
  } // end TemplateLine constructor
  
  /**
     Generates the output associated with a TemplateLine. 
     For an output command, this will consist of closing the current 
     output file (if one is already open) and opening the new one,
     using as a file name the data supplied with the output command
     (which may, in turn, have contained variable data that was replaced
     with its corresponding data values). For a non-command line, 
     appropriate output is the writing of the line itself, after variable
     substitution has been accomplished.
    
     @param dataRec  a collection of fields supplying substitution data
                     for variables in a template line.
   */  
  public void generateOutput (DataRecord dataRec) {

    String outString = lineString;
    
    // now do something to the output file
    if (this.isCommandLine()) {
      String operands = outString.substring
        ((startCommand.length() + this.command.length()), 
          (outString.length() - endCommand.length())).trim();
      templateUtil.tailorEvent (LogEvent.NORMAL,
        "Processing " + command + " Command", true);
      StringScanner operandScanner = new StringScanner (operands);
      
      // DELIMS Command
      if (this.command.equals (DELIMS)) {
        templateUtil.recordEvent();
        int delimsCount = 1;
        while (operandScanner.moreChars()) {
          String delim = operandScanner.extractQuotedString();
          switch (delimsCount) {
            case 1:
              templateUtil.setNlStartCommand (delim);
              break;
            case 2:
              templateUtil.setNlEndCommand (delim);
              break;
            case 3:
               templateUtil.setNlStartVariable (delim);
               break;
             case 4:
               templateUtil.setNlEndVariable (delim);
               break;
             case 5:
              templateUtil.setNlStartModifiers (delim);
              break;
            default:
              templateUtil.recordEvent (LogEvent.MINOR,
                "DELIM Command has excess parameters: " + delim, true);
          } // end switch 
          delimsCount++;
        } // end while
        templateUtil.recordEvent (LogEvent.NORMAL,
            "DELIM Command Results: " 
                + templateUtil.getNlStartCommand() + " "
                + templateUtil.getNlEndCommand() + " "
                + templateUtil.getNlStartVariable() + " "
                + templateUtil.getNlEndVariable() + " "
                + templateUtil.getNlStartModifiers(), 
                    true);
      } // end delims command processing
      
      // OUTPUT Command
      if (this.command.equals (OUTPUT)) {
        if (! templateUtil.isSkippingData()) {
          // templateUtil.recordEvent();
          templateUtil.setTextFileOutName 
              (replaceVarsInOperand (operandScanner, dataRec));
        } // end skippingData test
      } // end output command processing
      else
        
      // INCLUDE Command
      if (this.command.equals (INCLUDE)) {
        if (! templateUtil.isSkippingData()) {
          String includeFile = replaceVarsInOperand (operandScanner, dataRec);
          String includeParm = replaceVarsInOperand (operandScanner, dataRec);
          templateUtil.includeFile (includeFile, includeParm);
        } // end skippingData test
      } // end include command processing
      else

      // EPUB Command
      if (this.command.equals (EPUB)) {
        templateUtil.setEpub (true);
        templateUtil.setEpubSite (replaceVarsInOperand (operandScanner, dataRec));
      }
      else
        
      // ELSE Command
      if (this.command.equals (ELSE)) {
        templateUtil.anElse();
      }
      else

      // ENDIF Command
      if (this.command.equals (ENDIF)) {
        templateUtil.anotherEndIf();
      } // end endif command processing
      else
      
      // IF Command
      if (this.command.equals (IF)) {
        if (templateUtil.isSkippingData()) {
          templateUtil.anotherIf();
        } else {
          String opcode = "!=";
          DataField operand1 = new DataField (operandDef, "");
          DataField operand2 = new DataField (operandDef, "");
          int opCount = 0;
          boolean ifResult = false;
          while (operandScanner.moreChars()) {
            String op 
              = replaceVarsInOperand (operandScanner, dataRec);
            opCount++;
            
            // If first operand looks like a logical operator,
            // then assume that data operand was an unquoted empty string,
            // and treat this as a logical operator.
            if (opCount == 1 
                && op.length() >= 1
                && op.length() <= 2
                && (! Character.isLetter (op.charAt (0)))
                && (! Character.isDigit  (op.charAt (0)))) {
              int opIndex = -1;
              for (opIndex = 0;
                (opIndex < DataField.NUMBER_OF_LOGICAL_OPERANDS)
                  && (! op.equals 
                      (DataField.SYMBOL_LOGICAL_OPERANDS [opIndex]));
                  opIndex++) {
                // Do nothing -- for statement does all the work
              } // end for every possible logical operand using symbols
              if (opIndex >= DataField.NUMBER_OF_LOGICAL_OPERANDS) {
                for (opIndex = 0;
                  (opIndex < DataField.NUMBER_OF_LOGICAL_OPERANDS)
                    && (! op.equals 
                        (DataField.ALT_SYMBOL_LOGICAL_OPERANDS [opIndex]));
                    opIndex++) {
                  // Do nothing -- for statement does all the work
                } // end for every possible alt symbol operands
              } // end operator not found among symbol operands
              if (opIndex < DataField.NUMBER_OF_LOGICAL_OPERANDS) {
                opCount = 2;
              }
            } // end possible operator
            
            switch (opCount) {
              case 1:
                operand1.setData (op);
                break;
              case 2:
                opcode = op;
                break;
              default:
                operand2.setData (op);
                boolean thisIf;
                try {
                  thisIf = operand1.operateLogically (opcode, operand2);
                  /* templateUtil.recordEvent (LogEvent.NORMAL,
                    "IF Command Results: " 
                    + String.valueOf (thisIf) + " ("
                    + operand1.toString() + " "
                    + opcode + " "
                    + operand2.toString() + ")", 
                    false); */
                }
                catch (IllegalArgumentException e) {
                  templateUtil.recordEvent (LogEvent.MEDIUM, 
                    "Illegal logical operator (" + opcode + ")"
                    + " for line " + outString, true);
                  thisIf = true;
                }
                if (thisIf) {
                  ifResult = true;
                }
                break;
            } // end switch
          } // end while more chars for if command
          if (opCount < 2) {
            ifResult = (operand1.length() > 0);
          } else
          if (opCount < 3) {
            boolean thisIf;
            try {
              thisIf = operand1.operateLogically (opcode, operand2);
            }
            catch (IllegalArgumentException e) {
              templateUtil.recordEvent (LogEvent.MEDIUM, 
                "Illegal logical operator (" + opcode + ")", true);
              thisIf = true;
            } // end try/catch
            ifResult = thisIf;
          }
          templateUtil.setSkippingData (! ifResult);
        }
      } // end if command processing
      else
      
      // IFCHANGE Command
      if (this.command.equals (IFCHANGE)) {
        if (templateUtil.isSkippingData()) {
          templateUtil.anotherIf();
        } else {
          templateUtil.setIfChangeData 
              (replaceVarsInOperand (operandScanner, dataRec));
        }
      } // end ifchange command processing
      else
      
      // SET Command
      if (this.command.equals (SET)) {
        if (! templateUtil.isSkippingData()) {
          String opcode = "";
          String global = "";
          DataField operand1 = new DataField (operandDef, "");
          int opCount = 0;
          while (operandScanner.moreChars()) {
            String op 
              = replaceVarsInOperand (operandScanner, dataRec);
            opCount++;
            switch (opCount) {
              case 1:
                global = op;
                break;
              case 2:
                opcode = op;
                break;
              case 3:
                operand1.setData (op);
                break;
              default:
                templateUtil.recordEvent (LogEvent.MINOR,
                  "SET Command has excess parameters: " + op, false);
                break;
            } // end switch
          } // end while more chars for set command
          // System.out.println("TemplateLine.GenerateOutput set command with "
          //     + String.valueOf(opCount) + " operands, "
          //     + "global variable = " + global
          //     + " op code = " + opcode
          //     + " operand 1 = " + operand1);
          if (opCount < 2) {
            templateUtil.recordEvent (LogEvent.MINOR,
                  "SET Command does not have enough parameters", false);
          } else {
            DataField globalField;
            int globalColumn = globals.getColumnNumber(global);
            // System.out.println("  global column = " + String.valueOf(globalColumn));
            if (globalColumn >= 0) {
              globalField = globals.getField (globalColumn);
            }
            else {
              DataFieldDefinition globalDef = new DataFieldDefinition (global);
              globalField = new DataField (globalDef, "0");
              globals.addField (globalField);
            }
            if (opCount == 2) {
              globalField.operate (opcode);
            } else {
              globalField.operate (opcode, operand1);
            }
            /* templateUtil.recordEvent (LogEvent.NORMAL,
            "SET Command Results: " 
                + global + " = "
                + globals.getField(global).getData(), 
                    false); */
          } // end processing SET operands
        } // end if not skipping data
      } // end SET command processing
			else
      
      // DEFINEGROUP Command
      if (this.command.equals (DEFINEGROUP)) {
        templateUtil.clearIfs();
				int groupNumber = 0;
				String groupValue = "";
				if (operandScanner.moreChars()) {
					groupNumber = operandScanner.extractInteger(templateUtil.MAX_GROUPS); 
        }
				if (operandScanner.moreChars()) {
					groupValue = replaceVarsInOperand (operandScanner, dataRec);
				}
				if ((groupNumber > 0) && (groupNumber <= templateUtil.MAX_GROUPS)) {
					templateUtil.setGroup (groupNumber - 1, groupValue);
				}
			} // end DEFINEGROUP command processing
			else
      
      // IFENDGROUP Command
      if (this.command.equals (IFENDGROUP)) {
        templateUtil.clearIfs();
        int groupNumber = 0;
        if (operandScanner.moreChars()) {
          groupNumber = operandScanner.extractInteger(templateUtil.MAX_GROUPS); 
        }
        if ((groupNumber > 0) && (groupNumber <= templateUtil.MAX_GROUPS)) {
          templateUtil.setIfEndGroup (groupNumber - 1);
        }
			} // end IFENDGROUP command processing
			else
      
      // IFNEWGROUP Command
      if (this.command.equals (IFNEWGROUP)) {
        templateUtil.clearIfs();
        int groupNumber = 0;
        if (operandScanner.moreChars()) {
          groupNumber = operandScanner.extractInteger(templateUtil.MAX_GROUPS); 
        }
        if ((groupNumber > 0) && (groupNumber <= templateUtil.MAX_GROUPS)) {
          templateUtil.setIfNewGroup (groupNumber - 1);
        }
			} 
      else 
      
      // IFENDLIST Command
      if (this.command.equals (IFENDLIST)) {
        templateUtil.clearIfs();
        int groupNumber = 0;
        if (operandScanner.moreChars()) {
          groupNumber = operandScanner.extractInteger(templateUtil.MAX_GROUPS); 
        }
        if ((groupNumber > 0) && (groupNumber <= templateUtil.MAX_GROUPS)) {
          templateUtil.setIfEndList (groupNumber - 1);
        }
			} // end IFENDLIST command processing
			else
      
      // IFNEWLIST Command
      if (this.command.equals (IFNEWLIST)) {
        templateUtil.clearIfs();
        int groupNumber = 0;
        if (operandScanner.moreChars()) {
          groupNumber = operandScanner.extractInteger(templateUtil.MAX_GROUPS); 
        }
        if ((groupNumber > 0) && (groupNumber <= templateUtil.MAX_GROUPS)) {
          templateUtil.setIfNewList (groupNumber - 1);
        }
			} // end IFNEWLIST command processing
    } // end command line processing
    else 
    
    // not a command line
    { 
      if (templateUtil.isSkippingData()) {
        // do nothing
      }
      else {
        if (lineBreak) {
          templateUtil.writeLine 
              (replaceVariables (new StringBuilder(outString), dataRec));
        } else {
          templateUtil.write 
              (replaceVariables (new StringBuilder(outString), dataRec));
        }
      } // end not skipping data
    } // end non-command line
  } // end generateOutput method
  
  /**
   Get next quoted string from StringScanner, then replace any variables
   found within. 
  
   @param opScanner The string to be scanned for the next operand. 
   @param dataRec   The data record containing the variable values. 
  
   @return The next operand, with variable replacements completed. 
  */
  private String replaceVarsInOperand(StringScanner opScanner, DataRecord dataRec) {
    return replaceVariables (
        new StringBuilder(opScanner.extractQuotedString()),
        dataRec);
  }
  
  /**
   Replace any variables found in passed data. 
  
   @param str The StringBuilder to have its variables replaced. 
  */
  private String replaceVariables (StringBuilder str, DataRecord dataRec) {
    
    int varIndex = 0;
    while ((varIndex >= 0) && (varIndex < str.length())) {
      // find the beginning of the next variable
      int startDelim = str.indexOf (startVariable, varIndex);
      // If a variable starting delimiter also begins 1 character to the right,
      // then use that instead
      if (startDelim >= 0) {
        int startDelim2 = str.indexOf (startVariable, startDelim + 1);
        if (startDelim2 == (startDelim + 1)) {
          startDelim = startDelim2;
        }
      }
      if (startDelim < 0) {
        varIndex = startDelim;
      } else {
        // if beginning found, now find the end
        int endDelim = str.indexOf 
          (endVariable, startDelim + startVariable.length() + 1);
        if (endDelim < 0) {
          varIndex = endDelim;
        } else {
          // found beginning and end of variable -- process it
          int endVar = endDelim;
          // find beginning of variable modifiers, if any
          int startMods = str.indexOf
            (startModifiers, startDelim + startVariable.length() + 1);
          int leadingCount = 0;
          int caseCode = 0;
          boolean initialCase = false;
          char listSep = ' ';
          boolean formatStringFound = false;
          boolean underscoreFound = false;
          StringBuilder formatStringBuf = new StringBuilder();
          Date date = null;

          boolean xml = false;
          boolean html = false;
          boolean fileBaseName = false;
          boolean keepRight = false;
          boolean convertLinks = false;
          boolean makeFileName = false;
          boolean noBreaks = false;
          boolean noPunctuation = false;
          String formatString;
          
          boolean demarcation = false;
          int firstCase = 0;
          int leadingCase = 0;
          int normalCase = 0;
          int caseCount = 0;
          StringBuilder delimiter = new StringBuilder();
          
          // if we found any variable modifiers, then collect them now
          if ((startMods > 0) && (startMods < endDelim)) {
            endVar = startMods;
            for (int i = startMods + 1; i < endDelim; i++) {
              char workChar = str.charAt (i);
              if (Character.toLowerCase (workChar) == 'c') {
                demarcation = true;
              } else
              if (demarcation) {
                int wordCase = -2;
                if (Character.toLowerCase (workChar) == 'u') {
                  wordCase = 1;
                } else
                if (Character.toLowerCase (workChar) == 'l') {
                  wordCase = -1;
                } else
                if (Character.toLowerCase (workChar) == 'a') {
                  wordCase = 0;
                }
                if (wordCase > -2) {
                  caseCount++;
                  switch (caseCount) {
                    case 1:
                      firstCase = wordCase;
                      break;
                    case 2:
                      leadingCase = wordCase;
                      break;
                    default:
                      normalCase = wordCase;
                      break;
                  }
                } else {
                  delimiter.append (workChar);
                }
              } else
              if (Character.isDigit (workChar)) {
                leadingCount = (leadingCount * 10)
                  + Character.getNumericValue (workChar);
              } else
              if (Character.toLowerCase (workChar) == 'l') {
                caseCode = -1;
              } else
              if (Character.toLowerCase (workChar) == 'u') {
                caseCode = +1;
              } else
              if (Character.toLowerCase (workChar) == 'i') {
                initialCase = true;
              } else
              if (Character.toLowerCase (workChar) == 'x') {
                xml = true;
              } else
              if (workChar == 'h') {
                html = true;
              } else
              if (Character.toLowerCase (workChar) == 'b') {
                fileBaseName = true;
              } else
              if (Character.toLowerCase (workChar) == 'r') {
                keepRight = true;
              } else
              if (Character.toLowerCase(workChar) == 'j') {
                convertLinks = true;
              }
              else
              if (Character.toLowerCase(workChar) == 'f') {
                makeFileName = true;
              } else
              if (Character.toLowerCase(workChar) == 'n') {
                noBreaks = true;
              } else
              if (Character.toLowerCase(workChar) == 'p') {
                noPunctuation = true;
              } else
              if (formatStringFound) {
                formatStringBuf.append (workChar);
              } else
              if (Character.isLetter (workChar)) {
                formatStringFound = true;
                formatStringBuf.append (workChar);
              } else
              if (workChar == '_') {
                underscoreFound = true;
              } else
              if (! Character.isLetterOrDigit (workChar)) {
                listSep = workChar;
              }
            }
          } // end of variable modifier processing
          
          // get variable name and replacement value
          String variable = str.substring
            ((startDelim + startVariable.length()), endVar);
          CommonName common = new CommonName (variable);
          variable = common.getCommonForm();
          String replaceData = GlobalConstants.EMPTY_STRING;
          if (variable.equals (NO_LINE_BREAK)) {
            lineBreak = false;
          } else
          if (variable.equals (TEMPLATE_FILE_NAME_VARIABLE)) {
            replaceData = templateUtil.getTemplateFileName();
          } else
          if (variable.equals (TEMPLATE_PARENT_NAME_VARIABLE)) {
            replaceData = templateUtil.getTemplateParent();
          } else
          if (variable.equals (DATA_FILE_NAME_VARIABLE)) {
            replaceData = templateUtil.getDataFileDisplay();
          } else
          if (variable.equals (DATA_FILE_BASE_NAME_VARIABLE)) {
            replaceData = templateUtil.getDataFileBaseName();
          }else
          if (variable.equals (DATA_PARENT_NAME_VARIABLE)) {
            replaceData = templateUtil.getDataParent();
          } else 
          if (variable.equals (TODAYS_DATE_VARIABLE)) {
            date = Calendar.getInstance().getTime();
          } else
          if (globals.containsField (variable)) {
            replaceData = globals.getFieldData (variable);
          } else {
            replaceData = dataRec.getFieldData (variable);
          }
          
          // transform replacement value according to variable modifiers
          if ((replaceData != null) 
              && (replaceData.length() > 0)
              ) {
            
            if (leadingCount > 0) {
              if (leadingCount < replaceData.length()) {
                if (keepRight) {
                  replaceData = replaceData.substring (replaceData.length() - leadingCount);
                } else {
                  replaceData = replaceData.substring (0, leadingCount);
                }
              } else {
                while (leadingCount > replaceData.length()) {
                  replaceData = "0" + replaceData;
                }
              }
            } // end if leadingCount > 0
            
            if (initialCase) {
              StringBuilder work = new StringBuilder ("");
              if (replaceData.length() > 0) {
                if (caseCode > 0) {
                  work.append (replaceData.substring(0,1).toUpperCase());
                } else
                if (caseCode < 0) {
                  work.append (replaceData.substring(0,1).toLowerCase());
                }
                if (replaceData.length() > 1) {
                  work.append (replaceData.substring (1));
                }
              } // end if replaceData length > 0
              replaceData = work.toString();
            } else {
              if (caseCode > 0) {
                replaceData = replaceData.toUpperCase();
              } 
              else
              if (caseCode < 0) {
                replaceData = replaceData.toLowerCase();
              }
            } // end if not initialCase
            
            if (makeFileName) {
              replaceData = StringUtils.makeFileName(replaceData.trim(), false);
            }
            if (underscoreFound) {
              replaceData = StringUtils.replaceChars 
                  (replaceData.trim(), " ", "_");
            }
            if (demarcation) {
              replaceData = StringUtils.wordDemarcation 
                  (replaceData, delimiter.toString(), firstCase, leadingCase, normalCase);
            }
            if (noBreaks) {
              replaceData = templateUtil.noBreaks(replaceData);
            }
            if (noPunctuation) {
              replaceData = StringUtils.purifyPunctuation(replaceData);
            }
          } // end if replaceData non-blank
          
          if (listSep == ' ') {
            templateUtil.setListItemPending (false);
          } 
          else 
          if ((replaceData != null) && (replaceData.length() > 0)) {
            if (templateUtil.isListItemPending()) {
              if (listSep == '/' || listSep == '\\') {
                replaceData = String.valueOf(listSep) + replaceData;
              } else {
                replaceData = String.valueOf(listSep) + " " + replaceData;
              }
            }
            templateUtil.setListItemPending (true);
          }
          
          if (formatStringFound || date != null) {
            if (date == null) {
              StringScanner dateString = new StringScanner (replaceData);
              date = dateString.getDate("mdy");
            }
            if (formatStringBuf.length() > 0) {
              formatString = formatStringBuf.toString();
            } else {
              formatString = DEFAULT_DATE_FORMAT;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat (formatString);
            replaceData = dateFormat.format (date);
          }
          
          if (xml) {
            StringConverter xmlConverter = StringConverter.getXML();
            replaceData = xmlConverter.convert (replaceData);
          }

          if (html) {
            replaceData = htmlConverter.markup (replaceData, true);
          }
          
          if (convertLinks) {
            replaceData = StringUtils.convertLinks (replaceData);
          }
          
          if (fileBaseName) {
            FileName fn = new FileName (replaceData);
            replaceData = fn.getBase();
          }
          
          // now perform the variable replacement
          str.delete(startDelim, endDelim + endVariable.length());
          str.insert(startDelim, replaceData);
          varIndex = startDelim + replaceData.length();
        } // end processing when ending delimiters found
      } // end processing when starting delimiters found
    } // end processing of all variables in line
    
    return str.toString();
  }
  
  /**
     Returns the command extracted from a command line.
    
     @return Command without its surrounding delimiters.
   */
  public String getCommand () { return command; }
  
  /**
     Is this a command line?
    
     @return True if this is a command line. 
   */
  public boolean isCommandLine() { return commandLine; }
  
  /**
     Returns this object as some kind of string.
    
     @return The entire original line as a string.
   */
  public String toString () {
    return lineString;
  }
  
} // enc class TemplateLine
