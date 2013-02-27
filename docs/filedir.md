PSTextMerge File Directory Specification
========================================

*[PSTextMerge User Guide][userguide]*

The following special column headings are predefined for file directory entries.

<dl>
<dt>Sort Key</dt>
	<dd>Used for sorting the directory entries alphanumerically, without regards to case (upper or lower) or punctuation. The complete file path will appear here, all in lower-case, with spaces between file directories, and spaces replacing punctuation. </dd>
		
<dt>Folder1 through Foldern</dt>
	<dd>Folder1 will contain the first sub-directory name, within the specified input directory, if this entry came from a sub-directory. Folder1 through Foldern columns will appear, where "n" is the maximum directory depth - 1 (since a maximum directory depth of 1 indicates no sub-directory explosion at all). </dd>

	<dt>Path</dt>
		<dd>The series of folders, with each folder/directory separated from the previous one with a slash, between the top level folder selected and the file name identified later in this row. </dd>
		
<dt>File Name</dt>
	<dd>Name of the file or sub-directory. </dd>
	
<dt>Type</dt>
	<dd>The type of directory entry: "File" or "Directory".</dd>
	
<dt>English Name</dt>
	<dd>A file name with standardized spacing, without punctuation, and without a file extension.</dd>
	
	<dt>File Name w/o Extension</dt>
		<dd>The file name without its extension.</dd>
	
<dt>File Ext</dt>
	<dd>The file extension, if a file and if it has one.</dd>
	
<dt>File Size</dt>
	<dd>Size of the file, in bytes.</dd>
	
<dt>Last Mod Date</dt>
	<dd>Date of last change to the file, in "yyyy-mm-dd" format.</dd>
	
<dt>Last Mod Time </dt>
	<dd>Time of last change to the file, in "hh:mm:ss zzz" format, where "hh" is a 24-hour (military) hour, "mm" is minutes, "ss" is seconds and "zzz" is an abbreviation of the time zone. </dd>
	
<dt>Word1 through Word5 </dt>
	<dd>The file name, without directories and without extension, will be broken down into up to five separate fields, using punctuation, spaces, and case transitions to demarcate words. </dd>
	
</dl>

*[Return to User Guide][userguide]*

[userguide]: pstextmerge.html#input