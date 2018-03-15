import java.util.ArrayList;

public class Graph extends java.lang.Object {
    // nested static class.
    public static class Node {
        String data;
        boolean visited = false;
        ArrayList<Node> neighbours;
        int noIncoming = 0;

        Node(String graphIn) {
            this.data = graphIn;
            this.neighbours = new ArrayList<>();

        }

        public void addneighbours(Node neighbourNode) {
            neighbourNode.noIncoming++  ;
            this.neighbours.add(neighbourNode);
            boolean b = isReachable(neighbourNode, neighbourNode);
        }

        public ArrayList<Node> getNeighbours() {
            return neighbours;
        }

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

    public ArrayList<Node> vertexs = new ArrayList<Node>();;
    private ArrayList<ArrayList<Node>> edges = new ArrayList<ArrayList<Node>>();
    boolean isDag = true;
    // constructor of graph
    public Graph(java.lang.String[][] graphIn) {
        for (int row = 0; row < graphIn.length; row++) {
            vertexs.add(new Node(graphIn[row][0]));
        }
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
        for (Node nxt : from.getNeighbours()) {
            if (isReachable(target, nxt)) {
                return true;
            }
        }
        return false;
    }

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

    public java.util.ArrayList<java.lang.String> getVertices() {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < vertexs.size(); i++) {
            temp.add(vertexs.get(i).data);
        }
        return temp;

    }

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
