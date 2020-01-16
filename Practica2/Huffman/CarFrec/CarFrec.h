/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 13-3-19 *******************************
 * ***********************************************
 */

#ifndef CARFREC_H
#define CARFREC_H

#include <iostream>
#include <cstring>
#include <vector>
#include <fstream>

using namespace std;

/*
 * Fichero de interfaz del modulo carFrec
 */

class carFrec {
	// Estructura interna del tipo de dato carFrec
private:
		unsigned char caracter;     // valor del caracter
		int frecuencia;    // total de veces que aparece el caracter
public:

		/*
		 * Pre: ---
		 * Post: Devuelve una tupla vacia
		 */
		carFrec();

		/*
		 * Pre: <<c>> es un caracter y <<f>> es el total de veces que
		 *      aparece escrito en un fichero, es decir, su frecuencia:
		 * Post: Devuelve una tupla <<carFrec>> donde el valor del caracter
		 *       es igual a <<c>> y el valor de su frecuencia es igual a <<f>>
		 */
		carFrec(char c, int f);


		/*
		* Pre: ---
		* Post: Ha asignado al caracter de la tupla el valor de <<c>>
		*/
		void setCaracter(unsigned char c);


		/*
		* Pre: ---
		* Post: Ha asignado a la frecuencia de la tupla el valor de <<f>>
		*/
		void setFrecuencia(int f);


		/*
			* Pre: ---
			* Post: Ha devuelto el valor del caracter
			*/
		unsigned char getCaracter();

		/*
			* Pre: ---
			* Post: Ha devuelto el valor de la frecuencia
			*/
		int getFrecuencia();
};

#endif