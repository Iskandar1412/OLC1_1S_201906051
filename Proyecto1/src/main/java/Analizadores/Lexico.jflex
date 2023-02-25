
/* 1. Package e importaciones */
package Analizadores;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import Project.errors.LexicalError;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaraciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
    public LinkedList<LexicalError> lexicalErrors;
%}

//Directivas
%class Lexico
%public 
%cup
%char
%column
%full
%line
%unicode

//Inicializar el contador de columna y fila con 1
%init{ 
    yyline = 1; 
    yychar = 1; 
%init}

//Expresiones regulares

BLANCOS=[ \r\t]+
D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

comentariosimple    = "//" {InputCharacter}* {LineTerminator}?
comentario = ("//".*\r\n)|("//".*\n)|("//".*\r)
comentarioMultilinea = "/*""/"*([^*/]|[^*]"/"|"*"[^/])*"*"*"*/"
comentarioMultiple = \<\!([^"!>"]|[\r|\f|\s|\t|\n])*\!\>


STR = [\"][^\"]*[\"]
LETRA = [a-zA-Z]

IDENTIFICADOR = ({LETRA})?("_"|{LETRA}|{D})




%%
/* 3. Reglas Semanticas */
/*System.out.println("Reconocio PR: "+yytext());*/
"CONJ" 					{return new Symbol(sym.CONJ,yyline,yychar,yytext());} 
/* System.out.println("Reconocio "+yytext()+" punto y coma"); */
";" 					{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" parentesis abre"); */
"(" 					{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" parentesis cierra"); */
")" 					{return new Symbol(sym.PARDER,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" corchete abre"); */
"[" 					{return new Symbol(sym.CORIZQ,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" corchete cierra"); */
"]" 					{return new Symbol(sym.CORDER,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" llave abre"); */
"}" 					{return new Symbol(sym.LLAVDER,yyline,yychar, yytext());} 
/* System.out.println("Reconocio "+yytext()+" llave cierra"); */
"{" 					{return new Symbol(sym.LLAVIZQ,yyline,yychar, yytext());} 


"+" 					{return new Symbol(sym.MAS,yyline,yychar, yytext());} 
"-" 					{return new Symbol(sym.MENOS,yyline,yychar, yytext());} 
"*" 					{return new Symbol(sym.POR,yyline,yychar, yytext());} 
"/" 					{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());} 
/*Del proyecto a ver que sale :v*/
">" 					{return new Symbol(sym.MAYOR,yyline,yychar,yytext());}
"," 					{return new Symbol(sym.COMA,yyline,yychar,yytext());}
"~" 					{return new Symbol(sym.APROX,yyline,yychar,yytext());}
"\"" 					{return new Symbol(sym.COMILLA,yyline,yychar,yytext());}

"." 					{return new Symbol(sym.AND,yyline,yychar,yytext());}
"|"						{return new Symbol(sym.OR,yyline,yychar,yytext());}
"?"						{return new Symbol(sym.STARNO,yyline,yychar,yytext());}
"!"						{return new Symbol(sym.SG_ADMIRACION,yyline,yychar,yytext());}
"&"						{return new Symbol(sym.YY,yyline,yychar,yytext());}
"%%"					{return new Symbol(sym.DOBPOR,yyline,yychar,yytext());}
":"						{return new Symbol(sym.DOBPT,yyline,yychar,yytext());}


\n {yychar=1;}
{BLANCOS} 				{} 
{comentario} 			{}
{comentarioMultilinea} 	{}
{comentariosimple} 		{} /*System.out.println("Comentario: "+yytext());*/
{comentarioMultiple}	{}

{IDENTIFICADOR}			{return new Symbol(sym.IDENT,yyline,yychar,yytext());}

{STR} 					{return new Symbol(sym.STRING,yyline,yychar, yytext());}
{D} 					{return new Symbol(sym.ENTERO,yyline,yychar, yytext());} 
{DD} 					{return new Symbol(sym.DECIMAL,yyline,yychar, yytext());} 

. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    //System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
    lexicalErrors.add(new LexicalError(yytext(), yyline, (int) yychar));
}