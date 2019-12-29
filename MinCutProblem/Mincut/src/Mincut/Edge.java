package Mincut;

public class Edge {
	
	int degree;
	
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
