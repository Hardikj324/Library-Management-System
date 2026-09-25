package Project.comparators;

import java.util.Comparator;
import Project.Book;

/**
 * comparators/PriceComparator.java
 * Owned by: Person A (Book Management)
 * Sorts books by price (low to high).
 */
public class PriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Double.compare(b1.getPrice(), b2.getPrice());
    }
}