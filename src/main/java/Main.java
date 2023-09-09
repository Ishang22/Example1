import java.util.*;


public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean find(char[][] grid1, String word, boolean[][] visited, int i, int j, int k) {

        if (word.length() - 1 == k && isSafe(grid1, i, j, k, word)) {
            visited[i][j] = true;
            return true;
        }

        if (isSafe(grid1, i, j, k, word)) {

            visited[i][j] = true;
            ++k;

            if (find(grid1, word, visited, i + 1, j, k)) {
                return true;
            }

            if (find(grid1, word, visited, i, j + 1, k)) {
                return true;
            }

            visited[i][j] = false;
            --k;
        }

        return false;
    }

    public static boolean isSafe(char[][] grid1, int i, int j, int k, String word) {
        return i >= 0 && j >= 0 && i < grid1.length && j < grid1[0].length && k < word.length() && word.charAt(k) == grid1[i][j];
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int count = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) {
                    ++count;
                    max = Math.max(max, dfs(grid, i, j));
                }

        System.out.println("===count====" + count);
        return max;
    }

    public static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 0;

        return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
    }

    ///////////////////////////////////////////////////////////////////////////////

    public static boolean ratInMaze(int[][] maze, int i, int j) {
        if (maze.length - 1 == i && maze[0].length - 1 == j && maze[i][j] == 1) {
            return true;
        }

        if (isSafeRat(maze, i, j)) {
            if (ratInMaze(maze, i + 1, j)) {
                return true;
            }

            if (ratInMaze(maze, i, j + 1)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSafeRat(int[][] grid1, int i, int j) {
        return i >= 0 && j >= 0 && i < grid1.length && j < grid1[0].length && grid1[i][j] == 1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] argv) {
        String word1 = "catnip";

        //            0123456789
        String email="ishan@gmail.com";

        int split_position = email.indexOf("@");//5

                                    //5                                 ishan                                    @gmail.com
        System.out.println("====="+split_position+"     "+email.substring(0,split_position)+" domain name "+email.substring(split_position));

        char[][] grid1 = {
                {'c', 'a', 'p', 'i', 'b', 'x'},
                {'c', 'c', 'a', 't', 'i', 't'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'n', 'i', 'i', 'p', 'p'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'s', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'o', 'k', 'i'}
        };

        boolean[][] visited = new boolean[grid1.length][grid1[0].length];


        boolean result = false;

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                result = find(grid1, word1, visited, i, j, 0);
            }
            if (result) {
                break;
            }
        }

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) {
                    System.out.println("==========" + i + " " + j);
                }
            }
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}
        };

        System.out.println("=====maxAreaOfIsland====" + maxAreaOfIsland(grid));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};

        System.out.println(ratInMaze(maze, 0, 0));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}