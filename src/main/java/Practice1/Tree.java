package Practice1;

import java.util.Scanner;

public class Tree {
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        Node3 root = createTree();
        inOrder(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
    }

    static Node3 createTree() {

        Node3 root = null;
        System.out.println("Enter data: ");
        int data = sc.nextInt();

        if (data == -1) return null;

        root = new Node3(data);

        System.out.println("Enter left for " + data);
        root.left = createTree();

        System.out.println("Enter right for " + data);
        root.right = createTree();

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
}

class Node3 {
    Node3 left, right;
    int data;
    public Node3(int data) {
        this.data = data;
    }
}