grammar RegexAlt;

// ========================================
// PARSER RULES (Syntax Structure)
// ========================================

program
    : pattern+ EOF
    ;

pattern
    : 'pattern' IDENTIFIER ':' patternBody
    ;

patternBody
    : statement+
    ;

statement
    : literal
    | charClass
    | quantifier
    | group
    ;

literal
    : 'literal' STRING
    ;

charClass
    : 'digit'
    | 'letter'
    | 'uppercase'
    | 'lowercase'
    | 'whitespace'
    | 'any'
    | 'alphanumeric'
    ;

quantifier
    : 'exactly' '(' NUMBER ',' expression ')'
    | 'at_least' '(' NUMBER ',' expression ')'
    | 'between' '(' NUMBER ',' NUMBER ',' expression ')'
    | 'optional' '(' expression ')'
    | 'one_or_more' '(' expression ')'
    | 'zero_or_more' '(' expression ')'
    ;

expression
    : charClass
    | STRING
    ;

group
    : 'either' ':' alternative+
    ;

alternative
    : STRING
    | charClass
    ;

// ========================================
// LEXER RULES (Token Definitions)
// ========================================

IDENTIFIER
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

NUMBER
    : [0-9]+
    ;

STRING
    : '"' (~["\r\n])* '"'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '#' ~[\r\n]* -> skip
    ;