package BookRepo.dal;


import java.util.List;

import BookRepo.entity.Book;

public interface BookDao {
	public List<Book> getAll() throws Exception; // Returns sorted book list
	public void save(Book book) throws Exception; // Adds book to storage
	public void update(Book book) throws Exception; // Update existing book
	public void delete(String id) throws Exception; // Delete book by ID
	public Book get(String id) throws Exception; // Returns book by ID (null if does not exist).
}