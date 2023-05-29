package Task_2;

public class Ford extends Car {
    public Ford(String color, int maxSpeed, String transmission, int currentSpeed, int enginePower) {
        super(color, maxSpeed, transmission, currentSpeed);
        setEnginePower(enginePower);
        modelName = "Ford";
        setPrice(30000);


    }

    private int enginePower;

    private void setEnginePower(int enginePower) {
        this.enginePower = Math.max(enginePower, 0);
    }

    public int getEnginePower() {
        return enginePower;
    }
}
