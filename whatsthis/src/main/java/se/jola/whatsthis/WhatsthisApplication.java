package se.jola.whatsthis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.jola.whatsthis.properties.PropertyReader;

@SpringBootApplication
public class WhatsthisApplication {

	public static void main(String[] args) {

		SpringApplication.run(WhatsthisApplication.class, args);

	}
}
