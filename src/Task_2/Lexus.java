package Task_2;

public class Lexus extends Car {
    public Lexus(String color, int maxSpeed, String transmission, int currentSpeed, String equipmentInfo) {
        super(color, maxSpeed, transmission, currentSpeed);
        setEquipmentInfo(equipmentInfo);
        modelName = "Lexus";
        setPrice(40000);
    }

    private String equipmentInfo;

    private void setEquipmentInfo(String equipmentInfo) {
        this.equipmentInfo = equipmentInfo;
    }

    public String getEquipmentInfo() {
        return equipmentInfo;
    }
}
