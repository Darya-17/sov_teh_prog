package Task_2;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Garage {
    private int MaxCapacity;

    public int getMaxCapacity() {
        return MaxCapacity;
    }

    private void setMaxCapacity(int maxCapacity) {
        if (maxCapacity < 0)
            MaxCapacity = 0;
        else
            MaxCapacity = maxCapacity;
    }

    private Car[] cars;

    private HashMap<String, Integer> getCarTypesAndCounts() {
        var result = new HashMap<String, Integer>();
        for (var car : cars) {
            if (car != null)
                if (result.containsKey(car.modelName))
                    result.merge(car.modelName, 1, Integer::sum);
                else
                    result.put(car.modelName, 1);
        }
        return result;

    }

    private HashMap<String, Integer> getCarTypesAndPrices() {
        var result = new HashMap<String, Integer>();
        for (var car : cars) {
            if (car != null)
                if (!result.containsKey(car.modelName))
                    result.put(car.modelName, car.getPrice());
        }
        return result;

    }

    private HashMap<String,Integer> sortHashMapCars(HashMap<String, Integer> cars){
        return cars.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void printCars(HashMap<String, Integer> cars) {
        cars.forEach((key, value) -> System.out.println(MessageFormat.format("Имя - {0}, Количество - {1}", key, value)));

    }

    public void printCarsSortedByCount() {
        var cars = sortHashMapCars(getCarTypesAndCounts());
        System.out.println("Машины, отсортированные по количеству по возрастанию:");
        printCars(cars);
    }

    public void printCarsSortedByPrice() {

        var cars = sortHashMapCars(getCarTypesAndPrices());
        System.out.println("Машины, отсортированные по цене по возрастанию:");
        printCars(cars);

    }

    private void setCars(Car[] cars) {
        this.cars = new Car[MaxCapacity];
        for (int i = 0; i < MaxCapacity; i++) {
            if (cars.length - 1 < i)
                break;
            this.cars[i] = cars[i];
        }
    }

    public Garage(int maxCapacity, Car[] cars) {
        setMaxCapacity(maxCapacity);
        setCars(cars);


    }

}
