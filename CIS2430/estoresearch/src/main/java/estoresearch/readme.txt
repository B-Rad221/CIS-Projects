Description:
	This program runs a command loop that allows the user to add or search for a book or electronic by specifying its properties. When searching, a user can enter a product ID, some keywords to match the description, and/or a time period to search. When searching a time period, the user can enter a single year to search only for that year, or enter a range of years such as 2010-2020. Additionally, they may enter something like -2020 which will search for everything before 2020, or 2010- which will search for everything after 2010. When searching, the user must also enter whether they want to do a linear search or a hash search. Linear search, as the name suggests, linearly searches the list for the id, keywords, and/or time period. Hash search uses a hash map that has the keywords stored along with a list of indices of where to find that word in the products array list. Hash search can be much faster than a linear search, and if it fails, the program will instead do a linear search. Instead of requiring the user to add products to the program every time it runs, users can save and load products from a file. The user simply needs to specify the filename in the first argument of the command line, and the program will automatically load the file at the start and save the file when the user quits the program.

Assumptions/Limitations:
	When loading a file, the file was assumed to be of the correct format. Incorrect formatting will not break the program but will cause the program to be unable to load the file.
	The hash search function assumes that the hash map has been updated according to the products array list.
	The keyword search function assumes that there are no duplicates of the same word in the description of a single product. If there are duplicates in a product's description, the search function could match the product to keywords, even when the description doesn't contain all of the keywords.

User Guide:
	To run the program, enter the estoresearch folder in your terminal and run the following: ./gradlew run --args="<Filename>"
	Where <Filename> is the name of the file you wish to load/save.

Test Plan:
	Command Loop: Reasonable variations for input. Case insensitivity and accepting the use of only the first letter of the command are included. If the input is rejected the program will display a message and allow the user to try again.

	Search Functions: 
		1) The given search parameters do not match any product in the list
		2) The given search parameters are empty
		3) The product ID matches a product in the list
		4) The keywords match multiple products in the list
		5) The keywords match a single product in the list
		6) The time period parameter is a single number with no dashes
		7) The time period parameter is a single number followed by a dash
		8) The time period parameter is a single number preceded by a dash
		9) The time period parameter is two numbers with a dash in between
		10) The product ID parameter contains invalid values
		11) The keywords parameter contains invalid values
		12) The time period parameter contains invalid values

	Add Function:
		1) Product Id is invalid
		2) Description is invalid
		3) Price is invalid
		4) Year is invalid
		For Books:
		1) Author is empty
		2) Publisher is empty
		3) Author and Publisher are not empty
		For Electronics:
		1) Maker is empty
		2) Maker is not empty

	Load/Save:
		1) The command line argument contains the name of a valid file
		2) The command line argument contains the name of a file that doesn't exist

Possible Improvements:
	The keyword search function could be updated to allow duplicate words in the description of a product without allowing the possibility of the product matching with a set of keywords when it doesn't contain all of the keywords.