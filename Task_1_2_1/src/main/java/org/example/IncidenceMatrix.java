package org.example;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrix extends AbstractGraph {
    private final List<List<Integer>> incidenceMatrix;
    private int vertexCount;

    public IncidenceMatrix(int size) {
        incidenceMatrix = new ArrayList<>();
        vertexCount = size;
    }

    @Override
    public void addVertex(int vertex) {
        for (List<Integer> edge : incidenceMatrix) {
            edge.add(0);
        }
        vertexCount++;
    }

    @Override
    public void removeVertex(int vertex) {
        for (List<Integer> edge : incidenceMatrix) {
            edge.set(vertex, 0);
        }
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        List<Integer> edge = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            edge.add(0);
        }
        edge.set(from, 1);
        edge.set(to, -1);
        incidenceMatrix.add(edge);
    }

    @Override
    public void removeEdge(int from, int to) {
        for (List<Integer> edge : incidenceMatrix) {
            if (edge.get(from) == 1 && edge.get(to) == -1) {
                incidenceMatrix.remove(edge);
                break;
            }
        }
    }

    @Override
    public boolean hasEdge(int from, int to) {
        for (List<Integer> edge : incidenceMatrix) {
            if (edge.get(from) == 1 && edge.get(to) == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> edge : incidenceMatrix) {
            for (int value : edge) {
                sb.append(value).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}

