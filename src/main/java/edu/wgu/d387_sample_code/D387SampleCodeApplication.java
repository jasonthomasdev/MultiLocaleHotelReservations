package edu.wgu.d387_sample_code;

import edu.wgu.d387_sample_code.util.DisplayMessage;
import edu.wgu.d387_sample_code.util.TimezoneConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@SpringBootApplication
public class D387SampleCodeApplication {
	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		// Time of the event in Eastern Time (ET)
		LocalDateTime eventTimeET = LocalDateTime.of(2024, 2, 3, 15, 0); // 3 PM ET

		// Convert to Mountain Time (MT)
		String eventTimeMT = TimezoneConverter.convertTime(eventTimeET, ZoneId.of("America/New_York"), ZoneId.of("America/Denver"));

		// Convert to Coordinated Universal Time (UTC)
		String eventTimeUTC = TimezoneConverter.convertTime(eventTimeET, ZoneId.of("America/New_York"), ZoneId.of("UTC"));


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