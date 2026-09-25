package Project;

public class LibraryTest {

    public static void main(String[] args) {

        Library library = new Library();

        // Create books
        Book book1 = new Book(
            1,
            "Java Programming",
            "James Gosling",
            500
        );

        Book book2 = new Book(
            2,
            "Python Basics",
            "Guido van Rossum",
            400
        );

        // Create member
        Member member = new Member(
            101,
            "Pranay",
            "pranay@gmail.com"
        );

        // Add book and member
        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member);

        System.out.println("Library setup completed.");

        // Issue book
        System.out.println("\n--- Issuing Book ---");

        try {
            library.issueBook(1, 101);
        } catch (BookNotFoundException | BookAlreadyIssuedException e) {
            System.out.println(e.getMessage());
        }

        // Try issuing same book again
        System.out.println("\n--- Issuing Same Book Again ---");

        try {
            library.issueBook(1, 101);
        } catch (BookNotFoundException | BookAlreadyIssuedException e) {
            System.out.println(e.getMessage());
        }

        // Return book
        System.out.println("\n--- Returning Book ---");

        try {
            library.returnBook(1);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Issue again after returning
        System.out.println("\n--- Issuing Book Again ---");

        try {
            library.issueBook(1, 101);
        } catch (BookNotFoundException | BookAlreadyIssuedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nLibrary test completed successfully!");
    }
}