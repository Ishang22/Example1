package Practice1.DP;

// https://www.youtube.com/watch?v=6OjGE04Kx_M&t=46s

import java.util.Arrays;

/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
Time Complexity :
1) Recursion : O(2^n) - We have 2 possibilities for every stair.
2) Memoization : O(n) - We are not visiting already solved subproblems
3) Bottom UP : O(n) as we are iterating only once from i = 3 to i = n
                ___ 3
            __ 2
         __ 1
     __ start
 */
class climbStairs {

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
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int[] dp;
    // 0   1  2 3 4  5  6 7 8   9
    // [1,100,1,1,1,100,1,1,100,1]
    //                    7.->1+(9 vala 1) or 1+(8 vala 100)
    //                    8->100+(9 vala 1) or 100 +(0)

    private int solve(int idx, int[] cost) {
        // Base case
        if (idx >= cost.length) {
            return 0;
        }

        // Memoized result
        if (dp[idx] != -1) {
            return dp[idx];
        }

        // Take 1 step
        int oneStep = cost[idx] + solve(idx + 1, cost);

        // Take 2 steps
        int twoSteps = cost[idx] + solve(idx + 2, cost);

        // Store and return minimum
        dp[idx] = Math.min(oneStep, twoSteps);
        return dp[idx];
    }

    public int minCostClimbingStairs(int[] cost) {
        dp = new int[cost.length];
        Arrays.fill(dp, -1);

        // Can start from step 0 or step 1
        return Math.min(
                solve(0, cost),
                solve(1, cost)
        );
    }
}
