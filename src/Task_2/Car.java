package Task_2;

public class Car {
    private String color;
    private int maxSpeed;
    private String transmission;
    private int currentSpeed;
    private int price;

    protected String modelName;

    public Car(String color, int maxSpeed, String transmission, int currentSpeed) {
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.transmission = transmission;
        this.currentSpeed = currentSpeed;
    }


    public int getPrice() {
        return price;
    }

    protected void setPrice(int price) {
        this.price = Math.max(price, 0);
    }

    public String getColor(){
        return color;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getTransmission() {
        return transmission;
    }

    public void start(){

    }

    public void stop(){

    }

    public void  accelerate(int speed){

    }
}
