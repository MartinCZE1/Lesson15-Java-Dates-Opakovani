package cz.secda1.spsmb.javaDates;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(DateTimeUtils.formattedDate(LocalDateTime.of(2023, 12, 11, 15, 25)));

        String dateString = "13.12.2023";
        System.out.println(DateTimeUtils.parseDate(dateString));

        LocalDate date = LocalDate.now();
        System.out.println(DateTimeUtils.atMoonTime(date));

        System.out.println(DateTimeUtils.whatsTheDateToday(LocalDate.now()));

        System.out.println(DateTimeUtils.daysToChristmas());
    }
}
