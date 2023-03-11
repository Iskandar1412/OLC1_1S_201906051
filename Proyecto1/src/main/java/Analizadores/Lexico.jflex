/* 1. Package e importaciones */
package analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;
import Project.Error.Error_LS;
import Project.Token.tokens;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaraciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
    //public LinkedList<String> val = new LinkedList<String>();
    public  LinkedList<Error_LS> errores  = new LinkedList<Error_LS>();
    public  LinkedList<tokens> TokensList  = new LinkedList<tokens>();
%}

//Directivas 
%class Lexico                                                 
%public
%cup
%cupsym Simbolos
%char
%column
%full
%line
%ignorecase

//Expresiones regulares

//esppacios
espacios_muchos = [ \t\r\n\f]+

//letras & digitos
letra = [a-zA-Z]
digito = [0-9]
numero = {digito}+
s_flecha = -(\s)*>

//comentarios
comentario_simple = "//" [^"\n"]* 
comentario_multiple = "<!" [^"!>"]* "!>"

//Pal Proyecto
llave_abierta = "{"
llave_cerrada = "}"
dos_puntos = ":"
punto_coma = ";"

s_porcentaje = "%%"
virgilla	 = "~"
coma	 = ","
punto = "."
or = "|"
asterisco = "*"
s_mas = "+"
s_interrogacion = "?"
fin_linea = "\\n"
s_comilla = "\\\'"
doble_comilla = "\\\""
range = [!-/] | [:-@] | [\[-`] | [\{-\}]
espacio = "\" \""
conj_sym = ["c"|"C"]["o"|"O"]["n"|"N"]["j"|"J"]
ident = {letra}({letra}|{digito}|"_")*
string_dat = [^"\\\""]* "\\\""
s_frase = "\"" {string_dat}* [^"\""]*  {string_dat}* "\""


%state ESTADOCADENA
%%
/* 3. Reglas Semanticas */

<YYINITIAL>{
    {letra}                 { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.letra, yycolumn, yyline, yytext()); }
    {numero}                { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.numero, yycolumn, yyline, yytext()); }   
    {comentario_simple}     { /*System.out.println("Comentario Simple: "+ yytext());*/ tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); }
    {comentario_multiple}   { /*System.out.println("Comentario Multiple: "+ yytext());*/ tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); }
    {llave_abierta}         { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.llave_abierta, yycolumn, yyline, yytext()); }
    {llave_cerrada}         { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.llave_cerrada, yycolumn, yyline, yytext()); }
    {dos_puntos}            { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.dos_puntos, yycolumn, yyline, yytext()); }
    {punto_coma}            { tokens tmp= new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.punto_coma, yycolumn, yyline, yytext()); }
    {s_flecha}              { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_flecha, yycolumn, yyline, yytext()); }
    {s_porcentaje}          { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_porcentaje, yycolumn, yyline, yytext()); }
    {virgilla}              { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.virgilla, yycolumn, yyline, yytext()); }
    {coma}                  { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.coma, yycolumn, yyline, yytext()); }
    {punto}                 { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.punto, yycolumn, yyline, yytext()); }
    {or}                    { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.or, yycolumn, yyline, yytext()); }
    {asterisco}             { return new Symbol(sym.asterisco, yycolumn, yyline, yytext()); }
    {s_mas}                 { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_mas, yycolumn, yyline, yytext()); }
    {s_interrogacion}       { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_interrogacion, yycolumn, yyline, yytext()); }
    {fin_linea}             { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.fin_linea, yycolumn, yyline, yytext()); }
    {s_comilla}             { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_comilla, yycolumn, yyline, yytext()); }
    {doble_comilla}         { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.doble_comilla, yycolumn, yyline, yytext()); }
    {range}                 { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.range, yycolumn, yyline, yytext()); }
    {espacio}               { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.espacio, yycolumn, yyline, yytext()); }
    {conj_sym}              { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.conj_sym, yycolumn, yyline, yytext()); }
    {ident}                 { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.ident, yycolumn, yyline, yytext()); }
    {s_frase}               { tokens tmp = new tokens(yytext(), yyline, yycolumn); TokensList.add(tmp); return new Symbol(sym.s_frase, yycolumn, yyline, yytext()); }
}


{espacios_muchos} { }

.   {
        //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
        //System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
        Error_LS tmp = new Error_LS("Lexico", yytext(),"Caracter no encontrado", yyline, yycolumn);
        System.out.println("Error Lexico { \n\tLexema: "+yytext() + ", Linea: " + yyline + ", Columna: " + yycolumn + "\n}");
        errores.add(tmp);
        //lexicalErrors.add(new LexicalError(yytext(), yyline, (int) yychar));
    }
