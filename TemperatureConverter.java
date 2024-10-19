import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueConverting = true;

        while (continueConverting) {
            try {
                // User input for temperature value
                System.out.print("Enter the temperature value: ");
                double temperatureValue = scanner.nextDouble();

                // User input for source scale
                System.out.println("Select the source scale:");
                System.out.println("1. Celsius (C)");
                System.out.println("2. Fahrenheit (F)");
                System.out.println("3. Kelvin (K)");
                int sourceScale = scanner.nextInt();

                // User input for target scale
                System.out.println("Select the target scale:");
                System.out.println("1. Celsius (C)");
                System.out.println("2. Fahrenheit (F)");
                System.out.println("3. Kelvin (K)");
                int targetScale = scanner.nextInt();

                double convertedTemperature = convertTemperature(temperatureValue, sourceScale, targetScale);
                System.out.printf("Converted Temperature: %.2f %s%n", convertedTemperature, getScaleName(targetScale));

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter numeric values.");
                scanner.next(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Check if user wants to continue
            System.out.print("Do you want to perform another conversion? (yes/no): ");
            String response = scanner.next();
            continueConverting = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for using the temperature converter. Goodbye!");
        scanner.close();
    }

    private static double convertTemperature(double value, int sourceScale, int targetScale) {
        // Convert to Celsius first
        if (sourceScale == 1) { // Celsius
            return convertFromCelsius(value, targetScale);
        } else if (sourceScale == 2) { // Fahrenheit
            double celsius = (value - 32) * 5 / 9;
            return convertFromCelsius(celsius, targetScale);
        } else if (sourceScale == 3) { // Kelvin
            double celsius = value - 273.15;
            return convertFromCelsius(celsius, targetScale);
        } else {
            throw new IllegalArgumentException("Invalid source scale selected.");
        }
    }

    private static double convertFromCelsius(double celsius, int targetScale) {
        switch (targetScale) {
            case 1: // Celsius
                return celsius;
            case 2: // Fahrenheit
                return (celsius * 9 / 5) + 32;
            case 3: // Kelvin
                return celsius + 273.15;
            default:
                throw new IllegalArgumentException("Invalid target scale selected.");
        }
    }

    private static String getScaleName(int scale) {
        switch (scale) {
            case 1: return "Celsius (C)";
            case 2: return "Fahrenheit (F)";
            case 3: return "Kelvin (K)";
            default: return "Unknown scale";
        }
    }
}
