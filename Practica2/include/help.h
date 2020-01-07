/*
 *
 *  Created on: Mar 7, 2019
 *      Author: Alonso Muñoz García
 *      		Víctor Peñasco Estívalez
 */

#ifndef _HELP_H_
#define _HELP_H_

#include <iostream>

using namespace std;

void showHelp();
void showInfo();
void showArgcError(int numParameters);
void showInvalidOptionError(string option);

#endif
