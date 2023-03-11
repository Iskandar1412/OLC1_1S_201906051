/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
/**
 *
 * @author Pacos
 */
public class Conjunto {
    String tipo;
    String nombre;
    String var1,var2;
    String text;
    Integer CharVar1, CharVar2;
    String[] letras;
    Boolean validado = false;
    Boolean ValidarCadena;

    public Conjunto(String tipo, String nombre, String var1, String var2, String text) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.var1 = var1;
        this.var2 = var2;
        this.text = text;
        this.define();
        this.ValidarCadena = true;
    }

    public final void define() {
        if (isRank() == false) {
            letras = text.split(",");
        } else {
            if (var1.equals("\" \"")) {
                this.CharVar1 = 32;
            } else {
                char character1 = this.var1.charAt(0);
                this.CharVar1 = (int) character1;
            }
            char character2 = this.var2.charAt(0);
            this.CharVar2 = (int) character2;
            if (CharVar1 < CharVar2) {
                //System.out.println("Rango invalido");
                this.ValidarCadena = false;
            }
        }
    }

    public Boolean validar(String letter) {
        validado = false;
        if (isRank()) { validarRango(letter); }
        else { validarComas(letter); }
        return validado;
    }

    public void validarRango(String letter) {
        if (ValidarCadena) {
            //System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLL");
            char character = letter.charAt(0);
            int numberLetter = (int) character;
            if(numberLetter>=this.CharVar1 && numberLetter<= this.CharVar2) { validado = true; }
        }
    }

    public void validarComas(String letter) {
        for (String i : letras) {
            //System.out.println("ASDFDASDF");
            if(i.equals("\"\\\"\"")){
                if ("\\\"".equals(letter)) {
                    validado = true;
                    break;
                }
            }else if(i.equals("\"\\'\"")){
                //System.out.println("FFFFFFFFFFFFFFFFFFFFF");
                if ("\\'".equals(letter)) {
                    //System.out.println("HHHHHHHHHHHHHHHHHHHH");
                    validado = true;
                    break;
                }
            }else if(i.equals("\"\\n\"")){
                if(letter.equals("\n")) {
                    validado = true;
                    break;
                }
            }else if (i.equals(letter)) {
                validado = true;
                break;
            }else {
                if (i.length() > 2) {
                    String temptext = i;
                    temptext = i.substring(0, 0) + i.substring(0 + 1);
                    temptext = temptext.substring(0, temptext.length() - 1);
                    if (temptext.equals(letter)) {
                        validado = true;
                        break;
                    }
                }
            }
        }
    }

    public Boolean isRank() {
        if (this.tipo.equals("rango")) { return true; }
        else if (this.tipo.equals("comas")) { return false; }
        return null;
    }
}
