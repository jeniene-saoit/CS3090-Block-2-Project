import java.util.Scanner;

/**
 * Checks the strength of a password. The password strength is calculated based on
 * character types, character position, and password length. Upon password evaluation,
 * a rating is given (Weak, Medium, Slightly Good, Strong) based on the password strength.
 * <p>
 * <author> Jeniene Saoit </author>
 * <version> 02/20/ 25 </version>
 */
public class PasswordStrengthChecker {

    /**
     * This method takes the password and provides a strength score of the password.
     * The strength (uniqueness) of a password is calculated by counting the
     * occurrences of a "special" token or factor in your password. Tokens and
     * factors that add to a password's strength include:
     *  <ul>
     *      <li> numbers (0-9) </li>
     *      <li> uppercase letters (A-Z) </li>
     *      <li> symbols (@,#,_, !, $, .) </li>
     *      <li> password length </li>
     *  </ul>
     *
     *  @param password  The password being tested for password strength.
     */
    private static void passwordStrength(String password) {
        String[] passwordArray = password.split("");
        int strength = 0;

        // counters to track password statistics
        int digits = 0;
        int uppercase = 0;
        int commonSymbols = 0;
        int otherSymbols = 0;

        // calculating the password strength points for tokens
        for( String token : passwordArray) {
            if (token.matches("[0-9]")) {   // if token is number
                strength++;
                digits++;
            } else if (token.matches("[A-Z]")) { // if token is uppercase letter
                strength++;
                uppercase++;
            } else if (token.matches("[@#_!$.]")) {   // if using common symbols
                strength += 2;
                commonSymbols++;
            } else if (token.matches("[^0-9a-zA-Z@#_!$.]")) { // if using other tokens
                strength += 3;
                otherSymbols++;
            }
        }

        // calculating the password strength points for other factors
        if (passwordArray.length > 9) strength++;
        if (password.matches("^[A-Z]}")) strength++; // starts with uppercase letter
        if (password.matches("^[^a-zA-Z]")) strength += 2; // starts with non-letter
        if (password.matches("$[^a-zA-Z]")) strength += 2; // ends with non-letter

        // determines strength of the password
        String passwordStrength;
        if (strength <= 3) {
            passwordStrength = "Weak";
        } else if (strength <= 6) {
            passwordStrength = "Medium";
        } else if (strength <= 9) {
            passwordStrength =  "Slightly Good";
        } else {
            passwordStrength = "Strong";
        }

        // print password strength and strength attributes
        System.out.println("\nPassword Strength: " + passwordStrength);
        System.out.println("Strength score: " + strength + "\n");

        System.out.println("--- Password Score Statistics ---");
        System.out.println("Digits: " + digits);
        System.out.println("Uppercase Letters: " + uppercase);
        System.out.println("Common Symbols: " + commonSymbols);
        System.out.println("Other Symbols: " + otherSymbols);
        System.out.println("Password Length > 9: " + (passwordArray.length > 9 ? "Yes" : "No"));
        System.out.println("Starts with Uppercase: " + (password.matches("^[^a-zA-Z]") ? "Yes" : "No"));
        System.out.println("Starts with Non-letter: " + (password.matches("^[^a-zA-Z]") ? "Yes" : "No"));
    }

    /**
     * Prompts the user to enter a password to be evaluated for password strength.
     * It will continuously ask for a user input until it follows the following
     * format rules:
     * <ul>
     *     <li> a non-empty string </li>
     *     <li> contains no spaces</li>
     * </ul>
     *
     * @return The valid password entered by the user.
     */
    private static String getUserPassword() {
        Scanner userPassword = new Scanner(System.in);  // read user input
        String password;

        while (true) {
            System.out.print("Enter your password: ");
            password = userPassword.nextLine(); // get input from scanner

            // check for password format rules
            if (password == null || password.trim().isEmpty()) {
                System.out.println("Error: Password cannot be empty. Please try again.\n");
            } else if (password.contains(" ")) {
                System.out.println("Error: Password cannot contain spaces. Please try again.\n");
            } else {
                break; // Exit the loop if the password is valid
            }
        }

        return password; // return valid password
    }

    /**
     * Execute program.
     */
    public static void main(String[] args) {
        String userPassword = getUserPassword(); // ask/obtain user password
        passwordStrength(userPassword); // check for password's strength
    }
}
