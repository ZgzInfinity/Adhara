/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 29-3-19 *******************************
 * ***********************************************
 */

#ifndef PRELIMINAR_H
#define PRELIMINAR_H


#include <cstring>
#include <iostream>
#include <fstream>

using namespace std;


const int NUM_CARACTERES = 256;


/*
 * Pre: ---
 * Post: Ha asignado con valor cero todas las componentes del
 *       vector <<frecsPorChar>>
 */
void iniciarFrecuencias(int frecsPorChar[]);



/*
 * Pre: ---
 * Post: Ha asignado con valor '-' todas las componentes del
 *       vector <<frecsPorChar>>
 */
void iniciarCodificaciones(string codigos[]);



/*
 * Pre: <<nombreFichero>> es un fichero de caracteres y <<frescPorChar>> es un vector de
 *      enteros vacio destinado a almacenar en cada una de sus componentes las veces que
 *      aparece cada caracter del fichero <<nombreFichero>>
 * Post: Si la lectura del fichero de caracteres <<nombreFichero>> se ha efectuado correctamente
 *       ha guardado en las componentes del vector <<frecsPorChar>> el numero de veces
 *       que aparece cada caracter distinto en el fichero. En caso contrario ha informado
 *       mediante un error por pantalla de la innacesibilidad del fichero <<nombreFichero>>
 */
void frecuenciasPorCaracter(string nombreFichero, int frecsPorChar[]);

#endif
