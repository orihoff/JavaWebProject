package workout;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
	
	public static void main(String[] args) {
	
	// Load the Spring configuration file
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	// Retrieve a bean from the Spring Container
	
		BasketballCoach coach = context.getBean("myCoach", BasketballCoach.class);
	
		Trainee trainee = new Trainee("Moshe");
	
	// Call methods on the bean
	
		System.out.println(coach.getDailyWorkout(trainee));
		
		System.out.println( coach.getDailyFortune(trainee));
		System.out.println( coach.getDailyFortune(trainee));
		System.out.println( coach.getDailyFortune(trainee));
		context.close();
	
	}

}