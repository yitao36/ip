package tsundere.parser;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parse(String s) {
        LocalDate date = LocalDate.parse(s);
        return date;
    }

    public static void main(String[] args) {
        System.out.println(parse("2025-12-05").format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
