import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopSort {
	static ArrayList<String> f = new ArrayList<String>();
	static Stack<String> s = new Stack<String>();
	static ArrayList<Graph.Node> tmp = new ArrayList<Graph.Node>();
	static long timer1;
	static long timer2;

	// Recursive DFS, in order to use the nested static class Node in Graph.java.
	// Must use "Graph.node".
	public static void dfs(Graph.Node node) {
		f.add(node.data);
		List<Graph.Node> neighbours = node.getNeighbours();
		node.visited = true;
		for (int i = 0; i < neighbours.size(); i++) {
			Graph.Node n = neighbours.get(i);
			if (n != null && !n.visited) {
				dfs(n);
			}
		}
	}

	// helper class
	public static ArrayList<String> dfsTopSort(Graph g) {
		// returns the current value of the system timer, in nanoseconds
		timer1 = System.nanoTime();
		if (!g.isDag) {
			// returns the current value of the system timer, in milliseconds
			timer2 = System.nanoTime();
			System.out.print("time used: " + (timer2 - timer1) + " nanosecond\n");
			return null;
		}
		f.clear();
		s.clear();
		// Graph.Node node = g.vertexs.get(0);
		for (int i = 0; i < g.vertexs.size(); i++) {
			if (!g.vertexs.get(i).visited)
				dfs(g.vertexs.get(i));
			if (f.size() != 0) {
				for (int j = f.size() - 1; j >= 0; j--) {
					s.push(f.get(j));
				}
				f.clear();
			}
		}
		int size = s.size();
		for (int i = 0; i < size; i++) {
			f.add(s.pop());
		}
		// returns the current value of the system timer, in milliseconds
		timer2 = System.nanoTime();
		System.out.print("time used: " + (timer2 - timer1) + " nanosecond\n");
		return f;
	}

	public static ArrayList<String> sourceTopSort(Graph g) {
		// returns the current value of the system timer, in milliseconds
		timer1 = System.nanoTime();
		f.clear();
		if (!g.isDag) {
			// returns the current value of the system timer, in milliseconds
			timer2 = System.nanoTime();
			System.out.print("time used: " + (timer2 - timer1) + " nanosecond\n");
			return null;
		}

		for (int i = g.vertexs.size() - 1; i >= 0; i--) {
			tmp.add(g.vertexs.get(i));
		}
		for (int i = tmp.size() - 1; i >= 0; i--) {
			if (tmp.get(i).noIncoming == 0) {
				f.add(tmp.get(i).data);
				for (int j = 0; j < tmp.get(i).getNeighbours().size(); j++) {
					tmp.get(i).getNeighbours().get(j).noIncoming--;
				}
				tmp.remove(i);
			}
		}
		// returns the current value of the system timer, in milliseconds
		timer2 = System.nanoTime();
		System.out.print("time used: " + (timer2 - timer1) + " nanosecond\n");
		return f;
	}

}
