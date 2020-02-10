<p align="center">
  <a href="https://example.com/">
    <img src="https://i.ibb.co/K99Ys0r/Logo-APD.png" alt="Logo" width=800 height=350>
  </a>

  <h3 align="center">Algorithm for difficult problems</h3>

  <p align="center">
    <b>Practices of Algorithm for difficult problems - course 2019-20</b> <br>
  </p>
</p>

# 1. Description

In this repository the two practices done during **the course of the algorithmic for difficult problems** have been described 
carefully. Both practices have been implemented in **Java**. Apart from providing the perfectly documented code, the sequence 
of steps to follow will explained in order to compile and execute each practice.

&nbsp;

# 2. Practice 1 - Min cut problem

In the first practice the following problem has been resolved:

## 2.1 Explanation of the problem

For tax reasons Amazon has decided to split its business in two, but wants this to be less detrimental to its sales volume, as 
some customers may decide not to make a purchase when they have to divide it into two purchases from different suppliers. The
participation will be done by selecting (separately) some of the articles ́ıculos for the brandamazonyotros paraamazonymas. In 
a first approximation on we have the information on that pairs of products have ever been bought together, that is, we have a 
table of booleans so that T(i,j) is true if the products indexed by i and j indexes have ever been bought together and in 
another case. The aim is to make a particion of the products between Amazon and Amazonymas, so that the number of pairs of 
products that have been bought together once and are assigned to different suppliers is minimised.

## 2.2 Implementation

In order to resolve this problem the following steps have been done:

* Design and implement a data structure that stores the data with the necessary attributes for each product. 
* Identify the proposed problem as a graph problem, specifically the **minimun cut** or **min cut** problem which consists of
  splitting the vertices of a graph into two disjointed sets with a number of edges m ́ınimo between the two pieces.
* Implement an algorithm whose aim is make a partition close to the optimum of the products. For this purpose, 
  **the Karger probabilistic algorithm** has been programmed.
* Test the implemented algorithm with a reasonable number of data and with different random generators.
* Implement the Karger-Stein algorithm, which is an improvement on Karger's algorithm.
* Extend the algorithm to allow cases where products can be purchased more than once together, minimally justifying the
  performance achieved with the new algorithm.
  
## 2.3 Compilation and execution of the source code

The code of the practice is located in **Practica 1** and is composed by the following files:

* **gen_tests_cases.py** is a python script used to generate the randomized test cases to verify the behaviour of the code.
* **TestSets** is a folder which contains the tests cases.
* **src** is a folder are located the java files. 
* **ejecutar1.sh** is a bash script which allows the user to compile and execute the min-cut problem using karger or 
  karger-Stein algorithms with different tests cases and random number generators.
* **MemoriaP1_APD.pdf** is a report which explains with so much detail all the work done. 
* **LEEME.md** which contains the instructions (commands) to compile and execuete code.

&nbsp;

# 3. Practice 2 - A personal version of bzip2 compressor

In the second practice the following problem has been resolved:

## 3.1 Explanation of the problem

The Burrows-Wheeler transform (BWT) converts any string into a string with a much higher frequency of adjacent equal symbols,
and this process is reversible. one of its common uses is in the ́on text compressor, as it is used in conjunction with 
Move-To-Front and Huffman to obtain the popular bzip2 compressor. The most common way to calculate the BWT is by properly 
ordering the input chain rotations. This can be done directly at a cost of O(n2logn), where the length of the chain, or much
efficiently with cost in quasi-linear time using suffix vectors.

## 3.1 Implementation of the problem

In order to resolve this problem the following steps have been done:

* Implement the suffix vector data structure as efficiently as possible.
* Implement the **Burrows-Wheeler transformation** using suffix vectors, trying to reach a quasi-linear cost in time.
* Implement the compression algorithm **Move-to-Front**.
* Test all the implemented algorithms with a sufficient number of interesting data and of sufficient size.
* Using some **Huffman** public implementation, combine BWT, Move-To-Front and Huffman to obtain a 
  similar abzip2 compressor which has been named like **bzip3**.
  
## 3.3 Compilation and execution of the source code

The code of the practice is located in **Practica 2** and is composed by the following files:

* **compile.sh** is a bash script which allows to compile the java code files and also the Huffman code written in C++.
* **bzip3.ah** is a bash script which allows to execute the compressor.
* **test_bzip3.sh** is a bash script which test the bzip3 compressor realising compressions and uncompressions of files and
verifying if both are equal. Apart from that the time spent in each process is shown to the user too.
* **ejecutar2.sh** is a bash script which allows the user to compile and execute the compressor automatically.
* **LEEME.md** is a file that contains the instructions (commands) to compile and execuete code.
* **Tets** is a folder which stores the different files used to test the compressor.
* **src** is the folder where the code java files are located.
* **Huffman** is the directory where is stored the C++ implementation Huffman compressor.
* **MemoriaP2_APD.pdf** is a report which explains with so much detail all the work done.

&nbsp;

# 4 Authors

* [Victor Peñasco](https://github.com/vpec) - 741294
* [Rubén Rodríguez](https://github.com/ZgzInfinity) - 737215





























