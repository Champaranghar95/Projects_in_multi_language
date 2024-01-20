import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        // Define exchange rates
        double usdToEurRate = 0.85;
        double usdToGbpRate = 0.73;
        double usdToJpyRate = 113.85;

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount in USD: ");
        double amountInUsd = scanner.nextDouble();

        // Convert to other currencies
        double amountInEur = amountInUsd * usdToEurRate;
        double amountInGbp = amountInUsd * usdToGbpRate;
        double amountInJpy = amountInUsd * usdToJpyRate;

        // Display results
        System.out.println("Amount in EUR: " + amountInEur);
        System.out.println("Amount in GBP: " + amountInGbp);
        System.out.println("Amount in JPY: " + amountInJpy);

        // Close the scanner
        scanner.close();
    }
}
