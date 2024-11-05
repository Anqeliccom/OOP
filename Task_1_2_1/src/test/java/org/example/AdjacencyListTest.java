package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Tests for the AdjacencyList graph implementation.
 */
class AdjacencyListTest extends AbstractGraphTest<AdjacencyList> {

    @Override
    protected AdjacencyList createInstance(int size) {
        return new AdjacencyList(size);
    }

    @Test
    void testAddAndRemoveVertex() {
        AdjacencyList graph = createInstance(3);

        graph.addVertex();
        assertEquals(graph.getVertexCount(), 4);

        graph.removeVertex(1);
        assertFalse(graph.hasVertex(1));
        assertThrows(IllegalArgumentException.class, () -> graph.removeVertex(4));
    }

    @Test
    void testAddAndRemoveEdge() {
        AdjacencyList graph = createInstance(2);

        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(0, 2));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(2, 0));

        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1));
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(0, 2));
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(2, 0));
    }

    @Test
    void testToString() {
        AdjacencyList graph = createInstance(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        String expected =
            """
                0: 1, 2
                1: 2
                2: 3
                3: 4
                4: 0
                """;
        assertEquals(expected, graph.toString());
    }

    @Test
    void testGetNeighbors() {
        AdjacencyList graph = createInstance(3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);

        assertEquals(graph.getNeighbors(0), Set.of(1, 2));
        assertEquals(graph.getNeighbors(1), Set.of(2));
        assertTrue(graph.getNeighbors(2).isEmpty());
    }

    @Test
    void testReadFromFile() throws IOException {
        Path tempFile = Files.createTempFile("graph", ".txt");
        Files.writeString(tempFile, "0 1\n0 2\n1 2\n");

        AdjacencyList graph = new AdjacencyList(0);
        graph.readFromFile((tempFile).toString(), graph);

        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(0, 2));
        assertTrue(graph.hasEdge(1, 2));

        Files.delete(tempFile);
    }
}
