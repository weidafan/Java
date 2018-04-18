import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;

public class HuffmanCoding {
	public static String loopup_table(String s) {
	Map<String, String> codes = new HashMap<String, String>();
	codes.put("A", "10");
	codes.put("B", "1111");
	codes.put("C", "11101");
	codes.put("D", "00");
	codes.put("E", "110");
	codes.put("{NL}", "11100");
	codes.put("_", "01");
	return codes.get(s);
	}
	// numbers.get("A");  >>> "10"
	
	public static void creat_PriorityQueue() {
		PriorityQueue <Node>myQ = new PriorityQueue<Node>();
		
	}
}
