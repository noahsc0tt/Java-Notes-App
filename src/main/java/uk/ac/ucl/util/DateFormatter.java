package uk.ac.ucl.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// This utility class is used to convert between LocalDateTime and String

public class DateFormatter
{
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm.ss dd-MM-yyyy");
    
    public static String dateToString(LocalDateTime date) {
        return date.format(dateTimeFormatter);
    }
    public static LocalDateTime stringToDate(String date) {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
