package Practice1; /**
 * Description:<br>
 * Date: 26/04/25-12:15 pm
 *
 * @author ishangarg
 * @since
 */
import java.util.HashMap;
import java.util.Map;

public class LargestZeroSumSubarray {
    public static int[] findLargestZeroSumSubarray(int[] arr) {
        // Map to store (prefix_sum -> earliest index)
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();

        int maxLength = 0;
        int start = -1;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            // Case 1: Subarray from index 0 to i has zero sum
            if (sum == 0) {
                if (i + 1 > maxLength) {
                    maxLength = i -0 + 1;
                    start = 0;
                }
            }

            // Case 2: If sum seen before, subarray in between has zero sum
            if (prefixSumIndex.containsKey(sum)) {
                int prevIndex = prefixSumIndex.get(sum);
                if (i - prevIndex > maxLength) {
                    maxLength = i - prevIndex;
                    start = prevIndex + 1;
                }
            } else {
                // Store first occurrence of this prefix sum
                prefixSumIndex.put(sum, i);
            }
        }

        // No such subarray found
        if (start == -1) return new int[0];

        // Construct result
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = arr[start + i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9, -3, 3, -1, 6, -5};
        int[] result = findLargestZeroSumSubarray(arr);

        if (result.length == 0) {
            System.out.println("No zero-sum subarray found.");
        } else {
            System.out.print("Largest zero-sum subarray: ");
            for (int num : result) {
                System.out.print(num + " ");
            }
        }
    }
}
