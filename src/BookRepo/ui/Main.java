package BookRepo.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookCLI cli = context.getBean(BookCLI.class);
        
        cli.start();
        
        
        context.close();
    }
}
