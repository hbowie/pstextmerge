package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.RecordDefinition;
import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.DataRecord;
import com.powersurgepub.psdatalib.psdata.DataDictionary;
  import com.powersurgepub.psutils.*;
  import java.io.*;
  import java.util.*;
  import java.text.*;

/**
   A directory entry reader that creates a data record from the passed file. <p>
 */
 public class TextMergeDirEntryReader 
    extends     File
    implements  DataSource {
    
  /** The name of the sort key field within the directory record. */
  public  static   final  String  DIR_ENTRY_SORT_KEY = "Sort Key";
    
  /** The name of the name field within the directory record. */
  public  static   final  String  DIR_ENTRY_FOLDER = "Folder";
  
  /** The path from the starting folder to the file. */
  public  static   final  String  DIR_ENTRY_PATH = "Path";

  /** The name of the name field within the directory record. */
  public  static   final  String  DIR_ENTRY_NAME = "File Name";
    
  /** The name of the type field within the directory record. */
  public  static   final  String  DIR_ENTRY_TYPE = "Type";
    
  /** The name of the English-like name within the directory record. */
  public  static   final  String  DIR_ENTRY_ENGLISH_NAME = "English Name";

  /** The file name, without path and without file extension. */
  public  static   final  String  DIR_ENTRY_FILE_NAME_NO_EXT = "File Name w/o Ext";
    
  /** The name of the file extension field within the directory record. */
  public  static   final  String  DIR_ENTRY_FILE_EXT = "File Ext";
    
  /** The name of the file size field within the directory record. */
  public  static   final  String  DIR_ENTRY_FILE_SIZE = "File Size";
    
  /** The name of the last modification date field within the directory record. */
  public  static   final  String  DIR_ENTRY_LAST_MOD_DATE = "Last Mod Date";
  
  /** The name of the last modification time field within the directory record. */
  public  static   final  String  DIR_ENTRY_LAST_MOD_TIME = "Last Mod Time";
  
  /** The next apparent word in the file name. */
  public  static   final  String  DIR_ENTRY_WORD = "Word";
  
  /** The maximum number of words to extract from the file name. */
  public  static   final  int     MAX_WORDS = 5;
  
  /** 
     The number of levels of directories and sub-directories to be read. 
     A value of 1 (the default) indicates that only the top level directory
     should be read. A value of 2 indicates one level of sub-directories, and
     so forth.
   */
  private		 int							maxDepth = 0;
  
  private		 int							currDirDepth;
  
  /** The number of directories in the top directory to be read. */
  private		 int							directoryNumberOfFolders;

  /** The logger to use to log events. */    
  private    Logger           log;
  
  /* Let's not log all data. */
  private    boolean          dataLogging = false;
  
  /** Debug instance. */
  private		 Debug						debug = new Debug (false);
  
  /** The data dictionary to be used by this record. */
  private    DataDictionary   dict = null;
  
  /** The record definition to be used by this record. */
  private    RecordDefinition recDef = null;
  
  /** Pointer to a particular record within the array. */
  private    int              recordNumber;
  
  private    boolean          atEnd = false;
  
  /** Data to be sent to the log. */
  private    LogData          logData;
  
  /** An event to be sent to the log. */
  private    LogEvent         logEvent;
  
  /** Something to use to format dates. */
  private    DateFormat       dateFormatter
    = new SimpleDateFormat ("yyyy-MM-dd");
    
  /** Something to use to format times. */
  private    DateFormat       timeFormatter
    = new SimpleDateFormat ("HH:mm:ss zzz");
  
  /** The identifier for this reader. */
  private    String           fileId;

  /**
     Constructs a directory reader given a path defining the directory
     to be read.
    
     @param  inPath Directory path to be read.
   */
  public TextMergeDirEntryReader (String inPath) {
    super (inPath);
    initialize();
  }

  /**
     Constructs a directory reader given a file object 
     defining the directory to be read.
    
     @param  inPathFile Directory path to be read.
   */
  public TextMergeDirEntryReader (File inPathFile) {
    super (inPathFile.getAbsolutePath());
    initialize();
  }
  
  /**
     Performs standard initialization for all the constructors.
     By default, fileId is set to "directory".
   */
  private void initialize () {
    fileId = "directory-entry";
    logData = new LogData ("", fileId, 0);
    logEvent = new LogEvent (0, "");
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
  
  public void setCurrDirDepth(int currDirDepth) {
    this.currDirDepth = currDirDepth;
  }
  
  public void setDirectoryNumberOfFolders (int directoryNumberOfFolders) {
    this.directoryNumberOfFolders = directoryNumberOfFolders;
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
    if (recDef == null) {
      buildRecDef();
    }
    recordNumber = 0;
    atEnd = false;
  } // end of openForInput method

  /**
     Returns the next directory entry.
    
     @return Next directory entry as a data record.
   */
  public DataRecord nextRecordIn () {
    if (recordNumber > 0) {
      atEnd = true;
      return null;
    } else {
      recordNumber++;
      DataRecord nextRec = new DataRecord ();
      int fieldNumber;
      FileName fileName = new FileName (this);
      
      // Sort key
      fieldNumber = nextRec.addField 
        (recDef, StringUtils.wordSpace (this.getAbsolutePath(), true));
        
      // Individual folder names
      StringBuffer path = new StringBuffer();
      for (int i = 1; i < maxDepth; i++) {
        if (i < currDirDepth) {
          String folder = fileName.getFolder (directoryNumberOfFolders + i);
          fieldNumber = nextRec.addField (recDef, folder);
          if (path.length() > 0) {
            path.append ('/');
          }
          path.append (folder);
        } else {
          fieldNumber = nextRec.addField (recDef, "");
        }
      }
      
      // Path
      fieldNumber = nextRec.addField (recDef, path.toString());
      
      // File name
      fieldNumber = nextRec.addField (recDef, this.getName());
      
      String dirEntryType = "?";
      String size = " ";
      String lastModDate = " ";
      String lastModTime = " ";
      if (this.isFile()) {
        dirEntryType = "File";
        size = String.valueOf (this.length());
        Date lastMod = new Date (this.lastModified());
        lastModDate = dateFormatter.format (lastMod);
        lastModTime = timeFormatter.format (lastMod);
      } else
      if (this.isDirectory()) {
        dirEntryType = "Directory";
      }
      
      // Type of entry: File or Directory
      fieldNumber = nextRec.addField (recDef, dirEntryType);
      
      // File name looking like a regular English name
      fieldNumber = nextRec.addField (recDef, fileName.getFileNameEnglish());
      String ext = fileName.getExt();

      // File name without path or extension
      fieldNumber = nextRec.addField (recDef, fileName.getBase());
      
      // File extension
      fieldNumber = nextRec.addField (recDef, ext);
      
      // File size
      fieldNumber = nextRec.addField (recDef, size);
      
      // Date last modified
      fieldNumber = nextRec.addField (recDef, lastModDate);
      
      // Time last modified
      fieldNumber = nextRec.addField (recDef, lastModTime);
      StringScanner fileNameScanner 
          = new StringScanner (fileName.getBase());
          
      // Individual words in file name
      for (int i = 1; i <= MAX_WORDS; i++) {
        nextRec.addField (recDef, fileNameScanner.getNextWord());
      }
      
      return nextRec;
    } // end of logic if more directory entries to return
  } // end of nextRecordIn method

  /**
     Returns the record definition for the reader.
    
     @return Record definition.
   */
  public RecordDefinition getRecDef () {
    if (recDef == null) {
      buildRecDef();
    }
    return recDef;
  }
  
  private void buildRecDef() {
    if (dict == null) {
      dict = new DataDictionary();
    }
    recDef = new RecordDefinition (dict);
    recDef.addColumn (DIR_ENTRY_SORT_KEY);
    for (int i = 1; i < maxDepth; i++) {
      recDef.addColumn (DIR_ENTRY_FOLDER + String.valueOf(i));
    }
    recDef.addColumn (DIR_ENTRY_PATH);
    recDef.addColumn (DIR_ENTRY_NAME);
    recDef.addColumn (DIR_ENTRY_TYPE);
    recDef.addColumn (DIR_ENTRY_ENGLISH_NAME);
    recDef.addColumn (DIR_ENTRY_FILE_NAME_NO_EXT);
    recDef.addColumn (DIR_ENTRY_FILE_EXT);
    recDef.addColumn (DIR_ENTRY_FILE_SIZE);
    recDef.addColumn (DIR_ENTRY_LAST_MOD_DATE);
    recDef.addColumn (DIR_ENTRY_LAST_MOD_TIME);
    for (int i = 1; i <= MAX_WORDS; i++) {
      recDef.addColumn (DIR_ENTRY_WORD + String.valueOf(i));
    }
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
    return (atEnd);
  }
  
  /**
     Closes the reader.
   */
  public void close() {
    /** 
    ensureLog();
    logEvent.setSeverity (LogEvent.NORMAL);
    logEvent.setMessage ("DirectoryReader: " + directoryPath + " closed successfully");
    log.recordEvent (logEvent);
    */
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
     Retrieves the path to the original source data (if any).
    
     @return Path to the original source data (if any).
   */
  public String getDataParent () {

    return this.getParent();
  }
  
  /**
     Sets a file ID to be used to identify this reader in the log.
    
     @param  fileId An identifier for this reader.
   */
  public void setFileId (String fileId) {
    this.fileId = fileId;
    logData.setSourceId (fileId);
  }
  
} 