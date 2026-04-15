package com.regexalt;

import java.util.Scanner;

/**
 * Interactive demo showcasing CommonPatterns validation functions.
 */
public class InteractiveDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   RegexAlt Common Patterns Validator      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        boolean continueRunning = true;

        while (continueRunning) {
            showMenu();
            System.out.print("\nYour choice (1-6): ");

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1" -> validateEmail(scanner);
                case "2" -> validatePhone(scanner);
                case "3" -> validatePassword(scanner);
                case "4" -> validateUrl(scanner);
                case "5" -> validateDate(scanner);
                case "6" -> {
                    System.out.println("Thanks for using RegexAlt! Goodbye! 👋");
                    continueRunning = false;
                }
                default -> System.out.println("❌ Invalid choice. Please select 1-6.");
            }

            if (continueRunning) {
                System.out.println("\n" + "─".repeat(50) + "\n");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("Select validation type:");
        System.out.println("  1. Email (username@domain.com)");
        System.out.println("  2. Phone (555-123-4567)");
        System.out.println("  3. Password (8+ characters)");
        System.out.println("  4. URL (http://example.com)");
        System.out.println("  5. Date (YYYY-MM-DD)");
        System.out.println("  6. Exit");
    }

    private static void validateEmail(Scanner scanner) {
        System.out.print("Enter email address: ");
        String email = scanner.nextLine().trim();

        boolean isValid = CommonPatterns.isValidEmail(email);

        if (isValid) {
            System.out.println("✅ Valid email address!");
        } else {
            System.out.println("❌ Invalid email address.");
            System.out.println("   Expected format: username@domain.com");
        }

        showExamples("Email",
                "user@example.com",
                "test123@domain.org",
                "john.doe@company.net"
        );
    }

    private static void validatePhone(Scanner scanner) {
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        boolean isValid = CommonPatterns.isValidPhone(phone);

        if (isValid) {
            System.out.println("✅ Valid phone number!");
        } else {
            System.out.println("❌ Invalid phone number.");
            System.out.println("   Expected format: XXX-XXX-XXXX");
        }

        showExamples("Phone",
                "555-123-4567",
                "800-555-1234",
                "212-867-5309"
        );
    }

    private static void validatePassword(Scanner scanner) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        boolean isValid = CommonPatterns.isValidPassword(password);

        if (isValid) {
            System.out.println("✅ Valid password!");
        } else {
            System.out.println("❌ Invalid password.");
            System.out.println("   Password must be at least 8 characters.");
        }

        showExamples("Password",
                "MyPass123",
                "SuperSecure!",
                "longenoughpassword"
        );
    }

    private static void validateUrl(Scanner scanner) {
        System.out.print("Enter URL: ");
        String url = scanner.nextLine().trim();

        boolean isValid = CommonPatterns.isValidUrl(url);

        if (isValid) {
            System.out.println("✅ Valid URL!");
        } else {
            System.out.println("❌ Invalid URL.");
            System.out.println("   Expected format: http://... or https://...");
        }

        showExamples("URL",
                "http://example.com",
                "https://google.com",
                "https://github.com/user/repo"
        );
    }

    private static void validateDate(Scanner scanner) {
        System.out.print("Enter date: ");
        String date = scanner.nextLine().trim();

        boolean isValid = CommonPatterns.isValidDate(date);

        if (isValid) {
            System.out.println("✅ Valid date format!");
        } else {
            System.out.println("❌ Invalid date format.");
            System.out.println("   Expected format: YYYY-MM-DD");
        }

        showExamples("Date",
                "2024-03-19",
                "2024-12-31",
                "2023-01-01"
        );
    }

    private static void showExamples(String type, String... examples) {
        System.out.println("\n💡 Valid " + type + " examples:");
        for (String example : examples) {
            System.out.println("   • " + example);
        }
    }
}