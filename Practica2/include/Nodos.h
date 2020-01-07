/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef SRC_INCLUDE_NODOTRIE_H_
#define SRC_INCLUDE_NODOTRIE_H_

const int SIZE = 251;

unsigned int hashFunction(unsigned char c);

/*
 * Clase Nodo Trie
 */
class NodoTrie {
public:
	bool esHoja;
	virtual ~NodoTrie() {};
};

/*
 * Clase Nodo Trie Rama
 */
class NodoTrie_Rama : public NodoTrie {
public:
	NodoTrie *hijo_izq;
	NodoTrie *hijo_der;
	NodoTrie_Rama(NodoTrie* izq, NodoTrie* der);
};

/*
 * Clase Nodo Trie Hoja
 */
class NodoTrie_Hoja : public NodoTrie {
public:
	unsigned char c;
	NodoTrie_Hoja(unsigned char letra);
};

/****************************************************/

/*
 * Clase Nodo Cola
 */
class NodoCola {
	public:
	unsigned int numApariciones;
	NodoCola *siguiente;
	bool esHoja;
	virtual ~NodoCola() {};
};

/*
 * Clase Nodo Cola Rama
 */
class NodoCola_Rama : public NodoCola {
	public:
	NodoTrie* nodoTrie;
	NodoCola_Rama(NodoTrie* nodoT, unsigned int apariciones);
};

/*
 * Clase Nodo Cola Hoja
 */
class NodoCola_Hoja : public NodoCola {
	public:
	unsigned char c;
	NodoCola_Hoja(unsigned char letra, unsigned int apariciones);
};



/*
 * Clase Nodo Trie Fichero
 */
class NodoTrieFichero {
public:
	bool esHoja;
	char c;
};

#endif /* SRC_INCLUDE_NODOTRIE_H_ */
