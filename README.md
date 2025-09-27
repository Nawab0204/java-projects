# java-projects
Description of the System Code for the Java Library


1. Statements of Import
The application supports input handling and data management by utilizing the built-in Java libraries:
Map: An interface for assigning values to keys.
Scanner: To read input from users.
TreeMap: A map that has been sorted to arrange books according to their titles.

2. Book a class
four attributes are used to define a book object: title, author, quantity, and description. When a book is added, these fields are initialized by the constructor.

3. Main Driver for Library System Class
all library operations are contained in this main class, which also controls user-system interaction.
4. Global Variables
- Library: All book objects keyed by lowercase titles are stored in a Tree Map. 
-scanner: An object that scans for user input.
5. main() Method
Shows the main menu and iterates through the options selected by the user, executing various methods according to the input until the user leaves.

6. printMenu()
Shows a menu with choices to view the total value of the library, add, borrow, and return books. Colors make text easier to read.

7. getUserChoice()
Makes sure a number between 1 and 5 is entered by validating user input. If incorrect input is entered, it prompts again.

8. addBooks()
Asks the user for the book's details. It modifies the quantity if the book is already in existence. It adds a new entry if it's new.
9. getValidQuantity()
Makes sure that only legitimate numbers are entered for the number of books. Loops until it receives a valid input.
10. borrowBooks()
Manages the borrowing logic by determining whether a book is available and whether there are enough copies, after which the library is updated.
11. returnBooks()
Logic for returns is handled by first confirming that the book is from the library, then updating the quantity and confirming the return.

12. displayLibraryValue()
Determines the total worth of all the library's books. Five units are assigned to each book.

Console Colors
Uses ANSI escape codes to enhance console output with color:
- Blue: Menus
- Cyan: Prompts
- Green: Success
- Red: Errors
- Yellow: Warnings
- Purple: Value display
Summary of Features
- Object-Oriented design using a Book class
- TreeMap for auto-sorting
- Menu-driven interface
- Input validation
- Color-coded console UI
- Robust error handling
