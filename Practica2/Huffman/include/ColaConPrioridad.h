/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef SRC_COLACONPRIORIDAD_H_
#define SRC_COLACONPRIORIDAD_H_
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <map>

#include "Nodos.h"
#include "freq_table.h"

using namespace std;

/**
 *
 */

class ColaConPrioridad {
	private:
	unsigned int numElementos;			// Num de elementos dentro de la ColaConPrioridad
	NodoCola *primero;  					// Apunta a el objeto con menor numApariciones
	void encolarAlgoritmo(NodoCola *nuevoNodo, unsigned int numApariciones);

	public:
	ColaConPrioridad();
	unsigned int getNumElementos();
	void encolar_Hoja(unsigned char c, unsigned int numApariciones);
	void encolar_Rama(NodoTrie* nodoTrie, unsigned int numApariciones);
	void encolarTabla(NodoBucketTF** tabla);
	void desencolar();
	NodoCola* getPrimero();
	void muestraColaConPrioridad();
	void vaciaColaConPrioridad();
};

#endif /* SRC_COLACONPRIORIDAD_H_ */
