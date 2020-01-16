#!/bin/bash

#-----------------------------------
# Autores:
# Ruben Rodriguez Esteban - 737215
# Jose Maria Vallejo Puyal - 720004
#-----------------------------------


# Comprobar el numero de parametros
if [ $# -ne 2 ]
then
	# El numero de parametros es incorrecto
	echo "Invocar script como: ./profitChecker.sh <fichero1> <fichero2>"
	echo "<fichero1> es el fichero de resultados del algoritmo de ramificacion y poda"
	echo "<fichero2> es el fichero de resultados del algoritmo de fuerza bruta"

	# Fin de ejecucion codigo de error
	exit 1
fi

# Numero de argumentos correcto

# Evaluación de si el primer fichero existe o no
if [ ! -f $1 ]
then
      # El fichero introducido como primer parametro no existe
      echo "El fichero de nombre $1 no existe"

	  # Fin de ejecucion con segundo codigo de error
	  exit 2
fi

# Evaluación de si el segundo fichero existe o no
if [ ! -f $2 ]
then
      # El fichero introducido como primer parametro no existe
      echo "El fichero de nombre $2 no existe"

	  # Fin de ejecucion con segundo codigo de error
	  exit 3
fi

# Los ficheros pasados como parametro existen
# Comprobar si los beneficios obtenidos por ambos ficheros son identicos

# Creacion de un fichero temporal para guardar los beneficios obtenidos con el algoritmo de poda
ficheroPoda=$(mktemp resultadoPodaXXX)

# Creacion de un fichero temporal para guardar los beneficios obtenidos con el algoritmo de fuerza bruta
ficheroFuerzaBruta=$(mktemp resultadoFuerzaBrutaXXX)

# Guardado de la columna de beneficios calculados con la ramificacion y poda en el fichero ficheroPoda
awk '{print $1}' $1 > $ficheroPoda

# Guardado de la columna de beneficios calculados con la fuerza bruta en el fichero ficheroFuerzaBruta
awk '{print $1}' $2 > $ficheroFuerzaBruta

# Comparar ambos ficheros
diff -y $ficheroPoda $ficheroFuerzaBruta

# Borrar los ficheros temporales
rm -f $ficheroPoda
rm -f $ficheroFuerzaBruta