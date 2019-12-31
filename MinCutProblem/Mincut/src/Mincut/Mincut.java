/**
 ********************************************
 **** Algorithm for difficult problems ******
 **** Practice 1: Minimum cut problem *******
 **** Authors: ******************************
 **** Victor Pe�asco Estivalez - 741294 *****
 **** Ruben Rodriguez Esteban - 737215 ******
 **** Date: 29 - 12 - 2019 ******************
 ********************************************
 */


package Mincut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;


public class Mincut {

	// Minimun number of vertices to execute the karger algorithm
	private static int MIN_NUMBER_VERTEX = 2;
	// Number of iterations for this algorithm
	private static int ATTEMPTS = 20;
	
	
	/**
	 * 
	 * @param productsFile is the file which contains the matrix of products
	 * @return a graph whose vertices are the products read from the file << productsFile>>
	 * 		   and whose edges are the pairs of products that have ever been bought together.
	 */
	@SuppressWarnings("resource")
	public static  Hashtable <String, Node> readProducts(File productsFile, String attributesFilePath) {
		// Stores the graph where the vertices are the products
		// and the edges are the products that have been bought together
		Hashtable <String, Node> graph = new Hashtable<>();	
		
		// Open the file that contains the matrix the products 
		Scanner input;
		
		
		try {
			input = new Scanner(productsFile);
			BufferedReader br = null;
			if(attributesFilePath != null) {
				try {
					// Open the file that contains the products' attributes
					br = new BufferedReader(new FileReader(attributesFilePath));
				}
				catch(IOException e) {
					br = null;
				}
			}
			
			
			
			// Stores the number of products (first line of the file
			int numProducts = Integer.parseInt(input.next());

			// For each product do
			for (Integer i = 0; i < numProducts; i++) {
				// Try to get product's attribute
				List<String> attributes = new ArrayList<>();
				if(br != null) {
					String line = br.readLine();
					// Replace possible whitespace with tabs
					line = line.replaceAll(" ", "\t");
					line = line.replaceAll("\t+", "\t");
					// Store attributes as list of strings
					attributes = Arrays.asList(line.split("\t"));
				}
				
				Product p;
				if(attributes.isEmpty()) {
					// Create a new product with a new key
					p = new Product(i.toString());
				}
				else {
					// Create a new product with a new key and product attributes
					p = new Product(i.toString(), attributes);
				}
				
				// Hash table to store products that have ever been purchased with the product <<i>>
				Hashtable<String, Product> products = new Hashtable<>();
				
				// Added the product i to the hash table
				products.put(p.getId(), p);
				
				// Hash table which contains the adjacent vertices to the vertex i of the graph
				Hashtable<String, Edge> adjNodes = new Hashtable<String, Edge>();
				
				// Create a new vertex in the graph with the product <<i>>
				Node n = new Node(p.getId(), products, adjNodes);
				
				// Added the product to the graph
				graph.put(n.getId(), n);
			}
				
			// For each product do
			for (Integer i = 0; i < numProducts; i++) {
				// Get the product <<i>>
				Node n = graph.get(i.toString());
				
				// Hash table to store the adjacent vertex to the vertex <<i>>
				Hashtable<String, Edge> adjNodes = n.getAdjNodes();
				
				// For each product do
				for (Integer j = 0; j < numProducts; j++) {
					String s = input.next();
					// If the elements are different and the have been bought at the same time
					if (i != j && !s.contentEquals("0")) {
						// Creation of a edge between both vertices
						Edge e = new Edge(Integer.parseInt(s), graph.get(j.toString()));
						adjNodes.put(j.toString(), e);
					}
				}
			}
			return graph;	
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
	/**
	 * Make a partition close to the optimum of the products using the karger
	 * algorithm in order to resolve the minimun cut problem
	 * @param graph is the graph of products which is going to be separated in two
	 *		  disjointed sets
	 */
	public static void karger(Hashtable <String, Node> graph) {
		
		// While two different vertices of the graph can be chosen
		while (graph.size() > MIN_NUMBER_VERTEX) {
				
			// Get the two nodes to be joined.
			Object key [] = graph.keySet().toArray();
			String vertexKeys [] = Arrays.copyOf(key,key.length,String[].class);
			
			// Generator of random numbers
			Random random = new Random(); 
			
			// The first node is chosen randomly between the vertices of the graph
			int random1 = random.nextInt(vertexKeys.length);
			Node node1 = graph.get(vertexKeys[random1]);
				
			// Check if the vertex has another adyacent vertex
			if (!node1.getAdjNodes().isEmpty()) {
					
			// List that contains the edges of the other vertices with which it is connected
			List<String> edgesKeys = new ArrayList<>();
					
			// For each adjacent vertex the edge is added
			for(Entry<String, Edge> entry : node1.getAdjNodes().entrySet()) {
				for (int i = 0; i < entry.getValue().getDegree(); i++) {
					edgesKeys.add(entry.getKey());
				}
			}
			// Safety check
			if(edgesKeys.isEmpty()) {
				System.err.println("Invalid graph, vertex not connected");
				System.exit(3);
			}
					
			// The second vertex if chosen between the rest of the vertices of the graph
			// which are connected with the fisrt one
			int random2 = random.nextInt(edgesKeys.size());
			String nodeKey = edgesKeys.get(random2);
			Node node2 = node1.getAdjNodes().get(nodeKey).getVertex();
					
			// Remove the vertices of the graph which are going to be joined in a new one
			graph.remove(node1.getId());
			graph.remove(node2.getId());
			
			// Union of hashtable
			Hashtable<String, Product> unionProducts = new Hashtable<>();
			// Put all products of the first node into the union
			unionProducts.putAll(node1.getProducts());
			// Put all products of the second node into the union
			unionProducts.putAll(node2.getProducts());
					
					
			// There are no repeated elements because the sets are disjointed
					
			// Deleted the adjacent vertices connected with the chosen ones
			node1.getAdjNodes().remove(node2.getId());
			node2.getAdjNodes().remove(node1.getId());
					
			// For each edge if the second vertex
			for(Entry<String, Edge> entry : node2.getAdjNodes().entrySet()) {
				// Check if the edge is also connected with the first vertex
				if (node1.getAdjNodes().containsKey(entry.getKey())) {
					// Get the number of connections and add both of them
					int degree1 = node1.getAdjNodes().get(entry.getKey()).getDegree();
					int degree2 = entry.getValue().getDegree();
					node1.getAdjNodes().get(entry.getKey()).setDegree(degree1 + degree2);
					}
					else {
						// Add the edge to the first vertex
						node1.getAdjNodes().put(entry.getKey(), entry.getValue());
					}
				}
			joinOfVertices(node1, node2, unionProducts, graph);
			}
			else {
				// The graph is not correct
				System.err.println("Error the graph is invalid");
				System.exit(2);
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param node1 is one vertex of the graph chosen randomly
	 * @param node2 is one of the vertex connected with <<node1>> chosen randomly.
	 * @param newList is the list of products bought together from both vertex
	 * @param graph is the graph which is going to be modified by means 
	 * 		  of the union of the vertices <<node1>> and <<node2>> in a new one
	 */
	public static void joinOfVertices(Node node1, Node node2, Hashtable<String, Product> unionProducts, 
									  Hashtable <String, Node> graph) {
		
		// Creation of the new vertex of the graph
		Node newNode = new Node(node1.getId() + "_" + node2.getId(),  unionProducts, node1.getAdjNodes()); 
		
		// for each vertex of the graph
		for(Entry<String, Node> entry : graph.entrySet()) {
			int degree = 0;
			// Check if the actual vertex is connected with the first one chosen
			if (entry.getValue().getAdjNodes().containsKey(node1.getId())) {
				// Get the degree and creation of a new to connect this vertex with
				// the new vertex of the graph
				degree = entry.getValue().getAdjNodes().get(node1.getId()).getDegree();
				Edge e = new Edge(degree, newNode);
				// Remove the first vertex chosen of the adjacent and
				// store of the new edge between the actual vertex and the new one created
				entry.getValue().getAdjNodes().remove(node1.getId());
				entry.getValue().getAdjNodes().put(newNode.getId(), e);
			}
			// Check if the actual vertex is connected with the second one chosen
			if (entry.getValue().getAdjNodes().containsKey(node2.getId())) {
				Edge e;
				// Check if the vertex was connected with the first one
				if (degree > 0) {
					// Creation of a new edge with the degree of both of them
					e = new Edge(entry.getValue().getAdjNodes().get(node2.getId()).getDegree() + degree, newNode);
				}
				else {
					// Only stores the degree of the second one
					e = new Edge(entry.getValue().getAdjNodes().get(node2.getId()).getDegree(), newNode);
				}
				// Remove the second vertex of the actual vertex 
				entry.getValue().getAdjNodes().remove(node2.getId());
				entry.getValue().getAdjNodes().put(newNode.getId(), e);
			}
		}
		// Store the new created vertex in the graph
		graph.put(newNode.getId(), newNode);
	}
	
	public static int getCutValue(Hashtable <String, Node> initGraph, Hashtable <String, Node> graph) {
		Node firstSet = (Node)graph.values().toArray()[0];
		
		int cut = 0;
		boolean isInFirstSet;
		
		for(Entry<String, Node> entry : initGraph.entrySet()) {
			// Check if vertex product is in the first set
			if(firstSet.getProducts().containsKey(entry.getKey())) {
				isInFirstSet = true;
			}
			else {
				isInFirstSet = false;
			}
			for(Entry<String, Edge> innerEntry : entry.getValue().getAdjNodes().entrySet()) {
				// Check if vertex are in different sets
				if((isInFirstSet && !firstSet.getProducts().containsKey(innerEntry.getKey())) ||
				   (!isInFirstSet && firstSet.getProducts().containsKey(innerEntry.getKey())))
				{
					cut += innerEntry.getValue().getDegree();
				}
			}
		}
		cut /= 2;
		return cut;
	}
	
	
	
	/**
	 * 
	 * @param args[0] is the file which contains the matrix of products
	 * Finds a partition close to the optimum of the products using the karger
	 * algorithm in order to resolve the minimun cut problem 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// Check if the number of parameters is correct
		if (args.length != 1 && args.length != 2) {
			// Wrong number of parameters introduced
			System.err.println("Wrong number of parameters");
			System.err.println("Invoke like mincut <matrixFile> <productsFile>");
			System.exit(1);
		}
		String attributesFilePath = null;
		if (args.length == 2) {
			attributesFilePath = args[1];	
		}
		// Get the file which contains the products matrix
		File file = new File(args[0]);	
		
		// Store init graph for cut value checking
		Hashtable <String, Node> initGraph = readProducts(file, attributesFilePath);
		
		int minimumCut = Integer.MAX_VALUE;
		
		Hashtable <String, Product> firstProductSet = new Hashtable<>();
		Hashtable <String, Product> secondProductSet = new Hashtable<>();
		
		for(int i = 0; i < ATTEMPTS; i++) {
			System.out.println("Iteration " + (int)(i + 1));
			
			// Stores the graph where the vertices are the products
			// and the edges are the products that have been bought together		
			// Read the products from the file and store them in the graph
			Hashtable <String, Node> graph = readProducts(file, attributesFilePath);
			
			// Execution of the karger algorithm to resolve the mincut problem
			karger(graph);
			
			// Get cut value for this iteration
			int cut = getCutValue(initGraph, graph);
			System.out.println("Cut value obtained is: " + cut);
			
			if(cut < minimumCut) {
				// Update minimum cut value
				minimumCut = cut;
				// Update products sets
				firstProductSet = ((Node)graph.values().toArray()[0]).getProducts();
				secondProductSet = ((Node)graph.values().toArray()[1]).getProducts();
			}
		}
		// Print final results
		System.out.println();
		System.out.println("Execution finished");
		System.out.println("Minimum cut value: " + minimumCut + "\n");
		
		// Print first set of products
		System.out.println("First set of products");
		for(Entry<String, Product> entry : firstProductSet.entrySet()) {
			if(attributesFilePath == null) {
				System.out.print(entry.getKey() + " ");
			}
			else {
				System.out.println(entry.getKey() + ": " + entry.getValue().getAttributes().toString());
			}
		}
		System.out.println();
		
		// Print second set of products
		System.out.println("Second set of products");
		for(Entry<String, Product> entry : secondProductSet.entrySet()) {
			if(attributesFilePath == null) {
				System.out.print(entry.getKey() + " ");
			}
			else {
				System.out.println(entry.getKey() + ": " + entry.getValue().getAttributes().toString());
			}
		}
		
	} 
}
