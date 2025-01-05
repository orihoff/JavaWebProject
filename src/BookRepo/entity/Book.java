package BookRepo.entity;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {

	
	private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String content;
    static int currentId = 1;

    public Book(String title, String author, String genre, int publicationYear ) {
    	
        this.id = String.valueOf(Book.currentId);
        Book.currentId++;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Getters and Setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return id.equals(book.id); // Compare strings correctly
    }

    @Override
    public int compareTo(Book other) {
        return this.id.compareTo(other.id); // Use String's compareTo method
    }


    @Override
    public String toString() {
        return "\nBook: \n" +
                "\tID: " + id + "\n" +
                "\tTitle: " + title + "\n"+
                "\tAuthor: " + author + "\n" +
                "\tGenre: " + genre + "\n" +
                "\tPublication Year: " + publicationYear;
    }
}
