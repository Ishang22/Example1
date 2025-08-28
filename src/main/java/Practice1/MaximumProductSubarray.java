package Practice1;

/**
 * Description:<br>
 * Date: 28/08/25-6:41 pm
 *
 * @author ishangarg
 * @since
 */
public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int leftProduct = 1;
        int rightProduct = 1;
        int ans = nums[0];

        for (int i = 0; i < n; i++) {
            // Reset to 1 if product becomes zero
            leftProduct = (leftProduct == 0) ? 1 : leftProduct;
            rightProduct = (rightProduct == 0) ? 1 : rightProduct;

            // Prefix product
            leftProduct *= nums[i];
            // Suffix product
            rightProduct *= nums[n - 1 - i];

            // Update answer
            ans = Math.max(ans, Math.max(leftProduct, rightProduct));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        int[] nums2 = {-2, 0, -1};
        int[] nums3 = {-2, 3, -4};

        System.out.println("Max product of nums1: " + maxProduct(nums1)); // 6
        System.out.println("Max product of nums2: " + maxProduct(nums2)); // 0
        System.out.println("Max product of nums3: " + maxProduct(nums3)); // 24
    }
}

