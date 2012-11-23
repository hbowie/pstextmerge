package com.powersurgepub.pstextmerge;

  import com.powersurgepub.psdatalib.ui.*;
  import com.powersurgepub.psdatalib.tabdelim.*;
  import com.powersurgepub.psdatalib.psdata.*;
  import com.powersurgepub.pstextmerge.input.*;
  import com.powersurgepub.pstextmerge.script.*;
  import com.powersurgepub.pstextmerge.template.*;
  import com.powersurgepub.psutils.*;
  import com.powersurgepub.regcodes.*;
  import com.powersurgepub.xos2.*;
  import java.awt.*;
  import java.awt.event.*;
  import java.io.*;
  import java.net.*;
  import java.text.*;
  import java.util.*;
  import java.util.zip.*;
  import javax.swing.*;
  import javax.swing.border.*;
  import javax.swing.table.*;
  
/**
   An application with a GUI interface to do all sorts of things 
   with tab-delimited files. <p>
  
   This code is copyright (c) 1999-2002 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
    2004/06/01 - Removed references to TDFCommon. 
                 Modified to write user directory to log. <li>
    2003/12/16 - Added option to parse XML. <li>
    2003/08/03 - Modified program to reset all input options to initial defaults
                 after playing a script. Added default file extension of '.tcz'
                 for input and output script files. <li>
    2003/05/25 - Changed logging options to write log to disk when invoked
                 in quiet mode. Added code to make normalization optional. <li>
    2003/05/25 - Modified program to read an HTML file using the 
                 HTMLLinksFile class to generate links. <li>
    2003/04/22 - Modified program to pass a directory path to a
                 normalizer routine. <li>
    2003/03/25 - Modified program to allow it to run completely in
                 batch mode, from the command line, without a user interface. <li>
    2003/03/17 - Added an input control to allow the user to specify a
                 data normalization routine. <li>
    2003/02/20 - Modified code so that Play Again Button will always play
                 last script played back or recorded. Corrected bug that
                 prevented directory depth from being set back to 1 after
                 being incremented. <li>
    2003/02/19 - Added option to read HTML bookmarks 
                 with headings as categories. <li>
    2003/01/03 - Made sort parameters text box scrollable. <li>
    2002/12/16 - Modified to add an option for reading sub-directories as 
                 well as directories. <li>
    2002/11/17 - Modified to add an option for merging a file that does not have
                 its own column headings. <li>
    2002/11/05 - Modified to add menus and accelerator (shortcut) keys. <br>
                 Modified to disable buttons and menus when context would not
                 allow their use. <br>
                 Modified to remove applet functionality and make a pure
                 application. <br>
                 Added standard Mac handlers to open files dropped on the app, etc.<li>
    2002/10/12 - Modified to add an input option to read an HTML table, and to
                 improve the user interface on the Input, Output and 
                 Template tabs. <li>
    2002/09/21 - Modified to start with an empty data dictionary when
                 opening a new input data source, unless a merge has
                 been requested. <li>
    2002/08/31 - Added ability to merge a second data set into an
                 existing one. <li>
                 Added ability to combine sorted records. <li>
    2002/07/25 - Added ability to parse HTML. Also added ability to 
                 register product, and remove
                 limitations on running unregistered demo version. <li>
    2002/03/03 - Added scroll bars to script window. <li>
    2002/03/03 - Added scroll bars to script window. <li>
    2001/02/02 - Version 1.11 started to fix miscellaneous bugs
                         and make minor improvements to existing functionality. <li>
    2001/01/12 - Version 1.1 added the template tab
                         (replacing TabToTemplateMergeUI) and the output
                         tab. <li>
    2001/01/11 - Version 1.0 released, with the Input, View, Sort, Filter,
                         Script and Logging tabs. 
   </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com">
           www.powersurgepub.come</a>)
  
   @version 
    2004/06/26 - Set current directory when playing back a script file.  
 */

public class PSTextMerge 
    implements PSFileOpener, XHandler {

	/*
	   Constants
	 */
	
	// String associated with a logging threshold of normal severity. 
  public    static final String  LOG_NORMAL_STRING = "Normal";
  
  // String associated with a logging threshold of minor severity. 
  public    static final String  LOG_MINOR_STRING  = "Minor";
  
  // String associated with a logging threshold of medium severity. 
  public    static  final String  LOG_MEDIUM_STRING = "Medium";
  
  // String associated with a logging threshold of major severity. 
  public    static  final String  LOG_MAJOR_STRING  = "Major";    

	// Default values for tab configurations. 
	private   static  final String  DEFAULT_TABS = "IVSFOTCLA";
  
  // Maximum value for normalization Type
  private		static	final int			NORMALTYPE_MAX = 1;
  
  // Maximum number of records that can be processed in demo mode.
  private		static	final	int			DEMO_MAX_RECORDS = 20;
  
  /** Default file extension for script files. */
  public		static	final String  SCRIPT_EXT = "tcz";
  
  /** Program Name */
  public    static  final String  PROGRAM_NAME = "PSTextMerge";
  public    static  final String  PROGRAM_VERSION = "4.00";
  
  private   static  final String  USER_GUIDE
      = "userguide/pstextmerge.html";
  
  private   static  final String  PROGRAM_HISTORY
      = "versions.html";

  private   static  final String  MIME_TYPE = "mimetype";
  
  private   static  final String  AUTOPLAY = "autoplay";
  
  private   static  final String  EASYPLAY = "easyplay";
  
  /**
     Command line arguments.
   */
  private boolean quietMode = false;
  private String  startingScript = "";

  private ScriptExecutor scriptExecutor = null;
  
  // Flag to indicate this is not the main program
  private boolean mainClass = true;
  
  private     static  final int   DEFAULT_WIDTH = 680;
  private     static  final int   DEFAULT_HEIGHT = 450;
  
  private                   int   defaultX = 0;
  private                   int   defaultY = 0;
  
	/*
	   Fields to control the tabs that are displayed.
	 */
	// Tab configuration. 
	private     String              tabConfig = DEFAULT_TABS;
	
	// Should Input Tab be displayed? 
	private     boolean             inputTabDisplay = false;
	
	// Should View Tab be displayed? 
	private     boolean             viewTabDisplay = false;
	
	// Should Sort Tab be displayed? 
	private     boolean             sortTabDisplay = false;
	
	// Should Filter Tab be displayed? 
	private     boolean             filterTabDisplay = false;
	
	// Should Output Tab be displayed? 
	private     boolean             outputTabDisplay = false;
	
	// Should Template Tab be displayed? 
	private     boolean             templateTabDisplay = false;
	
	// Should Script Tab be displayed? 
	private     boolean             scriptTabDisplay = false;
	
	// Should Logging Tab be displayed? 
	private     boolean             logTabDisplay = false;
  
  // Input Modules
  private     ArrayList<TextMergeInputModule> inputModules = new ArrayList();
  
  private     int                   inputModuleIndex = 0;
  
  private     boolean               inputModuleFound = false;
  
  private     TextMergeInputModule  inputModule;
  
  private     TextMergeInputTDF     inTDF = new TextMergeInputTDF();
  private     TextMergeInputDirEntry inDir = new TextMergeInputDirEntry();
  private     TextMergeInputHTML    inHTML = new TextMergeInputHTML();
  private     TextMergeInputXML     inXML = new TextMergeInputXML();
  private     TextMergeInputExcel   inExcel = new TextMergeInputExcel();
  private     TextMergeInputTunes   inTunes = new TextMergeInputTunes();
  private     TextMergeInputClub    inClub = new TextMergeInputClub();
  // private     TextMergeInputOutline inOutline = new TextMergeInputOutline();
  private     TextMergeInputGrid    inGrid = new TextMergeInputGrid();
  // private     TextMergeInputReturnedMail inMail = new TextMergeInputReturnedMail();
  private     TextMergeInputYojimbo inYojimbo = new TextMergeInputYojimbo();
  // private     TextMergeInputYAML    inYAML = new TextMergeInputYAML();
  private     TextMergeInputMetaMarkdown inMarkdown = new TextMergeInputMetaMarkdown();
	
	/*
	   Scripting stuff
	 */
	private     URL                 scriptURL;
	private     ScriptFile          inScript = null;
	private     File                inScriptFile = null;
	private     ScriptFile          outScript;
  private     File                outScriptFile;
	private     ScriptAction        inAction;
	private     String              inActionModule;
	private     String              inActionAction;
	private     String              inActionModifier;
	private     String              inActionObject;
	private     String              inActionValue;
  private			int									inActionValueAsInt;
  private			boolean							inActionValueValidInt;
  private			String							inputObject = "";
	private     ScriptAction        outAction;
	private     boolean             scriptRecording = false;
	private     boolean             scriptPlaying = false; 
  private     PSFileList          recentScripts;   
  private     String              autoPlay = "";
  private     String              easyPlay = "";
  private     File                easyPlayFile = null;
	
	// Literals used to construct and decode script commands
	        static final String INPUT_MODULE        = "input";
	        static final String SORT_MODULE         = "sort";
          static final String COMBINE_MODULE      = "combine";
	        static final String FILTER_MODULE       = "filter";
	        static final String OUTPUT_MODULE       = "output";
	        static final String TEMPLATE_MODULE     = "template";
          static final String CALLBACK_MODULE     = "callback";
          static final String OPEN_ACTION         = "open";
          static final String EPUB_IN_ACTION      = "epubin";
          static final String EPUB_OUT_ACTION     = "epubout";
          static final String SET_ACTION          = "set";
          static final String ADD_ACTION          = "add";
          static final String CLEAR_ACTION        = "clear";
          static final String GENERATE_ACTION     = "generate";
          static final String URL_MODIFIER        = "url";
          static final String NO_MODIFIER         = "";
          static final String MERGE_OBJECT        = "merge";
          static final String MERGE_SAME_OBJECT   = "same";
          static final String NORMAL_OBJECT       = "normalization";
          static final String PARAMS_OBJECT       = "params";
          static final String AND_OR_OBJECT       = "andor";
          static final String DATA_LOSS_OBJECT    = "dataloss";
          static final String PRECEDENCE_OBJECT   = "precedence";
          static final String MIN_NO_LOSS_OBJECT  = "minnoloss";
          static final String USING_DICTIONARY_OBJECT = "usedict";
          static final String DIR_DEPTH_OBJECT		= "dirdepth";
          static final String NO_OBJECT           = "";
          static final String NO_VALUE            = "";
	
  // A log juggler to create other logging objects and perform logging operations. 
  private     LogJuggler          logJuggler;
  
  // The output destination for log messages. 
  private     LogOutput           logout;
  
  // The object that performs the logging operations. 
  private     Logger              log;
  
  // An object that can define an event worth logging. 
  private     LogEvent            logEvent;
  
  private     Trouble             trouble;
  
  // Debugging Instance
  private			Debug								debug = new Debug (false);
  
  /** Various system properties. */
  // private			String							mrjVersion;
  private     File                appFolder;
  private     String              userDirString;
  private     String              fileSeparatorString;
  private     File                userDirFile;
  private     URL                 pageURL;
  private     URL                 userGuideURL;
  private     URL                 programHistoryURL;
  // private			File								userGuideFile;
  // private			String							userGuideString;
  private static final String     TEMPLATE_LIBRARY_KEY = "templatelib";
  private     File                templateLibrary;
  
  private     File                currentDirectory = null;
  private     File                scriptDirectory = null;
  private			String							normalizerPath = "";
	            
	private     RecordDefinition    recDef;
	private     int                 numberOfFields = 0;
  
  private     String              possibleFileName = "";
  private     String              fileName = "";
  private     String              tabName = "";
  private     String              lastTabNameOutput = "";
  /** File name of file that contains basic information about PSTextMerge. */
  private     String              aboutFileName = "about.html";
  private			int									dirMaxDepth = 1;
  private     String              fileNameToDisplay;
  private     URL                 tabURL;
  private     DataSource          dataSource;

	private     DataSet             completeDataSet;
	private     DataSet             filteredDataSet;
  
  // Fields using for filtering
	private     FieldFilter         fieldFilter;
	private     CompoundFilter      compoundFilter;
	private     String              currentFilterField;
	private     int                 currentFilterColumn;
	private     String              currentFilterOperand;
	private     String              currentFilterValue = " ";
	private     boolean             currentAndLogic = true;
  
  // Fields used for sorting
  private			boolean							sorted = false;
	private     SequenceSpec        filterSortSpec;
	private     DataField           lastFieldValue;     
	private     Vector              currentFieldValues;
	private     SequenceSpec        sortSpec;
	private     String              currentSortField;
	private     String              currentSortDirection;
	private     DataTable           dataTable;
  
  // Fields used for combining
  private     int                 dataLossTolerance = 0;
  private			int                 precedence = +1;
  private			int									minNoLoss = 0;
  private			int									totalCombinations = -1;
	
  // Miscellaneous Fields
	private     String              iconFileName = "pstextmerge_icon_32.gif";
	private     URL                 iconURL;
	private     ImageIcon           iconImage;
  
  // File chosen as input Tab-Delimited File.
  private     File                chosenFile;

  // Epub files
  private     File                epubFolder;
  private     File                epubFile;
  
  // Fields used for Template processing
  private     Template            template;
  private     File                templateFile;
  private     File                lastTemplateFile = null;
        
  private     boolean             templateCreated = false;
  private     boolean             templateFileReady = false;
  private     boolean             templateFileOK = false;
  private     boolean             generateOutputOK = false;
        
  private     String              templateFileName = "                         ";
  private     String              outputFileName   = "                         ";
  
  // Fields used for tab-delimited data files, whether input or output
  private     FileName            tabFileName;
  
  // Data Dictionary Fields
	private			TabDelimFile				dictFile;
	private     DataDictionary      dataDict;
  private			boolean							usingDictionary = false;
  private     String              usingDictionaryValue = "No";
  public static final String      DICTIONARY_EXT = "dic";
  
  // Normalization Fields
  private			boolean							normalization = false;
  private static final String     NORMALIZATION_KEY = "normalization";
  private			int									normalType = 0;
  private     String							normalTypeValue = "No Normalization";
  private     DataSource          normalizer;
  
  // Merge Fields
  private			int							 		merge = 0;
  private			String							mergeValue = "No";
  
  // Fields used for Output processing
  private     File                chosenOutputFile;
  private     String              tabNameOutput = "";
  private			TabDelimFile				tabFileOutput;
  
  // Cross-Platform Support
  private     XOS                 xos = XOS.getShared();
  
  // Registration Info
  /*
  private			static final String REG_FILE_NAME = "parms.txt";
  private			FileInputStream     parmsIn;
  private			FileOutputStream    parmsOut;
  private			Properties					registrationProperties;
   */
  private     Home                home;
  private     UserPrefs           userPrefs;
  
  // Fields used for the User Interface
  private			JFrame							mainFrame;
  private     Border              raisedBevel;
  private     Border              etched;
  
  // Menu Objects
  private			JMenuBar						menuBar;
  
  private			JMenu								fileMenu;
  private			JMenuItem						fileOpen;
  private			JMenuItem						fileSave;
  private     JMenuItem           fileEpub;
  private			JMenuItem						fileExit;
  
  private			JMenu								templateMenu;
  private			JMenuItem						templateOpen;
  private     JMenuItem           templateOpenFromLibrary;
  private			JMenuItem						templateGenerate;
  
  private			JMenu								scriptMenu;
  private			JMenuItem						scriptRecord;
  private			JMenuItem						scriptEndRecording;
  private			JMenuItem						scriptPlay;
  private			JMenuItem						scriptReplay;
  private     JMenuItem           scriptAutoPlay;
  private     JMenuItem           scriptEasyPlay;
  
  private     JMenu               windowMenu;
  
  private			JMenu								helpMenu;
  private     JMenuItem           helpPurchase;
  private     JMenuItem           helpRegister;
  private     JSeparator          helpSeparator1;
  private     JMenuItem           helpHistory;
  private			JMenuItem						helpUserGuide;
  private     JSeparator          helpSeparator2;
  private			JMenuItem						helpAbout;
  
  private			JMenuItem						helpPSPubWebSite;
  private     JSeparator          helpSeparator3;
  private     JMenuItem           helpReduceWindowSize;

	
	private     GridBagger          gb = new GridBagger();
	
	private     JPanel              header;
	private     JLabel              programNameLabel;
	private     JLabel              fileNameLiteral   = new JLabel ("File Name:");
	private     JLabel              tabNameLabel      = new JLabel();
	private     JLabel              iconLabel;
	
	private     JTabbedPane         tabs;
	
	private     Insets              insetsTLBR0 = new Insets (0, 0, 0, 0);
	private     Insets              insetsTLBR1 = new Insets (1, 1, 1, 1);
	private     Insets              insetsTB2LR1 = new Insets (2, 1, 2, 1);
	private     Insets              insetsTB1LR3 = new Insets (1, 3, 1, 3);
	
	private     int                 tabPosition = 0;
	private     int                 viewTabPosition = 0;
	private     boolean             fileAvailable = false;
	
  // Input Panel objects
  private     JPanel              inputTab;
  
  // Open Input Button
  private     JButton             openDataButton;
  
  // Input Type Drop Down List
  private    JLabel               inputTypeLabel;
  
  private     JComboBox           inputTypeBox  = new JComboBox ();
  
  // Data Dictionary Check Box
  private    JLabel               inputDictionaryLabel;
  private    JCheckBox            inputDictionaryCkBox;
  
  // Merge Radio Buttons
  private    JLabel               inputMergeLabel;
  private		 ButtonGroup					inputMergeGroup;
  private 	 JRadioButton					inputMergeNoButton;
  private    JRadioButton         inputMergeButton;
  private		 JRadioButton				  inputMergeSameColumnsButton;
  
  // Directory Depth
  private    JLabel               inputDirMaxDepthLabel;
  private    JTextField           inputDirMaxDepthValue;
  private    JButton              inputDirMaxDepthUpButton;
  private    JButton              inputDirMaxDepthDownButton;
  
  // Normalization Type Drop Down List
  private    JLabel               inputNormalLabel;
  public    static final String  	INPUT_NORMAL0 = 		"None";
  public    static final String  	INPUT_NORMAL1 = 		"Boeing Docs";
  
  private     String[]            inputNormalTypes    = {
                                    INPUT_NORMAL0,
                                    INPUT_NORMAL1};
  private     JComboBox           inputNormalBox  = new JComboBox (inputNormalTypes);
    
  // Text Area (filler)
  private     JTextArea           inputText;
              
  // View Panel Objects
  private     JPanel              viewTab;
  private     JScrollPane         tableScrollPane;
  private     JTable              tabTable;
  private     boolean             tabTableBuilt = false;
  
  // Sort Panel Objects
  private     JPanel              sortTab;
  private     JLabel              sortFieldsLabel   = new JLabel();
  private     JComboBox           sortFieldsBox;
  private     String[]            sortDirections    = {
                                    SequenceField.ASCENDING,
                                    SequenceField.DESCENDING};
  private     JComboBox           sortDirectionBox  = new JComboBox (sortDirections);
  private     JButton             sortAddButton     = new JButton();
  private     JButton             sortClearButton   = new JButton();
  private     JButton             sortSetButton     = new JButton();
  
  private     JLabel              combineFieldsLabel   = new JLabel();
  
  // Combine Column 1 - Combine now using following parameters
  private     JButton             combineButton  = new JButton();
  
  // Combine Column 2 - Data Loss Tolerance
  private    JLabel               combineToleranceLabel;
  private    ButtonGroup          combineToleranceGroup;
  private    JRadioButton         combineToleranceNoLossButton;
  private    static final String  NO_DATA_LOSS_STRING 
                = "No Data Loss";
  private    JRadioButton         combineToleranceLaterButton;
  private		 static final String  LATER_OVERRIDES_STRING 
                = "Later Records Override Earlier";
  private		 JRadioButton					combineToleranceEarlierButton;
  private    static final String  EARLIER_OVERRIDES_STRING 
                = "Earlier Records Override Later";
  private    JRadioButton         combineToleranceAppendButton;
  private    static final String  COMBINED_STRING 
                = "Combine Fields Where Allowed";
  
  // Combine Column 3 - Minimum Lossless Fields
  private    JLabel               combineMinNoLossLabel;
  private    JTextField           combineMinNoLossValue;
  private    JButton              combineMinNoLossUpButton;
  private    JButton              combineMinNoLossDownButton;
  
  private     JLabel              sortTextLabel     = new JLabel();
  private     JScrollPane         sortTextScrollPane;
  private     JTextArea           sortText;
  
  private     boolean             sortTabBuilt      = false;  
  
  // Filter panel objects    
  private     JPanel              filterTab;
  private     JLabel              filterFieldsLabel   = new JLabel();
  private     JComboBox           filterFieldsBox;
  private     JComboBox           filterOperandBox;
  private     String[]            defaultFilterValues = {" "};
  private     JComboBox           filterValueBox;
  private     JRadioButton        filterAndButton     = new JRadioButton("And");
  private     JRadioButton        filterOrButton      = new JRadioButton("Or");
  private     ButtonGroup         filterAndOrGroup;
  private     JButton             filterAddButton     = new JButton();
  private     JButton             filterClearButton   = new JButton();
  private     JButton             filterSetButton     = new JButton();
  private     JLabel              filterTextLabel     = new JLabel();
  private     JScrollPane         filterTextScrollPane;
  private     JTextArea           filterText;
  private     boolean             filterTabBuilt      = false;
  
  // Output Panel objects
  private     JPanel              outputTab;
  
  // Open Output Button
  private     JButton             openOutputDataButton;
  private     JLabel              openOutputDataLabel;
  private     JLabel              openOutputDataName;
  
  // Data Dictionary Check Box
  private    JLabel               outputDictionaryLabel;
  private    JCheckBox            outputDictionaryCkBox;
  
  // Place holder for Third Column
  private    JLabel								outputPlaceHolder;
  
  private     JTextArea           outputText;
  
  // Template Panel Objects
  private     JPanel              templateTab; 
  
  private     JButton             setTemplateLibraryButton;
  
  private     JButton             openTemplateButton;
  private     JButton             openTemplateFromLibraryButton;

  private     JButton             generateOutputButton;
  
  private     JLabel              setTemplateLibraryLabel;
  private     JLabel              openTemplateLabel;
  private     JLabel              generateOutputLabel;
  
  private     JLabel              templateLibraryName;
  private     JLabel              openTemplateName;
  private     JLabel              generateOutputName;
  
  private     JTextArea           templateText;
  
  // Script panel objects
  private     JPanel              scriptTab;
  private     JButton             scriptRecordButton  = new JButton();
  private     JButton             scriptStopButton    = new JButton();
  private     JButton             scriptPlayButton    = new JButton();
  private     JButton             scriptReplayButton  = new JButton();
  private     JButton             scriptAutoPlayButton = new JButton();
  private     JButton             scriptEasyPlayButton = new JButton();
  private     JLabel							scriptPlaceHolder;
  private     JScrollPane         scriptTextScrollPane;
  private     JTextArea           scriptText;
  
  // Easy Play panel objects
  private     JPanel              easyPlayTab;
  
  // Logging Panel objects
  private    JPanel               logTab;
  
  // Column 1 - Logging Output
  private    JLabel               logOutputLabel;
  private    ButtonGroup          logOutputGroup;
  private    JRadioButton         logOutputNoneButton;
  private    JRadioButton         logOutputWindowButton;
  private		 JRadioButton					logOutputTextButton;
  private    JRadioButton         logOutputDiskButton;
  
  // Column 2 - Logging Threshold
  private    JLabel               logThresholdLabel;
  private    ButtonGroup          logThresholdGroup;
  private    JRadioButton         logThresholdNormalButton;
  private    JRadioButton         logThresholdMinorButton;
  private    JRadioButton         logThresholdMediumButton;
  private    JRadioButton         logThresholdMajorButton;
  
  // Column 3 - Log All Data?
  private    JLabel               logAllDataLabel;
  private    JCheckBox            logAllDataCkBox;
  
  // Bottom of Panel
  private     JScrollPane         logTextScrollPane;
  private     JTextArea           logText;
  
  private     AboutWindow         aboutWindow;
  
  // Registration variables
  private             RegisterWindow      registerWindow;
  private             UnregisteredWindow  unregisteredWindow;
  private static final int    DEMO_LIMIT    = 20;
  private             RegistrationCode    registrationCode;
  
	/**
     Main method.
   */
	public static void main(String[] args) {
		PSTextMerge merge = new PSTextMerge(args);
    merge.setMainClass (true);
    merge.run(null);
	} // end main method
  
	/**
     Play a script.
   
     @param script    Name of script file to be played.
     @param logOutput Output destination for log messages.
   */
	public static void execScript (String script, Logger logIn) {
    String[] args = new String[2];
    args[0] = "-q";
    args[1] = script;
		PSTextMerge merge = new PSTextMerge(args);
    merge.setMainClass (false);
    logIn.recordEvent (LogEvent.NORMAL, 
        PROGRAM_NAME + " " + PROGRAM_VERSION + " invoked by another program",
        false);
    merge.run(logIn);
	} // end execScript method

	/**
     Play a script.

     @param script    Name of script file to be played.
     @param logOutput Output destination for log messages.
     @param scriptExecutor A class that is prepared to execute requested
                           script callbacks.
   */
	public static void execScript (String script, Logger logIn,
      ScriptExecutor scriptExecutor) {
    String[] args = new String[2];
    args[0] = "-q";
    args[1] = script;
		PSTextMerge merge = new PSTextMerge(args);
    merge.setMainClass (false);
    merge.setScriptExecutor(scriptExecutor);
    logIn.recordEvent (LogEvent.MEDIUM,
        PROGRAM_NAME + " " + PROGRAM_VERSION + " invoked by another program",
        false);
    merge.run(logIn);
	} // end execScript method

	/**
     Constructor.
   */
  public PSTextMerge(String[] args) {
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.startsWith ("-")) {
        arg = arg.toLowerCase();
        for (int j = 1; j < arg.length(); j++) {
          char opt = arg.charAt (j);
          if (opt == 'q') {
            quietMode = true;
          }
        }
      } else {
        startingScript = arg;
      }
    } // end argument processing
    
	}
  
  /**
    Indicate whether this is the main class.
   
    @param mainClass True if this is the main class, false if called from another.
   */
  public void setMainClass (boolean mainClass) {
    this.mainClass = mainClass;
  }

  public void setScriptExecutor(ScriptExecutor scriptExecutor) {
    this.scriptExecutor = scriptExecutor;
  }
  
  /**
     Get the user interface up and running.
   */
  private void run(Logger logIn) {
    trouble = Trouble.getShared();
    if (mainClass) {
      xos.setDomainLevel1 ("powersurgepub");
      xos.setDomainLevel2 ("com");
      xos.setProgramName (PROGRAM_NAME);
      xos.initialize();
    }
    // mrjVersion = System.getProperty("mrj.version");
    mainFrame = new JFrame(PROGRAM_NAME);
    if (mainClass) {
      xos.setMainWindow (mainFrame);
      xos.setXHandler (this);
      home = Home.getShared(PROGRAM_NAME, PROGRAM_VERSION);
    } else {
      home = Home.getShared();
    }
    appFolder = home.getAppFolder();
    
	  if (mainClass) {
      mainFrame.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
      mainFrame.addWindowListener (new WindowAdapter()
        {
          public void windowClosing (WindowEvent e) {
            handleQuit();
          } // end ActionPerformed method
        } // end action listener for filter fields combo box
      ); 
    }

		try {
			initComponents(logIn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

    if (mainClass) {
      xos.setFileMenu (fileMenu);
      xos.setHelpMenu (helpMenu);
      WindowMenuManager.getShared().addWindowMenu(windowMenu);
      try {
        userGuideURL = new URL (pageURL, USER_GUIDE);
      } catch (MalformedURLException e) {
      }
      try {
        programHistoryURL = new URL(pageURL, PROGRAM_HISTORY);
      } catch (MalformedURLException e) {
        // shouldn't happen
      }
      xos.setHelpMenuItem (helpUserGuide);
      registerWindow = RegisterWindow.getShared();
      registerWindow.setDemoLimit(DEMO_LIMIT);
      Trouble.getShared().setParent(mainFrame);
    }
    calcDefaultScreenLocation();
    mainFrame.setBounds (
        userPrefs.getPrefAsInt (UserPrefs.LEFT, defaultX),
        userPrefs.getPrefAsInt (UserPrefs.TOP,  defaultY),
        userPrefs.getPrefAsInt (UserPrefs.WIDTH, DEFAULT_WIDTH),
        userPrefs.getPrefAsInt (UserPrefs.HEIGHT, DEFAULT_HEIGHT));
    
    // Initialization related to input modules
    int insertAt = 0;
    insertAt = addInputModule(inTDF, insertAt);
    insertAt = addInputModule(inClub, insertAt);
    insertAt = addInputModule(inExcel, insertAt);
    insertAt = addInputModule(inDir, insertAt);
    insertAt = addInputModule(inGrid, insertAt);
    insertAt = addInputModule(inHTML, insertAt);
    insertAt = addInputModule(inTunes, insertAt);
    insertAt = addInputModule(inMarkdown, insertAt);
    // insertAt = addInputModule(inOutline, insertAt);
    // insertAt = addInputModule(inMail, insertAt);
    insertAt = addInputModule(inXML, insertAt);
    // insertAt = addInputModule(inYAML, insertAt);
    insertAt = addInputModule(inYojimbo, insertAt);
    inputTypeBox.setSelectedIndex (0);
    
    // Open script file if it was passed as a parameter
    possibleFileName = System.getProperty ("scriptfile", "");
    if ((possibleFileName != null) && (! possibleFileName.equals (""))) {
      fileName = possibleFileName;
    }
    else
    if (! startingScript.equals ("")) {
      fileName = startingScript;
    } 
    if (! fileName.equals("")) {
      File sFile = new File (fileName);
      boolean scriptFound = (sFile.exists() && sFile.isFile());
      if (! scriptFound) {
        log.recordEvent (LogEvent.MEDIUM, 
          "MSG001 " + sFile.toString() + " could not be opened as a valid Script File",
          true);
        sFile = new File (currentDirectory, fileName);
        scriptFound = (sFile.exists() && sFile.isFile());
      }
      if (scriptFound) {
        inScriptFile = sFile;
        inScript = new ScriptFile (sFile, templateLibrary.toString());
        playScript();
      } 
      else {
        log.recordEvent (LogEvent.MEDIUM, 
          sFile.toString() + " could not be opened as a valid Script File",
          true);
      } 
    } // end if non-blank file name
    
    if (quietMode) {
      handleQuit();
    } else {
      recentScripts = new PSFileList ("Play Recent", "recentscript", this);
      recentScripts.setMax (10);
      scriptMenu.add (recentScripts.getFileMenu());
      mainFrame.setVisible (true);
      autoPlay = userPrefs.getPref(AUTOPLAY, "");
      if (autoPlay.length() > 0) {
        File autoPlayFile = new File (autoPlay);
        if (autoPlayFile.exists() && autoPlayFile.canRead()) {
          inScriptFile = autoPlayFile;
          inScript = new ScriptFile (inScriptFile, templateLibrary.toString());
          playScript();
        } // end if input script file is available
      } // end if autoplay was specified
      checkUnregistered();
    } // end if not quiet mode
  }
  
  private void setDefaultScreenSizeAndLocation() {

		mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    mainFrame.setResizable (true);
		calcDefaultScreenLocation();
		mainFrame.setLocation (defaultX, defaultY);
  }
  
  private void calcDefaultScreenLocation() {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    defaultX = (d.width - mainFrame.getSize().width) / 2;
    defaultY = (d.height - mainFrame.getSize().height) / 2;
  }
  
  /**
   Add another input module to the list. 
  
   @param anotherInputModule Another PSTextMerge input module to be made
                             available. 
  */
  private int addInputModule (
      TextMergeInputModule anotherInputModule,
      int insertAt) {
    int k = insertAt;
    inputModules.add(anotherInputModule);
    anotherInputModule.setInputType(0);
    for (int j = 1; j <= anotherInputModule.getInputTypeMax(); j++) {
      inputTypeBox.insertItemAt
          (anotherInputModule.getInputTypeLabel(j), k);
      k++;
    }
    return k;
  }
  
  private void initInputModules() {
    for (int i = 0; i < inputModules.size(); i++) {
      inputModules.get(i).setInputType(0);
    }
  }

	public void initComponents(Logger logIn) throws Exception {  
    
    // Get System Properties
    userDirString = System.getProperty (GlobalConstants.USER_DIR);
    if ((userDirString != null) && (! userDirString.equals (""))) {
      userDirFile = new File (userDirString);
      currentDirectory = userDirFile;
      normalizerPath = userDirString;
      // System.out.println ("User Directory = " + userDirString);
    }
    currentDirectory = home.getUserHome();
    fileSeparatorString = System.getProperty (GlobalConstants.FILE_SEPARATOR, "/");
    try {
      pageURL = appFolder.toURI().toURL(); 
    } catch (MalformedURLException e) {
      trouble.report ("Trouble forming pageURL from " + appFolder.toString(), 
          "URL Problem");
    }
    
    // Set up logging stuff
    logJuggler = new LogJuggler("PSTextMerge");
    if (logIn != null) {
      logJuggler.setLog (logIn.getLog());
    }
    else
    if (quietMode) {
      logJuggler.setLog (LogJuggler.LOG_DISK_STRING);
    } 
    else {
      logJuggler.setLog (LogJuggler.LOG_TEXT_STRING);
    }
    this.logout = logJuggler.getLog();
    this.log = logJuggler.getLogger(); 
    if (logIn == null) {
      this.log.setLogAllData (false);
      this.log.setLogThreshold (LogEvent.NORMAL);
    } else {
      this.log.setLogAllData (logIn.getLogAllData());
      this.log.setLogThreshold (logIn.getLogThreshold());
    }
    logEvent = new LogEvent();
    // System.out.println ("Log Threshold is " + String.valueOf (log.getLogThreshold()));
    // log.recordEvent (LogEvent.NORMAL, 
    //     "PSTextMerge Log File established",
    //    false);
    
    // Determine which Tabs are to be displayed
    tabConfig = System.getProperty ("tabs", DEFAULT_TABS);
    for (int i = 0; i < tabConfig.length(); i++) {
      char t = Character.toUpperCase (tabConfig.charAt (i));
      switch (t) {
        case 'I':
          inputTabDisplay = true;
          break;
        case 'V':
          viewTabDisplay = true;
          break;
        case 'S':
          sortTabDisplay = true;
          break;
        case 'F':
          filterTabDisplay = true;
          break;
        case 'O':
        	outputTabDisplay = true;
        	break;
        case 'T':
          templateTabDisplay = true;
          break;
        case 'C':
          scriptTabDisplay = true;
          break;
        case 'L':
          logTabDisplay = true;
          break;
        case 'A':
          // aboutTabDisplay = true;
          break;
        default:
          log.recordEvent (LogEvent.MINOR, 
            String.valueOf (t) + " is not a valid Tab Identifier",
            false);
          break;
      } // end switch
    } // end for loop
    
    sortText = new JTextArea("");
    filterText = new JTextArea("");
    scriptText = new JTextArea("");
    
    // Get info from Parms file
    if (mainClass) {
      home = Home.getShared (PROGRAM_NAME, PROGRAM_VERSION);
    } else {
      home = Home.getShared();
    }
    userPrefs = UserPrefs.getShared();
    /*
    registrationProperties = new Properties (RegistrationCode.getDefaultProperties());
    try {
      parmsIn = new FileInputStream (REG_FILE_NAME);
      // registrationProperties.load (parmsIn);
    } catch (IOException e) {
        // no action needed -- unregistered
    }
     */
    // log.recordEvent (LogEvent.NORMAL,
    //     "PSTextMerge " + registrationMessage,
    //     false);
    String normalProperty = userPrefs.getPref (NORMALIZATION_KEY);
    normalization = Boolean.valueOf(normalProperty).booleanValue();
    if (! normalization) {
      File boeing = new File (home.getAppFolder(), "boeing.txt");
      if (boeing.exists()) {
        // System.out.println (
        //     "File " + boeing.getPath() + " found -- normalization on");
        normalization = true;
      } else {
        // System.out.println (
        //     "File " + boeing.getPath() + " not found -- normalization off");
      }
    }
    
    templateLibrary = new File (userPrefs.getPref (TEMPLATE_LIBRARY_KEY));
    if ((! templateLibrary.exists())
        || (! templateLibrary.canRead())
        || (! templateLibrary.isDirectory())) {
      templateLibrary = new File (appFolder.getPath(),  "templates");
    }
    // System.out.println (templateLibrary.toString() 
    //     + " Exists? " 
    //     + String.valueOf (templateLibrary.exists()));
          
    // Open file if it was passed as a parameter
    possibleFileName = System.getProperty ("tabfile", "");
    if ((possibleFileName != null) && (! possibleFileName.equals (""))) {
      fileName = possibleFileName;
      tabURL = new URL (pageURL, fileName);
      openURL();
    } else {
      openEmpty();
    }
    
    // Create common interface components
    raisedBevel = BorderFactory.createRaisedBevelBorder();
    etched      = BorderFactory.createEtchedBorder();
    
    // Create Menu Bar
    menuBar = new JMenuBar();
    mainFrame.setJMenuBar (menuBar);
    
    fileMenu = new JMenu("File");
    menuBar.add (fileMenu);
        
    // Create Header
    header = new JPanel();
    iconURL = new URL (pageURL, iconFileName);
    iconImage = new ImageIcon (iconURL);
    iconLabel = new JLabel (iconImage, JLabel.CENTER);
    iconLabel.setVisible (true);
    fileNameLiteral.setVisible (true);
    tabNameLabel.setVisible (true);
		tabNameLabel.setText (fileNameToDisplay);
		gb.startLayout (header, 5, 1);
		gb.setDefaultColumnWeight (0.0);
		gb.setAllInsets (1);
		gb.add (iconLabel);
		gb.setLeftRightInsets (10);
		gb.add (fileNameLiteral);
		gb.setColumnWeight (1.0);
		gb.add (tabNameLabel);
		gb.setAllInsets (1);
    mainFrame.getContentPane().add (header, BorderLayout.NORTH);
    
		// Create tabbed pane
		tabs = new JTabbedPane();
    
    // Create Easy Play Tab
    easyPlay = userPrefs.getPref(EASYPLAY, "");
    if (easyPlay.length() > 0) {
      addEasyPlayTab(easyPlay);
    }
		
		// Create Input Tab
		if (inputTabDisplay) {
		  addInputTab();
		  tabPosition++;
		}
		
		// Create view Tab with Table
		if (viewTabDisplay) {
		  addViewTab();
		  viewTabPosition = tabPosition;
		  tabPosition++;
		}
		
		// Create Sort Pane
		if (sortTabDisplay) {
		  addSortTab();
		  tabPosition++;
		}
		
		// Create Filter Pane
		if (filterTabDisplay) {
		  addFilterTab();
		  tabPosition++;
		}
		
		// Create Output Pane
		if (outputTabDisplay) {
			addOutputTab();
			tabPosition++;
		}
		
		// Create a Template Pane
		if (templateTabDisplay) {
		  addTemplateTab();
		  tabPosition++;
		}
		
		// Create Script Pane
		if (scriptTabDisplay) {
		  addScriptTab();
		  tabPosition++;
		}
		
		// Create Logging Pane
		if (logTabDisplay) {
		  addLoggingTab();
		  tabPosition++;
		}
		
		// Create About Pane
    aboutWindow = new AboutWindow ();
    
    // Finish off File Menu
    if (! xos.isRunningOnMacOS()) {
      fileExit = new JMenuItem ("Exit/Quit");
      fileExit.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_Q,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
      fileMenu.add (fileExit);
      fileExit.addActionListener (new ActionListener ()
        {
          public void actionPerformed (ActionEvent event) {
            handleQuit();
          } // end actionPerformed method
        } // end action listener
      );
    }
    
    // Window menu
    windowMenu = new JMenu("Window");
    menuBar.add (windowMenu);
    
    // Help Menu 
    helpMenu = new JMenu("Help");
    menuBar.add (helpMenu);
    
    /* if (! xos.isRunningOnMacOS()) {
      helpAbout = new JMenuItem ("About " + PROGRAM_NAME);
      helpMenu.add (helpAbout);
      helpAbout.addActionListener (new ActionListener ()
        {
          public void actionPerformed (ActionEvent event) {
            handleAbout();
          } // end actionPerformed method
        } // end action listener
      );
    } */
    
    helpPurchase = new JMenuItem ("Purchase License");
    helpMenu.add (helpPurchase);
    helpPurchase.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          openURL (UnregisteredWindow.STORE);
        } // end ActionPerformed method
      } // end action listener
    );
    
    helpRegister = new JMenuItem ("Product Registration...");
    helpMenu.add (helpRegister);
    helpRegister.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          WindowMenuManager.getShared().locateCenterAndMakeVisible
              (mainFrame, registerWindow);
        } // end ActionPerformed method
      } // end action listener
    );
    
    helpSeparator1 = new JSeparator();
    helpMenu.add (helpSeparator1);
    
    helpHistory = new JMenuItem ("Program History");
    helpMenu.add (helpHistory);
    helpHistory.addActionListener(new ActionListener()
    {
      public void actionPerformed (ActionEvent event) {
        openURL (programHistoryURL);
      }
    });
    
    helpUserGuide = new JMenuItem ("User Guide");
    helpMenu.add (helpUserGuide);
    helpUserGuide.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          openURL (userGuideURL);
        } // end ActionPerformed method
      } // end action listener
    );
    
    helpSeparator2 = new JSeparator();
    helpMenu.add (helpSeparator2);

    helpPSPubWebSite = new JMenuItem (PROGRAM_NAME + " Home Page");
    helpMenu.add (helpPSPubWebSite);
    helpPSPubWebSite.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          openURL ("http://www.powersurgepub.com/");
        } // end ActionPerformed method
      } // end action listener
    );
    
    helpSeparator3 = new JSeparator();
    helpMenu.add (helpSeparator3);
    
    helpReduceWindowSize = new JMenuItem ("Reduce Window Size");
    helpReduceWindowSize.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_W,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    helpMenu.add (helpReduceWindowSize);
    helpReduceWindowSize.addActionListener(new ActionListener()
      {
        public void actionPerformed (ActionEvent event) {
          setDefaultScreenSizeAndLocation();
        }
      });
    
    log.recordEvent (LogEvent.NORMAL,
        "Application Folder = " + home.getAppFolder().toString(),
        false);
    log.recordEvent (LogEvent.NORMAL,
        "Java Virtual Machine = " + System.getProperty("java.vm.name") + 
        " version " + System.getProperty("java.vm.version") +
        " from " + StringUtils.removeQuotes(System.getProperty("java.vm.vendor")),
        false);
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    NumberFormat numberFormat = NumberFormat.getInstance();
    log.recordEvent (LogEvent.NORMAL,
        "Available Memory = " + numberFormat.format (Runtime.getRuntime().freeMemory()),
        false);
		
		// Set the starting Tab that will be visible
		if (fileAvailable) {
		  tabs.setSelectedIndex (viewTabPosition);
		}
		else {
		  tabs.setSelectedIndex (0);
		}
		
		// Add the Tabbed Pane to the Applet
		mainFrame.getContentPane().add (tabs, BorderLayout.CENTER);

	} // end initComponents method	  
  
  /**
     Throw a URL to the local Web Browser for display.
   */
  public void openURL (URL url) {
    openURL (url.toString());
  }
  
  private void openURL (String url) {
    try {
      xos.openURL (StringUtils.cleanURLString(url));
    } catch (java.io.IOException e) {
      JOptionPane.showMessageDialog (tabs, 
        e.getMessage(),
        "Browser Error",
        JOptionPane.ERROR_MESSAGE);
    }
  }
	
	/**
	   Add the Input tab to the interface. 
	 */
	private void addInputTab() {
		inputTab = new JPanel();
    
    // Button to Specify the Input Source and Open it
    openDataButton = new JButton ("Open Input");
    openDataButton.setToolTipText
      ("Specify the Data Source to be Input");
    openDataButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          openInputFile();		    
        } // end ActionPerformed method
		  } // end action listener
		);
    
    // Equivalent Menu Item
    fileOpen = new JMenuItem ("Open...");
    fileOpen.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_O,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    fileMenu.add (fileOpen);
    fileOpen.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (inputTab);
          openInputFile();		    
        } // end ActionPerformed method
      } // end action listener
    );

    // Menu Item for EPub
    fileEpub = new JMenuItem ("Create EPub...");
    fileMenu.add (fileEpub);
    fileEpub.addActionListener (new ActionListener()
      {
        public void actionPerformed (ActionEvent event) {
          chooseEpubFiles();
        } // end ActionPerformed method
      } // end action listener
    );
        
		// Combo box for input type
    inputTypeLabel = new JLabel ("Type of Data Source", JLabel.LEFT);
    inputTypeLabel.setBorder (etched);
    
		inputTypeBox.setEditable (false);
		inputTypeBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      String inType = (String)cb.getSelectedItem();
          initInputModules();
          
          inputModuleIndex = 0;
          inputModuleFound = false;
          while (inputModuleIndex < inputModules.size() && (! inputModuleFound)) {
            inputModule = inputModules.get(inputModuleIndex);
            inputModuleFound = inputModule.setInputType(inType);
            if (! inputModuleFound) {
              inputModuleIndex++;
            }
          }
		    } // end ActionPerformed method
		  } // end action listener for input type combo box
		); 
    
    // Create Check Box for Data Dictionary Input
    inputDictionaryLabel = new JLabel ("Data Dictionary Input", JLabel.LEFT);
    inputDictionaryLabel.setBorder (etched);
    
    inputDictionaryCkBox = new JCheckBox ("Open Companion Dictionary?");
    inputDictionaryCkBox.setSelected (false);
    inputDictionaryCkBox.addItemListener (new ItemListener()
		  {
		    public void itemStateChanged (ItemEvent event) {
          usingDictionary 
              = (event.getStateChange() != ItemEvent.DESELECTED);
          setDictionaryImplications();
		    } // end itemStateChanged method
		  } // end action listener
		);
    
    // Create Radio Buttons for File Merge
    inputMergeLabel = new JLabel ("Merge into Existing Data", JLabel.LEFT);
    inputMergeLabel.setBorder (etched);

    inputMergeGroup = new ButtonGroup();
  
    inputMergeNoButton = new JRadioButton ("No Merge");
    inputMergeNoButton.setActionCommand ("NO");
    inputMergeNoButton.setSelected (true);
    merge = 0;
    inputMergeGroup.add (inputMergeNoButton);
    inputMergeNoButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          merge = 0;
          setMergeImplications();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    inputMergeButton = new JRadioButton ("Merge New Data with Old");
    inputMergeButton.setActionCommand ("MERGE");
    inputMergeGroup.add (inputMergeButton);
    inputMergeButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          merge = 1;
          setMergeImplications();
		    } // end ActionPerformed method
		  } // end action listener
		);
		
    inputMergeSameColumnsButton = new JRadioButton ("Merge with Same Columns");
    inputMergeSameColumnsButton.setActionCommand ("SAME");
    inputMergeGroup.add (inputMergeSameColumnsButton);
    inputMergeSameColumnsButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          merge = 2;
          setMergeImplications();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // create directory depth fields
    inputDirMaxDepthLabel = new JLabel ("Maximum Directory Depth", JLabel.LEFT);
    inputDirMaxDepthLabel.setBorder (etched);
    
    inputDirMaxDepthValue = new JTextField (String.valueOf(dirMaxDepth));
    inputDirMaxDepthValue.setEditable (false);
    inputDirMaxDepthValue.setHorizontalAlignment (JTextField.RIGHT);
    
    inputDirMaxDepthUpButton = new JButton ("Increment (+)");
    inputDirMaxDepthUpButton.setBorder (raisedBevel);
    inputDirMaxDepthUpButton.setToolTipText
      ("Increase Level of Sub-Directory Explosion");
    inputDirMaxDepthUpButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          dirMaxDepth++;
          inputDirMaxDepthValue.setText (String.valueOf(dirMaxDepth));
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    inputDirMaxDepthDownButton = new JButton ("Decrement (-)");
    inputDirMaxDepthDownButton.setBorder (raisedBevel);
    inputDirMaxDepthDownButton.setToolTipText
      ("Decrease Level of Sub-Directory Explosion");
    inputDirMaxDepthDownButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          if (dirMaxDepth > 1) {
            dirMaxDepth--;
          }
          inputDirMaxDepthValue.setText (String.valueOf(dirMaxDepth));
		    } // end ActionPerformed method
		  } // end action listener
		);
    
		// Combo box for Normalization type
    inputNormalLabel = new JLabel ("Data Normalization", JLabel.LEFT);
    inputNormalLabel.setBorder (etched);
    
		normalType = 0;
    if (normalization) {
      inputNormalBox.setSelectedIndex (0);
      inputNormalBox.setEditable (false);
      inputNormalBox.addActionListener (new ActionListener()
        {
          public void actionPerformed (ActionEvent event) {
            JComboBox cb = (JComboBox)event.getSource();
            String inType = (String)cb.getSelectedItem();
            normalType = 0;
            if (inType.equals (INPUT_NORMAL1)) {
              normalType = 1;
            }
            setNormalTypeImplications();
          } // end ActionPerformed method
        } // end action listener for input type combo box
      ); 
    }
        
    // Bottom of Screen
		inputText = new JTextArea("");
		inputText.setLineWrap (true);
		inputText.setEditable (false);
		inputText.setWrapStyleWord (true);
		inputText.setVisible (true);

    // Finish up the Input Pane
    setMergeImplications();
    setNormalTypeImplications();
    
		gb.startLayout (inputTab, 3, 9);
		gb.setByRows (false);
		gb.setAllInsets (2);
		gb.setDefaultRowWeight (0.0);
		
		// Column 0
    gb.setBottomInset(6);
    gb.add (openDataButton);
    
    gb.setTopInset(6);
    gb.setBottomInset(2);
    gb.add (inputTypeLabel);
    gb.setAllInsets(2);
    gb.add (inputTypeBox);
    
    gb.setRow(5);
    gb.setTopInset(6);
    gb.add (inputDirMaxDepthLabel);
    gb.setAllInsets(2);
    gb.add (inputDirMaxDepthValue);
    gb.add (inputDirMaxDepthUpButton);
    gb.add (inputDirMaxDepthDownButton);
    
    // Column 1
    gb.nextColumn();
    gb.setRow(1);
    gb.setTopInset(6);
    gb.add (inputDictionaryLabel);
    gb.setAllInsets(2);
    gb.add (inputDictionaryCkBox);
    
    if (normalization) {
      gb.setRow(5);
      gb.setTopInset(6);
      gb.setBottomInset(2);
      gb.add (inputNormalLabel);
      gb.setAllInsets(2);
      gb.add (inputNormalBox);
    }
    
    // Column 2
    gb.nextColumn();
    gb.setRow(1);
    gb.setTopInset(6);
    gb.add (inputMergeLabel);
    gb.setAllInsets(2);
    gb.setTopInset(5);
    gb.add (inputMergeNoButton);
    gb.add (inputMergeButton);
    gb.add (inputMergeSameColumnsButton);
    
    // Bottom of Panel
    gb.setRow(9);
    gb.setColumn(0);
    gb.setWidth (3);
    gb.setRowWeight (1.0);
    gb.add (inputText);

		tabs.addTab ("Input", inputTab);
	} // end method addInputTab
  
  /**
     Open the input file.
   */
  private void openInputFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    if (currentDirectory != null) {
      fileChooser.setCurrentDirectory (currentDirectory);
    } 
    int fileChooserReturn 
      = fileChooser.showOpenDialog (openDataButton);
    if (fileChooserReturn 
      == JFileChooser.APPROVE_OPTION) {
      chosenFile = fileChooser.getSelectedFile();
      openFileOrDirectory();
    }
  }

  private void chooseEpubFiles () {

    // Let the user select the folder containing the contents of the book
    JFileChooser folderChooser = new JFileChooser();
    folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    folderChooser.setDialogTitle("Open Folder containing EPub Contents");
    if (currentDirectory != null) {
      folderChooser.setCurrentDirectory (currentDirectory);
    }
    int folderChooserReturn = folderChooser.showOpenDialog (tabs);
    if (folderChooserReturn == JFileChooser.APPROVE_OPTION) {

      // Let the user specify the name and location of the output epub file
      epubFolder = folderChooser.getSelectedFile();
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setCurrentDirectory(epubFolder.getParentFile());
      String epubFileName;
      if (epubFolder.getName().equalsIgnoreCase("epub")) {
        epubFileName = epubFolder.getParentFile().getName() + ".epub";
      } else {
        epubFileName = epubFolder.getName() + ".epub";
      }
      fileChooser.setSelectedFile(
            new File(epubFolder.getParentFile(), epubFileName));
      fileChooser.setDialogTitle("Specify the output EPub File");
      int fileChooserReturn = fileChooser.showSaveDialog (tabs);
      if (fileChooserReturn == JFileChooser.APPROVE_OPTION) {

        // Copy the folder contents into the output zip file
        epubFile = fileChooser.getSelectedFile();
        createEpub();
      } // end if user specified an output file
    } // end if user specified an input folder
  } // end method chooseEpubFiles

  /**
     Create the EPub file.
   */
  private void createEpub() {
        
    try {
      FileOutputStream epubStream = new FileOutputStream(epubFile);
      ZipOutputStream epub = new ZipOutputStream(
          new BufferedOutputStream(epubStream));
      // The Mime Type must be the first entry
      addEpubEntry (epub, epubFolder, new File (epubFolder, MIME_TYPE),
          ZipOutputStream.DEFLATED);
      addEpubDirectory (epub, epubFolder, epubFolder);
      epub.close();
      recordScriptAction (INPUT_MODULE, EPUB_IN_ACTION, 
          NO_MODIFIER,
          // DIRECTORY_MODIFIER,
        inputObject, epubFolder.toString());
      recordScriptAction (INPUT_MODULE, EPUB_OUT_ACTION, inTDF.getInputTypeModifier(1),
        inputObject, epubFile.toString());
      log.recordEvent (LogEvent.NORMAL,
        "Successfully created EPub file " + epubFile.toString(),
        false);
    } catch (IOException e) {
      log.recordEvent (LogEvent.MEDIUM,
        "Unable to create EPub file " + epubFile.toString()
        + " due to I/O Exception " + e.toString(),
        false);
      trouble.report
        ("I/O error creating an EPub from " + epubFolder.toString(),
        "EPub Problem");
    }
  } // end method createEpub

  /**
   Add the contents of the specified folder, including the contents
   of sub-folders, to the specified zip output stream.

   @param zipOut     The zip output stream to receive the output.
   @param topFolder  The top folder being zipped.
   @param folder     The specific folder to be zipped.
   @throws java.io.IOException
   */
  private void addEpubDirectory(
      ZipOutputStream zipOut, 
      File topFolder, 
      File folder)
        throws java.io.IOException {
    
    String filesAndFolders[] = folder.list();
    for (int i = 0; i < filesAndFolders.length; i++) {
      String nextFileOrFolderName = filesAndFolders[i];
      File nextFileOrFolder = new File (folder, nextFileOrFolderName);
      if (nextFileOrFolderName.equalsIgnoreCase (MIME_TYPE)) {
        // skip this, since we should have already added it as the first entry
      }
      else
      if (nextFileOrFolderName.startsWith(".")) {
        // skip this, since we don't want hidden system files
      }
      else
      if (nextFileOrFolder.isDirectory()) {
        addEpubDirectory (zipOut, topFolder, nextFileOrFolder);
      }
      else
      if (nextFileOrFolder.isFile()) {
        addEpubEntry (zipOut, topFolder, nextFileOrFolder,
            ZipOutputStream.DEFLATED);
      }
    } // end for each file or folder in the directory
  } // end method addEpubDirectory

  /**
   Add another entry to the specified zip output stream.

   @param zipOut     The zip output stream to receive the output.
   @param topFolder  The top folder being zipped.
   @param file       The specific file to be added.
   @throws java.io.IOException
   */
  private void addEpubEntry(
      ZipOutputStream zipOut,
      File topFolder,
      File file,
      int method)
        throws java.io.IOException {
    FileInputStream inStream = new FileInputStream(file);
    String topFolderPath = topFolder.getPath();
    String filePath = file.getPath();
    String zipPath = filePath.substring(topFolderPath.length() + 1);
    ZipEntry entry = new ZipEntry (zipPath);
    entry.setMethod(method);
    zipOut.putNextEntry(entry);
    int bytesRead;
    byte[] buffer = new byte[4096];
    while((bytesRead = inStream.read(buffer)) != -1) {
      zipOut.write(buffer, 0, bytesRead);
    }
    inStream.close();
  }
	
	/**
	   Add the View tab to the interface. 
	 */
	private void addViewTab() {
		viewTab = new JPanel();

		tabTable = new JTable(dataTable);
		tabTable.setAutoResizeMode (JTable.AUTO_RESIZE_OFF);
		tabTableBuilt = true;
    setColumnWidths();
		tabTable.setVisible(true);
		
		tableScrollPane = new JScrollPane(tabTable, 
		  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableScrollPane.setVisible(true);

		mainFrame.setLocation(new java.awt.Point(0, 0));

		gb.startLayout (viewTab, 1, 1);
		gb.setAllInsets (0);
		gb.add (tableScrollPane);
		
		tabs.addTab ("View", viewTab);
	} // end method addViewTab
	
	/**
	   Add the Sort tab to the interface. 
	 */
	private void addSortTab () {
		sortTab = new JPanel();
		
		sortFieldsLabel.setVisible(true);
		sortFieldsLabel.setText ("Add desired sort fields then Set the result:");
		
		// Combo box for sort field
		sortFieldsBox = new JComboBox ();
		loadSortFields();
		sortFieldsBox.setEditable (false);
		sortFieldsBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      currentSortField = (String)cb.getSelectedItem();
		    } // end ActionPerformed method
		  } // end action listener for sort fields combo box
		); 
		
		// Combo box for sort direction
		sortDirectionBox.setSelectedIndex (0);
		currentSortDirection = (String)sortDirectionBox.getSelectedItem();
		sortDirectionBox.setEditable (false);
		sortDirectionBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      currentSortDirection = (String)cb.getSelectedItem();
		    } // end ActionPerformed method
		  } // end action listener for sort direction combo box
		); 
		
		// Button to add the current sort field to the parameters being built
		sortAddButton.setText("Add");
		sortAddButton.setVisible(true);
		sortAddButton.setToolTipText("Add Field and Direction to Sort Parameters");
		sortAddButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      sortAdd();
		    } // end ActionPerformed method
		  } // end action listener for sort add button
		);

    // Button to clear the current sort parameters
		sortClearButton.setText("Clear");
		sortClearButton.setVisible(true);
		sortClearButton.setToolTipText("Clear all Sort Parameters");
		sortClearButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      sortClear();
		    } // end ActionPerformed method
		  } // end action listener for sort add button
		);

    // Button to set the current sort parameters
		sortSetButton.setText("Set");
		sortSetButton.setVisible(true);
		sortSetButton.setToolTipText("Set Table Sort Parameters as Specified Below");
		sortSetButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      sortSetParams();
		    } // end ActionPerformed method
		  } // end action listener for sort add button
		); 
    
    combineFieldsLabel.setText ("After setting a sort sequence, optionally combine records with duplicate keys");
    
    // Create combine column 1 - Combine Button
		combineButton.setText("Combine");
		combineButton.setVisible(true);
		combineButton.setToolTipText("Combine Records Using Parameters Shown");
		combineButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          if (sorted) {
            combineSet();
          }
          else {
            JOptionPane.showMessageDialog (tabs, 
              "Data must be sorted before it can be combined",
              "Sort/Combine Error",
              JOptionPane.ERROR_MESSAGE);
          } // end if not sorted 
		    } // end ActionPerformed method
		  } // end action listener for filter add button
		);
    
    // create combine column 2 - Data Loss Tolerance
    combineToleranceLabel = new JLabel ("Tolerance for Data Loss", JLabel.LEFT);
    combineToleranceLabel.setBorder (etched);

    combineToleranceGroup = new ButtonGroup();
  
    combineToleranceNoLossButton = new JRadioButton (NO_DATA_LOSS_STRING);
    combineToleranceNoLossButton.setActionCommand (NO_DATA_LOSS_STRING);
    combineToleranceNoLossButton.setSelected (true);
    dataLossTolerance = DataField.NO_DATA_LOSS;
    combineToleranceGroup.add (combineToleranceNoLossButton);
    combineToleranceNoLossButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          dataLossTolerance = DataField.NO_DATA_LOSS;
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    combineToleranceLaterButton = new JRadioButton (LATER_OVERRIDES_STRING);
    combineToleranceLaterButton.setActionCommand (LATER_OVERRIDES_STRING);
    combineToleranceGroup.add (combineToleranceLaterButton);
    combineToleranceLaterButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          dataLossTolerance = DataField.DATA_OVERRIDE;
          precedence = DataField.LATER_OVERRIDES;
		    } // end ActionPerformed method
		  } // end action listener
		);
		
    combineToleranceEarlierButton = new JRadioButton (EARLIER_OVERRIDES_STRING);
    combineToleranceEarlierButton.setActionCommand (EARLIER_OVERRIDES_STRING);
    combineToleranceGroup.add (combineToleranceEarlierButton);
    combineToleranceEarlierButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          dataLossTolerance = DataField.DATA_OVERRIDE;
          precedence = DataField.EARLIER_OVERRIDES;
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    combineToleranceAppendButton = new JRadioButton (COMBINED_STRING);
    combineToleranceAppendButton.setActionCommand (COMBINED_STRING);
    combineToleranceGroup.add (combineToleranceAppendButton);
    combineToleranceAppendButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          dataLossTolerance = DataField.DATA_COMBINED;
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // create combine column 3 - Minimum number of lossless fields
    combineMinNoLossLabel = new JLabel ("Minimum Number of Lossless Fields", JLabel.LEFT);
    combineMinNoLossLabel.setBorder (etched);
    
    combineMinNoLossValue = new JTextField (String.valueOf(minNoLoss));
    combineMinNoLossValue.setEditable (false);
    combineMinNoLossValue.setHorizontalAlignment (JTextField.RIGHT);
    
    combineMinNoLossUpButton = new JButton ("Increment (+)");
    combineMinNoLossUpButton.setBorder (raisedBevel);
    combineMinNoLossUpButton.setToolTipText
      ("Increase Minimum Number of Lossless Fields");
    combineMinNoLossUpButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          minNoLoss++;
          combineMinNoLossValue.setText (String.valueOf(minNoLoss));
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    combineMinNoLossDownButton = new JButton ("Decrement (-)");
    combineMinNoLossDownButton.setBorder (raisedBevel);
    combineMinNoLossDownButton.setToolTipText
      ("Decrease Minimum Number of Lossless Fields");
    combineMinNoLossDownButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          if (minNoLoss > 0) {
            minNoLoss--;
          }
          combineMinNoLossValue.setText (String.valueOf(minNoLoss));
		    } // end ActionPerformed method
		  } // end action listener
		);
    
		// Text area to display the current sort parameters
		sortTextLabel.setVisible(true);
		sortTextLabel.setText ("Resulting sort criteria will appear below:");
		
		sortText.setLineWrap (true);
		sortText.setEditable (false);
		sortText.setWrapStyleWord (true);
		sortText.setVisible (true);
    
    sortTextScrollPane = new JScrollPane(sortText, 
		  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sortTextScrollPane.setVisible(true);

    // Now layout the screen
		gb.startLayout (sortTab, 3, 11);
		gb.setDefaultRowWeight (0.0);
		gb.setLeftRightInsets (1);
		gb.setTopBottomInsets (3);
		gb.setWidth (3);
    gb.setTopInset (10);
		gb.add (sortFieldsLabel);
		
		gb.setAllInsets (1);
		gb.setWidth (2);
		gb.add (sortFieldsBox);
		gb.add (sortDirectionBox);
		
		gb.add (sortAddButton);
		gb.add (sortClearButton);
		gb.add (sortSetButton);
    
		gb.setLeftRightInsets (1);
		gb.setTopBottomInsets (3);
		gb.setWidth (3);
    gb.setTopInset (10);
		gb.add (combineFieldsLabel);
    
    gb.setRowWeight (0.0);
    gb.setAllInsets (1);
    gb.add (combineButton);
    gb.add (combineToleranceLabel);
    gb.add (combineMinNoLossLabel);
    
    gb.setColumn(1);
    gb.add (combineToleranceNoLossButton);
    gb.add (combineMinNoLossValue);
    
    gb.setColumn(1);
    gb.add (combineToleranceLaterButton);
    gb.add (combineMinNoLossUpButton);
    
    gb.setColumn(1);
    gb.add (combineToleranceEarlierButton);
    gb.add (combineMinNoLossDownButton);
    
    gb.setColumn(1);
    gb.add (combineToleranceAppendButton);
    
    gb.nextRow();
    gb.setTopBottomInsets (3);
		gb.setWidth (3);
    gb.setTopInset (10);
		gb.add (sortTextLabel);
    
    gb.setWidth (3);
		gb.setRowWeight (1.0);
		gb.add (sortTextScrollPane);
		
		tabs.addTab ("Sort", sortTab);
		
		sortTabBuilt = true;
	} // end method addSortTab
	
	/**
	   Add the Filter tab to the interface. 
	 */
	private void addFilterTab () {
		filterTab = new JPanel();
		
		// General Instructions for use of the Filter Tab
		filterFieldsLabel.setVisible(true);
		filterFieldsLabel.setText ("Add desired filter fields then Set the result:");
		
		filterValueBox = new JComboBox (defaultFilterValues);
		filterValueBox.setEditable (true);
		
		// Combo box to select a field to filter on
		filterFieldsBox = new JComboBox();
		loadFilterFields();
		filterFieldsBox.setEditable (false);
		filterFieldsBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      currentFilterField = (String)cb.getSelectedItem();
		      loadFilterValues();
		    } // end ActionPerformed method
		  } // end action listener for filter fields combo box
		); 
		
		// Combo box to select a logical operand for comparison to a value
		filterOperandBox = new JComboBox (DataField.WORD_LOGICAL_OPERANDS);
		filterOperandBox.setSelectedIndex (0);
		currentFilterOperand = (String)filterOperandBox.getSelectedItem();
		filterOperandBox.setEditable (false);
		filterOperandBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      currentFilterOperand = (String)cb.getSelectedItem();
		    } // end actionPerformed method
		  } // end action listener for filter operand combo box
		); 
		
		// Combo box to select or enter a value for comparison
		filterValueBox.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      JComboBox cb = (JComboBox)event.getSource();
		      currentFilterValue = (String)cb.getSelectedItem();
		    } // end actionPerformed method
		  } // end action listener for filter value combo box
		);
		
		// Radio button to select and logic
		filterAndButton.setActionCommand ("and");
		filterAndButton.setSelected (true);
		currentAndLogic = true;
		filterAndButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      filterAndOr (true);
		    } // end actionPerformed method
		  } // end action listener for filter and radio button
		);
		      
		// radio button to select or logic
		filterOrButton.setActionCommand ("or");
		filterOrButton.setSelected (false);
		filterOrButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      filterAndOr (false);
		    } // end actionPerformed method
		  } // end action listener for filter or radio button
		);
		
		filterAndOrGroup = new ButtonGroup();
		filterAndOrGroup.add(filterAndButton);
		filterAndOrGroup.add(filterOrButton);
		
		// Button to add current filter field, operand and value to current parameters
		filterAddButton.setText("Add");
		filterAddButton.setVisible(true);
		filterAddButton.setToolTipText("Add Field Filter to Parameter List");
		filterAddButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      filterAdd();
		    } // end ActionPerformed method
		  } // end action listener for filter add button
		);

    // Button to clear current filter parameters
		filterClearButton.setText("Clear");
		filterClearButton.setVisible(true);
		filterClearButton.setToolTipText("Clear all Filter Parameters");
		filterClearButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      filterClear();
		    } // end ActionPerformed method
		  } // end action listener for filter add button
		);

    // Button to set the current parameters
		filterSetButton.setText("Set");
		filterSetButton.setVisible(true);
		filterSetButton.setToolTipText("Set Table Filter Parameters as Specified Below");
		filterSetButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      filterSetParams();
		    } // end ActionPerformed method
		  } // end action listener for filter add button
		); 
		
		filterTextLabel.setVisible(true);
		filterTextLabel.setText ("Resulting filter criteria will appear below:");
		
		filterText.setLineWrap (true);
		filterText.setEditable (false);
		filterText.setWrapStyleWord (true);
		filterText.setVisible (true);
    
    filterTextScrollPane = new JScrollPane(filterText, 
		  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		filterTextScrollPane.setVisible(true);

		gb.startLayout (filterTab, 3, 6);
		gb.setDefaultRowWeight (0.0);
		gb.setLeftRightInsets (1);
		gb.setTopBottomInsets (3);
		gb.setWidth (3);
		gb.add (filterFieldsLabel);
		
		gb.setAllInsets (1);
		gb.add (filterFieldsBox);
		gb.add (filterOperandBox);
		gb.add (filterValueBox);
		
		gb.add (filterAddButton);
		gb.add (filterClearButton);
		gb.add (filterSetButton);
		
		gb.add (filterAndButton);
		gb.setWidth (2);
		gb.add (filterOrButton);
		
		gb.setTopBottomInsets (3);
		gb.setWidth (3);
		gb.add (filterTextLabel);
		
		gb.setTopBottomInsets (1);
		gb.setWidth (3);
		gb.setRowWeight (1.0);
		gb.add (filterTextScrollPane);
		
		tabs.addTab ("Filter", filterTab);
		
		filterTabBuilt = true;
	} // end method addFilterTab
	
	/**
	   Add the Output tab to the interface. 
	 */
	private void addOutputTab() {
		outputTab = new JPanel();
		
    // Button to Specify the Output Source and Open it
    openOutputDataButton = new JButton ("Save Output");
    openOutputDataButton.setToolTipText
      ("Specify the Output File Name and Location");
    openOutputDataButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          saveOutputFile();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // Equivalent Menu Item
    fileSave = new JMenuItem ("Save...");
    fileSave.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_S,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    fileMenu.add (fileSave);
    fileSave.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (outputTab);
          saveOutputFile();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Set Off Initially
    openOutputDataButton.setEnabled (false);
    fileSave.setEnabled (false);
        
    openOutputDataLabel       = new JLabel ("Output Data Destination", JLabel.CENTER);
    openOutputDataLabel.setBorder (etched);
        
    openOutputDataName    = new JLabel (tabNameOutput, JLabel.CENTER);
    openOutputDataName.setBorder (etched);
    
    // Create Check Box for Data Dictionary Output
    outputDictionaryLabel = new JLabel ("Data Dictionary Output", JLabel.LEFT);
    outputDictionaryLabel.setBorder (etched);
    
    outputDictionaryCkBox = new JCheckBox ("Save Companion Dictionary?");
    outputDictionaryCkBox.setSelected (false);
    usingDictionary = false;
    outputDictionaryCkBox.addItemListener (new ItemListener()
		  {
		    public void itemStateChanged (ItemEvent event) {
          usingDictionary 
              = (event.getStateChange() != ItemEvent.DESELECTED);
          setDictionaryImplications();
		    } // end itemStateChanged method
		  } // end action listener
		);
    
    // Create place holder for third column
    outputPlaceHolder       
        = new JLabel ("                            ", JLabel.CENTER);
    
    // Bottom of Screen
		outputText = new JTextArea("");
		outputText.setLineWrap (true);
		outputText.setEditable (false);
		outputText.setWrapStyleWord (true);
		outputText.setVisible (true);

    // Finish up the Input Pane
    setDictionaryImplications();
		gb.startLayout (outputTab, 3, 4);
		gb.setByRows (false);
		gb.setAllInsets (2);
		gb.setDefaultRowWeight (0.0);
		
		// Column 0
    gb.setBottomInset(6);
    gb.add (openOutputDataButton); 
    gb.setTopInset(6);
    gb.setBottomInset(2);
    gb.add (openOutputDataLabel);
    gb.setAllInsets(2);
    gb.add (openOutputDataName);
    
    // Column 1
    gb.nextColumn();
    gb.setRow(1);
    gb.setTopInset(6);
    gb.add (outputDictionaryLabel);
    gb.setAllInsets(2);
    gb.add (outputDictionaryCkBox);
    
    // Column 2
    gb.nextColumn();
    gb.setRow(1);
    gb.setTopInset(6);
    gb.add (outputPlaceHolder);
    gb.setAllInsets(2);
       
    // Bottom of Panel
    gb.setColumn (0);
    gb.setRow (3);
    gb.setWidth (3);
    gb.setRowWeight (1.0);
    gb.add (outputText);

		tabs.addTab ("Output", outputTab);
	} // end method addOutputTab
  
  /**
     Open and save output file.
   */
  private void saveOutputFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    if (currentDirectory != null) {
      fileChooser.setCurrentDirectory (currentDirectory);
    } 
    int fileChooserReturn 
      = fileChooser.showSaveDialog (openOutputDataButton);
    if (fileChooserReturn 
      == JFileChooser.APPROVE_OPTION) {
      chosenOutputFile = fileChooser.getSelectedFile();
      createOutput();
      openOutputDataName.setText (tabNameOutput);
    }
  }
	
	/**
	   Add the Template tab to the interface. 
	 */
	private void addTemplateTab() {
		templateTab = new JPanel();
    templateMenu = new JMenu("Template");
    menuBar.add (templateMenu);
		
    // Create Open Button for Template File
    openTemplateButton = new JButton ("Open Template");
    openTemplateButton.setToolTipText
      ("Specify the Template File to be used for the Merge");
    openTemplateButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          openTemplateFile();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // Equivalent Menu Item for Template Open
    templateOpen = new JMenuItem ("Open...");
    templateOpen.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_T,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    templateMenu.add (templateOpen);
    templateOpen.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (templateTab);
          openTemplateFile();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Create SetTemplate Library Button 
    setTemplateLibraryButton = new JButton ("Set Template Library");
    setTemplateLibraryButton.setToolTipText
      (templateLibrary.toString());
    setTemplateLibraryButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          setTemplateLibrary();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // Create Open from Library Button for Template File
    openTemplateFromLibraryButton = new JButton ("Open from Library");
    openTemplateFromLibraryButton.setToolTipText
      ("Specify the Template File to be used for the Merge");
    openTemplateFromLibraryButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          openTemplateFromLibrary();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // Equivalent Menu Item for Template Open
    templateOpenFromLibrary = new JMenuItem ("Open from Library...");
    templateMenu.add (templateOpenFromLibrary);
    templateOpenFromLibrary.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (templateTab);
          openTemplateFromLibrary();		    
        } // end ActionPerformed method
      } // end action listener
    );
           
    // Create Generate Button for Template
    generateOutputButton = new JButton ("Generate Output");
    generateOutputButton.setToolTipText
      ("Merge the data file with the template and "
      + "generate the output file(s) specified by the Template");
    generateOutputButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          generateTemplate();
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // Equivalent Menu Item for Template Generate
    templateGenerate = new JMenuItem ("Generate");
    templateGenerate.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_G,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    templateMenu.add (templateGenerate);
    templateGenerate.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (templateTab);
          generateTemplate();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Set Off Initially
    generateOutputButton.setEnabled (false);
    templateGenerate.setEnabled (false);
          
    // Create Descriptive Labels Beside Buttons
    setTemplateLibraryLabel = new JLabel ("Template Library", JLabel.CENTER);
    setTemplateLibraryLabel.setBorder (etched);
    
    openTemplateLabel = new JLabel ("Input Template File", JLabel.CENTER);
    openTemplateLabel.setBorder (etched);
        
    generateOutputLabel = new JLabel ("Merged Output File(s)", JLabel.CENTER);
    generateOutputLabel.setBorder (etched);
    
    // Create Dynamic Labels to hold Current Values
    templateLibraryName = new JLabel 
        (templateLibrary.getName(), JLabel.CENTER);
    templateLibraryName.setBorder (etched);
    
    openTemplateName = new JLabel (templateFileName, JLabel.CENTER);
    openTemplateName.setBorder (etched);
        
    generateOutputName = new JLabel (outputFileName, JLabel.CENTER);
    generateOutputName.setBorder (etched);
    
    // Bottom of Screen
		templateText = new JTextArea("");
		templateText.setLineWrap (true);
		templateText.setEditable (false);
		templateText.setWrapStyleWord (true);
		templateText.setVisible (true);

    // Finish up the Template Pane
		gb.startLayout (templateTab, 3, 4);
		gb.setByRows (false);
		gb.setAllInsets (2);
		gb.setDefaultRowWeight (0.0);
    
		// Column 0
    gb.setRow (1);
    gb.setBottomInset(6);
    gb.add (setTemplateLibraryButton);
    gb.setTopInset(6);
    gb.setBottomInset(2);
    gb.add (setTemplateLibraryLabel);
    gb.setAllInsets(2);
    gb.add (templateLibraryName);   
		
		// Column 1
    gb.nextColumn();
    gb.setBottomInset(6);
    gb.add (openTemplateButton);
    gb.add (openTemplateFromLibraryButton);
    gb.setTopInset(6);
    gb.setBottomInset(2);
    gb.add (openTemplateLabel);
    gb.setAllInsets(2);
    gb.add (openTemplateName);
    
    // Column 2
    gb.nextColumn();
    gb.setBottomInset(6);
    gb.add (generateOutputButton); 
    gb.setTopInset(6);
    gb.setBottomInset(2);
    gb.setRow (2);
    gb.add (generateOutputLabel);
    gb.setAllInsets(2);
    gb.add (generateOutputName);
    
    /* Column 2
    gb.nextColumn();
    gb.setRow(1);
    gb.setTopInset(6);
    gb.add (templatePlaceHolder);
    gb.setAllInsets(2);
     */
    
    // Bottom of Panel
    gb.setColumn (0);
    gb.setRow (4);
    gb.setWidth (3);
    gb.setRowWeight (1.0);
    gb.add (templateText);

		tabs.addTab ("Template", templateTab);
	} // end method addTemplateTab
  
  /**
     Set the location of the template library.
   */
  private void setTemplateLibrary () {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
    if (templateLibrary.exists()) {
      fileChooser.setCurrentDirectory (templateLibrary);
    }
    int fileChooserReturn 
      = fileChooser.showOpenDialog (openTemplateFromLibraryButton);
    if (fileChooserReturn
        == JFileChooser.APPROVE_OPTION) {
      templateLibrary = fileChooser.getSelectedFile();
      templateLibraryName.setText 
        (templateLibrary.getName());
      setTemplateLibraryButton.setToolTipText
      (templateLibrary.toString());
    }
  }
  
  /**
     Open a Template File from the Template Library
   */
  private void openTemplateFromLibrary () {
    templateFileOK = false;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    if (templateLibrary.exists()) {
      fileChooser.setCurrentDirectory (templateLibrary);
    } 
    int fileChooserReturn 
      = fileChooser.showOpenDialog (openTemplateButton);
    if (fileChooserReturn 
      == JFileChooser.APPROVE_OPTION) {
      templateFile = fileChooser.getSelectedFile();
      templateOpen();
      if (! templateFileOK) {
        JOptionPane.showMessageDialog (tabs, 
          "Error occurred while opening template file",
          "Template File Error",
          JOptionPane.ERROR_MESSAGE);
      } // end if error opening template file
    } // end if user performed a valid file selection
  }
  
  /**
     Open a Template File
   */
  private void openTemplateFile () {
    templateFileOK = false;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    if (currentDirectory != null) {
      fileChooser.setCurrentDirectory (currentDirectory);
    } 
    int fileChooserReturn 
      = fileChooser.showOpenDialog (openTemplateButton);
    if (fileChooserReturn 
      == JFileChooser.APPROVE_OPTION) {
      templateFile = fileChooser.getSelectedFile();
      templateOpen();
      if (! templateFileOK) {
        JOptionPane.showMessageDialog (tabs, 
          "Error occurred while opening template file",
          "Template File Error",
          JOptionPane.ERROR_MESSAGE);
      } // end if error opening template file
    } // end if user performed a valid file selection
  }
  
  /**
     Generate the template results
   */
  private void generateTemplate () {
    checkTemplateRepeat();
    boolean repeatOK = false;
    boolean ok = true;
    if (templateFileOK && fileAvailable && templateCreated) {
      if ((! lastTabNameOutput.equals (""))
        && (lastTemplateFile != null)
        && (lastTabNameOutput.equals (tabName))
        && (lastTemplateFile.equals (templateFile))) {
        int userResponse = JOptionPane.showConfirmDialog 
          (tabs, 
          "Are you sure you want to repeat\n the last merge operation?",
          "Repeat Confirmation",
          JOptionPane.OK_CANCEL_OPTION,
          JOptionPane.WARNING_MESSAGE);
        repeatOK = (userResponse != JOptionPane.CANCEL_OPTION);
        ok = repeatOK;
      } // end if repeating a merge operation
      if (ok) {
        templateGenerate();
        if (! generateOutputOK) {
          JOptionPane.showMessageDialog (tabs, 
            "Error occurred while generating output file",
            "Output File Error",
            JOptionPane.ERROR_MESSAGE);
        } // end if output not OK 
      } // end if all necessary files ready
    } else {
      JOptionPane.showMessageDialog (tabs, 
        "One or Both Input files Not Ready",
        "Input File Error",
        JOptionPane.ERROR_MESSAGE);
    } // end input files not ready
  }
	
	/**
	   Add the Script tab to the interface. 
	 */
	private void addScriptTab () {
		scriptTab = new JPanel();
    scriptMenu = new JMenu("Script");
    menuBar.add (scriptMenu);
				
		// Button to record a script
		scriptRecordButton.setText("Record");
		scriptRecordButton.setVisible(true);
		scriptRecordButton.setToolTipText("Start recording your actions");
		scriptRecordButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          startScriptRecording();
		    } // end ActionPerformed method
		  } // end action listener for script record button
		);
    
    // Equivalent Menu Item to Record a Script
    scriptRecord = new JMenuItem ("Record...");
    scriptRecord.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_R,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    scriptMenu.add (scriptRecord);
    scriptRecord.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (scriptTab);
          startScriptRecording();		    
        } // end ActionPerformed method
      } // end action listener
    );

    // Button to stop recording
		scriptStopButton.setText("Stop");
		scriptStopButton.setVisible(true);
		scriptStopButton.setToolTipText("Stop recording");
		scriptStopButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
		      stopScriptRecordingUI();
		    } // end ActionPerformed method
		  } // end action listener for script stop button
		);
    
    // Equivalent Menu Item to Stop Recording of a Script
    scriptEndRecording = new JMenuItem ("End Recording");
    scriptEndRecording.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_E,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    scriptMenu.add (scriptEndRecording);
    scriptEndRecording.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (scriptTab);
          stopScriptRecordingUI();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Set Off Initially
    scriptStopButton.setEnabled (false);
    scriptEndRecording.setEnabled (false);

    // Button to playback a script that has already been recorded
		scriptPlayButton.setText("Play");
		scriptPlayButton.setVisible(true);
		scriptPlayButton.setToolTipText("Play back a previously recorded script");
		scriptPlayButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          startScriptPlaying();
		    } // end ActionPerformed method
		  } // end action listener for script play button
		); 
    
    // Equivalent Menu Item to Play a Script
    scriptPlay = new JMenuItem ("Play");
    scriptPlay.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_P,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    scriptMenu.add (scriptPlay);
    scriptPlay.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (scriptTab);
          startScriptPlaying();		    
        } // end ActionPerformed method
      } // end action listener
    );

    // Button to replay the script just played/recorded
		scriptReplayButton.setText("Play Again");
		scriptReplayButton.setVisible(true);
		scriptReplayButton.setToolTipText("Replay the last script");
		scriptReplayButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          startScriptPlayingAgain();
		    } // end ActionPerformed method
		  } // end action listener for script play button
		); 
    
    // Equivalent Menu Item to Replay a Script
    scriptReplay = new JMenuItem ("Play Again");
    scriptReplay.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_A,
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    scriptMenu.add (scriptReplay);
    scriptReplay.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          tabs.setSelectedComponent (scriptTab);
          startScriptPlayingAgain();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Set Off Initially
    setScriptReplayControls();
    
    // Button to set an auto play script 
    autoPlay = userPrefs.getPref(AUTOPLAY, "");
    if (autoPlay.length() == 0) { 
		  scriptAutoPlayButton.setText("Turn Autoplay On");
    } else {
      scriptAutoPlayButton.setText("Turn Autoplay Off");
    }
		scriptAutoPlayButton.setVisible(true);
		scriptAutoPlayButton.setToolTipText("Select a script to automatically play at startup");
		scriptAutoPlayButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          toggleAutoPlay();
		    } // end ActionPerformed method
		  } // end action listener for script play button
		); 
    
    // Equivalent Menu Item to AutoPlay a Script
    if (autoPlay.length() == 0) {
      scriptAutoPlay = new JMenuItem ("Turn Autoplay On");
    } else {
      scriptAutoPlay = new JMenuItem("Turn Autoplay Off");
    }
    scriptMenu.add (scriptAutoPlay);
    scriptAutoPlay.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          toggleAutoPlay();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Button to set an easy play folder 
    easyPlay = userPrefs.getPref(EASYPLAY, "");
    if (easyPlay.length() == 0) { 
		  scriptEasyPlayButton.setText("Turn Easy Play On");
    } else {
      scriptEasyPlayButton.setText("Turn Easy Play Off");
    }
		scriptEasyPlayButton.setVisible(true);
		scriptEasyPlayButton.setToolTipText
        ("Select a folder of scripts to be invoked via buttons");
		scriptEasyPlayButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          toggleEasyPlay();
		    } // end ActionPerformed method
		  } // end action listener for script play button
		); 
    
    // Equivalent Menu Item to EasyPlay a Script
    if (easyPlay.length() == 0) {
      scriptEasyPlay = new JMenuItem ("Turn Easy Play On");
    } else {
      scriptEasyPlay = new JMenuItem("Turn Easy Play Off");
    }
    scriptMenu.add (scriptEasyPlay);
    scriptEasyPlay.addActionListener (new ActionListener() 
      {
        public void actionPerformed (ActionEvent event) {
          toggleEasyPlay();		    
        } // end ActionPerformed method
      } // end action listener
    );
    
    // Create place holder for third column
    scriptPlaceHolder       
        = new JLabel ("                            ", JLabel.CENTER);
				
		scriptText.setLineWrap (true);
		scriptText.setEditable (false);
		scriptText.setWrapStyleWord (true);
		scriptText.setVisible (true);
    
    scriptTextScrollPane = new JScrollPane(scriptText, 
		  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scriptTextScrollPane.setVisible(true);

		gb.startLayout (scriptTab, 3, 3);
		gb.setDefaultRowWeight (0.0);
		
		gb.setAllInsets (1);
		
		gb.add (scriptRecordButton);
		gb.add (scriptPlayButton);
    gb.add (scriptAutoPlayButton);
    
    gb.add (scriptStopButton);
		gb.add (scriptReplayButton);
    gb.add (scriptEasyPlayButton);
    // gb.add (scriptPlaceHolder);
		
		gb.nextRow ();
		
		gb.setWidth (3);
		gb.setRowWeight (1.0);
		gb.add (scriptTextScrollPane);
		
		tabs.addTab ("Script", scriptTab);
	} // end method addScriptTab
  
	/**
	   Add the Easy Play tab to the interface. 
	 */
	private void addEasyPlayTab (String easyPlay) {
		
    easyPlayTab = new JPanel();
    
    gb.startLayout (easyPlayTab, 3, 6);
		gb.setDefaultRowWeight (0.0);
		gb.setAllInsets (1);
    
    Dimension minButtonSize = new Dimension(200, 28);
    
    easyPlayFile = new File (easyPlay);
    String[] scripts = easyPlayFile.list();
    for (int i = 0; i < scripts.length; i++) {
      File scriptFile = new File (easyPlayFile, scripts[i]);
      FileName scriptName = new FileName(scriptFile);
      if (scriptName.getExt().equalsIgnoreCase(SCRIPT_EXT)) {
        JButton easyPlayButton = new JButton(scriptName.getBase());
        easyPlayButton.setActionCommand(scriptName.getBase());
        easyPlayButton.setToolTipText("Play the named script");
        easyPlayButton.setMinimumSize(minButtonSize);
        easyPlayButton.setVisible(true);
        easyPlayButton.addActionListener(new ActionListener()
        {
          public void actionPerformed (ActionEvent event) {
            inScriptFile = new File
                (easyPlayFile, event.getActionCommand() + "." + SCRIPT_EXT);
            inScript = new ScriptFile (inScriptFile, templateLibrary.toString());
            playScript();
          }
        });
        gb.add(easyPlayButton);
      } // end if directory entry ends with script extension
    } // end for each directory entry
		
		tabs.insertTab("Easy", null, easyPlayTab, "Easily play scripts from the Easy Play ", 0);

	} // end method addEasyPlayTab
  
  private void removeEasyPlayTab() {
    tabs.remove(easyPlayTab);
  }
  
  /**
     Start recording of a script.
   */
  private void  startScriptRecording() {
    if (! scriptRecording) {
      JFileChooser fileChooser = new JFileChooser();
      if (scriptDirectory != null) {
        fileChooser.setCurrentDirectory(scriptDirectory);
      }
      else
      if (currentDirectory != null) {
        fileChooser.setCurrentDirectory (currentDirectory);
      } 
      fileChooser.setDialogTitle ("Create Output File to Store Script");
      int fileChooserReturn 
        = fileChooser.showSaveDialog (scriptRecordButton);
      if (fileChooserReturn 
        == JFileChooser.APPROVE_OPTION) {
        outScriptFile = fileChooser.getSelectedFile();
        FileName outScriptFileName 
            = new FileName (outScriptFile, FileName.FILE_TYPE);
        if (outScriptFileName.getExt().trim().equals ("")) {
          outScriptFile 
              = new File (outScriptFileName.getPath(), 
                  outScriptFileName.replaceExt (SCRIPT_EXT));
        }
        outScript = new ScriptFile (outScriptFile, templateLibrary.toString());
        setCurrentDirectoryFromFile (outScriptFile);
        setScriptDirectoryFromFile (outScriptFile);
        normalizerPath = currentDirectory.getPath();
        outScript.setLog (log);
        outScript.openForOutput();
        scriptRecording = true;
        scriptText.append ("Recording new script "
          + outScript.getFileName() + GlobalConstants.LINE_FEED_STRING);
          
        scriptRecordButton.setEnabled (false);
        scriptRecord.setEnabled (false);
        scriptStopButton.setEnabled (true);
        scriptStopButton.requestFocus();
        scriptEndRecording.setEnabled (true);
        scriptPlayButton.setEnabled (false);
        scriptPlay.setEnabled (false);
        scriptReplayButton.setEnabled (false);
        scriptReplay.setEnabled (false);
      } // end if file approved
    } // end if not already recording
  }
  
  /**
     Stop recording of a script.
   */
  private void stopScriptRecordingUI () {
    stopScriptRecording();
    
    scriptRecordButton.setEnabled (true);
    scriptRecord.setEnabled (true);
    scriptStopButton.setEnabled (false);
    scriptEndRecording.setEnabled (false);
    scriptPlayButton.setEnabled (true);
    scriptPlay.setEnabled (true);
    setScriptReplayControls();
  }
  
  /**
     Start the playback of a script.
   */
  private void startScriptPlaying() {
    JFileChooser fileChooser = new JFileChooser();
    if (scriptDirectory != null) {
      fileChooser.setCurrentDirectory(scriptDirectory);
    }
    else
    if (currentDirectory != null) {
      fileChooser.setCurrentDirectory (currentDirectory);
    } 
    fileChooser.setDialogTitle ("Select Pre-Recorded Script to Play Back");
    int fileChooserReturn 
      = fileChooser.showOpenDialog (scriptPlayButton);
    if (fileChooserReturn 
        == JFileChooser.APPROVE_OPTION) {
      inScriptFile = fileChooser.getSelectedFile();
      setCurrentDirectoryFromFile (inScriptFile);
      setScriptDirectoryFromFile (inScriptFile);
      inScript = new ScriptFile (inScriptFile, templateLibrary.toString());
      playScript();
    } // end if file approved
  }
  
  /**
     Start the replay of the last script.
   */          
  private void startScriptPlayingAgain() {
    if (inScript != null) {
      playScript();
    } // end if script file defined
  }
  
  /**
     Toggle the script autoplay.
   */
  private void toggleAutoPlay() {
    if (autoPlay.length() > 0) {
      autoPlay = "";
      userPrefs.setPref(AUTOPLAY, autoPlay);
      scriptAutoPlay.setText("Turn Autoplay On");
      scriptAutoPlayButton.setText("Turn Autoplay On");
      removeEasyPlayTab();
    } else {
      JFileChooser fileChooser = new JFileChooser();
      if (scriptDirectory != null) {
        fileChooser.setCurrentDirectory (scriptDirectory);
      }
      else
      if (currentDirectory != null) {
        fileChooser.setCurrentDirectory (currentDirectory);
      } 
      fileChooser.setDialogTitle ("Select Autoplay Script");
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      int fileChooserReturn 
        = fileChooser.showOpenDialog (scriptAutoPlayButton);
      if (fileChooserReturn 
          == JFileChooser.APPROVE_OPTION) {
        File autoPlayFile = fileChooser.getSelectedFile();
          setScriptDirectoryFromFile(autoPlayFile);
          autoPlay = autoPlayFile.getPath();
          userPrefs.setPref(AUTOPLAY, autoPlay);
          scriptAutoPlay.setText("Turn Autoplay Off");
          scriptAutoPlayButton.setText("Turn Autoplay Off");

      } // end if file approved
    } // End if turning autoplay on
  }
  
  /**
     Toggle the Easy play folder.
   */
  private void toggleEasyPlay() {
    if (easyPlay.length() > 0) {
      easyPlay = "";
      userPrefs.setPref(EASYPLAY, easyPlay);
      scriptEasyPlay.setText("Turn Easy Play On");
      scriptEasyPlayButton.setText("Turn Easy Play On");
      removeEasyPlayTab();
    } else {
      JFileChooser fileChooser = new JFileChooser();
      if (scriptDirectory != null) {
        fileChooser.setCurrentDirectory(scriptDirectory);
      }
      else
      if (currentDirectory != null) {
        fileChooser.setCurrentDirectory (currentDirectory);
      } 
      fileChooser.setDialogTitle ("Select Easy Play Folder");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int fileChooserReturn 
        = fileChooser.showOpenDialog (scriptEasyPlayButton);
      if (fileChooserReturn 
          == JFileChooser.APPROVE_OPTION) {
        File easyPlayFile = fileChooser.getSelectedFile();
          this.setScriptDirectoryFromDir(easyPlayFile);
          easyPlay = easyPlayFile.getPath();
          userPrefs.setPref(EASYPLAY, easyPlay);
          scriptEasyPlay.setText("Turn Easy Play Off");
          scriptEasyPlayButton.setText("Turn Easy Play Off");
          addEasyPlayTab(easyPlay);
          tabs.setSelectedIndex(0);

      } // end if file approved
    } // End if turning easy play on
  }

	/**
	   Add the Logging tab to the interface. 
	 */
	private void addLoggingTab () {
	
    // create the Logging pane
    logTab = new JPanel();
    
    // create column 1 - Logging Output
    logOutputLabel = new JLabel ("Log Destination", JLabel.LEFT);
    logOutputLabel.setBorder (etched);

    logOutputGroup = new ButtonGroup();
  
    logOutputNoneButton = new JRadioButton (LogJuggler.LOG_NONE_STRING);
    logOutputNoneButton.setActionCommand (LogJuggler.LOG_NONE_STRING);
    logOutputGroup.add (logOutputNoneButton);
    logOutputNoneButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          logJuggler.setLog (event.getActionCommand());
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    logOutputWindowButton = new JRadioButton (LogJuggler.LOG_WINDOW_STRING);
    logOutputWindowButton.setActionCommand (LogJuggler.LOG_WINDOW_STRING);
    logOutputGroup.add (logOutputWindowButton);
    logOutputWindowButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          logJuggler.setLog (event.getActionCommand());
		    } // end ActionPerformed method
		  } // end action listener
		);
		
    logOutputTextButton = new JRadioButton (LogJuggler.LOG_TEXT_STRING);
    logOutputTextButton.setActionCommand (LogJuggler.LOG_TEXT_STRING);
    logOutputTextButton.setSelected (true);
    // logJuggler.setLog (LogJuggler.LOG_TEXT_STRING);
    logOutputGroup.add (logOutputTextButton);
    logOutputTextButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          logJuggler.setLog (event.getActionCommand());
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    logOutputDiskButton = new JRadioButton (LogJuggler.LOG_DISK_STRING);
    logOutputDiskButton.setActionCommand (LogJuggler.LOG_DISK_STRING);
    logOutputGroup.add (logOutputDiskButton);
    logOutputDiskButton.addActionListener  (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          logJuggler.setLog (event.getActionCommand());
		    } // end ActionPerformed method
		  } // end action listener
		);
            
    // create column 2 - Logging Threshold
    logThresholdLabel = new JLabel ("Logging Threshold", JLabel.LEFT);
    logThresholdLabel.setBorder (etched);
  
    logThresholdGroup = new ButtonGroup();

    logThresholdNormalButton = new JRadioButton (LOG_NORMAL_STRING);
    logThresholdNormalButton.setActionCommand (LOG_NORMAL_STRING);
    logThresholdNormalButton.setSelected (true);
    logThresholdGroup.add (logThresholdNormalButton);
    logThresholdNormalButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          log.setLogThreshold (LogEvent.NORMAL);
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    logThresholdMinorButton = new JRadioButton (LOG_MINOR_STRING);
    logThresholdMinorButton.setActionCommand (LOG_MINOR_STRING);
    // log.setLogThreshold (LogEvent.MINOR);
    logThresholdGroup.add (logThresholdMinorButton);
    logThresholdMinorButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          log.setLogThreshold (LogEvent.MINOR);
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    logThresholdMediumButton = new JRadioButton (LOG_MEDIUM_STRING);
    logThresholdMediumButton.setActionCommand (LOG_MEDIUM_STRING);
    logThresholdGroup.add (logThresholdMediumButton);
    logThresholdMediumButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          log.setLogThreshold (LogEvent.MEDIUM);
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    logThresholdMajorButton = new JRadioButton (LOG_MAJOR_STRING);
    logThresholdMajorButton.setActionCommand (LOG_MAJOR_STRING);
    logThresholdGroup.add (logThresholdMajorButton);
    logThresholdMajorButton.addActionListener (new ActionListener()
		  {
		    public void actionPerformed (ActionEvent event) {
          log.setLogThreshold (LogEvent.MAJOR);
		    } // end ActionPerformed method
		  } // end action listener
		);
    
    // create column 3 - Log All Data? 
    logAllDataLabel = new JLabel ("Data Logging", JLabel.LEFT);
    logAllDataLabel.setBorder (etched);
    
    logAllDataCkBox = new JCheckBox ("Log All Data?");
    logAllDataCkBox.setSelected (false);
    // log.setLogAllData (false);
    logAllDataCkBox.addItemListener (new ItemListener()
		  {
		    public void itemStateChanged (ItemEvent event) {
          boolean logAllData = true;
          if (event.getStateChange() == ItemEvent.DESELECTED) {
            logAllData = false;
          }
          log.setLogAllData (logAllData);
		    } // end itemStateChanged method
		  } // end action listener
		);
		
		// Bottom of Screen
		logText = new JTextArea("");
		logText.setLineWrap (true);
		logText.setEditable (false);
		logText.setWrapStyleWord (true);
		logText.setVisible (true);
    
    // Finish up the Logging Pane
		gb.startLayout (logTab, 3, 6);
		gb.setByRows (false);
		gb.setAllInsets (0);
		gb.setDefaultRowWeight (0.0);
		
		// Column 1
		gb.add (logOutputLabel);
    gb.add (logOutputNoneButton);
    gb.add (logOutputWindowButton);
		gb.add (logOutputTextButton);
    gb.add (logOutputDiskButton);
    
    // Column 2
    gb.nextColumn();
    gb.add (logThresholdLabel);
    gb.add (logThresholdNormalButton);
    gb.add (logThresholdMinorButton);
    gb.add (logThresholdMediumButton);
    gb.add (logThresholdMajorButton);
    
    // Column 3
    gb.nextColumn();
    gb.add (logAllDataLabel);
    gb.add (logAllDataCkBox);
    
    // Bottom of Panel
		logTextScrollPane = new JScrollPane(logText, 
		  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logTextScrollPane.setVisible(true);
		
    gb.setColumn (0);
    gb.setRow (5);
    gb.setWidth (3);
    gb.setRowWeight (1.0);
    gb.add (logTextScrollPane);

    tabs.addTab("Logging",logTab);
		
    if (mainClass) {
      logJuggler.setTextArea (logText);
      logJuggler.setLog (LogJuggler.LOG_TEXT_STRING);
      logout = logJuggler.getLog();
    }

	} // end addLoggingTab method
  
  public void handleOpenApplication () {
    // do nothing
  }
  
  public void handleOpenApplication (File inFile) {
    handleOpenFile (inFile);
  }
  
  /**
     Standard way to respond to an About Menu Item Selection on a Mac.
   */
  public void handleAbout() {
    WindowMenuManager.getShared().locateUpperLeftAndMakeVisible
        (mainFrame, aboutWindow);
  }
  
  /**
    Standard way to respond to a document being passed to this 
    application on a Mac.
   */
  public void handleOpenFile (File inFile) {
    FileName inFileName = new FileName (inFile);
    String inFileNameExt = inFileName.getExt();
    if (inFileNameExt.equals (SCRIPT_EXT)) {
      inScriptFile = inFile;
      inScript = new ScriptFile (inScriptFile, templateLibrary.toString());
      setCurrentDirectoryFromFile (inScriptFile);
      normalizerPath = currentDirectory.getPath();
      playScript();    
      tabs.setSelectedComponent (scriptTab);
    } else
    if (inFileNameExt.equals ("txt")
        || inFileNameExt.equals ("tab")
        || inFileNameExt.equals ("csv")) {
      chosenFile = inFile;
      openFileOrDirectory();
      
      tabs.setSelectedComponent (inputTab);
    }
  }
  
  public void handleOpenFile (PSFile inFile) {
    if (inFile.exists()
        && inFile.canRead()
        && inFile.isFile()) {
      inScriptFile = inFile;
      inScript = new ScriptFile (inScriptFile, templateLibrary.toString());
      setCurrentDirectoryFromFile (inScriptFile);
      normalizerPath = currentDirectory.getPath();
      playScript();
      if (mainClass & scriptTabDisplay) {
        tabs.setSelectedComponent (scriptTab);
      }

    } else {
      JOptionPane.showMessageDialog (tabs, 
        "Recent Script File " + inFile.toString() + " is not available",
        "Script File Error",
        JOptionPane.ERROR_MESSAGE);
    }
  }
  
  public void handleOpenURI (URI inURI) {
    
  }

  public boolean preferencesAvailable() {
    return false;
  }
  
  public void handlePreferences () {
    
  }
  
  public void handlePrintFile (File printFile) {
    
  }

  /**
     Standard way to respond to a Quit Menu Item on a Mac.
   */
  public void handleQuit() {	
    stopScriptRecording();
    if (mainClass) {
      recentScripts.close();
      userPrefs.setPref (TEMPLATE_LIBRARY_KEY, templateLibrary.toString());
      userPrefs.setPref (UserPrefs.LEFT, mainFrame.getX());
      userPrefs.setPref (UserPrefs.TOP, mainFrame.getY());
      userPrefs.setPref (UserPrefs.WIDTH, mainFrame.getWidth());
      userPrefs.setPref (UserPrefs.HEIGHT, mainFrame.getHeight());
      userPrefs.savePrefs();
    }
    if (mainClass) {
      System.exit(0);
    }
  }
  
  /**
     Plays back a script file that has already been recorded.
   */
  private void playScript() {
    log.recordEvent (LogEvent.NORMAL,
        "Playing script " + inScript.toString(),
        false);
    normalizerPath = currentDirectory.getPath();
    inScript.setLog (log);
    try {
      inScript.openForInput();
    } catch (IOException e) {
      if (quietMode) {
        log.recordEvent (LogEvent.MEDIUM, 
          "MSG003 " + inScript.toString() + " could not be opened as a valid Script File",
          true);
      } else {
        JOptionPane.showMessageDialog (tabs, 
          "Script File could not be opened successfully",
          "Script File Error",
          JOptionPane.ERROR_MESSAGE);
      }
    } 
    scriptPlaying = true;
    scriptText.append ("Playing script "
      + inScript.getFileName() + GlobalConstants.LINE_FEED_STRING);
    if (mainClass) {
      recentScripts.reference (inScriptFile);
    }
    while (! inScript.isAtEnd()) {
      try {
        inAction = inScript.nextRecordIn();
      } catch (IOException e) {
        inAction = null;
      } 
      if (inAction != null) {
        scriptText.append ("Playing action " + 
          inAction.toString() + 
          GlobalConstants.LINE_FEED_STRING);
        inActionModule = inAction.getModule();
        inActionAction = inAction.getAction();
        inActionModifier = inAction.getModifier();
        inActionObject = inAction.getObject();
        inActionValue = inAction.getValue();
        try {
          inActionValueAsInt = Integer.parseInt (inActionValue);
          inActionValueValidInt = true;
        } catch (NumberFormatException e) {
          inActionValueAsInt = 0;
          inActionValueValidInt = false;
        }
        if (inActionModule.startsWith("<!--")) {
          log.recordEvent(LogEvent.NORMAL, inActionModule, false);
        }
        else
        if (inActionModule.equals (INPUT_MODULE)) {
          playInputModule();
        } 
        else
        if (inActionModule.equals (SORT_MODULE)) {
          playSortModule();
        } 
        else
        if (inActionModule.equals (COMBINE_MODULE)) {
          playCombineModule();
        } 
        else
        if (inActionModule.equals (FILTER_MODULE)) {
          playFilterModule();
        }
        else
        if (inActionModule.equals (OUTPUT_MODULE)) {
          playOutputModule();
        }
        else
        if (inActionModule.equals (TEMPLATE_MODULE)) {
          playTemplateModule();
        }
        else
        if (inActionModule.equals (CALLBACK_MODULE)) {
          playCallbackModule();
        }
        else {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionModule + " is not a valid Scripting Module",
            true);
        } // end else unrecognized module
      } // end if inAction not null
    } // end while more script commands
    inScript.close();
    scriptPlaying = false;
    scriptText.append ("Playback stopped" + GlobalConstants.LINE_FEED_STRING);
    resetOptions();
    setScriptReplayControls();
  } // end method playScript
  
  /**
   Set the script replay controls to either be disabled if no script is 
   available, or to be enabled if a script is available. 
  */
  private void setScriptReplayControls() {
    if (inScript == null
        || inScriptFile == null) {
      scriptReplayButton.setEnabled (false);
      scriptReplay.setEnabled (false);
      scriptReplayButton.setToolTipText("Replay the last script");
    } else {
      scriptReplayButton.setEnabled (true);
      scriptReplay.setEnabled (true);
      scriptReplayButton.setToolTipText("Replay script " + inScriptFile.getPath());
    }
  }
  
  /**
     Play one recorded action in the Input module.
   */
  private void playInputModule () {
    if (inActionAction.equals (EPUB_IN_ACTION)) {
      epubFolder = new File (inActionValue);
    }
    else
    if (inActionAction.equals (EPUB_OUT_ACTION)) {
      epubFile = new File (inActionValue);
      createEpub();
    }
    else
    if (inActionAction.equals (SET_ACTION)) {
      if (inActionObject.equals (DIR_DEPTH_OBJECT)) {
        if (inActionValueValidInt) {
          if (inActionValueAsInt > 0) {
            dirMaxDepth = inActionValueAsInt;
          } else {
            log.recordEvent (LogEvent.MEDIUM, 
              inActionValue + " is not a valid value for an Open Directory Depth",
              true);
          }
        } else {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue + " is not a valid integer for an Open Directory Depth Value",
            true);
        }
      }
      else
      if (inActionObject.equals (NORMAL_OBJECT)) {
        if (inActionValueValidInt) {
          if (inActionValueAsInt >= 0
              && inActionValueAsInt <= NORMALTYPE_MAX) {
            normalType = inActionValueAsInt;
            setNormalTypeImplications();
          } else {
            log.recordEvent (LogEvent.MEDIUM, 
              inActionValue + " is not a valid value for a Normalization Type Value",
              true);
          }
        } else {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue + " is not a valid integer for a Normalization Type Value",
            true);
        }
      } else {
        log.recordEvent (LogEvent.MEDIUM, 
          inActionObject + " is not a valid Scripting Object for an Open Set Action",
          true);
      }
    } 
    else
    if (inActionAction.equals (OPEN_ACTION)) {
      
      merge = 0;
      if (inActionObject.equals (MERGE_OBJECT)) {
        merge = 1;
        setMergeImplications();
      } else
      if (inActionObject.equals (MERGE_SAME_OBJECT)) {
        merge = 2;
        setMergeImplications();
      }

      inputModuleIndex = 0;
      inputModuleFound = false;
      while (inputModuleIndex < inputModules.size() && (! inputModuleFound)) {
        inputModule = inputModules.get(inputModuleIndex);
        inputModuleFound = inputModule.setInputTypeByModifier(inActionModifier);
        if (! inputModuleFound) {
          inputModuleIndex++;
        }
      }
          
      if (inActionModifier.equals (URL_MODIFIER)) {
        try {
          tabURL = new URL (pageURL, inActionValue);
        } catch (MalformedURLException e) {
          tabURL = null;
        }
        if (tabURL == null) {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue + " is not a valid " + inActionModifier + " for an Open Action",
            true);
        }
        else {
          fileName = inActionValue;
          openURL();
          // openDataName.setText (fileNameToDisplay);
        } // end file existence selector
      } // end if URL modifier
      else
      if (inputModuleFound) {
        chosenFile = new File (inActionValue);
        if (chosenFile == null) {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue + " is not a valid " + inActionModifier + " for an Open Action",
            true);
        }
        else {
          // log.recordEvent (LogEvent.NORMAL,
          //   "PSTextMerge playInputModule openFileOrDirectory",
          //   false);
          // System.out.println ("PSTextMerge playInputModule openFileOrDirectory");
          openFileOrDirectory();
          // openDataName.setText (fileNameToDisplay);
        } // end file existence selector
      } // end file or directory
      else {
        log.recordEvent (LogEvent.MEDIUM, 
          inActionModifier + " is not a valid Scripting Modifier for an Open Action",
          true);
      } // end Action Modifier selector
    } // end valid action
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " is not a valid Scripting Action for the Open Module",
        true);
    } // end Action selector
  } // end playInputModule method
  
  /**
     Play one recorded action in the Sort module.
   */
  private void playSortModule () {
    if (inActionAction.equals (ADD_ACTION)) {
      currentSortDirection = inActionModifier;
      currentSortField = inActionObject;
      sortAdd();
    }
    else
    if (inActionAction.equals (CLEAR_ACTION)) {
      sortClear();
    }
    else
    if ((inActionAction.equals (SET_ACTION))
      && (inActionObject.equals (PARAMS_OBJECT))) {
      sortSetParams ();
    } // end valid actions
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " " + inActionObject +
        " is not a valid Scripting Action for the Sort Module",
        true);
    } // end Action selector
  } // end playSortModule method
  
  /**
     Play one recorded action in the Combine module.
   */
  private void playCombineModule () {
    if (inActionAction.equals (ADD_ACTION)) {
      if (inActionObject.equals (DATA_LOSS_OBJECT)) {
        dataLossTolerance = inActionValueAsInt;
        if (! inActionValueValidInt) {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue +
            " is not a valid Combine Data Loss Tolerance Value",
            true);
        } // end if invalid int
      } // end if data loss value
      else
      if (inActionObject.equals (PRECEDENCE_OBJECT)) {
        precedence = inActionValueAsInt;
        if (! inActionValueValidInt) {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue +
            " is not a valid Combine Precedence Value",
            true);
        } // end if invalid int
      } // end if precedence value
      else
      if (inActionObject.equals (MIN_NO_LOSS_OBJECT)) {
        minNoLoss = inActionValueAsInt;
        if (! inActionValueValidInt) {
          log.recordEvent (LogEvent.MEDIUM, 
            inActionValue +
            " is not a valid Combine Minimum Records with No Loss Value",
            true);
        } // end if invalid int
      } // end if Minimum No Loss value
      else {
        log.recordEvent (LogEvent.MEDIUM, 
          inActionObject +
          " is not a valid Combine Parameter",
          true);
      } // end no known combine object
    } // end if add action
    else
    if ((inActionAction.equals (SET_ACTION))
      && (inActionObject.equals (PARAMS_OBJECT))) {
      combineSet ();
    } // end valid actions
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " " + inActionObject +
        " is not a valid Scripting Action for the Combine Module",
        true);
    } // end Action selector
  } // end playCombineModule method
  
  /**
     Play one recorded action in the Filter module.
   */
  private void playFilterModule () {
    if ((inActionAction.equals (SET_ACTION)) 
      && (inActionObject.equals (AND_OR_OBJECT))) {
      filterAndOr (Boolean.valueOf(inActionValue).booleanValue());
    }
    else
    if (inActionAction.equals (ADD_ACTION)) {
      currentFilterOperand = inActionModifier;
      currentFilterField = inActionObject;
      currentFilterValue = inActionValue;
      filterAdd();
    }
    else
    if (inActionAction.equals (CLEAR_ACTION)) {
      filterClear();
    }
    else
    if ((inActionAction.equals (SET_ACTION))
      && (inActionObject.equals (PARAMS_OBJECT))) {
      filterSetParams ();
    } // end valid actions
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " " + inActionObject +
        " is not a valid Scripting Action for the Filter Module",
        true);
    } // end Action selector
  }
  
  /**
     Play one recorded action in the Output module.
   */
  private void playOutputModule () {
    if (inActionAction.equals (SET_ACTION)
      && inActionObject.equals (USING_DICTIONARY_OBJECT)) {
      usingDictionary = Boolean.valueOf(inActionValue).booleanValue();
      setDictionaryImplications();
    }
    else
    if (inActionAction.equals (OPEN_ACTION)) {
      chosenOutputFile = new File (inActionValue);
      if (chosenOutputFile == null) {
        log.recordEvent (LogEvent.MEDIUM, 
          inActionValue + " is not a valid file name for an Output Open Action",
          true);
      }
      else {
        createOutput();
      } // end file existence selector
    } // end valid action
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " is not a valid Scripting Action for the Output Module",
        true);
    } // end Action selector
  } // end playOutputModule method
  
  /**
     Play one recorded action in the Template module.
   */
  private void playTemplateModule () {
    if (inActionAction.equals (OPEN_ACTION)) {
      templateFile = new File (inActionValue);
      if (templateFile == null) {
        log.recordEvent (LogEvent.MEDIUM, 
          inActionValue + " is not a valid " + inActionModifier 
          + " for a Template Open Action", true);
      }
      else {
        templateOpen();
      } // end file existence selector
    }
    else 
    if (inActionAction.equals (GENERATE_ACTION)) {
      checkTemplateRepeat();
      templateGenerate();
    } // end valid action
    else {
      log.recordEvent (LogEvent.MEDIUM, 
        inActionAction + " is not a valid Scripting Action for the Template Module",
        true);
    } // end Action selector
  } // end playTemplateModule method

  private void playCallbackModule() {
    if (scriptExecutor != null) {
      scriptExecutor.scriptCallback(inActionAction);
    }
  }
  
  /**
    Reset all the input options that might have been modified by a script
    that was played.
   */
  private void resetOptions() {
    
    initInputModules();
    
    usingDictionary = false;
    setDictionaryImplications();
    
    merge = 0;
    setMergeImplications();
    
    dirMaxDepth = 0;
    
    normalType = 0;
    setNormalTypeImplications();
    
    if (! quietMode) {
      inputTypeBox.setSelectedIndex (0);
      inputDictionaryCkBox.setSelected (false);
      inputMergeNoButton.setSelected (true);
      inputDirMaxDepthValue.setText (String.valueOf(dirMaxDepth));
      if (normalization) {
        inputNormalBox.setSelectedIndex (0);
      }
    }
  }
  
  /**
     Sets other values related to the merge option.
   */
  private void setMergeImplications () {
    if ((merge > 0) && (! fileAvailable)) {
      merge = 0;
    }
    if (merge == 1) {
      mergeValue = "Yes";
      inputObject = MERGE_OBJECT;
    }
    else 
    if (merge == 2) {
      mergeValue = "Same";
      inputObject = MERGE_SAME_OBJECT;
    } else {
      mergeValue = "No";
      inputObject = NO_OBJECT;
    }
  }
  
  /**
     Sets appropriate input data normalization values.
   */
  private void setNormalTypeImplications() {
    if (normalType == 0) {
      normalTypeValue = "No Normalization";
    } 
    else 
    if (normalType == 1) {
      normalTypeValue = "Boeing Docs";
    }
  }
  
  /**
     Open the tab-delimited data file as a URL on the Web.
   */
  private void openURL () {
    recordScriptAction (INPUT_MODULE, SET_ACTION,
        NO_MODIFIER, NORMAL_OBJECT, String.valueOf (normalType));
    if (merge == 0) {
      dataDict = new DataDictionary();
      dataDict.setLog (log);
    }
    tabName = tabURL.toString();
    fileNameToDisplay = fileName;
    dataSource = new TabDelimFile (tabURL);
    dataSource.setDebug (debug);
    openData();
    recordScriptAction (INPUT_MODULE, OPEN_ACTION, URL_MODIFIER,
      inputObject, tabURL.toString());
  }
  
  /** 
     Decides whether to open the data source as a file or as a directory.
   */
  private void openFileOrDirectory() {
    recordScriptAction (INPUT_MODULE, SET_ACTION,
        NO_MODIFIER, NORMAL_OBJECT, String.valueOf (normalType));
    log.recordEvent (LogEvent.NORMAL,
        "Rows before open: "
            + String.valueOf(completeDataSet.getNumberOfRecords()),
        false);
    if (merge == 0) {
      dataDict = new DataDictionary();
      dataDict.setLog (log);
    }
    
    FileName chosenFileName = new FileName (chosenFile);
    
    if (chosenFileName.getExt().trim().equals (inXML.getPreferredExtension())
        && inXML.getInputType() < 1 && inTunes.getInputType() < 1) {
      inXML.setInputType(2);
      inputModuleFound = true;
      inputModule = inXML;
    }
    else
    if (chosenFileName.getExt().trim().equals (inExcel.getPreferredExtension())
        && inExcel.getInputType() == 0) {
      inExcel.setInputType(1);
      inputModuleFound = true;
      inputModule = inExcel;
    }
    /*
    else
    if (chosenFileName.getExt().trim().equals (inYAML.getPreferredExtension())
        && inYAML.getInputType() == 0) {
      inYAML.setInputType(1);
      inputModuleFound = true;
      inputModule = inYAML;
    } */
    
    if (! inputModuleFound) {
      inTDF.setInputType(1);
      inputModuleFound = true;
      inputModule = inTDF;
    }
    
    if (inputModuleFound) {
      fileNameToDisplay = chosenFile.getName();
      tabName = chosenFile.getAbsolutePath();
      if (chosenFile.isDirectory()) {
        setCurrentDirectoryFromDir (chosenFile);
        TextMergeDirectoryReader dirReader 
            = new TextMergeDirectoryReader (chosenFile);
        dirReader.setInputModule(inputModule);
        dirReader.setMaxDepth(dirMaxDepth);
        recordScriptAction (INPUT_MODULE, SET_ACTION,
          NO_MODIFIER, DIR_DEPTH_OBJECT, String.valueOf (dirMaxDepth));
        dataSource = dirReader;
      } else {
        setCurrentDirectoryFromFile (chosenFile);
        normalizerPath = currentDirectory.getPath();
        openDict();
        dataSource = inputModule.getDataSource(chosenFile);
      }
      dataSource.setDebug (debug);
      openData();
      recordScriptAction (INPUT_MODULE, OPEN_ACTION, 
          inputModule.getInputTypeModifier(), 
          inputObject, 
          chosenFile.getAbsolutePath());
    }
    
    log.recordEvent (LogEvent.NORMAL,
        "Rows loaded:      "
            + String.valueOf(completeDataSet.getRecordsLoaded()),
        false);
    log.recordEvent (LogEvent.NORMAL,
        "Rows after open:  "
            + String.valueOf(completeDataSet.getNumberOfRecords()),
        false);
  } // end openFileOrDirectory method
  
  /**
     Open dictionary file, if requested.
   */
  private void openDict () {
    if (usingDictionary) {
      tabFileName = new FileName (tabName);
      dictFile = 
        new TabDelimFile (currentDirectory,
          tabFileName.replaceExt(DICTIONARY_EXT));
      try {
        dataDict.load (dictFile);
      } catch (IOException e) {
        log.recordEvent (LogEvent.MEDIUM, 
            "Problem Reading Input Dictionary",
            false);
      } // end of catch
    } // end if using dictionary
  }
  
  /**
     Opens the input data source (whether a file or a directory).
   */
  private void openData () { 

    dataSource.setLog (log);
    DataSource original = dataSource;
    if (normalType > 0) {
      if (normalType == 1) {
        try {
          BoeingDocsNormalizer docs = new BoeingDocsNormalizer (original);
          docs.setDataParent (normalizerPath);
          // System.out.println ("Normalizer path = " + normalizerPath);
          dataSource = docs;
          log.recordEvent (LogEvent.NORMAL, 
            "BoeingDocsNormalizer successfully constructed",
            false);
        } catch (IOException e) {
          log.recordEvent (LogEvent.MAJOR, 
            "I/O Error in Data Normalization routine",
            false);
        }
      } // end if Boeing docs normalizer
      dataSource.setLog (log);
    } //end if noralization type specified
      
    try {
      if (merge == 1) {
        completeDataSet.merge (dataSource);
      }
      else
      if (merge == 2) {
        completeDataSet.mergeSame (dataSource);
      }
      else {
        completeDataSet = new DataSet (dataDict, dataSource, log);
      }
      recDef = completeDataSet.getRecDef();
      dataDict.setLog (log);

      if (mainClass 
          && (! RegisterWindow.getShared().isRegistered())
          && (completeDataSet.getNumberOfRecords() > DEMO_MAX_RECORDS)) {
        handleRegistrationLimitation();
        log.recordEvent(LogEvent.MEDIUM, 
            "List truncated due to demo limitations", false);
        while (completeDataSet.getNumberOfRecords() > DEMO_MAX_RECORDS) {
          completeDataSet.removeRecord(completeDataSet.getNumberOfRecords() - 1);
        }
      } // end if over demo limit

      initDataSets();
      fileAvailable = true;
      if (openOutputDataButton != null) {
        openOutputDataButton.setEnabled (true);
        fileSave.setEnabled (true);
      }
      log.recordEvent (LogEvent.NORMAL, 
          "Data Source named "
              + fileNameToDisplay
              + " was opened successfully",
          false);
    } catch (IOException e) {
      if (quietMode) {
        log.recordEvent (LogEvent.MEDIUM, 
            "Data Source named "
                + fileNameToDisplay
                + " could not be opened successfully" 
                + "\n       Data Source = "
                + original.toString()
                + "\n       I/O Error = "
                + e.toString(),
          true);
      } else {
        log.recordEvent (LogEvent.MEDIUM, 
            "Data Source named "
                + fileNameToDisplay
                + " could not be opened successfully"
                + "\n       Data Source = "
                + original.toString()
                + "\n       I/O Error = "
                + e.toString(),
          true);
        if (mainClass) {
          JOptionPane.showMessageDialog (tabs, 
              "Data Source named "
                  + fileNameToDisplay
                  + " could not be opened successfully",
              "Data Source Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
      openEmpty();
    } // end try block
    
    // Set appropriate value for Generate Button
    if (generateOutputButton != null) {
      if (templateFileOK && fileAvailable && templateCreated) {
        generateOutputButton.setEnabled (true);
        templateGenerate.setEnabled (true);
      } else {
        generateOutputButton.setEnabled (false);
        templateGenerate.setEnabled (false);
      }
    }
  } // openData method
  
  /**
     Open the tab-delimited data file as an empty data set.
   */
  private void openEmpty () {
    fileNameToDisplay = "No Input File";
    tabName = "";
    dataDict = new DataDictionary();
    dataDict.setLog (log);
    recDef = new RecordDefinition(dataDict);
    completeDataSet = new DataSet (recDef);
    completeDataSet.setLog (log);
    initDataSets();
    fileAvailable = false;
    if (openOutputDataButton != null) {
      openOutputDataButton.setEnabled (false);
      fileSave.setEnabled (false);
    }
  }
  
  private void sortAdd() {
    sortSpec.addField (currentSortField, currentSortDirection);
    sortText.append (currentSortField + " " + currentSortDirection
      + GlobalConstants.LINE_FEED_STRING);
    recordScriptAction (SORT_MODULE, ADD_ACTION, 
      currentSortDirection,
      currentSortField, NO_VALUE);
  }
  
  private void sortClear() {
    initSortSpec();
    recordScriptAction (SORT_MODULE, CLEAR_ACTION, 
      NO_MODIFIER, NO_OBJECT, NO_VALUE);
  }
  
  private void sortSetParams() {
    sortDataSet ();
    dataTable.fireTableDataChanged();
    sortText.append ("The sort parameters listed above have been set." 
      + GlobalConstants.LINE_FEED_STRING);
    recordScriptAction (SORT_MODULE, SET_ACTION, 
      NO_MODIFIER, PARAMS_OBJECT, NO_VALUE);
  }
  
  private void combineSet() {
    String msg = "";
    
    if (sorted) {
      if (dataLossTolerance == 0) {
        msg = "No data loss tolerated";
      }
      else
      if (dataLossTolerance == 1) {
        msg = "One field may override another";
      }
      else {
        msg = "Some fields may be combined";
      }
      sortText.append (msg + GlobalConstants.LINE_FEED_STRING);
      recordScriptAction (COMBINE_MODULE, ADD_ACTION, NO_MODIFIER,
        DATA_LOSS_OBJECT, String.valueOf (dataLossTolerance));
      
      if (dataLossTolerance > 0) {
        if (precedence > 0) {
          msg = "Later fields override earlier ones";
        }
        else
        if (precedence < 0) {
          msg = "Earlier fields override later ones";
        }
        else {
          msg = "No precedence established";
        }
        sortText.append (msg + GlobalConstants.LINE_FEED_STRING);
      }
      recordScriptAction (COMBINE_MODULE, ADD_ACTION, NO_MODIFIER,
        PRECEDENCE_OBJECT, String.valueOf (precedence));
      
      if (dataLossTolerance > 0) {
        sortText.append ("At least "
            + String.valueOf (minNoLoss)
            + " fields must suffer no data loss"
            + GlobalConstants.LINE_FEED_STRING);
      }
      recordScriptAction (COMBINE_MODULE, ADD_ACTION, NO_MODIFIER,
        MIN_NO_LOSS_OBJECT, String.valueOf (minNoLoss));
      
      combineDataSet ();
      dataTable.fireTableDataChanged();
      
      sortText.append (String.valueOf (totalCombinations) 
        + " records combined." 
        + GlobalConstants.LINE_FEED_STRING);
      recordScriptAction (COMBINE_MODULE, SET_ACTION, 
        NO_MODIFIER, PARAMS_OBJECT, NO_VALUE);
    } // end if sorted
  }
  
  private void filterAndOr (boolean currentAndLogic) {
    this.currentAndLogic = currentAndLogic;
    recordScriptAction (FILTER_MODULE, SET_ACTION, 
      NO_MODIFIER, AND_OR_OBJECT, String.valueOf(currentAndLogic));
  }
  
  private void filterAdd () {
    fieldFilter = new FieldFilter (recDef, currentFilterField,
      currentFilterOperand, currentFilterValue);
    compoundFilter.addFilter (fieldFilter);
    filterText.append (currentFilterField + " " + currentFilterOperand
      + " " + currentFilterValue + GlobalConstants.LINE_FEED_STRING);
    recordScriptAction (FILTER_MODULE, ADD_ACTION, 
      currentFilterOperand,
      currentFilterField, currentFilterValue);
  }
  
  private void filterClear() {
    initCompoundFilter();
    recordScriptAction (FILTER_MODULE, CLEAR_ACTION, 
      NO_MODIFIER, NO_OBJECT, NO_VALUE);
  }
  
  private void filterSetParams() {
    completeDataSet.setInputFilter (compoundFilter);
    filterDataSet();
    dataTable.setDataSet (filteredDataSet);
    dataTable.fireTableDataChanged();
    filterText.append ("The filter parameters listed above have been set." 
      + GlobalConstants.LINE_FEED_STRING);
    recordScriptAction (FILTER_MODULE, SET_ACTION, 
      NO_MODIFIER, PARAMS_OBJECT, NO_VALUE);
	}
	
  /**
     Toggles the setting to use a data dictionary file
     or not.
   */
  private void toggleUsingDictionary () {
    usingDictionary = (! usingDictionary);
    setDictionaryImplications();
  }
  
  /**
     Depending on we are using a data dictionary, sets other
     appropriate values.
   */
  private void setDictionaryImplications() {
    if (usingDictionary) {
      usingDictionaryValue = "Yes";
    } else {
      usingDictionaryValue = "No";
    }
    recordScriptAction 
     (OUTPUT_MODULE, 
      SET_ACTION, 
      NO_MODIFIER, 
      USING_DICTIONARY_OBJECT, 
      String.valueOf(usingDictionary));
  }
  
  /**
     Create output file of tab-delimited records.
   */
  private void createOutput() {
    setCurrentDirectoryFromFile (chosenOutputFile);
    tabFileOutput = new TabDelimFile (chosenOutputFile);
    tabFileOutput.setLog (log);
    tabFileOutput.setDataLogging (false);
    boolean outputOK = true;
    try {
      tabFileOutput.openForOutput (recDef);
    } catch (IOException e) {
      outputOK = false;
      log.recordEvent (LogEvent.MEDIUM, 
        "Problem opening Output File",
        false);
    }
    if (outputOK) {
      filteredDataSet.openForInput();
      DataRecord inRec;
      do {
        inRec = filteredDataSet.nextRecordIn ();
        if (inRec != null) {
          try {
            tabFileOutput.nextRecordOut (inRec);
          } catch (IOException e) {
            log.recordEvent (LogEvent.MEDIUM, 
              "Problem writing to Output File",
              true);
          }
        }
      } while (filteredDataSet.hasMoreRecords());
      filteredDataSet.close();
      try {
        tabFileOutput.close();
      } catch (IOException e) {
      }
      tabNameOutput = chosenOutputFile.getName();
      openOutputDataName.setText (tabNameOutput);
      if (usingDictionary) {
        tabFileName = 
          new FileName (chosenOutputFile.getAbsolutePath());
        dictFile = 
          new TabDelimFile (currentDirectory,
            tabFileName.replaceExt(DICTIONARY_EXT));
        dictFile.setLog (log);
        try {
          dataDict.store (dictFile);
        } catch (IOException e) {
          log.recordEvent (LogEvent.MEDIUM, 
              "Problem writing Output Dictionary",
              true);
        }
      } 
      recordScriptAction (OUTPUT_MODULE, OPEN_ACTION, 
        NO_MODIFIER,
        NO_OBJECT, chosenOutputFile.getAbsolutePath());
    }
  }
	
  /**
     Opens the template file for input.
   */
  private void templateOpen() {
    setCurrentDirectoryFromFile (templateFile);
    if (! templateCreated) {
      createNewTemplate();
    }
    templateFileReady = true;
    templateFileName = templateFile.getName();
    openTemplateName.setText (templateFileName);
    templateFileOK = template.openTemplate (templateFile);
    if (templateFileOK) {
      recordScriptAction (TEMPLATE_MODULE, OPEN_ACTION, 
        inTDF.getInputTypeModifier(1), NO_OBJECT, templateFile.getAbsolutePath());
    } else {
      log.recordEvent (LogEvent.MEDIUM, 
        templateFileName + " could not be opened as a valid Template File",
        true);
    }
    
    // Set appropriate value for Generate Button
    if (generateOutputButton != null) {
      if (templateFileOK && fileAvailable && templateCreated) {
        generateOutputButton.setEnabled (true);
        templateGenerate.setEnabled (true);
      } else {
        generateOutputButton.setEnabled (false);
        templateGenerate.setEnabled (false);
      }
    }
  } // end method templateOpen()
  
  /**
     Generates the output file specified in the template.
   */
  private void templateGenerate() {
    if (templateFileOK && fileAvailable && templateCreated) {
      template.openData (filteredDataSet, fileNameToDisplay);
      try {
        generateOutputOK = template.generateOutput();
      } catch (IOException e) {
        generateOutputOK = false;
      }
      lastTemplateFile = templateFile;
      lastTabNameOutput = tabName;
      templateCreated = false;
      templateFileReady = false;
      if (generateOutputOK) {
        outputFileName = template.getTextFileOutName();
        if (outputFileName != null) {
          FileName outputFN = new FileName (outputFileName);
          generateOutputName.setText (outputFN.getFileName());
          generateOutputName.setToolTipText (outputFileName);
        }
        recordScriptAction (TEMPLATE_MODULE, GENERATE_ACTION, 
          NO_MODIFIER, NO_OBJECT, NO_VALUE);
      } else {
        log.recordEvent (LogEvent.MEDIUM, 
          "Error occurred while generating output file from template "
          + templateFileName,
          true);
      }
    } else {
      log.recordEvent (LogEvent.MEDIUM, 
        "One or Both Input files (Template and/or Data) Not Ready",
        true);
    }
  } // end method templateGenerate
  
  /**
     Checks to see if a template file is to be reused.
   */
  private void checkTemplateRepeat () {
    if ((lastTemplateFile != null)
      && (! templateFileReady)) {
      templateFile = lastTemplateFile;
      templateOpen ();
    }
  } // end method checkTemplateRepeat
  
  /**
     Creates a new template object, to perform a new merge operation.
   */
  private void createNewTemplate () {
    template = new Template (log);
    templateCreated = true;
    outputFileName = "";
    generateOutputName.setText (outputFileName);
  }
  
  private void initDataSets () {
    initSortSpec();
    initCompoundFilter();
    filterDataSet();
    dataTable = new DataTable (filteredDataSet);
    numberOfFields = recDef.getNumberOfFields();
    if (tabTableBuilt) {
      tabTable.setModel (dataTable);
      tabNameLabel.setText (fileNameToDisplay);
      setColumnWidths();
    }
    if (sortTabBuilt) {
      loadSortFields();
    }
    if (filterTabBuilt) {
      loadFilterFields();
    }
  }
    
  private void initSortSpec () {
    sortSpec = new SequenceSpec (recDef);
    sortText.setText ("");
    sorted = false;
  }
  
  private void initCompoundFilter () {
    compoundFilter = new CompoundFilter (recDef, currentAndLogic);
    completeDataSet.setInputFilter (compoundFilter);
    filterText.setText ("");
  }
  
  private void filterDataSet () {
    try {
      filteredDataSet = new DataSet (completeDataSet);
    } catch (IOException e) {
    }
    sortDataSet();
  }
  
  /**
     Set preferred widths for table columns.
   */
  private void setColumnWidths() {
    TableColumn tc = null;
    int avg = 0;
    int max = 0;
    int pwc = 0;
    int pw = 0;
    for (int i = 0; i < numberOfFields; i++) { 
      avg = recDef.getAverageLength (i);
      max = recDef.getMaximumLength (i);
      pwc = avg * 3 / 2;
      if (pwc > max) {
        pwc = max;
      }
      if ((max - pwc) <= 2) {
        pwc = max;
      }
      if (pwc < 5) {
        pwc = 5;
      }
      pw = pwc * 9;
      tc = tabTable.getColumnModel().getColumn(i);
      tc.setPreferredWidth (pw);
    }
  }
  
  /**
     Load potential sort fields into the JComboBox.
   */
  private void loadSortFields() {
		SwingUtils.comboBoxLoad (sortFieldsBox, filteredDataSet.getNames());
		if (filteredDataSet.getNumberOfRecords() > 0
        && sortFieldsBox.getItemCount() > 0) {
		  sortFieldsBox.setSelectedIndex (0);
		  currentSortField = (String)sortFieldsBox.getSelectedItem();
		} else {
		  currentSortField = "";
		}
  }
  
  /** 
     Load potential filter fields into the JComboBox.
   */
  private void loadFilterFields() {
		SwingUtils.comboBoxLoad (filterFieldsBox, completeDataSet.getNames());
		if (completeDataSet.getNumberOfRecords() > 0
        && filterFieldsBox.getItemCount() > 0) {
		  filterFieldsBox.setSelectedIndex (0);
		  currentFilterField = (String)filterFieldsBox.getSelectedItem();
		  loadFilterValues();
		} else {
		  currentFilterField = "";
		  if (filterValueBox.getItemCount() > 0) {
        filterValueBox.removeAllItems();
      }
		}
  }
  
  private void loadFilterValues() {
    if (currentFilterField != null) {
      currentFilterColumn = recDef.getColumnNumber (currentFilterField);
      filterSortSpec= new SequenceSpec (recDef, 
        currentFilterField, SequenceField.ASCENDING);
      completeDataSet.setSequence (filterSortSpec);
      lastFieldValue = new DataField(recDef, currentFilterColumn, " ");     
      completeDataSet.openForInput();
      DataRecord nextRec;
      DataField nextField;
      int lastRecNum;
      if (filterValueBox.getItemCount() > 0) {
        filterValueBox.removeAllItems();
      }
      while (completeDataSet.hasMoreRecords()) {
        nextRec = completeDataSet.nextRecordIn();
        lastRecNum = completeDataSet.getLastRecordNumber();
        nextField = nextRec.getField (recDef, currentFilterColumn);
        if ((lastRecNum == 0) || (! nextField.equals (lastFieldValue))) {
          filterValueBox.addItem (nextField.getData());
          lastFieldValue = nextField;
        } // end if new value
      } // end while more data set records
      if (filterValueBox.getItemCount() > 0) {
        filterValueBox.setSelectedIndex(0);
        currentFilterValue = (String)filterValueBox.getSelectedItem();
      } else {
        currentFilterValue = "";
      }
      completeDataSet.close();
    } // end if currentFilterField not null
  } // end method loadFilterValues
  
  private void sortDataSet () {
		filteredDataSet.setSequence (sortSpec);
    sorted = true;
  }
  
  private void combineDataSet () {
		totalCombinations = filteredDataSet.combine 
        (precedence, dataLossTolerance, minNoLoss);
  }
  
  private void setCurrentDirectoryFromFile (File inFile) {
    currentDirectory = new File (inFile.getParent());
  }
  
  private void setCurrentDirectoryFromDir (File inFile) {
    currentDirectory = inFile;
  }
  
  private void setScriptDirectoryFromFile (File inFile) {
    scriptDirectory = new File(inFile.getParent());
  }
  
  private void setScriptDirectoryFromDir (File inFile) {
    scriptDirectory = inFile;
  }
	
	private void recordScriptAction (String module, String action, String modifier, 
	    String object, String value) {
	  if (scriptRecording) {
  	  outAction = new ScriptAction (module, action, modifier, object, value);
  	  outScript.nextRecordOut (outAction);
  	  scriptText.append (outAction.toString() + GlobalConstants.LINE_FEED_STRING);
	  }
	} // end recordScriptAction method
	
	private void stopScriptRecording() {
    if (scriptRecording) {
      outScript.close();
      scriptRecording = false;
      scriptText.append ("Recording stopped" + GlobalConstants.LINE_FEED_STRING);
      inScript = outScript;
      inScriptFile = outScriptFile;
      setScriptReplayControls();
    } // end if script recording
  } // end stopScriptRecording method
  
  /**
   Can more records/items be added without exceeding the demo limitation?

   @return     True if more can be added, false if we've hit the ceiling.
   */
  public boolean roomForMore() {
    return RegisterWindow.getShared().roomForMore
        (completeDataSet.getNumberOfRecords());
  }
  
  /**
   If the program hasn't been registered, then remind the user upon application
   launch.
   */
  private void checkUnregistered() {
    if (! RegisterWindow.getShared().isRegistered()) {
        unregisteredWindow
            = new UnregisteredWindow(
            "You may continue to use it in demo mode for as long as you like, "
            + "but the program will save no more than 20 URLs "
            + "until it is registered.");
        int w = mainFrame.getWidth();
        int h = mainFrame.getHeight();
        int x = mainFrame.getX();
        int y = mainFrame.getY();
        unregisteredWindow.setLocation(
            x + ((w - unregisteredWindow.getWidth()) / 2),
            y + ((h - unregisteredWindow.getHeight()) / 2));
        WindowMenuManager.getShared().locateCenterAndMakeVisible
            (mainFrame, unregisteredWindow);
    }
  }

  /**
   Help the user purchase a software license for URL Union.
   */
  private void purchase () {
    openURL (UnregisteredWindow.STORE);
  }

  private void register () {
    WindowMenuManager.getShared().locateCenterAndMakeVisible
        (mainFrame, registerWindow);
  }

  /**
   Handle the condition of not storing all user input due to the application
   not being registered.

   @param reg The registration exception generated.
   */
  public void handleRegistrationException (RegistrationException reg) {
    handleRegistrationLimitation();
  }

  /**
   Handle the condition of not saving all user input due to the application
   not being registered.
   */
  private void handleRegistrationLimitation () {
    Trouble.getShared().report("Unregistered copy will load no more than "
        + String.valueOf(DEMO_LIMIT) + " records in Demo mode",
        "Demo Warning");
  }

} // end PSTextMerge class
