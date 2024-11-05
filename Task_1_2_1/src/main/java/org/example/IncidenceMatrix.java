package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph implementation using an incidence matrix representation.
 */
public class IncidenceMatrix extends AbstractGraph {
    private final List<List<Integer>> incidenceMatrix;
    private int vertexCount;

    /**
     * Initializes the incidence matrix with a specified number of vertices.
     *
     * @param size the initial number of vertices.
     */
    public IncidenceMatrix(int size) {
        incidenceMatrix = new ArrayList<>();
        vertexCount = size;
    }

    @Override
    public int addVertex() {
        for (List<Integer> edge : incidenceMatrix) {
            edge.add(0);
        }
        return vertexCount++;
    }

    @Override
    public boolean hasVertex(int vertex) {
        return vertex >= 0 && vertex < vertexCount;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Couldn't remove a vertex - vertex "
                + vertex + " does not exist.");
        }
        incidenceMatrix.removeIf(edge -> edge.get(vertex) == 1 || edge.get(vertex) == -1);
        for (List<Integer> edge : incidenceMatrix) {
            edge.remove(vertex);
        }
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't add an edge -"
                + " one or both vertices do not exist.");
        }
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
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't remove an edge -"
                + " one or both vertices do not exist.");
        }
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
        return sb.toString();
    }
}

