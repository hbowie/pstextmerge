package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.CQGridReader;
  import java.io.*;

/**
 A PSTextMerge input module for reading Grid files.

 @author Herb Bowie
 */
public class TextMergeInputGrid 
    extends TextMergeInputModule {
  
  public TextMergeInputGrid () {
    
    modifiers.add("");
    modifiers.add("grid1");
    
    labels.add("No Grid");
    labels.add("Grid");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new CQGridReader (chosenFile);
    return dataSource;
  }

}
