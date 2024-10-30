package org.example;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList extends AbstractGraph {
    private final List<List<Integer>> adjacencyList;
    private int vertexCount;

    public AdjacencyList(int size) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        vertexCount = size;
    }

    @Override
    public void addVertex(int vertex) {
        adjacencyList.add(new ArrayList<>());
        vertexCount++;
    }

    @Override
    public void removeVertex(int vertex) {
        for (List<Integer> neighbors : adjacencyList) {
            neighbors.remove(Integer.valueOf(vertex));
        }
        adjacencyList.set(vertex, new ArrayList<>());
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
    }

    @Override
    public void removeEdge(int from, int to) {
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
        for (int i = 0; i < adjacencyList.size(); i++) {
            sb.append(i).append(": ");
            for (int neighbor : adjacencyList.get(i)) {
                sb.append(neighbor).append(", ");
            }
            if (sb.charAt(sb.length() - 2) == ',') {
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
