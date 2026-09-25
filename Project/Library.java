package Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import Project.comparators.AuthorComparator;
import Project.comparators.PriceComparator;

/**
 * Library.java
 * Central class connecting Book, Member, and Transaction models.
 */
public class Library {

    private ArrayList<Book> books;
    private HashMap<Integer, Member> members;
    private ArrayList<Transaction> transactions;
    private Set<Integer> bookIds;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        members = new HashMap<>();
        transactions = new ArrayList<>();
        bookIds = new HashSet<>();
    }

    
    // BOOK OPERATIONS
    

    public boolean addBook(Book book) {
        if (book == null) return false;
        if (bookIds.contains(book.getBookId())) {
            System.out.println("Error: Book with ID " + book.getBookId() + " already exists!");
            return false;
        }
        books.add(book);
        bookIds.add(book.getBookId());
        return true;
    }

    public void removeBook(int bookId) throws BookNotFoundException {
        Book book = getBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        if (book.isIssued()) {
            System.out.println("Cannot remove Book ID " + bookId + " because it is currently issued!");
            return;
        }
        books.remove(book);
        bookIds.remove(bookId);
        System.out.println("Book ID " + bookId + " removed successfully!");
    }

    public ArrayList<Book> searchBook(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Please enter a valid search keyword!");
            return results;
        }
        String lowerKeyword = keyword.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lowerKeyword) ||
                book.getAuthor().toLowerCase().contains(lowerKeyword)) {
                results.add(book);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No books found matching keyword: \"" + keyword + "\"");
        } else {
            System.out.println("\n--- Search Results for \"" + keyword + "\" ---");
            for (Book b : results) {
                b.displayBook();
            }
        }
        return results;
    }

    public Book getBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book findBook(int bookId) {
        return getBook(bookId);
    }

    
    // MEMBER OPERATIONS
    

    public boolean addMember(Member member) {
        if (member == null || !member.validateMember()) {
            System.out.println("Error: Invalid member details!");
            return false;
        }
        if (members.containsKey(member.getMemberId())) {
            System.out.println("Error: Member with ID " + member.getMemberId() + " already exists!");
            return false;
        }
        members.put(member.getMemberId(), member);
        return true;
    }

    public void removeMember(int memberId) {
        Member member = getMember(memberId);
        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }
        // Check if member has active transactions
        for (Transaction t : transactions) {
            if (t.getMemberId() == memberId && !t.isReturned()) {
                System.out.println("Cannot remove member with ID " + memberId + " because they have active issued books!");
                return;
            }
        }
        members.remove(memberId);
        System.out.println("Member with ID " + memberId + " removed successfully!");
    }

    public Member getMember(int memberId) {
        return members.get(memberId);
    }

    public Member findMember(int memberId) {
        return getMember(memberId);
    }

    
    // TRANSACTION OPERATIONS
    

    public void issueBook(int bookId, int memberId, int days)
            throws BookNotFoundException, BookAlreadyIssuedException {
        Book book = getBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        if (book.isIssued()) {
            throw new BookAlreadyIssuedException("Book '" + book.getTitle() + "' (ID: " + bookId + ") is already issued.");
        }
        Member member = getMember(memberId);
        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found. Cannot issue book.");
            return;
        }

        book.setIssued(true);
        int txnId = transactions.size() + 1;
        Transaction transaction = new Transaction(txnId, book, member, days);
        transactions.add(transaction);

        System.out.println("Book '" + book.getTitle() + "' issued successfully to " + member.getName() + " for " + (days > 0 ? days : 14) + " days! (Due: " + transaction.getDueDate() + ")");
    }

    public void issueBook(int bookId, int memberId)
            throws BookNotFoundException, BookAlreadyIssuedException {
        issueBook(bookId, memberId, 14);
    }

    public void returnBook(int bookId, int memberId) throws BookNotFoundException {
        Book book = getBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }

        Transaction activeTxn = null;
        for (Transaction t : transactions) {
            if (t.getBookId() == bookId && t.getMemberId() == memberId && !t.isReturned()) {
                activeTxn = t;
                break;
            }
        }

        if (activeTxn == null) {
            for (Transaction t : transactions) {
                if (t.getBookId() == bookId && !t.isReturned()) {
                    activeTxn = t;
                    break;
                }
            }
        }

        if (activeTxn == null) {
            System.out.println("No active issue record found for Book ID " + bookId + ".");
            return;
        }

        activeTxn.returnBook();
        System.out.println("Book '" + book.getTitle() + "' returned successfully!");
    }

    public void returnBook(int bookId) throws BookNotFoundException {
        Book book = getBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        for (Transaction t : transactions) {
            if (t.getBookId() == bookId && !t.isReturned()) {
                returnBook(bookId, t.getMemberId());
                return;
            }
        }
        System.out.println("This book is not currently issued.");
    }

    
    // STUDENT OPERATIONS
    

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (!book.isIssued()) {
                available.add(book);
            }
        }
        return available;
    }

    public ArrayList<Transaction> getStudentTransactions(int memberId) {
        ArrayList<Transaction> studentTxns = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getMemberId() == memberId) {
                studentTxns.add(t);
            }
        }
        return studentTxns;
    }

    public ArrayList<Book> getStudentIssuedBooks(int memberId) {
        ArrayList<Book> issued = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getMemberId() == memberId && !t.isReturned()) {
                if (t.getBook() != null) {
                    issued.add(t.getBook());
                }
            }
        }
        return issued;
    }

    
    // LIBRARIAN OPERATIONS
    

    public ArrayList<Book> getIssuedBooks() {
        ArrayList<Book> issuedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isIssued()) {
                issuedBooks.add(book);
            }
        }
        return issuedBooks;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return transactions;
    }

    public ArrayList<Book> getOverdueBooks() {
        ArrayList<Book> overdue = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.isOverdue()) {
                if (t.getBook() != null) {
                    overdue.add(t.getBook());
                }
            }
        }
        return overdue;
    }

    // Counting helper methods
    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBookCount() {
        return getAvailableBooks().size();
    }

    public int getIssuedBookCount() {
        return getIssuedBooks().size();
    }

    public int getMemberCount() {
        return members.size();
    }

    public int getOverdueBookCount() {
        return getOverdueBooks().size();
    }

    
    // SORTING OPERATIONS
    

    public void sortByTitle() {
        Collections.sort(books);
    }

    public void sortByAuthor() {
        Collections.sort(books, new AuthorComparator());
    }

    public void sortByPrice() {
        Collections.sort(books, new PriceComparator());
    }

    
    // DISPLAY HELPER METHODS FOR CLI
    

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-6s | %-25s | %-18s | %-10s | %s%n", "ID", "Title", "Author", "Price", "Status");
        System.out.println("------------------------------------------------------------------");
        for (Book b : books) {
            b.displayBook();
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered in the library.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-25s%n", "Member ID", "Name", "Email");
        System.out.println("------------------------------------------------------------------");
        for (Member m : members.values()) {
            System.out.printf("%-10d | %-20s | %-25s%n", m.getMemberId(), m.getName(), m.getEmail());
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayIssuedBooks() {
        ArrayList<Book> issued = getIssuedBooks();
        if (issued.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("CURRENTLY ISSUED BOOKS:");
        for (Transaction t : transactions) {
            if (!t.isReturned()) {
                System.out.println(t);
            }
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayOverdueBooks() {
        ArrayList<Book> overdue = getOverdueBooks();
        if (overdue.isEmpty()) {
            System.out.println("No books are currently overdue.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("OVERDUE BOOKS:");
        for (Transaction t : transactions) {
            if (t.isOverdue()) {
                System.out.println(t);
            }
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("ALL TRANSACTION HISTORY:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayStatistics() {
        System.out.println("\n========================================");
        System.out.println("          LIBRARY STATISTICS            ");
        System.out.println("========================================");
        System.out.println(" Total Books          : " + getTotalBooks());
        System.out.println(" Available Books      : " + getAvailableBookCount());
        System.out.println(" Issued Books         : " + getIssuedBookCount());
        System.out.println(" Overdue Books        : " + getOverdueBookCount());
        System.out.println(" Total Members        : " + getMemberCount());
        System.out.println(" Total Transactions   : " + transactions.size());
        System.out.println("========================================\n");
    }
}