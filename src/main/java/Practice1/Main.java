package Practice1;

public class Main {
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Time Complexity → O(N × M × 2^L) L - is length of word and n *m is for nested loop
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

    /// ///////////         /////////////////////////////////////////////       //////////////////////////////
   // ⭐ Total Time Complexity = O(N × M)
    /*
    Why it’s not O(4^(N×M)) or exponential?
    Because even though DFS branches in 4 directions:
    up, down, left, right
    Each cell becomes 0 immediately, so no cell is explored more than once.
    Thus total DFS work = total number of grid cells.
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ++count;
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
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

    /// ////////////////////////////////////////////////////////////////////////////
    /// O(2^(N+M))
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
/*
        int[][] matrix ={
                         {9,9,4},
                         {9,6,8},
                         {2,1,1}
                        };
                        1 2 9 answer is 3
 */
    //O(N × M) time complexity
    static int calcualteMaxPath(int[][] matrix,int[][] utilMatrix,int i,int j,int prev) {

        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || prev>=matrix[i][j]) return 0;


        if(utilMatrix[i][j]!=0)
        {
            return   utilMatrix[i][j];
        }

        prev=matrix[i][j];

        int pathDown   =     calcualteMaxPath(matrix, utilMatrix, i + 1, j, prev);
        int pathUp     =     calcualteMaxPath(matrix, utilMatrix, i - 1, j, prev);
        int pathRight  =     calcualteMaxPath(matrix, utilMatrix, i, j + 1, prev);
        int pathLeft   =     calcualteMaxPath(matrix, utilMatrix, i, j - 1, prev);
        int maxOfNeighbors = Math.max(pathDown, Math.max(pathUp, Math.max(pathRight, pathLeft)));
        utilMatrix[i][j]=1 + maxOfNeighbors;
        return 1 + maxOfNeighbors;

    }
    /// ///////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] argv) {
        String word1 = "catnip";

        // i->0 s->1 h->2 a->3 n->4 @->5 g->6 m->7 ail.com
        String email = "ishan@gmail.com";

        int split_position = email.indexOf("@");//5

        //5                                                                  ishan                                            @gmail.com
        System.out.println("=====" + split_position + "     " + email.substring(0, split_position) + " domain name " + email.substring(split_position));

        char[][] grid1 = {
                {'c', 'a', 'p', 'i', 'b', 'x'},
                {'c', 'c', 'a', 't', 'i', 't'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'n', 'i', 'i', 'p', 'p'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'s', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'o', 'k', 'i'}
        };

        //                j      j         j        j        j      j
        //        {.       0.     1.       2.        3.      4.     5
        // i        0   {'00', '01',    '02',     '03',   '04',   '05'},
        // i        1   {'10', '11 c',  '12 a',   '13 t', '14',   '15'},
        // i        2   {'20', '21',    '22',    '23 n', '24 ',  '25'},
        // i        3   {'30', '31',    '32',    '33 i', '34 p', '35'},
        // i        4   {'40', '41',    '42',    '43',   '44',   '45'},
        // i        5   {'50', '51',    '52',    '53',   '54',   '55'},
        // i        6   {'60', '61',    '62',    '63',   '64',   '65'}
        //        }

        boolean[][] visited = new boolean[grid1.length][grid1[0].length];

        boolean result = false;

        boolean found = false;

        for (int i = 0; i < visited.length && !found; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                result = find(grid1, word1, visited, i, j, 0);
                if (result) {
                    found = true;
                    break;
                }
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
                {1, 1, 1, 1}
        };

        System.out.println(ratInMaze(maze, 0, 0));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int[][] matrix ={
                         {9,9,4},
                         {9,6,8},
                         {2,1,1}
                        };

        int[][] utilMatrix=new int[matrix.length][matrix[0].length];


        int maxLength= Integer.MIN_VALUE;

        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                maxLength= Math.max(calcualteMaxPath(matrix,utilMatrix,i,j,Integer.MIN_VALUE),maxLength);
            }
        }



        System.out.println("maxLength    "+maxLength);
    }
}
//   https://www.notion.so/261c39c88693803dbbbfe605a8954019?v=261c39c8869381919de9000c68d253aa