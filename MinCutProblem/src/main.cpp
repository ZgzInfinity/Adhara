
#include <vector>
#include <fstream>
#include "../include/Product.h"

using namespace std;

int main(int argc, char* argv[]){
    string str;
    int numProducts;
    if (argc != 2 && argc != 3){
        cerr << "Wrong number of parameters" << endl;
        cerr << "Invoke like mincut <matrixFile> <productsFile>" << endl;
        return 1;
    }
    if(argc == 3){

    }
    // Reading flow of the file which contains the matrix
    ifstream f;
    // Open the file
    f.open(argv[1]);
    // Get number of products
    f >> numProducts;
    // Adjacency matrix of products
    vector<vector<int>> matrix(numProducts, vector<int>(numProducts));
    while(!f.eof()){
        for(int i = 0; i < numProducts; i++){
            for(int j = 0; j < numProducts; j++){
                // Save value in matrix (sold together or not)
                f >> matrix[i][j];
            }
        }
    }



    Product p = Product(0);
    return 0;
}