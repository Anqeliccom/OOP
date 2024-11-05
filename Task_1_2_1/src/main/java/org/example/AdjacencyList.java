package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph implementation using an adjacency list representation.
 */
public class AdjacencyList extends AbstractGraph {
    private final List<List<Integer>> adjacencyList;
    private int vertexCount;

    /**
     * Initializes the adjacency list with a specified number of vertices.
     *
     * @param size the initial number of vertices.
     */
    public AdjacencyList(int size) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        vertexCount = size;
    }

    @Override
    public int addVertex() {
        adjacencyList.add(new ArrayList<>());
        return vertexCount++;
    }

    @Override
    public boolean hasVertex(int vertex) {
        return vertex >= 0 && vertex < adjacencyList.size() && adjacencyList.get(vertex) != null;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Couldn't remove a vertex - vertex "
                + vertex + " does not exist.");
        }
        for (List<Integer> neighbors : adjacencyList) {
            neighbors.remove(Integer.valueOf(vertex));
        }
        adjacencyList.set(vertex, null);
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't add an edge -"
                + " one or both vertices do not exist.");
        }
        adjacencyList.get(from).add(to);
    }

    @Override
    public void removeEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Couldn't remove an edge -"
                + " one or both vertices do not exist.");
        }
        adjacencyList.get(from).remove(Integer.valueOf(to));
    }

    @Override
    public boolean hasEdge(int from, int to) {
        return adjacencyList.get(from).contains(to);
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int displayedIndex = 0;

        for (List<Integer> neighbors : adjacencyList) {
            if (neighbors != null) {
                sb.append(displayedIndex).append(": ");
                for (int neighbor : neighbors) {
                    sb.append(neighbor).append(", ");
                }
                if (!neighbors.isEmpty()) {
                    sb.delete(sb.length() - 2, sb.length());
                }
                sb.append("\n");
                displayedIndex++;
            }
        }
        return sb.toString();
    }
}
