package Practice1;


public class FlattenBinaryTree {
    // We'll use a global variable to track the next right node (NR)
    private TreeNode nextRightNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // Recurse on right subtree first
        flatten(root.right);

        // Then recurse on left subtree
        flatten(root.left);

        // Flatten current node
        root.right = nextRightNode;
        root.left = null;

        // Move the pointer to current node
        nextRightNode = root;
    }

    // Helper to print the flattened tree
    public void printFlattened(TreeNode root) {
        while (root != null) {
            System.out.print(root.val + " -> ");
            root = root.right;
        }
        System.out.println("null");
    }

    // Example
    public static void main(String[] args) {
        FlattenBinaryTree tree = new FlattenBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        tree.flatten(root);
        tree.printFlattened(root);
    }
}

