package Practice1.DP;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            int[] odd = expandFromCenter(s, i, i);

            // Even length palindrome
            int[] even = expandFromCenter(s, i, i + 1);

            // Choose longer one
            int oddLen = odd[1] - odd[0] + 1;
            int evenLen = even[1] - even[0] + 1;

            int[] better = oddLen > evenLen ? odd : even;

            // Update best palindrome
            if (better[1] - better[0] > bestEnd - bestStart) {
                bestStart = better[0];
                bestEnd = better[1];
            }
        }

        return s.substring(bestStart, bestEnd + 1);
    }

    private int[] expandFromCenter(String s, int left, int right) {

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // Move back to valid palindrome boundaries
        return new int[]{left + 1, right - 1};
    }
}
