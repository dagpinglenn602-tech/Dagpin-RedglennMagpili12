
import java.util.Scanner;

public class CustomerTransactionManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Interactive Input for Number of Transactions with Validation
        int count = 0;
        while (true) {
            System.out.print("Enter number of transactions (greater than 0): ");
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                if (count > 0) {
                    break;
                }
                System.out.println("Please enter a number greater than 0.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid token
            }
        }
        scanner.nextLine(); // Consume newline buffer

        // Array Declarations
        String[] customerIds = new String[count];
        double[] transactionAmounts = new double[count];
        double[][] itemDetails = new double[count][3]; // 3 item categories per customer

        // 2. Data Input Loop
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Customer " + (i + 1) + " ---");
            System.out.print("Enter Customer ID/Name: ");
            customerIds[i] = scanner.nextLine();

            System.out.print("Enter Total Transaction Amount: $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid amount. Please enter a valid number:");
                scanner.next();
            }
            transactionAmounts[i] = scanner.nextDouble();

            System.out.println("Enter breakdown for 3 item categories:");
            for (int j = 0; j < 3; j++) {
                System.out.print("  Category " + (j + 1) + " amount: $");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a valid number:");
                    scanner.next();
                }
                itemDetails[i][j] = scanner.nextDouble();
            }
            scanner.nextLine(); // Consume newline buffer
        }

        // 3. Sorting Algorithm Selection with Validation
        int sortChoice = 0;
        while (true) {
            System.out.println("\nSelect Sorting Order:");
            System.out.println("[1] Ascending Order");
            System.out.println("[2] Descending Order");
            System.out.print("Enter choice (1 or 2): ");
            
            if (scanner.hasNextInt()) {
                sortChoice = scanner.nextInt();
                if (sortChoice == 1 || sortChoice == 2) {
                    break;
                }
            } else {
                scanner.next(); // Clear invalid token
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        // Perform Bubble Sort (Sorting transactionAmounts while keeping parallel arrays aligned)
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                boolean condition = (sortChoice == 1) ? 
                    (transactionAmounts[j] > transactionAmounts[j + 1]) : 
                    (transactionAmounts[j] < transactionAmounts[j + 1]);

                if (condition) {
                    // Swap transaction amounts
                    double tempAmount = transactionAmounts[j];
                    transactionAmounts[j] = transactionAmounts[j + 1];
                    transactionAmounts[j + 1] = tempAmount;

                    // Swap corresponding Customer IDs
                    String tempId = customerIds[j];
                    customerIds[j] = customerIds[j + 1];
                    customerIds[j + 1] = tempId;

                    // Swap corresponding 2D row details
                    double[] tempRow = itemDetails[j];
                    itemDetails[j] = itemDetails[j + 1];
                    itemDetails[j + 1] = tempRow;
                }
            }
        }

        // 4. Display Sorted Summary Table
        System.out.println("\n==================================================");
        System.out.println("       SORTED CUSTOMER TRANSACTION SUMMARY        ");
        System.out.println("==================================================");
        System.out.printf("%-15s | %-18s | %-20s\n", "Customer ID", "Total Amount ($)", "Item Breakdown");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.printf("%-15s | $%-17.2f | [%.1f, %.1f, %.1f]\n", 
                customerIds[i], 
                transactionAmounts[i], 
                itemDetails[i][0], itemDetails[i][1], itemDetails[i][2]);
        }
        System.out.println("==================================================");

        scanner.close();
    }
}

