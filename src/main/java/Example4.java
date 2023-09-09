import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//   22.49
//   4.67

class Example4 {
    //  1 -> Red,
    // -1 -> Blue

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        //  1->2,3
        //  2->1,4
        //  3->1,4
        //  4->2,3
        int[] colors = new int[V];

        for (int i = 0; i < V; i++) {

            if (colors[i] == 1 || colors[i] == -1) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            colors[i] = 1;

            while (!queue.isEmpty()) {

                int cur = queue.poll();

                for (Integer next : adj.get(cur)) {

                    if (colors[next] == 0) {
                        colors[next] = -colors[cur];
                        queue.add(next);
                    }

                    if (colors[next] == colors[cur]) return false;

                }

            }
        }


        return true;
    }
}