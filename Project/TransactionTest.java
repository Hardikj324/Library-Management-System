package Project;

public class TransactionTest {

    public static void main(String[] args) {

        Book book = new Book(1, "Java Programming", "James Gosling", 500);

        Member member = new Member(
                101,
                "Pranay",
                "pranay@gmail.com"
        );

        Transaction transaction =
                new Transaction(1, book, member);

        System.out.println("Transaction created:");
        System.out.println(transaction);

        System.out.println("\nBefore return:");
        System.out.println("Returned: " + transaction.isReturned());

        transaction.returnBook();

        System.out.println("\nAfter return:");
        System.out.println("Returned: " + transaction.isReturned());

        System.out.println("\nTransaction test completed successfully!");
    }
}