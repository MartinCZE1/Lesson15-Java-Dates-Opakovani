package cz.secda1.spsmb.javaDates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void formattedDate() {
        String formattedDate = DateTimeUtils.formattedDate(LocalDate.of(2023, 11, 12).atTime(23, 59));
        Assertions.assertNotNull(formattedDate);
        assertEquals("12.11.2023 23:59", formattedDate);
    }

    @Test
    void parseDate() {
        LocalDate date = DateTimeUtils.parseDate("13.12.2023");
        assertNotNull(date);
        assertEquals(2023, date.getYear());
        assertEquals(13, date.getDayOfMonth());
        assertEquals(Month.DECEMBER, date.getMonth());

    }

    @ParameterizedTest
    @CsvSource(value = {"23.08.2023;23.08.2023 12:00", "12.11.2023;12.11.2023 12:00"}, delimiter = ';')
    void atMoonTime(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate someDay, String expected) {
        String todaysMoonTime = DateTimeUtils.atMoonTime(someDay);
        assertNotNull(todaysMoonTime);
        assertEquals(expected, todaysMoonTime);
    }


    @ParameterizedTest
    @CsvSource(value = {"12.12.2023,Dnes je úterý.", "13.12.2023,Dnes je středa.", "14.12.2023,Dnes je čtvrtek."}, delimiter = ',')
    void whatsTheDateToday(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate someDay, String expected) {
        String message = DateTimeUtils.whatsTheDateToday(someDay);
        assertNotNull(message);
        assertEquals(expected, message);
    }

    @Test
    void daysToChristmas() {
        String message = DateTimeUtils.daysToChristmas();
        assertNotNull(message);
        assertEquals("Do Vánoc zbývá 11 dní.", message);
    }

    @ParameterizedTest
    @CsvSource({
            "01.01.2023, 31.12.2023, 01.01.2023, 31.12.2023",
            "31.12.2023, 01.01.2023, 01.01.2023, 31.12.2023",
            "01.01.2023, 01.01.2023, 01.01.2023, 01.01.2023"
    })
    void smallerFirst(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate first, @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate second,
                      @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate expectedStart, @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate expectedEnd) {
        List<LocalDate> dates = DateTimeUtils.smallerFirst(first, second);
        assertNotNull(dates);
        assertArrayEquals(List.of(expectedStart, expectedEnd).toArray(), dates.toArray()
        );
    }


    @Test
    void firstMondayAfterChristmas() {
        LocalDate mondayAfterChristmas = DateTimeUtils.firstMondayAfterChristmas();
        assertNotNull(mondayAfterChristmas);
        assertEquals("2023-12-25", mondayAfterChristmas.toString());


    }
}