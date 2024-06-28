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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            if (subRoot == null) {
                return true;
            } else {
                return false;
            }
        } else if (subRoot == null) {
            if (root == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (root.val == subRoot.val) {
                return areEqual(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
            } else {
                return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
            }
        }
    }

    private boolean areEqual(TreeNode t, TreeNode s) {
        if (t == null) {
            if (s == null) {
                return true;
            } else {
                return false;
            }
        } else if (s == null) {
            if (t == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (t.val == s.val) {
                return areEqual(t.left, s.left) && areEqual(t.right, s.right);
            } else {
                return false;
            }
        }
    }
}