package edu.wgu.d387_sample_code.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayMessage {
    private final ResourceBundle bundle;

    public DisplayMessage(Locale locale) {
        this.bundle = ResourceBundle.getBundle("translation", locale);
    }

    public String getWelcomeMessage() {
        return bundle.getString("welcome");
    }
}
