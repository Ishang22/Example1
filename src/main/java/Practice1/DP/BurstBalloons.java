package Practice1.DP;

import java.util.Arrays;

public class BurstBalloons {

    public int findMinArrowShots(int[][] points) {

        if (points == null || points.length == 0) return 0;

        // Sort by starting point
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int count = 1; // at least one arrow
        int[] prev = points[0];

        for (int i = 1; i < points.length; i++) {

            int currStartPoint = points[i][0];
            int currEndPoint = points[i][1];

            int prevStartPoint = prev[0];
            int prevEndPoint = prev[1];

            // No overlap
            if (currStartPoint > prevEndPoint) {
                count++;
                prev = points[i];
            }
            // Overlap exists
            else {
                // Take overlapping region
                prev[0] = Math.max(prevStartPoint, currStartPoint);
                prev[1] = Math.min(prevEndPoint, currEndPoint);
            }
        }

        return count;
    }
}
