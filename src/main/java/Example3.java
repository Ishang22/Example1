public class Example3 {
//    Input: time = [1,2,3], totalTrips = 5
//    Output: 3
//    Explanation:
//            - At time t = 1, the number of trips completed by each bus are [1,0,0].
//    The total number of trips completed is 1 + 0 + 0 = 1.
//            - At time t = 2, the number of trips completed by each bus are [2,1,0].
//    The total number of trips completed is 2 + 1 + 0 = 3.
//            - At time t = 3, the number of trips completed by each bus are [3,1,1].
//    The total number of trips completed is 3 + 1 + 1 = 5.
//    So the minimum time needed for all buses to complete at least 5 trips is 3.

    int getMin(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < result) {
                result = arr[i];
            }
        }
        return result;
    }

    // Return number of trips that can be completed in t minutes
    int findItems(int[] arr, int t) {
      //  int n = arr.length;
        int result = 0;

        for (int j : arr) {
            result = result + (t / j);
        }

        return result;
    }

    int solution(int n, int[] cabTripTime) {
        // Finding the cab from the array which takes minimum time
        int minTimeCab = getMin(cabTripTime);


        //Binary search to find minimum time required to complete n trips
        int low = 1;
        int high = minTimeCab * n;

        while (low < high) {
            int mid = (low + high) / 2;

            // compute number of trips to completed in mid minutes
            int item = findItems(cabTripTime, mid);

            // if trips produced is less than n, set low to mid+1
            // else high to mid
            if (item < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        Example3 dictionary = new Example3();
        System.out.println(dictionary.solution(10, new int[]{1, 3, 5, 7, 8}));
    }

}
