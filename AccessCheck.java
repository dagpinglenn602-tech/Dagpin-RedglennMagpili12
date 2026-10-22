public class AccessCheck {
    public static void main(String[] args) {
        int userAge = 17;
        int securityPin = 4321;

        // Simple if statement
        if (userAge >= 18) {
            System.out.println("Adult Account Verified.");
        }

        // if-else statement
        if (securityPin == 4321) {
            System.out.println("Access Granted");
        } else {
            System.out.println("Access Denied: Invalid PIN");
        }
    }
}