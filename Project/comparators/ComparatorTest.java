package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Project.comparators.AuthorComparator;
import Project.comparators.PriceComparator;

public class ComparatorTest {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();

        books.add(new Book(1, "Java Programming", "James Gosling", 500));
        books.add(new Book(2, "Python Basics", "Guido van Rossum", 400));
        books.add(new Book(3, "C++ Programming", "Bjarne Stroustrup", 600));

        // Sort by Author
        Collections.sort(books, new AuthorComparator());

        System.out.println("Books sorted by Author:");

        for (Book book : books) {
            System.out.println(book);
        }

        // Sort by Price
        Collections.sort(books, new PriceComparator());

        System.out.println("\nBooks sorted by Price:");

        for (Book book : books) {
            System.out.println(book);
        }
    }
}