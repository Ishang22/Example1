package Practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumProblem {
    public static List<List<Integer>> threeSum(int[] arr) {

        if (arr == null || arr.length < 3) return new ArrayList<>();

        Arrays.sort(arr);

        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < arr.length - 2; i++) {

            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }

            }

        }
        return new ArrayList<>(result);
    }

    public int[] twoSum(int[] num, int sum) {

        Map<Integer, Integer> numMap = new HashMap<>();
        int n = num.length;

        // Build the hash table
        for (int i = 0; i < n; i++) {
            numMap.put(num[i], i);
        }

        // Find the complement
        for (int i = 0; i < n; i++) {
            int complement = sum - num[i];
            if (numMap.containsKey(complement) && numMap.get(complement) != i) {
                return new int[]{i, numMap.get(complement)};
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        List<List<Integer>> list1 = threeSum(new int[]{-4, -1, -1, 0, 1, 2});

        for (List<Integer> list2 :
                list1) {
            System.out.println(list2);
        }
    }
}
