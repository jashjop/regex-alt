package com.regexalt.ast;

/**
 * Represents a quantifier applied to another pattern.
 */
public class QuantifierNode extends ASTNode {

    public enum QuantifierType {
        EXACTLY,
        AT_LEAST,
        BETWEEN,
        OPTIONAL,
        ONE_OR_MORE,
        ZERO_OR_MORE
    }

    private final QuantifierType type;
    private final ASTNode child;
    private final Integer min;
    private final Integer max;

    public QuantifierNode(QuantifierType type, ASTNode child, Integer min, Integer max) {
        this.type = type;
        this.child = child;
        this.min = min;
        this.max = max;

        // Validate
        if (type == QuantifierType.EXACTLY || type == QuantifierType.AT_LEAST) {
            if (min == null || min < 0) {
                throw new IllegalArgumentException("Quantifier min must be non-negative");
            }
        }
        if (type == QuantifierType.BETWEEN) {
            if (min == null || max == null || min < 0 || max < min) {
                throw new IllegalArgumentException("Invalid range for BETWEEN");
            }
        }
    }

    public QuantifierType getType() {
        return type;
    }

    public ASTNode getChild() {
        return child;
    }

    @Override
    public String toRegex() {
        String childRegex = child.toRegex();

        // Wrap in non-capturing group if needed
        boolean needsGroup = childRegex.length() > 2 || childRegex.contains("|");
        if (needsGroup && !childRegex.startsWith("(")) {
            childRegex = "(?:" + childRegex + ")";
        }

        return switch (type) {
            case EXACTLY -> childRegex + "{" + min + "}";
            case AT_LEAST -> childRegex + "{" + min + ",}";
            case BETWEEN -> childRegex + "{" + min + "," + max + "}";
            case OPTIONAL -> childRegex + "?";
            case ONE_OR_MORE -> childRegex + "+";
            case ZERO_OR_MORE -> childRegex + "*";
        };
    }

    @Override
    public String toString() {
        return "QuantifierNode{type=" + type + ", min=" + min + ", max=" + max + "}";
    }
}