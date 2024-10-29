import java.util.Date;

public class Transaction {
    Book book;
    Member member;
    Date borrowDate;
    Date returnDate;

    public Transaction(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.borrowDate = new Date();
    }

    public void returnBook() {
        this.returnDate = new Date();
        book.setAvailable(true);
    }

    public void displayTransaction() {
        System.out.println("Book: " + book.getTitle() + ", Borrowed by: " + member.getName() +
                           ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate);
    }
}
