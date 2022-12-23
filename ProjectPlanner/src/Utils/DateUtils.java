package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    // Вспомогательные функции для работы с датами
    public static Date parseDate(String dateString, boolean is_datetime) {
        // парсит дату в формате  dd.MM.yyyy HH:mm если is_datetime
        // или в формате dd.MM.yyyy, если не is_datetime
        var format = "dd.MM.yyyy";
        if (is_datetime)
            format += " HH:mm";
        var parser = new SimpleDateFormat(format);
        try {
            return parser.parse(dateString);
        } catch (ParseException | NullPointerException ignored) {
            return null;
        }
    }

    public static String dateToString(Date date, boolean is_datetime) {
        // переводит дату в строчку в формате  dd.MM.yyyy HH:mm если is_datetime
        // или в формате dd.MM.yyyy, если не is_datetime
        var format = "dd.MM.yyyy";
        if (is_datetime)
            format += " HH:mm";
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);

        } catch (NullPointerException exception) {
            return null;
        }
    }
}

