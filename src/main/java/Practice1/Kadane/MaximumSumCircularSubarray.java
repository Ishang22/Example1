package Practice1.Kadane;

public class MaximumSumCircularSubarray {

    // Kadane's Algorithm for Maximum Subarray Sum
    private int kadanesMax(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    // Kadane's Algorithm for Minimum Subarray Sum
    private int kadanesMin(int[] nums) {
        int sum = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.min(sum + nums[i], nums[i]);
            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // 1. Total sum of array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // 2. Maximum subarray sum (normal case)
        int maxSum = kadanesMax(nums);

        // 3. Minimum subarray sum
        int minSum = kadanesMin(nums);

        // 4. Circular subarray sum
        int circularSum = totalSum - minSum;

        // If all numbers are negative, circularSum becomes 0 (invalid)
        if (maxSum > 0) {
            return Math.max(maxSum, circularSum);
        }

        return maxSum;
    }

}


