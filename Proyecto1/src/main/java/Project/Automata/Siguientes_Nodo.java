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
public class Siguientes_Nodo {
    Siguientes_Nodo next;
    Siguientes_Nodo previous;
    String CadenaAceptacion = "";
    Boolean repetidoAsiMimso = false;
    Boolean mostrado = false;
    Boolean mostradoGrafo = false;
    Integer Numeracion;
    Boolean Verificado = false;
    Integer Estado;
    Tipo_A data;
    List<Tipo_A> DatosAceptados = new ArrayList<>();
    List<Integer> primeros = new ArrayList<>();
    Siguientes listado;
    Boolean Aceptacion = false;
    Boolean EstadoRepetido = false;
    Integer EstadoDestino = 0;

    public Siguientes_Nodo(Tipo_A data, List<Integer> siguinetes) {
        this.next = null;
        this.previous = null;
        this.data = data;
        this.primeros = siguinetes;
        this.listado = new Siguientes();
    }
}