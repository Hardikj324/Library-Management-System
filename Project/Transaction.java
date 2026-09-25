package Project;

import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate returnDate;

    // Constructor
    public Transaction(int transactionId, Book book, Member member) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.issueDate = LocalDate.now();
        this.returnDate = null;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Return book
    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.setIssued(false);
    }

    // Check whether book has been returned
    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", book=" + book.getTitle() +
                ", member=" + member.getName() +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
