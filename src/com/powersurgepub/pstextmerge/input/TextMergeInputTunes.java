package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.ItunesParser;
  import java.io.*;

/**
 A PSTextMerge input module for reading iTunes XML files.

 @author Herb Bowie
 */
public class TextMergeInputTunes 
    extends TextMergeInputModule {
  
  public TextMergeInputTunes () {
    
    modifiers.add("");
    modifiers.add("itunes");
    
    labels.add("No iTunes");
    labels.add("iTunes Library.xml");
    
    extensions.add("xml");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new ItunesParser (chosenFile);
    return dataSource;
  }

}
