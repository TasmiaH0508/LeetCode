/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
    If one of the subtrees of the root is the same as one of the treenodes,
    we need to check if the other treenode is in the other subtree or in the 
    subtree of the subtree
    */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p) {
            return p;
        } else if (root == q) {
            return q;
        } else {
            if (isInSubtree(root.left, p)) {
                if (isInSubtree(root.right, q)) {
                    return root;
                } else {
                    return lowestCommonAncestor(root.left, p, q);
                }
            } else {
                if (isInSubtree(root.right, q)) {
                    return lowestCommonAncestor(root.right, p, q);
                } else {
                    return root;
                }
            }
        }
    }

    public boolean isInSubtree(TreeNode root, TreeNode t) {
        if (t == null && root == null) {
            return true;
        } else if (t == null || root == null) {
            return false;
        } else {
            if (root == t) {
                return true;
            } else {
                return isInSubtree(root.left, t) || isInSubtree(root.right, t);
            }
        }
    }
}