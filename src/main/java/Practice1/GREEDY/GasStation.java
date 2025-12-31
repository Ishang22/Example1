package Practice1.GREEDY;

import java.util.Collections;
import java.util.PriorityQueue;
/*
 A car travels from start (0) to target (X) miles away. It starts with startFuel liters of fuel.
 You are given a list of gas stations [pos, fuel] where pos is the distance from the start and fuel is
 how much fuel the station gives.
 Return the minimum number of refueling stops to reach the destination. If it cannot reach, return -1.
 Example:
 Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]


Output: 2
 */

public class GasStation {

    //⛽ LeetCode 134. Gas Station  https://www.youtube.com/watch?v=fOaUh1_fJPw&t=441s
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;

        // Step 1: Calculate total gas and total cost
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        // If overall gas < cost → impossible to complete
        if (totalGas < totalCost) {
            return -1;
        }

        // Step 2: Greedy check for valid start index
        int currentGas = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            currentGas += gas[i] - cost[i];

            // If gas goes negative → cannot start from previous startIndex
            if (currentGas < 0) {
                startIndex = i + 1; // Move start to next station
                currentGas = 0;      // Reset current gas
            }
        }

        return startIndex;
    }

    //🚗 LeetCode 871. Minimum Number of Refueling Stops https://www.youtube.com/watch?v=sKjKLN5JswQ&t=3s
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {

      /*  //      [pos, fuel]
        int[][] stations = {
                {10, 60},
                {20, 30},
                {30, 30},
                {60, 40}
        };
    */

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int stops = 0;
        long reachable = startFuel;
        int i = 0;


        while (reachable < target) {

            while (i < stations.length && stations[i][0] <= reachable) {
                maxHeap.offer(stations[i][1]);
                i++;
            }


            if (maxHeap.isEmpty()) return -1;

            reachable += maxHeap.poll();
            stops++;
        }

        return stops;
    }

    // Example runner
    public static void main(String[] args) {
        // Example from problem
        int target = 100;
        int startFuel = 10;
        //      [pos, fuel]
        int[][] stations = {
                {10, 60},
                {20, 30},
                {30, 30},
                {60, 40}
        };

        int result = minRefuelStops(target, startFuel, stations);
        System.out.println("Minimum stops: " + result); // expected: 2
    }
}
