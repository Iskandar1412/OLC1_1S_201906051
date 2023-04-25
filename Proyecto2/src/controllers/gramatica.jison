
%lex
// Declaración de reglas de gramática BNF
// Las reglas se definen en el siguiente formato:
// rule: production1 production2 { action }
/*declaramos caso insensitivo*/
%options case-insensitive
%x string

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
[a-zA-Z][a-zA-Z0-9_]*                                   return 'ID';
[0-9]+("."[0-9]+)?\b                                    return 'decimal';
[0-9]+\b                                                return 'entero';

["]                                                     { cadena=""; this.begin("string"); }
<string>[^"\\]+                                         { cadena+=yytext; }
<string>"\\\""                                          { cadena+="\""; }
<string>"\\n"                                           { cadena+="\n"; }
<string>"\\t"                                           { cadena+="\t"; }
<string>"\\\\"                                          { cadena+="\\"; }
<string>"\\\'"                                          { cadena+="\'"; }
<string>["]                                             { yytext=cadena; this.popState(); return 'Cadena'; }
\'((\\\')|[^\n\'])*\'	                                { yytext = yytext.substr(1,yyleng-2); return 'Char'; }

<<EOF>>                                                 return 'EOF';

.                                                       {
                                                            console.error('Error léxico: ' + yytext + ', línea: ' + yylloc.first_line + ', columna: ' + yylloc.first_column);
                                                            TablaErrores.getInstance().insertarError(new _Error("Lexico", "Caracter: \"" + yytext + "\" no valido", yylloc.first_line, yylloc.first_column));
                                                            return null;
                                                        }


/lex

%{
  // Declaración de código JavaScript

    const { TablaErrores } = require('./interpreter/errores/TablaErrores.js');
    const { _Error } = require('./interpreter/errores/_Error.js');

    var cadena = '';
    var tipo_variable = '';
    function AST_Node(name, value, tipo, entorno, fila, columna) {
    this.name = name;
    this.value = value;
    this.tipo = tipo;
    this.entorno = entorno;
    this.fila = fila;
    this.columna = columna;
    this.hijos = [];
    this.addHijos = addHijos;
    this.getHermano = getHermano;

    function addHijos() {
      for(var i = 0;  i < arguments.length; i++) {
        this.hijos.push(arguments[i]);
        if(arguments[i == null]) {
          arguments[i].padre.this;
        }
      }
    }

    function getHermanos(temp) {
      if(temp > this._hijos.lenght - 1) return null;
      return this._hijos[temp];
    }
  }

%}



//precedencia de operaciones

%right sig_interrogacion
%left log_or
%left log_and
%right log_not
%left rel_igualacion, rel_diferenciacion, rel_menor, rel_mayor, rel_menor_igual_que, rel_mayor_igual_que
%left operacion_suma, operacion_resta
%left operacion_divide, operacion_multiplicar, operacion_mod
%right ucast
%nonassoc operacion_potencia
%left unot umas, umenos
%right pre_inc pre_dec
%left op_incrementar op_decrementar


%start INIT
%%
/*Declaración de acciones semánticas en JavaScript*/

INIT
    : LINS EOF                                                                                                                                      { $$ = new AST_Node("RAIZ", "RAIZ", "palabra reservada", "global", this.$first_line, this._$.last_column); $$.addHijos($1); return $$; }
;

LINS
    : LINS INSTRUCC                                                                                                                                 { $1.addHijos($2); $$ = $1; }
    | INSTRUCC                                                                                                                                      { $$ = new AST_Node("SENTENCIAS","SENTENCIAS","SENTENCIAS","global",this._$.first_line,this._$.last_column); $$.addHijos($1); }
;

INSTRUCC
    : Rprintln par_abierto EXP par_cerrado punto_coma                                                                                               { $$= new AST_Node("PRINTLN","PRINTLN"); $$.addHijos($3); }
    | funcion_print par_abierto EXP par_cerrado punto_coma                                                                                                 { $$= new AST_Node("PRINT","PRINT"); $$.addHijos($3); }
    | DECLARAR punto_coma                                                                                                                           { $$=$1 }
    | ASIGNAR punto_coma                                                                                                                            { $$=$1 } 
    | IF                                                                                                                                            { $$=$1 } 
    | DOWHILE punto_coma                                                                                                                            { $$=$1 }
    | WHILE                                                                                                                                         { $$=$1 }
    | FOR                                                                                                                                           { $$=$1 }
    | SWITCH                                                                                                                                        { $$=$1 }
    | trans_break punto_coma                                                                                                                        { $$=new AST_Node("break","break"); }
    | trans_continue punto_coma                                                                                                                     { $$=new AST_Node("continue","continue"); }
    | METODOS                                                                                                                                       { $$=$1 }
    | LLAMADA punto_coma                                                                                                                            { $$=$1 }
    | RUNMETOD                                                                                                                                      { $$=$1 }
    | RETORNAR                                                                                                                                      { $$=$1 }
    | error                                                                                                                                         {
                                                                                                                                                      console.log("Sintactico","Error en : '"+yytext+"'",
                                                                                                                                                      this._$.first_line,this._$.first_column);
                                                                                                                                                      console.log("Se recupero en ",yytext," (",this._$.last_line,",",this._$.last_column,")");
                                                                                                                                                      TablaErrores.getInstance().insertarError(new _Error("Sintactico","Error en: \" "+yytext+"\" sintaxis no valida" ,this._$.first_line,this._$.first_column));
                                                                                                                                                    }
;
//falta en el semantico    
RETORNAR //Valores AST name, value, tipo,entorno, fila, columna
    : trans_return EXP punto_coma                                                                                                                   { $$= new AST_Node("RETURN","RETURN");            $$.addHijos(new AST_Node("return","return","return","return",this._$.first_line,this._$.first_column) ,$2) }
    | trans_return punto_coma                                                                                                                       { $$= new AST_Node("RETURN","RETURN");            $$.addHijos(new AST_Node("return","return","return","return",this._$.first_line,this._$.first_column)) }
;

DECLARAR // aqui inician las declaraciones de variables Valores AST name, value, tipo,entorno, fila, columna
    : LST_IDS                                                                                                                                       { $$ = $1 }
    | DECLARACION_INDV                                                                                                                              { $$= $1 }
;

///declaracines
DECLARACION_INDV  // esta funciona para hacer una declaracion simple
    : TIPO ID rel_igual EXP                                                                                                                         { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $$.addHijos(new AST_Node("ID",$2,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column),$4) }
    | TIPO ID                                                                                                                                       { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $$.addHijos(new AST_Node("ID",$2,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column)) }
    | TIPO cor_abierto cor_cerrado ID rel_igual operador_new TIPO cor_abierto EXP cor_cerrado                                                       { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $6 = new AST_Node("NEW","NEW",$7,"NEW",this._$.first_line,this._$.first_column);          $6.addHijos($9);        $$.addHijos(new AST_Node("ID",$4,$1,"VECTOR",this._$.first_line,this._$.first_column),new AST_Node("VECTOR1D","VECTOR1D"),$6); }
    | TIPO cor_abierto cor_cerrado ID rel_igual CHARTOARRAY                                                                                         { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $5 = new AST_Node("TOARRAY","TOARRAY",$1,"NEW",this._$.first_line,this._$.first_column);  $5.addHijos($6);        $$.addHijos(new AST_Node("ID",$4,$1,"VECTOR",this._$.first_line,this._$.first_column),new AST_Node("VECTOR1D","VECTOR1D"),$5); }
    | TIPO cor_abierto cor_cerrado ID rel_igual  llave_abierta LST_EXP llave_cerrada                                                                { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $7.tipo = tipo_variable;      $$.addHijos(new AST_Node("ID",$4,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column),new AST_Node("VECTOR1D","VECTOR1D"),$7); }
    | TIPO cor_abierto cor_cerrado cor_abierto cor_cerrado ID rel_igual operador_new TIPO cor_abierto EXP cor_cerrado cor_abierto EXP cor_cerrado   { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $8 = new AST_Node("NEW","NEW",$9,"NEW",this._$.first_line,this._$.first_column);          $8.addHijos($11,$14);   $$.addHijos(new AST_Node("ID",$6,tipo_variable,"VECTOR",this._$.first_line,this._$.first_column),new AST_Node("VECTOR2D","VECTOR2D"),$8); }
    | TIPO cor_abierto cor_cerrado cor_abierto cor_cerrado ID rel_igual cor_abierto AUXVECTOR cor_cerrado                                           { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $$.addHijos(new AST_Node("ID",$6,tipo_variable,"VECTOR",this._$.first_line,this._$.first_column),new AST_Node("VECTOR2D","VECTOR2D"),$9); }
    | listas_ou rel_menor TIPO rel_mayor ID rel_igual operador_new listas_ou rel_menor TIPO rel_mayor                                               { $$= new AST_Node("DECLARACION_INDV","DECLARACION_INDV");        $6 = new AST_Node("NEW","NEW",$9,"NEW",this._$.first_line,this._$.first_column);          $$.addHijos(new AST_Node("ID",$5,"LISTA",this._$.first_line,this._$.first_column),new AST_Node("LIST","LIST"),$7); }
;
//falta en el semantico
LST_IDS //esta funciona para devolver ,id,id ,id para declarar variables Valores AST name, value, tipo,entorno, fila, columna
    : LST_IDS sig_coma ID                                                                                                                           { $1.addHijos(new AST_Node("ID",$3,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column)); $$=$1; }
    | LST_IDS sig_coma ID rel_igual EXP                                                                                                             { $1.addHijos(new AST_Node("ID",$3,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column),$5); $$=$1; }
    | TIPO ID sig_coma ID                                                                                                                           { $$= new AST_Node("ID_LIST","ID_LIST");          $$.addHijos(new AST_Node("ID",$2,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column)); $$.addHijos(new AST_Node("ID",$4,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column)); }
    | TIPO ID sig_coma ID rel_igual EXP                                                                                                             { $$= new AST_Node("ID_LIST","ID_LIST");          $$.addHijos(new AST_Node("ID",$2,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column)); $$.addHijos(new AST_Node("ID",$4,tipo_variable,"VARIABLE",this._$.first_line,this._$.first_column , $6)); }
   
;
//falta en el semantico
CHARTOARRAY
    : native_tochararray par_abierto EXP par_cerrado                                                                                                { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("charToArray",$1,"charToArray","charToArray",this._$.first_line,this._$.last_column);   $1.addHijos($3) ;$$.addHijos($1); }
;

METODOS
    : TIPO ID par_abierto par_cerrado BODY                                                                                                          { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$2,tipo_variable,"",this._$.first_line,this._$.last_column), $1,$6); }
    | metod_void ID par_abierto par_cerrado BODY                                                                                                    { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$2,"void","",this._$.first_line,this._$.last_column), $1,$5); }
    | ID par_abierto par_cerrado  BODY                                                                                                              { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$1,"void","",this._$.first_line,this._$.last_column), $4); }
    | TIPO ID par_abierto PARAMETROS par_cerrado BODY                                                                                               { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$2,tipo_variable,"",this._$.first_line,this._$.last_column),$1, $4,$6); }
    | metod_void ID par_abierto PARAMETROS par_cerrado BODY                                                                                         { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$2,"void","",this._$.first_line,this._$.last_column),$1, $4,$6); }
    | ID par_abierto PARAMETROS par_cerrado  BODY                                                                                                   { $$= new AST_Node("METODOS","METODOS");          $$.addHijos(new AST_Node("ID",$1,"void","",this._$.first_line,this._$.last_column), $3,$5); }
;
//falta en el semantico
PARAMETROS
    : PARAMETROS sig_coma TIPO ID                                                                                                                   { $1.addHijos($3, new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column)); $$=$1; }
    | TIPO ID                                                                                                                                       { $$= new AST_Node("PARAMETROS","PARAMETROS");    $$.addHijos($1,new AST_Node("ID",$1,tipo_variable,"",this._$.first_line,this._$.last_column)); }
;

ASIGNAR //Valores AST name, value, tipo,entorno, fila, columna
    : ID rel_igual EXP                                                                                                                              { $$= new AST_Node("ASIGNAR","ASIGNAR");          $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),$3); }
    | ID INCorDEC                                                                                                                                   { $$= new AST_Node("ASIGNAR","ASIGNAR");          $2.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column));              $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),$2); }
    | ID cor_abierto EXP cor_cerrado rel_igual EXP                                                                                                  { $$= new AST_Node("ASIGNAR","ASIGNAR");          $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),$3,$6);}    
    | ID cor_abierto EXP cor_cerrado  cor_abierto EXP cor_cerrado rel_igual  EXP                                                                    { $$= new AST_Node("ASIGNAR","ASIGNAR");          $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),$3,$6,$9); }
;

INCorDEC
    : op_incrementar                                                                                                                                { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("incdes",$1,"incdes","incdes",this._$.first_line,this._$.last_column)); }
    | op_decrementar                                                                                                                                { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("incdes",$1,"incdes","incdes",this._$.first_line,this._$.last_column)); }
;
//falta en el semantico    
IF
    : control_if par_abierto EXP par_cerrado BODY                                                                                                   { $$= new AST_Node("IF","IF");                    $$.addHijos($3,$5); }
    | control_if par_abierto EXP par_cerrado BODY control_else BODY                                                                                 { $$= new AST_Node("IF","IF");                    $6= new AST_Node("ELSE","ELSE");        $6.addHijos($7);    $$.addHijos($3,$5,$6); }
    | control_if par_abierto EXP par_cerrado BODY control_else IF                                                                                   { $$= new AST_Node("IF","IF");                    $6= new AST_Node("ELSEIF","ELSEIF");    $6.addHijos($7);    $$.addHijos($3,$5,$6); }
;
//falta en el semantico    
SWITCH
    : control_switch par_abierto EXP par_cerrado llave_abierta LCASOS control_default doble_punto LINS llave_cerrada                                { $$= new AST_Node("SWITCH","SWITCH");            $7 =new AST_Node("DEFAULT","DEFAULT");  $7.addHijos($9);    $$.addHijos($3,$6,$7); }
    | control_switch par_abierto EXP par_cerrado llave_abierta LCASOS llave_cerrada                                                                 { $$= new AST_Node("SWITCH","SWITCH");            $$.addHijos($3,$6); }
    | control_switch par_abierto EXP par_cerrado llave_abierta control_default doble_punto LINS llave_cerrada                                       { $$= new AST_Node("SWITCH","SWITCH");            $6 =new AST_Node("DEFAULT","DEFAULT");  $6.addHijos($8);    $$.addHijos($3,$6); }
;
//falta en el semantico    
ELSEIF
    : control_if par_abierto EXP par_cerrado BODY                                                                                                   { $$= new AST_Node("ELSEIF","ELSEIF");            $$.addHijos($3,$5); }
    | ELSEIF control_else control_if par_abierto EXP par_cerrado BODY                                                                               { $$= $1;                                         $$.addHijos($5,$7); }
;
//falta en el semantico    
LCASOS
    : control_case EXP doble_punto LINS                                                                                                             { $$= new AST_Node("CASE","CASE");                $$.addHijos($2,$4); }
    | LCASOS control_case EXP doble_punto LINS                                                                                                      { $$= $1;                                         $$.addHijos($3,$5); }
;
//falta en el semantico    
DOWHILE
    : ciclic_do BODY ciclic_while par_abierto EXP par_cerrado                                                                                       { $$= new AST_Node("DOWHILE","DOWHILE");          $$.addHijos($2,$5); }
;
//falta en el semantico    
WHILE
    : ciclic_while par_abierto EXP par_cerrado BODY                                                                                                 { $$= new AST_Node("WHILE","WHILE");              $$.addHijos($3,$5); }
;
//falta en el semantico    
FOR
    : ciclic_for par_abierto ASIGNAR punto_coma EXP punto_coma ASIGNAR par_cerrado BODY                                                             { $$= new AST_Node("FOR","FOR");                  $$.addHijos($3,$5,$7,$9); }
    | ciclic_for par_abierto DECLARAR punto_coma EXP punto_coma ASIGNAR par_cerrado BODY                                                            { $$= new AST_Node("FOR","FOR");                  $$.addHijos($3,$5,$7,$9); }
;
//falta en el semantico    
BODY
    : llave_abierta LINS llave_cerrada                                                                                                              { $$= new AST_Node("BODY","BODY");                $$.addHijos($2); }
    | llave_abierta llave_cerrada                                                                                                                   { $$= new AST_Node("BODY","BODY");                $$.addHijos(new AST_Node("body_null","body_null")); }
;
//falta en el semantico    
ACTUALIZAR                                                      //Valores AST name, value, tipo,entorno, fila, columna
    : ID rel_igual EXP                                                                                                                              { $$= new AST_Node("ACTUALIZAR","ACTUALIZAR");    $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),$3); }
    | ID INCorDEC                                                                                                                                   { $$= new AST_Node("ACTUALIZAR","ACTUALIZAR");    $$.addHijos(new AST_Node("ID",$1,"ID","",this._$.first_line,this._$.last_column),new AST_Node("INCDEC",$2,"INCDEC","",this._$.first_line,this._$.last_column)); }
;
//falta en el semantico    
LLAMADA //Valores AST name, value, tipo,entorno, fila, columna
    : ID par_abierto par_cerrado                                                                                                                    { $$= new AST_Node("LLAMADA","LLAMADA");          $$.addHijos(new AST_Node("ID",$1,"ID","LLAMADA",this._$.first_line,this._$.last_column)); }
    | ID par_abierto L_EXP par_cerrado                                                                                                              { $$= new AST_Node("LLAMADA","LLAMADA");          $$.addHijos(new AST_Node("ID",$1,"ID","LLAMADA",this._$.first_line,this._$.last_column),$3); }
    | principal_main ID par_abierto par_cerrado                                                                                                     { $$= new AST_Node("RUN","RUN");                  $$.addHijos(new AST_Node("ID_RUN",$2,"ID_RUN","LLAMADA",this._$.first_line,this._$.last_column)); }
    | principal_main ID par_abierto L_EXP par_cerrado                                                                                               { $$= new AST_Node("RUN","RUN");                  $$.addHijos(new AST_Node("ID_RUN",$2,"ID_RUN","LLAMADA",this._$.first_line,this._$.last_column),$4); }
;
//falta en el semantico    
CASTEO
    : par_abierto TIPO par_cerrado EXP                                                                                                              { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("cast",$2,$2,"cast",this._$.first_line,this._$.last_column), $4); }
    | par_abierto TIPO par_cerrado ID                                                                                                               { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("cast",$2,$2,"cast",this._$.first_line,this._$.last_column), new AST_Node("ID",$1,"ID","LLAMADA",this._$.first_line,this._$.last_column)); }
;

TIPO
    : tipo_int                                                                                                                                      { tipo_variable = "int";                          $$=$1.toLowerCase(); }
    | tipo_string                                                                                                                                   { tipo_variable = "string";                       $$=$1.toLowerCase(); }
    | tipo_char                                                                                                                                     { tipo_variable = "char";                         $$=$1.toLowerCase(); }
    | tipo_boolean                                                                                                                                  { tipo_variable = "boolean";                      $$=$1.toLowerCase(); }
    | tipo_double                                                                                                                                   { tipo_variable = "double";                       $$=$1.toLowerCase(); }
;

//falta en el semantico    
                                                                                  //Valores AST name, value, tipo,entorno, fila, columna
EXP  
    : EXP operacion_suma EXP                                                                                                                        { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP operacion_resta EXP                                                                                                                       { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP operacion_multiplicar EXP                                                                                                                 { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP operacion_potencia EXP                                                                                                                    { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP operacion_divide EXP                                                                                                                      { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP operacion_mod EXP                                                                                                                         { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_menor EXP                                                                                                                             { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_mayor EXP                                                                                                                             { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_diferenciacion EXP                                                                                                                    { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_igualacion EXP                                                                                                                        { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_menor_igual_que EXP                                                                                                                   { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP rel_mayor_igual_que EXP                                                                                                                   { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3); }
    | EXP log_and EXP                                                                                                                               { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3);}
    | EXP log_or EXP                                                                                                                                { $$= new AST_Node("EXP","EXP");                  $$.addHijos($1,new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$3);}
    | EXP sig_interrogacion EXP doble_punto EXP                                                                                                     { $$= new AST_Node("TERNARIO","TERNARIO");        $$.addHijos($1,new AST_Node("opt",$2,"opt","opt",this._$.first_line,this._$.last_column),$3,$5);}
    | cor_abierto EXP cor_cerrado                                                                                                                   { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column));}
    | EXP op_incrementar                                                                                                                            { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$1);}
    | EXP op_decrementar                                                                                                                            { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("op",$2,"op","op",this._$.first_line,this._$.last_column),$1);}
    | log_not EXP %prec unot                                                                                                                        { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("op",$1,"op","op",this._$.first_line,this._$.last_column) , $2);}
    | operacion_resta EXP %prec umenos                                                                                                              { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("op",$1,"op","op",this._$.first_line,this._$.last_column) , $2);}
    | Cadena                                                                                                                                        { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("string",$1,"string","string",this._$.first_line,this._$.last_column)); }
    | Char                                                                                                                                          { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("char",$1,"char","char",this._$.first_line,this._$.last_column)); }
    | ID                                                                                                                                            { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("ID",$1,"ID","ID",this._$.first_line,this._$.last_column)); }
    | ID par_abierto par_cerrado                                                                                                                    { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("ID",$1,"ID","ID",this._$.first_line,this._$.last_column)); }
    | ID par_abierto LST_EXP par_cerrado                                                                                                            { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("ID",$1,"ID","ID",this._$.first_line,this._$.last_column), $3); }
    | ID cor_abierto EXP cor_cerrado                                                                                                                { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("ID",$1,"ID","ID",this._$.first_line,this._$.last_column), $3); }
    | ID cor_abierto EXP cor_cerrado cor_abierto EXP cor_cerrado                                                                                    { $$= new AST_Node("EXPVECTOR","EXPVECTOR");      $$.addHijos(new AST_Node("ID",$1,"ID","ID",this._$.first_line,this._$.last_column),$3, $6); }
    | entero                                                                                                                                        { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("entero",$1,"entero","entero",this._$.first_line,this._$.last_column)); }
    | decimal                                                                                                                                       { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("numero",$1,"numero","numero",this._$.first_line,this._$.last_column)); }
    | boolean_true                                                                                                                                  { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("true",$1,"true","true",this._$.first_line,this._$.last_column)); }
    | boolean_false                                                                                                                                 { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("false",$1,"false","false",this._$.first_line,this._$.last_column)); }
    | par_abierto EXP par_cerrado                                                                                                                   { $$= new AST_Node("EXP","EXP");                  $$.addHijos($2); }
    | par_abierto TIPO par_cerrado EXP %prec ucast                                                                                                  { $$= new AST_Node("EXP","EXP");                  $$.addHijos(new AST_Node("cast",$2,"cast","cast",this._$.first_line,this._$.last_column), $4); }
    | native_tostring par_abierto EXP par_cerrado %prec ucast                                                                                       { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("toStr",$1,"toStr","toStr",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | funcion_tolower par_abierto EXP par_cerrado %prec ucast                                                                                       { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("toLower",$1,"toLower","toLower",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | funcion_toupper par_abierto EXP par_cerrado %prec ucast                                                                                       { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("toUpper",$1,"toUpper","toUpper",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | native_round par_abierto EXP par_cerrado %prec ucast                                                                                          { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("round",$1,"round","round",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | native_lenght par_abierto EXP par_cerrado %prec ucast                                                                                         { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("leng",$1,"leng","leng",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | native_typeof par_abierto EXP par_cerrado %prec ucast                                                                                         { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("typeOf",$1,"typeOf","typeOf",this._$.first_line,this._$.last_column);$1.addHijos($3);$$.addHijos($1); }
    | native_truncate par_abierto EXP par_cerrado %prec ucast                                                                                       { $$= new AST_Node("EXP","EXP");                  $1=new AST_Node("truncate",$1,"truncate","truncate",this._$.first_line,this._$last_column);$1.addHijos($3);$$.addHijos($1); }
;

AUXVECTOR  //Valores AST name, value, tipo,entorno, fila, columna
    : AUXVECTOR sig_coma cor_abierto LST_EXP cor_cerrado                                                                                            { $1.addHijos($4); $$=$1; }
    | cor_abierto LST_EXP cor_cerrado                                                                                                               { $$= new AST_Node("AUXVECTOR","AUXVECTOR");      $$.addHijos($2); }
;

LST_EXP 
    : LST_EXP sig_coma EXP                                                                                                                          { $1.addHijos($3); $$=$1; }
    | EXP                                                                                                                                           { $$= new AST_Node("LST_EXP","LST_EXP","LST_EXP",this._$.first_line,this._$.first_column); $$.addHijos($1); }
;