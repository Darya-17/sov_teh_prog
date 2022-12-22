import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static boolean RecursiveBinary(int[] arr, int num, int left, int right) {
        if (left > right) return false;
        var middleIndex = (left + right) / 2;
        var cur = arr[middleIndex];
        if (cur == num)
            return true;
         else if (cur < num)
            left = middleIndex + 1;
         else
            right = middleIndex - 1;

        return RecursiveBinary(arr, num, left, right);
    }

    public static boolean BinarySearch(int[] arr, int num) {
        var left = 0;
        var right = arr.length - 1;
        return RecursiveBinary(arr, num, left, right);
    }

    public static boolean Enumeration(int[] arr, int num) {
        for (Integer integer : arr) {
            if (num == integer)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Random rnd = new Random();
        var arr = new int[100000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(0, 100);
        }
        var m = System.currentTimeMillis();
        System.out.println(MessageFormat.format("Перебор {0}", Enumeration(arr, 32)));
        System.out.println(MessageFormat.format("Количество миллисекунд перебора {0}",(System.currentTimeMillis() - m)));
        arr = Arrays.stream(arr).sorted().toArray();
        m = System.currentTimeMillis();
        System.out.println(MessageFormat.format("Бинарный поиск {0}", BinarySearch(arr, 32)));
        System.out.println(MessageFormat.format("Количество миллисекунд рекурсивного бинарного поиска в отсортированном массиве {0}",
                (System.currentTimeMillis() - m)));

    }
}