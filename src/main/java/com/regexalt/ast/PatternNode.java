package com.regexalt.ast;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a complete pattern definition.
 */
public class PatternNode extends ASTNode {

    private final String name;
    private final List<ASTNode> statements;

    public PatternNode(String name, List<ASTNode> statements) {
        this.name = name;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public List<ASTNode> getStatements() {
        return statements;
    }

    @Override
    public String toRegex() {
        return statements.stream()
                .map(ASTNode::toRegex)
                .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "PatternNode{name='" + name + "', statements=" + statements.size() + "}";
    }
}