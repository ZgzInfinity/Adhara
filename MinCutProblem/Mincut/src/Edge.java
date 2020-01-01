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


import java.io.Serializable;

public class Edge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Number of conections
	int degree;

	// Destinatip vertex with the edge is connected
	Node vertex;
	
	
	
	/**
	 * @param degree
	 * @param vertex
	 */
	public Edge(int degree, Node vertex) {
		this.degree = degree;
		this.vertex = vertex;
	}

	
	
	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	
	
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}

	
	
	/**
	 * @return the vertex
	 */
	public Node getVertex() {
		return vertex;
	}

	
	
	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(Node vertex) {
		this.vertex = vertex;
	}
	
}
