# Proyecto 1
## Manual de Usuario
### Introducción
<p style="text-align: justify;">
Dentro del primer proyecto del laboratorio de Organización de Lenguajes & Compiladores 1 se mostrará el modo en el que funciona el programa mediante una interfaz gráfica y sus subfunciones.

Se empleo el lenguaje de programación Java para la creación de la interfaz gráfica del programa en el que tiene diferentes funciones en el mismo para lo que es el análisis, la generación y otros más para su respectivo funcionamiento; se utilizo un software para la graficación de los arboles y tablas.

Se pudo determinar que la implementación de las estructuras y el almacenamiento y/o arreglo de la información obtenida en cada una de ellas no mostro ningún problema durante su ejecución.</p>


### Requisitos del Sistema


>- **_Sistema Operativo:_** Windows 7 o superior
>- **_CPU:_** Intel Pentium D o AMD Athlon 64 (K8) 2.6GHz. (Requisitos Mínimo)
>- **_RAM:_** 600MB
>- **_Lenguaje Utilizado:_** Java
>- **_IDE:_** NetBeans 16
>- **_JDK:_** 19.0 (Open JDK)
>- **_Versión Java:_** 19.0

### Requisitos En el Proyecto (Dependencias Proyecto hecho en MAVEN)


>- **_Versión CUP:_** 11-b
>- **_Versión CUP-Runtime:_** 11-b
>- **_Versión Gson:_** 2.8.9
>- **_Versión JFlex:_** 1.9.0
>- **_Versión json-simple:_** 1.1.1

### Funcionamiento del programa

>Inicio del Programa

</br>

El programa al correr lee el directorio de reportes, para ver si hay imágenes de anteriores veces que se haya utilizado y generado gráficas de las expresiones regulares y en caso de que no estén vacías las carpetas las agrega al menú de expresiones regulares para visualizarlas.

![](../Assets/Proyecto1/Manual%20Usuario/Inicio.png)
Programa Abierto cuando tiene archivos de imágenes anteriores.

</br>

![](../Assets/Proyecto1/Manual%20Usuario/Inicio2.png)
Programa Abierto cuando no tiene archivos de imágenes anteriores.

</br>

>Abrir Fichero

A la hora de querer abrir un fichero, abre una carpeta en el directorio de "Archivos" en el que estarán los archivos de prueba que se usarán (todos los archivos que abre son de extensión "olc").

![](../Assets/Proyecto1/Manual%20Usuario/abrir.png)

</br>

Una vez abierto el archivo lo muestra en la pantalla de texto lo que tiene el archivo.

![](../Assets/Proyecto1/Manual%20Usuario/abrir2.png)

</br>

>Guardar Archivo

En caso de querer cambiar el archivo y guardarlo se pueden agregar más líneas (en caso de que uno requiera o quiera agregar más y se utiliza la opción de guardar "Save"), mostrando en consola que se guardó el archivo.

![](../Assets/Proyecto1/Manual%20Usuario/Guardar.png)

</br>

En el caso de querer guardar el archivo como uno nuevo se utiliza la opción "Save As", abriendo una pestaña emergente para guardar el archivo (siempre al guardar poner la extensión).

![](../Assets/Proyecto1/Manual%20Usuario/SaveAs.png)

</br>

![](../Assets/Proyecto1/Manual%20Usuario/SaveAs2.png)

</br>

>Archivo Nuevo

En el caso para crear un nuevo archivo se utiliza la opción "nuevo" limpiando la pantalla de texto y el label que muestra la ruta.

![](../Assets/Proyecto1/Manual%20Usuario/Nuevo.png)

</br>

>Analizar Archivo & Generar

Ahora bien, para analizar el archivo y ver gráficas y todo lo demás, se requiere un archivo abierto, con la opción de "Analizar" analizará el archivo línea por línea mostrando si tiene error alguno o no, también agregará en la lista las expresiones regulares en caso de no haber errores.

![](../Assets/Proyecto1/Manual%20Usuario/analizar.png)

</br>

Ahora tocará generar los autómatas para poder visualizarlos </br>
En la consola mostrará al generar si las cadenas fueron aceptadas con respecto a las Expresiones Regulares correspondientes.

![](../Assets/Proyecto1/Manual%20Usuario/Generar.png)

</br>

>Otros

Como se puede ver se actualizo el árbol de archivos que muestra que si tienen archivos las carpetas y también ya se pueden visualizar las imágenes.

![](../Assets/Proyecto1/Manual%20Usuario/vis.png)

</br>

Con la opción de "abrir carpeta directorio" abre la carpeta "Archivos" que es la que tiene los archivos de prueba.

![](../Assets/Proyecto1/Manual%20Usuario/archivos.png)

</br>

Y por último esta la opción para limpiar y borrar todos los archivos de la carpeta de los reportes.


![](../Assets/Proyecto1/Manual%20Usuario/borrar.png)

</br>

![](../Assets/Proyecto1/Manual%20Usuario/SalidaJSON.png)
archivo de salida JSON

</br>

### Funciones del Programa

#### File
>- Nuevo: Crea un nuevo archivo con extención olc.
>- Open: Abre un menu desplegable en la carpeta de archivos para abrir archivos de extención olc. 
>- Save: Guarda el achivo con extención olc.
>- Save As: Guarda un nuevo archivo con extención olc.
>- Exit: Sale del programa.

</br>

#### Generate & Analize
>- Analizar: Analiza el archivo abierto, en caso de haber errores los agrega a la tabla de errores.
>- Generar: Genera los grafos de las expresiones regulares, el archivo de salida y muestra cuales cadenas son aceptadas y cuales cadenas no son aceptadas. 
>- Borrar archivos Directorios: Borra todos los archivos del directorio para poner nuevos.
>- Abrir Directorio Archivos Prueba: Abre la carpeta en donde están los archivos de prueba, ya sea para agregar o para borrar archivos.

</br>

### Conclusiones

- La utilización de expresiones regulares ayuda en la simplificación y obtención de mejor forma de expresiones que uno quiere obtener.
- El análisis sintáctico sirve para obtener reglas del funcionamiento del léxico que uno creó, para el análisis de un archivo.


### Recomendaciones

- Leer el manual antes de usar el programa.