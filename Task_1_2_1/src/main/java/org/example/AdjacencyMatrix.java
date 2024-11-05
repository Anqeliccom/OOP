package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph implementation using an adjacency matrix representation.
 */
public class AdjacencyMatrix extends AbstractGraph {
    private final List<List<Boolean>> matrix;
    private int vertexCount;

    /**
     * Initializes the adjacency matrix with a specified number of vertices.
     *
     * @param size the initial number of vertices.
     */
    public AdjacencyMatrix(int size) {
        matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(false);
            }
            matrix.add(row);
        }
        vertexCount = size;
    }

    @Override
    public int addVertex() {
        List<Boolean> newRow = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            newRow.add(false);
        }
        matrix.add(newRow);
        for (List<Boolean> row : matrix) {
            row.add(false);
        }
        return vertexCount++;
    }

    @Override
    public boolean hasVertex(int vertex) {
        return vertex >= 0 && vertex < matrix.size() && matrix.get(vertex) != null;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Couldn't remove a vertex - vertex "
                + vertex + " does not exist.");
        }
        for (List<Boolean> row : matrix) {
            row.remove(vertex);
        }
        matrix.set(vertex, null);
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't add an edge -"
                + " one or both vertices do not exist.");
        }
        matrix.get(from).set(to, true);
    }

    @Override
    public void removeEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't remove an edge -"
                + " one or both vertices do not exist.");
        }
        matrix.get(from).set(to, false);
    }

    @Override
    public boolean hasEdge(int from, int to) {
        return matrix.get(from).get(to);
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Boolean> row : matrix) {
            if (row != null) {
                for (int j = 0; j < row.size(); j++) {
                    sb.append(row.get(j) ? "1" : "0");
                    if (j < row.size() - 1) {
                        sb.append(" ");
                    }
                }
            } else {
                sb.append("- ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}