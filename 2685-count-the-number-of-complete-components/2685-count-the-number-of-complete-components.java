class Solution {

    List<Integer>[] graph;
    boolean[] visited;
    int nodes;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {

        // Step 1: Create graph
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Step 2: Build graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[n];

        int ans = 0;

        // Step 3: Traverse all components
        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                nodes = 0;
                edgeCount = 0;

                dfs(i);

                // Each edge counted twice
                edgeCount /= 2;

                int expectedEdges = nodes * (nodes - 1) / 2;

                if (edgeCount == expectedEdges) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node) {

        visited[node] = true;

        nodes++;

        edgeCount += graph[node].size();

        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}