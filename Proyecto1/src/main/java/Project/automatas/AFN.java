/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import com.mycompany.proyecto1.Grafica;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AFN<T> {
    private Automata afn;
    private List<String> Elementos;

    public AFN(List<String> Elementos) {
        this.Elementos = Elementos;
    }
    
    public void construir(){
        try{
            Stack<Automata> pila = new Stack<Automata>();
            for(String c : Elementos){
                switch(c){
                    case "*":
                        Automata kleene = cerradura((Automata)pila.pop()); 
                        pila.push(kleene);
                        this.afn = kleene;
                        break;
                    case ".":
                        Automata op1 = (Automata)pila.pop();
                        Automata op2 = (Automata)pila.pop();
                        Automata concatenar = concatenar(op1,op2);
                        pila.push(concatenar);
                        this.afn = concatenar;
                        break;
                    case "|":
                        Automata op1Or = (Automata)pila.pop();
                        Automata op2Or = (Automata)pila.pop();
                        Automata OR = union(op1Or,op2Or);
                        pila.push(OR);
                        this.afn = OR;
                        break;
                    default:
                        String temptext = (String) c;
                        if (c.equals("\"\\\"\"")) { temptext = ("\\\\\\\""); } 
                        else if (c.equals("\"\\'\"")) { temptext = ("\\\\'"); }
                        else if (c.equals("\"\\n\"")) { temptext = ("\\\\n"); }
                        else if (c.equals("\"\\n\"")) { temptext = ("\\\\n"); }
                        else if (String.valueOf(c.charAt(0)).equals("\"") && String.valueOf(c.charAt(c.length()-1)).equals("\"") ) {
                            temptext = temptext.substring(0,0) + temptext.substring(0+1);
                            temptext = temptext.substring(0,temptext.length()-1);
                        }
                    Automata simple = SimpleAutomata(temptext);
                    pila.push(simple);
                    this.afn = simple;
                    break;
                }
            }
            this.afn.crearSimbolos(Elementos);
            this.afn.setTipoAutomata(0);
        }catch(Exception e){ System.out.println("ERROR: No se pudo construir el automata"); }
    }
    
    public String validar_Tipo(Tipo_A text) {
        String dot = "";
        if (text.tipo.equals("s_frase") || text.tipo.equals("espacio")) {
                for (int letra = 0; letra < text.valor.length(); letra++) {
                        if (letra == 0) { dot += ("\\\""); }
                        else if (letra + 1 == text.valor.length()) { dot += ("\\\""); }
                        else { dot += (text.valor.charAt(letra)); }
                }
        }else if (text.tipo.equals("doble_comilla")) { dot += ("\\\\\\\""); }
        else if (text.tipo.equals("s_comilla")) { dot += ("\\\\'"); }
        else if (text.tipo.equals("fin_linea")) { dot += ("\\\\n"); }
        else if (text.valor.equals("|")) { dot += ("\\|"); }
        else { dot += (text.valor); }
        return dot;
    }
	
	
    public Automata concatenar(Automata op1, Automata op2){
        Automata a = new Automata();
        int i = 0;
        for ( i = 0; i < op2.getEstados().size(); i++){
            Estado temp = (Estado) op2.getEstados().get(i);
            temp.setId(i);
            if(i == 0) a.setInicial(temp);
            if(i == op2.getEstados().size() - 1) {
                for(int k = 0; k < op2.getAceptacion().size(); k++){ temp.setTransiciones(new Transition((Estado) op2.getAceptacion().get(k),op1.getInicial(),Grafica.EPSILON)); }
            }
            a.addEstados(temp);
        }
        for (int j = 0; j < op1.getEstados().size(); j++){
            Estado temp = (Estado) op1.getEstados().get(j);
            temp.setId(i);
            if(op1.getEstados().size() -1  == j) a.addEstadoAceptacion(temp);
            a.addEstados(temp);
            i++;
        }
        HashSet simbolos = new HashSet();
        simbolos.addAll(op1.getSimbolos());
        simbolos.addAll(op2.getSimbolos());
        a.setSimbolos(simbolos);
        a.setLenguaje(op1.getLenguaje() + " " + op2.getLenguaje());
        return a;
    }


    public Automata SimpleAutomata(String simbolo){
        Automata a = new Automata();
        Estado inicio = new Estado(0);
        Estado fin = new Estado(1);
        Transition tran = new Transition(inicio,fin,simbolo);
        inicio.setTransiciones(tran);
        a.addEstados(inicio);
        a.addEstados(fin);
        a.setInicial(inicio);
        a.addEstadoAceptacion(fin);
        a.setLenguaje(simbolo + "");        
        return a;
    }

    public Automata cerradura(Automata automata){
        Automata a = new Automata();
        Estado inicio = new Estado(0);
        a.addEstados(inicio);
        a.setInicial(inicio);
        for(int i = 0; i < automata.getEstados().size(); i++){
            Estado temp = (Estado)automata.getEstados().get(i);
            temp.setId(i + 1);
            a.addEstados(temp);
        }
        Estado fin = new Estado(automata.getEstados().size() + 1);
        a.addEstados(fin);
        a.addEstadoAceptacion(fin);
        Estado oldInicio = automata.getInicial();
        ArrayList<Estado> oldFin = automata.getAceptacion();
        inicio.getTransiciones().add(new Transition(inicio,oldInicio,Grafica.EPSILON));
        inicio.getTransiciones().add(new Transition(inicio,fin,Grafica.EPSILON));
        for(int i = 0; i < oldFin.size(); i++){
            oldFin.get(i).getTransiciones().add(new Transition(oldFin.get(i),oldInicio,Grafica.EPSILON));
            oldFin.get(i).getTransiciones().add(new Transition(oldFin.get(i),fin,Grafica.EPSILON));
        }
        a.setSimbolos(automata.getSimbolos());
        a.setLenguaje(automata.getLenguaje());
        return a;
    }

   
    public Automata union(Automata AFN1, Automata AFN2){
        Automata afnunion = new Automata();
        Estado inicioNuevo = new Estado(0);
        inicioNuevo.setTransiciones(new Transition(inicioNuevo,AFN2.getInicial(),Grafica.EPSILON));
        afnunion.addEstados(inicioNuevo);
        afnunion.setInicial(inicioNuevo);
        int i=0;
        for (i=0; i < AFN1.getEstados().size(); i++) {
            Estado tmp = (Estado) AFN1.getEstados().get(i);
            tmp.setId(i + 1);
            afnunion.addEstados(tmp);
        }
        for (int j=0; j < AFN2.getEstados().size(); j++) {
            Estado tmp = (Estado) AFN2.getEstados().get(j);   
            tmp.setId(i + 1);
            afnunion.addEstados(tmp);
            i++;
        }
        Estado nuevoFin = new Estado(AFN1.getEstados().size() +AFN2.getEstados().size()+ 1);
        afnunion.addEstados(nuevoFin);
        afnunion.addEstadoAceptacion(nuevoFin);
        Estado anteriorInicio = AFN1.getInicial();
        ArrayList<Estado> anteriorFin    = AFN1.getAceptacion();
        ArrayList<Estado> anteriorFin2    = AFN2.getAceptacion();
        inicioNuevo.getTransiciones().add(new Transition(inicioNuevo, anteriorInicio, Grafica.EPSILON));
        for (int k =0; k<anteriorFin.size();k++){ anteriorFin.get(k).getTransiciones().add(new Transition(anteriorFin.get(k), nuevoFin, Grafica.EPSILON)); }
        for (int k =0; k<anteriorFin.size();k++){ anteriorFin2.get(k).getTransiciones().add(new Transition(anteriorFin2.get(k),nuevoFin,Grafica.EPSILON)); }
        HashSet alfabeto = new HashSet();
        alfabeto.addAll(AFN1.getSimbolos());
        alfabeto.addAll(AFN2.getSimbolos());
        afnunion.setSimbolos(alfabeto);
        afnunion.setLenguaje(AFN1.getLenguaje()+" " + AFN2.getLenguaje());
        return afnunion;
    }

    public Automata getAfn() { return afn; }
}
