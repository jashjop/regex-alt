package com.regexalt.compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Custom error listener for better error messages.
 */
public class CompilerErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        String errorMsg = "Syntax error: " + msg;
        throw new CompilerException(errorMsg, line, charPositionInLine);
    }
}