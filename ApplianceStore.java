package com.appliancestore;// This will help to addd options 

import java.io.*;
import java.util.*;

public class ApplianceStore {
    private List<Appliance> appliances = new ArrayList<>();
    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                String itemNumber = data[0];
                String brand = data[1];
                int quantity = Integer.parseInt(data[2]);
                int wattage = Integer.parseInt(data[3]);
                String color = data[4];
                double price = Double.parseDouble(data[5]);
                char firstDigit = itemNumber.charAt(0);
                switch (firstDigit) {
                    case '1': 
                        int numberOfDoors = Integer.parseInt(data[6]);
                        int height = Integer.parseInt(data[7]);
                        int width = Integer.parseInt(data[8]);
                        appliances.add(new Refrigerator(itemNumber, brand, quantity, wattage, color, price, numberOfDoors, height, width));
                        break;
                    case '2': 
                        String grade = data[6];
                        int batteryVoltage = Integer.parseInt(data[7]);
                        appliances.add(new Vacuum(itemNumber, brand, quantity, wattage, color, price, grade, batteryVoltage));
                        break;
                    case '3': 
                        double capacity = Double.parseDouble(data[6]);
                        String roomType = data[7];
                        appliances.add(new Microwave(itemNumber, brand, quantity, wattage, color, price, capacity, roomType));
                        break;

                    case '4':
                    case '5': 
                        String feature = data[6];
                        String soundRating = data[7];
                        appliances.add(new Dishwasher(itemNumber, brand, quantity, wattage, color, price, feature, soundRating));
                        break;

                    default:
                        System.out.println("Unknown appliance type: " + itemNumber);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Modern Appliances!");
            System.out.println("How may we assist you?");
            System.out.println("1 – Check out appliance");
            System.out.println("2 – Find appliances by brand");
            System.out.println("3 – Display appliances by type");
            System.out.println("4 – Produce random appliance list");
            System.out.println("5 – Save & exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    checkOutAppliance(scanner);
                    break;
                case 2:
                    findAppliancesByBrand(scanner);
                    break;
                case 3:
                    displayAppliancesByType(scanner);
                    break;
                case 4:
                    produceRandomApplianceList(scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Saving and exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }

    public void checkOutAppliance(Scanner scanner) {
        System.out.print("Enter the item number of an appliance: ");
        String itemNumber = scanner.next();
        Appliance appliance = findApplianceByItemNumber(itemNumber);

        if (appliance != null) {
            if (appliance.quantity > 0) {
                appliance.quantity--;
                System.out.println("Appliance \"" + itemNumber + "\" has been checked out.");
            } else {
                System.out.println("The appliance is not available to be checked out.");
            }
        } else {
            System.out.println("No appliance found with that item number.");
        }
    }

    public void findAppliancesByBrand(Scanner scanner) {
        System.out.print("Enter brand to search for: ");
        String brand = scanner.next();
        boolean found = false;

        for (Appliance appliance : appliances) {
            if (appliance.brand.equalsIgnoreCase(brand)) {
                System.out.println(appliance);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appliances found for brand: " + brand);
        }
    }

    public void displayAppliancesByType(Scanner scanner) {
        System.out.println("Appliance Types");
        System.out.println("1 – Refrigerators");
        System.out.println("2 – Vacuums");
        System.out.println("3 – Microwaves");
        System.out.println("4 – Dishwashers");
        System.out.print("Enter type of appliance: ");
        int type = scanner.nextInt();

        for (Appliance appliance : appliances) {
            char firstDigit = appliance.itemNumber.charAt(0);
            if (firstDigit - '0' == type) {
                System.out.println(appliance);
            }
        }
    }
    public void produceRandomApplianceList(Scanner scanner) {
        System.out.print("Enter number of appliances: ");
        int number = scanner.nextInt();
        Random random = new Random();

        for (int i = 0; i < number; i++) {
            int randomIndex = random.nextInt(appliances.size());
            System.out.println(appliances.get(randomIndex));
        }
    }
    private Appliance findApplianceByItemNumber(String itemNumber) {
        for (Appliance appliance : appliances) {
            if (appliance.itemNumber.equals(itemNumber)) {
                return appliance;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ApplianceStore store = new ApplianceStore();
        store.loadData("C:\\CPRG-304\\workspace\\Appliancestore\\appliances.txt");
        store.displayMenu();
    }
}
