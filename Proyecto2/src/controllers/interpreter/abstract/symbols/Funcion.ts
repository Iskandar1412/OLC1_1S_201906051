

export class Funcion {
    nombre: string;
    parametros: string;
    sentencias: string;

    constructor(nombre: string, parametros: string, sentencias: string) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.sentencias = sentencias;
    }

    public getNombre(): string {
        return this.nombre;
    }

    public getParametros(): string {
        return this.parametros;
    }
}