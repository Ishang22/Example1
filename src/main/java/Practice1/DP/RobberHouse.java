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

}
