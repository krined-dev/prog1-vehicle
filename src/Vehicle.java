import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public abstract class Vehicle implements Comparable<Vehicle>, Cloneable, Driveable, Fileable {
    private String colour, name, serialNumber;
    private int model, price, direction;
    private double speed;
    private Calendar buyingDate;
    protected Scanner input = new Scanner(System.in);


    Vehicle(){
        this.buyingDate = new GregorianCalendar();
    }

    Vehicle(String name, String colour, int price, int model, String serialNumber, int direction) {
        this.colour = colour;
        this.name = name;
        this.serialNumber = serialNumber;
        this.model = model;
        this.price = price;
        this.direction = direction;
        this.buyingDate = new GregorianCalendar();

    }

    public void setAllFields(){
        System.out.println("Name: ");
        this.name = this.input.nextLine();
        System.out.println("Colour: ");
        this.colour = this.input.nextLine();
        System.out.println("Price: ");
        this.price = Integer.parseInt(this.input.nextLine());
        System.out.println("Model: ");
        this.model = Integer.parseInt(this.input.nextLine());
        System.out.println("Serial #: ");
        this.serialNumber = this.input.nextLine();
        }

    public abstract void turnLeft(int degrees);

    public abstract void turnRight(int degrees);

    @Override
    public void writeData(PrintWriter out) {

        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";

        out.append(this.getClass().getName()).append(COMMA_DELIMITER);
        out.append(this.name).append(COMMA_DELIMITER);
        out.append(this.colour).append(COMMA_DELIMITER);
        out.append(this.serialNumber).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.model)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.price)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.direction)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.speed)).append(COMMA_DELIMITER);
        out.append(String.valueOf(this.buyingDate.getTimeInMillis())).append(NEW_LINE_SEPARATOR);


    }

    @Override
    public void readData(Scanner in) {
        String[] inData = in.nextLine().split(",");
        for (int i = 0; i < inData.length; i++) {
            System.out.println(i);
            System.out.println(inData[i]);
        }
        this.name = inData[1];
        this.colour = inData[2];
        this.serialNumber = inData[3];
        this.model = Integer.parseInt(inData[4]);
        this.price = Integer.parseInt(inData[5]);
        this.direction = Integer.parseInt(inData[6]);
        this.speed = Double.parseDouble(inData[7]);

        this.buyingDate = new GregorianCalendar();
        this.buyingDate.setTimeInMillis(Long.parseLong(inData[8]));
    }

    @Override
    public void stop() {
        this.speed = 0.0;
        System.out.println("Vehicle stops. Speed = " + this.speed + " km/h");
    }

    @Override
    public String toString() {
        //TODO
        return "Name: " + this.name + " Colour: " + this.colour + " Price: " + this.price + " Model: " + this.model
                + " Serial#: " + this.serialNumber + " Direction: " + this.direction + " Speed: " + this.speed +
                "Date bought: " + this.buyingDate.getTime();
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.price - other.getPrice(); //Da price er av type int vil dette fungere.
    }

    @Override
    public Vehicle clone() {
        try {
            Vehicle vehicle = (Vehicle) super.clone();
            vehicle.buyingDate = (Calendar)(this.buyingDate.clone());
            return vehicle;
        } catch (CloneNotSupportedException ex) {
            return null;
    }


    }public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Calendar getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(int year, int month, int date) {
        this.buyingDate.set(year, month, date);
    }
}
