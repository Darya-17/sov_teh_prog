import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static Random rnd = new Random();
    static int sizeBuf;
    static final ReentrantLock locker = new ReentrantLock(); // создаем заглушку
    static Queue<Integer> buf;

    static class Manufacturer extends Thread {
        @Override
        public void run() {
            while (true) {
                while (buf.size() == sizeBuf) {
                    continue;
                }
                var num = rnd.nextInt(0, 10);
                buf.add(num);
                System.out.println(MessageFormat.format("Положил {0} в буффер", num));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                while (buf.size() == 0) {
                    continue;
                }
                System.out.println(MessageFormat.format("Взял {0} из буффера", buf.remove()));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        sizeBuf = 5;
        buf = new ArrayDeque<>();
        var c = new Consumer();
        var m = new Manufacturer();
        c.start();
        m.start();


    }
}