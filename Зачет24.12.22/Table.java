import java.util.ArrayList;
import java.util.List;

public class Table {

    public Table(int number, List<Order> orders){
        Number = number;
        Orders = orders;
    }

    boolean Status; // false свободен / true занят
    int Number;
    List<Order> Orders;

}
