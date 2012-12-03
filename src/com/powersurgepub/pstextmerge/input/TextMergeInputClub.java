package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.clubplanner.*;
  import com.powersurgepub.clubplanner.io.*;
  import com.powersurgepub.psdatalib.psdata.*;
  import java.io.*;

/**
 A PSTextMerge input module for reading Club Planner data.

 @author Herb Bowie
 */
public class TextMergeInputClub 
    extends TextMergeInputModule {
  
  public TextMergeInputClub () {
    
    modifiers.add("");
    modifiers.add("club");
    modifiers.add("clubnotes");
    
    labels.add("No Club Planner");
    labels.add("Club Planner");
    labels.add("Club Notes");
  }
  
  /**
   Is this input module interested in processing the specified file?
  
   @param candidate The file being considered. 
  
   @return True if the input module thinks this file is worth processing,
           otherwise false. 
  */
  public boolean isInterestedIn(File candidate) {
    if (candidate.isHidden()) {
      return false;
    }
    else
    if (candidate.getName().startsWith(".")) {
      return false;
    }
    else
    if (! candidate.canRead()) {
      return false;
    }
    else
    if (candidate.isFile() 
        && candidate.length() == 0
        && candidate.getName().equals("Icon\r")) {
      return false;
    }
    else
    if (candidate.isDirectory()) {
      return false;
    }
    else
    if (candidate.getParent().endsWith("templates")) {
      return false;
    }
    else
    if (candidate.getName().equalsIgnoreCase("New Event.txt")) {
      return false;
    }
    else
    if (candidate.getName().endsWith (".txt")) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   Get the appropriate data source for this type of data. 
  
   @param chosenFile The file containing the data to be read.
  
   @return The desired data source.
  */
  public DataSource getDataSource(File chosenFile) {
    DataSource dataSource;
    dataSource = new ClubEventReader(chosenFile, inputType);
    return dataSource;
  }

}
