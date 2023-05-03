# Proyecto 1
## Gramática
### Introducción
<p style="text-align: justify;">

ini<br/>
&nbsp;&nbsp;    : ENTRADA EOF  
&nbsp;&nbsp;    | error EOF   
;

ENTRADA
    : ENTRADA ENTCERO                   
    | ENTCERO                           
;
ENTCERO
    : FUNCIONBODY     
    | METODOBODY     
    | EXECBODY         
    | DEC_VAR         
    | DEC_VECT        
    | DEC_LIST       
;

FUNCIONBODY
    : TIPO id pabre pcierra labre INSTRUCCION lcierra                  
    | TIPO id pabre pcierra labre lcierra                            
    | TIPO id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra      
    | TIPO id pabre LISTAPARAMETROS pcierra labre lcierra               
    | TIPO_VECT id pabre pcierra labre INSTRUCCION lcierra                 
    | TIPO_VECT id pabre pcierra labre lcierra                            
    | TIPO_VECT id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra    
    | TIPO_VECT id pabre LISTAPARAMETROS pcierra labre lcierra             
    | TIPO_LIST id pabre pcierra labre INSTRUCCION lcierra             
    | TIPO_LIST id pabre pcierra labre lcierra                               
    | TIPO_LIST id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra  
    | TIPO_LIST id pabre LISTAPARAMETROS pcierra labre lcierra         
    | TIPO error lcierra                                                  
    | TIPO_VECT error lcierra                                        
    | TIPO_LIST error lcierra                                            
;

METODOBODY
    : prvoid id pabre pcierra labre INSTRUCCION lcierra                   
    | prvoid id pabre pcierra labre lcierra                              
    | prvoid id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra      
    | prvoid id pabre LISTAPARAMETROS pcierra labre lcierra                  
    | prvoid error lcierra                                           
;


EXECBODY
    : premain id pabre pcierra ptcoma                     
    | premain id pabre LISTAVALORES pcierra ptcoma           
    | premain error ptcoma                                 
;

LISTAPARAMETROS
    : LISTAPARAMETROS coma PARAMETROS  
    | PARAMETROS                    
;

PARAMETROS
    : TIPO_VECT id   
    | TIPO_LIST id     
    | TIPO id         
;

INSTRUCCION
    : INSTRUCCION INSCERO      
    | INSCERO                
;

INSCERO
    : DEC_VAR                 
    | SENTENCIACONTROL       
    | SENTENCIACICLO           
    | DEC_VECT                
    | DEC_LIST                
    | SENTENCIATRANSFERENCIA   
    | LLAMADA ptcoma          
    | FPRINT                 
    | error ptcoma           
    | error lcierra            
;

SENTENCIATRANSFERENCIA
    : prbreak ptcoma              
    | prreturn EXPRESION ptcoma     
    | prcontinue ptcoma          
    | prreturn ptcoma            
;

SENTENCIACICLO
    : WHILE       
    | FOR        
    | DOWHILE      
;

WHILE
    : prwhile pabre EXPRESION pcierra labre INSTRUCCION lcierra    
    | prwhile pabre EXPRESION pcierra labre lcierra             
    | prwhile error lcierra                                 
;

FOR
    : prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre INSTRUCCION lcierra    
    | prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre lcierra                 
    | prfor error lcierra                                          
;

ACTUALIZACION
    : id igual EXPRESION 
    | id incremento 
    | id decremento 
;

DOWHILE
    : prdo labre INSTRUCCION lcierra prwhile pabre EXPRESION pcierra ptcoma    
    | prdo labre lcierra prwhile pabre EXPRESION pcierra ptcoma            
    | prdo error ptcoma                                        
;

SENTENCIACONTROL
    : CONTROLIF    
    | SWITCH       
;

CONTROLIF
    : IF                   
    | IFELSE               
    | ELSEIF               
    | prif error lcierra    
;

IF
    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra   
    | prif pabre EXPRESION pcierra labre lcierra        
;

IFELSE
    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre INSTRUCCION lcierra   
    | prif pabre EXPRESION pcierra labre lcierra prelse labre INSTRUCCION lcierra              
    | prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre lcierra               
    | prif pabre EXPRESION pcierra labre lcierra prelse labre lcierra                    


ELSEIF
    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse CONTROLIF   
    | prif pabre EXPRESION pcierra labre lcierra prelse CONTROLIF     
;

SWITCH
    : prswitch pabre EXPRESION pcierra labre CASESLIST DEFAULT lcierra    
    | prswitch pabre EXPRESION pcierra labre CASESLIST lcierra         
    | prswitch pabre EXPRESION pcierra labre DEFAULT lcierra           
    | prswitch error lcierra                     
;

CASESLIST
    : CASESLIST prcase EXPRESION dospuntos INSTRUCCION     
    | CASESLIST prcase EXPRESION dospuntos                 
    | prcase EXPRESION dospuntos INSTRUCCION            
    | prcase EXPRESION dospuntos                            
    | prcase error dospuntos                             
;

DEFAULT
    : prdefault dospuntos INSTRUCCION        
    | prdefault dospuntos                    
;

DEC_VAR
    : TIPO id igual EXPRESION ptcoma   
    | TIPO id ptcoma                  
    | id igual EXPRESION ptcoma        
    | id incremento ptcoma        
    | id decremento ptcoma            
    | TIPO error ptcoma             
;

DEC_VECT
    : TIPO_VECT id igual prnew TIPO cabre EXPRESION ccierra ptcoma       
    | TIPO_VECT id igual labre LISTAVALORES lcierra ptcoma            
    | id cabre EXPRESION ccierra igual EXPRESION ptcoma         
    | TIPO_VECT id igual EXPRESION ptcoma                   
    | TIPO_VECT error ptcoma                                       
;


VALORES
    : EXPRESION 
;


DEC_LIST
    : TIPO_LIST id igual prnew prlist menor TIPO mayor ptcoma            
    | id punto pradd pabre EXPRESION pcierra ptcoma                   
    | id cabre cabre EXPRESION ccierra ccierra igual EXPRESION ptcoma      
    | TIPO_LIST id igual EXPRESION ptcoma                     
    | TIPO_LIST error ptcoma                      
;

TIPO_VECT
    : TIPO cabre ccierra     
;

TIPO_LIST
    : prlist menor TIPO mayor   
;

TIPO
    : TIPODATO    
;

TIPODATO
    : prstring      
    | printeger    
    | prdouble    
    | prchar       
    | prboolean   
;

FTOSTRING
    : prtoString pabre EXPRESION pcierra       
;


LLAMADA
    : id pabre LISTAVALORES pcierra     
    | id pabre pcierra                 
;

LISTAVALORES
    : LISTAVALORES coma VALORES 
    | VALORES                   
;

CASTEO
    : pabre TIPO pcierra EXPRESION 
;


EXPRESION
    :   EXPRESION suma EXPRESION              
    | EXPRESION menos EXPRESION                   
    | EXPRESION multi EXPRESION                
    | EXPRESION div EXPRESION                      
    | EXPRESION exponente EXPRESION               
    | EXPRESION modulo EXPRESION                    
    | menos EXPRESION %prec umenos               
    | EXPRESION igualigual EXPRESION             
    | EXPRESION diferente EXPRESION                
    | EXPRESION menor EXPRESION                    
    | EXPRESION menorigual EXPRESION               
    | EXPRESION mayor EXPRESION                   
    | EXPRESION mayorigual EXPRESION               
    | EXPRESION or EXPRESION                      
    | EXPRESION and EXPRESION                     
    | not EXPRESION                               
    | cadena                                      
    | caracter                                   
    | true                                     
    | false                                    
    | entero                                    
    | doble                                      
    | id cabre cabre EXPRESION ccierra ccierra     
    | id cabre EXPRESION ccierra                  
    | id                                       
    | CASTEO                                        
    | TERNARIO                                
    | LLAMADA                                  
    | FUNCIONESRESERVADAS                          
;

TERNARIO
    : EXPRESION interrogacion EXPRESION dospuntos EXPRESION 
;



FUNCIONESRESERVADAS
    : FTOLOWER        
    | FTOUPPER       
    | FLENGTH          
    | FTRUNCATE      
    | FROUND       
    | FTYPEOF           
    | FTOSTRING        
    | FTOCHARARRAY      
;
FPRINT
    : prprint pabre EXPRESION pcierra ptcoma    
    | prprint pabre pcierra ptcoma            
    | prprint error ptcoma                     
;


FTOLOWER
    : prtoLower pabre EXPRESION pcierra       
;

FTOUPPER
    : prtoUpper pabre EXPRESION pcierra        
;

FTOCHARARRAY
    : prtoCharArray pabre EXPRESION pcierra     
;

FLENGTH
    : prlength pabre EXPRESION pcierra         
;

FTRUNCATE
    : prtruncate pabre EXPRESION pcierra      
;

FROUND
    : prround pabre EXPRESION pcierra        
;

FTYPEOF
    : prtypeof pabre EXPRESION pcierra        
;

</p>
