package edu.wgu.d387_sample_code.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimezoneConverter {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String convertTime(LocalDateTime time, ZoneId fromZone, ZoneId toZone) {
        ZonedDateTime zonedDateTime = time.atZone(fromZone);
        ZonedDateTime convertedTime = zonedDateTime.withZoneSameInstant(toZone);
        return convertedTime.format(TIME_FORMATTER);
    }
}
