/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Archivos;
import Project.Automata.Automata;
import Project.Automata.Automata;
import Project.Automata.Transition;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Crear {
    String direccion;
    String tipo;
    Automata automata;
    String salida;
    
    public Crear(String direccion, String tipo, Automata automata) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.automata = automata;
    }
    
    public void dot_AFN(String name){
        String path = "src\\Reportes\\AFND_201906051\\";
        String texto = "digraph finite_state_machine{\n\trankdir=LR;\n";
        texto += "\tlabel=\"" + name +"\";\n\tnode[fontname=\"Helvetica,Arial,sans-serif\"];\n\tedge[fontname=\"Helvetica,Arial,sans-serif\" color=\"#9c9332\"];\n";
        texto +="\tnode[shape = doublecircle, color = \"#f78a05\" fillcolor=\"#f78a05\" style =filled fontcolor=white];";
        for(int i = 0; i < automata.getAceptacion().size(); i++){
            texto += " " + automata.getAceptacion().get(i);
        }
        texto += ";\n\tnode [shape = circle, color = \"#d19928\" fillcolor=\"#d19928\" style =filled fontcolor=white];\n";
        texto += "\tflechainicio[style=invis fontsize=\"0\"];\n\n\tflechainicio->" + automata.getInicial() + "[label=\"inicio\" fontcolor=\"#f78a05\"];\n\n";
        for(int i = 0; i < automata.getEstados().size(); i++){
            ArrayList<Transition> temp = automata.getEstados().get(i).getTransiciones();
            for (int j = 0; j < temp.size(); j++){
                texto += "\t" + temp.get(j).DOT_String() + "\n";
            }
        }
        texto += "\n}";
        salida = texto;
    }

    public String getSalida() { return salida;}
    
}
