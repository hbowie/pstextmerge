package com.powersurgepub.pstextmerge.input;

import com.powersurgepub.psdatalib.tabdelim.TabDelimFile;
  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading tab-delimited files.

 @author Herb Bowie
 */
public class TextMergeInputTDF 
    extends TextMergeInputModule {
  
  public TextMergeInputTDF () {
    
    modifiers.add("");
    modifiers.add("file");
    
    labels.add("No TDF");
    labels.add("Tab-Delimited File");
    
    extensions.add("tab");
    extensions.add("csv");
    extensions.add("tdu");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new TabDelimFile (chosenFile);;
    return dataSource;
  }

}
