package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Interface representing a directed graph with basic operations for
 * adding and removing vertices and edges, retrieving neighbors of a vertex,
 * reading from a file and other operations.
 */
public interface Graph {
    /**
     * Adds a new vertex to the graph with the next available vertex number.
     *
     * @return the index of the newly added vertex.
     */
    int addVertex();

    /**
     * Removes a vertex from the graph.
     * Does not preserve the order of the vertex numbers for IncidenceMatrix.
     *
     * @param vertex the vertex to be removed.
     * @throws IllegalArgumentException if the vertex does not exist.
     */
    void removeVertex(int vertex);

    /**
     * Adds a directed edge between two vertices.
     *
     * @param from the starting vertex.
     * @param to the ending vertex.
     * @throws IllegalArgumentException if one or both vertices do not exist.
     */
    void addEdge(int from, int to);

    /**
     * Removes a directed edge between two vertices.
     *
     * @param from the starting vertex.
     * @param to the ending vertex.
     * @throws IllegalArgumentException if one or both vertices do not exist.
     */
    void removeEdge(int from, int to);

    /**
     * Returns the total number of vertices in the graph.
     *
     * @return the number of vertices.
     */
    int getVertexCount();

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param from the starting vertex.
     * @param to the ending vertex.
     * @return true if the edge exists, false otherwise.
     */
    boolean hasEdge(int from, int to);

    /**
     * Checks if a vertex exists in the graph.
     *
     * @param vertex the vertex to check.
     * @return true if the vertex exists, false otherwise.
     */
    boolean hasVertex(int vertex);

    /**
     * Returns the neighbors of a given vertex.
     *
     * @param vertex the vertex whose neighbors are requested.
     * @return a set of neighbor vertices.
     */
    default Set<Integer> getNeighbors(int vertex) {
        Set<Integer> neighbors = new HashSet<>();
        for (int i = 0; i < getVertexCount(); i++) {
            if (hasEdge(vertex, i)) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Reads a graph from a file and populates the graph with vertices and edges.
     *
     * @param filename the path to the file.
     * @param graph an empty graph of size 0 to populate.
     */
    default void readFromFile(String filename, Graph graph) {
        try {
            int maxVertex = -1;

            Path path = Paths.get(filename);
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(" ");
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                maxVertex = Math.max(maxVertex, Math.max(from, to));
            }

            while (graph.getVertexCount() <= maxVertex) {
                graph.addVertex();
            }

            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(" ");
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                graph.addEdge(from, to);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
