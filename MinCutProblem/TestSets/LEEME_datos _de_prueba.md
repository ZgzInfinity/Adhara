Peñasco Estívalez, Víctor Miguel    741294@unizar.es    a741294
Rodríguez Esteban, Rubén    737215@unizar.es a737215

# APD - Práctica 1 - Generación de datos de prueba

## Descripción

La generación de los casos de prueba se ha efectuado de la siguiente forma:

Se ha utilizado un script en python 3 que genera matrices de componentes booleanas 
de acuerdo a las restricciones del enunciado. La matriz se procede a rellenar 
recorriéndola de forma triangular generando valores pseudoaleatorios y asignándolos a 
la componente que toca junto con su componente simétrica. 

La generación de dichos valores se efectúa de forma pseudoaleatoria con ayuda de 
las librerías de python **numpy** y **random**. 

Este script genera por defecto una  serie conjuntos de prueba con tamaño y valores
pseudoaleatorios, otro conjunto con una matriz identidad (ningún producto se ha
comprado con otro) y otro conjunto con una matriz rellenada con todo 1's (todos los
productos se han comprado alguna vez a la vez).

## Ejecución del script

Para ejecutar el script hay que cumplir los requisitos previos:

* Tener instalado python3.
* Importar la librería **numpy** con el siguiente comando:

```
pip3 install numpy
```

La librería **random** ya está instalada por defecto.

El script se ejecuta por medio del siguiente comando:

```
python3 gen_test_cases.py [<num_conjuntos_pseudoaleatorios> <tamaño_mínimo> <tamaño_máximo>]
```
donde:

* La ejecución sin parámetros genera por defecto un conjunto de prueba con valores 
  pseudoaleatorios, otro conjunto con matriz identidad y otro conjunto con matriz con
  todo 1's. Los tamaños por defecto se encuentran por defecto en el rango [50, 85].

* La ejecución con parámetros genera tantos conjuntos pseudoaleatorios como se haya 
  especificado en el primer parámetro, otro conjunto con matriz identidad y otro
  conjunto con una matriz con todo 1's. Los tamaños mínimos y máximos están
  especificados en el segundo y tercer parámetro, respectivamente.
  