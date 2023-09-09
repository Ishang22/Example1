public class ConsecutiveSumApproach {

    public static void main(String[] args) {

        int N = 15;
        int n = N / 2 + 1;
        int start = 0, end = 0, sum = 0;

        while (end <= n) {

            if (sum > N) {
                ++start;
                sum = sum - start;
            }

            if (sum < N) {
                ++end;
                sum = sum + end;


            }

            if (sum == N) {
                System.out.println(start + " " + end);
                ++start;
                sum = sum - start;

            }
        }

    }
}

//1 2 3 4 5 6 7

//Input: n = 15
//        Output: 4
//        Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5