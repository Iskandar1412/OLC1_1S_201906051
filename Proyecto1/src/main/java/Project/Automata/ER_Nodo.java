/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import Project.Automata.Siguientes;
import Project.Automata.Tipo_A;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class ER_Nodo {
    ER_Nodo next;
    ER_Nodo previous;
    ER_Nodo hijo1;
    ER_Nodo hijo2;
    Integer noHoja;
    List<Integer> primeros = new ArrayList<>();
    List<Integer> ultimos = new ArrayList<>();
    Boolean Anulable;
    Boolean hoja;
    String info;
    String tipo;
    Integer IDPadre = 0;
    Integer IDHijo = 0;

    public ER_Nodo(String info, String tipo, Boolean hoja) {
        this.next = null;
        this.previous = null;
        this.info = info;
        this.tipo = tipo;
        this.hoja = hoja;
        this.hijo1 = null;
        this.hijo2 = null;
        this.IDHijo = 0;
        this.IDPadre = 0;
    }
}