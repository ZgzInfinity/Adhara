/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 16-3-19 *******************************
 * ***********************************************
 */

#ifndef ARBOLTRIE_H
#define ARBOLTRIE_H

#include <iostream>
#include <cstring>
#include "../CarFrec/CarFrec.h"

using namespace std;


const int MAX_LONG_LINEA = 500;

/*
 * Fichero de interfaz del modulo ArbolTrie
 */

struct ArbolTrie {
	private:
		// Estructura interna del arbol que permite
		// trabajar con nodos
		struct Nodo {
			int frecuencia;		// suma de las frecuencias totales de los sub-arboles hijos
			carFrec dato;		// tupla <caracter, frecuencia>
			Nodo* der;
			Nodo* izq;		// punteros a los subarboles izquierdo y derecho
		};

		Nodo* raiz;		// puntero a la raiz del arbol
	public:
		// funciones amigas para poder trabajar fuera de la especificacion

		/*
		 * Pre: <<e>> es una tupla <carater, frecuencia>
		 * Post: Ha creado un arbol de manera que el campo <<dato>> del arbol nuevo
		 *       toma el valor de <<e>>, el campo <<frecuencia>> toma el valor de la frecuencia de <<e>>
		 *       y los punteros a los subarboles izquuierdo y derecho son nulos
		 */
		 friend void crearArbol(ArbolTrie& a, carFrec& e);


		/*
		 * Pre: <<a>> es un arbol de tuplas <caracter, frecuencia> y
		 *      <<frecuencia>> indica la frecuencia de caracteres a asignar
		 * Post: Ha asignado al caracter recogido en el arbol <<a>> la frecuencia
		 *       <<frecuencia>>
		 */
		 friend void asignarFrecuencia(ArbolTrie& a, const int& frecuencia);


		/*
		 * Pre: <<a>> y <<aIzq>> son dos arboles que almacenan tuplas <caracter, frecuencia>>
		 * Post: Ha asignado <<aIzq>> como subarbol izquierdo de <<a>>
		 */
		friend void asignarArbolIzquierdo(ArbolTrie& a, const ArbolTrie& aIzq);


		/*
		 * Pre: <<a>> y <<aDer>> son dos arboles que almacenan tuplas <caracter, frecuencia>>
		 * Post: Ha asignado <<aDer>> como subarbol derecho de <<a>>
		 */
		friend void asignarArbolDerecho(ArbolTrie& a, const ArbolTrie& aDer);


		/*
		* Pre: <<a>> es un arbol que almacena una tupla <caracter, valor> y
		*      <<cF>> es una tupla <caracter, valor> libre
		* Post: Ha reemplazado la tupla <caracter, valor> almacenada en <<a>> por
		*       la tupla <<cF>>
		*/
		friend void asignarArbolCarFrec(ArbolTrie& a, const carFrec& cF);


		/*
		 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
		 * Post: Ha devuelto la frecuencia del arbol <<a>>
		 */
		 friend int obtenerArbolFrecuencia(const ArbolTrie& a);


		/*
		 * Pre: <<a>> es un arbol que almacena una tupla <caracter, frecuencia>
		 * Post: Ha devuelto la tupla <caracter, valor> asociada al arbol <<a>>
		 */
		 friend carFrec obtenerCarFrec(const ArbolTrie& a);

		/*
		 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
		 * Post: Ha devuelto el subarbol izquierdo de <<a>>
		 */
		 friend ArbolTrie obtenerArbolIzquierdo(const ArbolTrie& a);


		/*
		 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
		 * Post: Ha devuelto el subarbol derecho de <<a>>
		 */
		 friend ArbolTrie obtenerArbolDerecho(const ArbolTrie& a);


		/*
		 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia
		 * Post: Devuelve <<true>> si y solo si el arbol <<a>> es hoja. En caso
		 *       contrario devuelve <<false>>
		 */
		 friend bool esHoja (const ArbolTrie& a);


		/*
		 * Pre: <<a1>> y <<a2>> son dos arboles que almacenan tuplas <caracter, frecuencia>
		 * Post: Ha devuelto un arbol de manera que <<a1>> es el hijo izquierdo del nuevo arbol y
		 *       y <<a2>> es el hijo derecho
		 */
		 friend void unir(ArbolTrie& a1, ArbolTrie& a2, ArbolTrie& arbolFinal);




		/*
		 * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
		 *      cada uno de sus nodos hoja correspondientes a los caracteres
		 *      recogidos en un fichero de texto junto con sus frecuencias de
		 *      aparicion y <<f>> es un flujo de escritura asociado a un fichero
		 *      de texto que guarda una representacion del contenido con el que debe
		 *      contar el arbol <<a>>
		 * Post: Ha volcado en el fichero de texto asociado al flujo de escritura
		 *       <<f>> el contenido restante del arbol <<a>>
		 *
		 */
		 friend void guardarArbolEnFicheroRec(ArbolTrie a, ifstream& f);



		/*
		 * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
		 *      cada uno de sus nodos hoja correspondientes a los caracteres
		 *      recogidos en un fichero de texto junto con sus frecuencias de
		 *      aparicion
		 * Post: Si se ha podido crear sin ningun problema el fichero de
		 *       texto <<arbolNombreFichero>> ha guardado en dicho fichero una
		 *       representacion del arbol con la siguiente estructura:
     	 *
		 *       Estructura: explicar
		 */
     friend void guardarArbolEnFichero(ArbolTrie a, const string arbolNombreFichero);



		 /*
		  * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
		  *      cada uno de sus nodos hoja correspondientes a los caracteres
		  *      recogidos en un fichero de texto junto con sus frecuencias de
		  *      aparicion y <<f>> es un flujo de escritura asociado a un fichero
		  *      de texto que guarda una representacion del contenido con el que debe
			*      contar el arbol <<a>>
		  * Post: Ha volcado en el fichero de texto asociado al flujo de escritura
		  *       <<f>> el contenido restante del arbol <<a>>
		  *
		  */
		  friend int construirArbolDeFicheroRec(ArbolTrie& a, ifstream& f);



		 /*
		  * Pre: <<arbolNombreFichero>> es un fichero de texto que almacena el arbol
		  *      de codigos Huffman de un fichero cuyo nombre es igual a <<NombreFichero>>
		  * Post: Ha construido en <<a>> un arbol el arbol de codigos Huffman
		  *       correspondiente al fichero <<NombreFichero>>
		  */
		  friend int construirArbolDeFichero(const string arbolNombreFichero, ArbolTrie& a);



		 /*
		  * Pre:  <<codigos>> es un vector de caracteres de con capacidad
		  *       para 256 caracteres, <<a>> es el trie que almacena en cada uno de sus
		  *       nodos un caracter presente en el fichero junto con su correspondiente
		  *       frecuencia, <<h>> SOBRA y <<codigo>> es un frgamento de codificacion
		  *       del caracter actual apuntado por la raiz del arbol <<a>>
		  * Post: Ha guardado en cada una de las componentes del vector <<codigos>>
		  *       la codificacion binaria a cada caracter presente en el fichero
		  *
		  *       Ejemplo:
		  *       A = 0
		  *       B = 101
		  *       C = 100
		  *       D = 111
		  *       E = 1101
		  *       F = 1100
		  *
		  *       ............
		  *
		  */
    	friend void codificador(string codigos[],const ArbolTrie& a, string codigo);

		/*
		 * Pre: <<inicial>> es un puntero a un nodo que almacena el arbol huffman inicial,
		 * 		<<actual>> es un puntero a un nodo que almacena el nodo del arbol que hay que comprobar,
		 *  	<<num>> es el bit que estamos decodificando,
		 *      <<sumar>> indica si hemos llegado a una hoja. 
		 * Post: Devuelve el SrbolTrie que hay que comprobar en la siguiente llamada, si se ha llegado a una
		 * 		 hoja escribe en el fichero de salida el caracter obtenido a partir del arbol y sumar = true.
		 */
		 friend ArbolTrie decodificarCaracter(const ArbolTrie& inical, ArbolTrie& actual,int num, ofstream& f,bool& sumar);

		 /*
		  * Pre: <<nombreFichero>> es el nombre de un fichero comprimido con
		  *      extension de archivo .huf y <<a>> es el arbol de codigos huffman
		  *      empleado en la codficacion del fichero <<nombreFichero>>
		  * Post: Ha devuelto como resultado un fichero descomprimido con el contenido
		  *       del del fichero comprimido <<nombreFichero>> empleando para la
		  *       descompresion el arbol de codigos Huffman <<a>>
		  */
		  
		  friend void descifraFichero(string nombreFichero, ArbolTrie& trie,int numB);
		

		
};

#endif