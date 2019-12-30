/**
 ********************************************
 **** Algorithm for difficult problems ******
 **** Practice 1: Minimum cut problem *******
 **** Authors: ******************************
 **** Victor Peñasco Estivalez - 741294 *****
 **** Ruben Rodriguez Esteban - 737215 ******
 **** Date: 29 - 12 - 2019 ******************
 ********************************************
 */


package Mincut;

import java.io.File;
import java.io.FileNotFoundException;
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
	
	
	/**
	 * 
	 * @param productsFile is the file which contains the matrix of products
	 * @return a graph whose vertices are the products read from the file << productsFile>>
	 * 		   and whose edges are the pairs of products that have ever been bought together.
	 */
	@SuppressWarnings("resource")
	public static  Hashtable <String, Node> readProducts(File productsFile) {
		// Stores the graph where the vertices are the products
		// and the edges are the products that have been bought together
		Hashtable <String, Node> graph = new Hashtable<>();	
		
		// Open the file that contains the matrix the products 
		Scanner input;
		try {
			input = new Scanner(productsFile);
			
			// Stores the number of products (first line of the file
			int numProducts = Integer.parseInt(input.next());

			// For each product do
			for (Integer i = 0; i < numProducts; i++) {
				// Create a new product with a new key
				Product p = new Product(i.toString());
				
				// List to store products that have ever been purchased with the product <<i>>
				List<Product> productsList = new ArrayList<>();
				
				// Added the product i to the list
				productsList.add(p);
				
				// Hash table which contains the adjacent vertices to the vertex i of the graph
				Hashtable<String, Edge> adjNodes = new Hashtable<String, Edge>();
				
				// Create a new vertex in the graph with the product <<i>>
				Node n = new Node(p.getId(), productsList, adjNodes);
				
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
					if (i != j && s != "0") {
						// Creation of a edge between both vertices
						Edge e = new Edge(Integer.parseInt(s), graph.get(j.toString()));
						adjNodes.put(j.toString(), e);
					}
				}
			}
			return graph;	
		} 
		catch (FileNotFoundException e) {
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
					
			// The second vertex if chosen between the rest of the vertices of the graph
			// which are connected with the fisrt one
			int random2 = random.nextInt(edgesKeys.size());
			String nodeKey = edgesKeys.get(random2);
			Node node2 = node1.getAdjNodes().get(nodeKey).getVertex();
					
			// Remove the vertices of the graph which are going to be joined in a new one
			graph.remove(node1.getId());
			graph.remove(node2.getId());
					
			// List of the products bought with the first product 
			List<Product> newList = new ArrayList<>(node1.getProductsList());
			// The list is stored with the products bought with the second product
			newList.addAll(node2.getProductsList());
					
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
			joinOfVertices(node1, node2, newList, graph);
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
	public static void joinOfVertices(Node node1, Node node2, List<Product> newList, 
									  Hashtable <String, Node> graph) {
		
		// Creation of the new vertex of the graph
		Node newNode = new Node(node1.getId() + "_" + node2.getId(),  newList, node1.getAdjNodes()); 
		
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
	
	
	
	/**
	 * 
	 * @param args[0] is the file which contains the matrix of products
	 * Finds a partition close to the optimum of the products using the karger
	 * algorithm in order to resolve the minimun cut problem 
	 */
	public static void main(String[] args) {
		
		// Check if the number of parameters is correct
		if (args.length != 1 && args.length != 2) {
			// Wrong number of parameters introduced
			System.err.println("Wrong number of parameters");
			System.err.println("Invoke like mincut <matrixFile> <productsFile>");
			System.exit(1);
		}
		if (args.length == 2) {
			
		}
		
		// Get the file which contains the products matrix
		File file = new File(args[0]);	
		
		// Stores the graph where the vertices are the products
		// and the edges are the products that have been bought together		
		// Read the products from the file and store them in the graph
		Hashtable <String, Node> graph = readProducts(file);
		
		// Execution of the karger algorithm to resolve the mincut problem
		karger(graph);
		
		// Showing the graphs
		for(Entry<String, Node> entry : graph.entrySet()) {
			System.out.println(entry.getKey());
		}
	} 
}
