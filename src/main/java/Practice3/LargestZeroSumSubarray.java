package Practice3; /**
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
        // Map to store (prefix_sum, index)
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        int maxLength = 0;
        int start = -1, end = -1;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == 0) {
                // Whole array from 0 to i sums to zero
                if (i + 1 > maxLength) {
                    maxLength = i + 1;
                    start = 0;
                    end = i;
                }
            }

            if (sumIndexMap.containsKey(sum)) {
                // Subarray between previous index+1 and i sums to zero
                int prevIndex = sumIndexMap.get(sum);
                if (i - prevIndex > maxLength) {
                    maxLength = i - prevIndex;
                    start = prevIndex + 1;
                    end = i;
                }
            }
            else {
                sumIndexMap.put(sum, i);
            }

        }

        if (start == -1) {
            // No subarray found
            return new int[0];
        }
          /*###########################*/
         // 0 1 2 3                  //
        //  Extract the subarray    //
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = arr[i];
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
