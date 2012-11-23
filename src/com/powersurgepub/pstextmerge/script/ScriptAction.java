package com.powersurgepub.pstextmerge.script;

  import com.powersurgepub.psdatalib.psdata.RecordDefinition;
import com.powersurgepub.psdatalib.psdata.DataField;
import com.powersurgepub.psdatalib.psdata.DataFieldDefinition;
import com.powersurgepub.psdatalib.psdata.DataRecord;
  import com.powersurgepub.psutils.*;
  
/**
   A an action recorded in a script. <p>
   
   This code is copyright (c) 2000-2002 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
     2004/05/12 - Added logic to change file separators to match platform. <li>
     2003/02/15 - Added abilities to store and get file values 
                  relative to current path. <li>
     2000/12/21 - Originally written as part of TDFCzar project.
      </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com/software/">
           www.powersurgepub.com/software</a>)
  
   @version 
     2004/06/26 - Changed logic back to always use a forward slash as a
                  file separator. 
 */

public class ScriptAction {
  
  /** The string used to identify the module field. */
  public static final String  MODULE_NAME = "module";
  
  /** The string used to identify the action field. */
  public static final String  ACTION_NAME = "action";
  
  /** The string used to identify the action modifier field. */
  public static final String  MODIFIER_NAME = "modifier";
  
  /** The string used to identify the object field. */
  public static final String  OBJECT_NAME = "object";
  
  /** The string used to identify the value field. */
  public static final String  VALUE_NAME = "value";
  
  /** String used to replace the path in a file name stored in a value. */
  public static final String  PATH_PLACE_HOLDER = "#PATH#";
  
  /** String used to replace the location of the template library. */
  public static final String  TEMPLATE_LIBRARY_PLACE_HOLDER = "#TEMPLATES#";
  
  /** The record definition to use for all ScriptAction occurrences. */
  private static RecordDefinition scriptRecDef;
  
  /** The area of the program in which the action takes place. */
  private     String          module = "";
  
  /** The action to take. */
  private     String          action = "";
  
  /** An optional action modifier. */
  private     String          modifier = "";
  
  /** The thing that is to be acted upon (the object of the action). */
  private     String          object = "";
  
  /** The value to be applied to the object. */
  private     String          value = "";
  
  /** The definition of the script action as a DataRecord. */
  private     RecordDefinition recDef;
  
  /** The ScriptAction stored as a DataRecord. */
  private     DataRecord      scriptRec;
  
  private  		FileName 				templateLibraryName;
  private  		FileName 				fileName;
  private     boolean 				morePathFolders = true;
  private     boolean         moreTemplateFolders = true;
  private     boolean 				moreFileFolders = true;
  private		  String 					pathFolder;
  private     String          templateFolder;
  private		  String 					fileFolder;
  private     StringBuffer 		newValue;
  private     String          fileSeparatorString = "/";
  
  /**
     Constructs a new data record with all fields. 
    
     @param module The area of the program in which the action takes place.
    
     @param action The action to be taken.
    
     @param modifier An optional action modifier.
    
     @param object The thing that is to be acted upon.
   */
  public ScriptAction (String module, String action, String modifier, String object,
      String value) {
    this.module = module;
    this.action = action;
    this.modifier = modifier;
    this.object = object;
    this.value = value;
    fileSeparatorString 
        = System.getProperty (GlobalConstants.FILE_SEPARATOR, "/");
  }
  
  /**
     Constructs a new record from a DataRecord.
    
     @param DataRecord with the appropriate fields.
   */
  public ScriptAction (DataRecord scriptRec) {
    this.scriptRec = scriptRec;
    this.module   = "";
    this.action   = "";
    this.modifier = "";
    this.object   = "";
    this.value    = "";
    DataField field;
    DataFieldDefinition def;
    String name;
    String data;
    scriptRec.startWithFirstField();
    while (scriptRec.hasMoreFields()) {
      field = scriptRec.nextField();
      def = field.getDef();
      name = def.getCommonName().toString();
      data = field.getData();
      if (name.equals (MODULE_NAME)) {
        this.module = data;
      } else
      if (name.equals (ACTION_NAME)) {
        this.action = data;
      } else
      if (name.equals (MODIFIER_NAME)) {
        this.modifier = data;
      } else
      if (name.equals (OBJECT_NAME)) {
        this.object = data;
      } else
      if (name.equals (VALUE_NAME)) {
        this.value = data;
      } // end if
    } // end while more fields
    fileSeparatorString 
        = System.getProperty (GlobalConstants.FILE_SEPARATOR, "/");
  } // end constructor
  
  /**
     Returns the Script Action in the form of a DataRecord.
     
     @return The Script Action formatted as a DataRecord.
   */
  public DataRecord getDataRec () {
    ensureRecDef();
    scriptRec = new DataRecord();
    scriptRec.addField (scriptRecDef, module);
    scriptRec.addField (scriptRecDef, action);
    scriptRec.addField (scriptRecDef, modifier);
    scriptRec.addField (scriptRecDef, object);
    scriptRec.addField (scriptRecDef, value);
    return scriptRec;
  } // end method getDataRec
  
  /**
     Returns the global record definition.
    
     @return Standard record definition.
   */
  public static RecordDefinition getRecDef () {
    ensureRecDef();
    return scriptRecDef;
  } // end method getRecDef
  
  /** 
     Returns module, the area of the program in which the action takes place. 
    
     @return Module.
   */
  public String getModule() {
    return module;
  }
  
  /** 
     Returns the action to take. 
    
     @return Action
   */
  public String getAction() {
    return action;
  }
  
  /** 
     Returns an optional action modifier. 
    
     @return Modifier.
   */
  public String getModifier() {
    return modifier;
  }
  
  /** 
     Returns the thing that is to be acted upon (the object of the action). 
    
     @return Object
   */
  public String getObject() {
    return object;
  }
  
  /** 
     Returns the value to be applied to the object. 
     
     @return Value
   */
  public String getValue() {        
    return value;
  }
  
  /**
     Subtracts path information from file name.
    
     @param path Path information to be removed.
   */
  protected void removePath (String path, String templateLibrary) {
    
    // Check for template library path first
    templateLibraryName = new FileName (templateLibrary);
    fileName = new FileName(value);
    // System.out.println ("removePath template = " + templateLibraryName.toString());
    // System.out.println ("removePath file = " + fileName.toString());
    moreTemplateFolders = true;
    moreFileFolders = true;
    boolean matched = true;
    while (moreTemplateFolders 
        && moreFileFolders
        && matched) {
      nextTemplateFolder();
      nextFileFolder();
      // System.out.println ("  template folder = " + templateFolder);
      // System.out.println ("  file     folder = " + fileFolder);
      if (! templateFolder.equals (fileFolder)) {
        matched = false;
      }
    }
    // System.out.println ("  matched = " + String.valueOf (matched));
    // System.out.println ("  more template folders = " 
    //     + String.valueOf (moreTemplateFolders));
    if (matched && (! moreTemplateFolders)) {
      newValue = new StringBuffer (TEMPLATE_LIBRARY_PLACE_HOLDER);
      while (moreFileFolders) {
        if (moreFileFolders) {
          newValue.append(fileFolder + "/");
        } // end if more file folders
      } // end while more file folders
      String fn = fileName.getFileName();
      if (fn.length() > 0) {
        newValue.append (fn);
      }
      value = newValue.toString();
    } else {    
      // if template path not found, check for path
      templateLibraryName = new FileName(path);
      fileName = new FileName(value);
      morePathFolders = true;
      moreFileFolders = true;
      nextPathFolder();
      nextFileFolder();
      if (morePathFolders
          && pathFolder.equals (fileFolder)) {
        newValue = new StringBuffer(PATH_PLACE_HOLDER);
        while (morePathFolders || moreFileFolders) {
          nextPathFolder();
          nextFileFolder();
          if (morePathFolders
              && moreFileFolders
              && pathFolder.equals (fileFolder)) {
            // no action necessary -- still following path
          }
          else {
            while (morePathFolders) {
              newValue.append("../");
              nextPathFolder();
            } // end while more path folders
            if (moreFileFolders) {
              newValue.append(fileFolder + "/");
            } // end if more file folders
          } // end if not matching folders
        }// end while more folders
        String fn = fileName.getFileName();
        if (fn.length() > 0) {
          newValue.append (fn);
        } 
        // if (fileSeparatorString.equals ("\\")) {
        //  value = StringUtils.replaceString (newValue.toString(),
        //      "/", fileSeparatorString);
        //} else {
        value = newValue.toString();
        // }
      } // end if first folders are equal
    } // end if template library path not found
  } // end removePath method
  
  /**
     Restores path information to file name.
    
     @param path Path information to be restored.
   */
  protected void restorePath (String path, String templateLibrary) {
    if (value.startsWith (PATH_PLACE_HOLDER)) {
      templateLibraryName = new FileName(path);
      int pathFolders = templateLibraryName.getNumberOfFolders();
      int fileStart = PATH_PLACE_HOLDER.length();
      int nextFileStart = fileStart + 3;
      while (value.length() >= nextFileStart
          && value.substring(fileStart, nextFileStart).equals ("../")) {
        pathFolders--;
        fileStart = nextFileStart;
        nextFileStart = fileStart + 3;
      }
      newValue = new StringBuffer();
      char firstCharSep = templateLibraryName.getFolderSeparator();
      if (firstCharSep != ' ') {
        newValue.append (firstCharSep);
      }
      char lastSep = '/';
      char sep;
      for (int i = 1; i <= pathFolders; i++) {
        newValue.append (templateLibraryName.getFolder(i));
        sep = templateLibraryName.getFolderSeparator();
        if (sep == ' ') {
          newValue.append (lastSep);
        }
        else {
          newValue.append (sep);
          lastSep = sep;
        }
      } // end for loop

      newValue.append (value.substring(fileStart));
      
      // if (fileSeparatorString.equals ("\\")) {
      //  value = StringUtils.replaceString (newValue.toString(),
      //      "/", fileSeparatorString);
      // } else {
      value = StringUtils.replaceString (newValue.toString(),
          "\\", "/");
      // }
    } // end if value starts with Path place holder
    else 
    if (value.startsWith (TEMPLATE_LIBRARY_PLACE_HOLDER)) {
      // System.out.println ("restorePath file name = " + value);
      // System.out.println ("     template library = " + templateLibrary);
      templateLibraryName = new FileName(templateLibrary);
      int templateFolders = templateLibraryName.getNumberOfFolders();
      int fileStart = TEMPLATE_LIBRARY_PLACE_HOLDER.length();
      int nextFileStart = fileStart + 3;
      newValue = new StringBuffer();
      char firstCharSep = templateLibraryName.getFolderSeparator();
      if (firstCharSep != ' ') {
        newValue.append (firstCharSep);
      }
      char lastSep = '/';
      char sep;
      for (int i = 1; i <= templateFolders; i++) {
        newValue.append (templateLibraryName.getFolder(i));
        sep = templateLibraryName.getFolderSeparator();
        if (sep == ' ') {
          newValue.append (lastSep);
        }
        else {
          newValue.append (sep);
          lastSep = sep;
        }
      } // end for loop

      newValue.append (value.substring(fileStart));
      
      // if (fileSeparatorString.equals ("\\")) {
      //  value = StringUtils.replaceString (newValue.toString(),
      //      "/", fileSeparatorString);
      // } else {
      value = StringUtils.replaceString (newValue.toString(),
          "\\", "/");
      // }
    } // end if value starts with template library place holder
  } // end method restorePath
  
  /**
     Get next folder in path.
   */
  private void nextPathFolder() {
    if (morePathFolders) {
      pathFolder = templateLibraryName.getNextFolder();
      if (pathFolder.length() == 0) {
        morePathFolders = false;
      } // end if folder length is zero
    } // end if more path folders
  } // end method
  
  /**
     Get next folder in template library path.
   */
  private void nextTemplateFolder() {
    if (moreTemplateFolders) {
      templateFolder = templateLibraryName.getNextFolder();
      if (templateFolder.length() == 0) {
        moreTemplateFolders = false;
      } // end if folder length is zero
    } // end if more template folders
  } // end method
  
  /**
     Get next folder in file name.
   */
  private void nextFileFolder() {
    if (moreFileFolders) {
      fileFolder = fileName.getNextFolder();
      if (fileFolder.length() == 0) {
        moreFileFolders = false;
      } // end if folder length is zero
    } // end if more path folders
  } // end method
  
  /** 
     Ensures that the global record definition has been built.
   */
  private static void ensureRecDef() {
    if (scriptRecDef == null) {
      buildRecDef();
    }
  } // end method ensureRecDef
  
  /**
     Builds the global record definition. 
   */
  private static void buildRecDef() {
    scriptRecDef = new RecordDefinition();
    scriptRecDef.addColumn (MODULE_NAME);
    scriptRecDef.addColumn (ACTION_NAME);
    scriptRecDef.addColumn (MODIFIER_NAME);
    scriptRecDef.addColumn (OBJECT_NAME);
    scriptRecDef.addColumn (VALUE_NAME);
  } // end method buildRecDef
    
  /**
     Returns this record as some kind of string.
    
     @return Concatenation of all the fields within this record.
   */
  public String toString () {
    return module + " "
      + action + " "
      + modifier + " "
      + object + " "
      + value;
  } // end method toString
    
} // end class ScriptAction 