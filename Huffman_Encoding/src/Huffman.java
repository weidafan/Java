import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {
	public static ArrayList<Character> char_array = new ArrayList<Character>();
	public static ArrayList<String> codings;
	public static ArrayList<Integer> count;

	public static void main(String args[]) throws IOException {
		huffmanEncode("./src/test.txt");

	}

	public static void huffmanEncode(String filename) throws IOException {
		FileInputStream in = new FileInputStream(filename);
		FileOutputStream out = new FileOutputStream("./src/output.txt");
		Scanner scan = new Scanner(in);
		PrintWriter writer = new PrintWriter(out);
		String line;
		int index;
		while ((line = scan.nextLine()) != null) {
			System.out.println(line);
			char[] ascii1 = line.toCharArray();
			for (char ch : ascii1) {
				index = char_array.indexOf(ch);
				if (index < 0) {
					// codings.add(HuffmanCoding.loopup_table(Character.toString(ch)));
					char_array.add(ch);
					count.add(1);
				} else {
					count.set(index, count.get(index) + 1);
				}
			}
		}
		writer.println("Char Freq Code");
		for (int i = 0; i < count.size(); i++) {
			writer.println(char_array.get(i) + " " + count.get(i) + " " + codings.get(i));
		}
		scan.close();
		writer.close();

	}
}
