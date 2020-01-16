partNameCopy="C4T"
partNameExtension=".bz3"
cp "$1" "$1$partNameCopy"
time ./bzip3.sh -c "$1"
time ./bzip3.sh -u "$1$partNameExtension"
diff "$1" "$1$partNameCopy"
if [ $? -eq 0 ]
then
    echo "Successful test: 100% coincidence"
else
    echo "Test failed"
fi
rm "$1$partNameCopy"
rm "$1$partNameExtension"
