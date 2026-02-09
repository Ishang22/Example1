package Practice1.DP;

public class UniquePathsIII {
}


class Solution {
    int m, n;
    int emptyCells;
    int result;

    int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    void dfs(int[][] grid, int currCount, int i, int j) {
        // Out of bounds or obstacle/visited
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1) {
            return;
        }

        // Reached ending square
        if (grid[i][j] == 2) {
            if (currCount == emptyCells) {
                result++;
            }
            return;
        }

        // Mark current cell as visited
        grid[i][j] = -1;

        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(grid, currCount + 1, ni, nj);
        }

        // Backtrack
        grid[i][j] = 0;
    }

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        emptyCells = 0;
        result = 0;

        int startX = 0, startY = 0;

        // Count empty cells and find start
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyCells++;
                }
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }

        // Include starting cell
        emptyCells += 1;

        dfs(grid, 0, startX, startY);
        return result;
    }
}