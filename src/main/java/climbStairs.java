public class climbStairs {
    public int climbStairs(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Solution4 {
    public int climbStairs(int n) {
        int[] memo = new int[n+1];
        return rec(n, memo);
    }

    private static int rec(int n, int[] memo){
        if(n <= 1) return 1;
        if(memo[n] > 0) return memo[n];
        int op1 = rec(n-1, memo);
        int op2 = rec(n-2, memo);
        memo[n] = op1 + op2;
        return  op1 + op2;
    }
}