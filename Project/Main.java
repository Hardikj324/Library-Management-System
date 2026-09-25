package Project;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        Book book1 = new Book(1, "Java Programming", "James Gosling", 500);
        Book book2 = new Book(2, "Clean Code", "Robert Martin", 600);

        library.addBook(book1);
        library.addBook(book2);

        Member member1 = new Member(
                101,
                "Pranay",
                "pranay@gmail.com");

        library.addMember(member1);
        try {
            library.issueBook(1, 101);
        } catch (BookNotFoundException | BookAlreadyIssuedException e) {
            System.out.println(e.getMessage());
        }

        // Return the book
        try {
            library.returnBook(1);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Library operation completed successfully!");
    }

}