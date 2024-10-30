package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface Graph {
    void addVertex(int vertex);
    void removeVertex(int vertex);
    void addEdge(int from, int to);
    void removeEdge(int from, int to);
    int getVertexCount();
    boolean hasEdge(int from, int to);

    default List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < getVertexCount(); i++) {
            if (hasEdge(vertex, i)) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    default void readFromFile(String filename) {
        try {
            for (String line : Files.readAllLines(Paths.get(filename))) {
                String[] parts = line.split(" ");
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                addEdge(from, to);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
