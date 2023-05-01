
%lex
// Declaración de reglas de gramática BNF
// Las reglas se definen en el siguiente formato:
// rule: production1 production2 { action }
/*declaramos caso insensitivo*/
%options case-insensitive
%x string

number [0-9]+
decimal ([0-9]+)"."([0-9]+)
string  (\"(\\.|[^\\"])*\")
char  (\'[^']\')
bool "true"| "false"
id ([a-zA-Z_])[a-zA-Z0-9_ñÑ]*
/*inicio de variables a definir*/
%%

//tipos de comentarios
\s+                                                     {}/* skip whitespace */
\/\/.*                                                  {}/* skip single-line comments */
\/\*([^*]|\*(?!\/))*\*\/                                {}/* skip multi-line comments */
[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]                     {}
//tipos de datos 
"int"                                                   return 'tipo_int';
"boolean"                                               return 'tipo_boolean';
"double"                                                return 'tipo_double';      
"char"                                                  return 'tipo_char';
"string"                                                return 'tipo_string';
//tipos dentro de boolean
"true"                                                  return 'boolean_true';
"false"                                                 return 'boolean_false';
//operador nuevo
"new"                                                   return 'operador_new';
//aritemeticos
"+"                                                     return 'operacion_suma';
"-"                                                     return 'operacion_resta';
"*"                                                     return 'operacion_multiplicar';
"/"                                                     return 'operacion_divide';
"^"                                                     return 'operacion_potencia';
"%"                                                     return 'operacion_mod';
//incrementar decrementar
"++"                                                    return 'op_incrementar';
"--"                                                    return 'op_decrementar';
//operadores relacionales
"=="                                                    return 'rel_igualacion';
"!="                                                    return 'rel_diferenciacion';
"<"                                                     return 'rel_menor';
">"                                                     return 'rel_mayor';
"<="                                                    return 'rel_menor_igual_que';
">="                                                    return 'rel_mayor_igual_que';
"="                                                     return 'rel_igual';
//operadores logicos
"||"                                                    return 'log_or';
"&&"                                                    return 'log_and';
"!"                                                     return 'log_not';
//otros operadores
"?"                                                     return 'sig_interrogacion';
"("                                                     return 'par_abierto';
")"                                                     return 'par_cerrado';
"["                                                     return 'cor_abierto';
"]"                                                     return 'cor_cerrado';
"{"                                                     return 'llave_abierta';
"}"                                                     return 'llave_cerrada';
"."                                                     return 'sig_punto';
","                                                     return 'sig_coma';
":"                                                     return 'doble_punto';
";"                                                     return 'punto_coma';
//sentencias de transferencia
"break"                                                 return 'trans_break';
"continue"                                              return 'trans_continue';
"return"                                                return 'trans_return';
//listas
"list"                                                  return 'listas_ou';
"add"                                                   return 'list_add';                          
//sentencias de control
"if"                                                    return 'control_if';
"else"                                                  return 'control_else';
"switch"                                                return 'control_switch';
"case"                                                  return 'control_case';
"default"                                               return 'control_default';
//sentencias ciclicas
"while"                                                 return 'ciclic_while';
"for"                                                   return 'ciclic_for';
"do"                                                    return 'ciclic_do';
//metodos
"void"                                                  return 'metod_void';
//funcion print y otras
"print"                                                 return 'funcion_print';
"println"                                               return 'funcion_println';
"toLower"                                               return 'funcion_tolower';
"toUpper"                                               return 'funcion_toupper';
//funciones nativas
"lenght"                                                return 'native_lenght';
"truncate"                                              return 'native_truncate';
"round"                                                 return 'native_round';
"typeof"                                                return 'native_typeof';
"toString"                                              return 'native_tostring';
"toCharArray"                                           return 'native_tochararray';
//funcion principal
"main"                                                  return 'principal_main';
/* Espacios en blanco */
[ \r\t]+                                                {}
\n                                                      {}


//variables
{id}                                                    return 'ID';
{number}                                                return 'number';
{decimal}                                               return 'decimal';
{string}                                                return 'string';
{bool}                                                  return 'bool';
{char}                                                  return 'char';


<string>[^"\\]+                                         { cadena+=yytext; }
<string>"\\\""                                          { cadena+="\""; }
<string>"\\n"                                           { cadena+="\n"; }
<string>"\\t"                                           { cadena+="\t"; }
<string>"\\\\"                                          { cadena+="\\"; }
<string>"\\\'"                                          { cadena+="\'"; }


<<EOF>>                                                 return 'EOF';

.                                                       {
                                                            console.error('Error léxico: ' + yytext + ', línea: ' + yylloc.first_line + ', columna: ' + yylloc.first_column);
                                                            TablaErrores.getInstance().insertarError(new _Error("Lexico", "Caracter: \"" + yytext + "\" no valido", yylloc.first_line, yylloc.first_column));
                                                            return null;
                                                        }


/lex

%{
  // Declaración de código JavaScript
    const{Type} = require("../interpreter/Symbol/Type.ts");
    //expresiones
    const{Literal} = require("../interpreter/Expresion/Literal.ts");
    const{Access} = require('../interpreter/Expresion/Access.ts');
    const{Arithmetic,ArithmeticOption} = require('../interpreter/Expresion/Arithmetic.ts');
    //instrucciones
    const{Declaration} = require('../interpreter/Instruction/Declaration.ts');
    const{Print} = require('../interpreter/Instruction/Print.ts');
    const{Assignment} = require('../interpreter/Instruction/Assignment.ts');
    const{Statement} = require('../interpreter/Instruction/Statement.ts');
    //------------
    const{TypeIns} = require('../interpreter/Instruction/Function.ts');
    const{Function} = require('../interpreter/Instruction/Function.ts');
    const{Call} = require('../interpreter/Instruction/Call.ts');
    const{Params} = require('../interpreter/Instruction/Params.ts');
    //---------------
    const{ToLowerUpper} = require('../interpreter/Expresion/ToLowerUpper.ts');
    const{Round} = require('../interpreter/Expresion/Round.ts');
    //---------------
    const{NewVector} = require('../interpreter/Expresion/NewVector.ts');
    const{AccessVector} = require('../interpreter/Expresion/AccessVector.ts');
    const{Assignment} = require('../interpreter/Instruction/AssignmentStruct.ts');
    //const{} = require('../interpreter');
    
    let arreglo_ = [];
    let contador_arreglo_ = 0;
%}



//precedencia de operaciones

%right sig_interrogacion
%left log_or
%left log_and
%right log_not
%left rel_igualacion, rel_diferenciacion, rel_menor, rel_mayor, rel_menor_igual_que, rel_mayor_igual_que
%left operacion_suma, operacion_resta
%left operacion_divide, operacion_multiplicar, operacion_mod
%left sig_punto, cor_abierto, cor_cerrado
%right ucast
%nonassoc operacion_potencia
%left unot umas, umenos
%right pre_inc pre_dec
%left op_incrementar op_decrementar


%start INIT
%%
/*Declaración de acciones semánticas en JavaScript*/

INIT : INSTRUCCIONES EOF { return $1; }
;

INSTRUCCIONES
    : INSTRUCCIONES INSTRUCCION         { $1.push($2); $$ = $1; }
    | INSTRUCCION                       { $$ = [$1]; }
;

INSTRUCCION
    : VOIDINSTRUC                       { $$ = $1; }
    | FUNCIONINSTRUCCION                { $$ = $1; }
    | DECLARACION                       { contador_arreglo_++; $$ = $1; }
    | SENTENCIA                         { $$ = $1; }
    | ASIGNACION punto_coma             { $$ = $1; }
    | PRINT punto_coma                  { $$ = $1; }
    | LLAMADA punto_coma                { $$ = $1; }
    | error                             { console.log({line: this._$.first_line, column: this._$.first_column, type: 'Sintáctico', message: `Error sintáctico, token no esperado '${yytext}' .`})}
;

DECLARACION
    : DATATYPE ID rel_igual EXP punto_coma 
    { 
        let arreglo_temp = [{ label: Type[$2], hijos: null }, { label: $3, hijos: null }]
        if($1){ arreglo_temp.push({ label: 'const', hijos: null }) }
        arreglo_.push( { label: 'Declaration', hijos: [arreglo_[contador_arreglo_]].concat(arreglo_temp) } )
        arreglo_.splice(contador_arreglo_,1)
        $$ = new Declaration($1, $2, $4 , @1.first_line, @1.first_column); 
    }
    | DATATYPE cor_abierto cor_cerrado ID rel_igual VECTOR punto_coma
    {
        $$ = new Declaration($1, $4, $6 ,@1.first_line, @1.first_column);
    }
;

ASIGNACION
    : ID rel_igual EXP
    {
        $$ = new Assignment($1, $3, @1.first_line, @1.first_column)
    }
    | ACCESSLIST rel_igual EXP
    {
        $$ = new AssignmentStruct($1, $3);
    }
;

PRINT
    : funcion_print par_abierto EXP par_cerrado
    {
        $$ = new Print(false, $3, @1.first_line, @1.first_column);
    }
    | funcion_println par_abierto EXP par_cerrado
    {
        $$ = new Print(false, $3, @1.first_line, @1.first_column);
    }
;

SENTENCIA
    : llave_abierta INSTRUCCIONES llave_cerrada     { $$ = new Statement($2, @1.first_line, @1.first_column); }
    | llave_abierta llave_cerrada                   { $$ = new Statement(new Array(), @1.first_line, @1.first_column);}
;

DATATYPE
    : tipo_int      { $$ = 0; }
    | tipo_double   { $$ = 1; }
    | tipo_char     { $$ = 2; }
    | tipo_string   { $$ = 3; }
    | tipo_boolean  { $$ = 4; }
;

EXP
    : EXP operacion_suma EXP                                    { $$ = new Arithmetic($1, $3, ArithmeticOption.PLUS, @1.first_line, @1.first_column) }
    | EXP operacion_resta EXP                                   { $$ = new Arithmetic($1, $3, ArithmeticOption.MINUS, @1.first_line, @1.first_column) }
    | EXP operacion_multiplicar EXP                             { $$ = new Arithmetic($1, $3, ArithmeticOption.TIMES, @1.first_line, @1.first_column) }
    | EXP operacion_potencia EXP                                { $$ = new Arithmetic($1, $3, ArithmeticOption.POT, @1.first_line, @1.first_column) }
    | EXP operacion_divide EXP                                  { $$ = new Arithmetic($1, $3, ArithmeticOption.DIV, @1.first_line, @1.first_column) }
    | EXP operacion_mod EXP                                     { $$ = new Arithmetic($1, $3, ArithmeticOption.MODULE, @1.first_line, @1.first_column) }
    | operacion_resta F                                         { $$ = new Literal($2, Type.NEGATIVE, @1.first_line, @1.first_column) }
    | F                                                         { 
                                                                    arreglo_.push({label: 'F', hijos: [arreglo_[contador_arreglo_]]});
                                                                    arreglo_.splice(contador_arreglo_,1);
                                                                    $$ = $1;
                                                                }
    | ToLowerUpperEXP                                           { $$ = $1; }
    | RoundEXP                                                  { $$ = $1; }
    | cor_abierto LISTAEXP cor_cerrado                          { $$ = new NewVector(1, null, $2, @1.first_line, @1.first_column); }
    | EXP cor_abierto EXP cor_cerrado                           { $$ = new AccessVector($1, $3, @1.first_line, @1.first_column); }
;

F
    : par_abierto EXP par_cerrado                               { $$ = $1; }
    | decimal                                                   { 
                                                                    arreglo_.push({label: `${$1}`, hijos: null});
                                                                    $$ = new Literal($1, Type.DECIMAL, @1.first_line, @1.first_column, @1.first_line, @1.first_column)
                                                                }
    | number                                                    { $$ = new Literal($1, Type.NUMBER, @1.first_line, @1.first_column); }
    | string                                                    { $$ = new Literal($1, Type.STRING, @1.first_line, @1.first_column); }
    | bool                                                      { $$ = new Literal($1, Type.BOOLEAN, @1.first_line, @1.first_column); }
    | char                                                      { $$ = new Literal($1, Type.CHAR, @1.first_line, @1.first_column); }
    | ID                                                        { $$ = new Literal($1, null, @1.first_line, @1.first_column); }
;


VOIDINSTRUC
    : metod_void par_abierto par_cerrado SENTENCIA                  { $$ = new Function(Type.NULL, TypeIns.VOID, $2, $5, [], @1.first_line, @1.first_column); }
    : metod_void par_abierto PARAMS par_cerrado SENTENCIA           { $$ = new Function($2, TypeIns.VOID, $2, $6, $4, @1.first_line, @1.first_column); }
;

FUNCIONINSTRUCCION
    : Const DATATYPE ID par_abierto par_cerrado SENTENCIA           { $$ = new Function($2, TypeIns.FUNCTION, $3, $6, [], @1.first_line, @1.first_column); }
    | Const DATATYPE ID par_abierto PARAMS par_cerrado SENTENCIA    { $$ = new Function($2, TypeIns.FUNCTION, $3, $7, $5, @1.first_line, @1.first_column); }
;

LLAMADA
    : principal_main ID par_abierto par_cerrado                     { $$ = new Function($2, TypeIns.FUNCTION, $3, $6, [], @1.first_line, @1.first_column)}
    | principal_main ID par_abierto LISTAEXP par_cerrado            { $$ = new Function($2, TypeIns.FUNCTION, $3, $7, $5, @1.first_line, @1.first_column)}
;

LISTAEXP
    : LISTAEXP sig_coma EXP                                         { $1.push($3); $$ = $1; }
    | EXP                                                           { $$ = [$1]; }
;

PARAMS
    : PARAMS sig_coma PARAM                                         { $1.push($3); $$ = $1; }
    | PARAM                                                         { $$ = [$1]; }
;

PARAM
    : DATATYPE ID                                                   { $$ = new Params($1, $2, @1.first_line, @1.first_column); }
;

ToLowerUpperEXP
    : funcion_tolower par_abierto EXP par_cerrado                   { $$ = new ToLowerUpper($3, 1, @1.first_line, @1.first_column); }
    | funcion_toupper par_abierto EXP par_cerrado                   { $$ = new ToLowerUpper($3, 2, @1.first_line, @1.first_column); }
;

RoundEXP
    : native_round par_abierto EXP par_cerrado                      { $$ = new Round($3, @1.first_line, @1.first_column); }
;

VECTOR
    : operador_new DATATYPE cor_abierto LISTAEXP cor_cerrado        { $$ = new Vector(2, $2, $4, @1.first_line, @1.first_column); }
    | EXP                                                           { $$ = $1; }
;

ACCESSLIST
    : ACCESSLIST cor_abierto EXP cor_cerrado                        { $1.push($3); $$ = $1; }
    | ID cor_abierto EXP cor_cerrado                                { $$ = [$1, $3]; }
;
//------------------------------------------------------

