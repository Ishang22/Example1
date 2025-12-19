package Practice1.DP;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    int n;

    // Check if substring s[l..r] is palindrome
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    // Backtracking function
    private void backtrack(String s, int idx,
                           List<String> curr,
                           List<List<String>> result) {

        // Base case: reached end of string
        if (idx == n) {
            result.add(new ArrayList<>(curr)); // deep copy
            return;
        }

        // Try all possible partitions starting at idx
        for (int i = idx; i < n; i++) {
            if (isPalindrome(s, idx, i)) {

                // choose
                curr.add(s.substring(idx, i + 1));

                // explore
                backtrack(s, i + 1, curr, result);

                // undo (backtrack)
                curr.remove(curr.size() - 1);
            }
        }
    }

    // Main function
    public List<List<String>> partition(String s) {
        n = s.length();
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
}
