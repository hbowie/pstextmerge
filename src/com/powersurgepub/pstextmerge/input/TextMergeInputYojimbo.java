package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.YojimboReader;
  import java.io.*;

/**
 A PSTextMerge input module for reading Yojimbo bookmark files.

 @author Herb Bowie
 */
public class TextMergeInputYojimbo 
    extends TextMergeInputModule {
  
  public TextMergeInputYojimbo () {
    
    modifiers.add("");
    modifiers.add("yojimbo");
    
    labels.add("No Yojimbo");
    labels.add("Yojimbo");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new YojimboReader (chosenFile);
    return dataSource;
  }

}
