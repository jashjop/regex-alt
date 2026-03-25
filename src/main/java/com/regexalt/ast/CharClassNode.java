package com.regexalt.ast;

/**
 * Represents a character class.
 */
public class CharClassNode extends ASTNode {

    public enum CharClass {
        DIGIT,
        LETTER,
        UPPERCASE,
        LOWERCASE,
        WHITESPACE,
        ANY,
        ALPHANUMERIC
    }

    private final CharClass type;

    public CharClassNode(CharClass type) {
        this.type = type;
    }

    public CharClass getType() {
        return type;
    }

    @Override
    public String toRegex() {
        return switch (type) {
            case DIGIT -> "\\d";
            case LETTER -> "[a-zA-Z]";
            case UPPERCASE -> "[A-Z]";
            case LOWERCASE -> "[a-z]";
            case WHITESPACE -> "\\s";
            case ANY -> ".";
            case ALPHANUMERIC -> "[a-zA-Z0-9]";
        };
    }

    @Override
    public String toString() {
        return "CharClassNode{type=" + type + "}";
    }
}