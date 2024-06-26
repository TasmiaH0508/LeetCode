/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
    Problems at hand:
    - we do not know many rotations the tree needs 
    - it becomes very difficult to track the parent ...

    Method:
    1. Construct a inorder traversal of the tree
    2. Construct a balanced tree from the inorder traversal
    */

    LinkedList<Integer> inorder = new LinkedList<>();
    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        int[] inorderArr = new int[inorder.size()];
        int i = 0;
        while (!inorder.isEmpty()) {
            inorderArr[i] = inorder.poll();
            i++;
        }
        return rebuild(0, inorderArr.length - 1, inorderArr);
    }

    private void getInorder(TreeNode root) {
        if (root.left == null && root.right == null) {
            inorder.add(root.val);
        } else if (root.left == null) {
            inorder.add(root.val);
            getInorder(root.right);
        } else if (root.right == null) {
            getInorder(root.left);
            inorder.add(root.val);
        } else {
            getInorder(root.left);
            inorder.add(root.val);
            getInorder(root.right);
        }
    }

    private TreeNode rebuild(int low, int high, int[] arr) {
        if (low > high) {
            return null;
        }
        int mid = (low + high)/2;
        int elemMid = arr[mid];
        return new TreeNode(elemMid, rebuild(low, mid - 1, arr), rebuild(mid + 1, high, arr));
    }
}