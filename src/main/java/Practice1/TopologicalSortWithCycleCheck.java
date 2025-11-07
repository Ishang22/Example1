package Practice1;
import java.util.*;

public class TopologicalSortWithCycleCheck {

    // DFS to detect cycle
    public static boolean isCycleDFS(int src, boolean[] vis, boolean[] recPath, List<int[]> edges) {
        vis[src] = true;
        recPath[src] = true;

        for (int[] edge : edges) {
            int v = edge[0];
            int u = edge[1];

            if (src == u) {
                if (!vis[v]) {
                    if (isCycleDFS(v, vis, recPath, edges))
                        return true;
                } else if (recPath[v]) { // Back edge found
                    return true;
                }
            }
        }

        recPath[src] = false;
        return false;
    }

    // DFS for topological ordering
    public static void topoOrder(int src, boolean[] vis, Stack<Integer> s, List<int[]> edges) {
        vis[src] = true;

        for (int[] edge : edges) {
            int v = edge[0];
            int u = edge[1];

            if (src == u && !vis[v]) {
                topoOrder(v, vis, s, edges);
            }
        }

        s.push(src);
    }

    // Function to get topological sort (with cycle check)
    public static List<Integer> topologicalSort(int n, List<int[]> edges) {
        boolean[] vis = new boolean[n];
        boolean[] recPath = new boolean[n];
        List<Integer> ans = new ArrayList<>();

        // Step 1: Check for cycle
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                if (isCycleDFS(i, vis, recPath, edges)) {
                    return ans; // return empty if cycle found
                }
            }
        }

        // Step 2: Topological sort
        Stack<Integer> s = new Stack<>();
        Arrays.fill(vis, false);

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topoOrder(i, vis, s, edges);
            }
        }

        while (!s.isEmpty()) {
            ans.add(s.pop());
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{5, 2});
        edges.add(new int[]{5, 0});
        edges.add(new int[]{4, 0});
        edges.add(new int[]{4, 1});
        edges.add(new int[]{2, 3});
        edges.add(new int[]{3, 1});

        List<Integer> topoSort = topologicalSort(n, edges);
        if (topoSort.isEmpty()) {
            System.out.println("Cycle detected! No topological order possible.");
        } else {
            System.out.println("Topological Sort: " + topoSort);
        }
    }
}
