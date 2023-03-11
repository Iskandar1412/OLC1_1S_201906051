/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
/**
 *
 * @author Pacos
 */
import Project.Automata.Estados;
import Project.Archivos.Crear;
import Project.Automata.Conjunto;
import Project.Automata.Struct;
import Project.Automata.Cadenas;
import Project.Automata.Automata;
import Project.Automata.AFN;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Stack;
import java_cup.reduce_action;

public class ER {
    Automata automata = null;
    String TransicionesDot = "";
    String SiguinetesDot = "";
    String DOT = "digraph structs {\nnode [shape=Mrecord];\n";
    Estados Estado_Inicial;
    ER_Nodo primero;
    ER_Nodo ultimo;
    public String name;
    Struct siguientes = new Struct();
    public Integer hojas = 0;
    List<String> ErTemp = new ArrayList<>();
    List<String> Temp;
    List<String> Temp2;

    public ER() {
        this.primero = null;
        this.ultimo = null;
    }

    public void GenerarHermano() { postorden(this.ultimo.hijo1); }
	
    private void postorden(ER_Nodo n) {
        if (n != null) {
            postorden(n.hijo1);
            postorden(n.hijo2);
            if(n.info.equals("+")) { GestionConversor(n); }
            else if (n.info.equals("?")) {
            	ErTemp.add("ε");
            	ErTemp.add("|");
            }else { ErTemp.add(n.info); }
        }
    }
    
    public void GestionConversor(ER_Nodo n) {
    	Temp = new ArrayList<>();
    	Temp2 = new ArrayList<>();
    	postordenSimbolos(n.hijo1);
    	Temp.add("*");
    	Temp.add(".");
    	for (String string : Temp) { ErTemp.add(string); }
    	//System.out.println(Temp);
    }
    
    private void postordenSimbolos(ER_Nodo n) {
        if (n != null) {
            postordenSimbolos(n.hijo1);
            postordenSimbolos(n.hijo2);
            Temp.add(n.info);
        }
    }
    

    private void preordenOriginal(ER_Nodo n) {
        if (n != null) {
            ErTemp.add(n.info);
            preordenOriginal(n.hijo1);
            preordenOriginal(n.hijo2);
        }
    }

    private void preorden(ER_Nodo n) {
        if (n != null) {
            if (n.info.equals("+")) {
                Temp = new ArrayList<>();
                Temp2 = new ArrayList<>();
                gestionmas(n.hijo1);
                for (String string : Temp) { Temp2.add(string); }
                Temp.add("*");
                for (String string : Temp2) { Temp.add(string); }
                List<String> TempFinal = new ArrayList<>();
                TempFinal.add(".");
                for (String string : Temp) { TempFinal.add(string); }
                System.out.println(Temp);
                for (String string : TempFinal) { ErTemp.add(string); }
            } else if (n.info.equals("?")) {
                Temp = new ArrayList<>();
                Temp.add("|");
                gestionmas(n.hijo1);
                Temp.add("epsilon");
                for (String string : Temp) { ErTemp.add(string); }
            } else {
                ErTemp.add(n.info);
                preorden(n.hijo1);
                preorden(n.hijo2);
            }
        }
    }

    private void gestionmas(ER_Nodo n) {
        if (n != null) {
            Temp.add(n.info);
            gestionmas(n.hijo1);
            gestionmas(n.hijo2);
        }
    }

    public void GenraraAFND() {
        AFN afn = new AFN(ErTemp);
        afn.construir();
        automata = afn.getAfn();
        //System.out.println(automata);
        String tipo = this.name;
        String dot= "";
        Crear crear = new Crear(tipo + ".dot",tipo,automata);
        crear.dot_AFN(this.name);
        dot = crear.getSalida();
        Draw_GraphizAFND(tipo,dot);
    }
	
    public void Draw_GraphizAFND(String name,String Dot) {
        try {
            if (isNone()) {
                String graph = "digraph L {\r\nnode[shape=note fillcolor=\"#A181FF\" style =filled]\r\nsubgraph cluster_p{\r\nbgcolor = \"#FF7878\"\r\nNodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n\r\n}}";
                Create_File("src\\Reportes\\AFND_201906051\\" + name + ".dot", graph);
            } else { Create_File("src\\Reportes\\AFND_201906051\\" + name + ".dot", Dot); }
            ProcessBuilder pb,pq;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", "src\\Reportes\\AFND_201906051\\" + name + ".png", "src\\Reportes\\AFND_201906051\\" + name + ".dot");
            pb.redirectErrorStream(true);
            pb.start();
            pq = new ProcessBuilder("dot", "-Tsvg", "-o", "src\\Reportes\\AFND_201906051\\" + name + ".svg", "src\\Reportes\\AFND_201906051\\" + name + ".dot");
            pq.redirectErrorStream(true);
            pq.start();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void insert(String info, String tipo, Boolean hoja) {
        ER_Nodo new_node = new ER_Nodo(info, tipo, hoja);
        if (comprobarCaderna(new_node) == false) {
            if (isNone()) {
                this.primero = new_node;
                this.ultimo = this.primero;
                if (hoja) {
                    SetHojas();
                    new_node.noHoja = this.hojas;
                    new_node.Anulable = false;
                }
            } else {
                this.ultimo = new_node;
                ER_Nodo actual = this.primero;
                ER_Nodo anterior = null;
                while (actual.next != null) {
                    anterior = actual;
                    actual = actual.next;
                    actual.previous = anterior;
                }
                if (hoja) {
                    SetHojas();
                    new_node.noHoja = this.hojas;
                    new_node.Anulable = false;
                }
                actual.next = new_node;
                actual.next.previous = actual;
            }
        } else { QuitarCadenas(new_node); }
    }

    public void insertNormal(String info, String tipo, Boolean hoja) {
        ER_Nodo new_node = new ER_Nodo(info, tipo, hoja);
        if (isNone()) {
            this.primero = new_node;
            this.ultimo = this.primero;
            if (hoja) {
                SetHojas();
                new_node.noHoja = this.hojas;
                new_node.Anulable = false;
            }
        } else {
            this.ultimo = new_node;
            ER_Nodo actual = this.primero;
            ER_Nodo anterior = null;
            while (actual.next != null) {
                anterior = actual;
                actual = actual.next;
                actual.previous = anterior;
            }
            if (hoja) {
                SetHojas();
                new_node.noHoja = this.hojas;
                new_node.Anulable = false;
            }
            actual.next = new_node;
            actual.next.previous = actual;
        }
    }

    public void insertHead(String info, String tipo, Boolean hoja) {
        ER_Nodo new_node = new ER_Nodo(info, tipo, hoja);
        if (hoja) {
            SetHojas();
            new_node.noHoja = this.hojas;
            new_node.Anulable = false;
        }
        new_node.next = this.primero;
        this.primero = new_node;
    }

    public Boolean comprobarCaderna(ER_Nodo actual) {
        boolean velidado = false;
        if (actual.tipo.equals("s_frase")) {
            String temp = actual.info.replace("\"", "");
            if (temp.length() > 1) { velidado = true; }
        }
        return velidado;
    }

    public void showList() {
        //System.out.println("======Show list ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.hoja) { /*System.out.println(actual.info + " - " + actual.tipo + " hoja " + actual.noHoja);*/ }
                else { /*System.out.println(actual.info + " - " + actual.tipo);*/ }
                actual = actual.next;
            }
        }
    }

    public void QuitarCadenas(ER_Nodo actual) {
        //System.out.println(temp);
        String temp = actual.info.replace("\"", "");
        Integer contador = 0;
        Boolean primeraVez = true;
        ER SimpleTemp = new ER();
        for (int i = temp.length() - 1; i >= 0; i--) {
            String letter = String.valueOf(temp.charAt(i));
            //System.out.println(letter);
            if (letter.equals(" ")) { this.insertNormal("\"" + letter + "\"", "espacio", true); }
            else { this.insertNormal("\"" + letter + "\"", "s_frase", true); }
            if (primeraVez == false) { this.insertNormal(".", "OP", false); }
            if (primeraVez) { contador++; }
            if (primeraVez) {
                if (contador == 2) {
                    this.insertNormal(".", "OP", false);
                    primeraVez = false;
                    contador = null;
                }
            }
        }
        SimpleTemp.showList();
    }

    public void showListInverse() {
        //System.out.println("======Show list Inverse ======");
        if (isNoneLast() == false) {
            ER_Nodo actual = this.ultimo;
            while (actual != null) {
                if (actual.hoja) { /*System.out.println(actual.info + " - " + actual.tipo + " hoja " + actual.noHoja);*/ }
                else { /*System.out.println(actual.info + " - " + actual.tipo);*/ }
                actual = actual.previous;
            }
        }
    }

    public void GestionArbol() {
        this.insertHead("$", "Finalizacion", true);
        this.insert(".", "OP", false);
        ER_Nodo aux1 = null;
        ER_Nodo Op = null;
        Integer hijos = 0;
        Integer idActual = 1;
        Integer AuxContado = 0;
        Boolean validdacionOp = true;
        Boolean validdacionHijo2 = true;
        Op = this.primero;
        while (true) {
            if (Op.next == null) { break; }
            while (validdacionOp) {
                if (Op.tipo.equals("OP") && Op.IDPadre == 0) {
                    hijos = Operator(Op.info);
                    validdacionOp = false;
                    Op.IDPadre = idActual;
                    continue;
                }
                Op = Op.next;
            }
            aux1 = Op.previous;
            AuxContado = 1;
            while (validdacionHijo2) {
                if (AuxContado <= hijos) {
                    if (aux1.IDHijo == 0) {
                        aux1.IDHijo = idActual;
                        if (AuxContado == 1) { Op.hijo1 = aux1; }
                        else if (AuxContado == 2) {
                            Op.hijo2 = aux1;
                            Op.hijo1 = Op.hijo1;
                        }
                        AuxContado++;
                    }
                    aux1 = aux1.previous;
                } else {
                    validdacionHijo2 = false;
                    continue;
                }
            }
            validdacionOp = true;
            validdacionHijo2 = true;
            idActual += 1;
        }
        AsignarAnulables();
    }

    public void AsignarAnulables() {
        //System.out.println("====== Asignando Anulables ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.tipo.equals("OP")) {
                    if (actual.info.equals("|")) {
                        if (actual.hijo1.Anulable || actual.hijo2.Anulable) { actual.Anulable = true; }
                        else { actual.Anulable = false; }
                    }
                    if (actual.info.equals(".")) {
                        if (actual.hijo1.Anulable && actual.hijo2.Anulable) { actual.Anulable = true; }
                        else { actual.Anulable = false; }
                    }
                    if (actual.info.equals("*") || actual.info.equals("?")) { actual.Anulable = true; }
                    if (actual.info.equals("+")) {
                        if (actual.hijo1.Anulable) { actual.Anulable = true; }
                        else { actual.Anulable = false; }
                    }
                }
                actual = actual.next;
            }
        }
        RedefiniendoHojas();
        Primero_Ultimos_Hojas();
        InsertarPrimeros();
    }

    public void RedefiniendoHojas() {
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.hoja) {
                    //System.out.println(actual.info);
                    actual.noHoja = this.hojas;
                    this.hojas--;
                }
                actual = actual.next;
            }
        }
    }

    public void Primero_Ultimos_Hojas() {
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.hoja) {
                    this.siguientes.insert(actual.info, actual.tipo, actual.noHoja);
                    actual.primeros.add(actual.noHoja);
                    actual.ultimos.add(actual.noHoja);
                }
                actual = actual.next;
            }
        }
    }

    public void InsertarPrimeros() {
        //System.out.println("====== Asignando Primeros ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.tipo.equals("OP")) {
                    if (actual.info.equals("|")) {
                        for (Integer i : actual.hijo1.primeros) { actual.primeros.add(i); }
                        for (Integer i : actual.hijo2.primeros) { actual.primeros.add(i); }
                    }
                    if (actual.info.equals(".")) {
                        if (actual.hijo1.Anulable) {
                            for (Integer i : actual.hijo1.primeros) { actual.primeros.add(i); }
                            for (Integer i : actual.hijo2.primeros) { actual.primeros.add(i); }
                        } else {
                            for (Integer i : actual.hijo1.primeros) { actual.primeros.add(i); }
                        }
                    }
                    if (actual.info.equals("*") || actual.info.equals("?") || actual.info.equals("+")) {
                        for (Integer i : actual.hijo1.primeros) { actual.primeros.add(i); }
                    }
                    QuitarDupicados(actual.primeros);
                }
                actual = actual.next;
            }
        }
        InsertarUltimos();
    }

    public void InsertarUltimos() {
        //System.out.println("====== Asignando Ultimos ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.tipo.equals("OP")) {
                    if (actual.info.equals("|")) {
                        for (Integer i : actual.hijo1.ultimos) { actual.ultimos.add(i); }
                        for (Integer i : actual.hijo2.ultimos) { actual.ultimos.add(i); }
                    }
                    if (actual.info.equals(".")) {
                        if (actual.hijo2.Anulable) {
                            for (Integer i : actual.hijo1.ultimos) { actual.ultimos.add(i); }
                            for (Integer i : actual.hijo2.ultimos) { actual.ultimos.add(i); }
                        } else {
                            for (Integer i : actual.hijo2.ultimos) { actual.ultimos.add(i); }
                        }
                    }
                    if (actual.info.equals("*") || actual.info.equals("?") || actual.info.equals("+")) {
                        for (Integer i : actual.hijo1.ultimos) { actual.ultimos.add(i); }
                    }
                    QuitarDupicados(actual.primeros);
                }
                actual = actual.next;
            }
        }
        InsertarSiguientes();
    }

    public void InsertarSiguientes() {
        //System.out.println("====== Asignando Siguientes ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.tipo.equals("OP")) {
                    if (actual.info.equals(".")) {
                        for (Integer primeroHijo2 : actual.hijo2.primeros) {
                            for (Integer UltimosHijo1 : actual.hijo1.ultimos) { this.siguientes.insertNext(UltimosHijo1, primeroHijo2); }
                        }
                    }
                    if (actual.info.equals("*") || actual.info.equals("+")) {
                        for (Integer ultimoH : actual.primeros) {
                            for (Integer primeroH : actual.ultimos) { this.siguientes.insertNext(primeroH, ultimoH); }
                        }
                    }
                }
                actual = actual.next;
            }
        }
        TablaTransiciones();
    }

    public void TablaTransiciones() {
        //System.out.println("====== Tabla de transiciones ======");
        DOT = "digraph structs {\n\nnode[shape=Mrecord color=gray fontcolor=\"#f78a05\"];\nlabel =\"" + this.name + "\"\nedge[color=\"#d19928\"];\n";
        Estado_Inicial = new Estados(0, false, this.ultimo.primeros, this.name);
        Estado_Inicial.trasitionTable(this.siguientes);
        if (isNoneLast() == false) {
            ER_Nodo actual = this.ultimo;
            GenerarArbol(actual);
        }
        if (this.siguientes.isNone() == false) { SiguinetesDot = this.siguientes.dotStruct(this.name); }
        if (Estado_Inicial.listado.isNone() == false) { TransicionesDot = Estado_Inicial.Generar_transiciones(); }
        //System.out.println(TransicionesDot);
        DOT += "\n}";
        Draw_GraphizSiguientes(this.name);
        Draw_GraphizArbol(this.name);
    }

    public void GenerarArbol(ER_Nodo actual) {
        DOT += "struct" + actual.hashCode() + "[label=\"{{" + actual.primeros + "|<here>";
        Tipo_A data = new Tipo_A(actual.info, actual.tipo);
        DOT += validacionTipo(data);
        DOT += "|" + actual.ultimos + "}|";
        if (actual.Anulable) { DOT += "A}\"];\n"; }
        else { DOT += "N.A}\"];\n"; }
        if (actual.hoja) { }
        else {
            if (actual.hijo1 != null) {
                DOT += "    struct" + actual.hashCode() + "->struct" + actual.hijo1.hashCode() + "\n";
                GenerarArbol(actual.hijo1);
            }
            if (actual.hijo2 != null) {
                DOT += "    struct" + actual.hashCode() + "->struct" + actual.hijo2.hashCode() + "\n";
                GenerarArbol(actual.hijo2);
            }
        }
    }

    public void Draw_GraphizSiguientes(String name) {
        try {
            if (isNone()) {
                String graph = "digraph L {\r\nnode[shape=note fillcolor=\"#A181FF\" style =filled]\r\nsubgraph cluster_p{\r\nbgcolor = \"#FF7878\"\r\n Nodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n\r\n}}";
                Create_File("src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".dot", graph);
            } else { Create_File("src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".dot", SiguinetesDot); }
            ProcessBuilder pb, pq;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", "src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".png", "src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".dot");
            pb.redirectErrorStream(true);
            pb.start();
            pq = new ProcessBuilder("dot", "-Tsvg", "-o", "src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".svg", "src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".dot");
            pq.redirectErrorStream(true);
            pq.start();
        }catch (Exception e) { e.printStackTrace(); }
    }

    public void openimgSiguientes(String name) {
        try {
            String url = "src\\Reportes\\SIGUIENTES_201906051\\S_" + name + ".png";
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception e) { e.printStackTrace(); }
    }
        
    private void Create_File(String route, String contents) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(route);
            pw = new PrintWriter(fw);
            pw.write(contents);
            pw.close();
            fw.close();
        }catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
        finally {
            if (pw != null){ pw.close(); }
        }
    }

    public void Draw_GraphizArbol(String name) {
        try {
            if (isNone()) {
                String graph = "digraph L {\r\nnode[shape=note fillcolor=\"#A181FF\" style =filled]\r\nsubgraph cluster_p{\r\nbgcolor = \"#FF7878\"\r\nNodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n\r\n}}";
                Create_File("src\\Reportes\\ARBOLES_201906051\\A_" + name + ".dot", graph);
            } else { Create_File("src\\Reportes\\ARBOLES_201906051\\A_" + name + ".dot", DOT); }
            ProcessBuilder pb,pq;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", "src\\Reportes\\ARBOLES_201906051\\A_" + name + ".png", "src\\Reportes\\ARBOLES_201906051\\A_" + name + ".dot");
            pb.redirectErrorStream(true);
            pb.start();
            pq = new ProcessBuilder("dot", "-Tsvg", "-o", "src\\Reportes\\ARBOLES_201906051\\A_" + name + ".svg", "src\\Reportes\\ARBOLES_201906051\\A_" + name + ".dot");
            pq.redirectErrorStream(true);
            pq.start();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void openimgArbol(String name) {
        try {
            String url = "src\\Reportes\\ARBOLES_201906051\\A_" + name + ".png";
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        }catch (Exception e) { e.printStackTrace(); }
    }

    public String validacionTipo(Tipo_A text) {
        String DOT = "";
        if (text.tipo.equals("s_frase") || text.tipo.equals("espacio")) {
            for (int letra = 0; letra < text.valor.length(); letra++) {
                if (letra == 0) { DOT += ("\\\""); }
                else if (letra + 1 == text.valor.length()) { DOT += ("\\\""); }
                else { DOT += (text.valor.charAt(letra)); }
            }
        }else if (text.tipo.equals("doble_comilla")) { DOT += ("\\\\\\\""); }
        else if (text.tipo.equals("s_comilla")) { DOT += ("\\\\'"); }
        else if (text.tipo.equals("fin_linea")) { DOT += ("\\\\n"); }
        else if (text.valor.equals("|")) { DOT += ("\\|"); }
        else { DOT += (text.valor); }
        return DOT;
    }

    public void GenrarGrafo() { verGrafo(); }

    public void verGrafo() { Estado_Inicial.verGrafo(); }

    public void showListUltimos() {
        //System.out.println("======Show list ultimos ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                //System.out.println("====== " + actual.info + " ====== ");
                actual.ultimos.forEach(System.out::println);
                actual = actual.next;
            }
        }
    }

    public void showListPrimeros() {
        //System.out.println("======Show list primeros ======");
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            while (actual != null) {
                //System.out.println("====== " + actual.info + " ====== ");
                actual.primeros.forEach(System.out::println);
                actual = actual.next;
            }
        }
    }

    public void QuitarDupicados(List<Integer> lista) { lista = lista.stream().distinct().collect(Collectors.toList()); }

    public void verNoAnulables() {
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            //System.out.println("=======No anulables ========");
            while (actual != null) {
                if (actual.Anulable != null) {
                    if (actual.Anulable == false) { /*System.out.println(actual.info + " - " + actual.tipo);*/ }
                }
                actual = actual.next;
            }
        }
    }

    public void verAnulables() {
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
            //System.out.println("======= anulables ========");
            while (actual != null) {
                if (actual.Anulable != null) {
                    if (actual.Anulable) { /*System.out.println(actual.info + " - " + actual.tipo);*/ }
                }
                actual = actual.next;
            }
        }
    }

    public int Operator(String text) {
        if (text.equals("|") || text.equals(".")) { return 2; }
        else if (text.equals("*") || text.equals("+") || text.equals("?")) { return 1; }
        System.out.println("Error");
        return -1;
    }

    public Boolean isNone() { return this.primero == null; }

    public Boolean isNoneLast() { return this.ultimo == null; }

    public void SetHojas() { this.hojas++; }

    public void Search(Object data) {
        if (isNone() == false) {
            ER_Nodo actual = this.primero;
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
    public void ValidarCadena(String cadena, LinkedList<Conjunto> conjList, Cadenas i) { Estado_Inicial.validadarCadena(cadena, conjList, i); }
}