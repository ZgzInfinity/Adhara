/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef _FREQ_TABLE_H
#define _FREQ_TABLE_H

#include <map>
#include "Nodos.h"
using namespace std;


class NodoBucketTF{
public:
    unsigned char c;
    int frecuencia;
    NodoBucketTF* next;
    NodoBucketTF(unsigned char character);

};

class Table {
  private:
    //map<char, TableNode> t;
    NodoBucketTF* t[SIZE];
    //int arrayAleatorio[SIZE];
    int numElements;


  public:
    Table();
    void increment(unsigned char c);
    NodoBucketTF** getTable();
    unsigned char getFirstKey();
    int getFirstValue();
    int getNumElements();
};

#endif
