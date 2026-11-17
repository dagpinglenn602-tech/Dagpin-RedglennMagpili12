
import java.util.InputMismatchException;
import java.util.Scanner;

// 1. Custom Exception Class (walang 'public' dahil nasa loob ng Main.java)
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// 2. Base Class Architecture (Encapsulation)
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount! Deposit must be greater than 0.");
        }
        balance += amount;
        System.out.printf("Successfully deposited $%.2f%n", amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount! Withdrawal must be greater than 0.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                String.format("Insufficient Funds! Required: $%.2f, Available: $%.2f", amount, balance)
            );
        }
        balance -= amount;
        System.out.printf("Successfully withdrew $%.2f%n", amount);
    }
}

// 3. Main Application Class (ito lang ang public at kapangalan ng file: Main.java)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount("ACC-1001", "Alex Cruz", 500.00);

        System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");
        System.out.printf("Account Created: %s (Holder: %s, Balance: $%.2f)%n",
                account.getAccountNumber(), account.getAccountHolder(), account.getBalance());

        try {
            // Withdrawal Transaction
            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input! Please enter a valid numeric amount.");
            scanner.nextLine(); // Clear invalid input from buffer
        }

        try {
            // Deposit Transaction
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input! Please enter a valid numeric amount.");
            scanner.nextLine(); // Clear invalid input from buffer
        } finally {
            System.out.println("[SESSION COMPLETE]: Transaction log updated.");
            System.out.printf("Current Balance: $%.2f%n", account.getBalance());
            scanner.close();
        }
    }
}
