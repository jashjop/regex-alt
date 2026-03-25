package com.regexalt;

import java.util.regex.Pattern;

/**
 * Example usage of RegexAlt.
 * Run this class to see the compiler in action.
 */
public class Examples {

    public static void main(String[] args) {
        System.out.println("=== RegexAlt Examples ===\n");

        emailExample();
        phoneExample();
        passwordExample();
        urlExample();
    }

    private static void emailExample() {
        System.out.println("--- Email Validation ---");

        String source = """
            pattern email:
              one_or_more(alphanumeric)
              literal "@"
              one_or_more(letter)
              literal "."
              between(2, 4, letter)
            """;

        Pattern p = RegexAlt.compilePattern(source, "email");

        testPattern(p, "user@example.com", true);
        testPattern(p, "test123@domain.org", true);
        testPattern(p, "invalid@", false);
        testPattern(p, "@example.com", false);

        System.out.println();
    }

    private static void phoneExample() {
        System.out.println("--- Phone Number Validation ---");

        String source = """
            pattern phone:
              optional("+")
              exactly(10, digit)
            """;

        Pattern p = RegexAlt.compilePattern(source, "phone");

        testPattern(p, "1234567890", true);
        testPattern(p, "+1234567890", true);
        testPattern(p, "123456789", false);
        testPattern(p, "12345678901", false);

        System.out.println();
    }

    private static void passwordExample() {
        System.out.println("--- Password Strength (8+ characters) ---");

        String source = """
            pattern password:
              at_least(8, any)
            """;

        Pattern p = RegexAlt.compilePattern(source, "password");

        testPattern(p, "MyPassword123", true);
        testPattern(p, "12345678", true);
        testPattern(p, "short", false);
        testPattern(p, "1234567", false);

        System.out.println();
    }

    private static void urlExample() {
        System.out.println("--- URL Protocol ---");

        String source = """
            pattern protocol:
              either:
                "http"
                "https"
                "ftp"
            """;

        Pattern p = RegexAlt.compilePattern(source, "protocol");

        testPattern(p, "http", true);
        testPattern(p, "https", true);
        testPattern(p, "ftp", true);
        testPattern(p, "ssh", false);

        System.out.println();
    }

    private static void testPattern(Pattern p, String input, boolean expectedMatch) {
        boolean actualMatch = p.matcher(input).matches();
        String result = actualMatch == expectedMatch ? "✓" : "✗";
        System.out.printf("%s '%s' -> %s (expected %s)\n",
                result, input, actualMatch, expectedMatch);
    }
}