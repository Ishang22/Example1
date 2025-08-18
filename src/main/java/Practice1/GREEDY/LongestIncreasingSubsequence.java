package Practice1.GREEDY;

/**
 * Description:<br>
 * Date: 18/08/25-3:34 pm
 *
 * @author ishangarg
 * @since
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int dp[][] = new int[nums.length][nums.length];
        return rec(-1, 0, nums, dp);
    }

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
