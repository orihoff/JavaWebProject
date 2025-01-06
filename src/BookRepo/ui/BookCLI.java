package BookRepo.ui;

import BookRepo.entity.Book;
import BookRepo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookCLI {

    private static final int SHOW_ALL_BOOKS = 1;
    private static final int ADD_BOOK = 2;
    private static final int UPDATE_BOOK = 3;
    private static final int DELETE_BOOK = 4;
    private static final int FIND_BOOK_BY_ID = 5;
    private static final int EXIT = 0;

    private final BookService bookService;

    @Autowired
    public BookCLI(BookService bookService) {
        this.bookService = bookService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = readChoice(scanner);

            switch (choice) {
                case SHOW_ALL_BOOKS:
					showAllBooks();
				
                    break;
                case ADD_BOOK:
                    addBook(scanner);
                    break;
                case UPDATE_BOOK:
                    updateBook(scanner);
                    break;
                case DELETE_BOOK:
                    deleteBook(scanner);
                    break;
                case FIND_BOOK_BY_ID:
                    findBookById(scanner);
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid menu option.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n=== Book Repository ===");
        System.out.println("    " + SHOW_ALL_BOOKS + ". Show all books");
        System.out.println("    " + ADD_BOOK + ". Add a book");
        System.out.println("    " + UPDATE_BOOK + ". Update a book");
        System.out.println("    " + DELETE_BOOK + ". Delete a book");
        System.out.println("    " + FIND_BOOK_BY_ID + ". Find a book by ID");
        System.out.println("    " + EXIT + ". Exit");
        System.out.print("Choose an option: ");
    }

    private int readChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1; // ערך ברירת מחדל המבטא שגיאה
        }
    }

    private void showAllBooks() {
    	
        try {
			bookService.getAllBooks().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void addBook(Scanner scanner) {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter publication year: ");
            int year = Integer.parseInt(scanner.nextLine());
            bookService.validateBookFields(title, author, genre, year);
            Book book = new Book(title, author, genre, year);
            bookService.addBook(book);
            System.out.println("Book added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateBook(Scanner scanner) {
        try {
            System.out.print("Enter ID of the book to update: ");
            String bookId = scanner.nextLine();
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new author: ");
            String author = scanner.nextLine();
            System.out.print("Enter new genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter new publication year: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            bookService.updateBook(bookId, title, author, genre, year);
            System.out.println("Book updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Enter ID to delete: ");
        String deleteId = scanner.nextLine();
        try {
            bookService.deleteBook(deleteId);
            System.out.println("Book deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findBookById(Scanner scanner) {
        System.out.print("Enter ID: ");
        String searchId = scanner.nextLine();
        try {
            System.out.println(bookService.getBookById(searchId));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
