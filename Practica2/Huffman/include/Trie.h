/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef SRC_TRIE_H_
#define SRC_TRIE_H_

#include "ColaConPrioridad.h"
#include "tabla_codigos.h"
#include <fstream>
//#include "Nodos.h"

class Trie {
private:
  NodoTrie* top;

public:
  Trie();
  NodoTrie* buildTrie(ColaConPrioridad cola);
  NodoTrie* getTop();
  void setTop(NodoTrie* nodoTop);
  NodoTrie* cola2trie(NodoCola* nodoCola);
  NodoCola* trie2cola(NodoTrie* nodoTrie, int numApariciones);
  void recorrer_y_crear_tabla(NodoTrie* nodo, string codigo, TablaCodigos* tabla);
  void escribirArbol(NodoTrie* nodo, ofstream &file);
  NodoTrie* leerArbol(ifstream &file);
  unsigned char obtenerByteOriginal(NodoTrie* nodo, string &cadena, unsigned int &pos);
};



#endif /* SRC_TRIE_H_ */
