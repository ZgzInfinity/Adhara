all: main.cpp help.o freq_table.o ColaConPrioridad.o Trie.o  Nodos.o tabla_codigos.o
	g++ main.cpp help.o freq_table.o ColaConPrioridad.o Trie.o Nodos.o tabla_codigos.o -o huf -std=c++11 -O3 -Wall

help.o: include/help.cpp include/help.h
	g++ include/help.cpp include/help.h -c

freq_table.o: include/freq_table.cpp include/freq_table.h
	g++ include/freq_table.cpp include/freq_table.h -c

ColaConPrioridad.o: include/ColaConPrioridad.cpp include/ColaConPrioridad.h
	g++ include/ColaConPrioridad.cpp include/ColaConPrioridad.h -c

Trie.o: include/Trie.cpp include/Trie.h
	g++ include/Trie.cpp include/Trie.h -c

Nodos.o: include/Nodos.cpp include/Nodos.h
	g++ include/Nodos.cpp include/Nodos.h -c

tabla_codigos.o: include/tabla_codigos.cpp include/tabla_codigos.h
	g++ include/tabla_codigos.cpp include/tabla_codigos.h -c

clean:
	rm -rf huf
	rm -rf *.o
	rm -rf *.gch
	rm -rf include/*.gch
