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

   static int[] maxLenWithIndex(int A[], int n) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int maxi = 0;
        int sum = 0;
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < n; i++) {
            sum += A[i];

            if (sum == 0) {
                if (i + 1 > maxi) {
                    maxi = i + 1;
                    startIndex = 0;
                    endIndex = i;
                }
            } else {
                if (mpp.containsKey(sum)) {
                    int prevIndex = mpp.get(sum);
                    if ((i - (prevIndex+1))+1 > maxi) {
                        maxi = i - prevIndex;
                        startIndex = prevIndex + 1;
                        endIndex = i;
                    }
                } else {
                    mpp.put(sum, i);
                }
            }
        }

        return new int[]{maxi, startIndex, endIndex}; // length, start, end
    }

    public static void main(String[] args) {
        int[] arr = {9, -3, 3, -1, 6, -5};
        int[] result = maxLenWithIndex(arr,arr.length);

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
