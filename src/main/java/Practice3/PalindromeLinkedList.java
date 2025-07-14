package Practice3;

/**
 * Description:<br>
 * Date: 26/04/25-12:16 pm
 *
 * @author ishangarg
 * @since
 */

class ListNode {
    char val;
    ListNode next;

    ListNode(char val) {
        this.val = val;
    }
}

public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle using fast & slow pointers
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode secondHalf = reverse(slow);

        // Step 3: Compare first and second half
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    // Utility to create a linked list from a string
    public static ListNode createListFromString(String s) {
        if (s == null || s.isEmpty()) return null;
        ListNode head = new ListNode(s.charAt(0));
        ListNode current = head;
        for (int i = 1; i < s.length(); i++) {
            current.next = new ListNode(s.charAt(i));
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        String input = "radar";
        ListNode head = createListFromString(input);
        boolean result = isPalindrome(head);

        System.out.println("Is the linked list \"" + input + "\" a palindrome? " + result);
    }
}