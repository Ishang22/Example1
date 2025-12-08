package Practice1;
/*
Mojo loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Mojo can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Mojo likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.



Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4[minimum bnana consune in one hour]

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

[10, 10, 10, 10], h = 50
The Issue:
Your answer: k = 10 (eats fast, finishes in 4 hours)
Correct answer: k = 1 (eats slowly, finishes in 40 hours)

 */
public class AllocateMinimumNumber {

    static boolean isPossible(int[] arr, int n, int m, int curr_min) {

        int studentsUsed = 1;
        int curr_sum = 0;

        for (int i = 0; i < n; i++) {
            curr_sum += arr[i];

            if (curr_sum > curr_min) {
                studentsUsed++;
                curr_sum = arr[i];
            }

        }

        return studentsUsed <= m;
    }

    // method to find minimum pages
    static int findPages(int[] arr, int n, int m) {
        int sum = 0;
        //n - no of books

        // return -1 if no. of books is less than no. of students sabi students ku ik book tu assign huni chahie
        if (n < m)
            return -1;

        // Count total number of pages
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // initialize start as arr[n-1] pages(minimum answer
        // possible) and end as total pages(maximum answer
        // possible)

        int start = arr[n - 1], end = sum;
        int result = Integer.MAX_VALUE;

        // traverse until start <= end
        while (start <= end) {
            // check if it is possible to distribute
            // books by using mid is current minimum
            int mid = start + (end - start) / 2;
            //  n - no. of books
            //. m - no  of students
            if (isPossible(arr, n, m, mid)) {
                // update result to current distribution
                // as it's the best we have found till now.
                result = mid;
                // as we are finding minimum so,
                end = mid - 1;
            } else {
                // if not possible, means pages should be
                // increased ,so update start = mid + 1
                start = mid + 1;
            }
        }

        // at-last return minimum no. of  pages
        return result;
    }

    // Driver Method
    public static void main(String[] args) {

//        int[] arr = {12, 34, 67, 90};       // Number of pages in books
//
//        int m = 2;                          // No. of students
//
//        System.out.println("Minimum number of pages = " + findPages(arr, arr.length, m));

        monkeyBanana();
    }


    public static void monkeyBanana() {
        int arr[] = new int[]{3,6,7,11};
        int h = 8;

        int minBananas = 1;  // Minimum possible speed
        int maxBananas = 0;

        for (int i = 0; i < arr.length; i++) {
            maxBananas = Math.max(maxBananas, arr[i]);  // Find MAX, not sum
        }

        int result = 0;
        while (minBananas <= maxBananas) {
            int mid = (minBananas + maxBananas) / 2;

            if (deciderFunction(mid, h, arr)) {
                result = mid;
                maxBananas = mid - 1;
            } else {
                minBananas = mid + 1;
            }
        }

        System.out.println("result  " + result);
    }

    public static boolean deciderFunction(int k, int numberOfHours, int arr[]) {
        int hoursConsumed = 0;

        for (int i = 0; i < arr.length; i++) {
            int remaining = arr[i];
            while (remaining > 0) {
                remaining -= k;
                hoursConsumed++;
            }
        }

        return hoursConsumed <= numberOfHours;
    }

}
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//   Continuous Integration -   Building the code and test , packaging it into jar/war file  whenever new commits are pushed into the branch.
//
//   Continuous Delivery -     is Continuous Integration + Deploy application to production by "clicking on a button" (Release to customers is often, but on demand).
//
//   Continuous Deployment -   is Continuous Delivery but without human intervention (Release to customers is on-going).
//

/*

CI   → Build & Test Automatically
CD   → Build, Test & Deploy On Demand
CD  → Build, Test & Deploy Automatically

           ┌──────────────────────────────────────────┐
           │              Feature Branches             │
           │  (feature/login, bugfix/payment, etc.)    │
           └──────────────────────────────────────────┘
                          │
                          ▼
                   Continuous Integration
                  (Build + Test + Quality)
                          │
            ┌─────────────┴─────────────┐
            │                           │
            ▼                           ▼
   Pull Request to main/master   More commits on branch
            │
            ▼
   CI runs again on the PR
            │
            ▼
 Merge into main/master branch
            │
            ▼
  ┌────────────────────────────────────────────────────┐
  │                  Main/Master Branch                 │
  └────────────────────────────────────────────────────┘
            │
            ▼
   Continuous Delivery (manual deploy click)
            or
   Continuous Deployment (automatic deploy)
            ▼
       Production Environment

 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
