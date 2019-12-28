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
	
	public static int main(int argc, String[] args) {
		
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
				
				Hashtable<String, Node> adjNodes = new Hashtable<String, Node>();
				
				Node n = new Node(p.getId(), productsList, adjNodes);
				graph.put(n.getId(), n);
			}
				
			for (Integer i = 0; i < numProducts; i++) {
				Node n = graph.get(i.toString());
				Hashtable<String, Node> adjNodes = n.getAdjNodes();
				
				for (Integer j = 0; j < numProducts; j++) {
					if (i != j && input.next() == "1") {
						adjNodes.put(j.toString(), graph.get(j.toString()));
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
					
					key = node1.getAdjNodes().keySet().toArray();
					vertexKeys = Arrays.copyOf(key, key.length,String[].class);
					
					int random2 = random.nextInt(vertexKeys.length);
					Node node2 = node1.getAdjNodes().get(vertexKeys[random2]);
					
					graph.remove(node1.getId());
					graph.remove(node2.getId());
					
					List<Product> newList = new ArrayList<>(node1.getProductsList());
					newList.addAll(node2.getProductsList());
					
					node1.getAdjNodes().remove(node2.getId());
					node2.getAdjNodes().remove(node1.getId());
					
					Hashtable<String, Node> unionNodes = new Hashtable<>();
					unionNodes.putAll(node1.getAdjNodes());
					unionNodes.putAll(node2.getAdjNodes());
							
					Node newNode = new Node(node1.getId() + "_" + node2.getId(),  newList, unionNodes); 
				
					// for-each loop
					for(Entry<String, Node> entry : graph.entrySet()) {
						if (entry.getValue().getAdjNodes().containsKey(node1.getId())) {
							entry.getValue().getAdjNodes().remove(node1.getId());
							entry.getValue().getAdjNodes().put(newNode.getId(), newNode);
						}
						if (entry.getValue().getAdjNodes().containsKey(node2.getId())) {
							entry.getValue().getAdjNodes().remove(node2.getId());
							entry.getValue().getAdjNodes().put(newNode.getId(), newNode);
						}
					}
					graph.put(newNode.getId(), newNode);
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
	 
		return argc;
		
	}
}
