
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


public class PopulatingNodes {
    public Node connect(Node root) {

        if (root == null) return null;

        Node root1 = root;

        while (root1.left != null) {
            Node curr = root1;

            while (curr != null) {
                curr.left.next = curr.right;
                curr.right.next = curr.next != null ? curr.next.left : null;
                curr = curr.next;
            }

            root1 = root1.left;
        }

        return root;
    }
}