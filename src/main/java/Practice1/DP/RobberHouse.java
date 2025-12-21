package Practice1.DP;

import java.util.Arrays;

public class RobberHouse {

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return robb(nums, nums.length - 1, dp);
    }

    public int robb(int[] nums, int index, int[] dp) {
        // If there is only one house
        if (index == 0) return nums[index];

        // If there are no houses
        if (index < 0) return 0;

        // If already calculated, return stored value
        if (dp[index] != -1) return dp[index];

        // Option 1: Rob this house
        int pick = nums[index] + robb(nums, index - 2, dp);

        // Option 2: Skip this house
        int notPick = robb(nums, index - 1, dp);

        // Store and return the best option
        return dp[index] = Math.max(pick, notPick);
    }

    /// ////////////////////////////////////House Robber II///////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////House Robber II///////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////House Robber II///////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////House Robber II///////////////////////////////////////////////////////////////////////////


    int[] t = new int[101];

    private int solve(int[] nums, int i, int n) {
        // Base case
        if (i > n)
            return 0;

        // Memoized result
        if (t[i] != -1)
            return t[i];

        // Take current house (i) and move to i+2
        int take = nums[i] + solve(nums, i + 2, n);

        // Skip current house and move to i+1
        int skip = solve(nums, i + 1, n);

        // Store and return the maximum
        return t[i] = Math.max(take, skip);
    }

    public int rob2(int[] nums) {
        int n = nums.length;

        // Edge cases
        if (n == 1)
            return nums[0];

        if (n == 2)
            return Math.max(nums[0], nums[1]);

        // -------- Case 1: Take 0th house (can't take last house) --------
        Arrays.fill(t, -1);
        int take0thIndexHouse = solve(nums, 0, n - 2);

        // -------- Case 2: Skip 0th house, take from 1st to last --------
        Arrays.fill(t, -1);
        int take1stIndexHouse = solve(nums, 1, n - 1);

        // Final answer
        return Math.max(take0thIndexHouse, take1stIndexHouse);
    }
}
