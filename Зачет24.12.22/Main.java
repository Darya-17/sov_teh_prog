import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static Table getTableByNumber(int number, List<Table> tables) {
        for (Table table : tables) {
            if (table.Number == number)
                return table;
        }
        return null;
    }

    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var out = System.out;
        var tables = new ArrayList<Table>();
        var meals = new ArrayList<Meal>();
        var orders = new ArrayList<Order>();
        meals.add(new Meal("Курица", 1000));
        meals.add(new Meal("Говядина", 2000));
        meals.add(new Meal("Свинина", 3000));
        orders.add(new Order(meals));
        tables.add(new Table(1, orders));
        while (true) {
            out.println("Выберите столик, введя его номер");
            for (Table table : tables) {
                if (!table.Status)
                    System.out.println(MessageFormat.format("Столик №{0}", table.Number));
            }
            int numberTable;
            Table currentTable;

            while (true) {
                var numberString = in.nextLine();
                try {
                    numberTable = Integer.parseInt(numberString);
                } catch (Exception ignored) {
                    continue;
                }
                currentTable = getTableByNumber(numberTable, tables);
                if (currentTable != null)
                    break;
                System.out.println("Введен неверный номер столика!");
            }
            Order currentOrder;
            if (currentTable.Orders.size() != 0)
                currentOrder = currentTable.Orders.get(0);

            else {
                System.out.println("На данном столе нет заказов");
                continue;
            }
            for (Meal meal : currentOrder.Meals) {
                System.out.println(MessageFormat.format("Блюдо {0}, Цена {1}", meal.Name, meal.Price));
            }
            System.out.println("Если хотите принять заказ, нажмите enter");
            in.nextLine();
            System.out.println("Если хотите рассчитать заказ, нажмите enter");
            int fullPrice = 0;
            for (Meal meal : currentOrder.Meals) {
                fullPrice +=meal.Price;
                System.out.println(MessageFormat.format("Блюдо {0}, Цена {1}", meal.Name, meal.Price));
            }
            System.out.println(MessageFormat.format("Итоговая цена {0}", fullPrice));
            currentTable.Orders.remove(currentOrder);
            currentTable.Status= true;
            System.out.println("Нажмите enter, чтобы прододжить");
        }


    }
}