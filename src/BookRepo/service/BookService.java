package BookRepo.service;

import BookRepo.dal.BookDao;
import BookRepo.entity.Book;
import BookRepo.exceptions.ExceedTitleLengthException;
import BookRepo.exceptions.InvalidBookYearException;
import BookRepo.exceptions.MissingRequiredBookFieldsException;
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
	
	@Value("${whenWasTheFirstBookwriten}")
	private int MinYear;
	
	
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
			throw new MissingRequiredBookFieldsException();
		}
		if (year < MinYear) {
            throw new InvalidBookYearException(MinYear);
		}
		if (title.length() > maxCharactersinBookName) {
	        throw new ExceedTitleLengthException(maxCharactersinBookName);
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
