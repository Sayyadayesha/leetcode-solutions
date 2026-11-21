
import java.util.*;

class Solution {

    List<Integer>[] children;
    int[] lastSeen;
    int[] newParent;
    int[] answer;
    int[] parent;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        this.parent = parent;
        this.s = s;

        children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        newParent = new int[n];
        answer = new int[n];

        // Find new parents using the original tree
        dfsParent(0);

        // Build the final tree
        for (int i = 0; i < n; i++) {
            children[i].clear();
        }

        for (int i = 1; i < n; i++) {
            children[newParent[i]].add(i);
        }

        // Calculate subtree sizes in the final tree
        dfsSize(0);

        return answer;
    }

    private void dfsParent(int node) {

        int ch = s.charAt(node) - 'a';

        // Previous closest ancestor with the same character
        int previous = lastSeen[ch];

        if (previous != -1) {
            newParent[node] = previous;
        } else {
            newParent[node] = parent[node];
        }

        // Mark current node as the latest ancestor
        lastSeen[ch] = node;

        // Traverse original children
        for (int child : children[node]) {
            dfsParent(child);
        }

        // Backtrack to restore ancestor information
        lastSeen[ch] = previous;
    }

    private int dfsSize(int node) {

        int size = 1;

        for (int child : children[node]) {
            size += dfsSize(child);
        }

        answer[node] = size;

        return size;
    }
}
