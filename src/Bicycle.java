import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Bicycle extends Vehicle{
    private int gears;
    private Calendar productionDate = new GregorianCalendar();;

    Bicycle() {

    }

    Bicycle(String name, String colour, int price, int model, String serialNumber, int gears, int direction) {
        super(name, colour, price, model, serialNumber, direction);
        this.gears = gears;
    }

    @Override
    public void accelerate(int speedFactor) {
        if (this.getSpeed() == 0.0) {
            this.setSpeed(0.3 * speedFactor);
        } else {
            if (speedFactor * 0.5 * this.getSpeed() < MAX_SPEED_BIKE) {
                this.setSpeed(this.getSpeed() * 0.5 * speedFactor);
            } else {
                this.setSpeed(MAX_SPEED_BIKE);
            }
        }

        System.out.println("Vehicle accelerated to: " +this.getSpeed() + "km/h");
    }

    @Override
    public void breaks(int speedFactor) {
        this.setSpeed(this.getSpeed() / (speedFactor * 0.5));
        System.out.println("Vehicle slowed down to: " +this.getSpeed() + "km/h");

    }

    @Override
    public void setAllFields() {
        super.setAllFields();
        System.out.println("Gears: ");
        this.gears = Integer.parseInt(this.input.nextLine());
    }
    @Override
    public void turnRight(int degrees) {
        // this.setDirection(degrees); // Uncomment if direction is to be logged
        System.out.println("Bicycle turned " + this.getDirection() + " To the Right.");
    }
    @Override
    public void turnLeft(int degrees) {
        // this.setDirection(360 - degrees); // Uncomment if direction is to be logged
        System.out.println("Bicycle turned " + this.getDirection() + " To the Left.");

    }

    @Override
    public String toString() {
        return super.toString() + " Gears: " + this.gears;
    }
    @Override
    public Bicycle clone() {
        Bicycle bicycle = (Bicycle) super.clone();
        bicycle.gears = this.gears;
        return bicycle;
    }
    @Override
    public void writeData(PrintWriter out) {
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";

        out.append(this.getClass().getName()).append(COMMA_DELIMITER);
        out.append(this.getName()).append(COMMA_DELIMITER);
        out.append(this.getColour()).append(COMMA_DELIMITER);
        out.append(this.getSerialNumber()).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getModel())).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getPrice())).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getDirection())).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getSpeed())).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getBuyingDate().getTimeInMillis())).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.gears)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.getProductionDate().getTimeInMillis())).append(NEW_LINE_SEPARATOR);

    }

    @Override
    public void readData(Scanner in) {
        super.readData(in);
        String[] inData = in.nextLine().split(",");

        for (int i = 0; i < inData.length; i++) {
            System.out.println(i);
            System.out.println(inData[i]);
        }

        this.gears = Integer.parseInt(inData[1]);
        this.productionDate = new GregorianCalendar();
        this.productionDate.setTimeInMillis(Long.parseLong(inData[2]));
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }






    public Calendar getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Calendar productionDate) {
        this.productionDate = productionDate;

    }
}
