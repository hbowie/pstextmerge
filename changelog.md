
## Version 4.50 (2016-08-15)

1. **Added New Input Type for Note Index**

    The Input Type of Note Index can be used to generate an index of notes that use the new Index field.


## Version 4.40 (2015-11-12)

1. **Added New Apostrophe Variable Modifier**

    A plain apostrophe placed in the variable modifiers string will now cause any HTML entities representing an apostrophe or a single quotation mark to be converted back to a normal ASCII/UTF straight single quote/apostrophe character. This can be useful when generating HTML for e-mails, since the HTML entities are sometimes dropped by e-mail clients.


## Version 4.30 (2014-06-09)

1. **Added .mdtoc File Extension for Include Conversion**

    Specifying an output file extension of .mdtoc will cause an included Markdown file to have a table of contents inserted into the document immediately following a heading titled &quot;Table of Contents&quot;. The table of contents will be based on the headings used in the document.

2. **Added OUTER and OUTERLOOP Commands**

    Added OUTER and OUTERLOOP commands, to allow a template to perform both an outer loop and an inner loop over the data. This can be useful, for example, to build a navigation menu for each of several items, with additional detail available when the focus is on a particular item.

3. **Added parentfolder variable**

    Added a parentfolder variable, to allow the name of the parent folder (without its path info) to be inserted by a template.

4. **Added relative global variable**

    Added the ability to set a folder as the root for a web site, and then use the relative global variable to create a path to the root from any location within the site.


## Version 4.20 (2013-06-09)

1. **Back slash at end of line will result in two spaces at line end**

    The software normally drops trailing spaces at the end of a line, but a line ending with a space and a backslash will now result in two spaces appearing at the end of the output line, which will result in a line break being generated if converting Markdown to HTML.

2. **Fixed Merge Bug**

    Merge options on the Input tab were being ignored, resulting in the merged data replacing the prior data, instead of being added to it. This has now been corrected.

3. **Merge without Data**

    Template generation can now be performed without any input data, so long as there is not a nextrec command in the template. The purpose of this would be to include output files from other operations into a single output file.


## Version 4.10 (2013-02-28)

1. **Refactored code for greater reuse**

    Refactored code to allow chunks of PSTextMerge to be used by other apps as well.

2. **Converted to Open Source**

    PSTextMerge has now been released under the Apache License Version 2.0.

3. **Added Else Command**

    Added an else command, which can be used following an if to invoke the opposite condition.

4. **Refined Template Variable Names**

    The variable  'templatefilename' now only returns the file name without the enclosing folder, and the new variable 'templateparent' now returns the enclosing folder. These are now consistent with the operation of 'datafilename' and 'dataparent'.


## Version 4.00 (2012-07-27)

1. **Added Club Planner Input Format**

    Added Club Planner and Club Notes input formats. This is a new input format designed for planning events to be held by a club.

2. **Added Autoplay Feature**

    Added Autoplay button on the Script tab that allows the user to select a script to be automatically played each time the program launches.

3. **Added Easy Play Feature**

    Added an Easy Play button to the Script tab to allow the user to select a folder containing multiple script files. After selecting such a folder, a new Easy tab will be added to the far left of the main window, with a button for each script in the folder. The user then need only click on the button to play the indicated script.

4. **Converted from Freeware back to Shareware**

    Added demo restrictions and ability to purchase a license from the PowerSurgePub.com store.

5. **Updated to Run on Recent Mac OS X Versions**

    Updated to run on recent versions of Mac OS X that no longer support the PowerPC architecture.

6. **Added Ability to Read iTunes Library XML File**

    Added a new Input Format for iTunes Library XML Files, which allows summary information about albums to be extracted.


## Version 3.5 (2007-12-09)

1. **Corrected a Bookmarks Lists Problem**

    Corrected a bug that occurred when reading a bookmarks file using lists to indicate categories. When the list depth was reduced, previous list items at the greater depth were being mistakenly retained for current bookmarks. This has now been fixed.


## Version 3.4 (2007-05-18)

1. **Converted from Shareware to Freeware**

    PSTextMerge is now completely free.

2. **Added Ability to Truncate a Field on the Left rather than the Right**

    The variable modifier of &quot;R&quot; may now be used, in combination with a length modifier, to keep characters on the right (truncating on the left), rather than the default behavior of keeping characters on the left.


## Version 3.3 (2005-11-27)

1. **Made Directory Last Mod Date Compliant with ISO 8601**

    Modified Last Mod Date format, generated when reading a file directory, to use hyphens instead of slashes between the month, day and year fields, to be compliant with ISO 8601. This also allows this date to be used when generating Google Sitemaps.

2. **Several New Text Logical Operands Added**

    Added several new logical operands to check if a string is not included, if one string starts with another or does not, and if a string ends with another or does not.

3. **Output Directories Now Created**

    Previously, all parent output directories had to be created before PSTextMerge would create an output file from a template. All necessary parent directories will now be created automatically, assuming sufficient permissions.


## Version 3.2 (2005-08-14)

1. **Add Informational Messages to Log at Startup**

    Added some additional informational messages to the log at startup. Now displaying amount of available memory and information about the Java Virtual Machine on which the application is running.

2. **Add Ability to Detect Start and End of Lists**

    Added IFNEWLIST and IFENDLIST commands, that indicate the beginning and ending of a list of records, all having the same group identifier at the specified level. So whereas IFNEWGROUP indicates a new value in the group identifier field for a particular level, IFNEWLIST indicates the start of a new sequence of values. In general, IFNEWLIST and IFENDLIST can be used to insert begin list and end list tags when generating HTML.

3. **Add Ability to Output Formatted HTML Table Cells**

    Added a new Data Source of &quot;Excel Table&quot;, which generates only one column for each row in the table, called &quot;Table Row&quot;. This column contains complete formatted HTML for every column in the row, including comparable text formatting, hyperlinks, and cell dimensions.

4. **Corrected a Bug in Play Recent Script Menu Item**

    Corrected a bug that prevented the length of the Play Recent Script menu item from ever showing more than four items. The list should now show up to 10 items, as previously intended.

5. **Simplified Syntax for Links in Outline Format**

    When processing a Data Source in PSPub Outline format, hyperlinks can now be added simply by specifying a URL (beginning with the usual http: prefix) on a line by itself following the line to be hyperlinked. An outline heading may now have such a hyperlink applied, as well.

6. **Add INCLUDE Command**

    Added an INCLUDE Command to Template processing. This command allows you to include text from another file into the output stream being generated by the template. The included text is not processed in any way, but is simply copied to the output file(s) being generated. This does allow output from a previous step in a script to be included in the output generated by a later step. If an include file is not found, then it will simply be skipped and processing will continue, with a log message to note the event.

7. **Notify User when Memory is Low**

    The program now generates log messages when it is low on memory.

8. **Hyperlinks Now Available from Excel Spreadsheets**

    When reading an Excel spreadsheet, if an Excel cell has a hyperlink associated with it, that hyperlink can now be accessed by adding &quot;link&quot; to the name of the column.


## Version 3.1 (2005-05-17)

1. **Add Template Library**

    Added a template library. This defaults to a new templates folder in the PSTextMerge application folder. A new button on the Template tab allows you to select another folder to contain your collection of standard templates. Another new button on the Template tab allows you to open a template from the template library, rather than the folder where the input data file, or script file, resides. Recording a script that references a file from the template library will store the folder location as the template library, so that moving the template library will not later break scripts that have been recorded. In combination with the ability to reference the dataparent folder as a variable, and call out the data file name as a base file name (without a file extension), templates may now be written (and are supplied with the PSTextMerge standard distribution), that can be used with any appropriate data file, and will generate output in the data file's location, and with the data file's name (rather than hard-coding a specific output file name in each template file).

2. **Added Global Variable of dataparent**

    Added a new global variable,  &quot;dataparent&quot;, allowing the enclosing folder of the input data file to be used in the output, most usefully as part of an output file name.

3. **New Variable Modifier to Access File Name Base**

    Added a new variable modifier, &quot;b&quot;, to strip the extension from a file name and return only the base part of the name.

4. **Added Outline Format**

    Added a new data input format, called PSPub Outline. This format allows an outline to be easily created within any text editor. Any character may be used consistently within a file as a bullet character, to indicate a heading item. Indention is used to indicate outline levels. The output may then be formatted as HTML, in any format that the user chooses.

5. **Added Play Recent submenu to Scripts Menu**

    Added the ability to remember recent scripts played, and to easily play them again by selecting them from the Play Recent submenu on the Script menu.

6. **Start in User's Home Directory**

    Modified the starting location for the first file open dialog to show the user's home directory, rather than the PSTextMerge folder.


## Version 3.0 (2005-04-09)

1. **Product Renamed from TDF Czar to PSTextMerge**

    Product Renamed from TDF Czar to PSTextMerge. &quot;TDF Czar&quot; has always been a mouthful, and with the change in the latest version to read MS Excel files directly, it was no longer even descriptive. Following the precedent set by the PSRenamer product, I am now calling this product PSTextMerge.

2. **Alternate Delimiters Now Available**

    Added a new delimiter scheme that should allow better compatibility with various HTML editors. The delimiters in the far right column are the new ones. <br><br><table class=shaded border=0 cellspacing=2 cellpadding=4><tr><th class=shaded>Meaning</th><th class=shaded>Original Delimiters</th><th class=shaded>New Delimiters</th></tr><tr><td class=shaded>Start of Command</td><td class=shaded>&lt;&lt;</td><td class=shaded>&lt;?</td></tr><tr><td class=shaded>End of Command</td><td class=shaded>&gt;&gt;</td><td class=shaded>?&gt;</td></tr><tr><td class=shaded>Start of Variable</td><td class=shaded>&lt;&lt;</td><td class=shaded>=$</td></tr><tr><td class=shaded>End of Variable</td><td class=shaded>&gt;&gt;</td><td class=shaded>$=</td></tr><tr><td class=shaded>Start of Variable Modifiers</td><td class=shaded>&amp;</td><td class=shaded>&amp;</td></tr></table>

3. **Commands Can Be Indented**

    PSTextMerge commands may now have white space on the line preceding the start of the actual commands.

4. **Read Excel Files Directly**

    Lists stored in MS Excel style spreadsheets can now be read directly as input, without having to first save the data in a tab-delimited format.

5. **Date formats in templates may now be used for any mm/dd/yy date**

    Date formats in templates may now be used for any mm/dd/yy date. Templates previously offered a very flexible way to format dates, but this formatting could only be used to format today's date. The same date formats may now be used for any date in month/day/year format.

6. **Variable Modifier of X converts for XML**

    A variable modifier of x will now cause a variable to have applicable characters translated to XML entities. This is especially useful when generating an RSS feed.

7. **User Preferences Now Stored per OS Standard**

    User preferences are now stored using the Java API, which places them in the proper location for preferences per the operating system standard. When converting from a prior version (of TDF Czar), the parms.txt file should be copied to the PSTextMerge folder before launching PSTextMerge for the first time.

8. **Word Demarcation**

    The way in which words are delimited within a variable can now be changed. The delimiter can be specified, as well as capitalization rules. All normal word demarcation rules are recognized to parse the variable into words, including punctuation characters, and transitions from lower- to upper-case.

9. **XML Parsing**

    XML is now a valid input type. Field names will be stored in columns 1 - 4, and field values will be stored in column 5.


## Version 2.6 (2003-08-19)

1. **User Guide doesn't seem to launch from Help Menu**

    Improved Help/User Guide functionality to more consistently launch the user guide in your Web browser under Mac OS X.

2. **Add the ability to lookup data in associated table**

    Added fields to the data dictionary that allow a field to be calculated, based on other fields and possibly other files. The first calculation function added is lookup , which allows a value in an associated tab-delimited file to be looked up, based on key values in the primary file. See the description of the Input tab in the Operating Guide for more information.

3. **Somehow try to enforce and/or supply default file**

    Modified program to automatically add the default file extension of '.tcz' when recording a script file, if no extension is specified.

4. **Space in file name causes difficulties**

    Removed space in name of executable in order to prevent problems on some platforms with execution via a batch script.

5. **Program sometimes hangs or quits unexpectedly**

    Modified program to reset input options to initial default values after playback of a script, to ensure stable execution of following actions.

6. **Add More Selective Case Modification**

    Added the letter 'i' as a template variable modifier to indicate that the associated case conversion character ('u' or 'l') should be applied only to the initial character of the variable.

7. **Seems to be a bug when reading a file directory.**

    Corrected a recently introduced bug in the 2.6 beta version that caused the following file or directory name, rather than the current one, to be returned with each record when reading a file directory.

8. **Modified to write log to disk when in quiet mode**

    Modified to write log file to disk when program is invoked in quite mode.

9. **Fixed Lookup Files Bug**

    Corrected a problem that would prevent lookup files from being found in same directory as the script file when the program was being invoked from the command line.

10. **Seem to be problems passing a run-time parameter**

    Modified the program to treat a run-time parameter script file strictly as a file, and not a URL. This cleared up some problems that were occurring when trying to pass the name of a script file that was not located on the path of the program (on a separate volume or server, for example).

11. **Add ability to run from command line**

    Added two command line arguments, to allow PSTextMerge to be run completely from the command line, without any graphical user interface. The first argument is specified as -q and tells the program to run in quiet mode -- that is, without any GUI. The second argument specifies the location and name of a script file to be played, the location being relative to the PSTextMerge program.

12. **Added Data Normalization Ability**

    Added the ability to invoke a data normalization routine, as demonstrated with a special routine for Boeing Mesa IT.

13. **Fixed Length Modifier Bug**

    Corrected a bug that resulted in an unrecoverable error when a length variable modifier was longer than the starting variable. Modified logic so that the variable will be padded with zeros on the left in this situation.

14. **Fixed Global Variable Bug**

    Corrected a bug that prevented a global variable from being set to a non-blank value once it had been initially set to a blank value.

15. **Fixed Global Variable Bug**

    Corrected a bug that caused a global variable to be set to 0 (zero) whenever a String (non-integer) value was assigned to it.

16. **Fixed Group Breaks Bug**

    Corrected a bug that caused new group breaks to be ignored when a higher level break had occurred, but the lower level data happened to be the same.


## Version 2.5 (2003-02-21)

1. **Should be able to open local user guide**

    Modified to try to open local user guide on Mac systems, instead of sending user to the PowerSurgePub Web site (already works this way on Windows).

2. **Can't decrement maximum directory depth on Input**

    Corrected Decrement Directory Depth logic so that it could be set back to 1 after having been incremented (previous logic would not allow it to go below 2).

3. **Change look of Script Play Button**

    Modified Script Tab so that focus shifts to Stop button after recording is begun.

4. **Play Back Script Just Recorded**

    Modified Script Play Again to always play back last script played or recorded (instead of last script played, if there was one, then last script recorded).

5. **Add HTML import ability that uses various heading levels**

    Added a new input type to extract bookmarks from HTML files using Heading tags as categories.

6. **Would be nice to have file references in scripts**

    Modified scripting logic to allow relative path references to refer to files higher in the folder hierarchy, using the ../ convention for each upwards folder level.

7. **Add Ability to Replace Spaces with Underscores**

    Added an underscore character ('_') as a new variable modifier. When present, this will cause any spaces in the variable to be replaced with underscore characters.

8. **Ignore cells with rowspan/colspan > 1**

    When reading HTML tables, modified logic to bypass table cells where rowspan or colspan is greater than 1, on the assumption that this would not be part of a true data table.

9. **Separator variable modifiers always have spaces**

    Omit spaces after forward and backwards slashes, when used as variable modifier separator characters.

10. **When reading multiple levels of a file directory**

    Corrected bug that caused wrong folders (one level too high) to appear in new Folder columns.

11. **Fix Bug in Playback of HTML Table Open**

    Corrected a bug that prevented playback of an action to open an HTML table.


## Version 2.4 (2003-01-04)

1. **Access Individual Words within Filenames**

    For file directory input, file names are now broken into a maximum of five separate words (separated by spaces, puncutation, etc.), which are returned as individual fields (Word1 through Word5).

2. **Make Sort parameters text box scrollable**

    Added scroll bars to sort and filter parameter displays.

3. **Add HTML entity Translation**

    For HTML input, added ability to translate character entities found within HTML text to their equivalent ASCII characters. For now, translation is only provided for characters that are not platform-specific: &nbsp; (non-breaking space), &lt; (less than sign),  &gt; (greater than sign),  &amp; (ampersand) and &quot; (double quotation marks). Entities may be specified using mnemonics or their numeric equivalents.

4. **For file directory opens, read sub-directories**

    Added a maximum explosion depth for file directory opens. Sub-directories will also show up as separate fields in resulting tabular data, as well as in sort key.

5. **Name seems a bit generic as the heading for file**

    For file directory opens, changed the column heading for file names from Name to File Name .

6. **For file directories, last mod date should be formatted for sorting**

    For file directories being input, broke the last modified date and time into two separate fields. The date field is in the form yyyy/MM/dd so that it can be easily sorted. The time field is in the form hh:mm:ss tmz with a military (24-hour) time, again for easy sorting.

7. **Add merge option to supply missing column headings**

    Added a new merge option that uses column headings from last file read, for files that do not have their own embedded column headings.


## Version 2.3 (2002-11-10)

1. **Merge Bug**

    Fixed a bug that was causing problems when processing an alias in a data dictionary.

2. **Application takes up too much screen real estate**

    Made the initial application window size smaller, and made it resizable.

3. **Doesn't obey Mac OS X interface guidelines**

    Made several Mac OS X specific enhancements: <br>Put menu bar at top of screen; <br>enabled About and Quit menu items on Application menu; <br>enabled script files to be played by dropping them on the application or by double-clicking a file with an extension of .tcz.

4. **User interface has several quirks**

    Removed the ability to run the program as an applet, since this seemed fairly useless, wasn't reliable (due to lack of consistent testing), and was causing several user interface challenges.

5. **Add Play Again Button for Scripts**

    Added a <b>Play Again</b> button on the script tab, which will rerun the last script recorded or played without having to re-select the script file.

6. **Add Today as Special Variable**

    Added <b>today</b> as a special variable that will be replaced with today's date, at the time that a template generation is run. Also added an optional date format as a variable modifier, to allow the format of today's date to be specified.

7. **Program has no menus or shortcut keys**

    Added proper menus and accelerator/shortcut keys to perform major program functions without having to use tabs and buttons.

8. **User interface doesn't gray out buttons and menu selections**

    Modified user interface to gray out buttons and menu items when they are not applicable.


## Version 2.2 (2002-10-12)

1. **No way to get data out of an HTML table**

    Added the ability to parse an HTML table as an input data source.

2. **User interface is awkward**

    Improved user interface on Input, Output and Template tabs.

3. **Added list punctuation variable modifier**

    Any punctuation entered as a variable modifier will cause that variable, along with any preceding or succeeding ones so marked, to be treated as elements of a list, with the punctuation character used to separate non-blank elements.


## Version 2.1.1 (2002-09-22)

1. **Bug in testing for equality**

    Corrected bug in checking for equality between two variables.

2. **Bug that prevents Set command from working**

    Corrected bug that prevented Set command from working.

3. **Data dictionary accumulates values when not desired**

    Modified to start with empty data dictionary every time a new input data source is opened, unless a merge has been requested.

4. **Creating templates can be hard**

    Provided example in user guide of using meta-templates to generate templates from a data dictionary.

5. **Hard to debug when results are not as expected**

    Added additional logging to show results of commands.


## Version 2.1 (2002-09-08)

1. **Add Creator and File Type Codes for Output Files on a Mac**

    When running on a Mac, output files will have the SimpleText creator code (ttxt) and the plain text (TEXT) type code assigned, to make these files easier to open using other programs.

2. **Add ability to combine records**

    Sort tab now offers the ability to combine records with identical sort keys. This may be used with the new merge feature, or on a single file to eliminate duplicates.

3. **Add ability to merge two files**

    Input tab now offers the option to merge an input file with the existing data set. Files should have some overlapping columns, but may have unique columns as well.


## Version 2 (2002-08-24)

1. **Not getting paid for this great program**

    Program now requires a registration code to process more than 20 data records.


## Version 1.17 (2002-08-14)

1. **Script files aren't portable**

    Modified script files to make file names relative to the location of the script file itself, where file names are within the same folder. This should make script files more portable.

2. **No way to go from HTML to tab-delimited records**

    Added an option to parse HTML into tab-delimited records. The first option available reads bookmark files that use multiple, indented lists to indicate the organization of the bookmarks. This is the format used by URL Manager, for example.


## Version 1.16 (2002-04-17)

1. **Add Group Commands**

    Added three new group commands to allow template lines to be written out before and after control breaks in up to ten different key fields.

2. **Doesn't work natively under Mac OS X**

    Now available for execution under Mac OS X.

3. **Logging output sometimes difficult to find**

    Added a logging option that sends log data to the text area on the Log tab.

4. **Text sometimes overflows available area on Script**

    Added scroll bars to output text areas on Log and Script tabs.


## Version 1.15 (2001-03-02)

1. **Create program icons for distribution**

    Created program icons.

2. **Last row in input data gets skipped**

    Corrected bug that caused last row of input data to be skipped when using a template to generate an output file.

3. **Script input file is not using same log file**

    Modified Script playback file to use same log file settings as the rest of the program.

4. **Fix IF Results Bug**

    Corrected bug that caused results of IF comparisons to be reversed.

5. **SET commands not working properly**

    Corrected program logic so that global variable values would not be confused with data record values.


## Version 1.14 (2001-02-16)

1. **Exception occurs when running as applet**

    Corrected an exception that occurred when running as an applet with an initial scriptfile.

2. **Script output file is not using same log file**

    Modified so that the output script dictionary would use the same log and log settings as the rest of the program.


## Version 1.12 (2001-02-08)

1. **Exception occurs when reading dictionary file**

    Added check in dictionary read routine to skip blank lines in the input file.

2. **Add some way to quit the program**

    Added ability to quit the program by closing the window.

3. **Change file:\\http to http at beginning of hyperlinks**

    Eliminated occurrences of  file:\\http at the beginning of hyperlink fields.

4. **Code for condition when URL begins with slashes**

    Added logic to handle condition when hyperlink field begins with slashes.

5. **Ensure that the output dictionary uses correct log settings**

    Modified so that the output dictionary would use the same log and log settings as the rest of the program.

6. **Trim spaces from front of hyperlinks**

    Added logic to trim spaces from the front of hyperlink fields.


## Version 1.11 (2001-02-05)

1. **ArrayIndexOutOfBoundsException**

    Corrected an ArrayIndexOutOfBoundsException at com.powersurgepub.psdata.DataRecord by being more tolerant of irregularities in the Input data source.

2. **Prevent fatal errors when data records misconfigured**

    Prevented fatal errors when one or more data records did not conform to expected configuration (blank lines, missing fields, extra fields, etc.).

3. **Dictionary output file contains extra records**

    Eliminated penultimate dictionary record containing no field name, but a non-blank data format. This was being created when a Dictionary was being saved as part of the Output function.

4. **Dictionary output file does not contain input data format rules**

    Modified to prevent extra dictionaries from being accidentally created internally. This would result in custom formats being lost when going from Input to Output functions.

5. **Make sure dictionary file gets saved in same directory**

    Modified to ensure that the dictionary file gets saved in the same directory as the output file.

6. **Maintain last used directory**

    Modified to remember last used directory and start further file activity (opens, saves) there, rather than home directory.

7. **Template Generation not creating a Script Action**

    Added code to record a Script action when pressing the Template Generate button.


## Version 1.10 (2001-02-26)

1. **Open dialog box for Script playback shows up in wrong location**

    That's where it should be! Not a problem.

