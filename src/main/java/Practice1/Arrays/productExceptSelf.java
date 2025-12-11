package Practice1.Arrays;

/**
 * Description:<br>
 * Date: 28/08/25-8:22 pm
 *
 * @author ishangarg
 * @since
 */
public class productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // Arrays to store prefix (left) and suffix (right) products
        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n];
        //1 2 3 4 5
        // Left product
        left[0] = 1;

        //1     1    2     6  24
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        //1    2     3    4  5
        // Right product
        right[n - 1] = 1;
        //120  60   20   5   1
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        // Final result: multiply left and right
        for (int i = 0; i < n; i++) {
            ans[i] = left[i] * right[i];
        }

        return ans;
    }
}
