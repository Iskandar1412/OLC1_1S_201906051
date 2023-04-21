

export class Tipo {
    tipo: string;
    constructor(tipo: string) {
        this.tipo = tipo;
    }

    getPrimitivo(): String {
        return this.tipo;
    }
    
}