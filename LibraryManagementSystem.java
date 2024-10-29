import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    public void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();
        members.add(new Student(name, id));
        System.out.println("Member added successfully.");
    }

    public void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        Book bookToBorrow = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                bookToBorrow = book;
                break;
            }
        }

        Member borrowingMember = null;
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(memberId)) {
                borrowingMember = member;
                break;
            }
        }

        if (bookToBorrow != null && borrowingMember != null) {
            bookToBorrow.setAvailable(false);
            transactions.add(new Transaction(bookToBorrow, borrowingMember));
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is not available or Member not found.");
        }
    }

    public void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        for (Transaction transaction : transactions) {
            if (transaction.book.getTitle().equalsIgnoreCase(title) &&
                    transaction.member.getId().equalsIgnoreCase(memberId) &&
                    transaction.returnDate == null) {
                transaction.returnBook();
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() +
                    ", Available: " + book.isAvailable());
        }
    }

    public void displayTransactions() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Transactions");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    library.addMember();
                    break;
                case 3:
                    library.borrowBook();
                    break;
                case 4:
                    library.returnBook();
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.displayTransactions();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
