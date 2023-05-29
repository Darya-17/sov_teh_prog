package Task_2;

public class Toyota extends Car {
    public Toyota(String color, int maxSpeed, String transmission, int currentSpeed, int countDoors) {
        super(color, maxSpeed, transmission, currentSpeed);
        setCountDoors(countDoors);
        modelName = "Toyota";
        setPrice(20000);

    }

    private int countDoors;

    private void setCountDoors(int countDoors) {
        this.countDoors = Math.max(countDoors, 0);
    }

    public int getCountDoors() {
        return countDoors;
    }
}
