package Practice2;


class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }

}

class ReturnNode {
    Node head;
    Node globalReturn;
    ReturnNode(Node head,Node globalReturn)
    {
        this.head=head;
        this.globalReturn=globalReturn;
    }

}

public class Example1 {
    public static void main(String args[]) {

        int arr[] = {1, 2, 3, 4, 5, 6};
        int m = 2;

        Node head = new Node(arr[0]);
        Node dummy = head;

        for (int i = 1; i < arr.length; i++) {
            dummy.next = new Node(arr[i]);
            dummy = dummy.next;
        }

        dummy = head;
        while (dummy != null) {
            System.out.print(dummy.value + "  ");
            dummy = dummy.next;
        }
        System.out.println("\n======================BREAK==========");

        // reverse in groups of m
        Node newHead = reverseListInMTimesK(head, m);

        // print modified list
        dummy = newHead;
        while (dummy != null) {
            System.out.print(dummy.value + "  ");
            dummy = dummy.next;
        }
    }


    // Reverse list in groups of size M
    public static Node reverseListInMTimesK(Node head, int M) {
        if (head == null || M <= 1) return head;

        Node currBlockStart = head;
        Node overallHead = null;   // final head after all reversals
        Node prevBlockTail = null; // tail of previous reversed block

        while (currBlockStart != null) {
            // reverse current block
            System.out.println("currBlockStart===  before"+currBlockStart.value);
            ReturnNode rn = reverseBlock(currBlockStart, M);
            System.out.println("currBlockStart===  after"+currBlockStart.value);
            Node reversedHead = rn.head;          // head of current reversed block
            Node nextBlockStart = rn.globalReturn;// start of next block
            Node currBlockTail = currBlockStart;  // after reversal, original head becomes tail

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

    public static ReturnNode reverseBlock(Node head, int M) {
        Node newHead = null;
        Node curr = head;
        int i = 0;

        while (curr != null && i < M) {
            i++;
            Node next = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = next;
        }

        // curr is the start of the next block
        return new ReturnNode(newHead, curr);
    }


}
