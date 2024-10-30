package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph g1 = new AdjacencyMatrix(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);

        Graph g2 = new AdjacencyList(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(3, 5);
        g2.addEdge(4, 5);

        Graph g3 = new IncidenceMatrix(5);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);
        g3.addEdge(3, 4);
        g3.addEdge(4, 1);

        TopologicalSort sorter = new TopologicalSort();

        printMessage("g1 (Adjacency Matrix)", g1, sorter);
        printMessage("g2 (Adjacency List)", g2, sorter);
        printMessage("g3 (Incidence Matrix)", g3, sorter);
    }

    private static void printMessage(String graphName, Graph graph, TopologicalSort sorter) {
        int[] sorted = sorter.sort(graph);
        if (sorted.length == 0) {
            System.out.println("Graph " + graphName + ":\n" + graph + "\n" + "Graph contains a cycle. Topological sorting is not possible.\n");
        } else {
            System.out.println("Graph " + graphName + ":\n" + graph + "\nTopological Sort of " + graphName + ": " + Arrays.toString(sorted) + "\n");
        }
    }
}
