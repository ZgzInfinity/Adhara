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

public class Product {

	// Identifier of the product
	private String id;
	
	// Name of the product
	private String name;
	
	// Number of unities of the product
	private int units;
	
	// Price of the product per unity
	private int price;
	
	
	
	/**
	 * @param id
	 */
	public Product(String id) {
		this.id = id;
	}
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param units
	 * @param price
	 */
	public Product(String id, String name, int units, int price) {
		this.id = id;
		this.name = name;
		this.units = units;
		this.price = price;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}


	
	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}


	
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
}
