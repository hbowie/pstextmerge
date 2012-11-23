package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.*;
  import com.powersurgepub.psdatalib.txbio.*;
  import java.io.*;

/**
 A PSTextMerge input module for reading Markdown files with embedded metadata.

 @author Herb Bowie
 */
public class TextMergeInputMetaMarkdown 
    extends TextMergeInputModule {
  
  public TextMergeInputMetaMarkdown () {
    
    modifiers.add("");
    modifiers.add("mmdown");
    modifiers.add("mmdowntags");
    
    labels.add("No Markdown");
    labels.add("Markdown Metadata");
    labels.add("Markdown Metadata Tags");
  }
  
  /**
   Is this input module interested in processing the specified file?
  
   @param candidate The file being considered. 
  
   @return True if the input module thinks this file is worth processing,
           otherwise false. 
  */
  public boolean isInterestedIn(File candidate) {
    return MetaMarkdownReader.isInterestedIn(candidate);
  }
  
  /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource;
    dataSource = new MetaMarkdownReader(chosenFile, inputType);
    return dataSource;
  }

}
