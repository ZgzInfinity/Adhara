if [ "$1" = "-c" ]
then
    # Execute java programm
    echo "Compressing $2 file"
    java -classpath bin Main -c $2
    partName="BW"
    file=$2$partName
    Huffman/huf -c $file
    rm $file
    exit 0
fi
if [ "$1" = "-u" ]
then
    # Execute java programm
    echo "Uncompressing $2 file"
    Huffman/huf -d $2
    java -classpath bin Main -u $2
    partName="BW"
    tempFile=$2$partName
    rm $tempFile
    exit 0
fi