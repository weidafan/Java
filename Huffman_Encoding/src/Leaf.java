public class Leaf extends Node {
	private String character;
	private String code = "";

	public Leaf() {
		super(weight);
	}

	// Calls Node subclass to add a bit to the sequence as the Huffman Tree is being
	// built
	@Override
	public void addBit(String s) {
		character =s;
		char c = s.charAt(0); 
		int ascii = (int) c;
		code = Integer.toString(ascii);
	}
}
