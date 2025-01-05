package BookRepo.service;

import BookRepo.dal.BookDao;
import BookRepo.entity.Book;
import BookRepo.exceptions.StorageLimitExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:params.properties")
public class BookService {
	@Value("${maxBooksAllowed}")
	private int maxNumofBooks;

	@Value("${maxWordsInBook}")
	private int maxCharactersinBookName;
	
	
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDao.getAll();
    }

	public void addBook(String title, String author, String genre, int year) throws Exception {
		if (bookDao.getAll().size() >= maxNumofBooks) {
			throw new StorageLimitExceededException();
		}
		if (title == null || title.isEmpty() || author == null || author.isEmpty() || genre == null
				|| genre.isEmpty()) {
			throw new Exception("Title, author, and genre are required fields.");
		}
		if (title.length() > maxCharactersinBookName) {
	        throw new Exception("Title cannot exceed " + maxCharactersinBookName + " characters.");
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
