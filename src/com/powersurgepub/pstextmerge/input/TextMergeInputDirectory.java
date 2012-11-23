package com.powersurgepub.pstextmerge.input;

import com.powersurgepub.psdatalib.txbio.strtext.TextIOStrText;
  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading all the files within a directory.

 @author Herb Bowie
 */
public class TextMergeInputDirectory 
    extends TextMergeInputModule {
  
  public TextMergeInputDirectory () {
    
    modifiers.add("");
    modifiers.add("dir");
    
    labels.add("No File Directory");
    labels.add("File Directory Entries");

  }
  
  /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new TextIOStrText (chosenFile);
    return dataSource;
  }

}
