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

	
	private static int MIN_NUMBER_VERTEX = 2;
	
	public static void main(int argc, String[] args) {
		
		if (argc != 2 && argc != 3) {
			System.err.println("Wrong number of parameters");
			System.err.println("Invoke like mincut <matrixFile> <productsFile>");
			System.exit(1);
		}
		if (argc == 3) {
			
		}
		File file = new File(args[1]);
	    Scanner input;
		try {
			input = new Scanner(file);
			
			int numProducts = Integer.parseInt(input.next());
			
			Hashtable <String, Node> graph = new Hashtable<>();
			
			for (Integer i = 0; i < numProducts; i++) {
				
				Product p = new Product(i.toString());
				
				List<Product> productsList = new ArrayList<>();
				productsList.add(p);
				
				Hashtable<String, Edge> adjNodes = new Hashtable<String, Edge>();
				
				Node n = new Node(p.getId(), productsList, adjNodes);
				graph.put(n.getId(), n);
			}
				
			for (Integer i = 0; i < numProducts; i++) {
				Node n = graph.get(i.toString());
				Hashtable<String, Edge> adjNodes = n.getAdjNodes();
				
				for (Integer j = 0; j < numProducts; j++) {
					String s = input.next();
					if (i != j && s != "0") {
						Edge e = new Edge(Integer.parseInt(s), graph.get(j.toString()));
						adjNodes.put(j.toString(), e);
					}
				}
			}
			
			Random random = new Random();
			
			while (graph.size() > MIN_NUMBER_VERTEX) {
				
				Object key [] = graph.keySet().toArray();
				String vertexKeys [] = Arrays.copyOf(key,key.length,String[].class);
				
				int random1 = random.nextInt(vertexKeys.length);
				Node node1 = graph.get(vertexKeys[random1]);
				
				if (!node1.getAdjNodes().isEmpty()) {
					
					// GET the vertex
					List<String> edgesKeys = new ArrayList<>();
					
					// for-each loop
					for(Entry<String, Edge> entry : node1.getAdjNodes().entrySet()) {
						for (int i = 0; i < entry.getValue().getDegree(); i++) {
							edgesKeys.add(entry.getKey());
						}
					}
					
					int random2 = random.nextInt(edgesKeys.size());
					String nodeKey = edgesKeys.get(random2);
					Node node2 = node1.getAdjNodes().get(nodeKey).getVertex();
					
					graph.remove(node1.getId());
					graph.remove(node2.getId());
					
					List<Product> newList = new ArrayList<>(node1.getProductsList());
					newList.addAll(node2.getProductsList());
					
					node1.getAdjNodes().remove(node2.getId());
					node2.getAdjNodes().remove(node1.getId());
					
					
					for(Entry<String, Edge> entry : node2.getAdjNodes().entrySet()) {
						if (node1.getAdjNodes().containsKey(entry.getKey())) {
							int degree1 = node1.getAdjNodes().get(entry.getKey()).getDegree();
							int degree2 = entry.getValue().getDegree();
							node1.getAdjNodes().get(entry.getKey()).setDegree(degree1 + degree2);
						}
						else {
							node1.getAdjNodes().put(entry.getKey(), entry.getValue());
						}
					}
					
					Node newNode = new Node(node1.getId() + "_" + node2.getId(),  newList, node1.getAdjNodes()); 
				
					// for-each loop
					for(Entry<String, Node> entry : graph.entrySet()) {
						int degree = 0;
						if (entry.getValue().getAdjNodes().containsKey(node1.getId())) {
							degree = entry.getValue().getAdjNodes().get(node1.getId()).getDegree();
							Edge e = new Edge(degree, newNode);
							entry.getValue().getAdjNodes().remove(node1.getId());
							entry.getValue().getAdjNodes().put(newNode.getId(), e);
						}
						if (entry.getValue().getAdjNodes().containsKey(node2.getId())) {
							Edge e;
							if (degree > 0) {
								e = new Edge(entry.getValue().getAdjNodes().get(node2.getId()).getDegree() + degree, newNode);
							}
							else {
								e = new Edge(entry.getValue().getAdjNodes().get(node2.getId()).getDegree(), newNode);
							}
							entry.getValue().getAdjNodes().remove(node2.getId());
							entry.getValue().getAdjNodes().put(newNode.getId(), e);
						}
					}
					graph.put(newNode.getId(), newNode);
				}
				else {
					System.err.println("Error the graph is invalid");
					System.exit(2);
				}
			}
			for(Entry<String, Node> entry : graph.entrySet()) {
				List<Product> productsList = entry.getValue().getProductsList();
				System.out.println(entry.getKey());
				for (Product product : productsList) {
					System.out.println(product.getId());
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} 
	}
}
