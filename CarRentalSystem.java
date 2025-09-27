import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

// Enums for input validation
enum FuelType { PETROL, DIESEL, ELECTRIC }
enum MotorcycleType { SPORT, CRUISER, OFF_ROAD }
enum TransmissionType { MANUAL, AUTOMATIC }

// Interfaces
interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

interface CarVehicle {
    void setDoors(int doors);
    int getDoors();
    void setFuelType(FuelType fuel);
    FuelType getFuelType();
}

interface MotorVehicle {
    void setWheels(int wheels);
    int getWheels();
    void setMotorcycleType(MotorcycleType type);
    MotorcycleType getMotorcycleType();
}

interface TruckVehicle {
    void setCargoCapacity(double tons);
    double getCargoCapacity();
    void setTransmission(TransmissionType transmission);
    TransmissionType getTransmission();
}

// Vehicle Classes
class Car implements Vehicle, CarVehicle {
    private String make, model;
    private int year, doors;
    private FuelType fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setDoors(int doors) { this.doors = doors; }
    @Override public int getDoors() { return doors; }
    @Override public void setFuelType(FuelType fuel) { fuelType = fuel; }
    @Override public FuelType getFuelType() { return fuelType; }
}

class Motorcycle implements Vehicle, MotorVehicle {
    private String make, model;
    private int year, wheels;
    private MotorcycleType type;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setWheels(int wheels) { this.wheels = wheels; }
    @Override public int getWheels() { return wheels; }
    @Override public void setMotorcycleType(MotorcycleType type) { this.type = type; }
    @Override public MotorcycleType getMotorcycleType() { return type; }
}

class Truck implements Vehicle, TruckVehicle {
    private String make, model;
    private int year;
    private double cargoCapacity;
    private TransmissionType transmission;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setCargoCapacity(double tons) { cargoCapacity = tons; }
    @Override public double getCargoCapacity() { return cargoCapacity; }
    @Override public void setTransmission(TransmissionType transmission) { this.transmission = transmission; }
    @Override public TransmissionType getTransmission() { return transmission; }
}

// Main Program with Color Outputs
public class CarRentalSystem {
    private static final String FILENAME = "vehicles.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        while(true) {
            System.out.println("\n\u001B[36m===== Beanstalk Car Rentals =====\u001B[0m");
            System.out.println("1. Add Car \n2. Add Motorcycle \n3. Add Truck");
            System.out.println("4. Show All Vehicles \n5. Save & Exit");
            System.out.print("\u001B[33mChoose an option: \u001B[0m");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if(choice == 5) {
                    saveToFile(vehicles);
                    System.out.println("\u001B[32mData saved to " + FILENAME + "!\u001B[0m");
                    break;
                }

                switch(choice) {
                    case 1 -> {
                        System.out.print("\nCar Make: ");
                        String make = scanner.nextLine();
                        System.out.print("Car Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Year: ");
                        int year = scanner.nextInt();
                        Car car = new Car(make, model, year);
                        System.out.print("Doors: ");
                        car.setDoors(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Fuel Type (PETROL/DIESEL/ELECTRIC): ");
                        car.setFuelType(FuelType.valueOf(scanner.nextLine().toUpperCase()));
                        vehicles.add(car);
                        System.out.println("\u001B[32mCar added!\u001B[0m");
                    }
                    case 2 -> {
                        System.out.print("\nMotorcycle Make: ");
                        String bikeMake = scanner.nextLine();
                        System.out.print("Model: ");
                        String bikeModel = scanner.nextLine();
                        System.out.print("Year: ");
                        int bikeYear = scanner.nextInt();
                        Motorcycle bike = new Motorcycle(bikeMake, bikeModel, bikeYear);
                        System.out.print("Wheels: ");
                        bike.setWheels(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Type (SPORT/CRUISER/OFF_ROAD): ");
                        bike.setMotorcycleType(MotorcycleType.valueOf(scanner.nextLine().toUpperCase()));
                        vehicles.add(bike);
                        System.out.println("\u001B[32mMotorcycle added!\u001B[0m");
                    }
                    case 3 -> {
                        System.out.print("\nTruck Make: ");
                        String truckMake = scanner.nextLine();
                        System.out.print("Model: ");
                        String truckModel = scanner.nextLine();
                        System.out.print("Year: ");
                        int truckYear = scanner.nextInt();
                        Truck truck = new Truck(truckMake, truckModel, truckYear);
                        System.out.print("Cargo Capacity (tons): ");
                        truck.setCargoCapacity(scanner.nextDouble());
                        scanner.nextLine();
                        System.out.print("Transmission (MANUAL/AUTOMATIC): ");
                        truck.setTransmission(TransmissionType.valueOf(scanner.nextLine().toUpperCase()));
                        vehicles.add(truck);
                        System.out.println("\u001B[32mTruck added!\u001B[0m");
                    }
                    case 4 -> {
                        System.out.println("\n\u001B[35m=== All Vehicles ===\u001B[0m");
                        for(Vehicle v : vehicles) {
                            System.out.println("\u001B[34m" + v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")\u001B[0m");
                            if(v instanceof Car c) {
                                System.out.println("Type: Car | Doors: " + c.getDoors() + " | Fuel: " + c.getFuelType());
                            } else if(v instanceof Motorcycle m) {
                                System.out.println("Type: Motorcycle | Wheels: " + m.getWheels() + " | Style: " + m.getMotorcycleType());
                            } else if(v instanceof Truck t) {
                                System.out.printf("Type: Truck | Cargo: %.1f tons | Transmission: %s%n", t.getCargoCapacity(), t.getTransmission());
                            }
                        }
                    }
                    default -> System.out.println("\u001B[31mInvalid choice!\u001B[0m");
                }
            } catch(InputMismatchException e) {
                System.out.println("\u001B[31mInvalid input! Use numbers only.\u001B[0m");
                scanner.nextLine();
            } catch(IllegalArgumentException e) {
                System.out.println("\u001B[31mInvalid option! Check your spelling.\u001B[0m");
            }
        }
        scanner.close();
    }

    private static void saveToFile(ArrayList<Vehicle> vehicles) {
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for(Vehicle v : vehicles) {
                writer.write(v.getMake() + "," + v.getModel() + "," + v.getYear() + "\n");
            }
        } catch(IOException e) {
            System.out.println("\u001B[31mError saving file: " + e.getMessage() + "\u001B[0m");
        }
    }
}