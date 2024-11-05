package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Abstract test class for graph implementations.
 * Provides shared test methods for all AbstractGraph subclasses.
 *
 * @param <T> the type of the graph implementation.
 */
abstract class AbstractGraphTest<T extends Graph> {

    /**
     * Creates an instance of the graph with the specified number of vertices.
     *
     * @param size the initial number of vertices.
     * @return a new instance of the graph.
     */
    protected abstract T createInstance(int size);

    @Test
    void testEmptyGraph() {
        T g1 = createInstance(0);
        T g2 = createInstance(0);

        assertTrue(g1.equals(g2));
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testIdenticalGraphs() {
        T g1 = createInstance(3);
        T g2 = createInstance(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);

        assertTrue(g1.equals(g2));
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testDifferentGraphsWithSameVertexCount() {
        T g1 = createInstance(3);
        T g2 = createInstance(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);

        assertFalse(g1.equals(g2));
        assertNotEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testGraphsWithSameNeighborsDifferentOrder() {
        T g1 = createInstance(3);
        T g2 = createInstance(3);

        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g2.addEdge(0, 2);
        g2.addEdge(0, 1);

        assertTrue(g1.equals(g2));
        assertEquals(g1.hashCode(), g2.hashCode());
    }
}
