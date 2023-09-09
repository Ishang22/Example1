
// res = res * 10 + str.charAt(i) - '0';

class Node1 {
    int data;
    Node1 right;
    Node1 left;
}

//                 67
//          45             89
//      12       56            91
//           48       57
//        46     49


public class NextGreatestElement {
    int leftTurnValue = -1;

    int nextHigherValue(Node1 root, int value) {

        if (root == null) {
            return -1;
        }

        if (root.data == value && root.right != null) {
            //45(46)  and  89(91)
            return returnLeftElement(root.right);
        }

        if (root.data == value && root.right == null && leftTurnValue != -1) {
            //46 ke liye ---- 48  and 49 ke liye 56
            return leftTurnValue;
        }


        if (root.data < value) {
            return nextHigherValue(root.right, value);
        } else {
            leftTurnValue = root.data;
            return nextHigherValue(root.left, value);
        }


    }

    static int returnLeftElement(Node1 root) {
        int data = -1;

        while (root != null) {
            data = root.data;
            root = root.left;
        }

        return data;
    }
}

