
/* 1. Package e importaciones */
package Analizadores;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.util.ArrayList;
import Project.errors.Errores;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaraciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
    public ArrayList<Errores> a = new ArrayList<>();
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
comentario = (\/\/).*
comentarioMultiple = \<\!([^"!>"]|[\r|\f|\s|\t|\n])*\!\>
strings = \"(\!|[\#-\»]|\s)*\"

STR = [\"][^\"]*[\"]
LETRA = [a-zA-Z]

IDENTIFICADOR = ({LETRA})?(_|{LETRA}|{D})+
SIMBOLO = [!-$]|[&-)]|\/|-|[<->]|@|[\[-\`] 
SPE = [\\n]|[\\\"]|[\\\']
FLECHA = -(\s)*>;


%%
/* 3. Reglas Semanticas */
/*System.out.println("Reconocio PR: "+yytext());*/
"CONJ" 					{return new Symbol(sym.conjunto,yyline,yychar,yytext());} 

/* System.out.println("Reconocio "+yytext()+" punto y coma"); */
";" 					{return new Symbol(sym.puntocoma,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" parentesis abre"); */
"(" 					{return new Symbol(sym.parizq,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" parentesis cierra"); */
")" 					{return new Symbol(sym.parder,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" corchete abre"); */
"[" 					{return new Symbol(sym.corizq,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" corchete cierra"); */
"]" 					{return new Symbol(sym.corder,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" llave abre"); */
"}" 					{return new Symbol(sym.cierre,yyline,yychar, yytext());} 

/* System.out.println("Reconocio "+yytext()+" llave cierra"); */
"{" 					{return new Symbol(sym.apertura,yyline,yychar, yytext());} 



"+" 					{return new Symbol(sym.positiva,yyline,yychar, yytext());} 
"-" 					{return new Symbol(sym.menos,yyline,yychar, yytext());} 
"*" 					{return new Symbol(sym.kleene,yyline,yychar, yytext());} 
"/" 					{return new Symbol(sym.dividido,yyline,yychar, yytext());} 
/*Del proyecto a ver que sale :v*/
">" 					{return new Symbol(sym.mayor,yyline,yychar,yytext());}
"," 					{return new Symbol(sym.coma,yyline,yychar,yytext());}
"~" 					{return new Symbol(sym.guion,yyline,yychar,yytext());}
"\"" 					{return new Symbol(sym.sg_comilla,yyline,yychar,yytext());}

"." 					{return new Symbol(sym.concatenacion,yyline,yychar,yytext());}
"|"						{return new Symbol(sym.or,yyline,yychar,yytext());}
"?"						{return new Symbol(sym.cerouno,yyline,yychar,yytext());}
"!"						{return new Symbol(sym.sg_admiracion,yyline,yychar,yytext());}
"&"						{return new Symbol(sym.sg_yy,yyline,yychar,yytext());}
"%"					    {return new Symbol(sym.porcentaje,yyline,yychar,yytext());}
":"						{return new Symbol(sym.dospuntos,yyline,yychar,yytext());}

\n {yychar=1;}



{BLANCOS} 				{} 
{comentario} 			{}
{comentariosimple} 		{} /*System.out.println("Comentario: "+yytext());*/
{comentarioMultiple}	{}
{FLECHA}                {return new Symbol(sym.flecha, yyline,yychar,yytext());}

{IDENTIFICADOR}			{return new Symbol(sym.id,yyline,yychar,yytext());}
{SIMBOLO}               {return new Symbol(sym.simbolo,yyline,yychar,yytext());}

{LETRA}                 {return new Symbol(sym.letra,yyline, yychar, yytext());}
{strings}               {return new Symbol(sym.cadena,yyline, yychar, yytext());}

{STR} 					{return new Symbol(sym.string_s,yyline,yychar, yytext());}
{D} 					{return new Symbol(sym.digito,yyline,yychar, yytext());} 
{DD} 					{return new Symbol(sym.decimal,yyline,yychar, yytext());} 
{SPE}                   {return new Symbol(sym.especial,yyline,yychar, yytext());}

. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    //System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
    System.out.println("Error Lexico: " + yytext() + ", linea: " + yyline + ", columna: " + yychar);
    Errores aux = new Errores("Lexico", yytext(), yyline, yychar);
    a.add(aux);
    //lexicalErrors.add(new LexicalError(yytext(), yyline, (int) yychar));
}