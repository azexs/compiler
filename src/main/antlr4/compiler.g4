grammar compiler;
@header {
    package parser;
}

program       : 'DECLARE' declarations 'BEGIN' commands 'END'
              | 'BEGIN' commands 'END'
              ;

declarations  : declarations',' PIDENTIFIER
              | declarations',' PIDENTIFIER'('NUM':'NUM')'
              | PIDENTIFIER
              | PIDENTIFIER'('NUM':'NUM')'
              ;

commands      : commands command
              | command
              ;

command       : identifier ASSIGN expression';'                                #cmdAssign
              | IF condition THEN commands ELSE commands ENDIF                 #cmdIfElse
              | IF condition THEN commands ENDIF                               #cmdIf
              | WHILE condition DO commands ENDWHILE                           #cmdWhile
              | REPEAT commands UNTIL condition';'                             #cmdRepeat
              | FOR PIDENTIFIER FROM value TO value DO commands ENDFOR         #cmdForUp
              | FOR PIDENTIFIER FROM value DOWNTO value DO commands ENDFOR     #cmdForDown
              | READ identifier';'                                             #cmdRead
              | WRITE value';'                                                 #cmdWrite
              ;

expression    : value PLUS value     #plusExp
              | value MINUS value    #minusExp
              | value TIMES value    #timesExp
              | value DIV value      #divExp
              | value MOD value      #modExp
              | value                #simpleExp
              ;

condition     : value op=(EQ|NEQ|LE|GE|LEQ|GEQ) value
              ;

value         : NUM
              | identifier
              ;

identifier    : PIDENTIFIER
              | PIDENTIFIER'('PIDENTIFIER')'
              | PIDENTIFIER'('NUM')'
              ;

              BLANK:         [ \t\r\n] -> skip;
              COMMENT:       '['.*?']' -> skip;
              MINUS :         ('-');
              PIDENTIFIER:   [_a-z]+;
              NUM:            [0-9]+;
              READ:           'READ';
              WRITE:          'WRITE';
              ASSIGN:         ':=';
              PLUS:           '+';
              TIMES:          '*';
              DIV:            '/';
              MOD:            '%';
              FOR:            'FOR';
              EQ:             '=';
              NEQ:            '!=';
              LE:             '<';
              GE:             '>';
              LEQ:            '<=';
              GEQ:            '>=';
              IF:             'IF';
              THEN:           'THEN';
              ELSE:           'ELSE';
              ENDIF:          'ENDIF';
              WHILE:          'WHILE';
              DO:             'DO';
              REPEAT:         'REPEAT';
              UNTIL:          'UNTIL';
              ENDWHILE:       'ENDWHILE';
              ENDDO:          'ENDDO';
              FROM:           'FROM';
              TO:             'TO';
              DOWNTO:         'DOWNTO';
              ENDFOR:         'ENDFOR';
