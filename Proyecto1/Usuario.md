# Proyecto 1
## Manual de Usuario
### Introducción
<p style="text-align: justify;">
Dentro del primer proyecto del laboratorio de Organización de Lenguajes  & Compiladores 1 se mostrará el modo en el que funciona el programa mediante una interfáz gráfica y sus subfunciones.

Se empleo el lenguaje de programación Java para la creación de la interfáz gráfica del programa en el que tiene diferentes funciones en el mismo para lo que es el análisis, la generación y otros más para su respectivo funcionamiento; se utilizo un software para la graficación de los arboles y tablas.

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


>- **_Versioó CUP:_** 11-b
>- **_Versión CUP-Runtime:_** 11-b
>- **_Versión Gson:_** 2.8.9
>- **_Versión JFlex:_** 1.9.0
>- **_Versión json-simple:_** 1.1.1

### Funcionamiento del programa

>Inicio

</br>

El programa al correr lee el directorio de reportes, para ver si hay imagenes de anteriores veces que se haya utilizado y generado gráficas de las expresiones regulares y en caso de que no estén vacias las carpetas las agrega al menu de expresiones retulares para visualizarlas.

![](../Assets/Proyecto1/Manual%20Usuario/Inicio.png)
Programa Abierto cuando tiene archivos de imagenes anteriores

</br>

![](../Assets/Proyecto1/Manual%20Usuario/Inicio2.png)
Programa Abierto cuando no tiene archivos de imagenes anteriores

</br>

A la hora de querer abrir un fichero, abre una carpeta en el directoiro de "Archivos" en el que estarán los archivos de prueba que se usarán (todos los archivos que abre son de extención "olc")

![](../Assets/Proyecto1/Manual%20Usuario/abrir.png)

</br>

Una vez abierto el archivo lo muestra en la pantalla de texto lo que tiene el archivo.

![](../Assets/Proyecto1/Manual%20Usuario/abrir2.png)

</br>

En caso de querer cambiar el archivo y guardarlo se pueden agregar más líneas (en caso de que uno requiera o quiera agregar más y se utiliza la opción de guardar "Save"), mostrando en consola que se guardo el archivo.

![](../Assets/Proyecto1/Manual%20Usuario/Guardar.png)

</br>

En el caso de querer guardar el archivo como uno nuevo se utiliza la opción "Save As", abriendo una pestaña emergente para guardar el archivo (siempre al guardar poner la extención)

![](../Assets/Proyecto1/Manual%20Usuario/SaveAs.png)

</br>

![](../Assets/Proyecto1/Manual%20Usuario/SaveAs2.png)

</br>

En el caso para crear un nuevo archivo se utiliza la opción "nuevo" limpiando la pantalla de texto y el label que muestra la ruta.

![](../Assets/Proyecto1/Manual%20Usuario/Nuevo.png)

</br>

Ahora bien para analizar el archivo y ver graficas y todo lo demas, se requiere un archivo abierto, con la opción de "Analizar" analizará el archivo linea por linea mostrando si tiene error alguno o no, tambien agregará en la lista las expresiones regulares en caso de no haber errores.

![](../Assets/Proyecto1/Manual%20Usuario/analizar.png)

</br>

Ahora tocará generar los automatas para poder visualizarlos </br>
En la consola mostrará al generar si las cadenas fueron aceptadas con respecto a las Expresiones Regulares correspondientes.

![](../Assets/Proyecto1/Manual%20Usuario/Generar.png)

</br>

Como se puede ver se actualizo el arbol de archivos que muestra que si tienen archivos las carpetas y tambien ya se pueden visualizar las imagenes.

![](../Assets/Proyecto1/Manual%20Usuario/vis.png)

</br>

Con la opción de "abrir carpeta directorio" abre la carpeta "Archivos" que es la que tiene los archivos de prueba

![](../Assets/Proyecto1/Manual%20Usuario/archivos.png)

</br>

Y por último esta la opción para limpiar y borrar todos los archivos de la carpeta de los reportes.


![](../Assets/Proyecto1/Manual%20Usuario/borrar.png)

</br>

![](../Assets/Proyecto1/Manual%20Usuario/SalidaJSON.png)
archivo de salida JSON

</br>

### Conclusiones

- La utilización de expresiones regulares ayuda en la simplificación y obtención de mejor forma de expresiones que uno quiere obtener.
- El análisis sintáctico sirve para obtener reglas de el funcionamiento del léxico que uno creó, para el análisis de un archivo.

### Recomendaciones

- Leer el manual antes de usar el programa.