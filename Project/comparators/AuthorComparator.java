package Project.comparators;

import java.util.Comparator;
import Project.Book;

/**
 * comparators/AuthorComparator.java
 * Owned by: Person A (Book Management)
 * Sorts books alphabetically by author name.
 */
public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
    }
}