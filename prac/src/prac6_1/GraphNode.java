package prac6_1;

import java.util.*;

public class GraphNode {

	private int id;
	private List<GraphNode> neighbors;
	
	public GraphNode(int id) {
		this.id = id;
		this.neighbors = new ArrayList<>();
	}
	
	// to get the ID
	public int getId() {
		return id;
	}
	// to get the neighbors 
	public List<GraphNode> getNeighbors(){
		return neighbors;
	}
	// to add neighbors
	public void addNeighbors(GraphNode neighbors) {
		this.neighbors.add(neighbors);
	}
	// to remove neighbors
	public void removeNeighbors(GraphNode neighbor) {
		neighbors.remove(neighbor);
	}
	// return string 
	@Override
	public String toString() {
		return "GraphNode{" +
				"id=" + id +
				", neighbors=" + neighbors.stream().map(GraphNode::getId).toList() + 
				'}';
	}
}
