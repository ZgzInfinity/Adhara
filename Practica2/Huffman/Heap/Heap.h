/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 29-3-19 *******************************
 * ***********************************************
 */

#ifndef HEAP_H
#define HEAP_H

#include <iterator>
#include <iostream>
#include <cstring>
#include "../CarFrec/CarFrec.h"
#include "../ArbolTrie/ArbolTrie.h"

/*
 * Fichero de interfaz del modulo Heap
 */

const int MAX_CARACTERES = 256;

using namespace std;

struct Heap {
	private:
		// monticulo de tuplas <caracter, frecuencia> ordenado por frecuencias
		ArbolTrie arboles[MAX_CARACTERES + 1];
		// total de arboles del monticulo
        int num;
	public:
		
		/*
		 * Pre: ---
		 * Post: Ha creado un monticulo de arboles vacio
		 */
		 friend void crearVacio(Heap& h);
		
		/*
		 * Pre: ---
		 * Post: Ha devuelto el total de arboles del monticulo
		 */
		 friend int numElementos(Heap& h);

		/*
		 * Pre: <<h>> es un monticulo de arboles  y <<a>> es un nuevo arbol
		 *      que se desea insertar
		 * Post: Ha insertado el arbol <<a>> en el monticulo de arboles <<h>>
		 */
		 friend void anyadir(Heap& h, ArbolTrie& a);

		/*
		 * Pre: ---
		 * Post: Ha eliminado el primer arbol del monticulo <<h>>
		 */
		 friend void eliminarMin(Heap& h);

		/*
		 * Pre: ---
		 * Post: Ha devuelto el primer arbol del monticulo <<h>>
		 */
		 friend ArbolTrie min(Heap& h);

		/*
		 * Pre: ---
		 * Post: Ha devuelto <<true>> si y solo si el monticulo <<h>> es vacio.
		 *       En caso contrario devuelve <<false>>
		 */
        friend bool esVacio(Heap& h);

		/*
		 * Pre: ---
		 * Post: Ha devuelto el arbol localizado en la posicion i-ésima del monticulo <<h>>
		 */
		friend ArbolTrie consultar(Heap& h, const int i);


		/*
		 * Pre: <<h>> es un monticulo de arboles vacio y <<frecsPorChar>> es un vector
		 *      que almacena la frecuencia de los distintos caracteres almacenados en fichero de texto
		 * Post: Ha rellenado el montiulo con prioridad <<h>> de modo que el caracter con menor frecuencia
		 *       se encuentra en la primera posicion
		 */
		friend void rellenar(Heap& h, const int frecsPorChar[]);
};


#endif
