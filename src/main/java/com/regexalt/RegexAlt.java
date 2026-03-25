package com.regexalt;

import com.regexalt.compiler.RegexAltCompiler;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Public API for RegexAlt compiler.
 */
public class RegexAlt {

    private static final RegexAltCompiler compiler = new RegexAltCompiler();

    private RegexAlt() {
        throw new AssertionError("Utility class");
    }

    public static Map<String, Pattern> compile(String source) {
        return compiler.compile(source);
    }

    public static Pattern compilePattern(String source, String patternName) {
        return compiler.compilePattern(source, patternName);
    }

    public static boolean matches(String source, String patternName, String input) {
        Pattern pattern = compilePattern(source, patternName);
        return pattern.matcher(input).matches();
    }
}