import org.example.IncidenceMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenceMatrixTest extends AbstractGraphTest<IncidenceMatrix> {

    protected IncidenceMatrix createInstance(int size) {
        return new IncidenceMatrix(size);
    }

    @Test
    public void testAddVertex() {
        IncidenceMatrix graph = createInstance(3);
        assertEquals(3, graph.getVertexCount());

        graph.addVertex(3);
        assertEquals(4, graph.getVertexCount());
    }

    @Test
    public void testAddAndRemoveEdge() {
        IncidenceMatrix graph = createInstance(2);

        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));

        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
    }

    @Test
    public void testToStringSmallMatrix() {
        IncidenceMatrix graph = createInstance(2);
        graph.addEdge(0, 1);
        assertEquals("1 -1", graph.toString());
    }

    @Test
    public void testToStringLargerMatrix() {
        IncidenceMatrix graph = createInstance(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        String expected = """
                1 -1 0 0
                0 0 1 -1
                """;
        assertEquals(expected.trim(), graph.toString().trim());
    }
}
