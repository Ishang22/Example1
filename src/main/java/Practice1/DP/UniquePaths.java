package Practice1.DP;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // first row or first column -> only 1 way to reach
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                } else {
                    // memoize ways from left + ways from top
                    grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
                }
            }
        }

        return grid[m - 1][n - 1];
    }

    // quick run
    public static void main(String[] args) {
        UniquePaths s = new UniquePaths();
        System.out.println(s.uniquePaths(3, 7)); // expected 28
    }
}
