import java.util.ArrayList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class testGraph {

        @Test
        public void testGraph1() {
            String[] [] graphin = {{"c1", "c3"},{"c2","c3"},
                    {"c3", "c4", "c5"},
                    {"c4", "c5"}, {"c5"}};
            Graph g = new Graph(graphin );
            ArrayList<String> expected = new ArrayList<String> (Arrays.asList("c1", "c2", "c3", "c4", "c5")); 
            assertEquals("dfs should be c2, c1, c3, c4, c5", expected, g.getVertices());
            expected = new ArrayList<String> (Arrays.asList("c4", "c5")); 
            assertEquals("edges for c3 is  c4, c5", expected, g.getEdgeList("c3"));
            expected = new ArrayList<String> (Arrays.asList( "c5")); 
            assertEquals("edges for c3 is  c4, c5", expected, g.getEdgeList("c4"));
            String s = "c1: c3,\nc2: c3,\nc3: c4,c5,\nc4: c5,\nc5: \n";
            assertEquals("wrong",s,g.toString());
        }
    }

