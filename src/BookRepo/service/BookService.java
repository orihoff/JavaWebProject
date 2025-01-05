package BookRepo.service;

import BookRepo.dal.BookDao;
import BookRepo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDao.getAll();
    }

	public void addBook(String title, String author, String genre, int year) throws Exception {
		if (bookDao.getAll().size() >= 100) {
			throw new Exception("Cannot save more than 100 books.");
		}
		Book book = new Book(title, author, genre, year);
		bookDao.save(book);
	}

    public void updateBook(Book book) throws Exception {
        bookDao.update(book);
    }

    public void deleteBook(String id) throws Exception { // Change int to String
        bookDao.delete(id); // Ensure DAO uses String
    }

    public Book getBookById(String id) throws Exception { // Change int to String
        return bookDao.get(id); // Ensure DAO uses String
    }

}
