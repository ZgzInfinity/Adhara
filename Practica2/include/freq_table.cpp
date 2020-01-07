/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */
#include "freq_table.h"

#include <iostream>
#include "Nodos.h"

using namespace std;

#define nullptr NULL

/*
 * Constructor NodoBucketTF
 */
NodoBucketTF::NodoBucketTF(unsigned char character){
    this->frecuencia = 0;
    this->c = character;
    this->next = NULL;
}

/*
 * Constructor Table
 * Pone el número de elementos a 0.
 */
Table::Table(){
    this->numElements = 0;
    //cout << "hola" << endl;
    for(int i = 0; i < SIZE; i++){
        this->t[i] = NULL; // Inicializar a NULL
    }
}

/*
 * Incrementa el número de apariciones de un carácter <c> en uno.
 */
void Table::increment(unsigned char c){
    unsigned int indice = hashFunction(c)%SIZE;
    if(this->t[indice] == NULL){ // Si no hay ningun NodoBucketTF aun
        this->t[indice] = new NodoBucketTF(c);
        this->t[indice]->frecuencia++;
        this->numElements++;
    }
    else{ // Hay al menos un NodoBucketTF en esa posicion de la tabla
        NodoBucketTF* nb = this->t[indice];
        while(nb->c != c){
            if(nb->next == NULL){   // No existe, entonces se crea
                nb->next = new NodoBucketTF(c);
                nb = nb->next;
                this->numElements++;
            }
            else{ // Hay un siguiente, y lo miramos
                nb = nb->next;
            }
        }
        nb->frecuencia++;
    }
}

/*
 * Devuelve tabla de frecuencias.
 */
NodoBucketTF** Table::getTable(){
    return this->t;
}

/*
 * Devuelve el número de elmentos de la tabla de frecuencias.
 */
int Table::getNumElements(){
    return this->numElements;
}

/*
 * Devuelve el carácter de la tabla.
 * Sólo usada en el caso genérico en el que haya un sólo elemento.
 * Recorre la tabla hasta encontrar el único elemento de esta.
 */
unsigned char Table::getFirstKey(){
    for(int i = 0; i < SIZE; i++){
        if(this->t[i] != NULL){
            return this->t[i]->c;
        }
    }
    return -1;
}

/*
 * Devuelve la frecuencia del único carácter de la tabla.
 * Sólo usada en el caso genérico en el que haya un sólo elemento.
 * Recorre la tabla hasta encontrar el único elemento de esta.
 */
int Table::getFirstValue(){
    for(int i = 0; i < SIZE; i++){
        if(this->t[i] != NULL){
            return this->t[i]->frecuencia;
        }
    }
    return -1;
}
