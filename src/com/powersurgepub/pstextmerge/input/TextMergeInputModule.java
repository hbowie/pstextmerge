package com.powersurgepub.pstextmerge.input;

  import com.powersurgepub.psdatalib.psdata.DataSource;
  import java.io.*;
  import java.util.*;

/**
 An abstract class for a PSTextMerge input module.

 @author Herb Bowie
 */
public abstract class TextMergeInputModule {
  
    protected             int inputType = 0;
    
    protected             ArrayList<String> modifiers = new ArrayList();
    
    protected             ArrayList<String> labels = new ArrayList();
    
    protected             ArrayList<String> extensions = new ArrayList();
    
  /**
   Determine what type of input is currently being processed, assuming
   that one input module may process more than one type of related inputs. 
  
   @return The specific type of input being processed, with 1 being the first. 
  */
  public int getInputType() {
    return inputType;
  }
  
  /**
   Return the maximum allowable value for input type, for this module.
  
   @return Maximum allowable input type, with 1 being the first input type. 
  */
  public int getInputTypeMax() {
    return labels.size() - 1;
  }
  
  /**
   See if the suggested input type falls within the valid range. 
  
   @param inputType The suggested input type.
  
   @return True if within the valid range, false otherwise. 
  */
  public boolean validInputType (int inputType) {
    return (inputType >= 0 && inputType <= getInputTypeMax());
  }
  
  /**
   Set the input type based on the passed label. 
  
   @param label The selected input type label. 
  
   @return True if the label matched a label for this input module,
           false if there was no match with this module.
  */
  public boolean setInputType(String label) {
    inputType = 1;
    while (validInputType (inputType)
        && (! getInputTypeLabel().equalsIgnoreCase(label))) {
      inputType++;
    }
    if (validInputType (inputType)) {
      return true;
    } else {
      inputType = 0;
      return false;
    }
  }
  
  /**
   Set the input type based on the passed modifier.
  
   @param modifier The specified modifier.
   
   @return True if a match was found, false otherwise.  
  */
  public boolean setInputTypeByModifier(String modifier) {
    inputType = 1;
    while (validInputType (inputType)
        && (! getInputTypeModifier().equalsIgnoreCase(modifier))) {
      inputType++;
    }
    if (validInputType (inputType)) {
      return true;
    } else {
      inputType = 0;
      return false;
    }
  }
  
  /**
   Set the specific input type currently being processed. 
  
   @param inputType The specific input type being processed. 
  */
  public void setInputType(int inputType) {
    if (validInputType (inputType)) {
      this.inputType = inputType;
    }
  }
  

  
  /**
   Increment the input type by 1, wrapping around to 1.
   if the maximum is exceeded. 
  */
  public void incrementInputType() {
    inputType++;
    if (! validInputType (inputType)) {
      inputType = 0;
    }
  }
  
  /**
   Return the modifier value to be used when writing or reading a script.
  
   @return The modifier value for the current input type.
  */
  public String getInputTypeModifier() {
    return getInputTypeModifier(inputType);
  }
  
  /**
   Return the modifier value to be used when writing or reading a script.
  
   @return The modifier value for the specified input type.
  */
  public String getInputTypeModifier(int inputType) {
    if (validInputType (inputType)) {
      return modifiers.get(inputType);
    } else {
      return "";
    }
  }
  
  /**
   Return the input type label for the current input type.
  
   @return The label for the current input type.
  */
  public String getInputTypeLabel() {
    return getInputTypeLabel(inputType);
  }
  
  /**
   Return the input type label for the specified input type.
  
   @return The label for the specified input type.
  */
  public String getInputTypeLabel(int inputType) {
    if (validInputType (inputType)) {
      return labels.get(inputType);
    } else {
      return "";
    }
  }
  
  /**
   Does this input module support the passed file extension?
  
   @param inExt The file extension of interest.
  
   @return True if yes, false if not.
  */
  public boolean isForFileExtension(String inExt) {
    int period = inExt.indexOf('.');
    String ext = inExt;
    if (period >= 0) {
      ext = inExt.substring(period + 1);
    }
    boolean extFound = false;
    int i = 0;
    while (! extFound && i < extensions.size()) {
      if (ext.equalsIgnoreCase(extensions.get(i))) {
        extFound = true;
      } else {
        i++;
      }
    } // end while looking for a matching file extension
    return extFound;
  } // end method supportsFileExtension
  
  /**
   Get the preferred file extensions for this input module. 
  
   @return The preferred file extension, if one exists, 
           otherwise an empty string. 
  */
  public String getPreferredExtension() {
    if (extensions.size() > 0) {
      return extensions.get(0);
    } else {
      return "";
    }
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
    if (extensions.size() > 0
        && (! candidate.getName().endsWith(getPreferredExtension()))) {
      return false;
    }
    else
    if (candidate.canRead()) {
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
  public abstract DataSource getDataSource(File chosenFile);

}
