/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class Transicion {
    Transicion_Nodo primero;
    public Transicion() { this.primero = null; }

    public void insert(String info,String tipo,Integer Hoja) {
        Transicion_Nodo new_node = new Transicion_Nodo(info,tipo,Hoja);
        if (isNone()) { this.primero = new_node; }
        else {
            Transicion_Nodo actual = this.primero;
            while (actual.next != null) { actual = actual.next; }
            actual.next = new_node;
        }
    }

    public void showList() {
        if (isNone() == false) {
            Transicion_Nodo actual = this.primero;
            while (actual != null) {
                //System.out.println(actual.info + " Hoja: " + actual.Hoja + " tipo: " + actual.tipo + " sig: " + actual.siguientes);
                actual = actual.next;
            }
        }
    }

    public void Search(Object data) {
        if (isNone() == false) {
            Transicion_Nodo actual = this.primero;
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
    
    public void InsertarSIguiente(Integer HojaBusqueda,Integer siguiente) {
        if (isNone() == false) {
            Transicion_Nodo actual = this.primero;
            while (actual != null && actual.Hoja != HojaBusqueda) {
                actual = actual.next;
                if (actual == null) {
                    System.out.println("No se encontro el dato: " + HojaBusqueda);
                    break;
                }
            }
            if (actual != null && actual.Hoja == HojaBusqueda) {
                actual.siguientes.add(siguiente);
                System.out.println("Dato encontrado: " + HojaBusqueda);
            }
        }
    }
    
    public Boolean isNone() { return this.primero == null; }
}