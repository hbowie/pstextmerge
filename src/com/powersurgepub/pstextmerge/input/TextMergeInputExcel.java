package com.powersurgepub.pstextmerge.input;

import com.powersurgepub.psdatalib.psexcel.ExcelFile;
import com.powersurgepub.psdatalib.psexcel.ExcelTable;
  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;

/**
 A PSTextMerge input module for reading Excel files.

 @author Herb Bowie
 */
public class TextMergeInputExcel 
    extends TextMergeInputModule {
  
  public TextMergeInputExcel () {
    
    modifiers.add("");
    modifiers.add("xls");
    modifiers.add("xls2");
    
    labels.add("No Excel");
    labels.add("Excel Spreadsheet");
    labels.add("Excel Table");
    
    extensions.add("xls");
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
        dataSource = new ExcelFile (chosenFile.getPath());
        break;
      case 2:
        dataSource = new ExcelTable (chosenFile.getPath());
        break;
    }
    return dataSource;
  }

}
