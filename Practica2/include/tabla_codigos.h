/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef _TABLA_CODIGOS_H
#define _TABLA_CODIGOS_H

#include <map>
#include <string>
#include "Nodos.h"

using namespace std;


class NodoBucketTC{
public:
    unsigned char c;
    string codigo;
    NodoBucketTC* next;
    NodoBucketTC(unsigned char character, string code);

};

class TablaCodigos {
  private:
    NodoBucketTC* t[SIZE];
    int numElements;

  public:
    TablaCodigos();
    void insertar(unsigned char c, string codigo);
    NodoBucketTC** getTable();
    int getNumElements();
    string getCodigo(unsigned char c);
};

#endif
