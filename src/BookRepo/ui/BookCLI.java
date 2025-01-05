package BookRepo.ui;

import BookRepo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookCLI {

    private final BookService bookService;

    @Autowired
    public BookCLI(BookService bookService) {
        this.bookService = bookService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String title, author, genre , bookId;
        int year = -1;

        while (!exit) {
            System.out.println("\n=== Book Repository ===");
            System.out.println("    1. Show all books");
            System.out.println("    2. Add a book");
            System.out.println("    3. Update a book");
            System.out.println("    4. Delete a book");
            System.out.println("    5. Find a book by ID");
            System.out.println("    0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Read input safely
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        bookService.getAllBooks().forEach(System.out::println);
                        break;
                    case 2:
                        // Add a book
                        System.out.print("Enter title: ");
                        title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        genre = scanner.nextLine();
                        System.out.print("Enter publication year: ");
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                            bookService.addBook(title, author, genre, year);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year. Please enter a valid number.");
                        }
                        break;
                    case 3:
                    	
                        // Update a book
                        System.out.print("Enter ID of the book to update: ");
                        bookId = scanner.nextLine();
                        System.out.print("Enter new title: ");
                        title = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        author = scanner.nextLine();
                        System.out.print("Enter new genre: ");
                        genre = scanner.nextLine();
                        System.out.print("Enter new publication year: ");
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                            bookService.updateBook(bookId, title, author, genre, year);
                            System.out.println("Book updated successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year. Please enter a valid number.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        break;
                    case 4:
                        // Delete a book
                        System.out.print("Enter ID to delete: ");
                        String deleteId = scanner.nextLine();
                        try {
                            bookService.deleteBook(deleteId);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 5:
                        // Find a book by ID
                        System.out.print("Enter ID: ");
                        String searchId = scanner.nextLine();
                        try {
                            System.out.println(bookService.getBookById(searchId));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
