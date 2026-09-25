package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Project.comparators.AuthorComparator;
import Project.comparators.PriceComparator;

/**
 * BookTest.java
 * NOT part of the shared file list in the design doc — this is just for
 * YOU to verify Book.java + the comparators work before you push.
 * Do not add this file to the repo; delete it (or keep it local) once
 * you're confident your module is correct. Library.java (Person D) is
 * the one that will actually create/hold/manage the list of books.
 */
public class BookTest {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(101, "Clean Code", "Robert C. Martin", 899.50));
        books.add(new Book(102, "The Alchemist", "Paulo Coelho", 299.00));
        books.add(new Book(103, "1984", "George Orwell", 250.00));
        books.add(new Book(104, "Atomic Habits", "James Clear", 399.00));
        books.add(new Book(105, "Java: The Complete Reference", "Herbert Schildt", 799.00));
        books.add(new Book(106, "To Kill a Mockingbird", "Harper Lee", 349.00));
        books.add(new Book(107, "The Hobbit", "J.R.R. Tolkien", 450.00));
        books.add(new Book(108, "Sapiens", "Yuval Noah Harari", 550.00));
        books.add(new Book(109, "The Pragmatic Programmer", "Andrew Hunt", 950.00));
        books.add(new Book(110, "Think and Grow Rich", "Napoleon Hill", 199.00));
        books.add(new Book(111, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 499.00));
        books.add(new Book(112, "The Great Gatsby", "F. Scott Fitzgerald", 275.00));
        books.add(new Book(113, "Introduction to Algorithms", "Thomas H. Cormen", 1250.00));
        books.add(new Book(114, "Wings of Fire", "A.P.J. Abdul Kalam", 220.00));
        books.add(new Book(115, "The Silent Patient", "Alex Michaelides", 399.00));
        books.add(new Book(116, "Rich Dad Poor Dad", "Robert Kiyosaki", 299.00));
        books.add(new Book(117, "Design Patterns", "Erich Gamma", 899.00));
        books.add(new Book(118, "The Kite Runner", "Khaled Hosseini", 349.00));
        books.add(new Book(119, "Effective Java", "Joshua Bloch", 850.00));
        books.add(new Book(120, "A Brief History of Time", "Stephen Hawking", 399.00));

        System.out.println("Original order:");
        for (Book b : books) b.displayBook();

        System.out.println("\nSorted by Title (Comparable):");
        Collections.sort(books);
        for (Book b : books) b.displayBook();

        System.out.println("\nSorted by Author:");
        Collections.sort(books, new AuthorComparator());
        for (Book b : books) b.displayBook();

        System.out.println("\nSorted by Price:");
        Collections.sort(books, new PriceComparator());
        for (Book b : books) b.displayBook();
    }
}