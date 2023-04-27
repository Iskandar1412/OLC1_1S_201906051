const { TablaErrores } = require('../interpreter/errores/TablaErrores');
const { _Error } = require('../interpreter/errores/_Error.js');
const { ResOperacion } = require('./resultado.js');
const { TablaSimbolos } = require('../interpreter/tabla_simbolos/TablaSimbolos.js');

export class Operador {
    constructor() { }

    ejecutar(root) {
        var Resultado1 = null;
        var Resultado2 = null;
        var Resultado3 = null;

        switch(root.name) {
            case "EXP":
                console.log('EXP');
                break;
            
            default:
                break;
        }

    }
    
}