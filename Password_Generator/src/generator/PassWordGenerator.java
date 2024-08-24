package generator;

import java.util.Scanner;

public class PassWordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the length of the password
        System.out.print("Enter the length of the password: ");
        int digit = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String lower_cases = "qwertyuioplkjhgfdsazxcvbnm";
        String upper_cases = "QWERTYUIOPLKJHGFDSAZXCVBNM";
        String special_chars = "!@#$%^&*()-_+=<>?";
        String numbers = "0123456789";

        String password = "";

        for (int i = 0; i < digit; i++) {
            int rand = (int) (4 * Math.random());

            switch (rand) {
                case 0:
                    password += String.valueOf(numbers.charAt((int) (numbers.length() * Math.random())));
                    break;
                case 1:
                    password += String.valueOf(lower_cases.charAt((int) (lower_cases.length() * Math.random())));
                    break;
                case 2:
                    password += String.valueOf(upper_cases.charAt((int) (upper_cases.length() * Math.random())));
                    break;
                case 3:
                    password += String.valueOf(special_chars.charAt((int) (special_chars.length() * Math.random())));
                    break;
            }
        }

        // Output the generated password
        System.out.println("Generated Password: " + password);

        // Prompt the user to customize their password
        System.out.print("Do you want to customize the password? (yes/no): ");
        String customize = scanner.nextLine().trim().toLowerCase();

        if (customize.equals("yes")) {
            System.out.print("Enter a name or keyword to include in your password: ");
            String keyword = scanner.nextLine().trim();

            // Include the keyword in the password
            password = insertKeywordIntoPassword(password, keyword);

            System.out.println("Customized Password: " + password);
        }

        // Evaluate the password strength
        String strength = evaluatePasswordStrength(password);
        System.out.println("Password Strength: " + strength);
    }

    private static String insertKeywordIntoPassword(String password, String keyword) {
        int mid = password.length() / 2;
        return password.substring(0, mid) + keyword + password.substring(mid);
    }

    private static String evaluatePasswordStrength(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()-_+=<>?].*")) score++;

        switch (score) {
            case 6: return "Very Strong";
            case 5: return "Strong";
            case 4: return "Moderate";
            case 3: return "Weak";
            default: return "Very Weak";
        }
    }
}
