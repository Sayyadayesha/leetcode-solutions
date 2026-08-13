class Solution {

    class Node {
        int len;
        int prefix;
        int suffix;
        int best;
        char leftChar;
        char rightChar;

        Node(int len, int prefix, int suffix, int best,
             char leftChar, char rightChar) {
            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s,
                                  String queryCharacters,
                                  int[] queryIndices) {

        // Convert String to char array
        // because String is immutable in Java
        this.s = s.toCharArray();

        int n = s.length();
        int k = queryIndices.length;

        tree = new Node[4 * n];

        // Build segment tree
        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);

            // Update the actual character array
            this.s[index] = newChar;

            // Update segment tree
            update(1, 0, n - 1, index, newChar);

            // Root contains answer for entire string
            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build the segment tree
    private void build(int node, int left, int right) {

        // Leaf node
        if (left == right) {

            tree[node] = new Node(
                1,          // length
                1,          // prefix
                1,          // suffix
                1,          // best
                s[left],    // first character
                s[left]     // last character
            );

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Update one index
    private void update(int node, int left, int right,
                       int index, char ch) {

        // Leaf node
        if (left == right) {

            tree[node] = new Node(
                1,
                1,
                1,
                1,
                ch,
                ch
            );

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        // Recalculate current node
        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Merge two nodes
    private Node merge(Node left, Node right) {

        int len = left.len + right.len;

        char leftChar = left.leftChar;
        char rightChar = right.rightChar;

        // -----------------------------------
        // Calculate prefix
        // -----------------------------------

        int prefix = left.prefix;

        if (left.prefix == left.len &&
            left.rightChar == right.leftChar) {

            prefix = left.len + right.prefix;
        }

        // -----------------------------------
        // Calculate suffix
        // -----------------------------------

        int suffix = right.suffix;

        if (right.suffix == right.len &&
            left.rightChar == right.leftChar) {

            suffix = right.len + left.suffix;
        }

        // -----------------------------------
        // Calculate best
        // -----------------------------------

        int best = Math.max(left.best, right.best);

        // If boundary characters are same,
        // suffix of left + prefix of right
        // can form a bigger repeating substring.
        if (left.rightChar == right.leftChar) {

            best = Math.max(
                best,
                left.suffix + right.prefix
            );
        }

        return new Node(
            len,
            prefix,
            suffix,
            best,
            leftChar,
            rightChar
        );
    }
}