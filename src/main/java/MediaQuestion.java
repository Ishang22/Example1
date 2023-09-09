//  Given a list of values containing stock prices for each day, find the maximum profit.
//  There are two conditions: At each day you can either Buy, Sell or Ignore You can buy only 1 quantity
//  But, you can sell multiple quantities.

//        N = 100 total elements
//        Input : [2, 5, 7, 1, 5, 6, 10] k = 3
//
//
//
//
//        At each day, can you tell me if i should hold, sell, or buy
//        buy → profit
//        → [2,2,-1]
//        int findMaxProfit(int[] stockPrices) {
//        int maxValue=-1,totalProfit=0;
//        for(int i=stockPrices.length-1;i>=0;i–)
//        {
//        if(maxValue>stockPrices[i])
//        {
//        totalProfit=totalProfit+maxValue-stockPrices[i];
//        }
//        else
//        {
//        maxValue=stockPrices[i];
//        }
//        return totalProfit;
//        }
//        }
//
//
//
//
//        You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
//        The length of the subarray is k, and
//        All the elements of the subarray are distinct.
//        Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
//        Input: nums = [1,5,4,2,9,9,9], k = 3
//        2-5
//        [1, 2, 3, 9, 14, 3, 7] k = 4
//        [1,]
//        Output: 15
//        Explanation: The subarrays of nums with length 3 are:
//        - [1,5,4] which meets the requirements and has a sum of 10.
//        - [5,4,2] which meets the requirements and has a sum of 11.
//        - [4,2,9] which meets the requirements and has a sum of 15.
//        - [2,9,9] which does not meet the requirements because the element 9 is repeated.
//        - [9,9,9] which does not meet the requirements because the element 9 is repeated.
//        We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
//        Input: nums = [1,5,4,2,9,9,9], k = 3
//        [1,6,10]
//        1-0
//        5-1
//        4-2
//
//
//public long maximumSubarraySum(int[] nums, int k) {
//        HashMap<Integer,Integer> m1 = new  HashMap<>();
//        int maxSum=0;
//        int i=0,j=nums.length;
//        while(i<j)
//        {
//        if(m1.size()<k)
//        {
//        m1.put(nums[i],i);
//        if(i>0)
//        {
//        nums[i]=nums[i]+nums[i-1];
//        }
//        }
//        else if(m1.size()==k )
//        {
//        if(nums[i]-nums[i-k]>maxSum)
//        {
//        maxSum=nums[i]-nums[i-k];
//        }
//
//
//        }
//
//
//        else if(m1.size()>k)
//        {
//        m1.remove(nums[i]);
//        }
//        i++;
//        }
//
//
//        return maxSum;
//        }


//https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/solutions/2784440/simple-java-solution-sliding-window-tc-o-n-sc-o-n/



