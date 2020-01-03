javac Mincut/src/*.java
echo "testSet_4 test cases execution"
time java -classpath Mincut/src Mincut -k -rg1 6184 TestSets/testSet_4
time java -classpath Mincut/src Mincut -ks -rg1 64 TestSets/testSet_4
time java -classpath Mincut/src Mincut -k -rg2 6184 TestSets/testSet_4
time java -classpath Mincut/src Mincut -ks -rg2 64 TestSets/testSet_4
time java -classpath Mincut/src Mincut -k -rg3 6184 TestSets/testSet_4
time java -classpath Mincut/src Mincut -ks -rg3 64 TestSets/testSet_4
echo "test1 test cases execution"
time java -classpath Mincut/src Mincut -k -rg1 26 TestSets/test1
time java -classpath Mincut/src Mincut -ks -rg1 8 TestSets/test1
time java -classpath Mincut/src Mincut -k -rg2 26 TestSets/test1
time java -classpath Mincut/src Mincut -ks -rg2 8 TestSets/test1
time java -classpath Mincut/src Mincut -k -rg3 26 TestSets/test1
time java -classpath Mincut/src Mincut -ks -rg3 8 TestSets/test1
echo "test2 test cases execution"
time java -classpath Mincut/src Mincut -k -rg1 26 TestSets/test2
time java -classpath Mincut/src Mincut -ks -rg1 8 TestSets/test2
time java -classpath Mincut/src Mincut -k -rg2 26 TestSets/test2
time java -classpath Mincut/src Mincut -ks -rg2 8 TestSets/test2
time java -classpath Mincut/src Mincut -k -rg3 26 TestSets/test2
time java -classpath Mincut/src Mincut -ks -rg3 8 TestSets/test2
echo "test3 test cases execution"
time java -classpath Mincut/src Mincut -k -rg1 26 TestSets/test3
time java -classpath Mincut/src Mincut -ks -rg1 8 TestSets/test3
time java -classpath Mincut/src Mincut -k -rg2 26 TestSets/test3
time java -classpath Mincut/src Mincut -ks -rg2 8 TestSets/test3
time java -classpath Mincut/src Mincut -k -rg3 26 TestSets/test3
time java -classpath Mincut/src Mincut -ks -rg3 8 TestSets/test3


rm Mincut/src/*.class