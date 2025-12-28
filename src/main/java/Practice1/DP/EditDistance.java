package Practice1.DP;

import java.util.Arrays;

public class EditDistance {
    int m, n;
    int[][] dp;

    private int solve(String s1, String s2, int i, int j) {

        // Base cases
        if (i == m) {
            return n - j;   // insert remaining characters of s2
        }
        if (j == n) {
            return m - i;   // delete remaining characters of s1
        }

        // Memoized result
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If characters match, no operation needed
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solve(s1, s2, i + 1, j + 1);
        }

        // Operations
        int insert = 1 + solve(s1, s2, i, j + 1);
        int delete = 1 + solve(s1, s2, i + 1, j);
        int replace = 1 + solve(s1, s2, i + 1, j + 1);

        return dp[i][j] = Math.min(insert, Math.min(delete, replace));
    }

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();

        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(word1, word2, 0, 0);
    }
}
