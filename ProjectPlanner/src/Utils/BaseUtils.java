package Utils;

public class BaseUtils {
    // Различные вспомогательные функции
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }




}
