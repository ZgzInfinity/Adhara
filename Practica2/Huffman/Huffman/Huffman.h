/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 25-3-19 *******************************
 * ***********************************************
 */

/*
 * Fichero de interfaz del modulo Huffman
 */

#ifndef HUFFMAN_H
#define HUFFMAN_H

#include <iostream>
#include <cstring>
#include <fstream>
#include "../Preliminar/Preliminar.h"
#include "../CarFrec/CarFrec.h"
#include "../Heap/Heap.h"
#include "../ArbolTrie/ArbolTrie.h"


using namespace std;


const int BASE = 2;
const int TAMANYO_BYTE = 8;


/*
	* Pre: <<c>> es una cola de arboles hojas en los que se guardan
	*       tuplas <caracter, valor> ordenadas por orden decreciente de
	*		frecuencias
	* Post: El arbol <<huffman>> es el arbol de codificacion Huffman que guarda un
	*       codigo libre de prefijos optimo para <<c>>
	*/
 void generaHuffman(Heap& c, ArbolTrie& huffman);




/*
	* Pre:  <<ficheroEntrada>> es un fichero de texto que almacena
	*       caracteres tanto especiales como alfanumericos
	* Post: Si el fichero se ha abierto correctamente ha leido su contenido
	*       caracter a caracter guardando la representacion binaria de cada
	*       uno en el vector <<contenidoFichero>>. En caso contrario ha
	*       mostrado por salida estandar el error de apertura.
	*/
 unsigned int leerFichero(string ficheroEntrada, string& contenidoFichero, string codigos[]);




/*
	* Pre:  <<contenido>> es una secuencia que almacena un conjunto
	*       de caracteres representados con su codificacion binaria
	*       caracteres tanto especiales como alfanumericos
	* Post: Si el fichero <<ficheroSalida>> se ha creado correctamente ha
	*       volcado el contenido almacenado en <<contenido>>
	*       comprimido. En caso contrario ha mostrado por salida estandar el
	*       error de apertura.
	*/
void escribirFichero(const string contenido, string ficheroSalida, unsigned int total);



/*
	* Pre: <<ficheroEntrada>> es un fichero de texto que almacena
	*      caracteres tanto especiales como alfanumericos
	* Post: <<ficheroSalida>> es un fichero comprimido binario que almacena
	*       de forma comprimida la secuencia de caracteres guardada en el fichero
	*       denominado <<ficheroEntrada>> empleando como mecanismo de compresion
	*		   los codigos Huffman
	*/
 void comprimir(string ficheroEntrada);




 /*
  * Pre: <<nombreFichero>> es el nombre de un fichero comprimido con la
  *      codificacion de Hufmman y que tiene extension .huf
  * Post: Ha creado un nuevo fichero resultado de llevar a cabo la descompresion
  *       del fichero <<nombreFichero>> de modo que el contenido del nuevo
  *       fichero es identico al del fichero original antes de hacer la
  *       compresion
  */
 void descomprimir(const string nombreFichero);


#endif
