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

    public void addBook(Book book) throws Exception {
        if (bookDao.getAll().size() >= 100) {
            throw new Exception("Cannot save more than 100 books.");
        }
        bookDao.save(book);
    }

    public void updateBook(Book book) throws Exception {
        bookDao.update(book);
    }

    public void deleteBook(String id) throws Exception {
        bookDao.delete(id);
    }

    public Book getBookById(String id) throws Exception {
        return bookDao.get(id);
    }
}
