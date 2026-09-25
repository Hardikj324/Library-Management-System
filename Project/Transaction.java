package Project;

import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private int bookId;
    private int memberId;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    // Main Constructor with days
    public Transaction(int transactionId, Book book, Member member, int days) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.bookId = (book != null) ? book.getBookId() : 0;
        this.memberId = (member != null) ? member.getMemberId() : 0;
        this.issueDate = LocalDate.now();
        this.dueDate = this.issueDate.plusDays(days > 0 ? days : 14);
        this.returnDate = null;
        this.returned = false;
    }

    // Constructor default duration (14 days)
    public Transaction(int transactionId, Book book, Member member) {
        this(transactionId, book, member, 14);
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public int getBookId() {
        return (book != null) ? book.getBookId() : bookId;
    }

    public int getMemberId() {
        return (member != null) ? member.getMemberId() : memberId;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Check whether book has been returned
    public boolean isReturned() {
        return returned || returnDate != null;
    }

    // Check if transaction is overdue
    public boolean isOverdue() {
        return !isReturned() && LocalDate.now().isAfter(dueDate);
    }

    // Return book
    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.returned = true;
        if (book != null) {
            book.setIssued(false);
        }
    }

    @Override
    public String toString() {
        String bookTitle = (book != null) ? book.getTitle() : ("Book ID " + bookId);
        String memberName = (member != null) ? member.getName() : ("Member ID " + memberId);
        return String.format("Txn #%d | Book: %s | Member: %s | Issued: %s | Due: %s | Status: %s",
                transactionId, bookTitle, memberName, issueDate, dueDate,
                isReturned() ? ("Returned (" + returnDate + ")") : (isOverdue() ? "OVERDUE" : "Active"));
    }
}
