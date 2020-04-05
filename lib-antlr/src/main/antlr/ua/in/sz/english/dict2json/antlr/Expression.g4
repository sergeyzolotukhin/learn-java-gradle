grammar Expression;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================
program     :   statement+ ;

statement   :   expression ';'                      # stmt
            |   identification '=' expression ';'   # assign
            ;

expression  :   expression op=('*'|'/') expression          # mul
            |   expression op=('+'|'-') expression          # add
            |   integer                                     # int
            |   identification                              # var
            |   '(' expression ')'                          # parens
            ;

integer: INTEGER;
identification: IDENTIFICATION;

// ====================================================================================================================
// Lexer Rules
// ====================================================================================================================
IDENTIFICATION  :   ('a'..'z'|'A'..'Z')+ ;
INTEGER :   [0-9]+ ;

WHITESPACE : [ \t\r\n]+ -> skip;
