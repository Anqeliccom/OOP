import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    public void testSortAdjacencyMatrixWithoutCycle() {
        Graph g1 = new AdjacencyMatrix(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);

        TopologicalSort sorter = new TopologicalSort();
        int[] sorted = sorter.sort(g1);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, sorted);
    }

    @Test
    public void testSortAdjacencyListWithoutCycle() {
        Graph g2 = new AdjacencyList(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(3, 5);
        g2.addEdge(4, 5);

        TopologicalSort sorter = new TopologicalSort();
        int[] sorted = sorter.sort(g2);
        assertArrayEquals(new int[]{0, 2, 1, 4, 3, 5}, sorted);
    }

    @Test
    public void testSortIncidenceMatrixWithCycle() {
        Graph g3 = new IncidenceMatrix(5);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);
        g3.addEdge(3, 4);
        g3.addEdge(4, 1);

        TopologicalSort sorter = new TopologicalSort();
        int[] sorted = sorter.sort(g3);
        assertEquals(0, sorted.length);
    }
}
