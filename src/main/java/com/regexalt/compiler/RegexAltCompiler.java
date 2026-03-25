package com.regexalt.compiler;

import com.regexalt.RegexAltLexer;
import com.regexalt.RegexAltParser;
import com.regexalt.ast.PatternNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Main compiler class.
 */
public class RegexAltCompiler {

    public Map<String, Pattern> compile(String source) {
        try {
            // Lexical Analysis
            CharStream input = CharStreams.fromString(source);
            RegexAltLexer lexer = new RegexAltLexer(input);

            // Tokenization
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Parsing
            RegexAltParser parser = new RegexAltParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new CompilerErrorListener());
            ParseTree tree = parser.program();

            // Build AST
            ASTBuilder builder = new ASTBuilder();
            ParseTreeWalker.DEFAULT.walk(builder, tree);
            List<PatternNode> patterns = builder.getPatterns();

            // Generate Code
            Map<String, Pattern> compiledPatterns = new HashMap<>();

            for (PatternNode pattern : patterns) {
                String regex = pattern.toRegex();

                try {
                    Pattern compiled = Pattern.compile(regex);
                    compiledPatterns.put(pattern.getName(), compiled);
                } catch (PatternSyntaxException e) {
                    throw new CompilerException(
                            "Generated invalid regex for pattern '" + pattern.getName() + "': " +
                                    regex + " - " + e.getMessage()
                    );
                }
            }

            return compiledPatterns;

        } catch (CompilerException e) {
            throw e;
        } catch (Exception e) {
            throw new CompilerException("Compilation failed: " + e.getMessage(), e);
        }
    }

    public Pattern compilePattern(String source, String patternName) {
        Map<String, Pattern> patterns = compile(source);
        Pattern pattern = patterns.get(patternName);

        if (pattern == null) {
            throw new CompilerException("Pattern not found: " + patternName);
        }

        return pattern;
    }
}