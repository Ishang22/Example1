package Practice1;

/**
 * Description:<br>
 * Date: 27/08/25-10:39 pm
 *
 * @author ishangarg
 * @since
 */
import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] arr = {2,2,2,2,0,0,0,1,1,1,2,0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sortColors(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {

            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else { // arr[mid] == 2
                swap(arr, mid, high);
                high--;
            }

        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}