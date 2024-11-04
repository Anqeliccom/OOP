package org.example;

/**
 * Class for performing topological sorting on directed graphs.
 */
public class TopologicalSort {
    private int counter;

    /**
     * Initiates a DFS on each unvisited vertex to process and order all reachable vertices.
     *
     * @param graph the graph to be sorted.
     * @return an array of vertices in topological order or an empty array if a cycle is detected.
     */
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

    /**
     * Performs a DFS to detect cycles and populate the topological order.
     *
     * @param graph the graph being sorted.
     * @param node the current node.
     * @param visited array tracking visited nodes.
     * @param recursionStack array tracking nodes in the current recursive stack.
     * @param result array storing the topological sort order.
     * @return true if a cycle is detected, false otherwise.
     */
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
