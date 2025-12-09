package Practice1;


class Node11 {
    int value;
    Node11 next;

    Node11(int value) {
        this.value = value;
        this.next = null;
    }

}

class ReturnNode {
    Node11 head;
    Node11 globalReturn;

    ReturnNode(Node11 head, Node11 globalReturn) {
        this.head = head;
        this.globalReturn = globalReturn;
    }

}

public class ReverseLinkedList {

    public static void main(String args[]) {

        int arr[] = {1, 2, 3, 4, 5, 6};
        int m = 2;

        Node11 head = new Node11(arr[0]);
        Node11 dummy = head;

        for (int i = 1; i < arr.length; i++) {
            dummy.next = new Node11(arr[i]);
            dummy = dummy.next;
        }

        dummy = head;
        while (dummy != null) {
            System.out.print(dummy.value + "  ");
            dummy = dummy.next;
        }
        System.out.println("\n======================BREAK==========");

        // reverse in groups of m
        Node11 newHead = reverseListInMTimesK(head, m);

        // print modified list
        dummy = newHead;
        while (dummy != null) {
            System.out.print(dummy.value + "  ");
            dummy = dummy.next;
        }
    }


    // Reverse list in groups of size M
    public static Node11 reverseListInMTimesK(Node11 head, int M) {
        if (head == null || M <= 1) return head;

        Node11 currBlockStart = head;
        Node11 overallHead = null;   // final head after all reversals
        Node11 prevBlockTail = null; // tail of previous reversed block

        while (currBlockStart != null) {
            // reverse current block
            System.out.println("currBlockStart===  before" + currBlockStart.value);
            ReturnNode rn = reverseBlock(currBlockStart, M);
            System.out.println("currBlockStart===  after" + currBlockStart.value);
            Node11 reversedHead = rn.head;          // head of current reversed block
            Node11 nextBlockStart = rn.globalReturn;// start of next block
            Node11 currBlockTail = currBlockStart;  // after reversal, original head becomes tail

            // set overall head once (for the first block)
            if (overallHead == null) {
                overallHead = reversedHead;
            }

            // connect previous block to current reversed block
            if (prevBlockTail != null) {
                prevBlockTail.next = reversedHead;
            }

            // move prevBlockTail for next iteration
            prevBlockTail = currBlockTail;

            // move to next block
            currBlockStart = nextBlockStart;
        }

        return overallHead;
    }

    public static ReturnNode reverseBlock(Node11 head, int M) {
        Node11 newHead = null;
        Node11 curr = head;
        int i = 0;

        while (curr != null && i < M) {
            i++;
            Node11 next = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = next;
        }

        // curr is the start of the next block
        return new ReturnNode(newHead, curr);
    }
}
