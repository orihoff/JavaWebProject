package workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myCoach")
public class BasketballCoach implements Coach {
	@Autowired
	CircularFortuneService fourtune;
	
	public String getDailyWorkout(Trainee t) {
		
		return String.format("%s, spend 30 minutes on shooting baskets",t.getName()) ;
	
	}
	public String getDailyFortune(Trainee t) {
		return fourtune.getFortune();
	}
	
	
}
