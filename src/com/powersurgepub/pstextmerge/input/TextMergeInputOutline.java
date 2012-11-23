package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.PSOutline;
import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading PSPub Outlines.

 @author Herb Bowie
 */
public class TextMergeInputOutline 
    extends TextMergeInputModule {
  
  public TextMergeInputOutline () {
    
    modifiers.add("");
    modifiers.add("out1");
    
    labels.add("No Outline");
    labels.add("PSPub Outline");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new PSOutline (chosenFile);
    return dataSource;
  }

}
