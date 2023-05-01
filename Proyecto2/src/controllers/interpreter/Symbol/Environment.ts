import { Singleton } from "../Singleton/Singleton";
import { Type } from "./Type";
import { Symbol } from "./Symbol";
import { Function } from "../Instruction/Function";

export class Environment {

    /*envGlobal
      envGlobal.anterior = null
        function X() {
            envFunction
            envFunction.anterior = envGlobal;
            if(1){
                envIf
                envIf.anterior = envFunction
            }
    }*/ 

    //int numero = 5;
    //char letra = 'a';
    //int numero = 8;
    
    /**
     * 
     *  < clave, valor  >
     *  < numero, Class ( 5, numero, int )
     *  < letra,  Class ( 'a', letra, char )
     * 
     * 
     */

    private variables: Map<string, Symbol>; //La clase simbolo tiene un id, valor y un tipo
    public funciones: Map<string, Function>;

    constructor(public anterior: Environment | null) {
        this.variables = new Map();
        this.funciones = new Map();
    }

    public guardar(id: string, valor: any, type: Type) {
        let env: Environment | null = this;
        while (env != null) {
            if (env.variables.has(id)) {
                env.variables.set(id, new Symbol(valor, id, type));
                return;
            }
            env = env.anterior;
        }
        this.variables.set(id, new Symbol(valor, id, type));
    }

    public saveVar(nombre: string, valor: any, type: Type): boolean {

        if (!this.searchVar(nombre)) {
            this.variables.set(nombre, new Symbol(valor, nombre, type));
            return true
        }
        return false
    }

    public searchVar(nombre: string): boolean {
        for (const entry of Array.from(this.variables.entries())) {
            if (entry[0] == nombre) return true;
        }
        return false
    }


    public getVar(id: string): Symbol | undefined | null {
        let env: Environment | null = this;
        while (env != null) {
            if (env.variables.has(id)) {
                return env.variables.get(id);
            }
            env = env.anterior;
        }
        return null;
    }

    public getGlobal(): Environment {
        let env: Environment | null = this;
        while (env?.anterior != null) {
            env = env.anterior;
        }
        return env;
    }

    public actualizar_variable(nombre: string, new_valor: any) {

        let env: Environment | null = this;
        while (env != null) {
            if (env.variables.has(nombre)) {

                for (let entry of Array.from(env.variables)) {
                    if (entry[0] === nombre) {
                        entry[1].valor = new_valor;
                    }
                }
            }
            env = env.anterior;
        }
        return null;
    }

    public getEnv() {
        return this.variables
    }

    public get_variable(nombre: string): Symbol | undefined | null {
        let env: Environment | null = this;
        while (env != null) {
            if (env.variables.has(nombre)) return env.variables.get(nombre);
            env = env.anterior;
        }
        return null;
    }

    public getVars(): any {
        let st = []
        for (const entry of Array.from(this.variables.entries())) {
            st.push({ name: entry[1].id, value: entry[1].valor, type: Type[entry[1].type] })
        }
        return st
    }

    public guardarFuncion(id: string, funcion: Function) {
        //TODO ver si la funcion ya existe, reportar error
        this.funciones.set(id, funcion);
    }

    public getFuncion(id: string): Function | undefined {

        let env: Environment | null = this;
        while (env != null) {
            if (env.funciones.has(id)) {
                return env.funciones.get(id);
            }
            env = env.anterior;
        }
        return undefined;
    }

}