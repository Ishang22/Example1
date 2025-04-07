package Practice1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
//https://www.youtube.com/watch?v=PiGYS7BbV_Q

//Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//        Output: [[1,2],[1,4],[1,6]]
//        Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

class Solution5656 {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> ans = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);   // sorted in ascending order on sum

        Set<String> set = new HashSet<>(); // HashSet will help to check if indexs is visited or not.

        int[] tem = new int[3];

        int sum = nums1[0] + nums2[0];

        tem[0] = sum;     // sum of i index for nums1 & j index from nums2.
        tem[1] = 0;      // tem[1] will alway store the index of nums1 element
        tem[2] = 0;     // tem[2] will alway store the index of nums2 element

        set.add("0$0"); // In set we will stor the i th index and j th index like this "i" + "&" + "j".

        pq.add(tem); // storing tem array in priority queue

        while (pq.size() > 0 && k > 0) {
            int[] tem2 = pq.poll(); // taking out minimum sum entry from priority queue

            int data = tem2[0];
            int i = tem2[1];
            int j = tem2[2];

            List<Integer> ls = new ArrayList<>();

            ls.add(nums1[i]);   // adding minimum removed sum operands of i th index from nums1
            ls.add(nums2[j]);   // adding minimum removed sum operands of j th index from nums2

            ans.add(ls);

            String s1 = i + "$" + (j + 1);
            String s2 = (i + 1) + "$" + j;

            if (j + 1 < nums2.length && set.contains(s1) == false) {
                int[] tem1 = new int[3];

                sum = nums1[i] + nums2[j + 1];

                tem1[0] = sum;
                tem1[1] = i;
                tem1[2] = j + 1;

                pq.add(tem1);
                set.add(s1);
            }

            if (i + 1 < nums1.length && set.contains(s2) == false) {
                int[] tem1 = new int[3];

                sum = nums1[i + 1] + nums2[j];

                tem1[0] = sum;
                tem1[1] = i + 1;
                tem1[2] = j;

                pq.add(tem1);

                set.add(s2);
            }

            k--;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};

        List<List<Integer>> kPairs = kSmallestPairs(nums1, nums2, 3);
//=kPairs==[[1, 2], [1, 4], [1, 6]]
        System.out.println("=kPairs==" + kPairs);

    }
}

// sort the data in ascending order based on sum of nums1[i] + nums2[j]
//min heap
//class customComparator implements Comparator<int[]> {
//    public int compare(int[] a, int[] b) {
//        return a[0] - b[0];
//    }
//}



