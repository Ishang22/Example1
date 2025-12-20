package Practice1.DP;

// https://www.youtube.com/watch?v=6OjGE04Kx_M&t=46s

/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
Time Complexity :
1) Recursion : O(2^n) - We have 2 possibilities for every stair.
2) Memoization : O(n) - We are not visiting already solved subproblems
3) Bottom UP : O(n) as we are iterating only once from i = 3 to i = n
 */
class climbStairs {
    public int climbStairsBottomUp(int n) {

        // Base cases
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // with memo - Time Complexity = O(n)
    // Time Complexity = O(2ⁿ)
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return rec(n, memo);
    }

    private static int rec(int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (memo[n] > 0) return memo[n];
        int op1 = rec(n - 1, memo);
        int op2 = rec(n - 2, memo);
        memo[n] = op1 + op2;
        return op1 + op2;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int[] cost;

    private int solve(int idx) {
        // Base case
        if (idx >= cost.length) {
            return 0;
        }

        int a = cost[idx] + solve(idx + 1);
        int b = cost[idx] + solve(idx + 2);

        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        return Math.min(solve(0), solve(1));
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int minCostClimbingStairsBottomUp(int[] cost) {
        int n = cost.length;

        // Edge case
        if (n == 2) {
            return Math.min(cost[0], cost[1]);
        }

        // Build DP in-place
        for (int i = 2; i < n; i++) {
            cost[i] = cost[i] + Math.min(cost[i - 1], cost[i - 2]);
        }

        // You can reach the top from either of last two steps
        return Math.min(cost[n - 1], cost[n - 2]);
    }

}
