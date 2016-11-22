grammar Assembler;


// START: members
@members {
    // Define the functionality required by the parser for code generation
    protected void gen(Token instrToken){;}
    protected void gen(Token instrToken, Token operandToken){;}
    protected void checkForUnresolvedReferences(){;}
    protected void defineFunction(Token idToken, int nargs, int nlocals){;}
    protected void defineDataSize(int n) {;}
    protected void defineLabel(Token idToken) {;}
}
// END: members

program
    :   globals?
    ( functionDeclaration |labelAddress| instr  )
   	(NEWLINE+( functionDeclaration |labelAddress| instr))*
   	NEWLINE*
        {checkForUnresolvedReferences();}
    ;
    
globals : NEWLINE* '.globals' INT NEWLINE {defineDataSize($INT.int);} ;


//  .def fact: args=1, locals=0
functionDeclaration
    : '.def' name=ID ':' 'args' '=' a=INT ',' 'locals' '=' n=INT 
      {defineFunction($name, $a.int, $n.int);}
    ;


labelAddress
    :   ID ':' {defineLabel($ID);}
    ;
instr
    :	ID operand              {gen($ID,$operand.start);}
	|	ID                 {gen($ID);}
    ;

operand
    :	FUNC // function label; E.g., "f()"
    |	ID
    |   FLOAT
    |   STRING
    |   INT
    ;


ID  :   LETTER (LETTER | '0'..'9')* ;

FUNC:   ID '()' {;} ;

fragment
LETTER
    :   ('a'..'z' | 'A'..'Z')
    ;
    
INT :   '-'? '0'..'9'+ ;


STRING: '\"' STR_CHARS '\"' {;} ;

fragment STR_CHARS : ~'"'* ;

FLOAT
    :   INT '.' INT*
    |   '.' INT+
    |   INT ',' INT*
    |   ',' INT+
    ;

WS  :   (' '|'\t')+ {skip();} ;

NEWLINE
    :   (';' . )* '\r'? '\n'  // optional comment followed by newline
    ;
