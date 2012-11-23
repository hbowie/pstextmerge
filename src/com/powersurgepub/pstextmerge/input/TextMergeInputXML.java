package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.*;
  import java.io.*;

/**
 A PSTextMerge input module for reading XML files.

 @author Herb Bowie
 */
public class TextMergeInputXML 
    extends TextMergeInputModule {
  
  public TextMergeInputXML () {
    
    modifiers.add("");
    modifiers.add("xml1");
    modifiers.add("xml2");
    
    labels.add("No XML");
    labels.add("XML Rows for Fields");
    labels.add("XML Rows for Records");
    
    extensions.add("xml");
  }
  
  /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource = null;
    switch (inputType) {
      case 1: 
        dataSource = new XMLParser (chosenFile);
        break;
      case 2:
        XMLParser2 xml2 = new XMLParser2();
        dataSource = xml2.parse (chosenFile.toString(), 99, "xml", "");
        break;
    }
    return dataSource;
  }

}
