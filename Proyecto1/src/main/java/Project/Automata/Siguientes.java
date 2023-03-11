/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import Project.Automata.Estados;
import Project.Automata.Conjunto;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Pacos
 */
public class Siguientes {
	Integer ContadorGeneral = 0;
	String TransicionesDot;
	List<Tipo_A> Terminales;
	List<Integer> EstadosAceptacion;
	Integer RegulacionNumeroacion = 0;
	Siguientes_Nodo primero;
        Siguientes_Nodo ultimo;

	public Siguientes() {
		this.primero = null;
		this.primero = null;
	}

    public void insert(Tipo_A valor_tipo, List<Integer> siguinetes) {
        RegulacionNumeroacion++;
        Siguientes_Nodo new_node = new Siguientes_Nodo(valor_tipo, siguinetes);
        new_node.Numeracion = RegulacionNumeroacion;
        new_node.DatosAceptados.add(new_node.data);
        if (isNone()) {
            this.primero = new_node;
            this.ultimo = this.primero;
        } else {
            Siguientes_Nodo actual = this.primero;
            if (BuscarRepetido(new_node)) {
                this.ultimo = new_node;
                while (actual.next != null) { actual = actual.next; }
                actual.next = new_node;
                actual.next.previous = actual;
            }
        }
    }

    public boolean BuscarRepetido(Siguientes_Nodo actualagregar) {
        Siguientes_Nodo actual = this.primero;
        while (actual != null && actual.data.valor != actualagregar.data.valor) {
            if (actual.data.valor.equals(actualagregar.data.valor)) { break; }
            else {
                actual = actual.next;
                if (actual == null) { return true; }
            }
        }
        if (actual != null && actual.data.valor.equals(actualagregar.data.valor)) {
            for (Integer i : actualagregar.primeros) { actual.primeros.add(i); }

            actual.primeros = QuitarDupicados(actual.primeros);
            return false;
        }
        return true;
    }

    public void AgregarDatos_Aceptados() {
        if (isNoneLast() == false) {
            Siguientes_Nodo actualReverse = this.ultimo;
            if (isNone() == false) {
                while (actualReverse != null) {
                    Siguientes_Nodo actual = this.primero;
                    while (actual != null && actual.primeros != actualReverse.primeros) {
                        if (actual.Numeracion > actualReverse.Numeracion) {
                            if (actual.hashCode() != actualReverse.hashCode()) {
                                if (actual.primeros.equals(actualReverse.primeros)) { break; }
                                else {
                                    actual = actual.next;
                                    if (actual == null) { break; }
                                }
                            } else { actual = actual.next; }
                        } else { break; }
                    }
                    if (actual != null && actual.primeros.equals(actualReverse.primeros)) {
                        if (actual.hashCode() != actualReverse.hashCode()) {
                            for (Tipo_A i : actualReverse.DatosAceptados) { actual.DatosAceptados.add(i); }
                            Delete(actualReverse);
                            actual.DatosAceptados = QuitarDupicadosAceptacion(actual.DatosAceptados);
                        }
                    }
                    actualReverse = actualReverse.previous;
                }
            }
        }
        verificadionEstadosRepetidos();
        agregarValorEstado();
    }

    public void verificadionEstadosRepetidos() {
        if (isNone() == false) {
            if (Estados.encabezadoEstado == this.primero) { }
            else {
                Siguientes_Nodo actual = this.primero;
                while (actual != null) {
                    verificacionDesdeEncabezado(actual.primeros, actual);
                    actual = actual.next;
                }
            }
        }
    }

    public void verificacionDesdeEncabezado(List<Integer> primeros, Siguientes_Nodo ActualVerificando) {
        if (isNone() == false) {
            Siguientes_Nodo actual = Estados.encabezadoEstado;
            while (actual != null) {
                if (actual != ActualVerificando && actual.EstadoRepetido == false) {
                    if (primeros.equals(actual.primeros)) {
                        if (actual.Aceptacion) { ActualVerificando.Aceptacion = actual.Aceptacion; }
                        ActualVerificando.EstadoRepetido = true;
                        ActualVerificando.EstadoDestino = actual.Estado;
                    }
                    verificacionDesdeOtraparte(primeros, ActualVerificando, actual);
                }
                actual = actual.next;
            }
        }
    }

    public void verificacionDesdeOtraparte(List<Integer> primeros, Siguientes_Nodo ActualVerificando, Siguientes_Nodo actualAnterior) {
        if (isNone() == false) {
            Siguientes_Nodo actual = actualAnterior.listado.primero;
            while (actual != null) {
                if (actual != ActualVerificando && actual.EstadoRepetido == false) {
                    if (primeros.equals(actual.primeros)) {
                        if (actual.Aceptacion) { ActualVerificando.Aceptacion = actual.Aceptacion; }
                        ActualVerificando.EstadoRepetido = true;
                        ActualVerificando.EstadoDestino = actual.Estado;
                    }
                    verificacionDesdeOtraparte(primeros, ActualVerificando, actual);
                }
                actual = actual.next;
            }
        }
    }

    public void agregarValorEstado() {
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.EstadoRepetido == false) {
                    Estados.estadosGestion++;
                    actual.Estado = Estados.estadosGestion;
                }
                actual = actual.next;
            }
        }
        if (Estados.encabezadoEstado == this.primero) { tabla_transiciones_EstadosNuevos(); }
        else { tabla_transiciones_EstadosNuevos(); }
    }

    public void tabla_transiciones_EstadosNuevos() {
        String Tipo, Valor;
        Tipo_A valor_tipo;
        List<Integer> Primeros;
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.EstadoRepetido == false) {
                    if (actual.Verificado == false) {
                        actual.Verificado = true;
                        for (Integer i : actual.primeros) {
                            Tipo = Estados.Sigeuintes.buscarTipo(i);
                            if (Tipo.equals("Finalizacion")) { actual.Aceptacion = true; }
                            else {
                                Valor = Estados.Sigeuintes.buscarInfo(i);
                                valor_tipo = new Tipo_A(Valor, Tipo);
                                Primeros = Estados.Sigeuintes.buscarPrimeros(i);
                                actual.listado.insert(valor_tipo, Primeros);
                            }
                        }
                        if (Estados.Sigeuintes.buscarTipo(actual.primeros.get(0)) == "Finalizacion") { actual.Aceptacion = true; }
                        actual.listado.AgregarDatos_Aceptados();
                    }
                }
                actual = actual.next;
            }
        }
    }

    public void AgregarAceptacionAvReporte() {
        Integer contador = 1;
        for (Integer integer : EstadosAceptacion) {
            Estados.dot += integer;
            if (contador < EstadosAceptacion.size()) { Estados.dot += ","; }
            else { Estados.dot += ";\n"; }
            contador++;
        }
        Estados.dot += ("\tnode[shape = circle, color = \"#d19928\" fillcolor=\"#d19928\" style =filled fontcolor=white];\n\n");
        verArbolMainReporte();
    }

    public void verArbolMainReporte() {
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            Estados.dot += ("\tflechainicio [style=invis fontsize=\"0\"];\n");
            Estados.dot += ("\tflechainicio -> 0 [label=\"inicio\" fontcolor=\"#f78a05\"];\n");
            while (actual != null) {
                Integer contador = 1;
                Estados.dot += ("\t0->");
                Estados.dot += (actual.Estado);
                Estados.dot += ("[label = \"");
                for (Tipo_A i : actual.DatosAceptados) {
                    validacionTipo(i);
                    if (contador < actual.DatosAceptados.size()) { Estados.dot += (","); }
                    else { Estados.dot += ("\" fontcolor=\"#f78a05\"];\n"); }
                    contador++;
                }
                actual = actual.next;
            }
        }
        ShowArbolReporte(this.primero);
    }

    public void validacionTipo(Tipo_A text) {
        if (text.tipo.equals("s_frase") || text.tipo.equals("espacio")) {
            for (int letra = 0; letra < text.valor.length(); letra++) {
                if (letra == 0) { Estados.dot += ("\\\""); }
                else if (letra + 1 == text.valor.length()) { Estados.dot += ("\\\""); }
                else { Estados.dot += (text.valor.charAt(letra)); }
            }
        }else if (text.tipo.equals("doble_comilla")) { Estados.dot += ("\\\""); }
        else if (text.tipo.equals("s_comilla")) { Estados.dot += ("\\\'"); }
        else if (text.tipo.equals("fin_linea")) { Estados.dot += ("\\\\n"); }
        else { Estados.dot += (text.valor); }
    }

    public void ShowArbolReporte(Siguientes_Nodo cabezera) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                actual.mostradoGrafo = true;
                if(actual.Estado!=null) { ShowAceptacionesReporte(actual.listado.primero, actual.Estado); }
                actual = actual.next;
            }
        }
    }

    public void ShowAceptacionesReporte(Siguientes_Nodo cabezera, Integer estado) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                Integer contador = 1;
                Estados.dot += (estado + "->");
                if (actual.EstadoRepetido == false) { Estados.dot += (actual.Estado); }
                else { Estados.dot += (actual.EstadoDestino); }
                Estados.dot += ("[label = \"");
                for (Tipo_A i : actual.DatosAceptados) {
                    validacionTipo(i);
                    if (contador < actual.DatosAceptados.size()) { Estados.dot += (","); }
                    else { Estados.dot += ("\" fontcolor=\"#f78a05\"];\n"); }
                    contador++;
                }
                //System.out.println("");
                if (actual.EstadoRepetido == false) {
                    if (actual.mostradoGrafo == false) { ShowArbolReporte(actual); }
                }
                actual = actual.next;
            }
        }
    }

    public void verReporte(String name) {
        Estados.dot += "\tlabel= \"" + name+"\"";
        Estados.dot += ("\n}"); 
    }

    // create the dot dile
    private void Create_File(String route, String contents) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(route);
            pw = new PrintWriter(fw);
            pw.write(contents);
            pw.close();
            fw.close();
        } catch (Exception ex) { System.out.println(ex.getMessage()); }
        finally {
            if (pw != null){ pw.close(); }
        }
    }

    // draw the graph
    public void Draw_Graphiz(String name) {
        try {
            if (isNone()) {
                String graph = "digraph L {\r\n" + "node[shape=note style =filled]\r\nsubgraph cluster_p{\r\nbgcolor = \"#FF7878\"\r\nNodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n" + "\r\n" + "}}";
                Create_File("src\\Reportes\\AFD_201906051\\" + name + ".dot", graph);
            } else { Create_File("src\\Reportes\\AFD_201906051\\" + name + ".dot", Estados.dot); }
            ProcessBuilder pb,pq;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", "src\\Reportes\\AFD_201906051\\" + name + ".png", "src\\Reportes\\AFD_201906051\\" + name + ".dot");
            pb.redirectErrorStream(true);
            pb.start();
            pq = new ProcessBuilder("dot", "-Tsvg", "-o", "src\\Reportes\\AFD_201906051\\" + name + ".svg", "src\\Reportes\\AFD_201906051\\" + name + ".dot");
            pq.redirectErrorStream(true);
            pq.start();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void openimg(String name) {
        try {
            String url = "src\\Reportes\\AFD_201906051\\" + name + ".png";
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception e) { e.printStackTrace(); }

    }

    public void verArbolMain(Boolean Aceptacion, String Name) {
        Estados.dot = "";
        Estados.dot += ("digraph finite_state_machine {\n\tnode[fontname=\"Helvetica,Arial,sans-serif\"]\n\tedge[fontname=\"Helvetica,Arial,sans-serif\" color=\"#9c9332\"]\n");
        Estados.dot += ("\trankdir=LR;\n\tnode[shape = doublecircle, color = \"#f78a05\" fillcolor=\"#f78a05\" style =filled fontcolor=white];\n");
        EstadosAceptacion = new ArrayList<>();
        if (Aceptacion) {
            //System.out.println("S0* a:");
            EstadosAceptacion.add(0);
        } else { /*System.out.println("S0 a:");*/ }
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.Aceptacion) { /*System.out.print("\tS" + actual.Estado + "* con ");*/ }
                else { /*System.out.print("\tS" + actual.Estado + " con ");*/ }
                for (Tipo_A i : actual.DatosAceptados) { /*System.out.print(i.valor + ",");*/ }
                //System.out.println("");
                actual = actual.next;
            }
        }
        ShowArbol(this.primero);
    }

    public void ShowArbol(Siguientes_Nodo cabezera) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.Aceptacion) {
                    //System.out.println("S" + actual.Estado + "* a:");
                    EstadosAceptacion.add(actual.Estado);
                    EstadosAceptacion = QuitarDupicados(EstadosAceptacion);
                } else { /*System.out.println("S" + actual.Estado + " a:");*/ }
                //System.out.println("");
                actual.mostrado = true;
                ShowAceptaciones(actual.listado.primero, actual.Estado);
                actual = actual.next;
            }
        }
    }

    public void ShowAceptaciones(Siguientes_Nodo cabezera, Integer EstadoCabezera) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.EstadoRepetido == false) {
                    if (actual.Aceptacion) { /*System.out.print("\tS" + actual.Estado + "* con ");*/ }
                    else { /*System.out.print("\tS" + actual.Estado + " con ");*/ }
                } else { /*System.out.print("\tS" + actual.EstadoDestino + " con ");*/ }
                if (actual.EstadoRepetido) {
                    if (actual.EstadoDestino == EstadoCabezera) { actual.repetidoAsiMimso = true; }
                    if (actual.repetidoAsiMimso == false) {
                        DesdeEncabezado(actual.EstadoDestino, actual);
                        if (actual.listado.primero != null) { }
                    }
                }
                for (Tipo_A i : actual.DatosAceptados) { /*System.out.print(i.valor + ",");*/ }
                //System.out.println("");
                actual = actual.next;
            }
        }
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.EstadoRepetido == false) {
                    if (actual.mostrado == false) { ShowArbol(actual); }
                }
                actual = actual.next;
            }
        }
    }

    public void DesdeEncabezado(Integer EstadoBuscado, Siguientes_Nodo ActualVerificando) {
        if (isNone() == false) {
            Siguientes_Nodo actual = Estados.encabezadoEstado;
            while (actual != null) {
                if (actual != ActualVerificando && actual.EstadoRepetido == false) {
                    if (EstadoBuscado == actual.Estado) {
                        ActualVerificando.listado = actual.listado;
                        if(actual.Aceptacion) { ActualVerificando.Aceptacion =actual.Aceptacion; }
                    }
                    DesdeOtraparte(EstadoBuscado, ActualVerificando, actual);
                }
                actual = actual.next;
            }
        }
    }

    public void DesdeOtraparte(Integer EstadoBuscado, Siguientes_Nodo ActualVerificando,
        Siguientes_Nodo actualAnterior) {
        if (isNone() == false) {
            Siguientes_Nodo actual = actualAnterior.listado.primero;
            while (actual != null) {
                if (actual != ActualVerificando && actual.EstadoRepetido == false) {
                    if (EstadoBuscado == actual.Estado) {
                        ActualVerificando.listado = actual.listado;
                        if(actual.Aceptacion) { ActualVerificando.Aceptacion =actual.Aceptacion; }
                    }
                    DesdeOtraparte(EstadoBuscado, ActualVerificando, actual);
                }
                actual = actual.next;
            }
        }
    }

    public void Delete(Siguientes_Nodo actualReverse) {
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null && actual != actualReverse) { actual = actual.next; }
            if (actual == null) { }
            else {
                if (actual.previous == null) { this.primero = actual.next; }
                else if (actual != null) {
                    actual.previous.next = actual.next;
                    actual.next = null;
                }
            }
        }
    }

    public List<Tipo_A> QuitarDupicadosAceptacion(List<Tipo_A> lista) {
        lista = lista.stream().distinct().collect(Collectors.toList());
        return lista;
    }

    public List<Integer> QuitarDupicados(List<Integer> lista) {
        lista = lista.stream().distinct().collect(Collectors.toList());
        return lista;
    }

    public void showList() {
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null) {
                if (actual.EstadoRepetido) { /*System.out.print("Repedtido datos de aceptacion: " + actual.primeros + " DESTINO " + actual.EstadoDestino + " finalizacion: " + actual.Aceptacion);*/ }
                else { /*System.out.print(actual.Estado + " datos de aceptacion: " + actual.primeros + " finalizacion: " + actual.Aceptacion);*/ }
                //System.out.print("::::");
                for (Tipo_A i : actual.DatosAceptados) { /*System.out.print(i.valor);*/ }
                //System.out.println("");
                actual = actual.next;
            }
        }
    }

    public void Search(Tipo_A data) {
        if (isNone() == false) {
            Siguientes_Nodo actual = this.primero;
            while (actual != null && actual.data != data) {
                actual = actual.next;
                if (actual == null) {
                    System.out.println("No se encontro el dato: " + data);
                    break;
                }
            }
            if (actual != null && actual.data == data) { System.out.println("Dato encontrado: " + data); }
        }
    }

    public Boolean isNone() { return this.primero == null; }

    public Boolean isNoneLast() { return this.ultimo == null; }

    public void DotTraniscisiones(String name, Boolean Aceptacion) {
        TransicionesDot = "";
        TransicionesDot += "digraph G {\ncolor=white;\nedge[fontname=\"Helvetica,Arial,sans-serif\"];\nnode[shape=box];\n";
        TransicionesDot += "subgraph cluster1 {\n\nnode[color=gray];\n";
        TransicionesDot += "label = \"Tabla Transiciones (" + name + ")\"\nnode[color=gray];\na0[fontcolor=red fontsize=\"18\"];\na0 [label=<\n<TABLE border=\"0\" cellspacing=\"5\" cellpadding=\"5\">\n";
        Terminales = new ArrayList<>();
        Terminales = Estados.Sigeuintes.TerminalsE();
        TransicionesDot += "<TR>\n<TD border=\"1\"><font color=\"#ff7b00\">Estados\\Terminales</font></TD>\n";
        for (Tipo_A i : Terminales) {
            TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">";
            TransicionesDot += i.valor;
            TransicionesDot += "</font></TD>\n";
            ContadorGeneral++;
        }
        TransicionesDot += "</TR>\n";
        verArbolMainTransiciones(Aceptacion);
        TransicionesDot += "\n</TABLE>>];\n}\n}";
    }

    public List<String> QuitarDupicadosString(List<String> lista) {
        lista = lista.stream().distinct().collect(Collectors.toList());
        return lista;
    }

    public void verArbolMainTransiciones(Boolean Aceptacion) {
        List<Tipo_A> CopyTerminales = new ArrayList<>();
        CopyTerminales = obtenerListaTerminalesNulas(this.primero);
        TransicionesDot += "<TR>\n";
        if (Aceptacion) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">0/$ S0</font></TD>\n"; }
        else{ TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">0 S0</font></TD>\n"; }
        CopyTerminales = obtenerListaTerminalesNulas(this.primero);
        Boolean hayDatos = false;
        for (Tipo_A k : Terminales) {
                hayDatos = false;
            for (Tipo_A valor_Tipo : CopyTerminales) {
                if (k.valor.equals(valor_Tipo.valor)) {
                    TransicionesDot += "<TD border=\"1\" ><font color=\"#18abcc\"> S" + valor_Tipo.Estado + "</font></TD>\n";
                    CopyTerminales.remove(valor_Tipo);
                    hayDatos = true;
                    break;
                }
            }
            if (hayDatos == false) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18abcc\"> -- </font></TD>\n"; }
        }
        TransicionesDot += "</TR>\n";
        ShowAceptacionesTransiociones(this.primero);
    }

    public List<Tipo_A> obtenerListaTerminalesNulas(Siguientes_Nodo primero) {
        Boolean descartado = true;
        List<String> Temp = new ArrayList<>();
        List<Tipo_A> CopyTerminales = new ArrayList<>();
        List<Tipo_A> probando = new ArrayList<>();
        if (primero != null) {
            Siguientes_Nodo actual = primero;
            while (actual != null) {
                for (Tipo_A i : actual.DatosAceptados) { Temp.add(i.valor); }
                for (Tipo_A i : actual.DatosAceptados) {
                    Tipo_A data = new Tipo_A(i.valor, i.tipo);
                    if (actual.EstadoRepetido) { data.Estado = actual.EstadoDestino; }
                    else { data.Estado = actual.Estado; }
                    probando.add(data);
                }
                actual = actual.next;
            }
            probando = QuitarDupicadosAceptacion(probando);
            Temp = QuitarDupicadosString(Temp);
            Boolean continuar = true;
            for (Tipo_A i : Terminales) { CopyTerminales.add(i); }
            while (descartado) {
                if (CopyTerminales.size() != 0) {
                    for (Tipo_A i : CopyTerminales) {
                        continuar = true;
                        if (Temp.size() != 0) {
                            for (String string : Temp) {
                                if (i.valor.equals(string)) {
                                    Temp.remove(string);
                                    CopyTerminales.remove(i);
                                    continuar = false;
                                    break;
                                }
                            }
                        } else { descartado = false; }
                        if (continuar == false) { break; }
                    }
                }else { descartado = false; }
            }
        }
        return probando;
    }

    public void ShowAceptacionesTransiociones(Siguientes_Nodo cabezera) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.Estado != null) {
                    TransicionesDot += "  <TR>\n";
                    if (actual.EstadoRepetido) {
                        if (actual.Aceptacion) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">$  S" + actual.EstadoDestino + "</font></TD>\n"; }
                        else { TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">S" + actual.EstadoDestino + "</font></TD>\n"; }
                    } else {
                        if (actual.Aceptacion) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">$  S" + actual.Estado + "</font></TD>\n"; }
                        else { TransicionesDot += "<TD border=\"1\" ><font color=\"#18cc84\">S" + actual.Estado + "</font></TD>\n"; }
                    }
                    //System.out.println(actual.listado.primero);
                    subpartes(actual.listado.primero);
                }
                actual = actual.next;
            }
        }
    }

    public void subpartes(Siguientes_Nodo cabezera) {
        List<Tipo_A> CopyTerminales = new ArrayList<>();
        if (cabezera != null) {
            CopyTerminales = obtenerListaTerminalesNulas(cabezera);
            Boolean hayDatos = false;
            for (Tipo_A k : Terminales) {
                hayDatos = false;
                for (Tipo_A valor_Tipo : CopyTerminales) {
                    if (k.valor.equals(valor_Tipo.valor)) {
                        TransicionesDot += "<TD border=\"1\" ><font color=\"#18abcc\"> S" + valor_Tipo.Estado + "</font></TD>\n";
                        CopyTerminales.remove(valor_Tipo);
                        hayDatos = true;
                        break;
                    }
                }
                if (hayDatos == false) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18abcc\"> -- </font></TD>\n"; }
            }
            TransicionesDot += "</TR>\n";
            if (cabezera.EstadoRepetido == false) { System.out.println("\tEstado::- " + cabezera.Estado + " Valor Repetido"); }
            ShowAceptacionesTransiociones(cabezera);
        } else {
            for (int i = 0; i < ContadorGeneral; i++) { TransicionesDot += "<TD border=\"1\" ><font color=\"#18abcc\"> -- </font></TD>\n"; }
            TransicionesDot += "</TR>\n";
        }
    }

    public void Draw_GraphizTransiciones(String name) {
        try {
            if (isNone()) {
                String graph = "digraph L {\r\nnode[shape=note]\r\nsubgraph cluster_p{\r\n\r\nNodo1008925772[label=\"Vacio\",]\r\n\r\n}}";
                Create_File("src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".dot", graph);
            } else { Create_File("src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".dot", TransicionesDot); }
            ProcessBuilder pb,pq;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", "src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".png", "src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".dot");
            pb.redirectErrorStream(true);
            pb.start();
            pq = new ProcessBuilder("dot", "-Tsvg", "-o", "src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".svg", "src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".dot");
            pq.redirectErrorStream(true);
            pq.start();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void openimgTransiciones(String name) {
        try {
            String url = "src\\Reportes\\TRANSICIONES_201906051\\T_" + name + ".png";
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void ValidacionCadenaAEstados(Siguientes_Nodo cabezera, String letter) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.Aceptacion) { /*System.out.println("S" + actual.Estado + "* a:");*/ }
                else { /*System.out.println("S" + actual.Estado + " a:");*/ }
                if (actual.Estado == 5) { /*System.out.print("");*/ }
                //System.out.println("");
                actual = actual.next;
            }
        }
    }

    public void ValidacionPivote(Siguientes_Nodo cabezera, String letter, String letterSig, boolean primero) {
        if (primero) { ValidacionCadenaInicial(cabezera, letter, letterSig); }
        else { ValidacionCadena(cabezera, letter, letterSig); }
    }

    public void ValidacionCadenaInicial(Siguientes_Nodo cabezera, String letter, String letterSig) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (budquedaAceptacion(actual, letter, letterSig) == false) { break; }
                actual = actual.next;
            }
            if (actual == null) {
                System.out.println("\tCadena no Aceptada\n");
                Estados.CadenaValida = false;
            }
        }
    }

    public void ValidacionCadena(Siguientes_Nodo cabezera, String letter, String letterSig) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (budquedaAceptacion(actual, letter, letterSig) == false) { break; }
                actual = actual.next;
            }
            if (actual == null) {
                System.out.println("Fallo");
                Estados.CadenaValida = false;
            }
        }
    }

    public Boolean budquedaAceptacion(Siguientes_Nodo actual, String letter, String letterSig) {
        boolean continuar = true;
        for (Tipo_A i : actual.DatosAceptados) {
            if (i.tipo == "REFCONJ") {
                for (Conjunto conjunto : Estados.ListaConjuntos) {
                    if (conjunto.nombre.equals(i.valor)) {
                        if (conjunto.validar(letter)) {
                            NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                            continuar = false;
                            break;
                        }
                    }
                }
            } else if (i.tipo == "s_frase" || i.tipo == "espacio") {
                String valor = i.valor.replace("\"", "");
                if (valor.equals(letter)) {
                    NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                    continuar = false;
                    break;
                }
            } else if (i.tipo == "fin_linea") {
                if (letter.equals("\\n")) {
                    NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                    continuar = false;
                    break;
                }
            } else if (i.tipo == "s_comilla") {
                //System.out.println("SSSSSSSSSSS");
                //System.out.println(i.tipo);
                if (letter.equals("\'")) {
                    //System.out.println("Wuuuuuuuuuujuuuuuuuuuuuuu");
                    NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                    continuar = false;
                    break;
                }
            } else if (i.tipo == "doble_comilla") {
                if (letter.equals("\"")) {
                    NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                    continuar = false;
                    break;
                }
            } else {
                if (i.valor.equals(letter)) {
                    NuevaAsignacion(actual.Aceptacion, actual, letter, letterSig);
                    continuar = false;
                    break;
                }
            }
        }
        return continuar;
    }

    public void NuevaAsignacion(Boolean Aceptacion, Siguientes_Nodo actual, String letter, String letterSig) {
        if (actual.EstadoRepetido) { System.out.println("Valor: " + letter + ", al Estado: " + actual.EstadoDestino); }
        else { System.out.println("Valor: " + letter + ", al Estado: " + actual.Estado); }
        try {
            if (Aceptacion) {
                if (letterSig == null) {
                    Estados.cadenai.validacion = true;
                    System.out.println("Cadena aceptada");
                    Estados.anteriorEstado = actual;
                } else {
                    if (actual.listado.primero == null) {
                        if (actual.EstadoRepetido) {
                            Estados.anteriorEstado = actual;
                            Estados.ActualValidacion = actual;
                        } else {
                            Estados.anteriorEstado = actual;
                            Estados.CadenaValida = false;
                        }
                    } else {
                        Estados.anteriorEstado = actual;
                        Estados.ActualValidacion = actual.listado.primero;
                    }
                }
            } else {
                if (letterSig == null) {
                    Estados.anteriorEstado = actual;
                    Estados.CadenaValida = false;
                } else {
                    Estados.anteriorEstado = actual;
                    if (actual.EstadoRepetido) {
                        if (actual.repetidoAsiMimso) { Estados.ActualValidacion = actual; }
                        else { Estados.ActualValidacion = actual.listado.primero; }
                    } else { Estados.ActualValidacion = actual.listado.primero; }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            Estados.CadenaValida = false;
        }
    }

    public void ShowArbolTest(Siguientes_Nodo cabezera) {
        if (cabezera != null) {
            Siguientes_Nodo actual = cabezera;
            while (actual != null) {
                if (actual.Aceptacion) {
                    //System.out.println("S" + actual.Estado + "* a:");
                    EstadosAceptacion.add(actual.Estado);
                    EstadosAceptacion = QuitarDupicados(EstadosAceptacion);
                } else { /*System.out.println("S" + actual.Estado + " a:");*/ }
                if (actual.Estado == 5) { /*System.out.print("");*/ }
                /*System.out.println("");*/
                actual = actual.next;
            }
        }
    }
}

