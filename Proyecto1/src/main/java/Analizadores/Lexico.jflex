
/* 1. Package e importaciones */
package Analizadores;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.util.ArrayList;
import Project.errors.Errores;
import Project.Token.Tokens;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaraciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
    public ArrayList<Errores> error = new ArrayList<>();
    public ArrayList<Tokens> tokens = new ArrayList<>();
%}

//Directivas
%class Lexico
%public 
%cup
%char
%column
%full
%line
%ignorecase
%unicode

//Inicializar el contador de columna y fila con 1
%init{ 
    yyline = 1; 
    yychar = 1; 
%init}

//Expresiones regulares

BLANCOS = [ \r\t]+
BLANCOS2 = [ \t\r\n\f]+

comentario_simple = "//"[^"\n"]*
comentario_multiple = "<!" [^"!>"]* "!>"

letra = [a-zA-Z]
digito = [0-9]
numero = {digito}+

/*Pal Proyecto*/
llave_abierta = "{"
llave_cerrada = "}"
corchete_abierto = "["
corchete_cerrado = "]"
dos_puntos = ":"
punto_coma = ";"
flecha = "->"
porcentaje = "%%"
virgilla = "~"
coma = ","
punto = "."
or = "|"
asterisco = "*"
mas = "+"
interrogacion = "?"
fin_linea = "\"\\n\""
signo_comilla = "\"\\\'\""
signo_doblecomilla = "\"\\\"\""
rango = [!-/]|[:-@]|[\[-`]|[\{-\}]
espacio = "\" \""
conj = ["c"|"C"]["o"|"O"]["n"|"N"]["j"|"J"]

/*identificadores a usar*/
id = {letra}({letra}|{digito}|"_")* 
cosa = [^"\\\""]* "\\\""
parte_identificador = "\"" {cosa}* [^"\""]* {cosa}* "\""


%%
/* 3. Reglas Semanticas */



\n {yychar=1;}

<YYINITIAL> {
    <letra>                 { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.letra,yyline,yychar,yytext()); }
    <numero>                { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.numero,yyline,yychar,yytext()); }
    <comentario_simple>     { System.out.println("Comentario Simple: " + yytext() + ", linea: " + yyline + ", columna: " + yychar); Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); }
    <comentario_multiple>   { System.out.println("Comentario Multiple: " + yytext() + ", linea: " + yyline + ", columna: " + yychar); Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); }
    <llave_abierta>         { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.llave_abierta,yyline,yychar,yytext()); }
    <llave_cerrada>         { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.llave_cerrada,yyline,yychar,yytext()); }
    <dos_puntos>            { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.dos_puntos,yyline,yychar,yytext()); }
    <punto_coma>            { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.punto_coma,yyline,yychar,yytext()); }
    <flecha>                { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.flecha,yyline,yychar,yytext()); }
    <porcentaje>            { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.porcentaje,yyline,yychar,yytext()); }
    <virgilla>              { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.virgilla,yyline,yychar,yytext()); }
    <coma>                  { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.coma,yyline,yychar,yytext()); }
    <punto>                 { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.punto,yyline,yychar,yytext()); }
    <or>                    { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.or,yyline,yychar,yytext()); }
    <asterisco>             { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.asterisco,yyline,yychar,yytext()); }
    <mas>                   { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.mas,yyline,yychar,yytext()); }
    <interrogacion>         { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.interrogacion,yyline,yychar,yytext()); }
    <fin_linea>             { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.fin_linea,yyline,yychar,yytext()); }
    <signo_comilla>         { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.signo_comilla,yyline,yychar,yytext()); }
    <signo_doblecomilla>    { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.signo_doblecomilla,yyline,yychar,yytext()); }
    <rango>                 { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.rango,yyline,yychar,yytext()); }
    <espacio>               { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.espacio,yyline,yychar,yytext()); }
    <conj>                  { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.conj,yyline,yychar,yytext()); }
    <id>                    { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.id,yyline,yychar,yytext()); }
    <parte_identificador>   { Tokens token_nuevo = new Tokens(yytext(),yyline,yychar); tokens.add(token_nuevo); return new Symbol(sym.parte_identificador,yyline,yychar,yytext()); }
}

{BLANCOS} 				    {} 
{BLANCOS2}                  {}

. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    //System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
    System.out.println("Error Lexico: " + yytext() + ", linea: " + yyline + ", columna: " + yychar);
    Errores aux = new Errores("Lexico", yytext(), yyline, yychar);
    error.add(aux);
    //lexicalErrors.add(new LexicalError(yytext(), yyline, (int) yychar));
}