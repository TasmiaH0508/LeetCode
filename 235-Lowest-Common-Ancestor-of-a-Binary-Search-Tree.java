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
    Input is a bst, where all treenode values are unique

    Method:
    If root.val is bigger than one of the treenode's values and smaller 
    than the other treenode's values, we have found the lowest common ancestor.

    If root.val is equal to one of the treenodes' values, we have found the
    lowest common ancestor.

    If root.val is smaller than both of the treenodes' values, recurse on the
    right side, where the values are bigger. 

    If root.val is larger than both treenodes' values, recurse on the left 
    side, where the values are smaller. 

    */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null && p != null && q != null) {
            // it is stated that p != q
            if (root.val == p.val || root.val == q.val) {
                return root;
            } else if (p.val > q.val) {
                if (root.val > p.val) {
                    return lowestCommonAncestor(root.left, p, q);
                } else if (root.val > q.val) {
                    return root;
                } else {
                    return lowestCommonAncestor(root.right, p, q);
                }
            } else {
                return lowestCommonAncestor(root, q, p);
            }
        } else {
            return null;
        }
    }
}