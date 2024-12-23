package workout;

import org.springframework.stereotype.Component;


public class Trainee {

	private String name;
	
	public Trainee(String name) {
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	
	}
	
	public void setName(String name) {
		this.name = name;
	}

}