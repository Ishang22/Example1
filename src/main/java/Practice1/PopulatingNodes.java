package Practice1;// https://www.youtube.com/watch?v=a8VKpW1DsD8

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
/*
         1

    2        3
 */

//https://www.youtube.com/watch?v=a8VKpW1DsD8
public class PopulatingNodes {

    public Node connect2(Node root) {

        if (root == null || root.left == null) {
            return root;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);

        Node prev = null;

        while (q.size() > 0) {
            Node curr = q.poll();

            if (curr == null) {
                if (q.size() == 0) {
                    break;
                }
                q.offer(null);
            } else {

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }

                if (prev != null) {
                    prev.next = curr;
                }
            }
            prev = curr;
        }

        return root;
    }

}