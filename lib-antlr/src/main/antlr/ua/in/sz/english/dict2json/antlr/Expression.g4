grammar Expression;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================
program:   statement+ ;

statement:   expression NEWLINE
    |   ID '=' expression NEWLINE
    |   NEWLINE
    ;

expression:   multiplicationExpression (('+'|'-') multiplicationExpression)* ;

multiplicationExpression:   atom ('*' atom)* ;

atom:   INT
    |   ID
    |   '(' expression ')'
    ;
// ====================================================================================================================
// Lexer Rules
// ====================================================================================================================
ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   [0-9]+ ;
NEWLINE :'\r'? '\n';

WHITESPACE : [ \t]+ -> skip;
