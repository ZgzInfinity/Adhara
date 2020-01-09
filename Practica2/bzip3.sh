if [ "$1" = "-c" ]
then
    echo "Preparing bzip3 libraries"
    # Compile c++ programm (Huffman)
    make --directory Huffman > /dev/null
    # Compile java programm
    javac -d bin src/*.java
    # Execute java programm
    echo "Compresing $2 file"
    java -classpath bin Main -c $2
    partName="BW"
    file=$2$partName
    Huffman/huf -c $file
    rm $file
    exit 0
fi
if [ "$1" = "-u" ]
then
    echo "Preparing bzip3 libraries"
    # Compile c++ programm (Huffman)
    make --directory Huffman > /dev/null
    # Compile java programm
    javac -d bin src/*.java
    # Execute java programm
    echo "Uncompresing $2 file"
    Huffman/huf -d $2
    java -classpath bin Main -u $2
    exit 0
fi
