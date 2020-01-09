#include <iostream>
#include <fstream>
#include <string.h>

#include "include/help.h"
#include "include/freq_table.h"
#include "include/ColaConPrioridad.h"
#include "include/Trie.h"
#include "include/tabla_codigos.h"

using namespace std;

bool errors = 0;
const int MAX_SIZE_BUFFER = 1000;
const int MIN_SIZE_VARIABLE = 50;
unsigned int posBuffer = 0;

/*
 * Convierte una cadena de 0s y 1s en un byte de manera que si:
 * string = "11110001" -> byte = "10001111"
 * y posteriormente escribe el byte en el fichero file
 */
unsigned int escribirByte(string cadena, ofstream &file){
    unsigned char bitBuffer = 0;
    unsigned int bitActual = 0;
    while(!cadena.empty()){
        if(cadena.substr(0,1) == "1"){
            bitBuffer |= (1<<bitActual);
        }
        bitActual++;
        cadena.erase(0,1);
    }
    file.write(reinterpret_cast<const char*> (&bitBuffer), sizeof(bitBuffer));
    return bitActual;
}


/*
 * Convierte un byte en una cadena de 0s y 1s de manera que si:
 * byte = "10001111" -> string = "11110001"
 * y devuelve la cadena como valor
 */
string byte2string(unsigned char c){
    string stringBuffer = "";
    for(int bitActual = 0; bitActual < 8; bitActual++){
        if((c >> bitActual)%2 == 1){
            stringBuffer.append("1");
        }
        else{
            stringBuffer.append("0");
        }
    }
    return stringBuffer;
}



/**
 * Crea un nuevo fichero llamado <file_name>.huf con el contenido de
 * <file_name> comprimido de forma que ocupe menos espacio que el original.
 */
void compress(string file_name){
    ifstream inputFile(file_name, ios::binary);
    if (inputFile.is_open()){ //file_name está abierto
        unsigned char c;
        Table table;
        while(true){ // El bucle terminará if(inputFile.eof())
            inputFile.read(reinterpret_cast<char*>(&c), sizeof(unsigned char));
            if(inputFile.eof()) break;
            table.increment(c);
        }
        file_name = file_name.substr(0, file_name.size() - 2);
        file_name.append(".bz3");
        // Preparar fichero de escritura
        ofstream outputFile(file_name, ios::binary);

        unsigned char codigoCasos;
        if(table.getNumElements() == 0){ //Caso concreto: En el fichero de entrada hay 0 elementos.
            codigoCasos = 0;
            // Guardar info caso concreto (0)
            outputFile.write(reinterpret_cast<const char*> (&codigoCasos), sizeof(codigoCasos));
        }
        else if(table.getNumElements() == 1){ //Caso concreto: En el fichero de entrada hay 1 elemento.
            codigoCasos = 1;
            // Guardar info caso concreto (1)
            outputFile.write(reinterpret_cast<const char*> (&codigoCasos), sizeof(codigoCasos));

            // Escribir byte
            unsigned char byteUnico = table.getFirstKey();
            outputFile.write(reinterpret_cast<const char*> (&byteUnico), sizeof(byteUnico));
            // Escribir número de apariciones del byte
            unsigned long byteUnicoApariciones = table.getFirstValue();
            outputFile.write(reinterpret_cast<const char*> (&byteUnicoApariciones), sizeof(byteUnicoApariciones));
        }
        else{ //Caso genérico
            codigoCasos = 2;

            // Creación de la cola con prioridad.
            ColaConPrioridad queue;
            //Llenamos la cola con prioridad:
            queue.encolarTabla(table.getTable());

            // Creación del trie.
            Trie trie;
            trie.buildTrie(queue);

            // Creación tabla de códigos.
            TablaCodigos tabla_codigos;
            trie.recorrer_y_crear_tabla(trie.getTop(), "", &tabla_codigos);

            if (outputFile.is_open()){
                // Guardar info caso generico (2)
                outputFile.write(reinterpret_cast<const char*> (&codigoCasos), sizeof(codigoCasos));

                unsigned char longitudUltimoByte = 0;
                // Guardar espacio para longitudUltimoByte
                outputFile.write(reinterpret_cast<const char*> (&longitudUltimoByte), sizeof(longitudUltimoByte));

                trie.escribirArbol(trie.getTop(), outputFile);

                // Volver al principio del fichero input.
                inputFile.clear();
                inputFile.seekg(0, ios::beg);
                string buffer = ""; // Buffer para guardar la cadena de 1s y 0s
                while(true){
                    // Leer un byte del fichero de entrada
                    inputFile.read(reinterpret_cast<char*>(&c), sizeof(unsigned char));
                    if(inputFile.eof()) break;
                    buffer.append(tabla_codigos.getCodigo(c)); // Añadirlo al buffer
                    while(buffer.size()>= 8){
                        // Escribir el buffer en el fichero
                        escribirByte(buffer.substr(0, 8), outputFile);
                        buffer.erase(0,8);
                    }
                }
                if(!buffer.empty()){ // Por si acaso quedan algunos bits sin escribir
                    longitudUltimoByte = escribirByte(buffer, outputFile);
                    // Volver al principio (+1) del fichero output.
                    outputFile.clear();
                    outputFile.seekp(1);
                    outputFile.write(reinterpret_cast<const char*> (&longitudUltimoByte), sizeof(longitudUltimoByte));
                }
            }
            else{
                cout << "Error while writting " << file_name << endl;
                errors = 1;
            }
        }
        // Cerrar los ficheros de entrada y salida
        outputFile.close();
        inputFile.close();
    }
    else{   //Error opening file
        cout << "Error: File " << file_name << " could not be open" << endl;
        errors = 1;
    }
}

/**
 * Crea un nuevo fichero llamado <file_name> recuperando el contenido original
 * comprimido en <file_name>.huf.
 */
void uncompress(string file_name){
    string file_name_output = file_name;
    ifstream inputFile(file_name, ios::binary);
    if (inputFile.is_open()){ //file_name is open
        unsigned char codigoCasos = 0;
        // Leer codigo casos
        inputFile.read(reinterpret_cast<char*>(&codigoCasos), sizeof(unsigned char));

        if(codigoCasos == 0){ //Caso concreto: 0 elementos
            // Simplemente creamos el fichero
            ofstream outputFile(file_name_output, ios::binary);
        }
        else if(codigoCasos == 1){ //Caso concreto: 1 elemento
            ofstream outputFile(file_name_output, ios::binary);
            if (outputFile.is_open()){
                // Leer byte
                unsigned char byteUnico;
                inputFile.read(reinterpret_cast<char*>(&byteUnico), sizeof(unsigned char));
                // Leer número de apariciones del byte
                unsigned long byteUnicoApariciones;
                inputFile.read(reinterpret_cast<char*>(&byteUnicoApariciones), sizeof(unsigned long));
                // Escribir en fichero de salida
                for(; byteUnicoApariciones > 0; byteUnicoApariciones--){
                    outputFile.write(reinterpret_cast<const char*> (&byteUnico), sizeof(byteUnico));
                }
            }
        }
        else{ //Caso genérico
            unsigned char longitudUltimoByte = 0;
            // Leer cuantos bits válidos hay en el último byte
            inputFile.read(reinterpret_cast<char*>(&longitudUltimoByte), sizeof(unsigned char));

            ofstream outputFile(file_name_output, ios::binary);

            if (outputFile.is_open()){
                unsigned char c;
                Trie trie;
                trie.setTop(trie.leerArbol(inputFile)); // Creamos el árbol
                int sizeBuffer = 0;
                string buffer = "";
                while(!inputFile.eof()){
					while (buffer.size() < MAX_SIZE_BUFFER) {
                        // Recargar el buffer
                        inputFile.read(reinterpret_cast<char*>(&c), sizeof(unsigned char));
                        if(inputFile.eof()) break;
                        buffer.append(byte2string(c)); // Añadir al buffer el nuevo byte
                    }
                    sizeBuffer = buffer.size();
					while ((sizeBuffer - posBuffer) >= MIN_SIZE_VARIABLE) {
                        // Recuperar byte original
                        unsigned char byte = trie.obtenerByteOriginal(trie.getTop(), buffer, posBuffer);
                        // Escribir byte original en el fichero de salida
                        outputFile.write(reinterpret_cast<const char*> (&byte), sizeof(byte));
                    }
                    buffer = buffer.substr(posBuffer, buffer.size());
                    posBuffer = 0;
                }
                size_t numBitsBasura = (8 - longitudUltimoByte) % 8;
                sizeBuffer = buffer.size();
                while((sizeBuffer - posBuffer) > numBitsBasura){
                    // Recuperar byte original
                    unsigned char byte = trie.obtenerByteOriginal(trie.getTop(), buffer, posBuffer);
                    // Escribir byte original en el fichero de salida
                    outputFile.write(reinterpret_cast<const char*> (&byte), sizeof(byte));
                }
            }
            // Cerrar los ficheros de entrada y salida
            outputFile.close();
            inputFile.close();

        }
    }

    else{   //Error opening file
        cout << "Error: File " << file_name << " could not be open" << endl;
        errors = 1;
    }
}

int main(int argc,  char* argv[]){
    // Ver que los parámetros son correctos:
    switch (argc) {
        case 3: //2 parameters
            if(strcmp(argv[1], "-c") == 0){
                compress(argv[2]);
            }
            else if(strcmp(argv[1], "-d") == 0){
                uncompress(argv[2]);
            }
            else{
                showInvalidOptionError(argv[1]);
            }
            break;
        case 2: //1 parameter3.498s

            if(strcmp(argv[1], "-h") == 0){
                showHelp();
            }
            else if(strcmp(argv[1], "-v") == 0){
                showInfo();
            }
            else{
                showArgcError(argc-1);
            }
            break;
        default:
            showArgcError(argc-1);
    }

    return errors;
}
