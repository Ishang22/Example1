package Practice1;

/**
 * Description:<br>
 * Date: 26/04/25-12:16 pm
 *
 * @author ishangarg
 * @since
 */
class ListNode1 {
    char val;
    ListNode1 next;

    ListNode1(char val) {
        this.val = val;
    }
}

public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode1 head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle using fast & slow pointers
        ListNode1 slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode1 secondHalf = reverse(slow);
        //🥶
        // Step 3: Compare first and second half
        ListNode1 firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private static ListNode1 reverse(ListNode1 head) {
        ListNode1 newHead = null;
        while (head != null) {
            ListNode1 next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    // Utility to create a linked list from a string
    public static ListNode1 createListFromString(String s) {
        if (s == null || s.isEmpty()) return null;
        ListNode1 head = new ListNode1(s.charAt(0));
        ListNode1 current = head;
        for (int i = 1; i < s.length(); i++) {
            current.next = new ListNode1(s.charAt(i));
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        String input = "radar";
        ListNode1 head = createListFromString(input);
        boolean result = isPalindrome(head);

        System.out.println("Is the linked list \"" + input + "\" a palindrome? " + result);
    }
}