package Practice1.DP;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();

        // dp array with extra row to avoid bounds checking
        int[][] dp = new int[height + 1][height + 1];

        // Start from the last row and move upwards
        for (int level = height - 1; level >= 0; level--) {
            for (int i = 0; i <= level; i++) {
                dp[level][i] = triangle.get(level).get(i)
                        + Math.min(dp[level + 1][i], dp[level + 1][i + 1]);
            }
        }

        return dp[0][0];
    }
}
