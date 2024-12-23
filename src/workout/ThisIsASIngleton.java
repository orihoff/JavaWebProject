package workout;

import org.springframework.stereotype.Component;

@Component

public class ThisIsASIngleton {

	public ThisIsASIngleton() {
		System.out.println("its a singletom and its created");
	}
	
	
	public void print() {
		System.out.println("this is the only class instence of this class");
	}
	
	
}
