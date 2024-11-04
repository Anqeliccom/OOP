package org.example;

import java.util.HashSet;

/**
 * An abstract base class for graph implementations, providing common methods.
 */
public abstract class AbstractGraph implements Graph {

    /**
     * Compares this graph to another object for equality based on set of neighbors.
     *
     * @param obj the object to compare to.
     * @return true if the graphs are structurally equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph otherGraph)) {
            return false;
        }
        if (this.getVertexCount() != otherGraph.getVertexCount()) {
            return false;
        }
        for (int v = 0; v < this.getVertexCount(); v++) {
            if (!new HashSet<>(this.getNeighbors(v))
                .equals(new HashSet<>(otherGraph.getNeighbors(v)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a hash code for the graph using the multiplication method.
     *
     * @return the hash code based on set of neighbors.
     */
    @Override
    public int hashCode() {
        final double A = (Math.sqrt(5) - 1) / 2;
        final int bucketCount = 17;
        int hash = 0;

        for (int v = 0; v < getVertexCount(); v++) {
            int neighborHash = new HashSet<>(getNeighbors(v)).hashCode();
            hash = (int) (bucketCount * ((neighborHash * A) % 1));
        }
        return hash;
    }
}
