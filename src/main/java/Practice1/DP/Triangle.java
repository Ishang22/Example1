package Practice1.DP;

import java.util.List;

/*

        2              0   1
      1   3            1   2
    8   9   1          2   3
  4   1   8   3        3   4

 0    1   2     3    4
+----+----+----+----+----+
|  9 |   |    |    |    | 0
+----+----+----+----+----+
|  10|  7 |    |    |    | 1
+----+----+----+----+----+
| 9  |10  | 4  |    |    | 2
+----+----+----+----+----+
|  4 |  1 | 8  |  3 |    | 3
+----+----+----+----+----+
|  0 |  0 |  0 |  0 |  0 | 4
+----+----+----+----+----+

 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size(); // 4

        // dp array with extra row to avoid bounds checking
        int[][] dp = new int[height + 1][height + 1];
                            // 5           5
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
