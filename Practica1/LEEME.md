Peñasco Estívalez, Víctor Miguel	741294@unizar.es	a741294
Rodríguez Esteban, Rubén	737215@unizar.es	a737215

# APD - Práctica 1

## Estructura del programa

La práctica se ha desarrollado en el lenguaje de programación Java.

El programa se estructura en cuatro ficheros:

* Product - TAD de producto.
* Node - TAD de nodo (vértice del grafo).
* Edge - TAD de arista.
* Mincut - Clase principal que contiene el código de los algoritmos.

## Ejecución del programa

Para ejecutar el programa primero se tienen que compilar los ficheros:

```
javac Mincut/src/*.java
```

Para ejecutar el programa se necesitan los siguientes argumentos:

```
java -classpath Mincut/src Mincut <-k | -ks> <-rg1 | -rg2 | -rg3> <NUM_ATTEMPTS> <matrixFile> [<productsFile>]
```

* -k indica utilizar el algoritmo de Karger
* -ks indica utilizar el algoritmo de Karger-Stein
* -rg1 indica usar utilizar el generador números pseudoaleatorios Random.nextInt
* -rg2 indica usar utilizar el generador números pseudoaleatorios Random.ints
* -rg3 indica usar utilizar el generador números pseudoaleatorios Math.random

  
## Pruebas automáticas

Para automatizar la compilación y ejecución de los casos de prueba
descritos en la memoria se provee el script ejecutar1.sh.

Antes de ejecutarlo es posible que haya que cambiar los permisos de
ejecución. Para hacerlo en sistemas Unix se utiliza el siguiente comando:

```
chmod u+x ejecutar1.sh
```

Para ejecutar el script simplemente se usa este comando:

```
./ejecutar1.sh
```