/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rub�n Rodr�guez Esteban 737215 ******
 *           Jos� Mar�a Vallejo Puyal 720044 *****
 * Fecha : 16-3-19 *******************************
 * ***********************************************
 */

#include "ArbolTrie.h"


/*
 * Pre: <<e>> es una tupla <carater, frecuencia>
 * Post: Ha creado un arbol de manera que el campo <<dato>> del arbol nuevo
 *       toma el valor de <<e>>, el campo <<frecuencia>> toma el valor de la frecuencia de <<e>>
 *       y los punteros a los subarboles izquuierdo y derecho son nulos
 */
void crearArbol(ArbolTrie& a, carFrec& e){
	ArbolTrie::Nodo* aux = new ArbolTrie::Nodo;
	aux->dato = e;
	aux->frecuencia = 0;
	aux->der = nullptr;
	aux->izq = nullptr;
	a.raiz = aux;
}


/*
 * Pre: <<a>> es un arbol de tuplas <caracter, frecuencia> y
 *      <<frecuencia>> indica la frecuencia de caracteres a asignar
 * Post: Ha asignado al caracter recogido en el arbol <<a>> la frecuencia
 *       <<frecuencia>>
 */
void asignarFrecuencia(ArbolTrie& a, const int& frecuencia){
	ArbolTrie::Nodo* aux = a.raiz;
	aux->frecuencia = frecuencia;
}


/*
 * Pre: <<a>> y <<aIzq>> son dos arboles que almacenan tuplas <caracter, frecuencia>>
 * Post: Ha asignado <<aIzq>> como subarbol izquierdo de <<a>>
 */
void asignarArbolIzquierdo(ArbolTrie& a, const ArbolTrie& aIzq){
	ArbolTrie::Nodo* aux = a.raiz;
	aux->izq = aIzq.raiz;
}


/*
 * Pre: <<a>> y <<aDer>> son dos arboles que almacenan tuplas <caracter, frecuencia>>
 * Post: Ha asignado <<aDer>> como subarbol derecho de <<a>>
 */
void asignarArbolDerecho(ArbolTrie& a, const ArbolTrie& aDer){
	ArbolTrie::Nodo* aux = a.raiz;
	aux->der = aDer.raiz;
}


/*
 * Pre: <<a>> es un arbol que almacena una tupla <caracter, valor> y
 *      <<cF>> es una tupla <caracter, valor> libre
 * Post: Ha reemplazado la tupla <caracter, valor> almacenada en <<a>> por
 *       la tupla <<cF>>
 */
void asignarArbolCarFrec(ArbolTrie& a, const carFrec& cF){
	a.raiz->dato = cF;
}



/*
 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
 * Post: Ha devuelto la frecuencia del arbol <<a>>
 */
int obtenerArbolFrecuencia(const ArbolTrie& a){
	return a.raiz->frecuencia;
}


/*
 * Pre: <<a>> es un arbol que almacena una tupla <caracter, frecuencia>
 * Post: Ha devuelto la tupla <caracter, valor> asociada al arbol <<a>>
 */
carFrec obtenerCarFrec(const ArbolTrie& a){
	return a.raiz->dato;
}



/*
 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
 * Post: Ha devuelto el subarbol izquierdo de <<a>>
 */
ArbolTrie obtenerArbolIzquierdo(const ArbolTrie& a){
	ArbolTrie aF;
	ArbolTrie::Nodo* aux = a.raiz;
	aF.raiz = aux->izq;
	return aF;
}


/*
 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia>>
 * Post: Ha devuelto el subarbol derecho de <<a>>
 */
ArbolTrie obtenerArbolDerecho(const ArbolTrie& a){
	ArbolTrie aF;
	ArbolTrie::Nodo* aux = a.raiz;
	aF.raiz = aux->der;
	return aF;
}



/*
 * Pre: <<a>> es un arbol que almacena tuplas <caracter, frecuencia
 * Post: Devuelve <<true>> si y solo si el arbol <<a>> es hoja. En caso
 *       contrario devuelve <<false>>
 */
bool esHoja (const ArbolTrie& a){
	return a.raiz->izq == nullptr && a.raiz->der == nullptr;
}


/*
 * Pre: <<a1>> y <<a2>> son dos arboles que almacenan tuplas <caracter, frecuencia>
 * Post: Ha devuelto un arbol de manera que <<a1>> es el hijo izquierdo del nuevo arbol y
 *       y <<a2>> es el hijo derecho
 */
void unir(ArbolTrie& a1, ArbolTrie& a2, ArbolTrie& arbolFinal){
	ArbolTrie::Nodo* aux;
	aux = new ArbolTrie::Nodo;
	aux->frecuencia = obtenerArbolFrecuencia(a1) + obtenerArbolFrecuencia(a2);
	aux->izq = a1.raiz;
	aux->der = a2.raiz;
	arbolFinal.raiz = aux;
}


/*
 * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
 *      cada uno de sus nodos hoja correspondientes a los caracteres
 *      recogidos en un fichero de texto junto con sus frecuencias de
 *      aparicion y <<f>> es un flujo de escritura asociado a un fichero
 *      de texto donde se ha escrito una parte el arbol codificado
 * Post: Ha volcado en el fichero de texto asociado al flujo de escritura
 *       <<f>> el contenido restante del arbol <<a>>
 *
 */
void guardarArbolEnFicheroRec(ArbolTrie a, ofstream& f){
		// Comprobacion de si el nodo es o no una hoja
		if (!esHoja(a)){
			// Escritura de la frecuencia del nodo y de que es interno
			// No es hoja por lo canto escribo solo una N
			f << "N";
			// Comprobar el hijo izquierdo del nodo actual
			guardarArbolEnFicheroRec(obtenerArbolIzquierdo(a), f);
			// Comprobar el hijo el hijo derecho del nodo actual
			guardarArbolEnFicheroRec(obtenerArbolDerecho(a), f);
		}
		else {
			// El nodo es una hojaArbolTrie::Nodo* a
		  	// Obtencion de la tupla <caracter, frecuencia> del nodo
			carFrec c = obtenerCarFrec(a);
			// Obtencion del caracter y la frecuencia de la tupla y del tipo de hijo
			unsigned char caracter = c.getCaracter();
			// Escritura de la frecuencia del nodo y de que es interno
			//Es un nodo hoja por lo tanto escibo H y el caracter que contiene la hoja
			f << "H" << caracter;
		}
}





/*
 * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
 *      cada uno de sus nodos hoja correspondientes a los caracteres
 *      recogidos en un fichero de texto junto con sus frecuencias de
 *      aparicion
 * Post: Si se ha podido crear sin ningun problema el fichero de
 *       texto <<arbolNombreFichero>> ha guardado en dicho fichero una
 *       representacion del arbol con la siguiente estructura:
 *
 *       Estructura: explicar
 *
 */
void guardarArbolEnFichero(ArbolTrie a, const string arbolNombreFichero){
		// Flujo de escritura asociado al fichero
		ofstream f;
		// Apertura del fichero asociado al flujo
		f.open(arbolNombreFichero);
		if (f.is_open()){
			// Si el fichero se ha abierto correctamente
			guardarArbolEnFicheroRec(a, f);
		}
		else {
			cerr << "El fichero para guardar el arbol de codificacion "
					 << arbolNombreFichero << " es innacesible" << endl;
		}
		// Insertar salto de linea
		f << endl;
		// Cierre del flujo asociado al fichero
		f.close();
 }




 /*
  * Pre: <<a>> es un arbol que guarda tuplas <caracter, frecuencia> en
  *      cada uno de sus nodos hoja correspondientes a los caracteres
  *      recogidos en un fichero de texto junto con sus frecuencias de
  *      aparicion y <<f>> es un flujo de escritura asociado a un fichero
  *      de texto que guarda una representacion del contenido con el que debe
	*      contar el arbol <<a>>
  * Post: Ha volcado en el fichero de texto asociado al flujo de escritura
  *       <<f>> el contenido restante del arbol <<a>>
  *
  */
 int construirArbolDeFicheroRec(ArbolTrie& a, ifstream& f){
	// Caracter a leer
	char caracter;
 	// Intento de leer una nueva linea del fichero
  	f.get(caracter);
	// Comprobar si no se ha leido salto de linea
	if (caracter != '\n'){
		// Se ha leido correctamente se crea la tupla
		carFrec c = carFrec();
		crearArbol(a, c);
		int izdo = 0;
		int dcho = 0;

		if (caracter == 'N'){
			// Nodo interno
			// Como el nodo es interno tiene hijos
			// se van a visitar los hijos izquierdo y derecho
		  	// Crear el arbolTrie del hijo izquierdo

		  	ArbolTrie aIzq;
			izdo = 1+construirArbolDeFicheroRec(aIzq, f);
			asignarArbolIzquierdo(a, aIzq);

			// Crear el arbolTrie del hijo derecho
			ArbolTrie aDer;
			dcho = construirArbolDeFicheroRec(aDer, f);
		 	asignarArbolDerecho(a, aDer);
			return izdo + dcho;
		}
		else {
			// Nodo hoja izquierda o derecha
			// Asignar el caracter y su frecuencia de aparicion
			
			f.get(caracter);
			c.setCaracter((unsigned char)caracter);
			asignarArbolCarFrec(a, c);

			// Apuntar los nodos hijos a nil
			a.raiz->der = nullptr;
			a.raiz->izq = nullptr;
			return 2;
		}
	}
	return 0;
 }




/*
 * Pre: <<arbolNombreFichero>> es un fichero de texto que almacena el arbol
 *      de codigos Huffman de un fichero cuyo nombre es igual a <<NombreFichero>>
 * Post: Ha construido en <<a>> un arbol el arbol de codigos Huffman
 *       correspondiente al fichero <<NombreFichero>>
 */
int construirArbolDeFichero(const string arbolNombreFichero, ArbolTrie& a){
	// Flujo de escritura asociado al fichero
	ifstream f;
	// Apertura del fichero asociado al flujo
	f.open(arbolNombreFichero);
	if (f.is_open()){
		// Si el fichero se ha abierto correctamente
		return construirArbolDeFicheroRec(a, f);
	}
	else {
		cerr << "El fichero de lectura del arbol de codificacion "
				 << arbolNombreFichero << " es innacesible" << endl;
		return 0;
	}
	// Cierre del flujo asociado al fichero
	f.close();
}




/*
 * Pre:  <<codigos>> es un vector de caracteres de con capacidad
 *       para 256 caracteres, <<a>> es el trie que almacena en cada uno de sus
 *       nodos un caracter presente en el fichero junto con su correspondiente
 *       frecuencia, <<h>> SOBRA y <<codigo>> es un frgamento de codificacion
 *       del caracter actual apuntado por la raiz del arbol <<a>>
 * Post: Ha guardado en cada una de las componentes del vector <<codigos>>
 *       la codificacion binaria a cada caracter presente en el fichero
 *
 *       Ejemplo:
 *       A = 0
 *       B = 101
 *       C = 100
 *       D = 111
 *       E = 1101
 *       F = 1100
 *
 *       ............
 *
 */
void codificador(string codigos[],const ArbolTrie& a, string codigo){
    // Comprobar que el nodo actual es hoja
    if(esHoja(a)){	
        // Obtencion del caracter con su frecuencia
        carFrec c = obtenerCarFrec(a);
        // Guardar el codigo del caracter
        codigos[(int)c.getCaracter()]=codigo;
    }
    else{
        // Si el nodo no es hoja se inserta en la codificacion del caracter
        // un 0 para ir al hijo izuierdo y un 1 para ir al hijo derecho
        string codigoIzq = codigo + "0";
        string codigoDer = codigo + "1";
        // Llamadas recursivas al hijo izquierdo y derecho
        codificador(codigos,obtenerArbolIzquierdo(a),codigoIzq);
        codificador(codigos,obtenerArbolDerecho(a),codigoDer);
    }

}

/*
 * Pre: <<inicial>> es un puntero a un nodo que almacena el arbol huffman inicial,
 * 		<<actual>> es un puntero a un nodo que almacena el nodo del arbol que hay que comprobar,
 *  	<<num>> es el bit que estamos decodificando,
 *      <<sumar>> indica si hemos llegado a una hoja. 
 * Post: Devuelve el SrbolTrie que hay que comprobar en la siguiente llamada, si se ha llegado a una
 * 		 hoja escribe en el fichero de salida el caracter obtenido a partir del arbol y sumar = true.
 */

ArbolTrie decodificarCaracter(const ArbolTrie& inicial, ArbolTrie& actual,int num, ofstream& f, bool& sumar){
	if (num == 0){
		ArbolTrie comprobar = obtenerArbolIzquierdo(actual);
		if(!esHoja(comprobar)){
			sumar=false;
			return comprobar;
		}else{
			sumar=true;
			carFrec tupla = obtenerCarFrec(comprobar);
			// Escritura del caracter en el fichero
			f << tupla.getCaracter();
			return inicial;
		}
	}
	else if (num == 1){
		ArbolTrie comprobar = obtenerArbolDerecho(actual);
		if(!esHoja(comprobar)){
			sumar=false;
			return comprobar;
		}else{
			carFrec tupla = obtenerCarFrec(comprobar);
			// Escritura del caracter en el fichero
			f << tupla.getCaracter();
			sumar=true;
			return inicial;
		}
	}else{
		sumar=false;
		return inicial;
	}

}

/*
 * Pre: <<nombreFichero>> es el nombre de un fichero comprimido con
 *      extension de archivo .huf y <<a>> es el arbol de codigos huffman
 *      empleado en la codficacion del fichero <<nombreFichero>>
 * Post: Ha devuelto como resultado un fichero descomprimido con el contenido
 *       del del fichero comprimido <<nombreFichero>> empleando para la
 *       descompresion el arbol de codigos Huffman <<a>>
 */

void descifraFichero(string nombreFichero, ArbolTrie& trie,int numB){
	// Flujo de lectura asociado al fichero.huf
	// Flujo de escritura asociado al fichero de texto
	ofstream f2;
	// Apertura del fichero asociado al flujo
	string ficheroSalida = nombreFichero + "BW";
	cout << "El nombre del fichero descomprimido es "<< ficheroSalida << endl;
	f2.open(ficheroSalida);
	ifstream f(nombreFichero, ios::binary);
  	int actuales=0;
  	bool sumar;
  	char c;
	//Se ignoran numB + 1 Bytes ya que es la representacion del arbol en el fichero comprimido.
	f.ignore(numB+1);
	f >> actuales;
	f.ignore(1);
	ArbolTrie t1 = trie;
	bool continuar = true;
  	while (continuar){
			f.get(c);
			// Parsear el contenido en grupos de bytes e ir recorriendo el arbol descifrando las letras.
			for (int i = 7; i >= 0; i--){
				int a =  ((c >> i) & 1);
				t1 = decodificarCaracter(trie,t1,a,f2,sumar);
				if(sumar){
					actuales--;
					if(actuales == 1){
						continuar = false;
						break;
					}
					sumar=false;	
				}
			}
  	}
}