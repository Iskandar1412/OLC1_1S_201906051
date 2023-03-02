
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
%cupsym Simbolos
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


%state ESTADOCADENA
%%
/* 3. Reglas Semanticas */



\n {yychar=1;}

<YYINITIAL> {
    
}

{BLANCOS} 				{} 
{BLANCOS2}              {}
{comentario} 			{}
{comentariosimple} 		{} /*System.out.println("Comentario: "+yytext());*/
{comentarioMultiple}	{}


. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    //System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
    System.out.println("Error Lexico: " + yytext() + ", linea: " + yyline + ", columna: " + yychar);
    Errores aux = new Errores("Lexico", yytext(), yyline, yychar);
    error.add(aux);
    //lexicalErrors.add(new LexicalError(yytext(), yyline, (int) yychar));
}