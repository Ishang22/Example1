package Practice1;

import java.util.HashMap;

// find the maximum sum of a subarray with exactly k distinct elements.
class MinimumSubArrayWithKLengthq {
    //  https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/solutions/2784440/simple-java-solution-sliding-window-tc-o-n-sc-o-n/
    // With Distinct

    //may have duplicae
    static int helper(int[] arr, int k) {
        HashMap<Integer, Integer> mp = new HashMap<>();

        int currentSum = 0, maxSum = 0;
        int n = arr.length, left = 0, i = 0;

        //Iterating for length k
        while (i < k && i < n) {
            currentSum += arr[i];
            if (mp.containsKey(arr[i])){
                mp.put(arr[i], mp.get(arr[i]) + 1);
            }
            else{
                mp.put(arr[i], 1);
            }
            i++;
        }

        // If distinct elements present in map
        // equal to k
        if (mp.size() == k) {
            maxSum = currentSum;
        }

        //Iterating over the left array
        for (i = k; i < n; i++) {

            if (mp.containsKey(arr[i])) {
                mp.put(arr[i], mp.get(arr[i]) + 1);
            }
            else {
                mp.put(arr[i], 1);
            }

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

        // Returning the maximum sum
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 3;


        System.out.println(helper(arr, k));
    }
}
