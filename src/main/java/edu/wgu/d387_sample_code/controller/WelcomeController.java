package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.util.DisplayMessage;
import edu.wgu.d387_sample_code.util.TimezoneConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public Map<String, String> getWelcomeMessages() {
        DisplayMessage enMessage = new DisplayMessage(new Locale("en", "US"));
        DisplayMessage frMessage = new DisplayMessage(new Locale("fr", "CA"));

        Map<String, String> messages = new HashMap<>();
        messages.put("en", enMessage.getWelcomeMessage());
        messages.put("fr", frMessage.getWelcomeMessage());

        return messages;
    }

    @GetMapping("/presentationTime")
    public Map<String, String> getPresentationTimes() {
        // Time of the event in Eastern Time (ET)
        LocalDateTime eventTimeET = LocalDateTime.of(2024, 2, 3, 15, 0); // 3 PM ET

        Map<String, String> times = new HashMap<>();
        times.put("ET", eventTimeET.format(TimezoneConverter.TIME_FORMATTER) + " ET");
        times.put("MT", TimezoneConverter.convertTime(eventTimeET, ZoneId.of("America/New_York"), ZoneId.of("America/Denver")) + " MT");
        times.put("UTC", TimezoneConverter.convertTime(eventTimeET, ZoneId.of("America/New_York"), ZoneId.of("UTC")) + " UTC");

        return times;
    }
}