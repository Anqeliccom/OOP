import org.example.AdjacencyList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListTest extends AbstractGraphTest<AdjacencyList> {

    @Override
    protected AdjacencyList createInstance(int size) {
        return new AdjacencyList(size);
    }

    @Test
    public void testAddVertex() {
        AdjacencyList graph = createInstance(3);
        assertEquals(3, graph.getVertexCount());

        graph.addVertex(3);
        assertEquals(4, graph.getVertexCount());
    }

    @Test
    public void testAddAndRemoveEdge() {
        AdjacencyList graph = createInstance(2);

        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));

        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
    }

    @Test
    public void testToString() {
        AdjacencyList graph = createInstance(3);
        graph.addEdge(0, 1);
        assertEquals("0: 1\n1: \n2: \n", graph.toString());
    }
}
