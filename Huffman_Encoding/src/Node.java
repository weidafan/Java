public abstract class Node extends java.lang.Object implements java.lang.Comparable<Node> {
	protected static int weight;
	protected int count;
	public Node(int weight) {
		super();
		this.weight = weight;
	}

	// Calls Node subclass to add a bit to the sequence as the Huffman Tree is being
	// built
	public abstract void addBit(java.lang.String s);

	// Returns the difference between the weight of this Node and the weight of the
	// node in the parameter
	public int compareTo(Node that) {
		return this.weight-that.weight;
	}

}