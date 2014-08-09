<!-- Generated using template product-user-guide-template.mdtoc -->
<!-- Generated using template product-user-guide-template.md -->
<h1 id="pstextmerge-user-guide">PSTextMerge User Guide</h1>


<h2 id="table-of-contents">Table of Contents</h2>

<div id="toc">
  <ul>
    <li>
      <a href="#introduction">Introduction</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li>
          <a href="#system-requirements">System Requirements</a>
        </li>
        <li>
          <a href="#rights">Rights</a>
        </li>
        <li>
          <a href="#installation">Installation</a>
        </li>
        <li>
          <a href="#command-line-parameters">Command Line Parameters</a>
        </li>
      </ul>

    </li>
    <li>
      <a href="#data-fields">Data Fields</a>
    </li>
    <li>
      <a href="#file-operations">File Operations</a>
    </li>
    <li>
      <a href="#user-interface">User Interface</a>
    </li>
    <li>
      <a href="#tips-tricks-and-special-functions">Tips, Tricks and Special Functions</a>
      <ul>
        <li>
          <a href="#textmerge-input">TextMerge Input</a>
        </li>
        <li>
          <a href="#textmerge-view">TextMerge View</a>
        </li>
        <li>
          <a href="#textmerge-sort">TextMerge Sort</a>
        </li>
        <li>
          <a href="#textmerge-filter">TextMerge Filter</a>
        </li>
        <li>
          <a href="#textmerge-output">TextMerge Output</a>
        </li>
        <li>
          <a href="#textmerge-template">TextMerge Template</a>
        </li>
        <li>
          <a href="#textmerge-script">TextMerge Script</a>
        </li>
        <li>
          <a href="#textmerge-logging">TextMerge Logging</a>
        </li>
      </ul>

    </li>
    <li>
      <a href="#help">Help</a>
    </li>
    <li>
      <a href="#file-formats">File Formats</a>
      <ul>
        <li>
          <a href="#template-files">Template Files</a>
        </li>
        <li>
          <a href="#script-files">Script Files</a>
        </li>
        <li>
          <a href="#file-directories">File Directories</a>
        </li>
        <li>
          <a href="#markdown-metadata">Markdown Metadata</a>
        </li>
        <li>
          <a href="#pspub-outline">PSPub Outline</a>
        </li>
        <li>
          <a href="#club-planner-files">Club Planner Files</a>
        </li>
      </ul>

    </li>
  </ul>

</div>


<h2 id="introduction">Introduction</h2>


PSTextMerge merges lists of tabular data into text templates to create fully populated text files. The output files can be Web pages, XML files, RSS Feeds or any other varieties of text files. The input data can be obtained from a tab-delimited data file, or directly from a Microsoft Excel (&#8216;.xls&#8217;) spreadsheet, or from a number of other sources. You can record and easily play back recorded scripts, making it easy to re-generate your output text files when your data changes.

The input data can be sorted and filtered before being used to generate output, and these operations can be scripted as well.  

Tab-delimited files can be easily exported from most spreadsheets, databases, address book, and many other programs.

PSTextMerge can also perform other operations with tabular data, extracting and merging data from multiple bookmarks files, address books, and other formats. 

PSTextMerge was formerly known as TDF Czar.






<h2 id="getting-started">Getting Started</h2>


<h3 id="system-requirements">System Requirements</h3>


PSTextMerge is written in Java and can run on any reasonably modern operating system, including Mac OS X, Windows and Linux. PSTextMerge requires a Java Runtime Environment (JRE), also known as a Java Virtual Machine (JVM). The version of this JRE/JVM must be at least 6. Visit [www.java.com](http://www.java.com) to download a recent version for most operating systems. Installation happens a bit differently under Mac OS X, but generally will occur fairly automatically when you try to launch a Java app for the first time.

Because PSTextMerge may be run on multiple platforms, it may look slightly different on different operating systems, and will obey slightly different conventions (using the CMD key on a Mac, vs. an ALT key on a PC, for example).

<h3 id="rights">Rights</h3>


PSTextMerge Copyright 1999 - 2014 by Herb Bowie

PSTextMerge is [open source software](http://opensource.org/osd). Source code is available at [GitHub](http://github.com/hbowie/pstextmerge).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

  [www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

PSTextMerge also incorporates or adapts the following open source software libraries.

* JExcelAPI — Copyright 2002 Andrew Khan, used under the terms of the [GNU General Public License](http://www.gnu.org/licenses/).

* parboiled — Copyright 2009-2011 Mathias Doenitz, used under the terms of the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

* pegdown — Copyright 2009-2011 Mathias Doenitz, used under the terms of the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

* Xerces &#8212; Copyright 1999-2012 The Apache Software Foundation, used under the terms of the [Apache License, Version 2.0][apache].

* Saxon &#8212; Copyright Michael H. Kay, used under the terms of the [Mozilla Public License, Version 1.0][mozilla].


<h3 id="installation">Installation</h3>


Download the latest version from [PowerSurgePub.com](http://www.powersurgepub.com/downloads.html). Decompress the downloaded file. Drag the resulting file or folder into the location where you normally store your applications. Double-click on the jar file (or the application, if you've downloaded the Mac app) to launch.


<h3 id="command-line-parameters">Command Line Parameters</h3>


PSTextMerge has two arguments which allow the program to be run completely from the command line, without any graphical user interface. The first argument is specified as &#8220;-q&#8221; and tells the program to run in &#8220;quiet mode&#8221; &#8212; that is, without any GUI. When this option is specified, the log file will be written to disk, as described in the Logging section.

The second argument specifies the location and name of a script file to be played, the location being relative to the PSTextMerge program.

Together, these two options allow PSTextMerge to be executed from a script or batch file, without any user interaction.

<h2 id="data-fields">Data Fields</h2>


PSTextMerge works with any data that can be represented in columns and rows. Each column has a title. The number of columns, and the titles of the columns, is completely variable.

<h2 id="file-operations">File Operations</h2>


File operations may be accessed via the File menu.

<h2 id="user-interface">User Interface</h2>



Most of PSTextMerge's user interface elements are laid out in a series of tabs that proceed in a natural progression from left to right. Most of the functions performed within these tabs may also be triggered by their corresponding script commands. See the Special Functions section for a description of each tab.


<h2 id="tips-tricks-and-special-functions">Tips, Tricks and Special Functions</h2>


<h3 id="textmerge-input">TextMerge Input</h3>


This function allows PSTextMerge to populate a list to be used in later processing. The following controls are available.

<h4 id="open-input">Open Input</h4>


Clicking on this button will allow you to select the file or directory to be input.  Make sure that all of the following input options are properly set before pressing this button. This function can also be invoked via the File/Open Menu item or with the O shortcut key.

Note that, when a directory (aka folder) is selected, then all eligible files within that directory will be processed, and the resulting list will consist of the concatenation of all the rows of data generated from all of the eligible files.

<h4 id="type-of-data-source">Type of Data Source</h4>


The default input is a tab-delimited text file, which may have been saved or exported from a spreadsheet, database, address book or other tabular data source. But other options are available as well. Select the one you want from the drop-down list.

Following are descriptions of each of the currently available options.

<h5 id="tabbed-data-file">Tabbed Data File</h5>


This is a text file. Each line in the file must use tabs (or commas, if the file extension is '.csv') to separate each field, or column, in the line. The first line of the file must contain column headings.

<h5 id="club-planner">Club Planner</h5>


This is a text file format that can be used to help plan events for a club, such as an alumni club.

See the [Club Planner specification][club] for details.

<h5 id="excel-spreadsheet">Excel Spreadsheet</h5>


Setting this option will cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an '.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first row will be expected to contain column headings, with data in following rows. The first blank row will terminate the list. Each row in the spreadsheet (after the first, containing headings) will be treated as a data record, and each column will be treated as a separate field. Columns containing hyperlinks will also generate fields containing the hyperlinks, and named by appending "link" to the column heading. For example, a column named "ISBN" could have its content accessed with the variable "isbn" and its link accessed with the variable "isbnlink".

<h5 id="excel-table">Excel Table</h5>


Setting this option will also cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an '.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first blank row will terminate the list. With this option, each row in the spreadsheet will be returned as a single data field, identified by a variable name of "Table Row". The data returned will include beginning and ending td tags for each column, with appropriate formatting and cell dimensions and hyperlinks, mimicking the format of the Excel spreadsheet as closely as possible. The data returned does not include beginning or ending tr tags.

<h5 id="file-directory">File Directory</h5>


When you specify a file directory as your data source, each entry in that directory will then be treated as if it were a single record, or line, or row, from a data file. The "Maximum Directory Depth" field below will control the depth to which sub-directories are read.

See the [File Directory specification](#filedirectory) for details.

<h5 id="html-bookmarks-using-lists">HTML Bookmarks using Lists</h5>


Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain bookmarks, using nested lists to indicate a folder hierarchy. A table will be created with the following columns. Many bookmark managers, including popular browsers, can save or export bookmarks into this format.

Category 1 through 6
:    Any text found outside of an actual hyperlink will be placed in one of these category columns, depending on the nesting level of the list it was found in.

Description
:    The text that was hyperlinked.

E-mail
:    If the hyperlink starts with "mailto:", then it will be interpreted as an e-mail address and placed in this column.

Web Site
:    If the hyperlink does not start with "mailto:", then the URL will be placed in this field.

<h5 id="html-bookmarks-using-headings">HTML Bookmarks using Headings</h5>


Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain bookmarks, using varying levels of heading tags to indicate a folder hierarchy. A table will be created with the same columns as the HTML Bookmarks using Lists, described above. The contents of the varying heading levels will be placed in Category 1 through 6.

<h5 id="html-table">HTML Table</h5>


Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain a table, using table, tr, th and/or td tags. The first row in the table will be expected to contain column headings, with following rows containing the corresponding data.

Note that many HTML pages are laid out using tables, not to present columns and rows of data, but to format the page in a desired fashion. Tables containing column headings and following data may often be embedded in such layout tables. In order to try to separate the two, PSTextMerge in this mode will look for the first table cell (within th or td tags) containing 1 to 40 characters of text, with the idea that text beyond either extreme is not likely to be a true column heading. During this search for the first cell of a data table, table cells with colspan or rowspan parameters greater than 1 will also be ignored.

In some cases, however, you may need to edit the prospective input file with your favorite text editor, and delete lines preceding and following the table containing the data you are interested in, saving the resulting file as a separate file to be input to PSTextMerge. If a true first column heading is longer than 40 characters, then you will also need to reduce its length to the acceptable 1-40 range.

Note that for any HTML input option, character entities found within HTML text will be translated to their equivalent ASCII characters. For now, translation is only provided for characters that are not platform-specific: "&nbsp;" (non-breaking space), "&lt;" (less than sign),  "&gt;" (greater than sign),  "&amp;" (ampersand) and "&quot;" (double quotation marks). Entities may be specified using mnemonics or their numeric equivalents.

<h5 id="html-links">HTML Links</h5>


Setting this option will cause the program to treat the input file as HTML. It will extract all of the links within this file, identifying the from page, the link type, and to file, for each link found. Link type may be linkhref, imgsrc, or ahref, for the corresponding tag and attribute combinations.

<h5 id="itunes-libraryxml">iTunes Library.xml</h5>


Selecting this option will cause the input file to be treated as an iTunes Library.xml file.

This file can generally be found in your Music / My Music folder, and then within your iTunes folder. See Apple Support articles [Where are my iTunes files located?](http://support.apple.com/kb/HT1391) and [What are the iTunes library files?](http://support.apple.com/kb/HT1660) for details.

This information can be used to publish your list of albums to a Web site, or to import your list of albums into a database, such as Bento.

This input routine will extract and summarize information about albums contained in your iTunes library. For each unique album title, the following fields will be extracted.

Album Title
:    The title of the album

Artist
:    The name of the Artist for the album. If the album contains tracks performed by multiple artists, then this field will be set to "Various".

Artist Sort Key
:    The Artist name that is used by iTunes for sorting: typically, the same as the Artist name, but without a leading "The".

Genre
:    The genre assigned to the tracks in the album. Any genre containing "jazz" will be reported as "Jazz"; any genre containing "holiday", "Christmas" or "xmas" will be reported as "Holiday"; any other variations in genre within an album will result in this field being set to "Mixed".

Year
:    The year in which the album was released.

Tracks
:    The number of tracks in your iTunes library having this album title: this field can usually tell you whether you have an entire album in your collection, or only a few selections.

Unrated?
:    This field will be set to "Unrated" if any of this album&#8217;s tracks are as yet unrated by you. Once you have assigned a rating to all tracks on this album, this field will be reported as blank.

Hi-Fi
:    This field will be reported as "Hi-Fi" if the album&#8217;s tracks have been recorded in the Apple Lossless format.

Lo-Fi Type
:    If the album contains tracks that are not in the lossless format, then their format will be reported here, typically as "AAC" or "MP3" or "Mixed", if the album contains a mix of types.

Podcast
:    If the album contains any tracks identified with a Media Kind of "podcast", then this field will be set to "Podcast".

<h5 id="markdown-metadata">Markdown Metadata</h5>


If you select a folder containing [Markdown][] files, then this input option will extract metadata about each file and make them available in a list.

See the [Markdown Metadata specification][metamarkdown] for details.

<h5 id="xml-rows-for-fields">XML Rows for Fields</h5>


Setting this option will cause the program to treat the input file as XML. Each field will be returned as a separate record. Field names will be stored in columns 1 - 4, with column names "Name1" through "Name4", and field values will be stored in column 5, with column name "Data".

<h5 id="xml-rows-for-records">XML Rows for Records</h5>


Setting this option will cause the program to treat the input file as XML. Each set of fields at the same level will be returned as a separate record, with the assumption that the XML file consists of a series of records with the same fields. Field names will be stored in columns 1 - 4, with column names "Name1" through "Name4", and field values will be stored in column 5, with column name "Data".

<h4 id="data-dictionary-input">Data Dictionary Input</h4>


Clicking on this check box causes the program to look for a special data dictionary file to accompany the tab-delimited data source. The data dictionary file must have the same name as the primary file, but with a file extension of ".dic".

The data dictionary file itself is in tab-delimited format. Each row in the file (after the column headings) represents one column in the primary data file.

Note that the easiest way to create a data dictionary for a file is to input the file to PSTextMerge (without a dictionary), then Output it with a dictionary (see the <a href="#output">Output</a> section for details). You can then edit the resulting file, only modifying the values you wish to change.

A dictionary may have the following columns describing the fields in the primary file.

<h5 id="proper-name">Proper Name</h5>


This is the column heading that identifies this field in the primary data file. It would normally include mixed-case, spaces and punctuation to make it readable.

<h5 id="common-name">Common Name</h5>


This is really a lowest-common denominator form of the column heading, with capitals, spaces and punctuation all removed. This is used internally by PSTextMerge to allow slight variations in punctuation, etc., without recognizing them as two separate names.

<h5 id="alias-for">Alias For</h5>


If the names previously given should be treated as an alias for another field name, then this column should contain the primary name for the field, and the remaining columns for the alias should be left blank. This feature doesn&#8217;t do much in this release of PSTextMerge, but should become more meaningful in later releases.

<h5 id="data-format-rule">Data Format Rule</h5>


The default value here is "DataFormatRule", which causes no special formatting of the input data. But specifying another value can cause the input data to be formatted and even converted according to any of the following special rules.

AllCapsRule
:    Causes all letters to be capitalized (good for US state codes, for example).

CountryRule
:    Ensures that the first letter of each word is capitalized, and remaining letters are lower-case, with the exceptions of the values "UK and "USA", which are ensured to be in all capitals. Intended for mailing list fields containing country!

DataFormatRule
:    Performs no conversion or formatting. This is the default value when PSTextMerge is creating a new dictionary file.

DateRule
:    Generally expects a date in a loose month-day-year format, and formats it into a "tighter" month-day-year format, with two-digit numbers for each. More flexibility may be supported in later releases.

HyperlinkRule
:    Replaces spaces with "%20" strings, and adds a protocol of "http:" or "file:" if one is not present, based on whether the address looks like a WWW address or a local file. Good for formatting hyperlinks from MS Office documents into valid HTML URL links.

InitialCapsRule
:    Ensures that the first letter of each word is capitalized, and other letters are lower-case. Good for formatting names of people, cities, etc.

LowerCaseRule
:    Ensures all letters are lower-case. Good for formatting URLs and e-mail addresses.

USMobileRule
:    Intended for formatting of mobile (cell and pager) telephone numbers according to US conventions. Similar to USPhoneRule, but with different rules for conversion of the 602 area code.

USPhoneRule
:    Intended for formatting of non-mobile telephone numbers according to US conventions. The standard format is "aaa-xxx-nnnn", where "aaa" is the area code, "xxx" is the local exchange, and "nnnn" is the rest of the phone number. The default area code is 602, for Phoenix, Arizona. This rule also will convert area codes from 602 to one of the newer Phoenix metropolitan area codes, depending on the exchange. Note that this logic has limited usefulness at this point, as new (and duplicate) exchanges begin to be assigned within the three area codes.

<h5 id="function">Function</h5>


If a valid function name is specified here, then the field value
will be calculated using the specified function. The following functions are available. The functions make use of the parameters specified in the following columns.

Lookup
:    Looks up the value for this field in a separate table, stored as a tab-delimited file in the same directory, using the following five parameters.

<table>
	<tr>
		<th>Parm</th>
		<th>Used As</th>
	</tr>
	<tr>
		<td>Parm1</td>
		<td>Name of the file containing the lookup table.</td>
	</tr>
	<tr>
		<td>Parm2</td>
		<td>
			Name of the field in the lookup table that should be used
			as the table&#8217;s key.
		</td>
	</tr>
	<tr>
		<td>Parm3</td>
		<td>A value of "Yes" or "True" indicates that the key comparison should be case-sensitive.
		</td>
	</tr>
	<tr>
		<td>Parm4</td>
		<td>Name of the field in this file that should be used as the lookup key.</td>
	</tr>
	<tr>
		<td>Parm5</td>
		<td>Name of the field in the lookup file that should be returned and used as the value for this field. </td>
	</tr>
</table>


<h5 id="parm1">Parm1</h5>


Used as input to the specified function.

<h5 id="parm2">Parm2</h5>


Used as input to the specified function.

<h5 id="parm3">Parm3</h5>


Used as input to the specified function.

<h5 id="parm4">Parm4</h5>


Used as input to the specified function.

<h5 id="parm5">Parm5</h5>


Used as input to the specified function.

<h4 id="merge-into-existing-data">Merge Into Existing Data</h4>


Select one of the following radio buttons to indicate how and whether you want new data to be merged with existing data.

<h5 id="no-merge">No Merge</h5>


This is the default. The next data source to be input will overlay any data previously input.

<h5 id="merge-new-data-with-old">Merge New Data With Old</h5>


The next input data source to be opened will be merged with the current data visible on the View Tab. Existing sort keys and filters may need to be reapplied. The new data and the old data should have column names that are at least partially overlapping, if not identical.

<h5 id="merge-with-same-columns">Merge with Same Columns</h5>


The next input data source to be opened will be merged with the current data visible on the View Tab. The program will not look for column headings in the new input file, but will instead assume that the column names for the new file are the same, and in the same order, as those currently visible on the View Tab. Existing sort keys and filters may need to be reapplied.

Note that this merge option is useful for programs (such as AppleWorks) that do not include column headings in their export files. If a separate file is created containing only column headings, then it can be input first, followed by the headerless file, with this merge option.

<h4 id="maximum-directory-depth">Maximum Directory Depth</h4>


If you are about to read a file directory, then this field controls whether sub-directories are read, and to what depth. A value of 1 is the default, and indicates that only files and directories in the specified directory will be listed, with no sub-directory contents. A value of 2 indicates one level of sub-directories, and so forth. Use the Increment and Decrement buttons to change the depth.

<h3 id="textmerge-view">TextMerge View</h3>


This tab allows the user to view a tab-delimited data file that has been opened for input. Subsequent tabs, such as Sort and Filter, will affect the data that is displayed on the View tab.

The user can scroll from left to right and up and down, assuming there is more data available than will fit within the current window. Columns can also be resized by clicking on their right borders and dragging. Each initial column size will be approximately proportional to the largest data field within the column.

<h3 id="textmerge-sort">TextMerge Sort</h3>


This tab allows the user to sort the data that has been input. Sorting is accomplished by using the following buttons that appear on this tab.

<h4 id="field-name">Field Name</h4>


This is a drop down list of all the columns in your data. Select the next field name on which you wish to sort, by starting with the most significant fields and proceeding to less significance.

<h4 id="sort-sequence">Sort Sequence</h4>


This is a drop down list. Pick either ascending (lower values towards the top, higher values towards the bottom) or descending. This sequence applies to the currently selected field name (see above).

<h4 id="add">Add</h4>


Pressing this button will add the field and sequence currently specified to the current sort parameters being built. The sort parameters added will appear in the text area shown below on this tab. After pressing the Add button, the user may go back and specify additional fields to be used in the sort criteria.

<h4 id="clear">Clear</h4>


Pressing this button will clear the sort parameters being built, so that you can start over.

<h4 id="set">Set</h4>


Once your desired sort parameters have been completely built, by pressing the Add button one or more times, you must press the Set button to cause your parameters to be applied to the data you are currently processing.

<h4 id="combine">Combine</h4>


After setting the desired sort sequence, you may optionally press this button to combine records with duplicate sort keys. The following buttons allow you to adjust the parameters controlling the combination process.

<h4 id="tolerance-for-data-loss">Tolerance for Data Loss</h4>


Record combination can be done with varying degrees of tolerance for data loss. Select one of the following radio buttons.

* No Data Loss &#8212; Records will only be combined if data (non-key) fields are identical, or if one of the two corresponding values is blank (in which case the non-blank value will be preserved in the resulting combined record).
* Later Records Override Earlier &#8212; If you are merging two input files, and one is known to carry more current data, then you can specify which file is the more current with this button or the next. This button will cause files merged later to be treated as more current.
* Earlier Records Override Later &#8212; Similar to prior button, but with files merged earlier taking precedence over later ones.
* Combine Fields Where Allowed &#8212; If the data dictionary allows fields to be concatenated, then this radio button indicates that concatenation may take place. This may be appropriate for a comment field.


<h4 id="minimum-number-of-lossless-fields">Minimum Number of Lossless Fields</h4>


If you specify some data loss to be acceptable, then this field may be used to specify a minimum number of data (non-key) fields that must be lossless (equal or one blank) before combination will be allowed to take place. This should be used if the sort keys are not guaranteed to establish uniqueness. Specifying a non-zero value here may help to prevent completely disparate records from being inadvertently combined. For example, names can be used to identify people, but two different people may have the same name.

Increment (+)
:    Use this button to increase the minimum number of lossless fields.

Decrement (-)
:    Use this button to decrease the minimum number of lossless fields. Zero is the smallest allowable value.

<h3 id="textmerge-filter">TextMerge Filter</h3>


This tab allows the user to filter the data that has been input, selecting some rows to appear and others to be suppressed. Filtering is accomplished by using the following buttons that appear on this tab.

<h4 id="field-name">Field Name</h4>


This is a drop down list of all the columns in your data. Select the next field name on which you wish to filter.

<h4 id="comparison-operator">Comparison Operator</h4>


This is a drop down list. Pick the operator that you want to use to compare your selected field to the following value. The following operators are available.

* equals
* greater than
* greater than or equal to
* less than
* less than or equal to
* not equal to
* contains
* does not contain
* starts with
* does not start with
* ends with
* does not end with

<h4 id="comparison-value">Comparison Value</h4>


This is the value to which the selected field will be compared. Only rows that satisfy this comparison will be visible after the filtering operation. You may type in a desired value, or select from the drop down list. The drop down list will consist of all the values found in this field within your data.

<h4 id="add">Add</h4>


Pressing this button will add the field, operator and value currently specified to the current filter parameters being built. The filter parameters added will appear in the text area shown below on this tab. After pressing the Add button, the user may go back and specify additional fields to be used in the filtering criteria.

<h4 id="clear">Clear</h4>


Pressing this button will clear the filter parameters being built, so that you can start over.

<h4 id="set">Set</h4>


Once your desired filter parameters have been completely built, by pressing the Add button one or more times, you must press the Set button to cause your parameters to be applied to the data you are currently processing.

<h4 id="andor">And/Or</h4>


If you specify more than one filter parameter, then you may specify whether all of them must be true (and) or only any one of them must be true (or) in order to satisfy the filtering criteria. This choice applies to the entire set of criteria, so this need only be selected once before pressing the Set button.

<h3 id="textmerge-output">TextMerge Output</h3>


This tab allows the user to save the current data to an output disk file. The data will be saved in the sequence set by the current sort parameters, if any. If a current filter is in effect, then only visible, filtered data will be saved. If any data transformation or formatting occurred on input, then the data will be saved in its new form. The following controls appear on this tab.

<h4 id="save-output">Save Output</h4>


Clicking on this button allows you to select a location and name for the output file, and then writes the file (with optional dictionary) as specified. This function can also be invoked via the File/Save Menu item or with the S shortcut key.

<h4 id="data-dictionary-output">Data Dictionary Output</h4>


Clicking on this check box causes the program to write a special data dictionary file to accompany the tab-delimited data source being output. The data dictionary file will have the same name as the primary file, but with a file extension of &#8220;.dic&#8221;. Clicking this check box again will cause it to revert to its original state, in which the dictionary will not be saved. See the <a href="input.html">Input</a> section for a complete description of the dictionary file.

<h3 id="textmerge-template">TextMerge Template</h3>


This tab allows the user to merge the currently loaded data into a template file, producing one or more output text files. The greatest anticipated use for this function is to create Web pages, based on input template files containing a mixture of HTML tags and special PSTextMerge tags. This allows tab-delimited data to be periodically merged into an HTML template that determines the format in which the data will be displayed on a Web site.

<h4 id="buttons">Buttons</h4>


This screen contains the following buttons.

<h5 id="set-template-library">Set Template Library</h5>


PSTextMerge supports the concept of a central template library where you can store reusable templates. The initial location for this folder is the &#8220;templates&#8221; folder within the PSTextMerge Folder that comes as part of the software distribution. However, this button can be used to allow you to select another folder as your template library. After installation of PSTextMerge, you may wish to copy the templates folder to another location, perhaps within your home folder, or your documents folder, and then use this button to specify that new location.

<h5 id="open-template">Open Template</h5>


This button allows you to specify the location and name of the template file you wish to use. (This file must have previously been created using a text editor.) This function may also be invoked via the Template/Open Menu item or with the T shortcut key.

<h5 id="open-from-library">Open From Library</h5>


This button also opens a template file, but uses your template library as the starting location.

<h5 id="generate-output">Generate Output</h5>


This button processes the template file you have selected, and creates whatever output file(s) you have specified in the template file.  The function may also be invoked via the Template/Generate Menu item or with the G shortcut key.

<h4 id="template-file-format">Template File Format</h4>


See the [Template File Format specification](#templatefiles) for details.

<h3 id="textmerge-script">TextMerge Script</h3>


This tab allows the user to record and playback sequences of PSTextMerge commands. The following buttons and menu commands are available.

<h4 id="record">Record</h4>


Clicking on this button once causes the program to begin recording your subsequent actions as part of a script that can be edited and played back later. This function may also be invoked via the Script/Record Menu item, or with the R shortcut key. You will need to specify the location and name for your script file. It is recommended that &#8220;.tcz&#8221; be used as a file extension for PSTextMerge script files (the original name for the program was &#8220;TDF Czar&#8221;). This will be supplied as a default if no extension is specified by the user.

<h4 id="stop">Stop</h4>


Clicking on this button causes recording of the current script to stop. This function may also be invoked via the Script/End Recording Menu item, or with the E shortcut key. The script file will be closed, and can now be opened for editing, if desired, using the text editor, spreadsheet or database program of your choice.

<h4 id="play">Play</h4>


This button allows you to select a script file to be played back. This function may also be invoked via the Script/Play Menu item, or with the P shortcut key. At the end of a script&#8217;s execution, the input file options will be reset to their initial default values, to ensure consistent execution when multiple scripts are executed consecutively.

<h4 id="play-again">Play Again</h4>


This button allows you to replay the last script file either played or recorded. Using this button allows you to bypass the file selection dialog. It can be handy if you are developing, modifying or debugging a series of actions and associated files. This function may also be invoked via the Script/Play Again Menu item, or with the A shortcut key.

<h4 id="turn-autoplay-on">Turn Autoplay On</h4>


Clicking this button will allow you to select a script to be automatically played every time the application is launched.

After selecting a script to play automatically, the label of this button will change to &#8220;Turn Autoplay Off&#8221;.

<h4 id="turn-easy-play-on">Turn Easy Play On</h4>


Clicking this button will allow you to select a folder of scripts that you want easy access to. A new tab will then be added to the interface, labeled &#8220;Easy&#8221;. The new tab will contain a button for every script found in the folder. Clicking on a button will then play the corresponding script.

After selecting an Easy Play folder, the label of this button will change to &#8220;Turn Easy Play Off&#8221;.

<h4 id="play-recent">Play Recent</h4>


This menu item, within the Script menu, allows you to select a recently played script to run. The most recent 10 scripts will be available to select from.

<h4 id="script-file">Script File</h4>


See the [Script File Format specification](#scriptfiles) for details.

<h3 id="textmerge-logging">TextMerge Logging</h3>


This tab allows the user to control logging operations. PSTextMerge writes information about certain events to a log file. Reviewing this data can be useful, especially if the program is not performing as expected. The following sections appear on this tab.

<h4 id="log-destination">Log Destination</h4>


This determines where the log output is sent. You can select any of the following options.

* None &#8212; The logging data will not be visible.
* Window &#8212; Sends log messages to the standard Java console.
* Text &#8212; Sends log messages to the text area below, within the Log tab.
* Disk &#8212; Saves the log messages to a disk file, with the standard name &#8220;PSTextMerge_log.txt&#8221;.

<h4 id="logging-threshold">Logging Threshold</h4>


This determines the quantity and severity of messages that will appear in the log. You have the following options.

* Normal &#8212; All events will be written to the log, even those indicating normal program operation.
* Minor &#8212; Minor, Medium and Major severity events will be written.
* Medium &#8212; Medium and Major severity events will be written.
* Major &#8212; Only major events will be written to the log.

<h4 id="data-logging">Data Logging</h4>


Input data files are often passed to the logger, primarily so that significant events that are data-related can include a display of the data record that generated the event. Checking the &#8220;Log All Data?&#8221; box will result in all data passed to the logger being written to the log. This may be helpful if the log is otherwise showing insufficient data to let you understand the workings of the program.

<h2 id="help">Help</h2>


The following commands are available. Note that the first two commands open local documentation installed with your application, while the next group of commands will access the Internet and access the latest program documentation, where applicable.

* **Program History** -- Opens the program's version history in your preferred Web browser.

* **User Guide** -- Opens the program's user guide in your preferred Web browser.

* **Check for Updates** -- Checks the PowerSurgePub web site to see if you're running the latest version of the application.

* **PSTextMerge Home Page** -- Open's the PSTextMerge product page on the World-Wide Web.

* **Reduce Window Size** -- Restores the main PSTextMerge window to its default size and location. Note that this command has a shortcut so that it may be executed even when the PSTextMerge window is not visible. This command may sometimes prove useful if you use multiple monitors, but occasionally in different configurations. On Windows in particular, this sometimes results in PSTextMerge opening on a monitor that is no longer present, making it difficult to see.

<h2 id="file-formats">File Formats</h2>


The following file formats are used by PSTextMerge.

<h3 id="template-files">Template Files</h3>


This section describes the contents of a template file, used for producing formatted output from a table of rows and columns.

This program will look for two sorts of special strings embedded within the template file: <a href="#vars">variables</a> and <a href="#commands">commands</a>.

<h4 id="delimiters">Delimiters</h4>


Beginning with version 3.0, PSTextMerge will recognize either of two sets of command and variable delimiters automatically. The choice of delimiters will be triggered by the first command beginning delimiters encountered. The new delimiters are generally recommended, since they are more likely to be treated kindly by various HTML editors on the market when you are editing your template files.

<table>
<tr><th>Meaning</th><th>Original Delimiters</th><th>New Delimiters</th></tr>
<tr><td>Start of Command</td><td>&lt;&lt;</td><td>&lt;?</td></tr>
<tr><td>End of Command</td><td>&gt;&gt;</td><td>?&gt;</td></tr>
<tr><td>Start of Variable</td><td>&lt;&lt;</td><td>=$</td></tr>
<tr><td>End of Variable</td><td>&gt;&gt;</td><td>$=</td></tr>
<tr><td>Start of Variable Modifiers</td><td>&amp;</td><td>&amp;</td></tr>
</table>

<h4 id="variables">Variables</h4>


Variables will be replaced by values taken from the corresponding columns of the current data record, or from an internal table of global variables. Variables must be enclosed in the chosen delimiters. Each variable name must match a column heading from the data file, or a global name specified in a SET command. The comparison ignores case (upper or lower), embedded spaces and embedded punctuation when looking for a matching column heading. So a column heading of "First Name" will match with a variable of "firstname", for example.

A variable, unlike a command, can appear anywhere within the template file, and need not be isolated on a line by itself. More than one variable can appear on the same line. Variables can be used within PSTextMerge commands, as well as other places within the template file.

The following special variables are predefined and available for substitution, no matter what data source is being used.

<dl>
<dt>datafilename</dt>
	<dd>The name of the data source being used.</dd>
<dt><a id="dataparent">dataparent</a></dt>
	<dd>The path to the enclosing folder for the current data file. This can be used as part of an output command to specify an output file in the same folder as the data file. </dd>
<dt>templatefilename</dt>
	<dd>The name of the template file itself</dd>
<dt>today</dt>
	<dd>The current date, at the time that template output is being generated.</dd>
</dl>

<h5 id="variable-modifiers">Variable Modifiers</h5>


A variable can be optionally followed (within the less than/greater than signs) by a modifier indicator and one or more modifiers. The default modifier character is the ampersand (&amp;).

<h5 id="case-modifiers-u-or-l">Case Modifiers "U" or "L"</h5>


The letters "U" or "L" (in either upper- or lower-case) will indicate that the variable is to be converted, respectively, to upper- or lower-case. If the letter "i" is also supplied (again in either upper- or lower-case), then only the first character of the variable value will be converted to the requested case. (The letter "i" stands for "initial".)

<h5 id="xml-modifier-x">XML Modifier "X"</h5>


The letter "X" will cause selected special characters to be translated to their equivalent XML entities. This is recommended, for example, when publishing an RSS (Really Simple Syndication) feed.

<h5 id="html-modifier-h">HTML Modifier "H"</h5>


The letter "H" will cause selected special characters to be translated to their equivalent HTML entities.

<h5 id="link-modifier-j">Link Modifier "J"</h5>


Convert a URL to an HTML anchor tag with that URL as the href value.

<h5 id="no-breaks-modifier-n">No Breaks Modifier "N"</h5>


Remove HTML break (br) tags from the string.

<h5 id="base-file-modifier-b">Base File Modifier "B"</h5>


The letter "B" will cause the file extension, including the period, to be removed from a file name. This can be used, for example, to generate an output file name with the same name as the input data file (using the variable name "datafilename"), but with a different extension.

<h5 id="file-name-modifier-f">File Name Modifier "F"</h5>


Converts a string to a conventional, universal file name, changing spaces to dashes, removing any odd characters, making all letters lower-case, and converting white space to hyphens.

<h5 id="remove-awkward-punctuation-modifier-p">Remove Awkward Punctuation Modifier "P"</h5>


Remove awkward punctuation characters.

<h5 id="keep-characters-on-the-right-modifier-r">Keep Characters on the Right Modifier "R"</h5>


The letter "R", in combination with a length modifier (see below), will cause the variable to be truncated to the given length, truncating characters on the left and keeping characters on the right.

<h5 id="length-modifier">Length Modifier</h5>


One or more digits following the modifier indicator will be interpreted as the length to which the variable should be truncated or padded. If the length modifier is shorter than the variable length, then by default characters will be truncated on the right (and preserved on the left) of the variable to bring it to the specified length (if it is desired to keep characters on the right, then also use the "R" modifier, described above). If the length modifier is longer than the initial variable length, then the variable will be padded with zeroes on the left to bring it to the specified length.

<h5 id="underscore-modifier">Underscore Modifier</h5>


An underscore character ("_") following the modifier indicator will cause all spaces in the variable to be replaced by underscores. This can be useful when creating a file name, for example.

<h5 id="punctuation-modifier">Punctuation Modifier</h5>


Any punctuation character other than an underscore following the modifier indicator will be interpreted as a separator that will be placed before the current variable, if the variable is non-blank, and if the preceding variable was also non-blank and also marked by a similar variable modifier. A space will be added after the separator, and before the current variable, if the punctuation is not a forwards or backwards slash ("/" or "\"). This is an easy way to list several variables on a single line, separating non-blank ones from others with commas (or other punctuation).

<h5 id="word-demarcation-modifier">Word Demarcation Modifier</h5>


If a variable may be interpreted as a series of "words," with the words delimited by white space, punctuation, or transitions from lower to upper case ("two words", "TWO_WORDS" or "twoWords"), then these variable modifiers may be used to change the way in which the words are delimited.

<table>
<tr><th>Letter</th><th>Meaning</th></tr>

	<tr><td>c </td>
		<td>This letter must begin the string, to indicate that modified word demarcation is desired. This should be followed by three letters, each with one of the following values. The first occurrence indicates what should be done with the first letter of the variable; the second occurrence indicates what should be done with the first letter of all other words; the third occurrence indicates what should be done with all other letters in the variable.</td></tr>
	<tr><td>u </td>
		<td>This letter indicates that upper-case is desired. </td></tr>
	<tr><td>l </td>
		<td>This letter indicates that lower-case is desired. </td></tr>
	<tr><td>a </td>
		<td>This letter indicates that the case should be left as-is. </td></tr>
	<tr><td>- </td>
		<td>Any character(s) following the 'c', other than 'u', 'l' or 'a', will be used as delimiters separating each word. </td></tr>
</table>

For example, if the template file contained the following:

<blockquote>
	AM32;
</blockquote>

And the name variable was equal to:

<blockquote>
	HERB BOWIE
</blockquote>

Then the resulting name in the output text file would be:

<blockquote>
	Herb Bowie
</blockquote>

<h5 id="formatting-string">Formatting String</h5>


A string of characters indicating how the variable is to be formatted. The formatting string, if specified, should follow any other variable modifiers. Any character other than those listed above will cause the remainder of the variable modifiers to be treated as a formatting string. Currently, a formatting string is valid only for dates -- either for the special variable **today**, or for any variable date in "mm/dd/yy" format.

A date formatting string follows the normal rules for Java date formatting. One or more occurrences of an upper-case "M" indicates a month, a lower-case "y" is used for a year, and a lower-case "d" is used for the day of the month. An upper-case "E" can be used for the day of the week. Generally, the number of occurrences of each letter you specify will be used to indicate the width of the field you want ("yyyy" for a 4-digit year, for example). Specifying more than two occurrences of "M" indicates you want the month represented by letters rather than numbers, with 4 or more occurrences indicating you want the month spelled out, and 3 occurrences indicating you want a three-letter abbreviation.

See below for full definition of allowable characters and their meanings.

<table>
	<tr>
		<th>Symbol</th>
		<th>Meaning</th>
		<th>Presentation</th>
		<th>Example</th>
	</tr>
	<tr>
		<td>G</td>
		<td>era designator</td>
		<td>Text</td>
		<td>AD</td>
	</tr>
	<tr>
		<td>y</td>
		<td>year</td>
		<td>Number</td>
		<td>1996</td>
	</tr>
	<tr>
		<td>M</td>
		<td>month in year</td>
		<td>Text & Number</td>
		<td>July & 07</td>
	</tr>
	<tr>
		<td>d</td>
		<td>day in month</td>
		<td>Number</td>
		<td>10</td>
	</tr>
	<tr>
		<td>h</td>
		<td>hour in am/pm</td>
		<td>1~12</td>
		<td>12</td>
	</tr>
	<tr>
		<td>H</td>
		<td>hour in day</td>
		<td>0~23</td>
		<td>0</td>
	</tr>
	<tr>
		<td>m</td>
		<td>minute in hour</td>
		<td>Number</td>
		<td>30</td>
	</tr>
	<tr>
		<td>s</td>
		<td>second in minute</td>
		<td>Number</td>
		<td>55</td>
	</tr>
	<tr>
		<td>S</td>
		<td>millisecond</td>
		<td>Number</td>
		<td>978</td>
	</tr>
	<tr>
		<td>E</td>
		<td>day in week</td>
		<td>Text</td>
		<td>Tuesday</td>
	</tr>
	<tr>
		<td>D</td>
		<td>day in year</td>
		<td>Number</td>
		<td>189</td>
	</tr>
	<tr>
		<td>F</td>
		<td>day of week in month</td>
		<td>Number</td>
		<td>2 (2nd Wed in July)</td>
	</tr>
	<tr>
		<td>w</td>
		<td>week in year</td>
		<td>Number</td>
		<td>27</td>
	</tr>
	<tr>
		<td>W</td>
		<td>week in month</td>
		<td>Number</td>
		<td>2</td>
	</tr>
	<tr>
		<td>a</td>
		<td>am/pm marker</td>
		<td>Text</td>
		<td>PM</td>
	</tr>
	<tr>
		<td>k</td>
		<td>hour in day</td>
		<td>Number</td>
		<td>24</td>
	</tr>
	<tr>
		<td>K</td>
		<td>hour in am/pm</td>
		<td>Number</td>
		<td>0</td>
	</tr>
	<tr>
		<td>z</td>
		<td>time zone</td>
		<td>Text</td>
		<td>Pacific Standard Time</td>
	</tr>
	<tr>
		<td>'</td>
		<td>escape for text</td>
		<td>Delimiter</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td>single quote</td>
		<td>Literal</td>
		<td></td>
	</tr>
</table>

The count of pattern letters determine the format.

<strong>(Text)</strong>: 4 or more pattern letters--use full form,
&lt; 4--use short or abbreviated form if one exists.

<strong>(Number)</strong>: the minimum number of digits. Shorter
numbers are zero-padded to this amount. Year is handled specially;
that is, if the count of 'y' is 2, the Year will be truncated to 2 digits.

<strong>(Text &amp; Number)</strong>: 3 or over, use text, otherwise use number.

Any characters in the pattern that are not in the ranges of ['a'..'z']
and ['A'..'Z'] will be treated as quoted text. For instance, characters
like ':', '.', ' ', '#' and '@' will appear in the resulting time text
even they are not embraced within single quotes.

<h4 id="commands">Commands</h4>


All commands must be enclosed in the chosen delimiters. In addition, all commands must appear on lines by themselves. Command names can be in upper- or lower-case. Each command may have zero or more operands. Operands may be separated by any of the following delimiters: space, comma (','), semi-colon (';') or colon (':'). Operands that contain any of these delimiters must be enclosed in single or double-quotation marks.

The following commands are recognized. They are presented in the typical sequence in which they would be used.

<div class="pnobr">
<p>&lt;?delims new delimiters?&gt;</p>
<p>&lt;?output "filename.ext"?&gt;</p>
<p>&lt;?set global = 0?&gt;</p>
<p>&lt;?nextrec?&gt;</p>
<p>&lt;?include "filename.ext" ?&gt;</p>
<p>&lt;?ifchange ?&gt;</p>
<p>&lt;?if ?&gt;</p>
<p>&lt;?definegroup group-number ?&gt;</p>
<p>&lt;?ifendgroup group-number?&gt;</p>
<p>&lt;?ifendlist group-number?&gt;</p>
<p>&lt;?ifnewlist group-number?&gt;</p>
<p>&lt;?ifnewgroup group-number?&gt;</p>
<p>&lt;?else?&gt;</p>
<p>&lt;?endif?&gt;</p>
<p>&lt;?loop?&gt;</p>
</div>

<h5 id="ltdelims-inew-delimitersigta">&lt;?delims <i>new delimiters</i>?&gt;</a></h5>


If used at all, this command should be the first command in the template file. This command overrides the standard delimiters used to recognize the beginnings and ends of commands and variables, for the remainder of the current template file. The command can have one to five operands. Each operand will become a new delimiter. They should be specified in the following order.

* beginning of a command (normally paired less than signs)
* the end of a command (normally paired greater than signs)
* the beginning of a variable (normally paired less than signs)
* the end of a variable (normally paired greater than signs)
* the beginning of variable modifiers (normally a single ampersand)

Note that, when using this command, this command itself must use the standard delimiters. The new delimiters should only begin to be used on following lines.

<h5 id="ltoutput-ifilenameextigt">&lt;?output <i>filename.ext</i>?&gt;</h5>


This command names and opens the output file. The single operand is the name of the output file. <i>filename.ext</i> should be the desired name of your output file. This command would normally be the first line in your template file. Subsequent template records will be written to the output file. Note, however, that the filename can contain a variable name. In this case, the output command would immediately follow the nextrec command, and a new output file would be opened for each tab-delimited data record.

<h5 id="ltset-iglobali--0gt">&lt;?set <i>global</i> = 0?&gt;</h5>


This command can define a global variable and set its value. This command would normally have three operands: the name of the global variable, an operator, and a value.

<ul>
<li>Global variable name. This should not be the same as the name of any variable name specified by the input data file. The global variable name, when used as the object of a SET command, should not be enclosed within the normal variable delimiters, since this would cause the variable name to be replaced by its current value.</li>

<li>Operator. Any of the following operators can be used.
<dl>
<dt>=</dt>
<dd>This will cause the global variable to be set equal to the following value.</dd>
<dt>+= or simply +</dt>
<dd>This will cause the value to be added to the current value of the global variable.</dd>
<dt>++</dt>
<dd>This can be used to add a value of 1 to the current value of the global variable, without having to specify the following value of 1. (In this case, the SET command only takes two operands.)</dd>
<dt>-= or simply -</dt>
<dd>This will cause the value to be subtracted from the current value of the global variable.</dd>
<dt>--</dt>
<dd>This can be used to subtract a value of 1 from the current value of the global variable, without having to specify the following value of 1. (In this case, the SET command only requires two operands.)</dd>
</dl></li>
<li>Value. This can be a literal or a variable (in which case it should be surrounded by the normal variable delimiters). The value can be a text string or an integer.</li>
</ul>
One intended use for the SET command is to support a line counter. By initializing the value to 0, and then adding to it whenever an output line is generated, the IF command can be used to check for page overflow (in a table column, for example), and then start a new page or column, resetting the counter to 0 again.

Another common use for the SET command is to preserve record variables in global variables so that they will be available within an IFENDGROUP block.

<h5 id="ltnextrecgt">&lt;?nextrec?&gt;</h5>


This command indicates the beginning of the code that will be written out once per data record. Lines prior to the nextrec command will only be written out once.

<h5 id="ltinclude-ifilenameextigt">&lt;?include <i>filename.ext</i>?&gt;</h5>


This command allows you to include text from another file into the output stream being generated by the template.

An optional operand of "copy" will ensure that the include file is included without conversion; otherwise, if the input and output file extensions are different, and are capable of conversion, the input file will be converted to the output file's format (for example, [Markdown][] or Textile can be converted to html).

Markdown conversion will be done using the [Pegdown][] processor, using the options for typographic conversions (as with SmartyPants) and table generation.

If converting from Markdown, then an optional operand of "nometa" will cause metadata lines to be skipped when generating the HTML output; otherwise, they will be included.

The filename may include variables, allowing you to tailor the included content based on one or more fields from your input data source. This is especially useful when you would like to include output from another template in the output generated by this template (effectively combining outputs from two separate templates into a single output). If an include file is not found, then it will simply be skipped and processing will continue, with a log message to note the event.

For any conversion resulting in HTML, a pseudo-tag of &lt;toc&gt; can be used to generate a table of contents based on following heading tags. An optional attribute of "from" can be used to specify the beginning of a range of heading levels to be included; an optional attribute of "through" or "thru" can be used to specify the end of a range of heading levels to be included. See the following example.

		<toc from="h2" thru="h4" />

<h5 id="ltifchange-gt">&lt;?ifchange ?&gt;</h5>


The ifchange command can be used to test a variable to see if it has a different value than it did on the last data record. If the variable has changed, then the following lines up to the closing endif command will be subjected to normal output processing. If the variable has not changed, then following lines will be skipped until the closing endif command is encountered. This command can be used to generate some special header information whenever a key field changes. Note that only one variable can be used with ifchange commands in one template file, since the value of any ifchange command is simply compared to the variable for the last ifchange command encountered.

<h5 id="ltif-gt">&lt;?if ?&gt;</h5>


The if command can be used to test a variable to see if it is non-blank. If the variable is non-blank, then the following lines up to the closing endif command will be subject to normal output processing. If the variable is blank, then following lines will be skipped until the closing endif command is encountered. In this case, the first and only operand would be the variable to be tested.

The if command can also be used to test a variable to compare it  to one or more constants. In this case, the command would have three or more operands: the name of the variable, a logical operator, and one or more values.

<ul>
<li>Variable Name. This can be a variable from the input data file, or a global variable (see the SET command above).</li>

<li>Logical Operator. Any of the following operators can be used.
<dl>
<dt>= or ==</dt>
<dd>This will return true if the variable is equal to any of the specified values.</dd>
<dt>&gt;</dt>
<dd>This will return true if the variable is greater than the value.</dd>
<dt>&lt;</dt>
<dd>This will return true if the variable is less than the value.</dd>
<dt>&gt;= or !&lt;</dt>
<dd>This will return true if the variable is greater than or equal to the value.</dd>
<dt>&lt;= or !&gt;</dt>
<dd>This will return true if the variable is less than or equal to the value.</dd>
</dl></li>
<li>Value. This can be a literal or a variable. The value can be a text string or an integer.</li>
</ul>

<h5 id="ltdefinegroup-igroupnumberi-gt">&lt;?definegroup <i>group-number</i> ?&gt;</h5>


This is the first of five commands that define key fields and then conditionally write output when there is a break on any of those fields. Up to ten group break fields can be defined. Each must be assigned a number from 1 to 10. Numbers should be assigned sequentially beginning with 1. Input data should normally be sorted by the same fields used in any definegroup commands. Definegroup commands should precede ifendgroup and ifnewgroup commands, and should generally be specified in ascending order by group number. The definegroup command has two operands.

* Group Number. This must be a number from 1 to 10. Numbers should be assigned sequentially beginning with 1. Lower-numbered groups are considered more major than higher-numbered groups, in the sense that lower-numbered group breaks will automatically trigger higher-numbered group breaks.

* Variable Name. This is the name of the key field variable.

<h5 id="ltifendgroup-igroupnumberigt">&lt;?ifendgroup <i>group-number</i>?&gt;</h5>


This is the second of the five group commands. Lines following this command and preceding the next group or endif command will be written to the output file at the end of a group of records sharing a common value for this key field. Ifendgroup commands should follow definegroup commands and precede ifnewgroup commands, and should generally be specified in <i>descending</i> order by group number. The ifendgroup command has one operand.

<ul>
<li>Group Number. The group number whose group-ending output lines follow.</li>
</ul>

Note that references to record variables within an IFENDGROUP block will retrieve the data from the record causing the break (i.e., the first record in the new group), not the last record in the group just ended. Use the SET command to save data in global variables if you need to later access it when a group break has been detected.

<h5 id="ltifendlist-igroupnumberigt">&lt;?ifendlist <i>group-number</i>?&gt;</h5>


This is the third of the five group commands. Lines following this command and preceding the next group or endif command will be written to the output file at the end of a list of records containing this key field. The end of a list will be triggered by a change in key values at the next higher level, or by a record containing blanks at the current group level. Ifendlist commands should follow ifendgroup commands and precede ifnewlist commands, and should generally be specified in <i>descending</i> order by group number. The ifendlist command has one operand. Note that the ifendlist and ifnewlist commands can generally be used to insert HTML tags to end a list and begin a list.

<ul>
<li>Group Number. The group number whose list-ending output lines follow.</li>
</ul>

Note that references to record variables within an IFENDLIST block will retrieve the data from the record causing the break (i.e., the first record in the new group), not the last record in the group just ended. Use the SET command to save data in global variables if you need to later access it when a list break has been detected. Note that the ifendlist and ifnewlist commands can generally be used to insert HTML tags to end a list and begin a list.

<h5 id="ltifnewlist-igroupnumberigt">&lt;?ifnewlist <i>group-number</i>?&gt;</h5>


This is the fourth of the five group commands. Lines following this command and preceding the next group or endif command will be written to the output file at the beginning of a new list of records at this group level. Ifnewlist commands should follow definegroup, ifendgroup and ifendlist commands, should precede ifnewgroup commands, and should generally be specified in <i>ascending</i> order by group number. The ifnewlist command has one operand.

<ul>
<li>Group Number. The group number whose list-beginning output lines follow.</li>
</ul>

<h5 id="ltifnewgroup-igroupnumberigt">&lt;?ifnewgroup <i>group-number</i>?&gt;</h5>


This is the fifth of the five group commands. Lines following this command and preceding the next group or endif command will be written to the output file at the beginning of a group of records sharing a common value for this key field. Ifnewgroup commands should follow all other group commands, and should generally be specified in <i>ascending</i> order by group number. The ifnewgroup command has one operand.

<ul>
<li>Group Number. The group number whose group-beginning output lines follow.
</ul>

<h5 id="ltelsegt">&lt;?else?&gt;</h5>


The else command terminates the scope of its corresponding if, ifchange, ifendgroup or ifnewgroup command, and applies the opposite logical condition to the following template lines.

<h5 id="ltendifgt">&lt;?endif?&gt;</h5>


The endif command terminates the scope of its corresponding if, ifchange, ifendgroup or ifnewgroup command.

<h5 id="ltloopgt">&lt;?loop?&gt;</h5>


This command indicates the end of the code that will be written out once per data record. Lines after the loop command will be written out once per output file created, at the end of each file.

<h3 id="script-files">Script Files</h3>


The script file is a tab-delimited text file, and you can edit one using your favorite tool for such things. You can create one completely from scratch if you want, but it usually easiest to record one first, and then edit the results.

The script file has the following columns.

1. module &#8212; This names the tab to process the command.
2. action &#8212; This names the action to be taken, and usually corresponds to a button on a tab.
3. modifier &#8212; This supplies a value that modifies the intent of the command in some way.
4. object &#8212; The name of the thing to be acted upon.
5. value &#8212; A value that the object is to be set equal to.

Following is a complete list of all the allowable forms for script commands. Constants are displayed in normal type. Variables appear in italics. Blank cells indicate fields that are not applicable to a particular command, and therefore can be left blank or empty. Forward slashes are used to separate alternate values: only one of them must appear (without the slash) in an actual script command. Most of the values correspond directly to equivalent buttons on the tabs, as described elsewhere in this user guide. The one non-intuitive value is probably the Filter values for the andor object: True sets &#8220;and&#8221; logic on, while False sets &#8220;or&#8221; logic on.

Note that file names may begin with the literal &#8220;PATH&#8221; surrounded by &#8220;#&#8221; symbols. When recording a script, the program will automatically replace the path containing the script file with this literal. In addition, upwards references from the location of the script file will be indicated by two consecutive periods for each level in the folder hierarchy. On playback, the reversing decoding will occur. In effect this means that files within the same path structure as the script file, or a sub-folder, will have their locations identified relative to the location of the script file. Files on a completely different path will have their locations identified with absolute drive and path information. The overall effect of this is to make a script file, along with the input files referenced by the script file, portable packages that can be moved from one location to another, or executed with different drive identifiers, and still execute correctly. Normally all of this will be transparent to the user.

Similarly, the literal &#8220;\#TEMPLATES\#&#8221; will be used as a placeholder for the path to the current template library, as set with the Set Template Library button on the Template tab.

The &#8220;epubin&#8221; and &#8220;epubout&#8221; actions require some additional description, since they have no correlates on the Script tab just described. The former identifies a directory containing the contents of an e-book in the EPUB format; the latter identifies the &#8220;.epub&#8221; file to be created using that directory as input.

<table class="shaded" border="0" cellspacing="2" cellpadding="4">
    <tr class="shaded">
        <th class="shaded" width="70" align="center">module</th>

        <th class="shaded" width="70" align="center">action</th>

        <th class="shaded" width="210" align="center">modifier</th>

        <th class="shaded" width="70" align="center">object</th>

        <th class="shaded" width="140" align="center">value<br /></th>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">url</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">url name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">file</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">dir</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">directory name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">html1</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">html2</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">html3</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">xml</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">xls</td>

        <td class="shaded" align="center">merge/blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">epubin</td>

        <td class="shaded" align="center">dir</td>

        <td class="shaded" align="center">blank</td>

        <td class="var" align="center">directory name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">input</td>

        <td class="shaded" align="center">epubout</td>

        <td class="shaded" align="center">file</td>

        <td class="shaded" align="center">blank</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">sort</td>

        <td class="shaded" align="center">add</td>

        <td class="shaded" align="center">Ascending/ Descending</td>

        <td class="var" align="center">field name</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">sort</td>

        <td class="shaded" align="center">clear</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">sort</td>

        <td class="shaded" align="center">set</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">params</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">combine</td>

        <td class="shaded" align="center">add</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">dataloss</td>

        <td class="var" align="center">integer</td>
    </tr>

    <tr class="shaded">
        <td colspan="5" align="right">0 = no data loss, 1 = one record overrides, 2 = allow concatenation</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">combine</td>

        <td class="shaded" align="center">add</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">precedence</td>

        <td class="var" align="center">integer</td>
    </tr>

    <tr class="shaded">
        <td colspan="5" align="right">+1 = later overrides earlier, -1 = earlier overrides later</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">combine</td>

        <td class="shaded" align="center">add</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">minnoloss</td>

        <td class="var" align="center">integer</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">combine</td>

        <td class="shaded" align="center">set</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">params</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">filter</td>

        <td class="shaded" align="center">set</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">andor</td>

        <td class="shaded" align="center">True/ False</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">filter</td>

        <td class="shaded" align="center">add</td>

        <td class="shaded" align="center">operator</td>

        <td class="var" align="center">field name</td>

        <td class="var" align="center">comparison value</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">filter</td>

        <td class="shaded" align="center">clear</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">filter</td>

        <td class="shaded" align="center">set</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">params</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">output</td>

        <td class="shaded" align="center">set</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">usedict</td>

        <td class="shaded" align="center">True/ False</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">output</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">template</td>

        <td class="shaded" align="center">open</td>

        <td class="shaded" align="center">file</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="var" align="center">file name</td>
    </tr>

    <tr class="shaded">
        <td class="shaded" align="center">template</td>

        <td class="shaded" align="center">generate</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>

        <td class="shaded" align="center">&nbsp;</td>
    </tr>
</table>

<h3 id="file-directories">File Directories</h3>


The following special column headings are predefined for file directory entries.


Sort Key
:    Used for sorting the directory entries alphanumerically, without regards to case (upper or lower) or punctuation. The complete file path will appear here, all in lower-case, with spaces between file directories, and spaces replacing punctuation.

Folder1 through Foldern
:    Folder1 will contain the first sub-directory name, within the specified input directory, if this entry came from a sub-directory. Folder1 through Foldern columns will appear, where "n" is the maximum directory depth - 1 (since a maximum directory depth of 1 indicates no sub-directory explosion at all).

Path
:    The series of folders, with each folder/directory separated from the previous one with a slash, between the top level folder selected and the file name identified later in this row.

File Name
:    Name of the file or sub-directory.

Type
:    The type of directory entry: "File" or "Directory".

English Name
:    A file name with standardized spacing, without punctuation, and without a file extension.

File Name w/o Extension
:    The file name without its extension.

File Ext
:    The file extension, if a file and if it has one.

File Size
:    Size of the file, in bytes.

Last Mod Date
:    Date of last change to the file, in "yyyy-mm-dd" format.

Last Mod Time
:    Time of last change to the file, in "hh:mm:ss zzz" format, where "hh" is a 24-hour (military) hour, "mm" is minutes, "ss" is seconds and "zzz" is an abbreviation of the time zone.

Word1 through Word5
:    The file name, without directories and without extension, will be broken down into up to five separate fields, using punctuation, spaces, and case transitions to demarcate words.

<h3 id="markdown-metadata">Markdown Metadata</h3>


The following special column headings are predefined for metadata gathered from [Markdown][] files.

Metadata is provided in the spirit of, although not in complete conformance with, the [MultiMarkdown][] syntax. That is, special lines are expected at the top of the file, each line starting with a key, followed by a colon and then a value, as in the following example.

> Title:  Markdown Metadata
> Author: Herb Bowie
> Tags:   Java, Documentation
> Date:   July 4, 2012

Note that there are two variants of this file type, one simply labeled "Markdown Metadata" and the other labeled "Markdown Metadata Tags". The first has only one row per Markdown file, and identifies all tags for that file. The second has one row per tag per file, and identifies only one tag at a time. The first file format would normally be used for a simple index of the files, while the second format would be used to generate an index by tag.


Complete Path
:    The complete path to the file, including all directories, plus the file name and extension.

Base Path
:    The folder path to the top directory containing the markdown files included in the list.

Local Path
:    The series of folders, with each folder/directory separated from the previous one with a slash, between the top level folder selected and the file name identified later in this row.

Depth
:    0 for files at the top level of the extract, 1 for files within the next set of folders, and so forth.

File Name
:    Name of the file, including its file extension.

File Name Base
:    The file name without its extension, and without the period preceding the extension.

File Ext
:    The file extension, without the preceding period.

Last Mod Date
:    Date of last change to the file, in "yyyy-mm-dd hh:mm:ss TMZ" format.

File Size
:    Approximate size of the file, in bytes.

Title
:    The title can come from a number of different sources. If the file does not contain a title, then the file name will be used as the title, after removing its extension, and normalizing the case and word separators to leading caps and a space, respectively. If the file contains a level 1 heading, denoted by an initial line followed by a line of simulated double underlines (four or more equal signs), per the [Markdown][] specification, then this will be used as the title. And finally, if the metadata at the top of the file contains a line starting with "Title:", then the remainder of the line will be used as the title.

Author
:    If the file title is followed by a byline, starting with the word "by", then the remainder of the line will be used as the author. Alternatively, if the metadata at the top of the file contains a line starting with "By", "Author", or "Creator", then the remainder of the line will be used as the author.

Date
:    The date associated with the file, as established by a metadata line at the top of the file starting with "Date:".

Breadcrumbs
:    A string that can be used to provide "breadcrumbs" for this page.

Tags
:    These would be the tags provided by a metadata line starting with the key of "Tags", "Keywords" or "Category". Multiple tags may be provided, each separated from the other by a comma, with or without spaces. Tags may be nested as well, with a period separating each level, without any spaces.

Linked Tags
:    Tags with links for each tag.

Tag
:    An individual tag.

<h3 id="pspub-outline">PSPub Outline</h3>


This is a special text file format to allow easy representation of an outline structure. Indention is used to indicate outline levels. The first character of the first line is assumed to be the "bullet" character that will subsequently identify all list items. Blank lines indicate paragraph breaks. A line beginning with "a:" (or simply with "http:") identifies a URL to be associated with the preceding outline item.

This structure is then converted to a columnar data structure, with one row for each paragraph/list item, and with the following columns.

sectionnumber1 through sectionnumber10
:    The sectionnumber1 column contains the current highest level "section", or item number, in the overall structure. These numbers are assigned internally, beginning with 1, and are not taken from the input file.

headingflag
:    Set to "true" if this item is a bulleted item, otherwise set to "false".

level
:    Starting with 1, the depth of the current item in the overall structure.

text
:    The body of the item or paragraph.

link
:    The URL, if any, supplied for this item or paragraph.

<h3 id="club-planner-files">Club Planner Files</h3>


Information about events and other items for club consideration are stored in text files, with one event/item per file.

Note that there are two variants of this file, one labeled "Club Planner" and the other labeled "Club Notes". The first has only one row per event, while the second has one row per note header in the Notes field for each event.

<h4 id="folder-structure">Folder Structure</h4>


The text files should be organized into folders whose names provide additional data about the items.

The event files should be placed into something like the following folder structure.

<ul>
	<li><em>Club Name 2011 - 2012</em> -- A folder containing all events for the club operating year, with both starting and ending year included in the folder name.
		<ul>
			<li><em>Events</em> -- A folder containing all the event info. Other folders at this level may be used to store other information. Folders at the next level indicate status, and the following folder names are suggested.
				<ul>
					<li><em>Archive</em> -- For events that have already occurred and are no longer of current interest.</li>
					<li><em>Board</em> -- Items of interest to the board of directors.</li>
					<li><em>Current</em> -- Items of current interest.</li>
					<li><em>Future</em> -- Items being considered for the future, but not yet of current interest.</li>
					<li><em>News</em> -- Items to be reported in the next Email newsletter.</li>
					<li><em>Save</em> -- Items being saved for possible consideration.</li>
				</ul>
			</li>
		</ul>
	</li>
</ul>

The text files themselves consist of a series of field names, each followed by a colon, and then followed by the field data on the same and/or successive lines.

<h4 id="template">Template</h4>


The following lines can be used as a template for creating a Club Planner event.

		/*
			Fill out this form for a new event, then save it with the name of the event. Use a plain
			text editor to complete the form. Fill out as many fields as you can, and leave the
			rest blank. Note that text such as this, between a slash asterisk and an asterisk slash,
			are comments and not content. Text following a pair of slashes on a line are also
			treated as comments. Comments may be deleted once the form is filled out, or left in.
		*/
		Type:
		/*
			Type should be one of the following:
			Active / Board / Career Networking / Close Meeting /
			Collaboration w/Other Clubs  / Communication / Community / Culture /
			Family / Finance  / Membership / Open Meeting / Organization  /
			Scholarship  / Social / Sports / Student Connections
		*/
		What:                           // Enter a short title for the event
		When:                           // e.g., Sat Mar 31 at 8 PM
		Where:                          // Name of the venue, street address, city state and zip
		Who:                            // Name of primary contact at email address
		Why:                            // Justification for approving this event
		Teaser:                         // Two or three sentences hitting the high points
		Blurb:                          // One or more paragraphs with additional details
		Cost:                           // e.g., $43 per person
		Purchase:                       // Instructions for purchasing tickets
		Tickets:                        // How purchasers will receive tickets
		Quantity:                       // e.g., Block of 20 seats
		Planned Income:                 // e.g., $43 x 20 = $860
		Planned Expense:                // e.g., $43 x 20 = $860
		Planned Attendance:             // How many people do we expect to participate?
		Actual Income:
		Actual Expense:
		Actual Attendance:
		Recap:                          // How did the event go? Any lessons learned?
		ID:                             // Enter the article ID from our Web site
		Link:                           // URL with more info about the event
		Venue:                          // Link with more info about the venue
		Image:                          // URL for an image about the event
		News Image:                     // URL for an image to use in the email newsletter
		Discuss:                        // Points to be discussed at our next board meeting
		Notes:
		/*
		Copy and paste notes about the event. Precede each note with a header line indicating
		who it came from, on what date and via what medium. For example:

		-- Will Dorchak, Feb 16, via email

		Follow the header with a blank line, and the the text of the note, using blank lines
		to separate paragraphs.

		*/

<h4 id="input-fields">Input Fields</h4>


Field names and definitions follow.

**Type**: This is the general type of the event. The following values are suggested.

* Active -- An active event, such as a hike.
* Board -- An item for consideration by the board, or pertaining to the board.
* Career Networking -- Professional advancement.
* Close Meeting -- An item to appear at the end of the board meeting agenda.
* Collaboration w/Other Clubs -- Worked in common with other clubs, such as the other Big Ten clubs in the area.
* Communication -- An item having to do with club communications, such as our Web site, Facebook page or email newsletters.
* Community -- A community service event.
* Culture -- A cultural event.
* Family -- An event intended for families with children.
* Finance -- An item having to do with club finances.
* Membership -- An event planned to promote membership in the club.
* Open Meeting -- An item to appear at the beginning of the board meeting agenda.
* Organization -- An event or item having to do with the organization of the club.
* Scholarship -- An event having to do with the club's scholarship fund and the scholarships awarded to deserving students.
* Social -- A purely social event.
* Sports -- An event having to do with athletics.
* Student Connections -- Prospective or current U-M students hailing from the greater Seattle area.

**What**: A brief descriptive title for the event.

**When**: An indication of the date and time that the event will be held, in a format emphasizing human readability. This need not be a complete date. It need not and generally should not contain the year, since this can be inferred from the operating year identified in the higher level folder. If an exact date is known, then this field should generally start with a three-character abbreviation for the day of the week. Three-character abbreviations for the month are also recognized and encouraged. Following are perfectly good examples of dates.

* Apr
* Sat May 5
* Thu Mar 25 5:30 - 7:30 PM

**Where**: The location of the event, including the name of the venue and its address.

**Who**: Who is assigned to plan, coordinate and host the event. Can include multiple names. Can include email addresses and phone numbers.

**Why**: Why does the club think it would be a good idea to host the event? Why do we think this would be an event deserving of the club's resources?

**Teaser**: One to three sentences describing the event. Not intended to provide complete information, but intended to pique the reader's interest and motivate him to read further.

**Blurb**: Additional information about the event. Need not repeat information in the teaser, and need not repeat additional event details available from other fields, such as When and Where. This field can contain multiple paragraphs, separated by blank lines. [Markdown][] formatting will be applied to this section.

**Cost**: The cost per person to attend the event. If the event is free, then leave this field blank.

**Purchase**: Instructions on how to purchase tickets to the event, if any.

**Tickets**: For purchasers, information on how they are to receive the tickets.

**Quantity**: Number of seats or tickets available for the event; maximum number of attendees.

**Planned Income**: The amount of money the club plans to receive for the event. For this and the following dollar amount fields, multiple dollar figures may be interspersed with descriptive words. "$20 x 40" will result in a planned income of $800.00, for example.

**Planned Expense**: The amount of money planned/budgeted to be spent on the event.

**Planned Attendance**: The number of attendees built into the club's planning assumptions.

**Actual Income**: The club's actual income for the event.

**Actual Expense**: The club's actual expenses for the event.

**Actual Attendance**: The actual number of people who attended the event.

**Recap**: A brief summary of how the event went. Can include lessons learned from the event.

**ID**: After the event has been added to the club web site, the ID assigned to the page by the Content Management System should be entered here. This might be identified in the URL for the event as the "articleid", as in "articleid=17", meaning that an ID of "17" should be entered here.

**Link**: A URL pointing to a Web page with more information about the event.

**Venue**: A URL pointing to a Web page with more information about the venue for the event.

**Image**: A URL pointing to an image that can be used to help advertise the event.

**News Image**: A URL pointing to an image suitable for use in our newsletter.

**Discuss**: Identification of any issues to be discussed at an upcoming club meeting.

**Notes**: One or more blocks of text with information about the event. This field can contain multiple paragraphs, separated by blank lines. [Markdown][] formatting will be applied to this section.

Each block of text should be preceded by a line similar to the following example.

    -- AAUM on Feb 21 via email

Note that each such header line contains the following elements:

* Two hyphens and a space
* Identification of the source of the following information.
* The date on which the information was communicated.
* The means by which the information was communicated.

<h4 id="calculated-fields">Calculated Fields</h4>


The following fields will be calculated and placed in the resulting list.

**Year**: The operating year for the event, if available from one of the enclosing folders (see section above on folder structure).

**Status**: The event's status, based on its immediately enclosing folder name.

**Seq**: This is intended as a sort key, to create an agenda for a club meeting. A type of "Open Meeting" will result in a sequence of 1; a type of "Finance" will result in a sequence of 2; a type of "Communication" will result in a sequence of 8; a type of "Close Meeting" will result in a sequence of 9; any other type will result in a sequence of 5.

**YMD**: This will contain the event's date, or as much of it as is known, in a predictable "yyyy-mm-dd" format that can be used for sorting. The information here is calculated based on the club's operating year and the *When* field.

**File Name**: The name of the file, without any folder information, and without a file extension.

**Blurb as HTML**: The blurb field, converted from [Markdown][] to HTML, suitable for insertion into a Web page or email.

**Over/Under**: The amount by which the club's actual income and expenses differed from the club's planned income and expenses. A positive amount indicates the club did better than expected, with lower expenses and/or higher income than planned; a negative amount means the club did worse than expected.

**Finance Projection**: The projected impact of this event on the club's finances, calculated based on the planned and actual income and expenses. A negative number decreases the club's funds, while a positive number increases them. This number is calculated using the planned values if no actuals are available, or the actual values once they are entered.

**Short Date**: This is a short, human-readable form of the date. It includes a three-letter abbreviation for the day of the week, a three-letter abbreviation for the month, and the 2-digit day of the month.

**Notes as HTML**: The entire notes field, converted from [Markdown][] to HTML, suitable for insertion into a Web page or email.

<h4 id="additional-fields-in-club-notes-file-format">Additional Fields in Club Notes File Format</h4>


The following additional fields are extracted for the Club Notes file format, with one row for each note header.

**Note For**: The date of the note, in yyyy-mm-dd format, suitable for sorting and/or filtering, as extracted from the note header.

**Note From**: The source of the note, as extracted from the note header.

**Note Via**: The medium by which the note was communicated, as extracted from the note header.

**Note**: The text of the note, following the note header.

**Note as HTML**: The text of the note, converted from [Markdown][] to HTML, suitable for insertion into a Web page or email.

[java]:       http://www.java.com/
[pspub]:      http://www.powersurgepub.com/
[downloads]:  http://www.powersurgepub.com/downloads.html
[osd]:		  http://opensource.org/osd
[gnu]:        http://www.gnu.org/licenses/
[apache]:	     http://www.apache.org/licenses/LICENSE-2.0.html
[markdown]:		http://daringfireball.net/projects/markdown/
[multimarkdown]:  http://fletcher.github.com/peg-multimarkdown/

[wikiq]:     http://www.wikiquote.org
[support]:   mailto:support@powersurgepub.com
[fortune]:   http://en.wikipedia.org/wiki/Fortune_(Unix)
[opml]:      http://en.wikipedia.org/wiki/OPML
[textile]:   http://en.wikipedia.org/wiki/Textile_(markup_language)
[pw]:        http://www.portablewisdom.org

[store]:     http://www.powersurgepub.com/store.html

[pegdown]:   https://github.com/sirthias/pegdown/blob/master/LICENSE
[parboiled]: https://github.com/sirthias/parboiled/blob/master/LICENSE
[Mathias]:   https://github.com/sirthias

[club]:         clubplanner.html
[filedir]:      filedir.html
[metamarkdown]: metamarkdown.html
[template]:     template.html

[mozilla]:    http://www.mozilla.org/MPL/2.0/


