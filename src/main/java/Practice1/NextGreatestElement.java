package Practice1;
// res = res * 10 + str.charAt(i) - '0';
import java.util.ArrayList;

// This algorithm effectively finds the next greater element in a BST for a given value.
class Node1 {
    int data;
    Node1 left, right;

    Node1(int x) {
        data = x;
        left = right = null;
    }
}

//                 6
//          4             8
//      1       5      7     9
//                   6.5
//

//inorder succor and precedor of 6.3 - > succes = 6.5 precorder 6

//inorder succor and precedor of 6 - > succes = 6.5 precorder 5

public class NextGreatestElement {

    static Node1 rightMost(Node1 node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    static Node1 leftMost(Node1 node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Return ArrayList with pre at index 0 and suc at index 1
    static ArrayList<Node1> findPreSuc(Node1 root, int key) {
        Node1 pre = null, suc = null;
        Node1 curr = root;

        while (curr != null) {

            if (curr.data > key) {
                suc = curr;
                curr = curr.left;
            }
           else if (curr.data < key) {
                pre = curr;
                curr = curr.right;
            }  else {
                if (curr.left != null)
                    pre = rightMost(curr.left);
                if (curr.right != null)
                    suc = leftMost(curr.right);

                break;
            }

        }

        ArrayList<Node1> result = new ArrayList<>();
        result.add(pre);  // index 0: predecessor
        result.add(suc);  // index 1: successor
        return result;
    }

}

