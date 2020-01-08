if [ $1 = "-c" ]
then
    # Compile c++ programm (Huffman)
    make
    # Compile java programm
    javac src/*.java
    # Execute java programm
    java -classpath src Main -c $2
    ./huf -c $2
    rm $2
    exit 0
fi
if [ $1 = "-u" ]
then
    # Compile c++ programm (Huffman)
    make
    # Compile java programm
    javac src/*.java
    # Execute java programm
    ./huf -d $2
    java -classpath src Main -u $2
    exit 0
fi
