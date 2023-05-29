import Task_1.Book;
import Task_1.Reader;
import Task_2.Car;
import Task_2.Ford;
import Task_2.Garage;
import Task_2.Lexus;
import com.sun.source.tree.Tree;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static void Task1() {
        var readers = new Reader[]{
                new Reader("Иванов Иван Иванович", 123, "Русский язык", "21.09.2001", "888888"),
                new Reader("Петров Петр Петрович", 321, "Иностранный язык", "21.10.2001", "777777")
        };
        var books = new Book[]{
                new Book("Война и мир", "Толстой"),
                new Book("Идиот", "Достоевский")
        };
        readers[0].takeBook(books);
        readers[1].takeBook(3);
        readers[0].returnBook(books);
        readers[1].returnBook(5);
    }

    private static void Task2() {
        var cars = new Car[]{
                new Lexus("белый", 150, "автомат", 50, "кондиционер"),
                new Lexus("белый", 150, "автомат", 20, "кондиционер"),
                new Ford("черный", 150, "автомат", 30, 50),
                new Ford("серый", 150, "автомат", 20, 50),
                new Ford("красный", 260, "ручная", 70, 50),
        };
        var garage = new Garage(5, cars);
        garage.printCarsSortedByCount();
        garage.printCarsSortedByPrice();
        System.out.println();
    }


    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("Задание 1:\n");
        Task1();
        System.out.println("Задание 2:\n");
        Task2();

    }
}
