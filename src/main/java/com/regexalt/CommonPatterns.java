package com.regexalt;

import java.util.regex.Pattern;

/**
 * Common validation patterns that are frequently used.
 * Each method compiles a RegexAlt pattern and provides easy validation.
 */
public class CommonPatterns {

    // Cache compiled patterns for performance
    private static final Pattern EMAIL_PATTERN;
    private static final Pattern PHONE_PATTERN;
    private static final Pattern PASSWORD_PATTERN;
    private static final Pattern URL_PATTERN;
    private static final Pattern DATE_PATTERN;

    static {
        // Email: username@domain.com
        EMAIL_PATTERN = RegexAlt.compilePattern("""
            pattern email:
              one_or_more(alphanumeric)
              literal "@"
              one_or_more(letter)
              literal "."
              between(2, 4, letter)
            """, "email");

        // Phone: 555-123-4567
        PHONE_PATTERN = RegexAlt.compilePattern("""
            pattern phone:
              exactly(3, digit)
              literal "-"
              exactly(3, digit)
              literal "-"
              exactly(4, digit)
            """, "phone");

        // Password: Minimum 8 characters
        PASSWORD_PATTERN = RegexAlt.compilePattern("""
            pattern password:
              at_least(8, any)
            """, "password");

        // URL: http://example.com or https://example.com
        URL_PATTERN = RegexAlt.compilePattern("""
            pattern url:
              either:
                "http"
                "https"
              literal "://"
              one_or_more(any)
            """, "url");

        // Date: YYYY-MM-DD
        DATE_PATTERN = RegexAlt.compilePattern("""
            pattern date:
              exactly(4, digit)
              literal "-"
              exactly(2, digit)
              literal "-"
              exactly(2, digit)
            """, "date");
    }

    /**
     * Validates an email address.
     * Format: username@domain.extension
     *
     * @param email the email address to validate
     * @return true if valid, false otherwise
     *
     * Examples:
     *   - user@example.com ✓
     *   - test123@domain.org ✓
     *   - invalid@ ✗
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates a US phone number.
     * Format: XXX-XXX-XXXX
     *
     * @param phone the phone number to validate
     * @return true if valid, false otherwise
     *
     * Examples:
     *   - 555-123-4567 ✓
     *   - 800-555-1234 ✓
     *   - 1234567890 ✗
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validates a password (minimum 8 characters).
     *
     * @param password the password to validate
     * @return true if valid (8+ characters), false otherwise
     *
     * Examples:
     *   - MyPass123 ✓
     *   - SuperSecure! ✓
     *   - short ✗
     */
    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validates a URL with http or https protocol.
     * Format: http://... or https://...
     *
     * @param url the URL to validate
     * @return true if valid, false otherwise
     *
     * Examples:
     *   - http://example.com ✓
     *   - https://google.com ✓
     *   - ftp://server.com ✗
     */
    public static boolean isValidUrl(String url) {
        if (url == null) return false;
        return URL_PATTERN.matcher(url).matches();
    }

    /**
     * Validates a date in YYYY-MM-DD format.
     * Note: This only checks format, not if the date is valid.
     *
     * @param date the date string to validate
     * @return true if format is valid, false otherwise
     *
     * Examples:
     *   - 2024-03-19 ✓
     *   - 2024-12-31 ✓
     *   - 24-03-19 ✗
     */
    public static boolean isValidDate(String date) {
        if (date == null) return false;
        return DATE_PATTERN.matcher(date).matches();
    }

    /**
     * Validates a custom RegexAlt pattern.
     *
     * @param patternSource the RegexAlt pattern source code
     * @param patternName the name of the pattern to compile
     * @param input the string to validate
     * @return true if input matches the pattern, false otherwise
     */
    public static boolean validateCustom(String patternSource, String patternName, String input) {
        if (input == null) return false;
        try {
            Pattern pattern = RegexAlt.compilePattern(patternSource, patternName);
            return pattern.matcher(input).matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the compiled Pattern for custom use.
     *
     * @param type the pattern type (email, phone, password, url, date)
     * @return the compiled Pattern object
     */
    public static Pattern getPattern(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> EMAIL_PATTERN;
            case "phone" -> PHONE_PATTERN;
            case "password" -> PASSWORD_PATTERN;
            case "url" -> URL_PATTERN;
            case "date" -> DATE_PATTERN;
            default -> throw new IllegalArgumentException("Unknown pattern type: " + type);
        };
    }
}