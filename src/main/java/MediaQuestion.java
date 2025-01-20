import java.util.HashMap;

// find the maximum sum of a subarray with exactly k distinct elements.
class MinimumSubArrayWithKLengthq {
    //  https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/solutions/2784440/simple-java-solution-sliding-window-tc-o-n-sc-o-n/
    // With Distinct
    static long maximumSubarraySum(int[] nums, int k) {
        long answer = 0;
        long sum = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        int release = 0;
        int distinct = 0;

        for (int i = 0; i < k; i++) {
            if (mp.containsKey(nums[i])) {
                sum += nums[i];
                mp.put(nums[i], mp.get(nums[i]) + 1);
            } else {
                mp.put(nums[i], 1);
                distinct++;
                sum += nums[i];
            }
        }

        if (distinct == k) {
            answer = Math.max(answer, sum);
        }

        for (int acquire = k; acquire < nums.length; acquire++) {

            sum -= nums[release];
            mp.put(nums[release], mp.get(nums[release]) - 1);

            if (mp.get(nums[release]) == 0) {
                mp.remove(nums[release]);
                distinct--;
            }
            release++;

            if (mp.containsKey(nums[acquire])) {
                sum += nums[acquire];
                mp.put(nums[acquire], mp.get(nums[acquire]) + 1);
            } else {
                mp.put(nums[acquire], 1);
                distinct++;
                sum += nums[acquire];
            }

            if (distinct == k) answer = Math.max(answer, sum);

        }

        return answer;
    }

    //may have duplicae
    static int helper(int[] arr, int k) {
        HashMap<Integer, Integer> mp = new HashMap<>();

        int currentSum = 0, maxSum = 0;
        int n = arr.length, left = 0, i = 0;

        //Iterating for length k
        while (i < k && i < n) {
            currentSum += arr[i];
            if (mp.containsKey(arr[i]))
                mp.put(arr[i], mp.get(arr[i]) + 1);
            else
                mp.put(arr[i], 1);
            i++;
        }

        // If distinct elements present in map
        // equal to k
        if (mp.size() == k) {
            maxSum = currentSum;
        }

        //Iterating over the left array
        for (i = k; i < n; i++) {

            if (mp.containsKey(arr[i]))
                mp.put(arr[i], mp.get(arr[i]) + 1);
            else
                mp.put(arr[i], 1);

            if (mp.containsKey(arr[left])) {
                mp.put(arr[left], mp.get(arr[left]) - 1);
                if (mp.get(arr[left]) <= 0) {
                    mp.remove(arr[left]);
                }

                currentSum += arr[i];
                currentSum -= arr[left];

                if (mp.size() == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }

                left++;
            }

        }

        // Returning the maximum sum
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 3;

        System.out.println(maximumSubarraySum(arr, k));
        System.out.println(helper(arr, k));
    }
}
