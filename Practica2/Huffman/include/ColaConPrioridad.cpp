/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#include "ColaConPrioridad.h"
#define nullptr NULL

using namespace std;

/**
 *	Construcctor de la ColaConPrioridad
 */
ColaConPrioridad::ColaConPrioridad() {
	numElementos = 0;
	primero = nullptr;
}

/**
 *	Get número de elementos de la ColaConPrioridad
 */
unsigned int ColaConPrioridad::getNumElementos(){
	return numElementos;
}


/**
 *	Algoritmo usado para encolar tipo de datos NodoCola. introduce un elemento en la
 *	ColaConPrioridad de forma ordenada por numApariciones
 */
void ColaConPrioridad::encolarAlgoritmo(NodoCola *nuevoNodo,
		unsigned int numApariciones) {
	// Sumamos un objeto a la cola:
	numElementos += 1;

	//Si la cola se encuentra vacía:
	if (primero == nullptr) {
		primero = nuevoNodo;
		nuevoNodo->siguiente = nullptr;
	}
	// Si el primer elemento de la cola tiene más apariciones que el que estamos introduciendo:
	else if (primero->numApariciones >= numApariciones) {
		nuevoNodo->siguiente = primero;
		primero = nuevoNodo;
	} else {
		// Si el primer elemento de la cola tiene menos apariciones que
		// el que estamos introduciendo, recorremos la cola:
		NodoCola *nodoAMirar = primero;
		while (nodoAMirar->siguiente != nullptr) {
			// Si el siguiente tiene más apariciones insertamos en la posición actual:
			if (nodoAMirar->siguiente->numApariciones >= numApariciones) {
				nuevoNodo->siguiente = nodoAMirar->siguiente;
				nodoAMirar->siguiente = nuevoNodo;
				return;
			}
			nodoAMirar = nodoAMirar->siguiente;
		}
		// Si todos tienen menos apariciones insertamos el último:
		nodoAMirar->siguiente = nuevoNodo;
		nuevoNodo->siguiente = nullptr;

	}
}

/**
 *	Introduce un elemento en la ColaConPrioridad de forma ordenada por numApariciones
 */
void ColaConPrioridad::encolar_Hoja(unsigned char c,
		unsigned int numApariciones) {
	NodoCola_Hoja *nuevoNodo = new NodoCola_Hoja(c, numApariciones);
	//nuevoNodo->c = c;
	//nuevoNodo->numApariciones = numApariciones;

	encolarAlgoritmo(nuevoNodo, numApariciones);
}

/**
 *	Introduce un elemento en la ColaConPrioridad de forma ordenada por numApariciones
 */
void ColaConPrioridad::encolar_Rama(NodoTrie* nodoTrie,
		unsigned int numApariciones) {
	NodoCola_Rama *nuevoNodo = new NodoCola_Rama(nodoTrie, numApariciones);
	//nuevoNodo->nodoTrie = &nodoTrie;
	//nuevoNodo->numApariciones = numApariciones;

	encolarAlgoritmo(nuevoNodo, numApariciones);
}

/**
 * Introduce todos los nodos de tabla como hojas en la cola con
 * prioridad.
 */
void ColaConPrioridad::encolarTabla(NodoBucketTF** tabla) {
	NodoBucketTF* nb;
	for(int i = 0; i < SIZE; i++){
		nb = tabla[i];
		while(nb != NULL){
			encolar_Hoja(nb->c, nb->frecuencia);
			nb = nb->next;
		}
    }
}

/**
 * Devuelve los datos del elemento con menor número de apariciones
 * y lo retira del principio de la cola.
 */
void ColaConPrioridad::desencolar() {
	NodoCola *aux;

	// Restamos un objeto a la cola:
	numElementos -= 1;

	aux = primero;      // aux apunta al inicio de la ColaConPrioridad
	primero = primero->siguiente;
	delete (aux);          // libera memoria a donde apuntaba aux
}

/**
 * Devuelve un puntero al primer elemento de la cola
 */
NodoCola* ColaConPrioridad::getPrimero() {
	return primero;
}


/**
 * TODO: Eliminar esta función para producción.
 *	Testing for ColaConPrioridad. Muestra la cola. Para detección de defectos.
 */
void ColaConPrioridad::muestraColaConPrioridad() {
	class NodoCola *aux;
	aux = primero;

	while (aux != nullptr) {
		if (aux->esHoja) {
			NodoCola_Hoja* aux_hoja = static_cast<NodoCola_Hoja*>(aux);
			cout << "   " << aux_hoja->c << "  -> " << aux->numApariciones << endl;
		}
		else {
			//NodoCola_Rama* aux_hoja = static_cast<NodoCola_Rama*>(aux);
			cout << " Rama -> " << aux->numApariciones << endl;
		}
		aux = aux->siguiente;
	}
}

/**
 * TODO: Eliminar esta función para producción.
 * 	Testing for KILLING ColaConPrioridad
 */
void ColaConPrioridad::vaciaColaConPrioridad() {
	class NodoCola *aux;

	while (primero != nullptr) {
		aux = primero;
		primero = aux->siguiente;
		delete (aux);
	}
	primero = nullptr;

}
