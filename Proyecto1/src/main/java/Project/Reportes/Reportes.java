/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Reportes;
import Project.Error.Error_LS;
import Project.Error.*;
import Project.Automata.*;
import java.io.FileWriter;
import java.util.LinkedList;
/**
 *
 * @author Pacos
 */
public class Reportes {
    String ReporteInicio = "";
    String ReporteFinal = "";
    String ReporteJuntar = "";
    String ReporteLexico = "";
    String ReporteSintactico = "";
    String ReporteMamiferos = "";
    String ReporteMoluscos = "";

   public void GenerarReporte(LinkedList<Error_LS> errores, LinkedList<Error_LS> errores2) {
        Starrt();
        ReporteLexico(errores);
        ReporteSintactico(errores2);
        ReporteJuntar();
        ReporteMostrar();
   }
	
    public void ReporteLexico(LinkedList<Error_LS> errores) {
        //String tipo,String lexema,String descripcion, int linea, int columna

        ReporteLexico = "\t\t\t<table class=\"steelBlueCols\">\n\t\t\t\t<thead>\n\t\t\t\t\t<tr>\n\t\t\t\t\t\t<th>Linea</th>\n"
                + "\t\t\t\t\t\t<th>Columna</th>\n\t\t\t\t\t\t<th>Lexema</th>\n\t\t\t\t\t\t<th>Tipo</th>\n\t\t\t\t\t\t<th>Descripcion</th>\n"
                + "\t\t\t\t\t</tr>\n\t\t\t\t</thead>\n\t\t\t\t<tbody>\n\t\t\t\t\t<tr>\n";

        for (Error_LS error : errores) {
            ReporteLexico += "\t\t\t\t\t<tr>\n";
            ReporteLexico +="\t\t\t\t\t\t<td>" + error.linea +" </td>\n" ;
            ReporteLexico +="\t\t\t\t\t\t<td>" + error.columna +" </td>\n" ;
            ReporteLexico +="\t\t\t\t\t\t<td>" + error.lexema+" </td>\n" ;
            ReporteLexico +="\t\t\t\t\t\t<td>" + error.tipo +" </td>\n" ;
            ReporteLexico +="\t\t\t\t\t\t<td>" + error.descripcion +" </td>\n" ;
            ReporteLexico += "\t\t\t\t\t</tr>\n";
        }
        ReporteLexico += "\t\t\t\t\t</tr>\n\t\t\t\t</tbody>\n\t\t\t</table>";
    }
	
    public void ReporteSintactico(LinkedList<Error_LS> errores) {
        ReporteSintactico= "\n\t\t\t<table class=\"steelBlueCols\">\n\t\t\t\t<thead>\n\t\t\t\t\t<tr>\n\t\t\t\t\t\t<th>Linea</th>\n"
                + "\t\t\t\t\t\t<th>Columna</th>\n\t\t\t\t\t\t<th>Lexema</th>\n\t\t\t\t\t\t<th>Tipo</th>\n\t\t\t\t\t\t<th>Descripcion</th>\n\t\t\t\t\t</tr>"
                + "\n\t\t\t\t</thead>\n\t\t\t\t<tbody>\n\t\t\t\t\t<tr>\n";

        for (Error_LS error : errores) {
            ReporteSintactico += "\t\t\t\t\t<tr>\n";
            ReporteSintactico += "\t\t\t\t\t\t<td>" + error.linea + " </td>\n";
            ReporteSintactico += "\t\t\t\t\t\t<td>" + error.columna + " </td>\n";
            ReporteSintactico += "\t\t\t\t\t\t<td>" + error.lexema + " </td>\n";
            ReporteSintactico += "\t\t\t\t\t\t<td>" + error.tipo + " </td>\n";
            ReporteSintactico += "\t\t\t\t\t\t<td>" + error.descripcion + " </td>\n";
            ReporteSintactico += "\t\t\t\t\t</tr>\n";
        }
        ReporteSintactico += "\t\t\t\t\t</tr>\n\t\t\t\t</tbody>\n\t\t\t</table>";
    }
    //REPORTE JUNTA
    public  void ReporteJuntar() { ReporteJuntar = ReporteLexico + ReporteSintactico; }

    //Reporte mostrear 
    public  void ReporteMostrar() {
        try{
            FileWriter archivo = new FileWriter("src\\Reportes\\ERRORES_201906051\\Errores.html");
            archivo.write(ReporteInicio + ReporteJuntar +   ReporteFinal);
            archivo.close();
        }catch(Exception e) { }
    }
	
    //Reportes incio
    public  void Starrt() {
        ReporteInicio = "<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset=\"UTF-8\">\n\t\t<meta name=\"name\" content=\"Juan Urbina\">\n\t\t<meta name=\"description\" content=\"Reporte de Errores\">\n"
            + "\t\t<meta name=\"keywods\" content=\"Creacion de AFDS\">\n\t\t<link rel=\"shortcut icon\" href=\"https://cdn-icons-png.flaticon.com/512/4081/4081896.png\">\n\t\t<meta name=\"robots\" content=\"Index, Follow\">\n\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/>\n\t\t<title>Reporte de Errores - 1S2023</title>\n"
            + "\t</head>\n\t<body>\n\t\t<center>\n\t\t\t<h1 class=\"titulos\">\n\t\t\t\t<font size=\"30px\">\n\t\t\t\t\t<b>Reportes de Errores - Juan Urbina (201906051)</b>\n\t\t\t\t</h1>\n\t\t</center>\n\t\t<center>\n";
        ReporteFinal = "\n\t\t</center>\n\t</body>\n</html>";
    }
}
