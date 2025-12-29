package Practice1.DP;

import java.util.Arrays;

public class MinimumPathSum {
    private int solve(int[][] grid, int i, int j, int m, int n, int[][] dp) {

        // Base case: reached bottom-right cell
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        // If already computed
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If last row → can only go RIGHT
        if (i == m - 1) {
            return dp[i][j] = grid[i][j] + solve(grid, i, j + 1, m, n, dp);
        }

        // If last column → can only go DOWN
        if (j == n - 1) {
            return dp[i][j] = grid[i][j] + solve(grid, i + 1, j, m, n, dp);
        }

        // Otherwise → choose min of RIGHT or DOWN
        int right = solve(grid, i, j + 1, m, n, dp);
        int down = solve(grid, i + 1, j, m, n, dp);

        return dp[i][j] = grid[i][j] + Math.min(right, down);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // Initialize dp with -1
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(grid, 0, 0, m, n, dp);
    }
}
