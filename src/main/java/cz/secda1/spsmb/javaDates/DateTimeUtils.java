package cz.secda1.spsmb.javaDates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateTimeUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Vypište aktuální datum ve formátu den.měsíc.rok hodina:minuta (např. 12.12.2023 14:41)
     *
     * @param date Libovolné datum a čas (LocalDateTime), které chceme naformátovat.
     * @return String s naformátovaným datem
     */
    public static String formattedDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    /**
     * Naparsujte datum "03.11.2023" pomocí parseru se zadaným fromátem. Správný zápis formátu můžete vyhledat.
     *
     * @param dateString String ze kterého vyrobíme datum (LocalDate)
     * @return
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    /**
     * Přičtěte k zadanému datu (LocalDate) 12 hodin a vypište ve formátu 12.12.2023 12:00
     *
     * @param date libovolné datum
     * @return vložené datum s časem 12:00
     */
    public static String atMoonTime(LocalDate date) {
        return date.atStartOfDay().plusHours(12).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    /**
     * Vypište aktuální den v týdnu v české lokalizaci.
     *
     * @return String "Dnes je středa."
     * @example "Dnes je středa."
     */
    public static String whatsTheDateToday(LocalDate today) {
        //dopolňte do proměnné dayOfWeek den v týdnu.
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        String dayNameInCzech = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());

        //Sestavte message "Dnes je <dayNameInCzech>." pomocí String.format()
        String formattedMessage = String.format("Dnes je %s.", dayNameInCzech);
        return formattedMessage;
    }

    /**
     * Definujte proměnou Vánoce (LocalDate), která bude ukazovat na datum 24.12.2023
     * a vypište kolik dní zbývá do začátku Vánoc pomocí String format();
     * <p>
     * 1) pro výpočet použijte proměnné Vánoce a aktuální datum.
     * 2) Výsledný výstup z metody bude String ve formátu "Do Vánoc zbývá XY dní."
     *
     * @return String message
     */
    public static String daysToChristmas() {
        LocalDate christmas = LocalDate.of(2023, 12, 24);
        return String.format("Do Vánoc zbývá %d dní.", christmas.compareTo(LocalDate.now()));
    }

    /**
     * Napište metodu, která porovná dva vložené datumy a vrátí vždy nižší datum na provní pozici a vyšší na druhé.
     * Pokud je tedy druhé datum nižší než to první, pak oba datumy prohodí prohodí a vrátí je jako list.
     *
     * @param start první datum pro porovnání
     * @param end   druhé datum pro porovnání
     * @return List<LocalDate> s oběma seřazenými datumy
     * @examples např. pro start = 01.01.2023 a end = 31.12.2023 vrátí list ve stejném pořadí
     * např. pro start = 31.12.2023  a end = 01.01.2023 vrátí list ve opět s nižším datem na první pozici a s vyšším na pozici druhé, tedy opět 01.01.2023, 31.12.2023.
     */
    public static List<LocalDate> smallerFirst(LocalDate start, LocalDate end) {


        List<LocalDate> localDateList = new ArrayList<>();
        int helpInt = start.compareTo(end);
        if (helpInt < 0) {
            localDateList.add(start);
            localDateList.add(end);
        } else {
            localDateList.add(end);
            localDateList.add(start);
        }
        return localDateList;
    }

    /**
     * Najděte a vypište první pondělí po Vánocích 24.12.2023
     *
     * @return první pondělí po Vánocích (LocalDate)
     */
    public static LocalDate firstMondayAfterChristmas() {
        LocalDate christmas = LocalDate.of(2023, 12, 24);
        DayOfWeek christmasDay = christmas.plusDays(1).getDayOfWeek();
        for (int i = 0; i < 7; i++) {
            if (christmasDay.equals(DayOfWeek.MONDAY)) {
                return christmas.plusDays(1);
            }
        }
        return null;
    }
}
