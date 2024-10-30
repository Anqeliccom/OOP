import org.example.AdjacencyMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyMatrixTest extends AbstractGraphTest<AdjacencyMatrix> {

    @Override
    protected AdjacencyMatrix createInstance(int size) {
        return new AdjacencyMatrix(size);
    }

    @Test
    public void testAddVertex() {
        AdjacencyMatrix graph = createInstance(3);
        assertEquals(3, graph.getVertexCount());

        graph.addVertex(3);
        assertEquals(4, graph.getVertexCount());
    }

    @Test
    public void testAddAndRemoveEdge() {
        AdjacencyMatrix graph = createInstance(2);

        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));

        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
    }

    @Test
    public void testToString() {
        AdjacencyMatrix graph = createInstance(3);
        graph.addEdge(0, 1);
        assertEquals("0 1 0 \n0 0 0 \n0 0 0 \n", graph.toString());
    }
}
