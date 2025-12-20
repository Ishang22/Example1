package Practice1.DP;

public class LongestCommonSequence {

    int m, n;
    int[][] dp;

    private int solve(String s1, String s2, int i, int j) {
        // Base case
        if (i >= m || j >= n) {
            return 0;
        }

        // Memoized result
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If characters match
        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = 1 + solve(s1, s2, i + 1, j + 1);
            return dp[i][j];
        }

        // If characters don't match
        dp[i][j] = Math.max(
                solve(s1, s2, i + 1, j),
                solve(s1, s2, i, j + 1)
        );

        return dp[i][j];
    }

    public int longestCommonSubsequence(String s1, String s2) {
        m = s1.length();
        n = s2.length();

        dp = new int[m][n];

        // Initialize dp with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(s1, s2, 0, 0);
    }
}
