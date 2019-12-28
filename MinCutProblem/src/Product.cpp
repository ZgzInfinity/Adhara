#include "../include/Product.h"


Product::Product(int _id){
    id = _id;
}

Product::Product(int _id, string _name, int _units, int _price){
    id = _id;
    name = _name;
    units = _units;
    price = _price;
}

int Product::getId(){
    return id;
}

string Product::getName(){
    return name;
}

int Product::getUnits(){
    return units;
}

int Product::getPrice(){
    return price;
}