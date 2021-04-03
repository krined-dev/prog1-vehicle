/**
 * TestVehicles oppretter Bicycle og Car objekter, legger disse i et ArrayList
 * Lar bruker manipulere data i arrayet på forskjellige måter
 */

import java.util.*;
import java.io.*;

public class VehicleTest {
    public static void main(String[] args) {
        VehicleTest vtest = new VehicleTest();
        try {
            vtest.menuLoop();
        } catch(IOException e) {
            System.out.println("IO Exception!");
            System.exit(1);
        } catch(CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException");
            System.exit(1);
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void menuLoop() throws IOException, CloneNotSupportedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Vehicle> arr=new ArrayList<Vehicle>();
        Vehicle vehicle;

        System.out.println("Import from file (yes/any key to skip)");
        String yesNo = scan.nextLine();
        if (yesNo.equalsIgnoreCase("y") || yesNo.equalsIgnoreCase("yes")) {
            System.out.println("filename: ");
            String filename = scan.nextLine();
            File inFile = new File(filename);

            Scanner in = new Scanner(inFile).useLocale(Locale.US);
            while (in.hasNextLine()) {
                in.useDelimiter(",");
                String vehClass = in.next();                    // leser klassenavnet fra filen
                Class veh1 = Class.forName(vehClass);           // oppretter Class objekt for angitt klassenavn (String)
                Vehicle veh = (Vehicle)veh1.newInstance(); // oppretter ny instans av Vehicle

                veh.readData(in);
            }
        }

        arr.add(new Car("Volvo","Black",85000,2010,"1010-11",163,0));
        arr.add(new Bicycle("Diamant","yellow",4000,1993,"BC100",10,0));
        arr.add(new Car("Ferrari Testarossa","red",1200000,1996,"A112",350,0));
        arr.add(new Bicycle("DBS","pink",5000,1994,"42",10,0));

        while(true) {
            System.out.println("1...................................New car");
            System.out.println("2...............................New bicycle");
            System.out.println("3......................Find vehicle by name");
            System.out.println("4..............Show data about all vehicles");
            System.out.println("5.......Change direction of a given vehicle");
            System.out.println("6.........................Test clone method");
            System.out.println("7...................Test drivable interface");
            System.out.println("8..............................Exit program");
            System.out.println(".............................Your choice?");
            int choice = scan.nextInt();
            scan.nextLine();
            
            switch (choice) {
                case 1:
                    //legg til en ny bil
                    Car newCar = new Car();
                    newCar.setAllFields();
                    arr.add(newCar);
                    break;
                case 2:
                    //legg til en ny sykkel
                    Bicycle newBicycle = new Bicycle();
                    newBicycle.setAllFields();
                    arr.add(newBicycle);
                    break;
                case 3:
                    //vis info om gitt kjøretøy
                    System.out.println("Name of vehicle: ");
                    String name = scan.nextLine();
                    for (Vehicle v : arr) {
                        if (name.equalsIgnoreCase(v.getName())) {
                            System.out.println(v.toString() + "\n");
                        }
                    }
                    break;
                case 4:
                    //vis info om alle kjøretøy
                    for (Vehicle v : arr) {
                        System.out.println(v.toString());
                    }
                    System.out.println("");
                    break;
                case 5:
                    // Finn kjøretøy med gitt navn, sett ny retning
                    System.out.println("Name of vehicle: ");
                    String nameForDirection = scan.nextLine();
                    for (Vehicle v : arr) {
                        if (nameForDirection.equals(v.getName())) {
                            System.out.println("Direction [R/L]");
                            String direction = scan.nextLine();
                            System.out.println("Degrees: ");
                            int deg = Integer.parseInt(scan.nextLine());
                            if (direction.equalsIgnoreCase("r")) {
                                v.turnRight(deg);
                            }
                            if (direction.equalsIgnoreCase("l")) {
                                v.turnLeft(deg);
                            }
                        }
                    }
                    break;
                case 6:
                    Car testCloneCar = new Car("Volvo","Black",85000,2010,"1010-11",163,0);
                    Car cloneCar = testCloneCar.clone();

                    cloneCar.setBuyingDate(2001, 11, 11);

                    System.out.println("Date objects are separate, deep copy.");
                    System.out.println(testCloneCar.getBuyingDate().getTime());
                    System.out.println(cloneCar.getBuyingDate().getTime());
                    break;
                case 7:
                    Car testDrivableCar = new Car("Volvo","Black",85000,2010,"1010-11",163,0);
                    Bicycle testDrivableBicycle = new Bicycle("Diamant","yellow",4000,1993,"BC100",10,0);

                    System.out.println("Car:");
                    testDrivableCar.accelerate(10);
                    testDrivableCar.accelerate(50);
                    testDrivableCar.breaks(1000);
                    testDrivableCar.stop();

                    System.out.println("Bicycle:");
                    testDrivableBicycle.accelerate(10);
                    testDrivableBicycle.accelerate(50);
                    testDrivableBicycle.breaks(1000);
                    testDrivableBicycle.stop();

                    break;
                case 8:
                    Collections.sort(arr);
                    System.out.println("Filename to save to?");
                    String filename = scan.nextLine() + ".csv";
                    File outFile = new File(filename);
                    PrintWriter out = new PrintWriter(outFile);
                    for (Vehicle v : arr) {
                        v.writeData(out);
                    }
                    out.flush();
                    out.close();
                    scan.close();
                    System.exit(0);
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}

