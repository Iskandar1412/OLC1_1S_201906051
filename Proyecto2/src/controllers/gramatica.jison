%{
  // Declaración de código JavaScript

%}

%lex
// Declaración de reglas de gramática BNF
// Las reglas se definen en el siguiente formato:
// rule: production1 production2 { action }
/*declaramos caso insensitivo*/
%options case-insensitive
%x string

/*inicio de variables a definir*/
%%
\/\/[^\n]*                                  {/*comentario de una linea*/}
\/\*((^\*)|\*(?!\/))*\*\/                   {/*comentario multilinea*/}
[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]         {/*otro comentario*/} 

/*Transferencia o continuidad o paro*/
"break"                                     return 'sen_break'
"continue"                                  return 'sen_continue'
"return"                                    return 'sen_return'


// Declaración de acciones semánticas en JavaScript