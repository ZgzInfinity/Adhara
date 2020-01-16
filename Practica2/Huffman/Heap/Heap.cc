/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 29-3-19 *******************************
 * ***********************************************
 */

#include "Heap.h"

/*
 * Fichero de implementacion del modulo Heap
 */


/*
 * Pre: ---
 * Post: Ha creado un monticulo de arboles vacio
 */
void crearVacio(Heap& h){
    h.num = 0;
}



/*
 * Pre: ---
 * Post: Ha devuelto el total de arboles del monticulo
 */
int numElementos(Heap& h){
	return h.num;
}



/*
 * Pre: <<h>> es un monticulo de arboles  y <<a>> es un nuevo arbol
 *      que se desea insertar
 * Post: Ha insertado el arbol <<a>> en el monticulo de arboles <<h>>
 */
void anyadir(Heap& h, ArbolTrie& a){
	// Insercion del nuevo arbol en la ultima posicion del monticulo
    h.num++;
    h.arboles[h.num]=a;
    int i = h.num;
    bool debeSubir;
	// Comprobacion del numero total de arboles del monticulo
    if(i > 1){
		// el nuevo arbol tiene mas prioridad que su padre
		// debe flotar y ascender
        debeSubir = obtenerArbolFrecuencia(h.arboles[i]) < obtenerArbolFrecuencia(h.arboles[i/2]);
    }
	else{
		// el nuevo arbol tiene menos prioridad que su padre
        debeSubir = false;
    }
	// bucle de reorganizacion de arboles en el monticulo
    while(debeSubir){
		// intercambio del arbol hijo con su padre
        ArbolTrie aux;
        aux = h.arboles[i];
        h.arboles[i]=h.arboles[i / 2];
        h.arboles[i / 2]=aux;
        i = i / 2;
        if( i> 1){
			// es mas priritario que su nuevo padre
            debeSubir = obtenerArbolFrecuencia(h.arboles[i]) < obtenerArbolFrecuencia(h.arboles[i/2]);
        }
		else{
			// el nuevo padre tiene mas prioridad
            debeSubir = false;
        }
    }
}



/*
 * Pre: ---
 * Post: Ha eliminado el primer arbol del monticulo <<h>>
 */
void eliminarMin(Heap& h){
	int i, j;
	ArbolTrie aux;
	if(h.num > 0){
		// eliminacion del primer arbol del monticulo
		h.arboles[1]= h.arboles[h.num];
		h.num--;
		i = 1;
		// <<i>> es el indice de la posicion actual del que antes era el primer elemento
		while(i <= (h.num / 2 )){
		// el arbol i-esimo tiene hijos
		// hundir el anterior ultimo elemento del arbol
		if((2 * i == h.num) || (obtenerArbolFrecuencia(h.arboles[2 * i]) < obtenerArbolFrecuencia(h.arboles[ 2 * i + 1]))){
			j = 2 * i;
			}else{
			j = 2 * i + 1;
		}
		// el arbol <<j>> es el subarbol hijo de <<i>> con mayor prioridad
		if(obtenerArbolFrecuencia(h.arboles[i]) > obtenerArbolFrecuencia(h.arboles[j])){
				// intercambiar el anterior ultimo elemento con su hijo
				aux = h.arboles[i];
				h.arboles[i]= h.arboles[j];
				h.arboles[j]= aux;
				i = j;
			}
			else{
				// necesario para salir del bucle
				i = h.num;
			}
		}
	}
}



/*
 * Pre: ---
 * Post: Ha devuelto el primer arbol del monticulo <<h>>
 */
ArbolTrie min(Heap& h){
    return h.arboles[1];
}



/*
 * Pre: ---
 * Post: Ha devuelto <<true>> si y solo si el monticulo <<h>> es vacio.
 *       En caso contrario devuelve <<false>>
 */
bool esVacio(Heap& h){
    return h.num == 0;
}



/*
 * Pre: ---
 * Post: Ha devuelto el arbol localizado en la posicion i-ésima del monticulo <<h>>
 */
ArbolTrie consultar(Heap& h, const int i){
	return h.arboles[i];
}



/*
 * Pre: <<h>> es un monticulo de arboles vacio y <<frecsPorChar>> es un vector
 *      que almacena la frecuencia de los distintos caracteres almacenados en fichero de texto
 * Post: Ha rellenado el montiulo con prioridad <<h>> de modo que el caracter con menor frecuencia
 *       se encuentra en la primera posicion
 */
void rellenar(Heap& h, const int frecsPorChar[]){
	ArbolTrie a;
	carFrec nuevoCarFrec;
	char c;
	// Recorrido del del vector de frecuencias por caracter
	for (int i = 0; i < MAX_CARACTERES; i++){
		// si el caracter i-ésimo aparece en el fichero
		if (frecsPorChar[i] > 0){
			// obtencion del caracter
			c = char(i);
			nuevoCarFrec = carFrec(c, frecsPorChar[i]);
			// construccion de un arbol hoja con ese caracter y su frecuencia
			crearArbol(a, nuevoCarFrec);
			asignarFrecuencia(a, frecsPorChar[i]);
			// incorporacion del nuevo arbol al monticulo de prioridades
			anyadir(h, a);
		}
	}
}