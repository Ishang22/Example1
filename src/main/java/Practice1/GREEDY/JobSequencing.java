package Practice1.GREEDY;

import java.util.*;

class Job {

    int id;
    int profit;
    int deadline;

    Job(int id, int profit, int deadline) {
        this.id = id;
        this.profit = profit;
        this.deadline = deadline;
    }

}

public class JobSequencing {

    // Function to find maximum profit and job order
    public static int[] jobSequencing(Job[] jobs) {

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);


        int maxDeadline = 0;

        for (Job job : jobs)
            maxDeadline = Math.max(maxDeadline, job.deadline);


        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int totalProfit = 0;
        int jobsDone = 0;


        for (Job job : jobs) {

            for (int t = job.deadline; t > 0; t--) {
                if (slots[t] == -1) {
                    slots[t] = job.id;
                    totalProfit += job.profit;
                    jobsDone++;
                    break;
                }
            }

        }

        System.out.println("Scheduled Jobs: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slots[i] != -1)
                System.out.print("J" + slots[i] + " ");
        }
        System.out.println("\nTotal Jobs Done: " + jobsDone);
        System.out.println("Total Profit: " + totalProfit);

        return new int[]{jobsDone, totalProfit};
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job(1, 100, 2),
                new Job(2, 19, 1),
                new Job(3, 27, 2),
                new Job(4, 25, 1),
                new Job(5, 15, 3)
        };

        jobSequencing(jobs);
    }
}

