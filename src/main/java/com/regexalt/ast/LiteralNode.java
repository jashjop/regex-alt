package com.regexalt.ast;

import java.util.regex.Pattern;

/**
 * Represents a literal string to match.
 */
public class LiteralNode extends ASTNode {

    private final String text;

    public LiteralNode(String quotedText) {
        // Remove surrounding quotes
        if (quotedText.startsWith("\"") && quotedText.endsWith("\"")) {
            this.text = quotedText.substring(1, quotedText.length() - 1);
        } else {
            this.text = quotedText;
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public String toRegex() {
        return Pattern.quote(text);
    }

    @Override
    public String toString() {
        return "LiteralNode{text='" + text + "'}";
    }
}