package com.powersurgepub.pstextmerge.input;

import com.powersurgepub.psdatalib.txbio.HTMLFile;
import com.powersurgepub.psdatalib.txbio.HTMLTableFile;
import com.powersurgepub.psdatalib.txbio.HTMLLinksFile;
  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading HTML.

 @author Herb Bowie
 */
public class TextMergeInputHTML 
    extends TextMergeInputModule {
  
  public TextMergeInputHTML () {
    
    modifiers.add("");
    modifiers.add("html1");
    modifiers.add("html2");
    modifiers.add("html3");
    modifiers.add("html4");
    
    labels.add("No HTML");
    labels.add("HTML Bookmarks using Lists");
    labels.add("HTML Table");
    labels.add("HTML Bookmarks using Headings");
    labels.add("HTML Links");
    
    extensions.add("html");
    extensions.add("htm");
    extensions.add("xhtml");
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
        dataSource = new HTMLFile (chosenFile);
        break;
      case 2:
        dataSource = new HTMLTableFile (chosenFile);
        break;
      case 3:
        HTMLFile htmlIn = new HTMLFile (chosenFile);
        htmlIn.useHeadings();
        dataSource = htmlIn;
        break;
      case 4:
        dataSource = new HTMLLinksFile (chosenFile);
        break;
    }
    return dataSource;
  }

}
