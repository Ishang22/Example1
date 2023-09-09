import java.util.Arrays;

// array of positve integer values [10,12,5,7,20,24,35,90,100]
// 20,5,7
// 24,5,7
public class Example9 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4 ,6 ,7};
        //3,6,7  3,4,6     4,6,7

        Arrays.sort(arr);

        int count = 0;

        for (int i = 2; i < arr.length; i++) {

            int j = 0, k = i - 1;
            //arr[j] + arr[k] <= arr[i]
            // arr[j] + arr[k] > arr[i]
            while (j < k) {
                if (arr[j] + arr[k] <= arr[i]) {
                    j++;
                } else {
                    count = count + k - j;
                    k--;
                }
            }

        }
        // checked giving right output = 3 and time complexity is n*n
        System.out.println(count);
    }
}