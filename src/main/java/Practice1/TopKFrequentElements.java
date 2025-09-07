package Practice1;

/**
 * Description:<br>
 * Date: 31/08/25-1:15 pm
 *
 * @author ishangarg
 * @since
 */
import java.util.*;
/*
int[] arr = new int[5]; → an array of ints.

String[] arr = new String[5]; → an array of Strings.

List<Integer>[] bucket = new List[5];

 List<List<Integer>> ans = new ArrayList<>();
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Build frequency map


        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Step 2: Create bucket list (index = frequency)
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // Step 3: Gather results from highest frequency
        int[] res = new int[k];
        int counter = 0;

        for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
            if (bucket[pos] != null) {
                for (int val : bucket[pos]) {
                    res[counter++] = val;
                    if (counter == k) break;
                }
            }
        }

        return res;
    }

    // Testing
    public static void main(String[] args) {
        TopKFrequentElements solver = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(solver.topKFrequent(nums, k)));
        // Output: [1, 2] (since 1 appears 3 times, 2 appears 2 times)
    }
}

