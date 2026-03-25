package com.regexalt.compiler;

/**
 * Exception thrown when compilation fails.
 */
public class CompilerException extends RuntimeException {

    private final int line;
    private final int column;

    public CompilerException(String message) {
        super(message);
        this.line = -1;
        this.column = -1;
    }

    public CompilerException(String message, Throwable cause) {
        super(message, cause);
        this.line = -1;
        this.column = -1;
    }

    public CompilerException(String message, int line, int column) {
        super(formatMessage(message, line, column));
        this.line = line;
        this.column = column;
    }

    private static String formatMessage(String message, int line, int column) {
        return String.format("Error at line %d, column %d: %s", line, column, message);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}