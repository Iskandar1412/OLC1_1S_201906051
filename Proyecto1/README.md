# Manual Técnico
### Introducción
&nbsp;
```
Dentro del primer proyecto del laboratorio de Organización de Lenguajes & Compiladores 1 la aplicación realizada fue en base al uso
de JFlex y Cup en el lenguaje de programación Java, abordando los conceptos de gramáticas y expresiones regulares para la creación de
lo que es el análisis léxico y sintáctico para analizar por consiguiente las cadenas o el archivo de prueba respectivo, la utilización
de listas enlazadas para el almacenamiento de los errores, & tokens que son almacenados para su utilización en los que son los árboles
y tablas graficadas posteriormente; utlizando asimismo el modo gráfico para obtener aparte de los archivos, generar, graficas, nuevos
documentos y observar las imagenes de las expresiones regulares aceptadas.

Se empleo el lenguaje de programación Java como herramienta en la función de las estructuras y analisis, para almacenar la información
que la interfaz gráfica le enviaba, y la interfaz
gráfica se utilizaba para la obtención y envío de información a las estructuras/actualización de tokens y realización gráficas; se
utilizó Graphviz para graficar las estructuras de las tablas y árboles.

Se pudo determinar que la implementación de las estructuras y el almacenamiento y/o arreglo de la información obtenida en cada una
de ellas no mostro ningún problema durante su ejecución.
```

&nbsp;
### Requisitos del Sistema
&nbsp;

>- **_Sistema Operativo:_** Windows 7 o superior
>- **_CPU:_** Intel Pentium D o AMD Athlon 64 (K8) 2.6GHz. (Requisitos Mínimo)
>- **_RAM:_** 600MB
>- **_Lenguaje Utilizado:_** Java
>- **_IDE:_** NetBeans 16
>- **_JDK:_** 19.0 (Open JDK)
>- **_Versión Java:_** 19.0
&nbsp;
### Requisitos En el Proyecto (Dependencias Proyecto hecho en MAVEN)
&nbsp;

>- **_Versioó CUP:_** 11-b
>- **_Versión CUP-Runtime:_** 11-b
>- **_Versión Gson:_** 2.8.9
>- **_Versión JFlex:_** 1.9.0
>- **_Versión json-simple:_** 1.1.1

&nbsp;
### Explicación del Código
#### Analisis Léxico
&nbsp;
```
Utiliza las siguientes expresiones regulares para obtener los parámetros a la hora de leer el archivo, obteniendo los errores
léxicos que tenga el archivo.
```
&nbsp;
![](../Assets/lexico.png)
&nbsp;
#### Análisis Sintáctico
```
Se encarga de obtener los errores sintácticos y mediante una gramática regular cada en cada producción obtener los datos
requeridos.
```
&nbsp;
![](../Assets/sintactico.png)
&nbsp;

