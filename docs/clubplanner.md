PSTextMerge Club Planner Data
=============================

*[PSTextMerge User Guide][userguide]*

Information about events and other items for club consideration are stored in text files, with one event/item per file. 

Note that there are two variants of this file, one labeled "Club Planner" and the other labeled "Club Notes". The first has only one row per event, while the second has one row per note header in the Notes field for each event.

Folder Structure
----------------

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

Template
--------

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

Input Fields
------------

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

Calculated Fields
-----------------

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

Additional Fields in Club Notes File Format
-------------------------------------------

The following additional fields are extracted for the Club Notes file format, with one row for each note header.

**Note For**: The date of the note, in yyyy-mm-dd format, suitable for sorting and/or filtering, as extracted from the note header. 

**Note From**: The source of the note, as extracted from the note header. 

**Note Via**: The medium by which the note was communicated, as extracted from the note header. 

**Note**: The text of the note, following the note header. 

**Note as HTML**: The text of the note, converted from [Markdown][] to HTML, suitable for insertion into a Web page or email.

*[Return to User Guide][userguide]*

[markdown]:       http://daringfireball.net/projects/markdown/
[multimarkdown]:  http://fletcher.github.com/peg-multimarkdown/
[userguide]:      index.md#input
