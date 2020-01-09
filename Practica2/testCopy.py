# Authors:
# Penasco Estivalez, Victor Miguel  741294
# Rodriguez Esteban, Ruben          737215

# Import libraries
import random
import numpy
import sys

if len(sys.argv) == 4 :
    # Variable values if 3 arguments are provided
    NUM_CASES = int(sys.argv[1])
    MIN_NUM_PRODUCTS = int(sys.argv[2])
    MAX_NUM_PRODUCTS = int(sys.argv[3])
else:
    # Default values
    NUM_CASES = 1
    MIN_NUM_PRODUCTS = 50
    MAX_NUM_PRODUCTS = 85

# Test cases with random values
for i in range(NUM_CASES):
    # Random number of products
    num_products = random.randint(MIN_NUM_PRODUCTS, MAX_NUM_PRODUCTS)
    # Initialize matrix with ones
    t = numpy.ones((num_products, num_products))
    col_init = 0
    bound = numpy.random.uniform()
    # Iterate rows
    for row in range(num_products):
        # Iterate cols (upper triangle matrix)
        col_init = col_init + 1
        for col in range(col_init, num_products):
            if(numpy.random.uniform() < bound):
                cell_value = 0
            else:
                cell_value = 1
            # Fill both symmetric components in matrix with random 1 or 0
            t[row][col] = t[col][row] = cell_value
    # Open file
    fw = open("testSet_" + str(i + 1),"w+")
    # Open number of products
    fw.write(str(num_products)+ "\n")
    # Write matrix to file
    for row in range(num_products):
        for col in range(num_products):
            # Different columns separated by single space
            fw.write(str(int(t[row][col]))+ " ")
        # Different rows separated by new line
        fw.write("\n")
    # Close file
    fw.close()

# Particular case with random dimension matrix filled with ones
# Random number of products
num_products = random.randint(MIN_NUM_PRODUCTS, MAX_NUM_PRODUCTS)
# Initialize matrix with ones
t = numpy.ones((num_products, num_products))
# Open file
fw = open("testSet_ones","w+")
# Open number of products
fw.write(str(num_products)+ "\n")
# Write matrix to file
for row in range(num_products):
    for col in range(num_products):
        # Different columns separated by single space
        fw.write(str(int(t[row][col]))+ " ")
    # Different rows separated by new line
    fw.write("\n")
# Close file
fw.close()

# Particular case with random dimension matrix filled with zeros (eye matrix)
# Random number of products
num_products = random.randint(MIN_NUM_PRODUCTS, MAX_NUM_PRODUCTS)
# Initialize matrix with ones
t = numpy.eye(num_products)
# Open file
fw = open("testSet_eye","w+")
# Open number of products
fw.write(str(num_products)+ "\n")
# Write matrix to file
for row in range(num_products):
    for col in range(num_products):
        # Different columns separated by single space
        fw.write(str(int(t[row][col]))+ " ")
    # Different rows separated by new line
    fw.write("\n")
# Close file
fw.close()




