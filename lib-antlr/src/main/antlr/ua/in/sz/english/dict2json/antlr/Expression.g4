grammar Expression;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================
program     :   statement+ ;

statement   :   expression ';'                      # printExpr
            |   identification '=' expression ';'   # assign
            ;

expression  :   expression op=('*'|'/') expression          # multLbl
            |   expression op=('+'|'-') expression          # addLbl
            |   integer                                     # intLbl
            |   identification                              # idLbl
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
