/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#include "Nodos.h"

unsigned int hashFunction(unsigned char c){
    return (unsigned int)c;
}


/*
 * CONSTRUCTOR Clase Nodo Trie Rama
 */
NodoTrie_Rama::NodoTrie_Rama(NodoTrie* izq, NodoTrie* der){
	hijo_izq = izq;
	hijo_der = der;
	esHoja = false;
}

/*
 * CONSTRUCTOR Clase Nodo Trie Hoja
 */
NodoTrie_Hoja::NodoTrie_Hoja(unsigned char letra){
	c = letra;
	esHoja = true;
}

/*
 * CONSTRUCTOR Clase Nodo Cola Rama
 */
NodoCola_Rama::NodoCola_Rama(NodoTrie* nodoT,  unsigned int apariciones){
	nodoTrie = nodoT;
	numApariciones = apariciones;
	esHoja = false;
}

/*
 * CONSTRUCTOR Clase Nodo Cola Hoja
 */
NodoCola_Hoja::NodoCola_Hoja(unsigned char letra, unsigned int apariciones){
	c = letra;
	numApariciones = apariciones;
	esHoja = true;
}
