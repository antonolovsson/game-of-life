package idv.koppen.gameoflife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GameOfLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfLifeApplication.class, args);
		Runner runner = new Runner();
		try {
			runner.run();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
