package com.regexalt.compiler;

import com.regexalt.RegexAltBaseListener;
import com.regexalt.RegexAltParser;
import com.regexalt.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Builds AST from ANTLR parse tree.
 */
public class ASTBuilder extends RegexAltBaseListener {

    private final Stack<ASTNode> nodeStack = new Stack<>();
    private final List<PatternNode> patterns = new ArrayList<>();

    public List<PatternNode> getPatterns() {
        return patterns;
    }

    @Override
    public void exitLiteral(RegexAltParser.LiteralContext ctx) {
        String text = ctx.STRING().getText();
        nodeStack.push(new LiteralNode(text));
    }

    @Override
    public void exitCharClass(RegexAltParser.CharClassContext ctx) {
        String text = ctx.getText();

        CharClassNode.CharClass type = switch (text) {
            case "digit" -> CharClassNode.CharClass.DIGIT;
            case "letter" -> CharClassNode.CharClass.LETTER;
            case "uppercase" -> CharClassNode.CharClass.UPPERCASE;
            case "lowercase" -> CharClassNode.CharClass.LOWERCASE;
            case "whitespace" -> CharClassNode.CharClass.WHITESPACE;
            case "any" -> CharClassNode.CharClass.ANY;
            case "alphanumeric" -> CharClassNode.CharClass.ALPHANUMERIC;
            default -> throw new IllegalArgumentException("Unknown character class: " + text);
        };

        nodeStack.push(new CharClassNode(type));
    }

    @Override
    public void exitQuantifier(RegexAltParser.QuantifierContext ctx) {
        ASTNode child = nodeStack.pop();
        String quantifierName = ctx.getChild(0).getText();

        QuantifierNode node = switch (quantifierName) {
            case "exactly" -> {
                int count = Integer.parseInt(ctx.NUMBER(0).getText());
                yield new QuantifierNode(QuantifierNode.QuantifierType.EXACTLY, child, count, null);
            }
            case "at_least" -> {
                int min = Integer.parseInt(ctx.NUMBER(0).getText());
                yield new QuantifierNode(QuantifierNode.QuantifierType.AT_LEAST, child, min, null);
            }
            case "between" -> {
                int min = Integer.parseInt(ctx.NUMBER(0).getText());
                int max = Integer.parseInt(ctx.NUMBER(1).getText());
                yield new QuantifierNode(QuantifierNode.QuantifierType.BETWEEN, child, min, max);
            }
            case "optional" ->
                    new QuantifierNode(QuantifierNode.QuantifierType.OPTIONAL, child, null, null);
            case "one_or_more" ->
                    new QuantifierNode(QuantifierNode.QuantifierType.ONE_OR_MORE, child, null, null);
            case "zero_or_more" ->
                    new QuantifierNode(QuantifierNode.QuantifierType.ZERO_OR_MORE, child, null, null);
            default -> throw new IllegalArgumentException("Unknown quantifier: " + quantifierName);
        };

        nodeStack.push(node);
    }

    @Override
    public void exitExpression(RegexAltParser.ExpressionContext ctx) {
        if (ctx.STRING() != null) {
            nodeStack.push(new LiteralNode(ctx.STRING().getText()));
        }
    }

    @Override
    public void exitGroup(RegexAltParser.GroupContext ctx) {
        List<ASTNode> alternatives = new ArrayList<>();
        int altCount = ctx.alternative().size();

        for (int i = 0; i < altCount; i++) {
            alternatives.add(0, nodeStack.pop());
        }

        nodeStack.push(new GroupNode(alternatives));
    }

    @Override
    public void exitAlternative(RegexAltParser.AlternativeContext ctx) {
        if (ctx.STRING() != null) {
            nodeStack.push(new LiteralNode(ctx.STRING().getText()));
        }
    }

    @Override
    public void exitPattern(RegexAltParser.PatternContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        List<ASTNode> statements = new ArrayList<>();
        int statementCount = ctx.patternBody().statement().size();

        for (int i = 0; i < statementCount; i++) {
            statements.add(0, nodeStack.pop());
        }

        patterns.add(new PatternNode(name, statements));
    }
}