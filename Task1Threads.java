import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final ReentrantLock locker = new ReentrantLock(); // создаем заглушку

    static class MyThread extends Thread {
        public MyThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            synchronized (locker) {
                while (true) {

                    System.out.println(getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    locker.notify();
                    try {
                        locker.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var a = new MyThread("c");
        var b = new MyThread("b");
        a.start();
        b.start();
    }
}