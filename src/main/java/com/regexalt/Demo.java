package com.regexalt;

import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== RegexAlt Demo ===\n");

        // Define a pattern in RegexAlt syntax
        String emailPattern = """
            pattern email:
              one_or_more(alphanumeric)
              literal "@"
              one_or_more(letter)
              literal "."
              between(2, 4, letter)
            """;

        // Compile it to a Java Pattern
        Pattern pattern = RegexAlt.compilePattern(emailPattern, "email");

        // Test some emails
        String[] emails = {
                "user@example.com",
                "test123@domain.org",
                "invalid@",
                "missing-at-sign.com"
        };

        System.out.println("Email Validation:");
        for (String email : emails) {
            boolean isValid = pattern.matcher(email).matches();
            System.out.printf("  %s: %s\n", email, isValid ? "VALID" : "INVALID");
        }
    }
}