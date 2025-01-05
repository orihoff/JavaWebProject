package BookRepo.entity;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {

	
	private int id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String content;
    static int currentId = 1;

    public Book(String title, String author, String genre, int publicationYear ) {
        this.id = Book.currentId;
        Book.currentId++;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Getters and Setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return id == book.id;
    }

    @Override
    public int compareTo(Book other) {
        return this.id - other.id;
    }

    @Override
    public String toString() {
        return "Book: \n" +
                "id='" + id + '\n' +
                ", title='" + title + '\n'+
                ", author='" + author + '\n' +
                ", genre='" + genre + '\n' +
                ", publicationYear=" + publicationYear;
    }
}
