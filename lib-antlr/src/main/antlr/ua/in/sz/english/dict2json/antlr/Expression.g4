grammar Expression;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================
program:   statement+ ;

statement:   expression statementEnd
    |   ID '=' expression statementEnd
    ;

statementEnd: END;

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
END :   ';';

WHITESPACE : [ \t\r\n]+ -> skip;
