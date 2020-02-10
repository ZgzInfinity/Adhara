# Create directory for binary files if not exists
[ -d bin ] || mkdir bin
echo "Preparing bzip3 libraries"
# make clean --directory=Huffman > /dev/null
cd Huffman
make clean > /dev/null
cd ..
# Compile c++ programm (Huffman)
# make --directory=Huffman > /dev/null
cd Huffman
make > /dev/null
cd ..
# Compile java programm
javac -d bin src/*.java