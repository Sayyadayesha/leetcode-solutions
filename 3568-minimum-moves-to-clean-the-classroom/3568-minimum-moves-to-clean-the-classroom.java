import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        // Give every litter cell a bit number
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;

        // best[r][c][mask] = maximum energy reached at this state
        int[][][] best = new int[m][n][totalMasks];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<State> queue = new ArrayDeque<>();

        best[startR][startC][0] = energy;
        queue.offer(new State(startR, startC, 0, energy));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int allCollected = (1 << litterCount) - 1;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {
                State cur = queue.poll();

                int r = cur.r;
                int c = cur.c;
                int mask = cur.mask;
                int currEnergy = cur.energy;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Need 1 energy to make a move
                    if (currEnergy == 0) {
                        continue;
                    }

                    int newEnergy = currEnergy - 1;
                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    // All litter collected
                    if (newMask == allCollected) {
                        return moves + 1;
                    }

                    // If we have already reached this state
                    // with equal or greater energy, skip it.
                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;

                    queue.offer(
                        new State(nr, nc, newMask, newEnergy)
                    );
                }
            }

            moves++;
        }

        return -1;
    }

    static class State {
        int r;
        int c;
        int mask;
        int energy;

        State(int r, int c, int mask, int energy) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
        }
    }
}