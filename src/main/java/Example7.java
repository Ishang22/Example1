//[10:23 am] Ravi
//{34, 8, 10, 3, 2, 80, 30, 33, 1}
//
//[10:24 am] Ravi
// hi = 7
// li = 1

// diffINdex =  1
// minIndex =  4
// (arr[i]-min)>=0
// diffINdex = max(diffINdex,i-minIndex)

// {34, 8, 10, 3, 2, 80, 30, 33, 1}

// n
//34 ->0
//8->1
//10->2
//3->3
//2->4
//80->5
//30->6
//33->7
//1->8
//        4,
//
//        nlong
//        {1,2,3,8,10,30,33,34,80}
//        3,8,10,34             1,2,30,33,80
//        34, 8, 10, 3         2, 80, 30, 33, 1
//
//                8,34   3,10        2, 80    30, 33

// [[[[1,2,3],4,5],[6,7],8,[9,10,[11,12]]]]


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example7 {

    public static List<Integer> flatten(Object[] inputArray) throws IllegalArgumentException {

        if (inputArray == null) return null;

        List<Integer> flatList = new ArrayList<>();

        for (Object element : inputArray) {
            if (element instanceof Integer) {
                flatList.add((Integer) element);
            } else if (element instanceof Object[]) {
                flatList.addAll(flatten((Object[]) element));
            } else {
                throw new IllegalArgumentException("Input must be an array of Integers or nested arrays of Integers");
            }
            System.out.println("Flattened Array " + flatList);
        }

        return flatList;
    }

    static int maxIndexDiff(int[] arr, int n) {

        int[] leftMin = new int[n];
        leftMin[0] = arr[0];

        for (int i = 1; i < n; i++)
            leftMin[i] = Math.min(leftMin[i - 1], arr[i]);

           //   leftMin[i] = min{ arr[i...(n-1] }
         //     [34, 8, 8,  3, 2, 2,  2,  2,  1]  - i - left
         //     [34, 8, 10, 3, 2, 80, 30, 33, 1]  - j - arr

        int maxDist = Integer.MIN_VALUE;

        int i = n - 1, j = n - 1;

        while (i >= 0 && j >= 0) {
            if (arr[j] >= leftMin[i]) {
                maxDist = Math.max(maxDist, j - i);
                i--;
            } else {
                j--;
            }
        }

        return maxDist;
    }

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<Integer>();

        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        arr.add(50);

//        number.add(2);
        for (Integer integer : arr) {
            System.out.println("=======" + integer);
            arr.add(2);
        }

//        Integer[][] arr = {
//                {1, 2},
//                {3, 4, 5, 6},
//                {7, 8, 9}
//        };
//        List<Integer> arr1 = flatten(arr);
//        System.out.println(arr1);
//
//
//        ////////////////////////////////////////////////////////////////////////////////////////////////
//        int[] arr11 = {34, 8, 10, 3, 2, 80, 30, 33, 1};
//        int n = arr11.length;
//        int maxDiff = maxIndexDiff(arr11, n);
//        System.out.print(maxDiff);
    }
}
