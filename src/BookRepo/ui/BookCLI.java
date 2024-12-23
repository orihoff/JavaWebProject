package BookRepo.ui;

import BookRepo.entity.Book;
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

        while (!exit) {
            System.out.println("\n=== Book Repository ===");
            System.out.println("	1. Show all books");
            System.out.println("	2. Add a book");
            System.out.println("	3. Update a book");
            System.out.println("	4. Delete a book");
            System.out.println("	5. Find a book by ID");
            System.out.println("	0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        bookService.getAllBooks().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter publication year: ");
                        int year = scanner.nextInt();
                        bookService.addBook(new Book(id, title, author, genre, year));
                        break;
                    case 3:
                        // Update logic
                        break;
                    case 4:
                        System.out.print("Enter ID to delete: ");
                        bookService.deleteBook(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter ID: ");
                        System.out.println(bookService.getBookById(scanner.nextLine()));
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
