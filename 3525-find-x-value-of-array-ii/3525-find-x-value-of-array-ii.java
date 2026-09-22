class Solution {

    static class Node {
        int[] cnt;
        int product;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    int n;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            update(1, 0, n - 1, index, value);

            // Query nums[start...n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            answer[i] = res.cnt[x];
        }

        return answer;
    }

    void build(int[] nums, int node, int left, int right) {
        if (left == right) {
            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(nums, node * 2, left, mid);
        build(nums, node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {
        Node res = new Node(k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += a.cnt[r];
        }

        // Prefixes that contain all of left
        // and some prefix of right
        for (int r = 0; r < k; r++) {
            int newRem = (a.product * r) % k;
            res.cnt[newRem] += b.cnt[r];
        }

        res.product = (a.product * b.product) % k;

        return res;
    }

    void update(int node, int left, int right, int index, int value) {
        if (left == right) {
            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right,
               int queryLeft, int queryRight) {

        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (queryRight <= mid) {
            return query(node * 2, left, mid,
                         queryLeft, queryRight);
        }

        if (queryLeft > mid) {
            return query(node * 2 + 1, mid + 1, right,
                         queryLeft, queryRight);
        }

        Node a = query(node * 2, left, mid,
                       queryLeft, queryRight);

        Node b = query(node * 2 + 1, mid + 1, right,
                       queryLeft, queryRight);

        return merge(a, b);
    }
}