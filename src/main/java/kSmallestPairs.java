import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class kSmallestPairs {
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> kPairs = new ArrayList<>();

        int len1 = nums1.length, len2 = nums2.length;

        if (len1 == 0 || len2 == 0)
            return kPairs;

        // minimum Heap ........... bitch
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]])
        );

        minHeap.add(new int[]{0, 0});

        while (k-- > 0 && !minHeap.isEmpty()) {

            int[] pair = minHeap.poll();

            kPairs.add(new int[]{nums1[pair[0]], nums2[pair[1]]});

            if (pair[0] + 1 < len1)
                minHeap.add(new int[]{pair[0] + 1, pair[1]});


            if (pair[0] == 0 && pair[1] + 1 < len2)
                minHeap.add(new int[]{pair[0], pair[1] + 1});

        }

        return kPairs;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};

        List<int[]> kPairs = kSmallestPairs(nums1, nums2, 3);

        for (int[] kPair : kPairs) System.out.println("==   " + kPair[0] + "==*==" + kPair[1]);
    }

}