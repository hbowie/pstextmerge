package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.*;
  import com.powersurgepub.psdatalib.txbio.*;
  import com.powersurgepub.psutils.*;
  import java.io.*;
  import java.util.*;
  import java.text.*;

/**
   A directory reader that returns DataSource input from
   multiple files. 
   
 */
 public class TextMergeDirectoryReader 
    extends     File
    implements  DataSource {
  
  /** 
     The number of levels of directories and sub-directories to be read. 
     A value of 1 (the default) indicates that only the top level directory
     should be read. A value of 2 indicates one level of sub-directories, and
     so forth.
   */
  private		 int							maxDepth = 1;
  
  private		 DirToExplode			newDirToExplode;
  
  private		 int							currDirDepth;
  
  private		 File							currDirAsFile;
  
  private    ArrayList<DirToExplode> dirList;
  
  private		 int							dirNumber;		
  
  private		 ArrayList<String> dirEntries;
  
  private		 int							entryNumber;
  
  private    String						nextDirEntry;
  
  private    DataSource       dataSource = null;
  
  private    DataRecord       dataRec = null;

  /** The logger to use to log events. */    
  private    Logger           log;
  
  /* Let's not log all data. */
  private    boolean          dataLogging = false;
  
  /** Debug instance. */
  private		 Debug						debug = new Debug (false);
  
  /** The data dictionary to be used by this record. */
  private    DataDictionary   dict;
  
  /** The record definition to be used by this record. */
  private    RecordDefinition recDef;
  
  /** Pointer to a particular record within the array. */
  private    int              recordNumber;
  
  /** Data to be sent to the log. */
  private    LogData          logData;
  
  /** An event to be sent to the log. */
  private    LogEvent         logEvent;
  
  /** The identifier for this reader. */
  private    String           fileId;
  
  /** The directory to be read. */
  private    String           directoryPath;
  
  /** The number of directories in the top directory to be read. */
  private		 int							directoryNumberOfFolders;
  
  private    TextMergeInputModule inputModule = null;

  /**
     Constructs a directory reader given a path defining the directory
     to be read.
    
     @param  inPath Directory path to be read.
   */
  public TextMergeDirectoryReader (String inPath) {
    super (inPath);
    directoryPath = inPath;
    initialize();
  }

  /**
     Constructs a directory reader given a file object 
     defining the directory to be read.
    
     @param  inPathFile Directory path to be read.
   */
  public TextMergeDirectoryReader (File inPathFile) {
    super (inPathFile.getAbsolutePath());
    directoryPath = this.getAbsolutePath();
    initialize();
  }
  
  /**
     Performs standard initialization for all the constructors.
     By default, fileId is set to "directory".
   */
  private void initialize () {
    FileName dirName = new FileName (directoryPath, FileName.DIR_TYPE);
    directoryNumberOfFolders = dirName.getNumberOfFolders();
    fileId = "directory";
    logData = new LogData ("", fileId, 0);
    logEvent = new LogEvent (0, "");
  }
  
  /**
   Provide a Text Merge Input Module to be used to select and process
   eligible input files. 
  
   @param inputModule The input module to be used to select and 
                      process eligible input files.
  */
  public void setInputModule(TextMergeInputModule inputModule) {
    this.inputModule = inputModule;
  }
    
  /**
     Opens the reader for input using a newly defined
     data dictionary.
    
     @throws IOException If there are problems reading the directory.
   */
  public void openForInput () 
      throws IOException {
    openForInput (new DataDictionary());
  }
  
  /**
     Opens for input with the supplied record definition.
    
     @param  inRecDef Record definition already constructed.
    
     @throws IOException If there are problems reading the directory.
   */
  public void openForInput (RecordDefinition inRecDef) 
      throws IOException {
    openForInput (inRecDef.getDict());
  } // end of openForInput method
  
  /**
     Opens for input with the supplied data dictionary.
    
     @param  inDict Data dictionary already constructed.
    
     @throws IOException If there are problems reading the directory.
   */
  public void openForInput (DataDictionary inDict) 
      throws IOException {
    ensureLog();
    dict = inDict;
    recDef = new RecordDefinition (dict);
    dirList = new ArrayList();
    newDirToExplode = new DirToExplode (1, directoryPath);
    dirList.add (newDirToExplode);
    dirNumber = -1;
    dirEntries = new ArrayList();
    entryNumber = -1;
    recordNumber = 0;
    
    dataSource = null;
    dataRec = null;
    while (dataSource == null && (! isAtEnd())) {
      nextDataSource();
    }
  } // end of openForInput method
  
  public DataRecord nextRecordIn() {
    dataRec = null;
    while (dataRec == null && (! isAtEnd())) {
      nextDataRec();
    }
    if (dataRec != null) {
      recordNumber++;
    }
    return dataRec;
  }
  
  /**
   Try to return the next available data record.
  */
  private void nextDataRec() {
    dataRec = null;
    if (dataSource == null) {
      nextDataSource();
    } 
    else
    if (dataSource.isAtEnd()) {
      nextDataSource();
    } else {
      try {
        dataRec = dataSource.nextRecordIn();
      } catch (IOException e) {
        dataRec = null;
      }
    }
  }
  
  /**
   Get the next data source, if we have any more.
  */
  private void nextDataSource() {
    closeLastDataSource();
    dataSource = null;
    entryNumber++;
    if (entryNumber >= dirEntries.size()) {
      nextDirectory();
    } else {
      nextDirEntry = dirEntries.get (entryNumber);
      File dirEntryFile = new File (currDirAsFile, nextDirEntry);
      if (dirEntryFile.isDirectory()) {
        if (currDirDepth < maxDepth) {
          newDirToExplode = new DirToExplode 
              (currDirDepth + 1, dirEntryFile.getAbsolutePath());
          dirList.add (newDirToExplode);
        }
      } 

      if (inputModule != null) {
        if (inputModule.isInterestedIn(dirEntryFile)) {
          dataSource = inputModule.getDataSource(dirEntryFile);
          if (dataSource != null) {
            dataSource.setLog(log);
            if (dataSource instanceof TextMergeDirEntryReader) {
              TextMergeDirEntryReader dirReader 
                  = (TextMergeDirEntryReader)dataSource;
              dirReader.setMaxDepth(maxDepth);
              dirReader.setCurrDirDepth(currDirDepth);
              dirReader.setDirectoryNumberOfFolders(directoryNumberOfFolders);
            } // end if we're reading directory entries
            if (dataSource instanceof MetaMarkdownReader) {
              MetaMarkdownReader mmReader = (MetaMarkdownReader)dataSource;
              mmReader.setBasePath(directoryPath);
            }
            recDef = dataSource.getRecDef();
            try {
              dataSource.openForInput();
            } catch (IOException e) {
              log.recordEvent(
                  LogEvent.MAJOR, 
                  "I/O Error attempting to open " + dirEntryFile.toString(), 
                  true);
              dataSource = null;
            }
          } // end if data source is available
        } // end if this is a suitable file for processing by this input module
      } // end if input module is available
    } // end if we have a directory entry to consider
  } // end method nextDataSource
  
  private void closeLastDataSource() {
    if (dataSource != null) {
      try {
        dataSource.close();
      } catch (IOException e) {
        // let's just ignore this, shall we?
      }
    }
  }
  
  /**
   Let's explode the next directory, if we have any more.
  */
  private void nextDirectory() {
    dirNumber++;
    if (! isAtEnd()) {
      DirToExplode currDir = dirList.get(dirNumber);
      currDirAsFile = new File (currDir.path);
      currDirDepth = currDir.depth;
      String[] dirEntry = currDirAsFile.list();
      if (dirEntry == null) {
        dirEntries = new ArrayList();
        logEvent.setSeverity (LogEvent.MINOR);
        logEvent.setMessage (currDir.path + " not listed successfully");
        logEvent.setDataRelated(false);
        log.recordEvent (logEvent);
      } else {
        dirEntries = new ArrayList (Arrays.asList(dirEntry));
      }
      entryNumber = -1;
    } // end if more directories to explode
  }

  /**
     Returns the record definition for the reader.
    
     @return Record definition.
   */
  public RecordDefinition getRecDef () {
    return recDef;
  }
  
  /**
     Returns the sequential record number of the last record returned.
    
     @return Sequential record number of the last record returned via 
             nextRecordIn, where 1 identifies the first record.
   */
  public int getRecordNumber () {
    return recordNumber;
  }
  
  /**
     Returns the reader as some kind of string.
    
     @return Name of the directory.
   */
  public String toString () {
    return ("Directory Name is "
      + super.toString ());
  }

  /**
     Indicates whether there are more records to return.
    
     @return True if no more records to return.
   */
  public boolean isAtEnd() {
    return (dirNumber >= dirList.size());
  }
  
  /**
     Closes the reader.
   */
  public void close() {
    closeLastDataSource();
  }
  
  /**
     Ensures that a log is available, by allocating a new one if
     one has not already been supplied.
   */
  protected void ensureLog () {
    if (log == null) {
      setLog (new Logger (new LogOutput()));
    }
  }
    
  /**
     Sets a log to be used by the reader to record events.
    
     @param  log A logger object to use.
   */
  public void setLog (Logger log) {
    this.log = log;
  }
  
  /**
     Indicates whether all data records are to be logged.
    
     @param  dataLogging True if all data records are to be logged.
   */
  public void setDataLogging (boolean dataLogging) {
    this.dataLogging = dataLogging;
  }
  
  /**
     Sets the debug instance to the passed value.
    
     @param debug Debug instance. 
   */
  public void setDebug (Debug debug) {
    this.debug = debug;
  }
  
  /**
     Sets the maximum directory explosion depth. The default is 1, meaning
     that only one level is returned (no explosion). If this is changed, it
     should be done after the reader is constructed, but before it is opened
     for input.
    
     @param maxDepth Desired directory/sub-directory explosion depth.
   */
  public void setMaxDepth (int maxDepth) {
    this.maxDepth = maxDepth;
  }
  
  /**
     Retrieves the path to the original source data (if any).
    
     @return Path to the original source data (if any).
   */
  public String getDataParent () {
    if (directoryPath == null) {
      return System.getProperty (GlobalConstants.USER_DIR);
    } else {
      return directoryPath;
    }
  }
  
  /**
     Sets a file ID to be used to identify this reader in the log.
    
     @param  fileId An identifier for this reader.
   */
  public void setFileId (String fileId) {
    this.fileId = fileId;
    logData.setSourceId (fileId);
  }
  
  /**
     Inner class to define a directory to be processed.
   */
  class DirToExplode {
    int 		depth = 0;
    String	path  = "";
    
    DirToExplode (int depth, String path) {
      this.depth = depth;
      this.path = path;
    } // DirToExplode constructor
  } // end DirToExplode inner class
  
} 