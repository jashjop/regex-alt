package com.regexalt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonPatternsTest {

    @Test
    public void testValidEmails() {
        assertTrue(CommonPatterns.isValidEmail("user@example.com"));
        assertTrue(CommonPatterns.isValidEmail("test123@domain.org"));
        assertTrue(CommonPatterns.isValidEmail("john@company.net"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(CommonPatterns.isValidEmail("invalid@"));
        assertFalse(CommonPatterns.isValidEmail("@example.com"));
        assertFalse(CommonPatterns.isValidEmail("missing-at-sign.com"));
        assertFalse(CommonPatterns.isValidEmail(null));
    }

    @Test
    public void testValidPhones() {
        assertTrue(CommonPatterns.isValidPhone("555-123-4567"));
        assertTrue(CommonPatterns.isValidPhone("800-555-1234"));
        assertTrue(CommonPatterns.isValidPhone("212-867-5309"));
    }

    @Test
    public void testInvalidPhones() {
        assertFalse(CommonPatterns.isValidPhone("1234567890"));
        assertFalse(CommonPatterns.isValidPhone("555-1234"));
        assertFalse(CommonPatterns.isValidPhone("55-123-4567"));
        assertFalse(CommonPatterns.isValidPhone(null));
    }

    @Test
    public void testValidPasswords() {
        assertTrue(CommonPatterns.isValidPassword("MyPass123"));
        assertTrue(CommonPatterns.isValidPassword("12345678"));
        assertTrue(CommonPatterns.isValidPassword("SuperSecure!"));
    }

    @Test
    public void testInvalidPasswords() {
        assertFalse(CommonPatterns.isValidPassword("short"));
        assertFalse(CommonPatterns.isValidPassword("1234567"));
        assertFalse(CommonPatterns.isValidPassword(""));
        assertFalse(CommonPatterns.isValidPassword(null));
    }

    @Test
    public void testValidUrls() {
        assertTrue(CommonPatterns.isValidUrl("http://example.com"));
        assertTrue(CommonPatterns.isValidUrl("https://google.com"));
        assertTrue(CommonPatterns.isValidUrl("https://github.com/user/repo"));
    }

    @Test
    public void testInvalidUrls() {
        assertFalse(CommonPatterns.isValidUrl("ftp://server.com"));
        assertFalse(CommonPatterns.isValidUrl("example.com"));
        assertFalse(CommonPatterns.isValidUrl("http://"));
        assertFalse(CommonPatterns.isValidUrl(null));
    }

    @Test
    public void testValidDates() {
        assertTrue(CommonPatterns.isValidDate("2024-03-19"));
        assertTrue(CommonPatterns.isValidDate("2024-12-31"));
        assertTrue(CommonPatterns.isValidDate("2023-01-01"));
    }

    @Test
    public void testInvalidDates() {
        assertFalse(CommonPatterns.isValidDate("24-03-19"));
        assertFalse(CommonPatterns.isValidDate("2024/03/19"));
        assertFalse(CommonPatterns.isValidDate("03-19-2024"));
        assertFalse(CommonPatterns.isValidDate(null));
    }
}