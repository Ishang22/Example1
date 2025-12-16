package Practice1.GREEDY;
import java.util.*;

public class TaskSchedular {
    public int leastInterval(char[] tasks, int n) {

        // Step 1: Frequency count
        int[] freq = new int[26];
        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        // Step 2: Max heap
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        int time = 0;

        // Step 3: Process tasks
        while (!pq.isEmpty()) {

            List<Integer> temp = new ArrayList<>();

            // Try to execute n + 1 tasks
            for (int i = 0; i <= n; i++) {
                if (!pq.isEmpty()) {
                    int count = pq.poll();
                    count--;
                    temp.add(count);
                }
            }

            // Push remaining counts back
            for (int cnt : temp) {
                if (cnt > 0) {
                    pq.offer(cnt);
                }
            }

            // Step 4: Time calculation
            if (pq.isEmpty()) {
                time += temp.size();   // no idle needed
            } else {
                time += (n + 1);       // include idle slots
            }
        }

        return time;
    }
}
