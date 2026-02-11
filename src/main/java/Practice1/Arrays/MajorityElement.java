package Practice1.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:<br>
 * Date: 27/08/25-11:16 pm
 *
 * @author ishangarg
 * @since
 */

public class MajorityElement {
    /*
    ⏱ Complexity
                Time: O(n)
                Space: O(1)
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {


            if (num == candidate) {
                count++;
            } else  if (count == 0) {
                candidate = num;
                count=1;
            } else {
                count--;
            }
        }

        return candidate;
    }

   /*
   Time: O(n)

  Space: O(1) (output list excluded)
 */
    public List<Integer> majorityElement2(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer cand1 = null, cand2 = null;

        // Phase 1: Find candidates
        for (int num : nums) {
            if (cand1 != null && num == cand1) {
                count1++;
            } else if (cand2 != null && num == cand2) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Phase 2: Verify candidates
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        if (count1 > n / 3) result.add(cand1);
        if (count2 > n / 3) result.add(cand2);

        return result;
    }
}
