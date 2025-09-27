import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Book {
    String title;
    String author;
    int quantity;
    String description;

    Book(String title, String author, int quantity, String description) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.description = description;
    }
}

public class LibrarySystem {
    private static final Map<String, Book> library = new TreeMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1: addBooks(); break;
                case 2: borrowBooks(); break;
                case 3: returnBooks(); break;
                case 4: displayLibraryValue(); break;
                case 5: System.out.println("\u001B[32mExiting the program. Goodbye!\u001B[0m"); break;
                default: System.out.println("\u001B[31mInvalid choice. Please choose a valid option (1-5).\u001B[0m");
            }
        } while (choice != 5);
    }

    private static void printMenu() {
        System.out.println("\n\u001B[34m====== Library Menu ======\u001B[0m");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. View Library's Total Value");
        System.out.println("5. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("\u001B[31mInvalid input. Please enter a number (1-5): \u001B[0m");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addBooks() {
        scanner.nextLine(); // clear buffer

        System.out.print("\u001B[36mEnter book title: \u001B[0m");
        String title = scanner.nextLine().trim();

        System.out.print("\u001B[36mEnter author name: \u001B[0m");
        String author = scanner.nextLine().trim();

        System.out.print("\u001B[36mEnter book description: \u001B[0m");
        String description = scanner.nextLine().trim();

        System.out.print("\u001B[36mEnter quantity: \u001B[0m");
        int quantity = getValidQuantity();

        if (library.containsKey(title.toLowerCase())) {
            Book existing = library.get(title.toLowerCase());
            existing.quantity += quantity;
            System.out.println("\u001B[33mBook already exists. Updated quantity to " + existing.quantity + "\u001B[0m");
        } else {
            library.put(title.toLowerCase(), new Book(title, author, quantity, description));
            System.out.println("\u001B[32mBook added successfully!\u001B[0m");
        }
    }

    private static int getValidQuantity() {
        while (!scanner.hasNextInt()) {
            System.out.print("\u001B[31mInvalid quantity. Please enter a valid number: \u001B[0m");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void borrowBooks() {
        scanner.nextLine(); // clear buffer

        System.out.print("\u001B[36mEnter the title of the book to borrow: \u001B[0m");
        String title = scanner.nextLine().trim().toLowerCase();

        if (!library.containsKey(title)) {
            System.out.println("\u001B[31mBook not found in the library.\u001B[0m");
            return;
        }

        System.out.print("\u001B[36mEnter the number of books to borrow: \u001B[0m");
        int quantity = getValidQuantity();

        Book book = library.get(title);

        if (quantity <= book.quantity) {
            book.quantity -= quantity;
            System.out.println("\u001B[32mSuccessfully borrowed " + quantity + " copy(ies) of '" + book.title + "'.\u001B[0m");
        } else {
            System.out.println("\u001B[31mNot enough copies available. Only " + book.quantity + " left.\u001B[0m");
        }
    }

    private static void returnBooks() {
        scanner.nextLine(); // clear buffer

        System.out.print("\u001B[36mEnter the title of the book to return: \u001B[0m");
        String title = scanner.nextLine().trim().toLowerCase();

        if (!library.containsKey(title)) {
            System.out.println("\u001B[31mThis book does not belong to our library.\u001B[0m");
            return;
        }

        System.out.print("\u001B[36mEnter the number of books to return: \u001B[0m");
        int quantity = getValidQuantity();

        Book book = library.get(title);
        book.quantity += quantity;
        System.out.println("\u001B[32mSuccessfully returned " + quantity + " copy(ies) of '" + book.title + "'.\u001B[0m");
    }

    private static void displayLibraryValue() {
        int totalValue = 0;
        for (Book book : library.values()) {
            totalValue += book.quantity * 10; // assuming each book is worth 10 units
        }
        System.out.println("\u001B[35mTotal value of the library: " + totalValue + " units.\u001B[0m");
    }
}
