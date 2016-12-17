<!-- Generated using template product-user-guide-template.mdtoc -->
<!-- Generated using template product-custom-user-guide-template.md -->
<h1 id="pstextmerge-user-guide">PSTextMerge User Guide</h1>


Version: 4.80

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
    </li>
  </ul>

</div>


<h2 id="introduction">Introduction</h2>


PSTextMerge merges lists of tabular data into text templates to create fully populated text files. The output files can be Web pages, XML files, RSS Feeds or any other varieties of text files. The input data can be obtained from a tab-delimited data file, or directly from a Microsoft Excel (&#8216;.xls&#8217;) spreadsheet, or from a number of other sources. You can record and easily play back recorded scripts, making it easy to re-generate your output text files when your data changes.

The input data can be sorted and filtered before being used to generate output, and these operations can be scripted as well.

Tab-delimited files can be easily exported from most spreadsheets, databases, address book, and many other programs, and then used as input to this application.

PSTextMerge can also perform other operations with tabular data, extracting and merging data from multiple bookmarks files, address books, and other formats.

PSTextMerge was formerly known as TDF Czar.


<h2 id="getting-started">Getting Started</h2>


<h3 id="system-requirements">System Requirements</h3>


PSTextMerge is written in Java and can run on any reasonably modern operating system, including Mac OS X, Windows and Linux. PSTextMerge requires a Java Runtime Environment (JRE), also known as a Java Virtual Machine (JVM). The version of this JRE/JVM must be at least 6. Visit [www.java.com](http://www.java.com) to download a recent version for most operating systems. Installation happens a bit differently under Mac OS X, but generally will occur fairly automatically when you try to launch a Java app for the first time.

Because PSTextMerge may be run on multiple platforms, it may look slightly different on different operating systems, and will obey slightly different conventions (using the CMD key on a Mac, vs. an ALT key on a PC, for example).

<h3 id="rights">Rights</h3>


PSTextMerge Copyright 1999 - 2016 by Herb Bowie

PSTextMerge is [open source software](http://opensource.org/osd). Source code is available at [GitHub](http://github.com/hbowie/pstextmerge).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at:
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


Most of PSTextMerge's user interface elements are laid out in a series of tabs that proceed in a natural progression from left to right. Most of the functions performed within these tabs may also be triggered by their corresponding script commands.

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

See the [Club Planner specification](#club-planner-file-formats) for details.

<h5 id="excel-spreadsheet">Excel Spreadsheet</h5>


Setting this option will cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an '.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first row will be expected to contain column headings, with data in following rows. The first blank row will terminate the list. Each row in the spreadsheet (after the first, containing headings) will be treated as a data record, and each column will be treated as a separate field. Columns containing hyperlinks will also generate fields containing the hyperlinks, and named by appending "link" to the column heading. For example, a column named "ISBN" could have its content accessed with the variable "isbn" and its link accessed with the variable "isbnlink".

<h5 id="excel-table">Excel Table</h5>


Setting this option will also cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an '.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first blank row will terminate the list. With this option, each row in the spreadsheet will be returned as a single data field, identified by a variable name of "Table Row". The data returned will include beginning and ending td tags for each column, with appropriate formatting and cell dimensions and hyperlinks, mimicking the format of the Excel spreadsheet as closely as possible. The data returned does not include beginning or ending tr tags.

<h5 id="file-directory">File Directory</h5>


When you specify a file directory as your data source, each entry in that directory will then be treated as if it were a single record, or line, or row, from a data file. The "Maximum Directory Depth" field below will control the depth to which sub-directories are read.

See the [File Directory specification](#file-directory-format) for details.

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

See the [Markdown Metadata specification](#markdown-metadata-file-format) for details.

<h5 id="notenik-notes">Notenik Notes</h5>


Reads a folder of basic [Notenik][] Notes, containing only the basic fields of Title, Link, Tags and Body.

<h5 id="notenik-notes-plus">Notenik Notes Plus</h5>


Reads a folder of extended [Notenik][] Notes, containing the basic fields plus others.

<h5 id="notenik-general">Notenik General</h5>


Reads a folder of [Notenik][] Notes containing any fields.

<h5 id="notenik-index">Notenik Index</h5>


Reads a folder of [Notenik][] Notes containing Index fields, and builds a term index for the collection based on those fields.

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

<h4 id="tags-explosion">Tags Explosion</h4>


If this box is checked, then a field named "Tags" will be broken down into individual tags, and one row/record will be created for each separate tag embedded within the Tags field, with each individual tag being placed in a new field named "Tag" (singular rather than plural).

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

<h5 id="set-web-root">Set Web Root</h5>


This allows the root folder of the output web directory to be set. Relative variables will then be replaced based on the depth of a file relative to this root directory.

<h5 id="set-template-library">Set Template Library</h5>


PSTextMerge supports the concept of a central template library where you can store reusable templates. The initial location for this folder is the &#8220;templates&#8221; folder within the PSTextMerge Folder that comes as part of the software distribution. However, this button can be used to allow you to select another folder as your template library. After installation of PSTextMerge, you may wish to copy the templates folder to another location, perhaps within your home folder, or your documents folder, and then use this button to specify that new location.

<h5 id="open-template">Open Template</h5>


This button allows you to specify the location and name of the template file you wish to use. (This file must have previously been created using a text editor.) This function may also be invoked via the Template/Open Menu item or with the T shortcut key.

<h5 id="open-from-library">Open From Library</h5>


This button also opens a template file, but uses your template library as the starting location.

<h5 id="generate-output">Generate Output</h5>


This button processes the template file you have selected, and creates whatever output file(s) you have specified in the template file.  The function may also be invoked via the Template/Generate Menu item or with the G shortcut key.

See the [Template File Format specification](#template-file-format) for details.

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

<?include "../mdlib/template-file-spec.md" ?>

<?include "../mdlib/script-file-spec.md" ?>

<?include "../mdlib/filedir-file-spec.md" ?>

<?include "../mdlib/metamarkdown-file-spec.md" ?>

<?include "../mdlib/outline-file-spec.md" ?>

<?include "../mdlib/clubplanner-file-spec.md" ?>


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
[notenik]:    http://www.powersurgepub.com/products/notenik/index.html


