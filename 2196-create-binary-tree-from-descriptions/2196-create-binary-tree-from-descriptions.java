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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> children = new HashSet<>();
        Map<Integer, TreeNode> valToTreeNode = new HashMap<>();
        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            int side = desc[2];
            if (!children.contains(childVal)) {
                children.add(childVal);
            }
            if (valToTreeNode.containsKey(parentVal)) {
                TreeNode tree = valToTreeNode.get(parentVal);
                if (valToTreeNode.containsKey(childVal)) {
                    TreeNode subtree = valToTreeNode.get(childVal);
                    if (side == 0) {
                        tree.right = subtree;
                    } else {
                        tree.left = subtree;
                    }
                } else {
                    TreeNode subtree = new TreeNode(childVal);
                    valToTreeNode.put(childVal, subtree);
                    if (side == 0) {
                        tree.right = subtree;
                    } else {
                        tree.left = subtree;
                    }
                }
            } else {
                TreeNode tree = new TreeNode(parentVal);
                if (valToTreeNode.containsKey(childVal)) {
                    TreeNode subtree = valToTreeNode.get(childVal);
                    if (side == 0) {
                        tree.right = subtree;
                    } else {
                        tree.left = subtree;
                    }
                } else {
                    TreeNode subtree = new TreeNode(childVal);
                    valToTreeNode.put(childVal, subtree);
                    if (side == 0) {
                        tree.right = subtree;
                    } else {
                        tree.left = subtree;
                    }
                }
                valToTreeNode.put(parentVal, tree);
            }
        }
        for (Integer i : valToTreeNode.keySet()) {
            if (!children.contains(i)) {
                System.out.println("THe value not contained in children is" + i);
                return valToTreeNode.get(i);
            }
        }
        return null;
    }
}