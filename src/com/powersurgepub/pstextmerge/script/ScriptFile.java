package com.powersurgepub.pstextmerge.script;

import com.powersurgepub.psdatalib.tabdelim.TabDelimFile;
import com.powersurgepub.psdatalib.psdata.RecordDefinition;
import com.powersurgepub.psdatalib.psdata.DataRecord;
import com.powersurgepub.psdatalib.psdata.DataDictionary;
  import java.io.*;
  import java.net.*;
  import com.powersurgepub.psutils.*;

/**
   A disk file of script action records, stored as tab-delimited records. 
   Records are passed to and from this class in ScriptAction format. <p>
   
   This code is copyright (c) 2000 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
      2002/08/14 - Modified to store file names relative to the location of the 
                         script file, if they are within the same folder.
      2000/12/23 - Initially written for TDFCzar project
         </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com/software/">
           www.powersurgepub.com/software</a>)
  
   @version 
      2002/08/14 - Modified to store file names relative to the location of the 
                   script file, if they are within the same folder.
 */
public class ScriptFile {
  
  /** Tab-Delimited File. */
  private     TabDelimFile      dataFile;
  
  /** Data record used as input/output for the Tab-Delimited File. */
  private     DataRecord        dataRec;
  
  /** Script Record. */
  private     ScriptAction      scriptAction;
  
  /** Data dictionary to be used for the file. */
  private    DataDictionary     dict;
  
  /** Record definition to be used for the file. */
  private    RecordDefinition   recDef;
  
  /** Sequential number identifying last record read or written. */
  private    int                recordNumber;
  
  /** File Name from which we can extract the path and extension. */
  private    FileName           fileNameObject;
  
  /** Path information for the tab-delimited file. */
  private		 String							dataPath = "";
  
  /** Path information for the template folder. */
  private    String             templateLibrary = "";
  
  /** File name extension for the tab-delimited file. */
  private    String             fileNameExt = "";
  
  /**
     A constructor that accepts a path and file name.
    
     @param path      A path to the directory containing the file.
    
     @param fileName  The file name itself (without path info).
   
     @param templateLibrary The location of the template library.
   */
  public ScriptFile (String inPath, String inFileName, String templateLibrary) {
    dataFile = new TabDelimFile (inPath, inFileName);
    this.templateLibrary = templateLibrary;
    commonConstruction();
  }
  
  /**
     A constructor that accepts a file name.
    
     @param inFileName  A file name.
   
     @param templateLibrary The location of the template library.
   */
  public ScriptFile (String inFileName, String templateLibrary) {
    dataFile = new TabDelimFile (inFileName);
    this.templateLibrary = templateLibrary;
    commonConstruction();
  }
  
  /**
     A constructor that accepts two parameters: a File object
     representing the path to the file, and a String containing the file
     name itself.
     
     @param inPathFile  A path to the directory containing the file
     
     @param inFileName  The file name itself (without path info).
   
     @param templateLibrary The location of the template library.
   */
  public ScriptFile (File inPathFile, String inFileName, String templateLibrary) {
    dataFile = new TabDelimFile (inPathFile, inFileName);
    this.templateLibrary = templateLibrary;
    commonConstruction();
  }
  
  /**
     A constructor that accepts a single File object
     representing the file.</p>
     
     @param inFile  The text file itself.
   
     @param templateLibrary The location of the template library.
   */
  public ScriptFile (File inFile, String templateLibrary) {
    dataFile = new TabDelimFile (inFile);
    this.templateLibrary = templateLibrary;
    commonConstruction();
  }
  
  /**
     A constructor that accepts a URL
     representing the file.</p>
     
     @param url  A URL pointing to the text file itself.
   
     @param templateLibrary The location of the template library.
   */
  public ScriptFile (URL url, String templateLibrary) {
    dataFile = new TabDelimFile (url);
    this.templateLibrary = templateLibrary;
    commonConstruction();
  }
  
  /**
     Common code to be executed for all constructors.
   */
  private void commonConstruction () {
    fileNameObject = new FileName (dataFile.getFilePathAndName());
    dataPath = fileNameObject.getPath();
  }
  
  /**
     Sets the Logger object to be used for logging. 
    
     @param log The Logger object being used for logging significant events.
   */
  public void setLog (Logger log) {
    dataFile.setLog (log);
  }
  
  /**
     Opens the script file for subsequent input. 
    
     @throws IOException If the input file is not found, or if there
                         is trouble reading it. 
   */
  public void openForInput () 
      throws IOException {
    dataFile.openForInput ();
  }
  
  /**
     Opens the script file for subsequent input. 
    
     @param  dict Data dictionary to be used by this file.
    
     @throws IOException If the input file is not found, or if there
                         is trouble reading it. 
   */
  public void openForInput (DataDictionary dict) 
      throws IOException {
    dataFile.openForInput (dict);
  }
    
  /**
     Returns the next record in the input file as a script action.
    
     @return Next script action, built from tab-delimited record.
    
     @throws IOException If there is an error reading the file.
   */
  public ScriptAction nextRecordIn () 
      throws IOException {
    dataRec = dataFile.nextRecordIn();
    if (dataRec == null) {
      return null;
    } else {
      scriptAction = new ScriptAction (dataRec);
      scriptAction.restorePath (dataPath, templateLibrary);
      return scriptAction;
    }
  }
  
  public boolean isAtEnd () {
    return dataFile.isAtEnd();
  }
  
  /**
     Opens the script file for subsequent output.
    
     @throws IOException If there is trouble opening the disk file.
   */
  public void openForOutput () {
    try {
      dataFile.openForOutput (ScriptAction.getRecDef());
    } catch (IOException e) {
    }
  }
    
  /**
     Writes the script action to the output disk file.
    
     @param scriptAction Next ScriptAction to be written out.
    
     @throws IOException If there is trouble writing the record.
   */
  public void nextRecordOut (ScriptAction scriptAction) {
    scriptAction.removePath (dataPath, templateLibrary);
    dataRec = scriptAction.getDataRec();
    try {
      dataFile.nextRecordOut (dataRec);
    } catch (IOException e) {
    }
  }
  
  /**
     Returns the name of the file.
    
     @return Name of file
   */
  public String getFileName () {
    return dataFile.getFileName();
  } // end getFileName method
  
  /**
     Returns the record definition for the file.
    
     @return Record definition for this tab-delimited file.
   */
  public RecordDefinition getRecDef () {
    return ScriptAction.getRecDef();
  }
  
  /**
     Returns the record number of the last record
     read or written.
    
     @return Number of last record read or written.
   */
  public int getRecordNumber () {
    return dataFile.getRecordNumber();
  }
  
  /**
     Closes the script file.
   */
  public void close () {
    try {
      dataFile.close();
    } catch (IOException e) {
    }
  }
  
  /**
     Returns this object as some sort of string.
    
     @return Name of this disk file.
   */
  public String toString () {
    return ("Script File Name is "
      + dataFile.toString ());
  }
}
