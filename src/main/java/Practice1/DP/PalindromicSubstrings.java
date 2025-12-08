package Practice1.DP;

public class PalindromicSubstrings {
     //ABBAC Time Complexity: O(n^2)

    public int countSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // Count palindromes with odd length (center at i)
            count += countPalindromes(s, i, i);

            // Count palindromes with even length (center between i and i+1)
            count += countPalindromes(s, i, i + 1);
        }

        return count;
    }
     // 0 0
    // Expand around center and count palindromes
    private int countPalindromes(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }
}
