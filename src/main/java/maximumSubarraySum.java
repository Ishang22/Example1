import java.util.HashMap;

public class maximumSubarraySum {


    public long maximumSubArraySum(int[] a, int target) {
        int n = a.length;

        int lengthOfSmallestSubarray = Integer.MIN_VALUE;

        HashMap<Integer, Integer> mp = new HashMap<>();
        int sum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {

            mp.put(a[windowEnd], mp.getOrDefault(a[windowEnd], 0) + 1);
            sum = sum + a[windowEnd];

            while (sum >= target) {

                if (mp.size() == target) {
                    lengthOfSmallestSubarray = Math.max(lengthOfSmallestSubarray, windowEnd - windowStart + 1);
                }

                sum = sum - a[windowStart];

                if (mp.containsKey(a[windowStart]) && mp.get(a[windowStart]) == 1) {
                    mp.remove(a[windowStart]);
                }

                if (mp.containsKey(a[windowStart]) && mp.get(a[windowStart]) > 1) {
                    mp.put(a[windowStart], mp.get(a[windowStart]) - 1);
                }

                windowStart++;


            }


        }

        return lengthOfSmallestSubarray == Integer.MAX_VALUE ? 0 : lengthOfSmallestSubarray;
    }
}
