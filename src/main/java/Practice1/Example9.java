package Practice1;

import java.util.Arrays;

// array of positive integer values [10,12,5,7,20,24,35,90,100]
// 20,5,7
// 24,5,7
//https://www.youtube.com/watch?v=QAqfQwJH2w4

//This algorithm solves the problem of counting the number of triangles that can be formed from a given array of side lengths. According to the triangle inequality theorem,
// for any triangle, the sum of the lengths of any two sides must be greater than the length of the remaining side.

public class Example9 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 6, 7};
        //3,6,7  3,4,6     4,6,7

        Arrays.sort(arr);

        int count = 0;

        for (int i = 2; i < arr.length; i++) {
            int j = 0, k = i - 1;
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