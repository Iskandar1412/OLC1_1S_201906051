import React, { useState, useRef, useEffect } from "react";
import * as d3 from "d3";
import * as d3Graphviz from "d3-graphviz";
import logico from "./Log.png";
import "./App.css";
const Graph = require("./Analizer/controller/AST/Graph");
const parser = require("./Analizer/gramatica");
const Ambito = require("./Analizer/model/Ambito/Ambito");
const Global = require("./Analizer/model/Ambito/Global");

function App() {
  const [tabs, setTabs] = useState([]);
  const [activeTab, setActiveTab] = useState(null);
  const [text, setText] = useState('');
  const [text_err, setText_err] = useState('');
  //--------------------------------------------
  // para graphos

  const renderGraph = (dot) => {
    d3Graphviz.graphviz("#seccion-ast").width(1455).height(787).renderDot(dot);
  };
  let dot_final;
  const dot = `digraph {
    NoGraph -> 0;
  }`;
  //--------------------------------------------
  //--------------------------------------------
  //Tabla de Simbolos
  const SimbolTable = (sim_data) => {
    //console.log(sim_data[0].valor)
    //err_data[n].<tipo_valor>
    var str_data_table = '';
    str_data_table += '<table><thead><tr><th scope="col">#</th><th scope="col">NOMBRE</th><th scope="col">SIMBOLO</th><th scope="col">TIPO DATO</th><th scope="col">AMBITO</th><th scope="col">LINEA</th><th scope="col">COLUMNA</th></tr></thead><tbody>';
    let i = 0;
    //console.log(sim_data.length)
    while(i < sim_data.length){
      //str_data_table += '<table><tr><th>ID</th><th>Objeto</th><th>Tipo</th><th>Entorno</th><th>Linea</th><th>Columna</th></tr>';
      //str_data_table += '<tr><td>' + sim_data[i].id + '</td><td>' + sim_data[i].objeto + '</td><td>' + sim_data[i].tipo + '</td><td>' + sim_data[i].entorno + '</td><td>' + sim_data[i].linea + '</td><td>' + sim_data[i].columna + '</td></tr>';
      str_data_table += '<tr><td scope="row">' + (i + 1) +'</td><td>' + sim_data[i].id + '</td><td>' + sim_data[i].objeto + '</td><td>' + sim_data[i].tipo + '</td><td>' + sim_data[i].entorno + '</td><td>' + sim_data[i].linea + '</td><td>' + sim_data[i].columna + '</td></tr>';
      i++;
    }
    str_data_table += '</tbody></table>';
    setText(str_data_table);
  }
  //--------------------------------------------
  //TAblaErrores
  const ErrorTable = (err_data) => {
    var str_data_err = '';
    //console.log(err_data.arreglo_errores)
    var err_fs = err_data.arreglo_errores;

    
    str_data_err += '<table><thead><tr><th scope="col">#</th><th scope="col">TIPO</th><th scope="col">ERROR</th><th scope="col">LINEA</th><th scope="col">COLUMNA</th></tr></thead><tbody>';
    let i = 0;
    //console.log(err_fs)
    while(i < err_fs.length){
      str_data_err += '<tr><td scope="row">' + (i + 1) + '</td><td>' + err_fs[i].tipo + '</td><td>' + err_fs[i].error + '</td><td>' + err_fs[i].linea + '</td><td>' + err_fs[i].columna + '</td></td>';
      i++;
    }
    str_data_err += '</tbody></table>';
    setText_err(str_data_err);
  }
  //--------------------------------------------
  const handleTabClick = (id) => {
    setActiveTab(id);
  };

  const handleAddTab = () => {
    const newId = tabs.length === 0 ? 1 : tabs[tabs.length - 1].id + 1;
    const newTab = { id: newId, value1: "", value2: "" };
    setTabs([...tabs, newTab]);
    setActiveTab(newId);
  };

  const handleDeleteTab = (id) => {
    const newTabs = tabs.filter((tab) => tab.id !== id);
    setTabs(newTabs);
    setActiveTab(newTabs.length === 0 ? null : newTabs[0].id);
  };

  const handleCopyValue = (id) => {
    const newTabs = [...tabs];
    const tabIndex = newTabs.findIndex((tab) => tab.id === id);
    const value1 = newTabs[tabIndex].value1;
    //para parsear
    //const result = parser.parse(value1);

    /*
    if (errores.length > 0) {
      console.log('Error para generar el AST');
      return;
    }else{
      console.log(parse);
    */
    /*
      console.log('errores');
      console.log(errores);
      console.log('errores');
      */
    //-----------------
    /**/
    try {
      var input = value1;
      var ast = parser.parse(input);
      //console.log(respuesta);
      //var parse = ast.parse;
      //var errores = ast.errores;
      if (ast.parse === null) {
        var output = {
          arreglo_simbolos: [],
          arreglo_errores: ast.errores,
          output: "Error, no se ha podido obtener el valor",
        };
        return;
      }
      var parse = ast.parse;
      var errores = ast.errores;
      const global = new Ambito(null, "global");
      var cadena = Global(parse, global);
      var simbolos = global.getArraySimbols();
      for (let i = 0; i < cadena.errores; i++) {
        const err = cadena.errores[i];
        errores.push(err);
      }
      var output = {
          arreglo_simbolos: simbolos,
          arreglo_errores: errores,
          output: cadena.cadena
      }

      //console.log(simbolos[0].id);
      ErrorTable(output);
      SimbolTable(simbolos);
      //console.log(errores);
      newTabs[tabIndex] = { ...newTabs[tabIndex], value2: cadena.cadena };
      setTabs(newTabs);
      var graph = new Graph(parse);
      var grafico_dot = graph.getDot();
      renderGraph(grafico_dot);
    } catch (error) {
      console.log(error);

      //console.log(output);
      
      newTabs[tabIndex] = { ...newTabs[tabIndex], value2: String(error) };
      setTabs(newTabs);
      renderGraph(dot);
    }

    /*}*/
  };

  //-------------------------------------
  const handleOpenFile = async () => {
    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.accept = ".tw";
    fileInput.addEventListener("change", async (event) => {
      const target = event.target;
      const file = target.files?.[0];
      if (file) {
        const fileText = await file.text();
        const newTabs = [...tabs];
        const tabIndex = newTabs.findIndex((tab) => tab.id === activeTab);
        newTabs[tabIndex] = { ...newTabs[tabIndex], value1: fileText };
        setTabs(newTabs);
      }
    });
    fileInput.click();
  };
  //-------------------------------
  const handleSaveFile = async () => {
    const content = tabs.find((tab) => tab.id === activeTab)?.value1;
    if (!content) return;
    const blob = new Blob([content], { type: "text/plain" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = "ArchivoProy.tw";
    link.click();
    URL.revokeObjectURL(url);
  };
  return (
    <>
      <nav className="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <a className="navbar-brand ps-3" href="/">
          <img src={logico} className="Logo-Asv2" alt="logo" />
          Proyecto 2 (201906051)
        </a>
      </nav>
      <div id="layoutSidenav">
        <main className="container-w">
          <input id="tab1" type="radio" name="tabs" defaultChecked />
          <label htmlFor="tab1" className="label-type">
            Analyzer
          </label>

          <input id="tab2" type="radio" name="tabs" />
          <label htmlFor="tab2" className="label-type">
            Errores
          </label>

          <input id="tab3" type="radio" name="tabs" />
          <label htmlFor="tab3" className="label-type">
            Árbol AST
          </label>

          <input id="tab4" type="radio" name="tabs" />
          <label htmlFor="tab4" className="label-type">
            Tabla de Símbolos
          </label>

          <section id="content1" className="tabs-contentype">
            <div className="tabs-section">
              <div className="tabs-container">
                {tabs.map((tab) => (
                  <div
                    key={tab.id}
                    className={`tab ${activeTab === tab.id ? "active" : ""}`}
                    onClick={() => handleTabClick(tab.id)}
                  >
                    P{tab.id}
                    <button
                      className="delete-tab"
                      onClick={() => handleDeleteTab(tab.id)}
                    >
                      𝒙
                    </button>
                  </div>
                ))}
                <button className="add-tab" onClick={handleAddTab}>
                  +
                </button>
              </div>
              <div className="tab-content">
                {activeTab !== null && (
                  <>
                    <div className="content-text">
                      <div className="editor">
                        <textarea
                          className="text-area"
                          value={
                            tabs.find((tab) => tab.id === activeTab)?.value1
                          }
                          onChange={(e) => {
                            const newTabs = [...tabs];
                            const tabIndex = newTabs.findIndex(
                              (tab) => tab.id === activeTab
                            );
                            newTabs[tabIndex] = {
                              ...newTabs[tabIndex],
                              value1: e.target.value,
                            };
                            setTabs(newTabs);
                          }}
                        ></textarea>
                      </div>
                      <div className="editor">
                        <textarea
                          className="text-area"
                          value={
                            tabs.find((tab) => tab.id === activeTab)?.value2
                          }
                          readOnly
                        ></textarea>
                      </div>
                    </div>
                    <div className="content-button">
                      <button
                        className="open-file"
                        onClick={() => {
                          handleOpenFile();
                        }}
                      >
                        Open File
                      </button>
                      <button
                        className="acept-analyze"
                        onClick={() => handleCopyValue(activeTab)}
                      >
                        Analyze
                      </button>
                      <button className="save-file" onClick={handleSaveFile}>
                        Save File
                      </button>
                    </div>
                  </>
                )}
              </div>
            </div>
          </section>

          <section id="content2" className="tabs-contentype">
            <div id="seccion-errores" dangerouslySetInnerHTML={{ __html: text_err }}></div>
          </section>

          <section id="content3" className="tabs-contentype">
            <div id="seccion-ast">
              <img />
            </div>
          </section>

          <section id="content4" className="tabs-contentype">
            <div id="seccion-tabla" dangerouslySetInnerHTML={{ __html: text }}></div>
          </section>
        </main>
      </div>
    </>
  );
}

export default App;
