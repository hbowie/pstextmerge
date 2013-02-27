#### <a id="outline">PSPub Outline</a> ####

This is a special text file format to allow easy representation of an outline structure. Indention is used to indicate outline levels. The first character of the first line is assumed to be the "bullet" character that will subsequently identify all list items. Blank lines indicate paragraph breaks. A line beginning with "a:" (or simply with "http:") identifies a URL to be associated with the preceding outline item.

This structure is then converted to a columnar data structure, with one row for each paragraph/list item, and with the following columns. 

<dl>
	<dt>sectionnumber1 through sectionnumber10</dt>
		<dd>The sectionnumber1 column contains the current highest level "section", or item number, in the overall structure. These numbers are assigned internally, beginning with 1, and are not taken from the input file. </dd>
		
	<dt>headingflag</dt>
		<dd>Set to "true" if this item is a bulleted item, otherwise set to "false". </dd>
		
	<dt>level</dt>
		<dd>Starting with 1, the depth of the current item in the overall structure. </dd>
		
	<dt>text</dt>
		<dd>The body of the item or paragraph. </dd>
		
	<dt>link</dt>
		<dd>The URL, if any, supplied for this item or paragraph. </dd>		
</dl>

This columnar data can then be formatted and printed using the appropriate template. Several samples are supplied in the templates folder with the PSTextMerge folder supplied with the application's distribution. 