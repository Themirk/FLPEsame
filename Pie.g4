/** A simple dynamically-typed language that smacks of Python.
 *  This builds a tree, then we'll interpret it with a tree grammar
 *  Build a convential symbol table while parsing.  Save
 *  symbol ptrs in AST nodes.
 */
grammar Pie;

@header{
  package pie.parser; 
}


program
	:	( functionDefinition | statement )+ EOF 
	;
	
structDefinition
    :   'struct' ID '{' vardef (',' vardef)* '}' NL
    ;
    

functionDefinition
	:	'def' ID '(' (vardef (',' vardef)* )? ')' block
	;

vardef
  : ID
  ;


block
	:	':' NL statement+ '.' NL	
	|	statement
	;


statement
	:	structDefinition                 	# StatStructDefinition
	|	field '=' expr NL	          	    # StatAssigment
	|	'return' expr NL                 	# StatReturn  
	|	'print' expr NL	                 	# StatPrint 
	|	'if' expr block ('else' block)?  	# StatIf
	|	'while' expr block               	# StaWhile
	|	functionCall NL                  	# StatFunctionCall 
	|	NL	                              # StatNL
	;

functionCall
	:	ID '(' (expr (',' expr )*)? ')'
	;

// few operators to keep grammar simple ... add *, /, !=, >, >=...
expr:   functionCall             # ExprFunctionCall
    |   'new' ID                 # ExprStructCreation
    |   field                    # ExprField
    |   expr op=('*' | '/') expr # ExprMult
    |   expr op=('+' | '-') expr # ExprAdd   
    |   INT                      # ExprInt
    |   STRING                   # ExprString 
    |   '(' expr ')'             # ExprParens
    |   expr op=('=='|'<') expr  # ExprCondition 
    ;


field: ID ('.' ID)* ;  // CAN'T RESOLVE TIL RUNTIME!



// LEXICAL RULES

NL	:	'\r'? '\n' ;

ID  :   LETTER (LETTER | '0'..'9')*  
    ;
fragment
LETTER:  ('a'..'z' | 'A'..'Z')
    ;

CHAR:	'\'' . '\'' 
    ;

STRING:	'\"' (.)*? '\"' 
   ;

INT :   '0'..'9'+ 
   ;

FLOAT:	INT '.' INT*
	|	'.' INT+
	;

WS  :   (' '|'\t') -> skip
    ;

SL_COMMENT
    :   '#' ~('\r'|'\n')* -> skip
    ;