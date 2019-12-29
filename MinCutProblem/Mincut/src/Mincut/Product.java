package Mincut;

public class Product {

	private String id;
	
	private String name;
	
	private int units;
	
	private int price;
	
	
	public Product(String id) {
		this.id = id;
	}
	
	
	
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
