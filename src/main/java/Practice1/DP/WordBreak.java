package Practice1.DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
/*
String s = "catsandog";
List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
FALSE

String s = "applepenapple";
List<String> wordDict = Arrays.asList("apple", "pen");
TRUE
*/

    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert the dictionary to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);

        // Find the maximum word length in the dictionary
        int maxLen = 0;

        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        int n = s.length();
        // dp[i] states if the substring s[0..i) can be segmented
        boolean[] dp = new boolean[n + 1];

        // Base case: empty string is valid
        dp[0] = true;
       //  String s = "catsandog";
      //  List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
     //      C A T S A N D O G
     //   0  1 2 3 4 5 6 7 8 9
    //    T  F F T T F F T F F
        for (int i = 1; i <= n; i++) {
                                        //      3    4 = -1
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {
                // 2 1 0                                  0 4
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    System.out.println(" j "+j+ "  i  " +i +" words "+s.substring(j, i)+"  "+(i - maxLen));
                    dp[i] = true;
                    break; // No need to check further prefixes
                }
            }
        }

        return dp[n];
    }

    public static void main(String args[])
    {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        wordBreak(s,wordDict);
    }
}
