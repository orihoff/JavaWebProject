package BookRepo.service;

import BookRepo.dal.BookDao;
import BookRepo.entity.Book;
import BookRepo.exceptions.BookNotFoundException;
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

    public void updateBook(String id,String title, String author, String genre, int year) throws Exception {
    	// Fetch the existing book by ID
        Book existingBook = bookDao.get(id);
        
        // If the book does not exist, throw an exception
        if (existingBook == null) {
            throw new BookNotFoundException(id);
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
     // If the book exists, create a new Book object with the updated details
        existingBook.setTitle(title);
        existingBook.setAuthor(author);
        existingBook.setGenre(genre);
        existingBook.setPublicationYear(year);
        
        // Call the update method of the DAO to update the book in the repository
        bookDao.update(existingBook);

    }

    public void deleteBook(String id) throws Exception { // Change int to String
        bookDao.delete(id); // Ensure DAO uses String
    }

    public Book getBookById(String id) throws Exception { // Change int to String
        return bookDao.get(id); // Ensure DAO uses String
    }

}
