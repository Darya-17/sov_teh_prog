package Utils;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUtils {
    // Вспомогательные функции для работы с консолью

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static String Cancel = "отменить";

    public static String getNotEmptyString() {
        while (true) {
            var result = in.nextLine();
            if (result.toLowerCase(Locale.ROOT).equals(Cancel)) {
                return null;
            }
            if (!result.isEmpty() && !result.isBlank())
                return result;
            out.println("Данное значение не может быть пустым!\nВведите еще раз");
        }
    }

    public static String getString() {
        var result = in.nextLine();
        if (result.toLowerCase(Locale.ROOT).equals(Cancel)) {
            return null;
        }
        return result;
    }



}
