/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import Project.Automata.Conjunto;
import Project.Automata.Cadenas;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class Estados {
    static Struct Sigeuintes;
    static Cadenas cadenai;
    static LinkedList<Conjunto> ListaConjuntos;
    static Boolean CadenaValida;
    static Siguientes_Nodo ActualValidacion;
    static String dot;
    static Integer estadosGestion = 0;
    static Siguientes_Nodo encabezadoEstado;
    static Siguientes_Nodo anteriorEstado = null;
    String name;
    Integer Estado;
    Boolean Aceptacion;
    List<Integer> Valores = new ArrayList<>();
    Siguientes listado;
    List<String> CaracteresAceptados = new ArrayList<>();

    public Estados(Integer Estado, Boolean Aceptacion, List<Integer> Siguientes,String name) {
        this.Estado = Estado;
        this.Aceptacion = Aceptacion;
        this.Valores = Siguientes;
        this.listado = new Siguientes();
        this.name = name;
    }

    public void show() {
        String data = "\nEstado:" + this.Estado + "\nAceptacion:" + this.Aceptacion + "\nValores:" + this.Valores + "\nTransiciones:";
        //System.out.println(data);
        Estados.Sigeuintes.showList();
    }

    public void trasitionTable(Struct siguientes) {
        Estados.Sigeuintes = siguientes;
        dot = "";
        String Tipo, Valor;
        Tipo_A valor_tipo;
        List<Integer> Primeros;
        for (Integer i : this.Valores) {
            Tipo = siguientes.buscarTipo(i);
            if (Tipo.equals("Finalizacion")) { this.Aceptacion = true; }
            else {
                Valor = siguientes.buscarInfo(i);
                valor_tipo = new Tipo_A(Valor, Tipo);
                Primeros = siguientes.buscarPrimeros(i);
                listado.insert(valor_tipo, Primeros);
            }   
        }
        encabezadoEstado = listado.primero;
        listado.AgregarDatos_Aceptados();
        //System.out.println("==============Mostrando Arbol==============");
        Estados.Sigeuintes.showList();
        estadosGestion = 0;
        //System.out.println(this.name);
        listado.verArbolMain(this.Aceptacion, this.name);
        listado.AgregarAceptacionAvReporte();
        listado.verReporte(this.name);
        listado.Draw_Graphiz(this.name);
    }

    public String Generar_transiciones() {
        String dot = "";
        listado.DotTraniscisiones(this.name, this.Aceptacion);
        listado.Draw_GraphizTransiciones(this.name);
        return dot;
    }

    public void validadarCadena(String cadena, LinkedList<Conjunto> conjList, Cadenas cadenai) {
        Estados.cadenai = cadenai;
        ListaConjuntos = conjList;
        CadenaValida = true;
        anteriorEstado = null;
        ActualValidacion = listado.primero;
        if (cadena != "") {
            for (int i = 0; i < cadena.length(); i++) {
                String letter = String.valueOf(cadena.charAt(i));
                String letterSig;
                if (CadenaValida) {
                    if (i + 1 < cadena.length()) { letterSig = String.valueOf(cadena.charAt(i + 1)); }
                    else { letterSig = null; }
                    ValidacionGuia(ActualValidacion, letter, letterSig);
                }else { break; }
            }
            if (CadenaValida) {
                if (ActualValidacion.Aceptacion) {
                    cadenai.validacion = true;
                    if(anteriorEstado.EstadoRepetido) { System.out.println("\tEstado en: " + ActualValidacion.EstadoDestino); }
                    else { System.out.println("\tEstado en: " + ActualValidacion.Estado); }
                }	
            } else {
                if (anteriorEstado == null) { System.out.println("\tFallo en : 0"); }
                else {
                    if(anteriorEstado.EstadoRepetido) { System.out.println("\tFallo en anterior: " + anteriorEstado.EstadoDestino); }
                    else { System.out.println("\tFallo en anterior: " + anteriorEstado.Estado); }
                }
            }
        } else {
            if (Aceptacion) {
                cadenai.validacion = true;
                System.out.println("Cadena Aceptada");
            }else{ System.out.println("Fallo"); }
        }
    }

    public void ValidacionGuia(Siguientes_Nodo actual, String letter, String letterSig) {
        if(anteriorEstado == null) { actual.listado.ValidacionPivote(actual, letter, letterSig,true); }
        else { actual.listado.ValidacionPivote(actual, letter, letterSig,false); }
    }

    public void verGrafo() { listado.openimg(this.name); }
}
