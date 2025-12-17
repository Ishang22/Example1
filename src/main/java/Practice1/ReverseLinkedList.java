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
    Node11 newHead;
    Node11 nextStarting;

    ReturnNode(Node11 head, Node11 globalReturn) {
        this.newHead = head;
        this.nextStarting = globalReturn;
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

        Node11 currHead = head;
        Node11 overallHead = null;   // final head after all reversals
        Node11 prevHead = null; // tail of previous reversed block

        while (currHead != null) {
            // reverse current block
            ReturnNode rn = reverseBlock(currHead, M);

            // set overall head once (for the first block)
            if (overallHead == null) {
                overallHead = rn.newHead;
            }

            // connect previous block to current reversed block
            if (prevHead != null) {
                prevHead.next = rn.newHead;
            }

            // move prevBlockTail for next iteration 1
            prevHead = currHead;

            // move to next block
            currHead = rn.nextStarting;
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

        if (newHead != null) {
            System.out.println("    Head    " + newHead.value);
        }

        if (curr != null) {
            System.out.println("    curr    " + curr.value);
        }

        System.out.println("****************** &&&&&&&&&& **************");
        // curr is the start of the next block
        return new ReturnNode(newHead, curr);
    }
}
