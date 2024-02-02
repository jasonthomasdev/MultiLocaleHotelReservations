package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.util.DisplayMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
