import java.util.Hashtable;
import java.util.List;

public class Node {
	
	// Vertex identifier
	String id;
	// List of products
	List<Product> productsList;
	// Hash table which contains the adjacent nodes
	Hashtable<String, Node> adjNodes;
	
	
	public Node(String id, List<Product> productsList, Hashtable<String, Node> adjNodes) {
		this.id = id;
		this.productsList = productsList;
		this.adjNodes = adjNodes;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the productsList
	 */
	public List<Product> getProductsList() {
		return productsList;
	}


	/**
	 * @param productsList the productsList to set
	 */
	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}


	/**
	 * @return the adjNodes
	 */
	public Hashtable<String, Node> getAdjNodes() {
		return adjNodes;
	}


	/**
	 * @param adjNodes the adjNodes to set
	 */
	public void setAdjNodes(Hashtable<String, Node> adjNodes) {
		this.adjNodes = adjNodes;
	}
	
	
	
}

