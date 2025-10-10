package Practice1;//{16, 17, 4, 3, 5, 2,-4}
//17

import java.util.PriorityQueue;

public class LeaderElement {
    // 4,3,2,6  - 7,2,6 - 9,6 - 15
    //cost      - 7    + 9 +15
   // 4,3,2,6  - 4,5,6 - 9,6 - 15
    //cost      - 5  + 9 +15
    //2,3,4,6 - PriorityQueue

    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currmax = nums[0];

        for(int i=1;i<nums.length;i++)
        {
            currmax= Math.max(nums[i],currmax+nums[i]);

            maxSoFar=Math.max(maxSoFar,currmax);
        }

        return maxSoFar;
    }

    public static void calculateCost()
    {
        int[] arr = {4,3,2,6};

        PriorityQueue<Integer> q1 = new PriorityQueue<>();

        for (int j : arr) {
            q1.add(j);
        }

        int totalCost=0;
        int stepCost=0;

        while(q1.size()>1)
        {
            int e1= q1.poll();
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
        //MAJORITY ELEMENT
        //https://www.youtube.com/watch?v=X0G5jEcvroo&t=1334s


        // LEADERS ELEMENT -
        //https://www.youtube.com/watch?v=tzV8yPmtw1A&t=1246s

        int[] arr = {16, 17, 4, 3, 5, 2, -4};
        int leader = Integer.MIN_VALUE;

        for (int i = arr.length-1; i >= 0; i--) {
            if(arr[i]>leader)
            {
                leader=arr[i];
                System.out.println("Leader Element :"+leader);
            }
        }

//  https://www.youtube.com/watch?v=e1HlptlipB0&t=1468s
        calculateCost();
    }
}
