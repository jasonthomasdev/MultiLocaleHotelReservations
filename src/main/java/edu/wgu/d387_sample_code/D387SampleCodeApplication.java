package edu.wgu.d387_sample_code;

import edu.wgu.d387_sample_code.util.DisplayMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Locale;

@SpringBootApplication
public class D387SampleCodeApplication {
	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		// Create DisplayMessage instances
		DisplayMessage enDisplay = new DisplayMessage(new Locale("en", "US"));
		DisplayMessage frDisplay = new DisplayMessage(new Locale("fr", "CA"));

		// Create and start threads
		Thread threadEN = new Thread(() -> System.out.println(enDisplay.getWelcomeMessage()));
		Thread threadFR = new Thread(() -> System.out.println(frDisplay.getWelcomeMessage()));

		threadEN.start();
		threadFR.start();
	}

	// Global CORS configuration
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Allow requests from the Angular application
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}
}