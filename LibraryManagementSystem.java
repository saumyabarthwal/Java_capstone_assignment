import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Title: " + title +
               ", Author: " + author +
               ", Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Add a book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    //Method Overloading (Search by Title) 
    public Book searchBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    //  Method Overloading (Search by Author)
    public ArrayList<Book> searchBookByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                result.add(b);
            }
        }
        return result;
    }

    // Display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library!");
            return;
        }

        System.out.println("\n--- Library Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Search Book by Author");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(title, author));
                    break;

                case 2:
                    System.out.print("Enter Book Title to Borrow: ");
                    String borrowTitle = sc.nextLine();

                    Book bookToBorrow = library.searchBook(borrowTitle);

                    if (bookToBorrow == null) {
                        System.out.println("Book not found!");
                    } else if (bookToBorrow.isBorrowed()) {
                        System.out.println("Book is already borrowed!");
                    } else {
                        bookToBorrow.borrowBook();
                        System.out.println("Book borrowed successfully!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Book Title to Return: ");
                    String returnTitle = sc.nextLine();

                    Book bookToReturn = library.searchBook(returnTitle);

                    if (bookToReturn == null) {
                        System.out.println("Book not found!");
                    } else if (!bookToReturn.isBorrowed()) {
                        System.out.println("This book was not borrowed!");
                    } else {
                        bookToReturn.returnBook();
                        System.out.println("Book returned successfully!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book Title to Search: ");
                    String searchTitle = sc.nextLine();

                    Book foundBook = library.searchBook(searchTitle);

                    if (foundBook == null) {
                        System.out.println("Book not found!");
                    } else {
                        System.out.println("Book Found: " + foundBook);
                    }
                    break;

                case 5:
                    System.out.print("Enter Author Name to Search: ");
                    String searchAuthor = sc.nextLine();

                    ArrayList<Book> booksByAuthor = library.searchBookByAuthor(searchAuthor);

                    if (booksByAuthor.isEmpty()) {
                        System.out.println("No books found for this author.");
                    } else {
                        System.out.println("Books by " + searchAuthor + ":");
                        for (Book b : booksByAuthor) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 6:
                    library.displayBooks();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
