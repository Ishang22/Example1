//{16, 17, 4, 3, 5, 2,-4}
//17

import java.util.PriorityQueue;

public class LeaderElement {
    // 4,3,2,6  - 7,2,6 - 9,6 - 15
    //cost      - 7    + 9 +15
   // 4,3,2,6  - 4,5,6 - 9,6 - 15
    //cost      - 5  + 9 +15
    //2,3,4,6 - PriorityQueue

    public static void calculateCost()
    {
        int[] arr = new int[]{4,3,2,6};
        PriorityQueue<Integer> q1 = new PriorityQueue<>();

        for (int j : arr) {
            q1.add(j);
        }
        int totalCost=0;
        int stepCost=0;
        while(q1.size()>=2)
        {
            int e1=q1.poll();
            int e2 = q1.poll();
            System.out.println("e1 and e2   "+e1+"   "+e2);
            stepCost=e1+e2;
            totalCost=stepCost+totalCost;
            System.out.println("totalCost   stepCost "+stepCost);
            q1.add(stepCost);
        }

        System.out.println("result     "+totalCost);


    }

    public static void main(String[] args) {
//        int[] arr = new int[]{16, 17, 4, 3, 5, 2, -4};
//        int leader = Integer.MIN_VALUE;
//
//        for (int i = arr.length-1; i >= 0; i--) {
//            if(arr[i]>leader)
//            {
//                leader=arr[i];
//                System.out.println("Leader Element :"+leader);
//            }
//        }
        calculateCost();
    }
}
