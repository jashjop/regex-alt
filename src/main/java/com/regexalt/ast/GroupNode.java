package com.regexalt.ast;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an alternation group (either/or).
 */
public class GroupNode extends ASTNode {

    private final List<ASTNode> alternatives;

    public GroupNode(List<ASTNode> alternatives) {
        if (alternatives == null || alternatives.isEmpty()) {
            throw new IllegalArgumentException("Group must have at least one alternative");
        }
        this.alternatives = alternatives;
    }

    public List<ASTNode> getAlternatives() {
        return alternatives;
    }

    @Override
    public String toRegex() {
        String joined = alternatives.stream()
                .map(ASTNode::toRegex)
                .collect(Collectors.joining("|"));
        return "(?:" + joined + ")";
    }

    @Override
    public String toString() {
        return "GroupNode{alternatives=" + alternatives.size() + "}";
    }
}