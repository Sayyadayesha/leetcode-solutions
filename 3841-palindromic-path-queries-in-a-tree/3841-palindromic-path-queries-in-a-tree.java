import java.util.*;

class Solution {
    int n, LOG, timer;
    List<Integer>[] tree;
    int[] tin, tout, depth, parent, bit, charMask;
    int[][] up;

    void add(int idx, int val) {
        while (idx <= n) {
            bit[idx] ^= val;
            idx += idx & -idx;
        }
    }

    int sum(int idx) {
        int res = 0;
        while (idx > 0) {
            res ^= bit[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        charMask = new int[n];

        // Iterative DFS
        int[] stack = new int[n];
        int[] iter = new int[n];
        Arrays.fill(parent, -1);

        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[top - 1];

            if (iter[u] == 0) {
                tin[u] = ++timer;
            }

            if (iter[u] < tree[u].size()) {
                int v = tree[u].get(iter[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Binary lifting for LCA
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Fenwick tree: subtree range updates, point queries
        bit = new int[n + 2];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');

            add(tin[i], charMask[i]);
            add(tout[i] + 1, charMask[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int newMask = 1 << (c - 'a');
                int delta = charMask[u] ^ newMask;

                add(tin[u], delta);
                add(tout[u] + 1, delta);

                charMask[u] = newMask;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = sum(tin[u]);
                int maskV = sum(tin[v]);

                int pathMask = maskU ^ maskV ^ charMask[ancestor];

                // At most one character can have an odd frequency
                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }
}