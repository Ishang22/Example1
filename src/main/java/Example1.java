import java.util.*;

// array of positive integer values [10,12,5,7,20,24,35,90,100]
// 20,5,7
// 24,5,7
//find minimum time required to complete n trips cab can operate parallely

class Example1 {
    static int solution(int[][] coordinates) {
        coordinates = merge(coordinates);
        int sum = 0;
        for (int[] coordinate : coordinates) {
            sum += (coordinate[1] - coordinate[0] + 1);
        }

        return sum;
    }

    static public int[][] merge(int[][] intervals) {
//{{4, 7}, {-1, 5}, {3, 6}};
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
//      {{-1, 5}, {3, 6},{4,7}}
        LinkedList<int[]> merged = new LinkedList<>();

        for (int[] interval : intervals) {
            //                          3                   5
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = interval[1];
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }


    public static void main(String args[]) {
        int inv[][] = {{4, 7}, {-1, 5}, {3, 6}};

        System.out.println("===========$$$$$$$$$=========" + Example1.solution(inv));
    }
}
