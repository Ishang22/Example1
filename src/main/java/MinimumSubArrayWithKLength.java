import java.util.HashMap;

public class MinimumSubArrayWithKLength {

    private static int findLengthOfSmallestSubarray(int[] a, int K) {
        int n = a.length;

        int lengthOfSmallestSubarray = Integer.MAX_VALUE;

        HashMap<Integer, Integer> mp = new HashMap<>();

        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {

            mp.put(a[windowEnd], mp.getOrDefault(a[windowEnd], 0) + 1);

            while (mp.size() >= K) {
                if (mp.size() == K) {
                    lengthOfSmallestSubarray = Math.min(lengthOfSmallestSubarray, windowEnd - windowStart + 1);
                }

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

    public static void main(String[] args) {
        int[] arr = {8, 8, 1, 7, 7, 8, 9, 9, 2, 3, 3, 322, 322, 41};
        int k = 4;
        //  System.out.println(smallestSubarray(arr, k));

        System.out.println(findLengthOfSmallestSubarray(arr, k));
    }
}
