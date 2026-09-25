package Project;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<Transaction> transactions;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a member to the library
    public void addMember(Member member) {
        members.add(member);
    }

    // Find a book by ID
    public Book findBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {
                return book;
            }
        }

        return null;
    }

    // Find a member by ID
    public Member findMember(int memberId) {

        for (Member member : members) {

            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    // Issue a book to a member
    public void issueBook(int bookId, int memberId)
            throws BookNotFoundException, BookAlreadyIssuedException {

        // Find the book
        Book book = findBook(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }

        // Check if book is already issued
        if (book.isIssued()) {
            throw new BookAlreadyIssuedException(
                    "Book '" + book.getTitle() + "' is already issued.");
        }

        // Find the member
        Member member = null;

        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                member = m;
                break;
            }
        }

        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        // Mark book as issued
        book.setIssued(true);

        // Create transaction
        Transaction transaction = new Transaction(transactions.size() + 1, book, member);

        // Add transaction
        transactions.add(transaction);

        System.out.println("Book issued successfully!");
    }

    // Return a book

    public void returnBook(int bookId) throws BookNotFoundException {

        // Find the book

        Book book = findBook(bookId);

        if (book == null) {

            throw new BookNotFoundException(

                    "Book with ID " + bookId + " not found."

            );

        }

        // Find the active transaction

        for (Transaction transaction : transactions) {

            if (transaction.getBook().getBookId() == bookId

                    && !transaction.isReturned()) {

                transaction.returnBook();

                System.out.println("Book returned successfully!");

                return;

            }

        }

        System.out.println("This book is not currently issued.");

    }
}