package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading a file system directory entry.

 @author Herb Bowie
 */
public class TextMergeInputDirEntry 
    extends TextMergeInputModule {
  
  public TextMergeInputDirEntry () {
    
    modifiers.add("");
    modifiers.add("dir");
    
    labels.add("No File Directory");
    labels.add("File Directory");

  }
 
  /**
   Is this input module interested in processing the specified file?
  
   @param candidate The file being considered. 
  
   @return True if the input module thinks this file is worth processing,
           otherwise false. 
  */
  public boolean isInterestedIn(File candidate) {
    if (candidate.isHidden()) {
      return false;
    }
    else
    if (candidate.isFile() 
        && candidate.length() == 0
        && candidate.getName().equals("Icon\r")) {
      return false;
    }
    if (candidate.canRead()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new TextMergeDirEntryReader (chosenFile);
    return dataSource;
  }

}
