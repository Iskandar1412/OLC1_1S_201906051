/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Pacos
 */
public class Struct {
    Struct_Nodo primero;
    Struct_Nodo ultimo;

    public Struct() {
        this.primero = null;
        this.ultimo = null;
    }
    
    public Boolean isNone() { return this.primero == null; }

    public void insert(String info,String tipo,Integer Hoja) {
        Struct_Nodo new_node = new Struct_Nodo(info,tipo,Hoja);
        if (isNone()) {
            this.primero = new_node;
            this.ultimo = this.primero;
        } else {
            Struct_Nodo actual = this.primero;
            this.ultimo = new_node;
            while (actual.next != null) { actual = actual.next; }
            actual.next = new_node;
            actual.next.previous = actual;
        }
    }

    public void insertNext(Integer HojaBusqueda,Integer siguiente) {
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null && actual.Hoja != HojaBusqueda) {
                actual = actual.next;
                if (actual == null) { break; }
            }
            if (actual != null && actual.Hoja == HojaBusqueda) { actual.siguientes.add(siguiente); }
        }
    }
	
    public String dotStruct(String name) {
        String dot = "digraph structs {\n\n\tnode [shape=Mrecord color=gray fontcolor=\"#d97e16\"];\n";
        dot += "label =\"Siguientes (" + name + ")\";\nstruct1 [label=\" { Valor | Hoja| Siguietes } | \n";
        if (this.ultimo != null) {
            Struct_Nodo actual = this.ultimo;
            while (actual != null) {
                String temp = "";
                temp += "{ ";
                Tipo_A data = new Tipo_A(actual.info,actual.tipo);
                temp += validarTipo(data);
                temp += "| " + actual.Hoja + " | " + actual.siguientes + "}";
                actual = actual.previous;
                if(actual != null) { temp+="|"; }
                temp += "\n";
                dot += temp;
            }
            dot += "\"];\n }";
            return dot;
        }
        return null;
    }

    public List<Tipo_A> TerminalsE() {
        List<Tipo_A> Terminales = new ArrayList<>();
        if (this.ultimo != null) {
            Struct_Nodo actual = this.ultimo;
            while (actual != null) {
                Boolean agregar = true; 
                for (Tipo_A i : Terminales) {
                    if(actual.info.equals(i.valor)) { agregar = false; }
                }
                if(agregar) {
                    Tipo_A data= new Tipo_A(actual.info,actual.tipo);
                    if(data.tipo.equals("Finalizacion")){ }
                    else { Terminales.add(data); }	
                }
                actual= actual.previous;
            }
            return Terminales;
        }
        return null;
    }

    public String validarTipo(Tipo_A text) {
        String dot = "";
        if (text.tipo.equals("s_frase") || text.tipo.equals("espacio")) {
            for (int letra = 0; letra < text.valor.length(); letra++) {
                if (letra == 0) { dot += ("\\\""); }
                else if (letra + 1 == text.valor.length()) { dot += ("\\\""); }
                else { dot += (text.valor.charAt(letra)); }
            }
        }else if (text.tipo.equals("doble_comilla")) { dot += ("\\\\\\\""); }
        else if (text.tipo.equals("s_comilla")) { dot += ("\\\'"); }
        else if (text.tipo.equals("fin_linea")) { dot += ("\\\\n"); }
        else if (text.valor.equals("|")) { dot += ("\\|"); }
        else { dot += (text.valor); }
        return dot;
    }
	
    public void Search(Object data) {
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null && actual.info != data) {
                actual = actual.next;
                if (actual == null) {
                    System.out.println("No se encontro el dato: " + data);
                    break;
                }
            }
            if (actual != null && actual.info == data) { System.out.println("Dato encontrado: " + data); }
        }
    }

    public String buscarInfo(Integer data) {
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null && actual.Hoja != data) {
                actual = actual.next;
                if (actual == null) { break; }
            }
            if (actual != null && actual.Hoja == data) { return actual.info; }
        }
        return null;
    }
	
    public String buscarTipo(Integer data) {
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null && actual.Hoja != data) {
                actual = actual.next;
                if (actual == null) { break; }
            }
            if (actual != null && actual.Hoja == data) { return actual.tipo; }
        }
        return null;
    }
	
    public List<Integer> buscarPrimeros(Integer data) {
        List<Integer> siguientes = new ArrayList<>();
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null && actual.Hoja != data) {
                actual = actual.next;
                if (actual == null) { break; }
            }
            if (actual != null && actual.Hoja == data) {
                for (Integer integer : actual.siguientes) { siguientes.add(integer); }
                return siguientes;
            }
        }
        return null;
    }
    
    public void showList() {
        if (isNone() == false) {
            Struct_Nodo actual = this.primero;
            while (actual != null) {
                //System.out.println(actual.info + " Hoja: " + actual.Hoja + " tipo: " + actual.tipo + " sig: " + actual.siguientes);
                /*for (Integer i : actual.siguientes) { System.out.println(i); }*/
                actual = actual.next;
            }
        }
    }
}