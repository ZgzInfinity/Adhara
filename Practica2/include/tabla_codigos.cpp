/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#include "tabla_codigos.h"

#include <iostream>
#include <string>
#include "Nodos.h"

using namespace std;

/*
 * Constructor NodoBucketTC.
 */
NodoBucketTC::NodoBucketTC(unsigned char character, string code){
    this->c = character;
    this->codigo = code;
    this->next = NULL;
}

/*
 * Constructor TablaCodigos.
 * Pone el número de elemntos a 0.
 */
TablaCodigos::TablaCodigos(){
    this->numElements = 0;
    for(int i = 0; i < SIZE; i++){
        this->t[i] = NULL; // Inicializar a NULL
    }
}

/*
 * Inserta un nuevo caracter, con su correspondiente código a la tabla
 * de códigos.
 */
void TablaCodigos::insertar(unsigned char c, string codigo){
    unsigned int indice = hashFunction(c)%SIZE;
    if(this->t[indice] == NULL){ // Si no hay ningun NodoBucketTC aun
        this->t[indice] = new NodoBucketTC(c, codigo);
        this->numElements++;
    }
    else{ // Hay al menos un NodoBucketTC en esa posicion de la tabla
        NodoBucketTC* nb = this->t[indice];
        while(nb->c != c){
            if(nb->next == NULL){   // No existe, entonces se crea
                nb->next = new NodoBucketTC(c, codigo);
                this->numElements++;
            }
            else{ // Hay un siguiente, y lo miramos
                nb = nb->next;
            }
        }
    }
}

/*
 * Devuelve la tabla de códigos.
 */
NodoBucketTC** TablaCodigos::getTable(){
    return this->t;
}

/*
 * Devuelve el número de elementos de la tabla de códigos.
 */
int TablaCodigos::getNumElements(){
    return this->numElements;
}

/*
 * Devuelve el código de un caracter <c>.
 */
string TablaCodigos::getCodigo(unsigned char c){
    unsigned int indice = hashFunction(c)%SIZE;
    NodoBucketTC* nb = this->t[indice];
    if(nb != NULL){
        while(nb->c != c){
            if(nb->next != NULL){
                nb = nb->next;
            }
            else{
                return "";
            }
        }
        return nb->codigo;
    }
    else{
        return "";
    }
}
