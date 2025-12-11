package Practice1.Arrays;

/**
 * Description:<br>
 * Date: 27/08/25-11:16 pm
 *
 * @author ishangarg
 * @since
 */

public class MajorityElement {
    public static int majorityElement(int[] a) {
        int ansIndex = 0;
        int count = 1;

        for (int i = 1; i < a.length; i++) {

            if (a[i] == a[ansIndex]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                ansIndex = i;
                count = 1;
            }
        }

        // Optional: verify that a[ansIndex] is actually majority
        int candidate = a[ansIndex];
        count = 0;
        for (int num : a) {
            if (num == candidate) {
                count++;
            }
        }
        if (count > a.length / 2) {
            return candidate;
        }

        // If no majority element exists
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 3, 5, 1};
        System.out.println("Majority Element: " + majorityElement(arr));
    }
}
