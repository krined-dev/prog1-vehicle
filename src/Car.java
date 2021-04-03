import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Car extends Vehicle{
    private int power;
    private Calendar productionDate = new GregorianCalendar();;
    
    Car(){

    }

    Car(String name, String colour, int price, int model, String serialNumber, int power, int direction) {
        super(name, colour, price, model, serialNumber, direction);
        this.power = power;
    }

    @Override
    public void setAllFields() {
        super.setAllFields();
        System.out.println("Power:");
        this.power = Integer.parseInt(this.input.nextLine());
    }

    @Override
    public void turnLeft(int degrees) {
        if (degrees > 0 && degrees < 360) {
            if (this.getDirection() - degrees < 0) {
                this.setDirection((this.getDirection() - degrees) + 360);
            } else {
                this.setDirection(this.getDirection() - degrees);
            }
        }
    }

    @Override
    public void turnRight(int degrees) {
        if (degrees > 0 && degrees < 360) {
            if (this.getDirection() + degrees > 360) {
                this.setDirection((this.getDirection() + degrees) - 360);
            } else {
                this.setDirection(this.getDirection() + degrees);
            }
            if (this.getDirection() == 360) {
                this.setDirection(0);
            }
        }
    }

    @Override
    public Car clone() {
        Car car = (Car) super.clone();
        car.power = this.power;
        return car;
    }

    @Override
    public String toString() {
        return super.toString() + " Power: " + this.power;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    @Override
    public void accelerate(int speedFactor) {
        if (this.getSpeed() == 0.0) {
            this.setSpeed(0.5 * speedFactor);
        } else {
            if (speedFactor * this.getSpeed() < MAX_SPEED_CAR) {
                this.setSpeed(this.getSpeed() * speedFactor);
            } else {
                this.setSpeed(MAX_SPEED_CAR);
            }
        }
        System.out.println("Vehicle accelerated to: " +this.getSpeed() + "km/h");
    }

    @Override
    public void breaks(int speedFactor) {
        this.setSpeed(this.getSpeed() / speedFactor);
        System.out.println("Vehicle slowed down to: " +this.getSpeed() + "km/h");
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
        out.append(String.valueOf(this.power)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.productionDate.getTimeInMillis())).append(NEW_LINE_SEPARATOR);

    }

    @Override
    public void readData(Scanner in) {
        super.readData(in);
        String[] inData = in.nextLine().split(",");
        this.power = Integer.parseInt(inData[8]);
        this.productionDate = new GregorianCalendar();
        this.productionDate.setTimeInMillis(Long.parseLong(inData[8]));
    }

    public Calendar getProductionDate () {

        return productionDate;
    }

    public void setProductionDate (Calendar productionDate){
        this.productionDate = productionDate;

    }

}

