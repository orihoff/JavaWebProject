package workout;

import org.springframework.stereotype.Component;

@Component

public class TennisCoach implements Coach {

	public String getDailyWorkout(Trainee t) {
		
		return String.format("%s, play with a friend for 2 hours", t.getName());
		
	}

}