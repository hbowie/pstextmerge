PSTextMerge User Guide
======================

<h2 id="toc">Table of Contents</h2>

<ul id="toc-div">
<li><a href="#introduction">Introduction</a></li>
<li><a href="#sysrqmts">System Requirements</a></li>
<li><a href="#rights">Rights</a></li>
<li><a href="#installation">Installation</a></li>
<li><a href="#command-line-parms">Command Line Parameters</a></li>
<li><a href="#gui">User Interface</a></li>
<li><a href="#input">Input</a></li>
<li><a href="#view">View</a></li>
<li><a href="#sort">Sort</a></li>
<li><a href="#filter">Filter</a></li>
<li><a href="#output">Output</a></li>
<li><a href="#template">Template</a></li>
<li><a href="#script">Script</a></li>
<li><a href="#logging">Logging</a></li>
<li><a href="#about">About</a></li>
<li><a href="#help-menu">Help Menu</a></li>
</ul>

<h2 id="introduction">Introduction</h2>

PSTextMerge merges lists of tabular data into text templates to create fully populated text files. The output files can be Web pages, XML files, RSS Feeds or any other varieties of text files. The input data can be obtained from a tab-delimited data file, or directly from a Microsoft Excel (&#8216;.xls&#8217;) spreadsheet, or from a number of other sources. You can record and easily play back recorded scripts, making it easy to re-generate your output text files when your data changes.

The input data can be sorted and filtered before being used to generate output, and these operations can be scripted as well.  

Tab-delimited files can be easily exported from most spreadsheets, databases, address book, and many other programs.

PSTextMerge can also perform other operations with tabular data, extracting and merging data from multiple bookmarks files, address books, and other formats. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="sysrqmts">System Requirements</h2>

PSTextMerge is written in Java and can run on any reasonably modern operating system, including Mac OS X, Windows and Linux. PSTextMerge requires a Java Runtime Environment (JRE), also known as a Java Virtual Machine (JVM). The version of this JRE/JVM must be at least 6. Visit [www.java.com][java] to download a recent version for most operating systems. Installation happens a bit differently under Mac OS X, but generally will occur fairly automatically when you try to launch a Java app for the first time.  

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="rights">Rights</h2>

PSTextMerge Copyright &copy; 1999 - 2013 Herb Bowie

As of version 4.10, PSTextMerge is [open source software][osd]. 

Licensed under the Apache License, Version 2.0 (the &#8220;License&#8221;); you may not use this file except in compliance with the License. You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an &#8220;AS IS&#8221; BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

PSTextMerge also incorporates or adapts the following open source software libraries. 

* BrowserLauncher2 &#8212; Copyright 2004 - 2007 Markus Gebhard, Jeff Chapman, used under the terms of the [GNU General Public License][gnu]. 

* JExcelAPI &#8212; Copyright 2002 Andrew Khan, used under the terms of the [GNU General Public License][gnu]. 

* parboiled  &#8212; Copyright 2009-2011 Mathias Doenitz, used under the terms of the [Apache License, Version 2.0][apache]. 
	
* pegdown &#8212; Copyright 2010-2011 Mathias Doenitz, used under the terms of the [Apache License, Version 2.0][apache].

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="installation">Installation</h2>

Download the latest version from [PowerSurgePub.com][downloads]. Decompress the downloaded file. Drag the resulting file or folder into the location where you normally store your applications. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="command-line-parms">Command Line Parameters</h2>

PSTextMerge has two arguments which allow the program to be run completely from the command line, without any graphical user interface. The first argument is specified as &#8220;-q&#8221; and tells the program to run in &#8220;quiet mode&#8221; &#8212; that is, without any GUI. When this option is specified, the log file will be written to disk, as described in the Logging section. 

The second argument specifies the location and name of a script file to be played, the location being relative to the PSTextMerge program. 

Together, these two options allow PSTextMerge to be executed from a script or batch file, without any user interaction. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="gui">User Interface</h2>

Most of PSTextMerge&#8217;s user interface elements are laid out in a series of tabs that proceed in a natural progression from left to right. Most of the functions performed within these tabs may also be triggered by their corresponding script commands.

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="input">Input</h2>

This function allows PSTextMerge to populate a list to be used in later processing. The following controls are available.  

### Open Input ###

Clicking on this button will allow you to select the file or directory to be input.  Make sure that all of the following input options are properly set before pressing this button. This function can also be invoked via the File/Open Menu item or with the O shortcut key.  

Note that, when a directory (aka folder) is selected, then all eligible files within that directory will be processed, and the resulting list will consist of the concatenation of all the rows of data generated from all of the eligible files. 

### Type of Data Source ###

The default input is a tab-delimited text file, which may have been saved or exported from a spreadsheet, database, address book or other tabular data source. But other options are available as well. Select the one you want from the drop-down list.  

Following are descriptions of each of the currently available options. 

#### Tabbed Data File ####

This is a text file. Each line in the file must use tabs (or commas, if the file extension is &#8220;.csv&#8221;) to separate each field, or column, in the line. The first line of the file must contain column headings.  

#### Club Planner ####

This is a text file format that can be used to help plan events for a club, such as an alumni club. 

See the [Club Planner specification][club] for details.

#### Excel Spreadsheet ####

Setting this option will cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an &#8216;.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first row will be expected to contain column headings, with data in following rows. The first blank row will terminate the list. Each row in the spreadsheet (after the first, containing headings) will be treated as a data record, and each column will be treated as a separate field. Columns containing hyperlinks will also generate fields containing the hyperlinks, and named by appending &#8220;link&#8221; to the column heading. For example, a column named &#8220;ISBN&#8221; could have its content accessed with the variable &#8220;isbn&#8221; and its link accessed with the variable &#8220;isbnlink&#8221;. 

#### Excel Table ####

Setting this option will also cause the program to treat the input file as a Microsoft Excel spreadsheet, in Excel 97 - 2004 format (with an &#8216;.xls&#8217; extension). The first or only worksheet (tab) will be accessed. The first blank row will terminate the list. With this option, each row in the spreadsheet will be returned as a single data field, identified by a variable name of &#8220;Table Row&#8221;. The data returned will include beginning and ending td tags for each column, with appropriate formatting and cell dimensions and hyperlinks, mimicking the format of the Excel spreadsheet as closely as possible. The data returned does not include beginning or ending tr tags.

#### File Directory ####

When you specify a file directory as your data source, each entry in that directory will then be treated as if it were a single record, or line, or row, from a data file. The &#8220;Maximum Directory Depth&#8221; field below will control the depth to which sub-directories are read. 

See the [File Directory specification][filedir] for details. 

#### HTML Bookmarks using Lists ####

Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain bookmarks, using nested lists to indicate a folder hierarchy. A table will be created with the following columns. Many bookmark managers, including popular browsers, can save or export bookmarks into this format.

<dl>
<dt>Category 1 through 6</dt>
	<dd>Any text found outside of an actual hyperlink will be placed in one of these category columns, depending on the nesting level of the list it was found in.</dd>
<dt>Description</dt>
	<dd>The text that was hyperlinked.</dd> 
<dt>E-mail</dt>
	<dd>If the hyperlink starts with &#8220;mailto:&#8221;, then it will be interpreted as an e-mail address and placed in this column. </dd>
<dt>Web Site</dt>
	<dd>If the hyperlink does not start with &#8220;mailto:&#8221;, then the URL will be placed in this field. </dd>
</dl>

#### HTML Bookmarks using Headings ####

Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain bookmarks, using varying levels of heading tags to indicate a folder hierarchy. A table will be created with the same columns as the HTML Bookmarks using Lists, described above. The contents of the varying heading levels will be placed in Category 1 through 6.

#### HTML Table ####

Setting this option will cause the program to treat the input file as HTML. It will expect the file to contain a table, using table, tr, th and/or td tags. The first row in the table will be expected to contain column headings, with following rows containing the corresponding data. 

Note that many HTML pages are laid out using tables, not to present columns and rows of data, but to format the page in a desired fashion. Tables containing column headings and following data may often be embedded in such layout tables. In order to try to separate the two, PSTextMerge in this mode will look for the first table cell (within th or td tags) containing 1 to 40 characters of text, with the idea that text beyond either extreme is not likely to be a true column heading. During this search for the first cell of a data table, table cells with colspan or rowspan parameters greater than 1 will also be ignored. 

In some cases, however, you may need to edit the prospective input file with your favorite text editor, and delete lines preceding and following the table containing the data you are interested in, saving the resulting file as a separate file to be input to PSTextMerge. If a true first column heading is longer than 40 characters, then you will also need to reduce its length to the acceptable 1-40 range. 

Note that for any HTML input option, character entities found within HTML text will be translated to their equivalent ASCII characters. For now, translation is only provided for characters that are not platform-specific: &#8220;&nbsp;&#8221; (non-breaking space), &#8220;&lt;&#8221; (less than sign),  &#8220;&gt;&#8221; (greater than sign),  &#8220;&amp;&#8221; (ampersand) and &#8220;&quot;&#8221; (double quotation marks). Entities may be specified using mnemonics or their numeric equivalents.

#### HTML Links ####

Setting this option will cause the program to treat the input file as HTML. It will extract all of the links within this file, identifying the from page, the link type, and to file, for each link found. Link type may be linkhref, imgsrc, or ahref, for the corresponding tag and attribute combinations. 

#### iTunes Library.xml ####

Selecting this option will cause the input file to be treated as an iTunes Library.xml file. 

This file can generally be found in your Music / My Music folder, and then within your iTunes folder. See Apple Support articles [Where are my iTunes files located?](http://support.apple.com/kb/HT1391) and [What are the iTunes library files?](http://support.apple.com/kb/HT1660) for details. 

This information can be used to publish your list of albums to a Web site, or to import your list of albums into a database, such as Bento. 

This input routine will extract and summarize information about albums contained in your iTunes library. For each unique album title, the following fields will be extracted. 

<dl>
	<dt>Album Title</dt>
		<dd>The title of the album</dd>
	<dt>Artist</dt>
		<dd>The name of the Artist for the album. If the album contains tracks performed by multiple artists, then this field will be set to &#8220;Various&#8221;.</dd>
	<dt>Artist Sort Key</dt>
		<dd>The Artist name that is used by iTunes for sorting: typically, the same as the Artist name, but without a leading &#8220;The&#8221;. </dd>
	<dt>Genre</dt>
		<dd>The genre assigned to the tracks in the album. Any genre containing &#8220;jazz&#8221; will be reported as &#8220;Jazz&#8221;; any genre containing &#8220;holiday&#8221;, &#8220;Christmas&#8221; or &#8220;xmas&#8221; will be reported as &#8220;Holiday&#8221;; any other variations in genre within an album will result in this field being set to &#8220;Mixed&#8221;. </dd>
	<dt>Year</dt>
		<dd>The year in which the album was released.</dd>
	<dt>Tracks</dt>
		<dd>The number of tracks in your iTunes library having this album title: this field can usually tell you whether you have an entire album in your collection, or only a few selections. </dd>
	<dt>Unrated?</dt>
		<dd>This field will be set to &#8220;Unrated&#8221; if any of this album&#8217;s tracks are as yet unrated by you. Once you have assigned a rating to all tracks on this album, this field will be reported as blank. </dd>
	<dt>Hi-Fi</dt>
		<dd>This field will be reported as &#8220;Hi-Fi&#8221; if the album&#8217;s tracks have been recorded in the Apple Lossless format. </dd>
	<dt>Lo-Fi Type</dt>
		<dd>If the album contains tracks that are not in the lossless format, then their format will be reported here, typically as &#8220;AAC&#8221; or &#8220;MP3&#8221; or &#8220;Mixed&#8221;, if the album contains a mix of types. </dd>
	<dt>Podcast</dt>
		<dd>If the album contains any tracks identified with a Media Kind of &#8220;podcast&#8221;, then this field will be set to &#8220;Podcast&#8221;.</dd>
</dl>

#### Markdown Metadata ####

If you select a folder containing [Markdown][] files, then this input option will extract metadata about each file and make them available in a list. 

See the [Markdown Metadata specification][metamarkdown] for details.

#### XML Rows for Fields ####

Setting this option will cause the program to treat the input file as XML. Each field will be returned as a separate record. Field names will be stored in columns 1 - 4, with column names &#8220;Name1&#8221; through &#8220;Name4&#8221;, and field values will be stored in column 5, with column name &#8220;Data&#8221;. 

#### XML Rows for Records ####

Setting this option will cause the program to treat the input file as XML. Each set of fields at the same level will be returned as a separate record, with the assumption that the XML file consists of a series of records with the same fields. Field names will be stored in columns 1 - 4, with column names &#8220;Name1&#8221; through &#8220;Name4&#8221;, and field values will be stored in column 5, with column name &#8220;Data&#8221;. 

### Data Dictionary Input ###

Clicking on this check box causes the program to look for a special data dictionary file to accompany the tab-delimited data source. The data dictionary file must have the same name as the primary file, but with a file extension of &#8220;.dic&#8221;. 

The data dictionary file itself is in tab-delimited format. Each row in the file (after the column headings) represents one column in the primary data file. 

Note that the easiest way to create a data dictionary for a file is to input the file to PSTextMerge (without a dictionary), then Output it with a dictionary (see the <a href="#output">Output</a> section for details). You can then edit the resulting file, only modifying the values you wish to change. 

A dictionary may have the following columns describing the fields in the primary file. 

#### Proper Name ####

This is the column heading that identifies this field in the primary data file. It would normally include mixed-case, spaces and punctuation to make it readable.

#### Common Name ####

This is really a lowest-common denominator form of the column heading, with capitals, spaces and punctuation all removed. This is used internally by PSTextMerge to allow slight variations in punctuation, etc., without recognizing them as two separate names. 

#### Alias For ####

If the names previously given should be treated as an alias for another field name, then this column should contain the primary name for the field, and the remaining columns for the alias should be left blank. This feature doesn&#8217;t do much in this release of PSTextMerge, but should become more meaningful in later releases. 

#### Data Format Rule ####

The default value here is &#8220;DataFormatRule&#8221;, which causes no special formatting of the input data. But specifying another value can cause the input data to be formatted and even converted according to any of the following special rules. 

<dl>
<dt>AllCapsRule</dt>
	<dd>Causes all letters to be capitalized (good for US state codes, for example).</dd>

<dt>CountryRule</dt>
	<dd>Ensures that the first letter of each word is capitalized, and remaining letters are lower-case, with the exceptions of the values &#8220;UK and &#8220;USA&#8221;, which are ensured to be in all capitals. Intended for mailing list fields containing country!</dd>

<dt>DataFormatRule</dt>
	<dd>Performs no conversion or formatting. This is the default value when PSTextMerge is creating a new dictionary file.</dd>

<dt>DateRule</dt>
	<dd>Generally expects a date in a loose month-day-year format, and formats it into a &#8220;tighter&#8221; month-day-year format, with two-digit numbers for each. More flexibility may be supported in later releases.</dd>

<dt>HyperlinkRule</dt>
	<dd>Replaces spaces with &#8220;%20&#8221; strings, and adds a protocol of &#8220;http:&#8221; or &#8220;file:&#8221; if one is not present, based on whether the address looks like a WWW address or a local file. Good for formatting hyperlinks from MS Office documents into valid HTML URL links.</dd>

<dt>InitialCapsRule</dt>
	<dd>Ensures that the first letter of each word is capitalized, and other letters are lower-case. Good for formatting names of people, cities, etc.</dd>

<dt>LowerCaseRule</dt>
	<dd>Ensures all letters are lower-case. Good for formatting URLs and e-mail addresses.</dd>

<dt>USMobileRule</dt>
	<dd>Intended for formatting of mobile (cell and pager) telephone numbers according to US conventions. Similar to USPhoneRule, but with different rules for conversion of the 602 area code.</dd>

<dt>USPhoneRule</dt>
	<dd>Intended for formatting of non-mobile telephone numbers according to US conventions. The standard format is &#8220;aaa-xxx-nnnn&#8221;, where &#8220;aaa&#8221; is the area code, &#8220;xxx&#8221; is the local exchange, and &#8220;nnnn&#8221; is the rest of the phone number. The default area code is 602, for Phoenix, Arizona. This rule also will convert area codes from 602 to one of the newer Phoenix metropolitan area codes, depending on the exchange. Note that this logic has limited usefulness at this point, as new (and duplicate) exchanges begin to be assigned within the three area codes.</dd>

</dl>

#### Function ####

If a valid function name is specified here, then the field value
will be calculated using the specified function. The following functions are available. The functions make use of the parameters specified in the following columns. 

<dl>
<dt>Lookup</dt>
	<dd>Looks up the value for this field in a separate table, stored as a tab-delimited file in the same directory, using the following five parameters.</dd>
</dl>

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
		<td>A value of &#8220;Yes&#8221; or &#8220;True&#8221; indicates that the key comparison should be case-sensitive. 
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


#### Parm1 ####

Used as input to the specified function. 

#### Parm2 ####

Used as input to the specified function. 

#### Parm3 ####

Used as input to the specified function. 

#### Parm4 ####

Used as input to the specified function. 

#### Parm5 ####

Used as input to the specified function. 

### Merge Into Existing Data ###

Select one of the following radio buttons to indicate how and whether you want new data to be merged with existing data.  

#### No Merge ####

This is the default. The next data source to be input will overlay any data previously input. 

#### Merge New Data With Old ####

The next input data source to be opened will be merged with the current data visible on the View Tab. Existing sort keys and filters may need to be reapplied. The new data and the old data should have column names that are at least partially overlapping, if not identical. 

#### Merge with Same Columns ####

The next input data source to be opened will be merged with the current data visible on the View Tab. The program will not look for column headings in the new input file, but will instead assume that the column names for the new file are the same, and in the same order, as those currently visible on the View Tab. Existing sort keys and filters may need to be reapplied. 

Note that this merge option is useful for programs (such as AppleWorks) that do not include column headings in their export files. If a separate file is created containing only column headings, then it can be input first, followed by the headerless file, with this merge option. 

### Maximum Directory Depth ###

If you are about to read a file directory, then this field controls whether sub-directories are read, and to what depth. A value of 1 is the default, and indicates that only files and directories in the specified directory will be listed, with no sub-directory contents. A value of 2 indicates one level of sub-directories, and so forth. Use the Increment and Decrement buttons to change the depth. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="view">View</h2>

This tab allows the user to view a tab-delimited data file that has been opened for input. Subsequent tabs, such as Sort and Filter, will affect the data that is displayed on the View tab. 

The user can scroll from left to right and up and down, assuming there is more data available than will fit within the current window. Columns can also be resized by clicking on their right borders and dragging. Each initial column size will be approximately proportional to the largest data field within the column.

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="sort">Sort</h2>

This tab allows the user to sort the data that has been input. Sorting is accomplished by using the following buttons that appear on this tab.

### Field Name ###

This is a drop down list of all the columns in your data. Select the next field name on which you wish to sort, by starting with the most significant fields and proceeding to less significance.  

### Sort Sequence ###

This is a drop down list. Pick either ascending (lower values towards the top, higher values towards the bottom) or descending. This sequence applies to the currently selected field name (see above).  

### Add ###

Pressing this button will add the field and sequence currently specified to the current sort parameters being built. The sort parameters added will appear in the text area shown below on this tab. After pressing the Add button, the user may go back and specify additional fields to be used in the sort criteria.  

### Clear ###

Pressing this button will clear the sort parameters being built, so that you can start over.  

### Set ###

Once your desired sort parameters have been completely built, by pressing the Add button one or more times, you must press the Set button to cause your parameters to be applied to the data you are currently processing.  

### Combine ###

After setting the desired sort sequence, you may optionally press this button to combine records with duplicate sort keys. The following buttons allow you to adjust the parameters controlling the combination process.   

### Tolerance for Data Loss ###

Record combination can be done with varying degrees of tolerance for data loss. Select one of the following radio buttons.  

* No Data Loss &#8212; Records will only be combined if data (non-key) fields are identical, or if one of the two corresponding values is blank (in which case the non-blank value will be preserved in the resulting combined record).
* Later Records Override Earlier &#8212; If you are merging two input files, and one is known to carry more current data, then you can specify which file is the more current with this button or the next. This button will cause files merged later to be treated as more current.
* Earlier Records Override Later &#8212; Similar to prior button, but with files merged earlier taking precedence over later ones.
* Combine Fields Where Allowed &#8212; If the data dictionary allows fields to be concatenated, then this radio button indicates that concatenation may take place. This may be appropriate for a comment field.


### Minimum Number of Lossless Fields ###

If you specify some data loss to be acceptable, then this field may be used to specify a minimum number of data (non-key) fields that must be lossless (equal or one blank) before combination will be allowed to take place. This should be used if the sort keys are not guaranteed to establish uniqueness. Specifying a non-zero value here may help to prevent completely disparate records from being inadvertently combined. For example, names can be used to identify people, but two different people may have the same name.   

<dl>
  <dt>Increment (+)</dt>
    <dd>Use this button to increase the minimum number of lossless fields.</dd>
  <dt>Decrement (-)</dt>
    <dd>Use this button to decrease the minimum number of lossless fields. Zero is the smallest allowable value.</dd>
</dl>

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="filter">Filter</h2>

This tab allows the user to filter the data that has been input, selecting some rows to appear and others to be suppressed. Filtering is accomplished by using the following buttons that appear on this tab.

### Field Name ###

This is a drop down list of all the columns in your data. Select the next field name on which you wish to filter.  

### Comparison Operator ###

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

### Comparison Value ###

This is the value to which the selected field will be compared. Only rows that satisfy this comparison will be visible after the filtering operation. You may type in a desired value, or select from the drop down list. The drop down list will consist of all the values found in this field within your data.  

### Add ###

Pressing this button will add the field, operator and value currently specified to the current filter parameters being built. The filter parameters added will appear in the text area shown below on this tab. After pressing the Add button, the user may go back and specify additional fields to be used in the filtering criteria.  

### Clear ###

Pressing this button will clear the filter parameters being built, so that you can start over.  

### Set ###

Once your desired filter parameters have been completely built, by pressing the Add button one or more times, you must press the Set button to cause your parameters to be applied to the data you are currently processing.  

### And/Or ###

If you specify more than one filter parameter, then you may specify whether all of them must be true (and) or only any one of them must be true (or) in order to satisfy the filtering criteria. This choice applies to the entire set of criteria, so this need only be selected once before pressing the Set button. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="output">Output</h2>

This tab allows the user to save the current data to an output disk file. The data will be saved in the sequence set by the current sort parameters, if any. If a current filter is in effect, then only visible, filtered data will be saved. If any data transformation or formatting occurred on input, then the data will be saved in its new form. The following controls appear on this tab.

### Save Output ###

Clicking on this button allows you to select a location and name for the output file, and then writes the file (with optional dictionary) as specified. This function can also be invoked via the File/Save Menu item or with the S shortcut key.

### Data Dictionary Output ###

Clicking on this check box causes the program to write a special data dictionary file to accompany the tab-delimited data source being output. The data dictionary file will have the same name as the primary file, but with a file extension of &#8220;.dic&#8221;. Clicking this check box again will cause it to revert to its original state, in which the dictionary will not be saved. See the <a href="input.html">Input</a> section for a complete description of the dictionary file.

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="template">Template</h2>

This tab allows the user to merge the currently loaded data into a template file, producing one or more output text files. The greatest anticipated use for this function is to create Web pages, based on input template files containing a mixture of HTML tags and special PSTextMerge tags. This allows tab-delimited data to be periodically merged into an HTML template that determines the format in which the data will be displayed on a Web site.

### Buttons ###

This screen contains the following buttons. 

#### <a id="templatelib">Set Template Library</a> ####

PSTextMerge supports the concept of a central template library where you can store reusable templates. The initial location for this folder is the &#8220;templates&#8221; folder within the PSTextMerge Folder that comes as part of the software distribution. However, this button can be used to allow you to select another folder as your template library. After installation of PSTextMerge, you may wish to copy the templates folder to another location, perhaps within your home folder, or your documents folder, and then use this button to specify that new location. 

#### Open Template ####

This button allows you to specify the location and name of the template file you wish to use. (This file must have previously been created using a text editor.) This function may also be invoked via the Template/Open Menu item or with the T shortcut key.

#### Open From Library ####

This button also opens a template file, but uses your template library as the starting location.

#### Generate Output ####

This button processes the template file you have selected, and creates whatever output file(s) you have specified in the template file.  The function may also be invoked via the Template/Generate Menu item or with the G shortcut key.

### Template File Format ###

See the [Template File Format specification][template] for details. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="script">Script</h2>

This tab allows the user to record and playback sequences of PSTextMerge commands. The following buttons and menu commands are available.

### Record ###

Clicking on this button once causes the program to begin recording your subsequent actions as part of a script that can be edited and played back later. This function may also be invoked via the Script/Record Menu item, or with the R shortcut key. You will need to specify the location and name for your script file. It is recommended that &#8220;.tcz&#8221; be used as a file extension for PSTextMerge script files (the original name for the program was &#8220;TDF Czar&#8221;). This will be supplied as a default if no extension is specified by the user.   

### Stop ###

Clicking on this button causes recording of the current script to stop. This function may also be invoked via the Script/End Recording Menu item, or with the E shortcut key. The script file will be closed, and can now be opened for editing, if desired, using the text editor, spreadsheet or database program of your choice. 

### Play ###

This button allows you to select a script file to be played back. This function may also be invoked via the Script/Play Menu item, or with the P shortcut key. At the end of a script&#8217;s execution, the input file options will be reset to their initial default values, to ensure consistent execution when multiple scripts are executed consecutively. 

### Play Again ###

This button allows you to replay the last script file either played or recorded. Using this button allows you to bypass the file selection dialog. It can be handy if you are developing, modifying or debugging a series of actions and associated files. This function may also be invoked via the Script/Play Again Menu item, or with the A shortcut key.

### Turn Autoplay On ###

Clicking this button will allow you to select a script to be automatically played every time the application is launched. 

After selecting a script to play automatically, the label of this button will change to &#8220;Turn Autoplay Off&#8221;.

### Turn Easy Play On ###

Clicking this button will allow you to select a folder of scripts that you want easy access to. A new tab will then be added to the interface, labeled &#8220;Easy&#8221;. The new tab will contain a button for every script found in the folder. Clicking on a button will then play the corresponding script. 

After selecting an Easy Play folder, the label of this button will change to &#8220;Turn Easy Play Off&#8221;.

### <a id="playrecent">Play Recent</a> ###

This menu item, within the Script menu, allows you to select a recently played script to run. The most recent 10 scripts will be available to select from.

### Script File ###

The script file is itself a tab-delimited text file, and you can edit one using your favorite tool for such things. You can create one completely from scratch if you want, but it usually easiest to record one first, and then edit the results. 

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

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="logging">Logging</h2>

This tab allows the user to control logging operations. PSTextMerge writes information about certain events to a log file. Reviewing this data can be useful, especially if the program is not performing as expected. The following sections appear on this tab.

### Log Destination ###

This determines where the log output is sent. You can select any of the following options.  

* None &#8212; The logging data will not be visible.
* Window &#8212; Sends log messages to the standard Java console.
* Text &#8212; Sends log messages to the text area below, within the Log tab.
* Disk &#8212; Saves the log messages to a disk file, with the standard name &#8220;PSTextMerge_log.txt&#8221;.

### Logging Threshold ###

This determines the quantity and severity of messages that will appear in the log. You have the following options. 

* Normal &#8212; All events will be written to the log, even those indicating normal program operation.
* Minor &#8212; Minor, Medium and Major severity events will be written.
* Medium &#8212; Medium and Major severity events will be written.
* Major &#8212; Only major events will be written to the log.

### Data Logging ###

Input data files are often passed to the logger, primarily so that significant events that are data-related can include a display of the data record that generated the event. Checking the &#8220;Log All Data?&#8221; box will result in all data passed to the logger being written to the log. This may be helpful if the log is otherwise showing insufficient data to let you understand the workings of the program. 

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="about">About</h2>

This tab allows the user to view some basic information about the PSTextMerge program. It includes a version number and copyright information. More complete information is contained in this user guide.

This tab can also be reached by selecting the About PSTextMerge Menu Item. On the Mac, this will appear in the Application Menu. On other operating systems, it will appear in the Help Menu.

<p class="back-to-top"><a href="#toc">Back to Top</a></p>

<h2 id="help-menu">Help Menu</h2>

The Help Menu includes three other useful items. The first, User Guide, attempts to launch the PSTextMerge User Guide installed with your program as a window within a local Web Browser. This operation will not always be successful, due to operating system restrictions. In some cases, as an alternative, your Web Browser will be directed to the PSTextMerge Home Page on the Web. It is possible that the information on the Web may not apply to your version of PSTextMerge. You can always navigate to your local user guide by using your file system navigator to select the file &#8220;userguide/index.html&#8221; within your PSTextMerge application folder, and then launching it using your preferred Web Browser. 

The next item on the Help Menu will attempt to direct your Web Browser to the PSTextMerge Home Page. You will need an active Internet connection for this to be successful. Again, operating system restrictions may prevent this action from being completed successfully. If so, you can always copy and paste the following address into your Web Browser: http://www.powersurgepub.com. 

[java]:  http://www.java.com/

[pspub]:     http://www.powersurgepub.com/
[downloads]: http://www.powersurgepub.com/downloads.html
[store]:     http://www.powersurgepub.com/store.html

[markdown]:  http://daringfireball.net/projects/markdown/
[pegdown]:   https://github.com/sirthias/pegdown/blob/master/LICENSE
[parboiled]: https://github.com/sirthias/parboiled/blob/master/LICENSE
[Mathias]:   https://github.com/sirthias

[club]:         clubplanner.html
[filedir]:      filedir.html
[metamarkdown]: metamarkdown.html
[template]:     template.html

[osd]:				http://opensource.org/osd
[gnu]:        http://www.gnu.org/licenses/
[apache]:			http://www.apache.org/licenses/LICENSE-2.0.html

<p class="back-to-top"><a href="#toc">Back to Top</a></p>