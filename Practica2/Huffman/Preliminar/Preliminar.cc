/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 29-3-19 *******************************
 * ***********************************************
 */


#include "Preliminar.h"


/*
 * Pre: ---
 * Post: Ha asignado con valor cero todas las componentes del
 *       vector <<frecsPorChar>>
 */
void iniciarFrecuencias(int frecsPorChar[]){
	for (int i = 0; i < NUM_CARACTERES; i++){
		frecsPorChar[i] = 0;
	}
}


/*
 * Pre: ---
 * Post: Ha asignado con valor '-' todas las componentes del
 *       vector <<frecsPorChar>>
 */
void iniciarCodificaciones(string codigos[]){
	for(int k = 0; k < NUM_CARACTERES ; k++){
		codigos[k]= "-";
	}
}

/*
 * Pre: <<nombreFichero>> es un fichero de caracteres y <<frescPorChar>> es un vector de
 *      enteros vacio destinado a almacenar en cada una de sus componentes las veces que
 *      aparece cada caracter del fichero <<nombreFichero>>
 * Post: Si la lectura del fichero de caracteres <<nombreFichero>> se ha efectuado correctamente
 *       ha guardado en las componentes del vector <<frecsPorChar>> el numero de veces
 *       que aparece cada caracter distinto en el fichero. En caso contrario ha informado
 *       mediante un error por pantalla de la innacesibilidad del fichero <<nombreFichero>>
 */
void frecuenciasPorCaracter(string nombreFichero, int frecsPorChar[]){
	int codAscii;
	// Creacion del flujo de lectura
	ifstream f;
	// Asociacion del flujo al fichero
	f.open(nombreFichero);
	if (f.is_open()){
		// Flujo asociado a fichero correctamente
		unsigned char c;
		// lectura del primer caracter del fichero
		c = f.get();
		// mientras no acaba el fichero
		while (!f.eof()){
			// obtencion del codigo ascii del caracter leido
			
			codAscii = int(c);
			// incremento de la frecuencia correspondiente
			frecsPorChar[codAscii]++;
			// lectura de un nuevo caracter del fichero
			c = f.get();
		}
		// Cierre del flujo de lectura asociado al fichero
		f.close();
	}
	else {
		// Error en la asociacion del flujo al fichero
		cerr << "El fichero de " << nombreFichero << " no se ha podido leer " << endl;
	}
}