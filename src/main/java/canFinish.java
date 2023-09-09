import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class ScheduleCourse {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //Array Of LinkedList
        List<Integer>[] graph = new ArrayList[numCourses];

        int[] degree = new int[numCourses];

        for (int[] edge : prerequisites) {

            if (graph[edge[1]] == null) {
                // store things that are dependent on 1 to execute.....
                graph[edge[1]] = new ArrayList<>();
            }

             //  first we have to complete course 1 then 0 , means 1 is dependent on 0
            //   v[0] <----- v[1]

            degree[edge[0]]++;

            // storing childrens .....
            graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++)
            if (degree[i] == 0)
                q.add(i);

        while (!q.isEmpty()) {
            Integer e = q.poll();
            numCourses--;

            //means iske baad kisi ko complete krne ki jrurat nahi .................
            if (graph[e] == null)
                continue;

            for (int i : graph[e])
                if (--degree[i] == 0)
                    q.add(i);
        }


        return numCourses == 0;
    }
}