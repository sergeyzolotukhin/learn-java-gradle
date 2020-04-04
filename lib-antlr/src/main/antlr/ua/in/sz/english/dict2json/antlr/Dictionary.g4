grammar Dictionary;

@header {
package ua.in.sz.english.dict2json.antlr;
}

// ====================================================================================================================
// Parser Rules
// ====================================================================================================================

dictionary: definition+ EOF ;
definition: word transcription partOfSpeech meaning END NEWLINE;

word: EN_WORD;
transcription: TRANSCRIPTION;
partOfSpeech: PART_OF_SPEECH;
meaning: MEANING;
// ====================================================================================================================
// Lexer Rules
// ====================================================================================================================
fragment DASH : '-';
fragment DOT : '.';
fragment NOUN : ('N' | 'n');
fragment VERB : ('V' | 'v');
fragment EN_LETTER : [a-zA-Z];
fragment RU_LETTER : [\u0400-\u04FF'];

NEWLINE : ('\r'? '\n' | '\r')+;
WHITESPACE : [ \t]+ -> skip;
COMMA : (',');

PART_OF_SPEECH : ( NOUN | VERB ) ;

EN_WORD : ( EN_LETTER | DASH )+;
RU_WORD : ( RU_LETTER )+;

MEANING : ( RU_WORD )+ ( (COMMA | WHITESPACE) WHITESPACE* RU_WORD )*;

TRANSCRIPTION : ( '[' ~[\]]+ ']' )+;

END : (DOT)+;