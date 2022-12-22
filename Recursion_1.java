public class Main {


    static class Recursion {

        private static int firstNumber = -1;
        private static int currentNum;

        public static void printAllTasks(int number) {
            if (firstNumber == -1)
                firstNumber = number;
            if (number == 0) return;
            System.out.println(firstNumber - number + 1);
            printAllTasks(number - 1);
        }
    }

    public static void main(String[] args) {
        Recursion.printAllTasks(10);
    }
}