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


import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;



public class Node implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Vertex identifier
	String id;
	// Hash table of products
	Hashtable<String, Product> products;
	// Hash table which contains the adjacent nodes
	Hashtable<String, Edge> adjNodes;
	
	
	
	/**
	 * @param id
	 * @param products
	 * @param adjNodes
	 */
	public Node(String id, Hashtable<String, Product> products, Hashtable<String, Edge> adjNodes) {
		this.id = id;
		this.products = products;
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
	 * @return the products
	 */
	public Hashtable<String, Product> getProducts() {
		return products;
	}


	
	/**
	 * @param products the products to set
	 */
	public void setProducts(Hashtable<String, Product> products) {
		this.products = products;
	}


	
	/**
	 * @return the adjNodes
	 */
	public Hashtable<String, Edge> getAdjNodes() {
		return adjNodes;
	}


	
	/**
	 * @param adjNodes the adjNodes to set
	 */
	public void setAdjNodes(Hashtable<String, Edge> adjNodes) {
		this.adjNodes = adjNodes;
	}
	
	
	
}

