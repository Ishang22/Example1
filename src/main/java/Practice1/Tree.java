package Practice1;

import java.util.Scanner;

public class Tree {
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int data = sc.nextInt();
        Node3 root = null;

        while (data != -1) {
            root = createTree(root, data);
            data = sc.nextInt();
        }

        inOrder(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
    }
            /*
            5
        4
            */
    static Node3 createTree(Node3 root, int data) {

        if (root == null) {
            return new Node3(data);
        }

        if (root.data > data) {
            root.left = createTree(root.left, data);
        } else {
            root.right = createTree(root.right, data);
        }

        return root;
    }

    static void inOrder(Node3 root) {
        if (root == null) return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    static void preOrder(Node3 root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node3 root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    //                 6
//          4                 8
//      1       5        7        9
//.         4.5   5.5                10
    public static Node3 deleteNode(Node3 root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = findMin(root.right);

            root.right = deleteNode(root.right, root.data);

        }
        return root;
    }

    public static int findMin(Node3 root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    public int findMax(Node3 root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    // Function to return the lowest common ancestor in a Binary Tree.
    Node3 lca(Node3 root, int n1, int n2)
    {
        if (root == null) return null;
        if (root.data == n1 || root.data == n2) return root;

        Node3 left = lca(root.left, n1, n2);
        Node3 right = lca(root.right, n1, n2);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }


}



class Node3 {
    Node3 left, right;
    int data;

    public Node3(int data) {
        this.data = data;
    }
}