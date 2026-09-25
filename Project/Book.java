package Project;

/**
 * Book.java
 * Owned by: Person A (Book Management)
 *
 * Represents a single Book in the Library Management System.
 * Implements Comparable so Collections.sort(books) sorts by TITLE by default.
 */
public class Book implements Comparable<Book> {

    private int bookId;
    private String title;
    private String author;
    private double price;
    private boolean issued;

    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isIssued() {
        return issued;
    }

    // Called by Library.java (Person D) when a book is issued/returned
    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    // Prints one formatted line for this book — used by Library/Main for listings
    public void displayBook() {
        System.out.printf("[%d] %-25s | %-15s | Rs.%-8.2f | %s%n",
                bookId, title, author, price, issued ? "Issued" : "Available");
    }

    // Default sort order: by title (A-Z)
    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s by %s (Rs.%.2f) - %s",
                bookId, title, author, price, issued ? "Issued" : "Available");
    }
}