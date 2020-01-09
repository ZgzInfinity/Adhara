/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#include "Trie.h"
#include "ColaConPrioridad.h"
#include "tabla_codigos.h"
//#include "Nodos.h"

#include <stdio.h>
#include <stdlib.h>

using namespace std;
#define nullptr NULL

Trie::Trie(){
	this->top = nullptr;
}

/*
 * Convierte e introduce un nodo de la cola con pioridad en el Trie.
 */
NodoTrie* Trie::cola2trie(NodoCola* nodoCola){
	NodoTrie* nuevoNodo;
	if(nodoCola->esHoja){
		NodoCola_Hoja *aux = static_cast<NodoCola_Hoja*>(nodoCola);
		nuevoNodo = new NodoTrie_Hoja(aux->c);
	}
	else{
		//Le asigno el nodoTrie que tiene almacenado el NodoCola_Rama
		NodoCola_Rama *aux = static_cast<NodoCola_Rama*>(nodoCola);
		nuevoNodo = aux->nodoTrie;
	}
	return nuevoNodo;
}

/*
 * Convierte e introduce un nodo del trie en la cola con prioridad.
 */
NodoCola* Trie::trie2cola(NodoTrie* nodoTrie, int numApariciones){
	NodoCola* nuevoNodo;
	if(nodoTrie->esHoja){
		NodoTrie_Hoja *aux = static_cast<NodoTrie_Hoja*>(nodoTrie);
		nuevoNodo = new NodoCola_Hoja(aux->c, numApariciones);
	}
	else{
		nuevoNodo = new NodoCola_Rama(nullptr, numApariciones);
	}
	return nuevoNodo;
}

/*
 * Dada una cola con prioridad, contruye el trie correspondiente.
 */
NodoTrie* Trie::buildTrie(ColaConPrioridad cola){
	NodoTrie *topTrie, *izqAux, *derAux;
	NodoCola *nodoColaAux;
	unsigned int numApariciones = 0; //Llevar cuenta del numero de apariciones

	if(cola.getNumElementos() == 1){
		//cout << "Entro" << endl;
		nodoColaAux = cola.getPrimero();
		numApariciones = nodoColaAux->numApariciones; //numApariciones del primero
		izqAux = cola2trie(nodoColaAux); //izqAux apunta a un NodoTrie
		cola.desencolar(); //Desencolar el menos frecuente de la cola
		//cout << "Desencolado" << endl;
		//Crear Nodo trie con los dos (top = new NodoTrie)
		topTrie = new NodoTrie_Rama(izqAux, NULL);
		//Encolar el Nodo trie en la cola (se transforma a nodo cola)
		//cout << "Encolando de nuevo" << endl;
		cola.encolar_Rama(topTrie, numApariciones);
		//cout << "Encolado" << endl;
	}
	else{
		while(cola.getNumElementos() != 1){
			nodoColaAux = cola.getPrimero();
			numApariciones = nodoColaAux->numApariciones; //numApariciones del primero
			izqAux = cola2trie(nodoColaAux); //izqAux apunta a un NodoTrie
			cola.desencolar(); //Desencolar el menos frecuente de la cola

			nodoColaAux = cola.getPrimero();
			numApariciones += nodoColaAux->numApariciones; //numApariciones del primero + segundo
			derAux = cola2trie(nodoColaAux); //derAux apunta a un NodoTrie
			cola.desencolar(); //Desencolar el nuevo elemento menos frecuente de la cola

			//Crear Nodo trie con los dos (top = new NodoTrie)
			topTrie = new NodoTrie_Rama(izqAux, derAux);
			//Encolar el Nodo trie en la cola (se transforma a nodo cola)
			cola.encolar_Rama(topTrie, numApariciones);
		}
	}
	//Cuando sólo queda 1 elemento en la cola
	nodoColaAux = cola.getPrimero();
	//cout << "Sacado" << endl;
	topTrie = cola2trie(nodoColaAux);
	//cout << "Asignado" << endl;
	cola.desencolar();
	//cout << "Ultimo desencole" << endl;

	this->top = topTrie; //guardar top
	return topTrie;
}

/*
 * Devuelve la raiz del trie.
 */
NodoTrie* Trie::getTop(){
	return this->top;
}

/*
 * Pone de raiz del trie <nodoTop>.
 */
void Trie::setTop(NodoTrie* nodoTop){
	this->top = nodoTop;
}

/*
 * Recorre todos los nodos del árbol en preorden y crea la tabla de códigos
 */
void Trie::recorrer_y_crear_tabla(NodoTrie* nodo, string codigo, TablaCodigos* tabla){
	if (nodo->esHoja) {
		//cout << "Hoja" << endl;
		NodoTrie_Hoja *aux = static_cast<NodoTrie_Hoja*>(nodo);
		tabla->insertar(aux->c, codigo); //Añadir a la tabla
		//cout << "finalizado" << endl;
	}
	else{
		//cout << "Rama" << endl;
		NodoTrie_Rama *aux = static_cast<NodoTrie_Rama*>(nodo);
		codigo.push_back('0');
		recorrer_y_crear_tabla(aux->hijo_izq, codigo, tabla);
		codigo = codigo.substr(0, codigo.size()-1);
		//codigo.pop_back();
		codigo.push_back('1');
		recorrer_y_crear_tabla(aux->hijo_der, codigo, tabla);
	}
}


/*
 * Escribe el arbol trie al principio del fichero de salida <file>.
 */
void Trie::escribirArbol(NodoTrie* nodo, ofstream &file){
	NodoTrieFichero nodoTrieFichero;
	if (nodo->esHoja) {
		NodoTrie_Hoja *aux = static_cast<NodoTrie_Hoja*>(nodo);
		nodoTrieFichero.esHoja = true;
		nodoTrieFichero.c = aux->c;
		file.write(reinterpret_cast<const char*> (&nodoTrieFichero), sizeof(nodoTrieFichero));
	}
	else{
		NodoTrie_Rama *aux = static_cast<NodoTrie_Rama*>(nodo);
		nodoTrieFichero.esHoja = false;
		file.write(reinterpret_cast<const char*> (&nodoTrieFichero), sizeof(nodoTrieFichero));
		escribirArbol(aux->hijo_izq, file);
		escribirArbol(aux->hijo_der, file);
	}
}


/*
 * Construye el árbol a partir de los datos del fichero de entrada
 */
NodoTrie* Trie::leerArbol(ifstream &file){
	NodoTrieFichero nodoTrieFichero;
	NodoTrie* nuevoNodo;
	file.read(reinterpret_cast<char*>(&nodoTrieFichero), sizeof(NodoTrieFichero));
	if(nodoTrieFichero.esHoja){ // Es una hoja
		nuevoNodo = new NodoTrie_Hoja(nodoTrieFichero.c);
	}
	else{ // Es una rama
		NodoTrie* izqAux = leerArbol(file);
		NodoTrie* derAux = leerArbol(file);
		nuevoNodo = new NodoTrie_Rama(izqAux, derAux);
	}
	return nuevoNodo;
}


/*
 * Recorre <cadena> componente a componente y devuelve el caracter correspondiente del trie
 * a los datos leidos de la cadena de 1s y 0s.
 */
unsigned char Trie::obtenerByteOriginal(NodoTrie* nodo, string &cadena, unsigned int &pos){
	NodoTrie* nodoAux = nodo;
	while(!nodoAux->esHoja){
		NodoTrie_Rama *nodoRama = static_cast<NodoTrie_Rama*>(nodoAux);
		if(cadena[pos] == '0'){
			// Si es 0, hijo izquierdo
			nodoAux = nodoRama->hijo_izq;
			//obtenerByteOriginal(aux->hijo_izq, cadena);
		}
		else{
			// Si es 1, hijo derecho
			nodoAux = nodoRama->hijo_der;
			//obtenerByteOriginal(aux->hijo_der, cadena);
		}
		pos++;
	}
	NodoTrie_Hoja *returnVal = static_cast<NodoTrie_Hoja*>(nodoAux);
	return returnVal->c;

}
