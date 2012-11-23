package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
import com.powersurgepub.psdatalib.psdata.ReturnedMailReader;
  import java.io.*;

/**
 A PSTextMerge input module for reading Returned Mail files.

 @author Herb Bowie
 */
public class TextMergeInputReturnedMail 
    extends TextMergeInputModule {
  
  public TextMergeInputReturnedMail () {
    
    modifiers.add("");
    modifiers.add("returnedmail");
    
    labels.add("No Returned Mail");
    labels.add("Returned Mail");

  }
  
    /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = new ReturnedMailReader (chosenFile);
    return dataSource;
  }

}
