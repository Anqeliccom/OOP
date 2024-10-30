package org.example;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix extends AbstractGraph {
    private final List<List<Boolean>> matrix;
    private int vertexCount;

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
    public void addVertex(int vertex) {
        List<Boolean> newRow = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            newRow.add(false);
        }
        matrix.add(newRow);
        for (List<Boolean> row : matrix) {
            row.add(false);
        }
        vertexCount++;
    }

    @Override
    public void removeVertex(int vertex) {
        matrix.remove(vertex);
        for (List<Boolean> row : matrix) {
            row.remove(vertex);
        }
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        matrix.get(from).set(to, true);
    }

    @Override
    public void removeEdge(int from, int to) {
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
            for (Boolean cell : row) {
                sb.append(cell ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
