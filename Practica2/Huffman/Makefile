#**********************************************************************
# Archivo: Makefile Practica_1
# Author:  Ruben Rodriguez Esteban 737215
#          Jose Maria Vallejo Puyal 720044
# Fecha:   23 de marzo de 2019
# Coms:    Fichero MakeFile para compilar la practica_1   
#          Para compilar y ejecutar seguir estos pasos en terminal
#          1 - Make clean
#          2 - Make
#          3 - Para comprimir fichero: huf -c <nombre de fichero>
#            - Para descomprimir fichero: huf -d <nombre de fichero>.huf
#*********************************************************************

# Seleccion del compilador
CC=g++ -O3

# Expansion de variables

CARFREC_DIR=CarFrec
PRELIMINAR_DIR=Preliminar
ARBOLTRIE_DIR=ArbolTrie
HEAP_DIR=Heap
HUFFMAN_DIR=Huffman

CARFREC=${CARFREC_DIR}/CarFrec
PRELIMINAR=${PRELIMINAR_DIR}/Preliminar
ARBOLTRIE=${ARBOLTRIE_DIR}/ArbolTrie
HEAP=${HEAP_DIR}/Heap
HUFFMAN=${HUFFMAN_DIR}/Huffman
PRUEBAS=huf

# Flags de compilacion
CPPFLAGS=-std=c++11 -Wall

# Regla para compilar el programa principal
all: ${PRUEBAS} 


# CARFREC
# Compilacion
${CARFREC}.o: ${CARFREC}.h  ${CARFREC}.cc
	${CC} -c ${CPPFLAGS} ${CARFREC}.cc -o ${CARFREC}.o
#----------------------------------------------------------------


# PRELIMINAR
# Compilacion
${PRELIMINAR}.o: ${PRELIMINAR}.h  ${PRELIMINAR}.cc
	${CC} -c ${CPPFLAGS} ${PRELIMINAR}.cc -o ${PRELIMINAR}.o
#---------------------------------------------------------------


# ARBOLTRIE
# Compilacion
${ARBOLTRIE}.o: ${ARBOLTRIE}.h ${ARBOLTRIE}.cc
	${CC} -c ${CPPFLAGS} ${ARBOLTRIE}.cc -o ${ARBOLTRIE}.o
#--------------------------------------------------------------

# HEAP
# Compilacion
${HEAP}.o: ${HEAP}.h ${HEAP}.cc
	${CC} -c ${CPPFLAGS} ${HEAP}.cc -o ${HEAP}.o
#--------------------------------------------------------------


# HUFFMAN
# Compilacion
${HUFFMAN}.o: ${HUFFMAN}.h ${HUFFMAN}.cc
	${CC} -c ${CPPFLAGS} ${HUFFMAN}.cc -o ${HUFFMAN}.o
#--------------------------------------------------------------

# PRUEBAS
# Compilacion
${PRUEBAS}.o: ${PRUEBAS}.cpp
	${CC} -c ${CPPFLAGS} ${PRUEBAS}.cpp

# Linkado
${PRUEBAS}: ${PRELIMINAR}.o ${HUFFMAN}.o ${CARFREC}.o ${ARBOLTRIE}.o ${HEAP}.o ${PRUEBAS}.o  
	${CC} ${PRELIMINAR}.o ${HUFFMAN}.o ${CARFREC}.o ${ARBOLTRIE}.o ${HEAP}.o ${PRUEBAS}.o -o ${PRUEBAS}
#---------------------------------------------------------------

# LIMPIEZA
clean:
	$(RM) ${CARFREC}.o
	$(RM) ${PRELIMINAR}.o
	$(RM) ${ARBOLTRIE}.o
	$(RM) ${HUFFMAN}.o
	$(RM) ${HEAP}.o
	$(RM) ${PRUEBAS} ${PRUEBAS}.o
