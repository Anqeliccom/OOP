package org.example;

public class TopologicalSort {
    private int counter;

    public int[] sort(Graph graph) {
        int vertexCount = graph.getVertexCount();
        counter = vertexCount;
        boolean[] visited = new boolean[vertexCount];
        boolean[] recursionStack = new boolean[vertexCount];
        int[] result = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i] && dfs(graph, i, visited, recursionStack, result)) {
                return new int[0];
            }
        }
        return result;
    }

    private boolean dfs(Graph graph, int node, boolean[] visited, boolean[] recursionStack, int[] result) {
        visited[node] = true;
        recursionStack[node] = true;

        for (int neighbor : graph.getNeighbors(node)) {
            if ((!visited[neighbor] && dfs(graph, neighbor, visited, recursionStack, result)) || recursionStack[neighbor]) {
                return true;
            }
        }

        result[--counter] = node;
        recursionStack[node] = false;
        return false;
    }
}
