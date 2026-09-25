package Project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main.java
 * Role-Based CLI Interface for the Library Management System.
 * Owned by: Person D (Integration & Logic)
 */
public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Pre-populate sample books & members
        initializeSampleData(library);

        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("      LIBRARY MANAGEMENT SYSTEM         ");
            System.out.println("========================================");
            System.out.println("1. Librarian / Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.println("========================================");
            System.out.print("Select Role (1-3): ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    handleLibrarianMenu(library, scanner);
                    break;
                case "2":
                    handleStudentMenu(library, scanner);
                    break;
                case "3":
                    System.out.println("\nThank you for using Library Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please select 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    private static void initializeSampleData(Library library) {
        library.addBook(new Book(101, "Clean Code", "Robert Martin", 600.00));
        library.addBook(new Book(102, "Java Programming", "James Gosling", 500.00));
        library.addBook(new Book(103, "Data Structures", "Mark Allen", 450.00));
        library.addBook(new Book(104, "Design Patterns", "Erich Gamma", 800.00));
        library.addBook(new Book(105, "The Pragmatic Programmer", "Andrew Hunt", 950.00));

        library.addMember(new Member(501, "Student1", "Student1@gmail.com"));
        library.addMember(new Member(502, "Student2", "Student2@gmail.com"));
        library.addMember(new Member(503, "Student3", "Student3@gmail.com"));
    }

    // LIBRARIAN MENU & HANDLER

    private static void handleLibrarianMenu(Library library, Scanner scanner) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n----------------------------------------");
            System.out.println("            LIBRARIAN MENU              ");
            System.out.println("----------------------------------------");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Search Book");
            System.out.println("5. View All Members");
            System.out.println("6. View Issued Books");
            System.out.println("7. View All Transactions");
            System.out.println("8. View Overdue Books");
            System.out.println("9. View Library Statistics");
            System.out.println("10. Sort Books");
            System.out.println("11. Logout");
            System.out.println("----------------------------------------");
            System.out.print("Enter choice (1-11): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    library.displayAllBooks();
                    break;
                case "2":
                    addNewBook(library, scanner);
                    break;
                case "3":
                    removeBook(library, scanner);
                    break;
                case "4":
                    searchBook(library, scanner);
                    break;
                case "5":
                    library.displayAllMembers();
                    break;
                case "6":
                    library.displayIssuedBooks();
                    break;
                case "7":
                    library.displayAllTransactions();
                    break;
                case "8":
                    library.displayOverdueBooks();
                    break;
                case "9":
                    library.displayStatistics();
                    break;
                case "10":
                    sortBooksMenu(library, scanner);
                    break;
                case "11":
                    System.out.println("Logging out from Librarian menu...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 11.");
            }
        }
    }

    private static void addNewBook(Library library, Scanner scanner) {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Enter Author Name: ");
            String author = scanner.nextLine().trim();
            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Book book = new Book(id, title, author, price);
            if (library.addBook(book)) {
                System.out.println("Book '" + title + "' added successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input!");
        }
    }

    private static void removeBook(Library library, Scanner scanner) {
        try {
            System.out.print("Enter Book ID to remove: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            library.removeBook(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric Book ID!");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchBook(Library library, Scanner scanner) {
        System.out.print("Enter search keyword (Title/Author): ");
        String keyword = scanner.nextLine().trim();
        library.searchBook(keyword);
    }

    private static void sortBooksMenu(Library library, Scanner scanner) {
        System.out.println("\n--- SORT BOOKS ---");
        System.out.println("1. By Title (Default)");
        System.out.println("2. By Author");
        System.out.println("3. By Price");
        System.out.print("Select sorting criteria (1-3): ");

        String sortChoice = scanner.nextLine().trim();
        switch (sortChoice) {
            case "1":
                library.sortByTitle();
                System.out.println("\nBooks sorted by Title:");
                library.displayAllBooks();
                break;
            case "2":
                library.sortByAuthor();
                System.out.println("\nBooks sorted by Author:");
                library.displayAllBooks();
                break;
            case "3":
                library.sortByPrice();
                System.out.println("\nBooks sorted by Price:");
                library.displayAllBooks();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    // STUDENT MENU & HANDLER

    private static void handleStudentMenu(Library library, Scanner scanner) {
        System.out.print("Enter Student / Member ID: ");
        int memberId;
        try {
            memberId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Member ID format!");
            return;
        }

        Member member = library.getMember(memberId);
        if (member == null) {
            System.out.println("Member ID " + memberId + " not found!");
            System.out.print("Would you like to register as a new student? (y/n): ");
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter your Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter your Email: ");
                String email = scanner.nextLine().trim();

                member = new Member(memberId, name, email);
                if (library.addMember(member)) {
                    System.out.println("Registration successful! Welcome, " + name + ".");
                } else {
                    return;
                }
            } else {
                return;
            }
        }

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n----------------------------------------");
            System.out.println("     STUDENT MENU (Member: " + member.getName() + " | ID: " + memberId + ")");
            System.out.println("----------------------------------------");
            System.out.println("1. View Available Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. View My Issued Books");
            System.out.println("5. Return Book");
            System.out.println("6. View My Transactions");
            System.out.println("7. Logout");
            System.out.println("----------------------------------------");
            System.out.print("Enter choice (1-7): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    displayAvailableBooks(library);
                    break;
                case "2":
                    searchBook(library, scanner);
                    break;
                case "3":
                    issueBookStudent(library, scanner, memberId);
                    break;
                case "4":
                    viewMyIssuedBooks(library, memberId);
                    break;
                case "5":
                    returnBookStudent(library, scanner, memberId);
                    break;
                case "6":
                    viewMyTransactions(library, memberId);
                    break;
                case "7":
                    System.out.println("Logging out from Student menu...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 7.");
            }
        }
    }

    private static void displayAvailableBooks(Library library) {
        ArrayList<Book> available = library.getAvailableBooks();
        if (available.isEmpty()) {
            System.out.println("No books are currently available in the library.");
            return;
        }
        System.out.println("\n--- AVAILABLE BOOKS ---");
        for (Book b : available) {
            b.displayBook();
        }
    }

    private static void issueBookStudent(Library library, Scanner scanner, int memberId) {
        try {
            System.out.print("Enter Book ID to issue: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Select Borrowing Period:");
            System.out.println("1. 5 Days");
            System.out.println("2. 7 Days");
            System.out.println("3. 10 Days");
            System.out.print("Choice (1-3, default 14 days): ");

            String periodChoice = scanner.nextLine().trim();
            int days = 14;
            if (periodChoice.equals("1"))
                days = 5;
            else if (periodChoice.equals("2"))
                days = 7;
            else if (periodChoice.equals("3"))
                days = 10;

            library.issueBook(bookId, memberId, days);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input!");
        } catch (BookNotFoundException | BookAlreadyIssuedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBookStudent(Library library, Scanner scanner, int memberId) {
        try {
            System.out.print("Enter Book ID to return: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            library.returnBook(bookId, memberId);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input!");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewMyIssuedBooks(Library library, int memberId) {
        ArrayList<Book> issued = library.getStudentIssuedBooks(memberId);
        if (issued.isEmpty()) {
            System.out.println("You have no currently issued books.");
            return;
        }
        System.out.println("\n--- MY ISSUED BOOKS ---");
        for (Book b : issued) {
            b.displayBook();
        }
    }

    private static void viewMyTransactions(Library library, int memberId) {
        ArrayList<Transaction> txns = library.getStudentTransactions(memberId);
        if (txns.isEmpty()) {
            System.out.println("No transaction history found for your account.");
            return;
        }
        System.out.println("\n--- MY TRANSACTIONS ---");
        for (Transaction t : txns) {
            System.out.println(t);
        }
    }
}