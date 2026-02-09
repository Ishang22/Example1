package Practice1;

import java.util.*;

//TIME COMPLEXITY -> 0(m*n)
public class pacificAtlantic {

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Top and Bottom borders
        for (int col = 0; col < n; col++) {
            dfs(matrix, 0, col, Integer.MIN_VALUE, pacific);
            dfs(matrix, m - 1, col, Integer.MIN_VALUE, atlantic);
        }

        // Left and Right borders
        for (int row = 0; row < m; row++) {
            dfs(matrix, row, 0, Integer.MIN_VALUE, pacific);
            dfs(matrix, row, n - 1, Integer.MIN_VALUE, atlantic);
        }

        // Cells reachable from both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] matrix, int i, int j, int prevHeight, boolean[][] ocean) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length)
            return;

        if (ocean[i][j] || matrix[i][j] < prevHeight)
            return;

        ocean[i][j] = true;

        dfs(matrix, i + 1, j, matrix[i][j], ocean);
        dfs(matrix, i - 1, j, matrix[i][j], ocean);
        dfs(matrix, i, j + 1, matrix[i][j], ocean);
        dfs(matrix, i, j - 1, matrix[i][j], ocean);
    }
}
