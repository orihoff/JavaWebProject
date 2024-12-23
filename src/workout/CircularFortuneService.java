package workout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:furtunes.properties")
public class CircularFortuneService implements FortuneService{
	@Value("${list_of_fortune}")
	private String[] furtunes;
	private int currentIndex = 0;
	@Override
	public String getFortune() {
		return furtunes[currentIndex++];
	}

}