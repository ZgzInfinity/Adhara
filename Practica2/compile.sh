# Creat directory for binary files if not exists
[ -d bin ] || mkdir bin
echo "Preparing bzip3 libraries"
make clean --directory Huffman > /dev/null
# Compile c++ programm (Huffman)
make --directory Huffman > /dev/null
# Compile java programm
javac -d bin src/*.java