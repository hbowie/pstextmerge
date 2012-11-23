package com.powersurgepub.pstextmerge.input;

import com.powersurgepub.psdatalib.txbio.strtext.TextIOStrText;
  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading YAML files.

 @author Herb Bowie
 */
public class TextMergeInputYAML 
    extends TextMergeInputModule {
  
  public TextMergeInputYAML () {
    
    modifiers.add("");
    modifiers.add("yaml");
    
    labels.add("No YAML");
    labels.add("YAML File");
    
    extensions.add("yaml");

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
