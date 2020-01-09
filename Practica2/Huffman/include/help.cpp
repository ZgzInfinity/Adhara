/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#include "help.h"

using namespace std;

/*
 * Muestra la ayuda por pantalla.
 */
void showHelp(){
    cout << "  _         __   _        _      " << endl;
    cout << " | |_ _  _ / _| | |_  ___| |_ __ " << endl;
    cout << " | ' \\ || |  _| | ' \\/ -_) | '_ \\" << endl;
    cout << " |_||_\\_,_|_|   |_||_\\___|_| .__/" << endl;
    cout << "                           |_|   " << endl;
    cout << "" << endl;
    cout << "Huf is a tool designed for compressing files (text files or binary files) and uncompressing them." << endl;
    cout << "Please note that Huf CANNOT compress directories, just single files." << endl;
    cout << "" << endl;
    cout << "-HOW TO COMPRESS A FILE?-" << endl;
    cout << "   Type in command line:" << endl;
    cout << "       $huf -c <file_name>" << endl;
    cout << "   <file_name> must be replaced by an existing file name (or its path)." << endl;
    cout << "   Huf compressing option will generate a .huf extension file (same name as the original file) in the execution directory." << endl;
    cout << "" << endl;
    cout << "Examples:" << endl;
    cout << "       $huf -c document.txt" << endl;
    cout << "   This command will generate a file named document.huf" << endl;
    cout << "       $huf -c hello_world" << endl;
    cout << "   This command will generate a file named hello_world.huf" << endl;
    cout << "" << endl;
    cout << "-HOW TO UNCOMPRESS A FILE?-" << endl;
    cout << "   Type in command line:" << endl;
    cout << "       $huf -d <file_name>" << endl;
    cout << "   <file_name> must be replaced by a .huf extension file (or its path)." << endl;
    cout << "   This command will generate the original file in the execution directory." << endl;
    cout << "" << endl;
    cout << "Examples (previously compressed files):" << endl;
    cout << "       $huf -d document.huf" << endl;
    cout << "   This command will generate a file named document.txt" << endl;
    cout << "       $huf -d hello_world.huf" << endl;
    cout << "   This command will generate a file named hello_world" << endl;
    cout << "" << endl;
    cout << "Type huf -v for more information about Huf." << endl;
}

/*
 * Muestra la información por pantalla.
 */
void showInfo(){
    cout << "  _            __ " << endl;
    cout << " | |          / _|" << endl;
    cout << " | |__  _   _| |_ " << endl;
    cout << " | '_ \\| | | |  _|" << endl;
    cout << " | | | | |_| | |  " << endl;
    cout << " |_| |_|\\__,_|_|  " << endl;
    cout << "" << endl;
    cout << "Welcome to Huf, a simple tool designed for compressing files."
        << endl;
    cout << "----------------------------" << endl;
    cout << "AUTHORS:" << endl;
    cout << "   Alonso Muñoz García" << endl;
    cout << "   Víctor Peñasco Estívalez" << endl;
    cout << "----------------------------" << endl;
    cout << "Version = 0.0.1" << endl;
}

/*
 * Muestra un mensaje de error que sucede cuando el número de
 * argumentos con el que se ha invocado el programa es incorrecto.
 */
void showArgcError(int numParameters){
    cout << "Error: " << numParameters <<
        " parameters were given, 2 were expected." << endl;
    cout << "Correct usage: huf [ -c | -d ] <file_name>" << endl;
    cout << "Type \"huf -h\" for help." << endl;
}

/*
 * Muestra un mensaje de error que sucede el valor de uno de los argumentos
 * no coincide con ninguna de laas opciones del programa disponibles.
 */
void showInvalidOptionError(string option){
    cout << "Error: Invalid " << option << " option. " << endl;
    cout << "Correct usage: huf [ -c | -d ] <file_name>" << endl;
    cout << "Type \"huf -h\" for help." << endl;
}
