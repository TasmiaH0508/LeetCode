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
    Method:
    1. Go down the tree and fill up a hashmap that maps from depth to the 
    nodes at that depth. At the same time, create a parent hashmap
    that maps from the child to parent. 
    2. for the node at the greater depth, trace its parent until the parent 
    is at the same depth as the higher node. 
    3. find lowest common ancestor
    */
    Map<TreeNode, TreeNode> parentOf = new HashMap<>();
    Map<TreeNode, Integer> depthOf = new HashMap<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        parentOf.put(root, null);
        fillHashMaps(root, 0);
        return lowestCommonAncestor(p, q);
    }

    private TreeNode lowestCommonAncestor(TreeNode t, TreeNode p) {
        if (depthOf.containsKey(t) && depthOf.containsKey(p)) {
            int depthT = depthOf.get(t);
            int depthP = depthOf.get(p);
            if (depthT == depthP) {
                if (t == p) {
                    return t;
                } else {
                    return lowestCommonAncestor(parentOf.get(t), parentOf.get(p));
                }
            } else if (depthT > depthP) {
                return lowestCommonAncestor(parentOf.get(t), p);
            } else {
                return lowestCommonAncestor(t, parentOf.get(p));
            }
        }
        return null;
    }

    private void fillHashMaps(TreeNode t, int l) {
        if (t.left == null && t.right == null) {
            depthOf.put(t, l);
        } else if (t.left == null) {            
            depthOf.put(t, l);
            parentOf.put(t.right, t);
            fillHashMaps(t.right, l + 1);
        } else if (t.right == null) {
            depthOf.put(t, l);
            parentOf.put(t.left, t);
            fillHashMaps(t.left, l + 1);
        } else {
            depthOf.put(t, l);
            parentOf.put(t.left, t);
            parentOf.put(t.right, t);
            fillHashMaps(t.left, l + 1);
            fillHashMaps(t.right, l + 1);
        }
    }
}