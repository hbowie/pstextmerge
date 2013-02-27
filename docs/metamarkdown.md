PSTextMerge Markdown Metadata
=============================

*[PSTextMerge User Guide][userguide]*

The following special column headings are predefined for metadata gathered from [Markdown][] files.

Metadata is provided in the spirit of, although not in complete conformance with, the [MultiMarkdown][] syntax. That is, special lines are expected at the top of the file, each line starting with a key, followed by a colon and then a value, as in the following example.

> Title:  Markdown Metadata  
> Author: Herb Bowie
> Tags:   Java, Documentation
> Date:   July 4, 2012

Note that there are two variants of this file type, one simply labeled "Markdown Metadata" and the other labeled "Markdown Metadata Tags". The first has only one row per Markdown file, and identifies all tags for that file. The second has one row per tag per file, and identifies only one tag at a time. The first file format would normally be used for a simple index of the files, while the second format would be used to generate an index by tag. 

<dl>
<dt>Complete Path</dt>
	<dd>The complete path to the file, including all directories, plus the file name and extension. </dd>
		
<dt>Base Path</dt>
	<dd>The folder path to the top directory containing the markdown files included in the list. </dd>

<dt>Local Path</dt>
	<dd>The series of folders, with each folder/directory separated from the previous one with a slash, between the top level folder selected and the file name identified later in this row. </dd>
	
<dt>Depth</dt>
	<dd>0 for files at the top level of the extract, 1 for files within the next set of folders, and so forth. </dd>
		
<dt>File Name</dt>
	<dd>Name of the file, including its file extension. </dd>
	
<dt>File Name Base</dt>
	<dd>The file name without its extension, and without the period preceding the extension.</dd>
	
<dt>File Ext</dt>
	<dd>The file extension, without the preceding period.</dd>
	
<dt>Last Mod Date</dt>
	<dd>Date of last change to the file, in "yyyy-mm-dd hh:mm:ss TMZ" format.</dd>
	
<dt>File Size</dt>
	<dd>Approximate size of the file, in bytes.</dd>
	
<dt>Title</dt>
	<dd>The title can come from a number of different sources. If the file does not contain a title, then the file name will be used as the title, after removing its extension, and normalizing the case and word separators to leading caps and a space, respectively. If the file contains a level 1 heading, denoted by an initial line followed by a line of simulated double underlines (four or more equal signs), per the [Markdown][] specification, then this will be used as the title. And finally, if the metadata at the top of the file contains a line starting with "Title:", then the remainder of the line will be used as the title.  </dd>
	
<dt>Author</dt>
	<dd>If the file title is followed by a byline, starting with the word "by", then the remainder of the line will be used as the author. Alternatively, if the metadata at the top of the file contains a line starting with "By", "Author", or "Creator", then the remainder of the line will be used as the author. </dd>
	
<dt>Date</dt>
	<dd>The date associated with the file, as established by a metadata line at the top of the file starting with "Date:". </dd>
		
<dt>Breadcrumbs</dt>
	<dd></dd>
	
<dt>Tags</dt>
	<dd>These would be the tags provided by a metadata line starting with the key of "Tags", "Keywords" or "Category". Multiple tags may be provided, each separated from the other by a comma, with or without spaces. Tags may be nested as well, with a period separating each level, without any spaces. </dd>

<dt>Linked Tags</dt>
	<dd></dd>
	
<dt>Tag</dt>
	<dd></dd>
	
</dl>

*[Return to User Guide][userguide]*

[markdown]:       http://daringfireball.net/projects/markdown/
[multimarkdown]:  http://fletcher.github.com/peg-multimarkdown/
[userguide]:      index.md#input