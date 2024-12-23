package BookRepo.dal;

import java.util.Scanner;

public class Ui_class {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Book Repository Menu ===");
            System.out.println("1. Show all books");
            System.out.println("2. Show single book");
            System.out.println("3. Add book");
            System.out.println("4. Update book");
            System.out.println("5. Delete book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showAllBooks();
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    String idToShow = scanner.nextLine();
                    showSingleBook(idToShow);
                    break;
                case 3:
                    addBook(scanner);
                    break;
                case 4:
                    System.out.print("Enter book ID to update: ");
                    String idToUpdate = scanner.nextLine();
                    updateBook(scanner, idToUpdate);
                    break;
                case 5:
                    System.out.print("Enter book ID to delete: ");
                    String idToDelete = scanner.nextLine();
                    deleteBook(idToDelete);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting the application. Goodbye!");
    }

    private static void showAllBooks() {
        // Logic to display all books
        System.out.println("Show all books functionality to be implemented.");
    }

    private static void showSingleBook(String id) {
        // Logic to display a single book by ID
        System.out.println("Show single book functionality to be implemented for ID: " + id);
    }

    private static void addBook(Scanner scanner) {
        // Logic to add a new book
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Add book functionality to be implemented for: " + title);
    }

    private static void updateBook(Scanner scanner, String id) {
        // Logic to update book details
        System.out.println("Update book functionality to be implemented for ID: " + id);
    }

    private static void deleteBook(String id) {
        // Logic to delete a book by ID
        System.out.println("Delete book functionality to be implemented for ID: " + id);
    }
}
