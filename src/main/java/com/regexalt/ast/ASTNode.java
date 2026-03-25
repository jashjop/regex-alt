package com.regexalt.ast;

/**
 * Base class for all Abstract Syntax Tree nodes.
 */
public abstract class ASTNode {

    /**
     * Generates the Java regex string for this node.
     *
     * @return Java regex string
     */
    public abstract String toRegex();
}