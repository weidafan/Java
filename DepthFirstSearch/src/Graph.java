import java.util.ArrayList;

public class Graph extends java.lang.Object {
	// make all vertex as a node. This is the Node instance class
	public static class Node {
		String data;
		boolean visited = false;
		// all neighbors of the vertex.
		ArrayList<Node> neighbours;
		// counter for incoming edges.
		int noIncoming = 0;

		// next, left,right is for the DAGGen class.
		Node next;
		public Node left;
		public Node right;

		// Constructor for Node class.
		Node(String graphIn) {
			this.data = graphIn;
			this.neighbours = new ArrayList<>();
			this.next = null;
		}

		// add neighbor to a vertex.
		public void addneighbours(Node neighbourNode) {
			neighbourNode.noIncoming++;
			this.neighbours.add(neighbourNode);
			boolean b = isReachable(neighbourNode, neighbourNode);
		}

		// neighbours getter
		public ArrayList<Node> getNeighbours() {
			return neighbours;
		}

		// This method check if a graph is a dag by using recursive call to compare if
		// the
		// target is equal to its neighbors.
		public boolean isReachable(Node target, Node from) {
			if (target.equals(from)) {
				return true;
			}
			for (Node nxt : getNeighbours()) {
				if (isReachable(target, nxt)) {
					return true;
				}
			}
			return false;
		}
	}

	// fields for Graph.
	public ArrayList<Node> vertexs = new ArrayList<Node>();;
	private ArrayList<ArrayList<Node>> edges = new ArrayList<ArrayList<Node>>();
	boolean isDag = true;

	// constructor of graph
	public Graph(java.lang.String[][] graphIn) {
		// set first column of the 2d array as vertexs.
		for (int row = 0; row < graphIn.length; row++) {
			vertexs.add(new Node(graphIn[row][0]));
		}
		// set neighbors for each vertex. and check if a graph a dag or not after adding
		// a new neighbors. if not a dag then set isDag to false.
		for (int row = 0; row < graphIn.length; row++) {
			for (int col = 1; col < graphIn[row].length; col++) {
				for (int i = 0; i < vertexs.size(); i++) {
					if (vertexs.get(i).data == graphIn[row][col]) {
						vertexs.get(row).addneighbours(vertexs.get(i));
						if (isReachable(vertexs.get(row), vertexs.get(i))) {
							isDag = false;
						}
						break;
					}
				}
			}
			edges.add(vertexs.get(row).getNeighbours());
		}
	}

	// check if the graph is a dag. if not a dag then set the isDag to false.
	private boolean isReachable(Node target, Node from) {
		if (target.equals(from)) {
			return true;
		}
		if (from.equals(null)) {
			return false;
		}
		for (Node nxt : from.getNeighbours()) {
			if (isReachable(target, nxt)) {
				return true;
			}
		}
		return false;
	}
//get edge list for a vertex.
	public java.util.ArrayList<java.lang.String> getEdgeList(java.lang.String v) {
		int row = -1;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < vertexs.size(); i++) {
			if (vertexs.get(i).data == v) {
				row = i;
			}
		}
		for (int i = 0; i < edges.get(row).size(); i++) {
			temp.add(edges.get(row).get(i).data);
		}
		return temp;
	}
//get a list of vertexs
	public java.util.ArrayList<java.lang.String> getVertices() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < vertexs.size(); i++) {
			temp.add(vertexs.get(i).data);
		}
		return temp;

	}
//output string of the adjacent list.
	@Override
	public java.lang.String toString() {
		String temp = "";
		for (int i = 0; i < vertexs.size(); i++) {
			String e = "";
			for (Node s : edges.get(i)) {
				e = e.concat(s.data + ",");
			}
			temp = temp.concat(vertexs.get(i).data + ": " + e + "\n");
		}
		return temp;

	}
}
