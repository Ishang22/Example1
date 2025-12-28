package Practice1.DP;

public class InterleavingString {

    int m, n, N;
    int[][] dp; // -1 = unvisited, 0 = false, 1 = true

    private boolean solve(int i, int j, String s1, String s2, String s3) {

        // If both strings are fully consumed
        if (i == m && j == n && i + j == N) {
            return true;
        }

        // If s3 is consumed but s1 or s2 is not
        if (i + j >= N) {
            return false;
        }

        // Memo check
        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        boolean result = false;

        // Take from s1
        if (i < m && s1.charAt(i) == s3.charAt(i + j)) {
            result = solve(i + 1, j, s1, s2, s3);
        }

        // If already true, no need to check s2
        if (result) {
            dp[i][j] = 1;
            return true;
        }

        // Take from s2
        if (j < n && s2.charAt(j) == s3.charAt(i + j)) {
            result = solve(i, j + 1, s1, s2, s3);
        }

        dp[i][j] = result ? 1 : 0;
        return result;
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        m = s1.length();
        n = s2.length();
        N = s3.length();

        // Length mismatch → impossible
        if (m + n != N) return false;

        dp = new int[m + 1][n + 1];

        // Initialize dp with -1
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0, 0, s1, s2, s3);
    }
}
