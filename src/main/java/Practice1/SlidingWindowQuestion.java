package Practice1;

import java.util.HashMap;
import java.util.Map;

// find the maximum sum of a subarray with exactly k distinct elements.
class SlidingWindowQuestion {

    public int minSubArrayLen(int x, int[] arr) {
        int windowStart = 0;
        int windowEnd = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (windowEnd < arr.length) {

            sum += arr[windowEnd];

            while (windowStart <= windowEnd && sum >= x) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                sum -= arr[windowStart];
                windowStart++;
            }

            windowEnd++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int longestOnes(int[] arr, int hopsallowed) {

        int j = 0, i = 0, temHops = hopsallowed, maxLength = 0;
        while (j < arr.length) {

            while (temHops == 0 && i <= j && arr[j] == 0) {
                if (arr[i] == 0) {
                    ++temHops;

                }
                ++i;
            }

            if (arr[j] == 0 && temHops >= 0) {
                --temHops;
            }

            if (j - i + 1 > maxLength) {
                maxLength = j - i + 1;
            }

            j++;

        }

        return maxLength;
    }

    public static long maximumSubarraySumWithDistinctK(int[] arr, int k) {
        int windowStart = 0;
        int windowEnd = 0;
        long sum = 0;
        long maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (windowEnd < arr.length) {
            sum = sum + arr[windowEnd];
            map.put(arr[windowEnd], map.getOrDefault(arr[windowEnd], 0) + 1);

            while (windowStart <= windowEnd && windowEnd - windowStart + 1 > k) {
                map.put(arr[windowStart], map.get(arr[windowStart]) - 1);
                if (map.get(arr[windowStart]) == 0) {
                    map.remove(arr[windowStart]);
                }
                sum -= arr[windowStart];
                windowStart++;
            }

            if (windowEnd - windowStart + 1 == k && map.size() == k) {
                maxLength = Math.max(maxLength, sum);
            }

            windowEnd++;
        }

        return maxLength;
    }

    public int lengthOfLongestSubstringWithoutDuplicates(String s) {
        int windowStart = 0;
        int windowEnd = 0;
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();

        int maxLen = Integer.MIN_VALUE;

        while (windowEnd < n) {
            char ch = s.charAt(windowEnd);

            if (map.containsKey(ch) && map.get(ch) >= windowStart) {
                windowStart = map.get(ch) + 1;
            }

            map.put(ch, windowEnd);
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
            windowEnd++;
        }

        return (maxLen == Integer.MIN_VALUE) ? 0 : maxLen;

    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        //populate the map with t string
        // T-> M
        // S -> N
        //TC -> O(M + 2N)~ O(N+M)
        //SC -> O(M)
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int uniqueCharCount = freqMap.size();
        int startIndex = -1;
        int windowStart = 0;
        int windowEnd = 0;
        int minLen = Integer.MAX_VALUE;
        int N = s.length();
        // O(2N)
        while (windowEnd < N) {
            //Expansion Phase
            char ch = s.charAt(windowEnd);
            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) - 1);
                if (freqMap.get(ch) == 0) {
                    uniqueCharCount--;
                }
            }


            //Shrinking Phase
            while (uniqueCharCount == 0) {
                //find len
                int len = windowEnd - windowStart + 1;

                if (len < minLen) {
                    minLen = len;
                    startIndex = windowStart;
                }

                ch = s.charAt(windowStart);
                if (freqMap.containsKey(ch)) {
                    freqMap.put(ch, freqMap.get(ch) + 1);
                    if (freqMap.get(ch) > 0) {
                        uniqueCharCount++;
                    }
                }

                windowStart++;
            }

            windowEnd++;

        }

        if (startIndex == -1) {
            return "";
        }
        return s.substring(startIndex, startIndex + minLen);
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4};
//        int k = 3;
//        System.out.println(maximumSubarraySumWithDistinctK(arr, k));
//
//        int arr1[] = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
//        longestOnes(arr1, 2);

        String s = "ADOBECODEBANC", t = "ABC";
        // Output: "BANC"
        System.out.println(minWindow(s, t));

    }

}
