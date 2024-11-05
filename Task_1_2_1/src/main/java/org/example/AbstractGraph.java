package org.example;

import java.util.HashSet;

/**
 * An abstract base class for graph implementations, providing common methods.
 */
public abstract class AbstractGraph implements Graph {

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
