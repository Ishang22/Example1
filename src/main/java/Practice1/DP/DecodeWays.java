package Practice1.DP;

import java.util.Arrays;

public class DecodeWays {

    int[] dp;

    private int solve(int i, String s) {
        int n = s.length();

        // Base case: reached end
        if (i == n) {
            return 1;
        }

        // Leading zero → invalid
        if (s.charAt(i) == '0') {
            return 0;
        }

        // Memoized result
        if (dp[i] != -1) {
            return dp[i];
        }

        System.out.println(s.charAt(i));
        // Take one digit
        int result = solve(i + 1, s);

        // Take two digits if valid
        if (i + 1 < n) {
            if (s.charAt(i) == '1' ||
                    (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                System.out.println(" 2" +s.substring(i, i + 2));
                result += solve(i + 2, s);
            }
        }

        return dp[i] = result;
    }

    public int numDecodings(String s) {
        int n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, s);
    }

    public static void main(String args[]) {
        DecodeWays decodeWays = new DecodeWays();
        decodeWays.numDecodings("123");
    }
}
