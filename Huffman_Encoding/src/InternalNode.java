public class InternalNode extends Node {
	private Node left;
	private Node right;
	
	InternalNode(int value, Node l, Node r){
		super(value);
		left = l;
		right = r;
	}

	@Override
	public void addBit(String s) {
		left.addBit(s);
		right.addBit(s);
	}

}