package Practice1;

import java.util.ArrayList;

//https://www.youtube.com/watch?v=u6-vT50sgrs

public class reversePairs {
    // 0  1  2                       3    4
    // 2  3  4    -----GAP-----      1   5
    static int merge(int[] nums, int low, int mid, int high) {
        int cnt = 0;
        int j = mid + 1;

        int l = low;

        while (l <= mid && j <= high) {
            if (nums[l] > 2 * nums[j]) {
                cnt += mid - l + 1;
                j++;
            } else {
                l++;
            }
        }

        ArrayList<Integer> temp = new ArrayList<>();

        int left = low, right = mid + 1;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp.add(nums[left++]);
            } else {
                temp.add(nums[right++]);
            }
        }

        while (left <= mid) {
            temp.add(nums[left++]);
        }

        while (right <= high) {
            temp.add(nums[right++]);
        }

        for (int i = low; i <= high; i++) {
            nums[i] = temp.get(i - low);
        }

        return cnt;
    }

    static int mergeSort(int[] nums, int low, int high)  {
        System.out.println("===  main  " + low + "   " + high);
        if (low >= high) return 0;

        int mid = (low + high) / 2;
        System.out.println("===  mid  " + "   " + mid);

        int inv = mergeSort(nums, low, mid);

        //      System.out.println("===  right  " + (mid + 1) + "   " + high);
        inv += mergeSort(nums, mid + 1, high);

        //  System.out.println("===  merging tym  " + low + "   " + mid + "    " + high);
        inv += merge(nums, low, mid, high);

        return inv;
    }

    static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {

        int[] arr = {2, 4, 3, 5, 1};

        //  System.out.println("LENGTH ------" + arr.length);
        System.out.println("The Total Reverse Pairs are ***  " + reversePairs(arr));

        for (int i = 0; i < arr.length; i++) {
            //     System.out.println("((000     LENGTH ------" + arr[i]);
        }
    }
}

//            0 1 2 3 4 (mid 2)

//  0_1_2 (mid 1)                              3_4 (mid 3)

// 01 (mid 0) , 22 (return)                  // 33 (return)  ,  44(return)

// 00 (return), 11(return)

//🔹 Class names (such as StudentDetails, PaymentService, and OrderController) should be in PascalCase.
//🔹 Interface names ➔ (such as PaymentGateway, DatabaseConnector) should also be in PascalCase.
//🔹 Method Names ➔ (processPayment(), calculateSalary()) Should be camelCase.
//🔹 Variable Names ➔ (userName, orderId, paymentStatus) Should be camelCase
//🔹 Constants/ENUM ➔ (MAX_RETRY_COUNT, DEFAULT_TIMEOUT_MS) Should be UPPER_SNAKE_CASE
//package name should be all small