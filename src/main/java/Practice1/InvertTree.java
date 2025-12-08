package Practice1;

/**
 * Description:<br>
 * Date: 28/08/25-6:56 pm
 *
 * @author ishangarg
 * @since
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {

        if(root==null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right=tempNode;

        return root;

    }
}