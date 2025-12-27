package Practice1.Kadane;

public class MaximumSumSubArray {

    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currmax = nums[0];

        for(int i=1;i<nums.length;i++)
        {
            currmax= Math.max(nums[i],currmax+nums[i]);

            maxSoFar=Math.max(maxSoFar,currmax);
        }

        return maxSoFar;
    }

}
