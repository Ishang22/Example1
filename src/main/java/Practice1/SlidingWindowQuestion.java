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
        int len = 0;

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

    static int maximum_sum_of_distinct_subarrays_with_length_k(int[] arr, int k) {
        int n = arr.length;
        int windowStart = 0;
        int windowEnd = 0;
        int len = 0;
        int maxLength = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        while (windowEnd < n) {
            map.put(arr[windowStart], map.getOrDefault(windowStart, 0) + 1);

            if (map.size() > k) {
                while (map.size() > k && windowStart < windowEnd) {
                    if (map.containsKey(windowStart)) {
                        if (map.get(windowStart) == 0) {
                            map.remove(windowStart);
                        } else {
                            map.put(windowStart, map.get(windowStart) - 1);
                        }
                    }
                    windowStart++;
                }
            }

            if (map.size() == k) {
                len = windowEnd - windowStart + 1;
                maxLength = Math.max(maxLength, len);
            }

            windowEnd++;
        }

        return maxLength == Integer.MIN_VALUE ? -1 : maxLength;

    }

    public int lengthOfLongestSubstringwithoutDuplicates(String s) {
        int windowStart = 0;
        int windowEnd = 0;
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();

        int maxLen = Integer.MIN_VALUE;

        while (windowEnd < n) {
            char ch = s.charAt(windowStart);

            if (map.containsKey(ch) && map.get(ch) >= windowStart) {
                windowStart = map.get(ch) + 1;
            }

            map.put(ch, windowEnd);
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
            windowEnd++;
        }

        return (maxLen == Integer.MIN_VALUE) ? 0 : maxLen;

    }

    public String minWindow(String s, String t) {
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
        int[] arr = {1, 2, 3, 4};
        int k = 3;
        System.out.println(maximum_sum_of_distinct_subarrays_with_length_k(arr, k));

        int arr1[] = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        longestOnes(arr1, 2);

    }

}
