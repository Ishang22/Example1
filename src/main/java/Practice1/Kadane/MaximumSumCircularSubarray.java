package Practice1.Kadane;

public class MaximumSumCircularSubarray {

    private int kadanes(int[] nums){
        int maxSoFar = nums[0];
        int currmax = nums[0];

        for(int i=1;i<nums.length;i++)
        {
            currmax= Math.max(nums[i],currmax+nums[i]);

            maxSoFar=Math.max(maxSoFar,currmax);
        }

        return maxSoFar;
    }
    public int maxSubarraySumCircular(int[] A) {
        if(A.length == 0) return 0;
        int x = kadanes(A);
        int y = 0;
        for(int i=0; i<A.length;i++){
            y += A[i];
            A[i] *= -1;
        }
        int z = kadanes(A);
        if(y+z == 0) return x;
        return  Math.max(x, y+z);
    }

}


