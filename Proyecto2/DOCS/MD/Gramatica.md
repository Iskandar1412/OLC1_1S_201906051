# Proyecto 1
## Gramática
### Introducción
<p style="text-align: justify;">

ini<br/>
&nbsp;&nbsp;    : ENTRADA EOF  
&nbsp;&nbsp;    | error EOF   
;

ENTRADA<br/>
&nbsp;&nbsp;    : ENTRADA ENTCERO                   
&nbsp;&nbsp;    | ENTCERO                           
;
ENTCERO<br/>
&nbsp;&nbsp;    : FUNCIONBODY     
&nbsp;&nbsp;    | METODOBODY     
&nbsp;&nbsp;    | EXECBODY         
&nbsp;&nbsp;    | DEC_VAR         
&nbsp;&nbsp;    | DEC_VECT        
&nbsp;&nbsp;    | DEC_LIST       
;

FUNCIONBODY<br/>
&nbsp;&nbsp;    : TIPO id pabre pcierra labre INSTRUCCION lcierra                  
&nbsp;&nbsp;    | TIPO id pabre pcierra labre lcierra                            
&nbsp;&nbsp;    | TIPO id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra      
&nbsp;&nbsp;    | TIPO id pabre LISTAPARAMETROS pcierra labre lcierra               
&nbsp;&nbsp;    | TIPO_VECT id pabre pcierra labre INSTRUCCION lcierra                 
&nbsp;&nbsp;    | TIPO_VECT id pabre pcierra labre lcierra                            
&nbsp;&nbsp;    | TIPO_VECT id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra    
&nbsp;&nbsp;    | TIPO_VECT id pabre LISTAPARAMETROS pcierra labre lcierra             
&nbsp;&nbsp;    | TIPO_LIST id pabre pcierra labre INSTRUCCION lcierra             
&nbsp;&nbsp;    | TIPO_LIST id pabre pcierra labre lcierra                               
&nbsp;&nbsp;    | TIPO_LIST id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra  
&nbsp;&nbsp;    | TIPO_LIST id pabre LISTAPARAMETROS pcierra labre lcierra         
&nbsp;&nbsp;    | TIPO error lcierra                                                  
&nbsp;&nbsp;    | TIPO_VECT error lcierra                                        
&nbsp;&nbsp;    | TIPO_LIST error lcierra                                            
;

METODOBODY<br/>
&nbsp;&nbsp;    : prvoid id pabre pcierra labre INSTRUCCION lcierra                   
&nbsp;&nbsp;    | prvoid id pabre pcierra labre lcierra                              
&nbsp;&nbsp;    | prvoid id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra      
&nbsp;&nbsp;    | prvoid id pabre LISTAPARAMETROS pcierra labre lcierra                  
&nbsp;&nbsp;    | prvoid error lcierra                                           
;


EXECBODY<br/>
&nbsp;&nbsp;    : premain id pabre pcierra ptcoma                     
&nbsp;&nbsp;    | premain id pabre LISTAVALORES pcierra ptcoma           
&nbsp;&nbsp;    | premain error ptcoma                                 
;

LISTAPARAMETROS<br/>
&nbsp;&nbsp;    : LISTAPARAMETROS coma PARAMETROS  
&nbsp;&nbsp;    | PARAMETROS                    
;

PARAMETROS<br/>
&nbsp;&nbsp;    : TIPO_VECT id   
&nbsp;&nbsp;    | TIPO_LIST id     
&nbsp;&nbsp;    | TIPO id         
;

INSTRUCCION<br/>
&nbsp;&nbsp;    : INSTRUCCION INSCERO      
&nbsp;&nbsp;    | INSCERO                
;

INSCERO<br/>
&nbsp;&nbsp;    : DEC_VAR                 
&nbsp;&nbsp;    | SENTENCIACONTROL       
&nbsp;&nbsp;    | SENTENCIACICLO           
&nbsp;&nbsp;    | DEC_VECT                
&nbsp;&nbsp;    | DEC_LIST                
&nbsp;&nbsp;    | SENTENCIATRANSFERENCIA   
&nbsp;&nbsp;    | LLAMADA ptcoma          
&nbsp;&nbsp;    | FPRINT                 
&nbsp;&nbsp;    | error ptcoma           
&nbsp;&nbsp;    | error lcierra            
;

SENTENCIATRANSFERENCIA<br/>
&nbsp;&nbsp;    : prbreak ptcoma              
&nbsp;&nbsp;    | prreturn EXPRESION ptcoma     
&nbsp;&nbsp;    | prcontinue ptcoma          
&nbsp;&nbsp;    | prreturn ptcoma            
;

SENTENCIACICLO<br/>
&nbsp;&nbsp;    : WHILE       
&nbsp;&nbsp;    | FOR        
&nbsp;&nbsp;    | DOWHILE      
;

WHILE<br/>
&nbsp;&nbsp;    : prwhile pabre EXPRESION pcierra labre INSTRUCCION lcierra    
&nbsp;&nbsp;    | prwhile pabre EXPRESION pcierra labre lcierra             
&nbsp;&nbsp;    | prwhile error lcierra                                 
;

FOR<br/>
&nbsp;&nbsp;    : prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre INSTRUCCION lcierra    
&nbsp;&nbsp;    | prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre lcierra                 
&nbsp;&nbsp;    | prfor error lcierra                                          
;

ACTUALIZACION<br/>
&nbsp;&nbsp;    : id igual EXPRESION 
&nbsp;&nbsp;    | id incremento 
&nbsp;&nbsp;    | id decremento 
;

DOWHILE<br/>
&nbsp;&nbsp;    : prdo labre INSTRUCCION lcierra prwhile pabre EXPRESION pcierra ptcoma    
&nbsp;&nbsp;    | prdo labre lcierra prwhile pabre EXPRESION pcierra ptcoma            
&nbsp;&nbsp;    | prdo error ptcoma                                        
;

SENTENCIACONTROL<br/>
&nbsp;&nbsp;    : CONTROLIF    
&nbsp;&nbsp;    | SWITCH       
;

CONTROLIF<br/>
&nbsp;&nbsp;    : IF                   
&nbsp;&nbsp;    | IFELSE               
&nbsp;&nbsp;    | ELSEIF               
&nbsp;&nbsp;    | prif error lcierra    
;

IF<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra        
;

IFELSE<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre INSTRUCCION lcierra   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse labre INSTRUCCION lcierra              
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre lcierra               
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse labre lcierra                    
;

ELSEIF<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse CONTROLIF   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse CONTROLIF     
;

SWITCH<br/>
&nbsp;&nbsp;    : prswitch pabre EXPRESION pcierra labre CASESLIST DEFAULT lcierra    
&nbsp;&nbsp;    | prswitch pabre EXPRESION pcierra labre CASESLIST lcierra         
&nbsp;&nbsp;    | prswitch pabre EXPRESION pcierra labre DEFAULT lcierra           
&nbsp;&nbsp;    | prswitch error lcierra                     
;

CASESLIST<br/>
&nbsp;&nbsp;    : CASESLIST prcase EXPRESION dospuntos INSTRUCCION     
&nbsp;&nbsp;    | CASESLIST prcase EXPRESION dospuntos                 
&nbsp;&nbsp;    | prcase EXPRESION dospuntos INSTRUCCION            
&nbsp;&nbsp;    | prcase EXPRESION dospuntos                            
&nbsp;&nbsp;    | prcase error dospuntos                             
;

DEFAULT<br/>
&nbsp;&nbsp;    : prdefault dospuntos INSTRUCCION        
&nbsp;&nbsp;    | prdefault dospuntos                    
;

DEC_VAR<br/>
&nbsp;&nbsp;    : TIPO id igual EXPRESION ptcoma   
&nbsp;&nbsp;    | TIPO id ptcoma                  
&nbsp;&nbsp;    | id igual EXPRESION ptcoma        
&nbsp;&nbsp;    | id incremento ptcoma        
&nbsp;&nbsp;    | id decremento ptcoma            
&nbsp;&nbsp;    | TIPO error ptcoma             
;

DEC_VECT<br/>
&nbsp;&nbsp;    : TIPO_VECT id igual prnew TIPO cabre EXPRESION ccierra ptcoma       
&nbsp;&nbsp;    | TIPO_VECT id igual labre LISTAVALORES lcierra ptcoma            
&nbsp;&nbsp;    | id cabre EXPRESION ccierra igual EXPRESION ptcoma         
&nbsp;&nbsp;    | TIPO_VECT id igual EXPRESION ptcoma                   
&nbsp;&nbsp;    | TIPO_VECT error ptcoma                                       
;

VALORES<br/>
&nbsp;&nbsp;    : EXPRESION 
;

DEC_LIST<br/>
&nbsp;&nbsp;    : TIPO_LIST id igual prnew prlist menor TIPO mayor ptcoma            
&nbsp;&nbsp;    | id punto pradd pabre EXPRESION pcierra ptcoma                   
&nbsp;&nbsp;    | id cabre cabre EXPRESION ccierra ccierra igual EXPRESION ptcoma      
&nbsp;&nbsp;    | TIPO_LIST id igual EXPRESION ptcoma                     
&nbsp;&nbsp;    | TIPO_LIST error ptcoma                      
;

TIPO_VECT<br/>
&nbsp;&nbsp;    : TIPO cabre ccierra     
;

TIPO_LIST<br/>
&nbsp;&nbsp;    : prlist menor TIPO mayor   
;

TIPO<br/>
&nbsp;&nbsp;    : TIPODATO    
;

TIPODATO<br/>
&nbsp;&nbsp;    : prstring      
&nbsp;&nbsp;    | printeger    
&nbsp;&nbsp;    | prdouble    
&nbsp;&nbsp;    | prchar       
&nbsp;&nbsp;    | prboolean   
;

FTOSTRING<br/>
&nbsp;&nbsp;    : prtoString pabre EXPRESION pcierra       
;

LLAMADA<br/>
&nbsp;&nbsp;    : id pabre LISTAVALORES pcierra     
&nbsp;&nbsp;    | id pabre pcierra                 
;

LISTAVALORES<br/>
&nbsp;&nbsp;    : LISTAVALORES coma VALORES 
&nbsp;&nbsp;    | VALORES                   
;

CASTEO<br/>
&nbsp;&nbsp;    : pabre TIPO pcierra EXPRESION 
;

EXPRESION<br/>
&nbsp;&nbsp;    : EXPRESION suma EXPRESION              
&nbsp;&nbsp;    | EXPRESION menos EXPRESION                   
&nbsp;&nbsp;    | EXPRESION multi EXPRESION                
&nbsp;&nbsp;    | EXPRESION div EXPRESION                      
&nbsp;&nbsp;    | EXPRESION exponente EXPRESION               
&nbsp;&nbsp;    | EXPRESION modulo EXPRESION                    
&nbsp;&nbsp;    | menos EXPRESION %prec umenos               
&nbsp;&nbsp;    | EXPRESION igualigual EXPRESION             
&nbsp;&nbsp;    | EXPRESION diferente EXPRESION                
&nbsp;&nbsp;    | EXPRESION menor EXPRESION                    
&nbsp;&nbsp;    | EXPRESION menorigual EXPRESION               
&nbsp;&nbsp;    | EXPRESION mayor EXPRESION                   
&nbsp;&nbsp;    | EXPRESION mayorigual EXPRESION               
&nbsp;&nbsp;    | EXPRESION or EXPRESION                      
&nbsp;&nbsp;    | EXPRESION and EXPRESION                     
&nbsp;&nbsp;    | not EXPRESION                               
&nbsp;&nbsp;    | cadena                                      
&nbsp;&nbsp;    | caracter                                   
&nbsp;&nbsp;    | true                                     
&nbsp;&nbsp;    | false                                    
&nbsp;&nbsp;    | entero                                    
&nbsp;&nbsp;    | doble                                      
&nbsp;&nbsp;    | id cabre cabre EXPRESION ccierra ccierra     
&nbsp;&nbsp;    | id cabre EXPRESION ccierra                  
&nbsp;&nbsp;    | id                                       
&nbsp;&nbsp;    | CASTEO                                        
&nbsp;&nbsp;    | TERNARIO                                
&nbsp;&nbsp;    | LLAMADA                                  
&nbsp;&nbsp;    | FUNCIONESRESERVADAS                          
;

TERNARIO<br/>
&nbsp;&nbsp;    : EXPRESION interrogacion EXPRESION dospuntos EXPRESION 
;

FUNCIONESRESERVADAS<br/>
&nbsp;&nbsp;    : FTOLOWER        
&nbsp;&nbsp;    | FTOUPPER       
&nbsp;&nbsp;    | FLENGTH          
&nbsp;&nbsp;    | FTRUNCATE      
&nbsp;&nbsp;    | FROUND       
&nbsp;&nbsp;    | FTYPEOF           
&nbsp;&nbsp;    | FTOSTRING        
&nbsp;&nbsp;    | FTOCHARARRAY      
;

FPRINT<br/>
&nbsp;&nbsp;    : prprint pabre EXPRESION pcierra ptcoma    
&nbsp;&nbsp;    | prprint pabre pcierra ptcoma            
&nbsp;&nbsp;    | prprint error ptcoma                     
;

FTOLOWER<br/>
&nbsp;&nbsp;    : prtoLower pabre EXPRESION pcierra       
;

FTOUPPER<br/>
&nbsp;&nbsp;    : prtoUpper pabre EXPRESION pcierra        
;

FTOCHARARRAY<br/>
&nbsp;&nbsp;    : prtoCharArray pabre EXPRESION pcierra     
;

FLENGTH<br/>
&nbsp;&nbsp;    : prlength pabre EXPRESION pcierra         
;

FTRUNCATE<br/>
&nbsp;&nbsp;    : prtruncate pabre EXPRESION pcierra      
;

FROUND<br/>
&nbsp;&nbsp;    : prround pabre EXPRESION pcierra        
;

FTYPEOF<br/>
&nbsp;&nbsp;    : prtypeof pabre EXPRESION pcierra        
;

</p>
