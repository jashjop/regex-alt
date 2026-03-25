package com.regexalt;

import com.regexalt.compiler.RegexAltCompiler;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

    private final RegexAltCompiler compiler = new RegexAltCompiler();

    @Test
    void testSimpleLiteral() {
        String source = """
            pattern test:
              literal "hello"
            """;

        Pattern p = compiler.compilePattern(source, "test");
        assertTrue(p.matcher("hello").matches());
        assertFalse(p.matcher("world").matches());
    }

    @Test
    void testDigit() {
        String source = """
            pattern test:
              digit
            """;

        Pattern p = compiler.compilePattern(source, "test");
        assertTrue(p.matcher("5").matches());
        assertFalse(p.matcher("a").matches());
    }

    @Test
    void testExactlyQuantifier() {
        String source = """
            pattern test:
              exactly(3, digit)
            """;

        Pattern p = compiler.compilePattern(source, "test");
        assertTrue(p.matcher("123").matches());
        assertFalse(p.matcher("12").matches());
    }

    @Test
    void testEmail() {
        String source = """
            pattern email:
              one_or_more(alphanumeric)
              literal "@"
              one_or_more(letter)
              literal "."
              between(2, 4, letter)
            """;

        Pattern p = compiler.compilePattern(source, "email");
        assertTrue(p.matcher("user@example.com").matches());
        assertFalse(p.matcher("invalid@").matches());
    }
}