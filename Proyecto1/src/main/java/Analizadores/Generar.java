/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadores;
/**
 *
 * @author Pacos
 */
public class Generar {
    public static void main(String[] args){
        try{
            String ruta = "src/main/java/analizadores/";
            String[] opcFlex = {ruta +  "lexico.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String[] opcCup = {"-destdir", ruta, "-parser", "Sintactico", ruta + "sintactico.cup"};
            java_cup.Main.main(opcCup);
        }catch(Exception e){

        }
    }
}