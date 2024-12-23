package BookRepo.dal;

import java.util.List;

public interface BookDao {
    List<Book> getAll() throws Exception; // טוען את כל הספרים ברשימה ממוינת
    void save(Book book) throws Exception; // שומר ספר חדש במיקום מתאים
    void update(Book book) throws Exception; // מעדכן ספר קיים
    void delete(String id) throws Exception; // מוחק ספר לפי מזהה
    Book get(String id) throws Exception; // טוען ספר לפי מזהה
}
