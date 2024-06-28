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
    is a bst - has a sorted property
    1. do an inorder traversal 
    2. return k th smallest 
    */

    LinkedList<Integer> s = new LinkedList<>();
    int reqdSize;
    public int kthSmallest(TreeNode root, int k) {
        reqdSize = k;
        getInorder(root);
        while (k > 1) {
            k--;
            s.poll();
        }
        return s.poll();
    }

    private void getInorder(TreeNode t) {
        if (t.left == null && t.right == null) {
            s.add(t.val);
        } else if (t.left == null) {
            s.add(t.val);
            getInorder(t.right);
        } else if (t.right == null) {
            getInorder(t.left);
            s.add(t.val);
        } else {
            getInorder(t.left);
            s.add(t.val);
            getInorder(t.right);
        }
    }
}