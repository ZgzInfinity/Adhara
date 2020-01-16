/*
 * ***********************************************
 * Asignatura : Algoritmia basica ****************
 * Autores : Rubén Rodríguez Esteban 737215 ******
 *           José María Vallejo Puyal 720044 *****
 * Fecha : 25-3-19 *******************************
 * ***********************************************
 */
 

/*
 * Fichero de implementacion del modulo Huffman
 */

#include "Huffman.h"

using namespace std;


/*
 * Pre: <<c>> es una cola de prioridades o monticulo donde se almacenan todos los
 *      caracteres y sus correspondientes frecuencias de caracteres ordenados por
 *      orden decreciente de frecuencia. <<a>> es un trie en cuyos nodos residen los
 *      caracteres y sus correspondientes frecuencias
 * Post: <<h>> es el resultado de aplicar el algoritmo de codificacion Huffman de tal forma
 *        que a los caracteres con mayor valor de frecuencia se les ha asignado un codigo de
 *        compresion menor y a los digitos de mayor frecuencia un codigo mayor, garantizando
 *        asi un tamanyo de fichero comprimido menor
 */
void generaHuffman(Heap& c, ArbolTrie& a){
	// Obtener primer elmentos del monticulo
    ArbolTrie pri = min(c);
	// Eliminar el primer elemento del monticulo
    eliminarMin(c);
	// Obtener el nuevo primer elemento del monticulo
    ArbolTrie segundo = min(c);
	// Eliminar el nuevo primer elemento del monticulo
    eliminarMin(c);
	// Si quedan elementos
    if(numElementos(c)>0){
		// Crear un nuevo nodo uniendo los otros dos
        ArbolTrie aux;
        unir(pri, segundo, aux);
		// Insertar el nuevo formado en la cola de prioridades
        anyadir(c,aux);
        generaHuffman(c, a);
    }
	else{
		// Crear un nuevo nodo uniendo los otros dos
        unir(pri, segundo, a);
    }
}



/*
 * Pre:  <<ficheroEntrada>> es un fichero de texto que almacena
 *       caracteres tanto especiales como alfanumericos
 * Post: Si el fichero se ha abierto correctamente ha leido su contenido
 *       caracter a caracter guardando la representacion binaria de cada
 *       uno en el vector <<contenidoFichero>>
 */
unsigned int leerFichero(string ficheroEntrada, string& contenidoFichero, string codigos[]){
    // Flujo de lectura asociado al fichero
    ifstream f;
    unsigned int total = 0 ;
    // Apertura del fichero de texto
    f.open(ficheroEntrada);
    if (f.is_open()){
      // Si el fichero se abre correctamente
      // Leer el fichero caracter a caracter
      char c;
      unsigned char c2;
      f.get(c);
      total++;
      // Mientras queden caracteres por leer
      while (!f.eof()){
          c2 = (unsigned char)c;
          // Guardar la codificacion binario del caracter leido
          contenidoFichero += codigos[(int)c2];
          // Leer el siguiente caracter
          f.get(c);
          total++;
      }

      // Cierre del flujo de lectura
      f.close();
    }
    else {
      // El fichero no se abre correctamente
      cerr << "Error al abir el fichero " << ficheroEntrada << endl;
    }

    if(contenidoFichero.length()%8 != 0){
      string anyadir = "";
	    for(int i = 0; i < (int)contenidoFichero.length()%8  ; i++){
		    anyadir = anyadir + "0"; 
	    }
      contenidoFichero = contenidoFichero + anyadir;
    }

	return total;
}



/*
 * Pre:  <<contenido>> es una secuencia que almacena un conjunto
 *       de caracteres representados con su codificacion binaria
 *       caracteres tanto especiales como alfanumericos
 * Post: Si el fichero <<ficheroSalida>> se ha creado correctamente ha
 *       volcado el contenido almacenado en <<contenido>>
 *       comprimido. En caso contrario ha mostrado por salida estandar el
 *       error de apertura.
 */
void escribirFichero(const string contenido, string ficheroSalida, unsigned int total){
  // Flujo de lectura asociado al fichero
  ofstream f;
  // Apertura del fichero de texto
  f.open(ficheroSalida, ios::app);
  f << total << endl;
  if (f.is_open()){
    // Si el fichero se abre correctamente
    // Parsear el contenido del fichero para guardalo en grupos de bytes
    int indice = 0;
    //Mientras queden caracteres por leer
    while(indice < int(contenido.length())){
        if((indice - contenido.length() < TAMANYO_BYTE)){
            f << (char)std::stoi(contenido.substr(indice, indice - contenido.length()), nullptr, BASE);
        }
        else{
            std::stoi(contenido.substr(indice, TAMANYO_BYTE), nullptr, BASE);
            f << (char)std::stoi(contenido.substr(indice, TAMANYO_BYTE), nullptr, BASE);
        }
        // Nuevo byte leido
        indice += TAMANYO_BYTE;
    }
    // Cierre del flujo de escritura
    f.close();
  }
  else {
    // El fichero no se abre correctamente
    cerr << "Error al abir el fichero " << ficheroSalida << endl;
  }
}



/*
 * Pre: <<ficheroEntrada>> es un fichero de texto que almacena una secuencia
 *      de caracteres
 * Post: Si el fichero <<ficheroEntrada>> se ha abierto se ha podido leer correctamente
 *       ha almacenado en <<h>> el resultado de aplicar a dicho fichero el
 *       algoritmo de compresion Huffman de modo que a los caracteres con
 *       mayor frecuencia de aparicion se les ha asignado un codigo de compresion
 *       menor y a los caracteres con una frecuencia de aparicion menor se les ha
 *       asignado un codigo de compresion menor
 */
void comprimir(string ficheroEntrada){
	// Vector de frecuencias de cada caracter

  int frecsPorChar[MAX_CARACTERES];

	// Inicializar la frecuencia de aparicion de cada caracter a cero
  iniciarFrecuencias(frecsPorChar);
  

  // vector de codigos binarios para cada caracter
  string codigos[MAX_CARACTERES];

  // Iniciar codificaciones binarias
  iniciarCodificaciones(codigos);

	// Contabilizar las frecuencias de cada caracter
  frecuenciasPorCaracter(ficheroEntrada, frecsPorChar);
  
  // Creacion del monticulo de prioridades
	Heap hp;
	crearVacio(hp);

	// Rellenado del monticulo a partir de las frecuencias de cada caracter
	rellenar(hp, frecsPorChar);

  // Construccion del arbol de codificacion Huffman
	ArbolTrie huff;
	generaHuffman(hp, huff);

	// Codificacion de caracteres con codigos binarios
	codificador(codigos, huff, "");

	// Nombre del fichero binario codificado de salida esto solo vale si es txt
  string ficheroSalida = ficheroEntrada.substr(0, ficheroEntrada.length() - 2) + ".bz3";

  // Generacion del fichero con el arbol comprimido
  guardarArbolEnFichero(huff, ficheroSalida);

  // Cadena donde se almacena la informacion del fichero
  string contenidoFichero = "";

  // Leer fichero de texto y guardar su contenido codificado en binario
  unsigned int total  = leerFichero(ficheroEntrada, contenidoFichero, codigos);

  // Escribir el contenido comprimido en un nuevo fichero
  escribirFichero(contenidoFichero, ficheroSalida, total);
}



/*
 * Pre: <<nombreFichero>> es el nombre de un fichero comprimido con la
 *      codificacion de Hufmman y que tiene extension .huf
 * Post: Ha creado un nuevo fichero resultado de llevar a cabo la descompresion
 *       del fichero <<nombreFichero>> de modo que el contenido del nuevo
 *       fichero es identico al del fichero original antes de hacer la
 *       compresion
 */
void descomprimir(const string nombreFichero){
    // Construccion del arbol de codigos Huffman para descomprimir
    int bytes;
    ArbolTrie a;
  	bytes =  construirArbolDeFichero(nombreFichero, a);
    // Efectua la descompresion del fichero
    descifraFichero(nombreFichero, a, bytes);
}