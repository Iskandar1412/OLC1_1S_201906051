/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author Pacos
 */
public class Generate {
    public static void main(String[] args){
        try{
            String ruta = "src/main/java/Analizadores/";
            String[] opcFlex = {ruta +  "Lexico.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String[] opcCup = {"-destdir", ruta, "-parser", "Sintactico", ruta + "Sintactico.cup"};
            java_cup.Main.main(opcCup);
        }catch(Exception e){

        }
    }
}