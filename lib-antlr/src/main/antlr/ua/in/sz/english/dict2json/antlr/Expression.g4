grammar Expression;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================
program:   statement+ ;

statement:   expression ';'
    |   identification '=' expression ';'
    ;

expression:   multiplicationExpression (('+'|'-') multiplicationExpression)* ;

multiplicationExpression:   atom ('*' atom)* ;

atom:   integer
    |   identification
    |   '(' expression ')'
    ;

integer: INTEGER;
identification: IDENTIFICATION;

// ====================================================================================================================
// Lexer Rules
// ====================================================================================================================
IDENTIFICATION  :   ('a'..'z'|'A'..'Z')+ ;
INTEGER :   [0-9]+ ;

WHITESPACE : [ \t\r\n]+ -> skip;
