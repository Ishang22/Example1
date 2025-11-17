package Practice1.GREEDY;

/**
 * Description: Longest Increasing Subsequence using Dynamic Programming (Memoization)<br>
 * Date: 18/08/25-3:34 pm
 *
 * Time Complexity: O(n²)
 * - There are n × n possible states (prev index × current index)
 * - Each state is computed only once due to memoization
 * - Each state computation takes O(1) time
 * 
 * Space Complexity: O(n²)
 * - dp array of size n × n takes O(n²) space
 * - Recursion stack depth can go up to O(n)
 * - Overall space complexity is O(n²)
 *
 * @author ishangarg
 * @since
 */
/*
  * ═══════════════════════════════════════════════════════════════════════════════════
  * LONGEST INCREASING SUBSEQUENCE - RECURSION TREE
  * ═══════════════════════════════════════════════════════════════════════════════════
  * Array: [1, 2, 5, 3, 4, 9]
  * Index:  0  1  2  3  4  5
  *
  * At each step: INCLUDE element (if valid) OR SKIP element
  * ═══════════════════════════════════════════════════════════════════════════════════
  *
  */

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int dp[][] = new int[nums.length][nums.length];
        return rec(-1, 0, nums, dp);
    }
//Final Time Complexity: O(n²)
    private static int rec(int prev, int curr, int nums[], int dp[][]) {
        if (curr == nums.length)
            return 0;


        if (prev != -1 && dp[prev][curr] != 0)
            return dp[prev][curr];

        int op1 = 0;

        if (prev == -1 || nums[prev] < nums[curr]) {
            op1 = 1 + rec(curr, curr + 1, nums, dp);
        }

        int op2 = rec(prev, curr + 1, nums, dp);

        if (prev != -1)
            dp[prev][curr] = Math.max(op1, op2);

        return Math.max(op1, op2);
    }
}
