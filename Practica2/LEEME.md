Peñasco Estívalez, Víctor Miguel	741294@unizar.es	a741294
Rodríguez Esteban, Rubén	737215@unizar.es	a737215

# APD - Práctica 2

## Estructura del programa

La práctica se ha desarrollado en el lenguaje de programación Java,
aunque el compresor Huffman se encuentra implementado en C++.

El programa se estructura en cuatro carpetas y varios scripts:

* bin - Directorio que contiene los binarios Java.
* src - Directorio que contiene el código Java (Arrays de sufijos, BWT y Move to front).
* Huffman - Directorio que contiene el código C++ (Huffman).
* test - Directorio que contiene los ficheros de prueba.
* compile.sh - Script que simplifica la compilación de todos los fuentes.
* bzip3.sh - Script que permite comprimir o descomprimir un fichero.
* test_bzip3.sh - Script que permite automatizar la comprobación de la corrección del compresor sobre un fichero.
* ejecutar2.sh - Script que ejecuta los casos de prueba especificados en la memoria.

  
## Ejecución de pruebas automáticas

Para automatizar la compilación y ejecución de los casos de prueba
descritos en la memoria se provee el script ejecutar2.sh.

Antes de ejecutarlo es posible que haya que cambiar los permisos de
ejecución. Para hacerlo en sistemas Unix se utiliza los siguientes comandos:

```
chmod u+x bzip3.sh
chmod u+x compile.sh
chmod u+x test_bzip3.sh
chmod u+x ejecutar2.sh
```

Para ejecutar el script simplemente se usa este comando:

```
./ejecutar2.sh
```

Si se deseea ejecutar el compresor directamente se puede utilizar el siguiente comando:

```
./bzip3.sh <-c | -u> <file>
```

* -c indica utilizar el algoritmo de compresión
* -u indica utilizar el algoritmo de descompresión
* file indica el fichero a comprimir o descomprimir