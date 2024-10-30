package org.example;

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
            if (!this.getNeighbors(v).equals(otherGraph.getNeighbors(v))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final double A = (Math.sqrt(5) - 1) / 2;
        final int BUCKET_COUNT = 17;
        int hash = 0;

        for (int v = 0; v < getVertexCount(); v++) {
            int neighborHash = getNeighbors(v).hashCode();
            hash += (int) (BUCKET_COUNT * ((neighborHash * A) % 1));
        }
        return hash;
    }
}
