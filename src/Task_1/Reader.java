package Task_1;

import java.text.MessageFormat;
import java.util.Arrays;

public class Reader {
    public String FIO;
    public int Number;
    public String Faculty;
    public String DateOfBirthday;
    public String Phone;


    private String getBooksWord(int count){
        var last_num = count % 10;
        if (last_num == 1)
            return "книгу";
        if (count < 5 && count > 1)
            return "книги";
        return "книг";
    }
    public Reader(String fio, int number, String faculty, String dateOfBirthday, String phone) {
        FIO = fio;
        Number = number;
        Faculty = faculty;
        DateOfBirthday = dateOfBirthday;
        Phone = phone;
    }

    public void takeBook(int count) {
        System.out.println(MessageFormat.format("{0} взял {1} {2}", FIO, count, getBooksWord(count)));
    }

    public void takeBook(Book... books) {
        String booksOut = "";
        for (var b : books) {
            booksOut += b.Name;
            booksOut += ", ";
        }
        System.out.println(MessageFormat.format("{0} взял книги: {1}", FIO, booksOut));
    }

    public void returnBook(int count) {
        System.out.println(MessageFormat.format("{0} вернул {1} {2}", FIO, count, getBooksWord(count)));
    }
    public void returnBook(Book... books) {
        String booksOut = "";
        for (var b : books) {
            booksOut += b.Name;
            booksOut += ", ";
        }
        System.out.println(MessageFormat.format("{0} вернул книги: {1}", FIO, booksOut));
    }

}