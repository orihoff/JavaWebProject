package BookRepo.ui;

import BookRepo.ui.BookCLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("BookRepo");
        BookCLI cli = context.getBean(BookCLI.class);
        cli.start();
    }
}
