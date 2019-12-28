#ifndef PRODUCT_H
#define PRODUCT_H

#include <cstring>
#include <iostream>

using namespace std;

class Product{

    int id;
    string name;
    int units;
    int price;

public:

    Product(int _id);

    Product(int _id, string _name, int _units, int _price);

    int getId();

    string getName();

    int getUnits();

    int getPrice();

};

#endif 