# Proyecto 1
## Gramática
------------------------------------------------
<p style="text-align: justify;">

ini<br/>
&nbsp;&nbsp;    : ENTRADA EOF  
&nbsp;&nbsp;    | error EOF<br/>
;<br/><br/>

ENTRADA<br/>
&nbsp;&nbsp;    : ENTRADA ENTCERO                   
&nbsp;&nbsp;    | ENTCERO<br/>
;<br/><br/>

ENTCERO<br/>
&nbsp;&nbsp;    : FUNCIONBODY     
&nbsp;&nbsp;    | METODOBODY     
&nbsp;&nbsp;    | EXECBODY         
&nbsp;&nbsp;    | DEC_VAR         
&nbsp;&nbsp;    | DEC_VECT        
&nbsp;&nbsp;    | DEC_LIST<br/>
;<br/><br/>

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
&nbsp;&nbsp;    | TIPO_LIST error lcierra<br/>
;<br/><br/>

METODOBODY<br/>
&nbsp;&nbsp;    : prvoid id pabre pcierra labre INSTRUCCION lcierra                   
&nbsp;&nbsp;    | prvoid id pabre pcierra labre lcierra                              
&nbsp;&nbsp;    | prvoid id pabre LISTAPARAMETROS pcierra labre INSTRUCCION lcierra      
&nbsp;&nbsp;    | prvoid id pabre LISTAPARAMETROS pcierra labre lcierra                  
&nbsp;&nbsp;    | prvoid error lcierra<br/>
;<br/><br/>


EXECBODY<br/>
&nbsp;&nbsp;    : premain id pabre pcierra ptcoma                     
&nbsp;&nbsp;    | premain id pabre LISTAVALORES pcierra ptcoma           
&nbsp;&nbsp;    | premain error ptcoma<br/>
;<br/><br/>

LISTAPARAMETROS<br/>
&nbsp;&nbsp;    : LISTAPARAMETROS coma PARAMETROS  
&nbsp;&nbsp;    | PARAMETROS<br/>
;<br/><br/>

PARAMETROS<br/>
&nbsp;&nbsp;    : TIPO_VECT id   
&nbsp;&nbsp;    | TIPO_LIST id     
&nbsp;&nbsp;    | TIPO id<br/>
;<br/><br/>

INSTRUCCION<br/>
&nbsp;&nbsp;    : INSTRUCCION INSCERO      
&nbsp;&nbsp;    | INSCERO<br/>
;<br/><br/>

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
&nbsp;&nbsp;    | error lcierra<br/>
;<br/><br/>

SENTENCIATRANSFERENCIA<br/>
&nbsp;&nbsp;    : prbreak ptcoma              
&nbsp;&nbsp;    | prreturn EXPRESION ptcoma     
&nbsp;&nbsp;    | prcontinue ptcoma          
&nbsp;&nbsp;    | prreturn ptcoma<br/>
;<br/><br/>

SENTENCIACICLO<br/>
&nbsp;&nbsp;    : WHILE       
&nbsp;&nbsp;    | FOR        
&nbsp;&nbsp;    | DOWHILE<br/>
;<br/><br/>

WHILE<br/>
&nbsp;&nbsp;    : prwhile pabre EXPRESION pcierra labre INSTRUCCION lcierra    
&nbsp;&nbsp;    | prwhile pabre EXPRESION pcierra labre lcierra             
&nbsp;&nbsp;    | prwhile error lcierra<br/>
;<br/><br/>

FOR<br/>
&nbsp;&nbsp;    : prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre INSTRUCCION lcierra    
&nbsp;&nbsp;    | prfor pabre DEC_VAR EXPRESION ptcoma ACTUALIZACION pcierra labre lcierra                 
&nbsp;&nbsp;    | prfor error lcierra<br/>
;<br/><br/>

ACTUALIZACION<br/>
&nbsp;&nbsp;    : id igual EXPRESION 
&nbsp;&nbsp;    | id incremento 
&nbsp;&nbsp;    | id decremento<br/>
;<br/><br/>

DOWHILE<br/>
&nbsp;&nbsp;    : prdo labre INSTRUCCION lcierra prwhile pabre EXPRESION pcierra ptcoma    
&nbsp;&nbsp;    | prdo labre lcierra prwhile pabre EXPRESION pcierra ptcoma            
&nbsp;&nbsp;    | prdo error ptcoma<br/>
;<br/><br/>

SENTENCIACONTROL<br/>
&nbsp;&nbsp;    : CONTROLIF    
&nbsp;&nbsp;    | SWITCH<br/>
;<br/><br/>

CONTROLIF<br/>
&nbsp;&nbsp;    : IF                   
&nbsp;&nbsp;    | IFELSE               
&nbsp;&nbsp;    | ELSEIF               
&nbsp;&nbsp;    | prif error lcierra<br/>
;<br/><br/>

IF<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra<br/>
;<br/><br/>

IFELSE<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre INSTRUCCION lcierra   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse labre INSTRUCCION lcierra              
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse labre lcierra               
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse labre lcierra<br/>
;<br/><br/>

ELSEIF<br/>
&nbsp;&nbsp;    : prif pabre EXPRESION pcierra labre INSTRUCCION lcierra prelse CONTROLIF   
&nbsp;&nbsp;    | prif pabre EXPRESION pcierra labre lcierra prelse CONTROLIF<br/>
;<br/><br/>

SWITCH<br/>
&nbsp;&nbsp;    : prswitch pabre EXPRESION pcierra labre CASESLIST DEFAULT lcierra    
&nbsp;&nbsp;    | prswitch pabre EXPRESION pcierra labre CASESLIST lcierra         
&nbsp;&nbsp;    | prswitch pabre EXPRESION pcierra labre DEFAULT lcierra           
&nbsp;&nbsp;    | prswitch error lcierra<br/>
;<br/><br/>

CASESLIST<br/>
&nbsp;&nbsp;    : CASESLIST prcase EXPRESION dospuntos INSTRUCCION     
&nbsp;&nbsp;    | CASESLIST prcase EXPRESION dospuntos                 
&nbsp;&nbsp;    | prcase EXPRESION dospuntos INSTRUCCION            
&nbsp;&nbsp;    | prcase EXPRESION dospuntos                            
&nbsp;&nbsp;    | prcase error dospuntos<br/>
;<br/><br/>

DEFAULT<br/>
&nbsp;&nbsp;    : prdefault dospuntos INSTRUCCION        
&nbsp;&nbsp;    | prdefault dospuntos<br/>
;<br/><br/>

DEC_VAR<br/>
&nbsp;&nbsp;    : TIPO id igual EXPRESION ptcoma   
&nbsp;&nbsp;    | TIPO id ptcoma                  
&nbsp;&nbsp;    | id igual EXPRESION ptcoma        
&nbsp;&nbsp;    | id incremento ptcoma        
&nbsp;&nbsp;    | id decremento ptcoma            
&nbsp;&nbsp;    | TIPO error ptcoma<br/>
;<br/><br/>

DEC_VECT<br/>
&nbsp;&nbsp;    : TIPO_VECT id igual prnew TIPO cabre EXPRESION ccierra ptcoma       
&nbsp;&nbsp;    | TIPO_VECT id igual labre LISTAVALORES lcierra ptcoma            
&nbsp;&nbsp;    | id cabre EXPRESION ccierra igual EXPRESION ptcoma         
&nbsp;&nbsp;    | TIPO_VECT id igual EXPRESION ptcoma                   
&nbsp;&nbsp;    | TIPO_VECT error ptcoma<br/>
;<br/><br/>

VALORES<br/>
&nbsp;&nbsp;    : EXPRESION<br/>
;<br/><br/>

DEC_LIST<br/>
&nbsp;&nbsp;    : TIPO_LIST id igual prnew prlist menor TIPO mayor ptcoma            
&nbsp;&nbsp;    | id punto pradd pabre EXPRESION pcierra ptcoma                   
&nbsp;&nbsp;    | id cabre cabre EXPRESION ccierra ccierra igual EXPRESION ptcoma      
&nbsp;&nbsp;    | TIPO_LIST id igual EXPRESION ptcoma                     
&nbsp;&nbsp;    | TIPO_LIST error ptcoma<br/>
;<br/><br/>

TIPO_VECT<br/>
&nbsp;&nbsp;    : TIPO cabre ccierra<br/>
;<br/><br/>

TIPO_LIST<br/>
&nbsp;&nbsp;    : prlist menor TIPO mayor<br/>
;<br/><br/>

TIPO<br/>
&nbsp;&nbsp;    : TIPODATO<br/>
;<br/><br/>

TIPODATO<br/>
&nbsp;&nbsp;    : prstring      
&nbsp;&nbsp;    | printeger    
&nbsp;&nbsp;    | prdouble    
&nbsp;&nbsp;    | prchar       
&nbsp;&nbsp;    | prboolean<br/>
;<br/><br/>

FTOSTRING<br/>
&nbsp;&nbsp;    : prtoString pabre EXPRESION pcierra<br/>
;<br/><br/>

LLAMADA<br/>
&nbsp;&nbsp;    : id pabre LISTAVALORES pcierra     
&nbsp;&nbsp;    | id pabre pcierra<br/>
;<br/><br/>

LISTAVALORES<br/>
&nbsp;&nbsp;    : LISTAVALORES coma VALORES 
&nbsp;&nbsp;    | VALORES<br/>
;<br/><br/>

CASTEO<br/>
&nbsp;&nbsp;    : pabre TIPO pcierra EXPRESION<br/>
;<br/><br/>

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
&nbsp;&nbsp;    | FUNCIONESRESERVADAS<br/>
;<br/><br/>

TERNARIO<br/>
&nbsp;&nbsp;    : EXPRESION interrogacion EXPRESION dospuntos EXPRESION<br/>
;<br/><br/>

FUNCIONESRESERVADAS<br/>
&nbsp;&nbsp;    : FTOLOWER        
&nbsp;&nbsp;    | FTOUPPER       
&nbsp;&nbsp;    | FLENGTH          
&nbsp;&nbsp;    | FTRUNCATE      
&nbsp;&nbsp;    | FROUND       
&nbsp;&nbsp;    | FTYPEOF           
&nbsp;&nbsp;    | FTOSTRING        
&nbsp;&nbsp;    | FTOCHARARRAY<br/>
;<br/><br/>

FPRINT<br/>
&nbsp;&nbsp;    : prprint pabre EXPRESION pcierra ptcoma    
&nbsp;&nbsp;    | prprint pabre pcierra ptcoma            
&nbsp;&nbsp;    | prprint error ptcoma<br/>
;<br/><br/>

FTOLOWER<br/>
&nbsp;&nbsp;    : prtoLower pabre EXPRESION pcierra<br/>
;<br/><br/>

FTOUPPER<br/>
&nbsp;&nbsp;    : prtoUpper pabre EXPRESION pcierra<br/>
;<br/><br/>

FTOCHARARRAY<br/>
&nbsp;&nbsp;    : prtoCharArray pabre EXPRESION pcierra<br/>
;<br/><br/>

FLENGTH<br/>
&nbsp;&nbsp;    : prlength pabre EXPRESION pcierra<br/>
;<br/><br/>

FTRUNCATE<br/>
&nbsp;&nbsp;    : prtruncate pabre EXPRESION pcierra<br/>
;<br/><br/>

FROUND<br/>
&nbsp;&nbsp;    : prround pabre EXPRESION pcierra<br/>
;<br/><br/>

FTYPEOF<br/>
&nbsp;&nbsp;    : prtypeof pabre EXPRESION pcierra<br/>
;

</p>
