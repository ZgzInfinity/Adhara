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

import java.util.List;

public class Product {

	// Identifier of the product
	private String id;
	
	// List of attributes
	private List<String> attributes;
	
	
	
	/**
	 * @param id
	 */
	public Product(String id) {
		this.id = id;
	}

	
	
	/**
	 * @param id
	 * @param attributes
	 */
	public Product(String id, List<String> attributes) {
		this.id = id;
		this.attributes = attributes;
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
	 * @return the attributes
	 */
	public List<String> getAttributes() {
		return attributes;
	}

	
	
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	
	
}
