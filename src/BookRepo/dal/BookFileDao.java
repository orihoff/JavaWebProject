package BookRepo.dal;

import BookRepo.entity.Book;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookFileDao implements BookDao {

    private final String filePath = "./books.dat";
    private List<Book> books;

    public BookFileDao() {
        loadBooks();
    }

    @Override
    public List<Book> getAll() throws Exception {
        Collections.sort(books);
        return new ArrayList<>(books);
    }

    @Override
    public void save(Book book) throws Exception {
        if (books.contains(book)) {
            throw new Exception("Book with ID " + book.getId() + " already exists.");
        }
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public void update(Book book) throws Exception {
        Book existingBook = get(book.getId());
        if (existingBook == null) {
            throw new Exception("Book with ID " + book.getId() + " not found.");
        }
        books.remove(existingBook);
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public void delete(String id) throws Exception {
        Book book = get(id);
        if (book == null) {
            throw new Exception("Book with ID " + id + " not found.");
        }
        books.remove(book);
        saveBooksToFile();
    }

    @Override
    public Book get(String id) throws Exception {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            books = new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error loading books from file.", e);
        }
    }

    private void saveBooksToFile() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
        } catch (Exception e) {
            throw new RuntimeException("Error saving books to file.", e);
        }
    }
}
