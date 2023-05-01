import { Expression } from "../Abstract/Expression";
import { Instruction } from "../Abstract/Instruction";
import { Error_ } from "../Error";
import { errores } from "../Errores";
import { Environment } from "../Symbol/Environment";
import { Type } from "../Symbol/Type";

export class For extends Instruction {
    constructor(private initial: Instruction, private condition: Expression, private update: Expression, private code: Instruction, line: number, column: number){
        super(line, column);
    }

    public execute(env: Environment) {
        
        //ejecutar instruccion
        const init = this.initial.execute(env);
        let condition = this.condition.execute(env);
        let update = this.update.execute(env);

        while(condition.value == true) {
            const element = this.code.execute(env);
            if(element != null || element != undefined) {
                if(element.type == 'Break') {
                    break;
                }else if(element.type == 'Continue') {
                    condition = this.condition.execute(env);
                    continue;
                }else{
                    return element;
                }    
            }
            update = this.update.execute(env);
            condition = this.condition.execute(env);

            if(condition.type != Type.BOOLEAN) {
                errores.push(new Error_(this.line, this.column, 'Semantico', `Instrucción While: la condición '${condition.value}' no es booleana.` ))
            }
        }
        if(condition.type != Type.BOOLEAN) {
            errores.push(new Error_(this.line, this.column, 'Semantico', `Instruccion IF: la condicion '${condition.value}' no es booleana.`));
        }
    }

}