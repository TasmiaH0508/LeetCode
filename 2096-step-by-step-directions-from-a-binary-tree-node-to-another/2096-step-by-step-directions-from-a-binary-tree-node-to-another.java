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

class Pair {
    int val;
    char direction;
    Pair(int i, char c) {
        val = i;
        direction = c;
    }
}

class Solution {
    // we need to find the lowest common ancestor
    Map<Integer, Pair> map = new HashMap<>();
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder();
        TreeNode lca = lowestCommonAncestor(root, startValue, destValue);
        dfs(lca);
        if (map.containsKey(startValue)) {
            while (map.containsKey(startValue)) {
                s.append('U');
                startValue = map.get(startValue).val;
            }
        } else {
            // it is the root/lca
        }
        Stack<Character> stack = new Stack<>();
        if (map.containsKey(destValue)) {
            while (map.containsKey(destValue)) {
                stack.push(map.get(destValue).direction);
                destValue = map.get(destValue).val;
                if (destValue == lca.val) {
                    break;
                }
            }
        } else {
            // it is the root
        }
        while (!stack.empty()) {
            Character c = stack.pop();
            s.append(c);
        }
        return s.toString();
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root.val == p) {
            return root;
        } else if (root.val == q) {
            return root;
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

    private boolean isInSubtree(TreeNode root, int t) {
        if (root == null) {
            return false;
        } else {
            if (root.val == t) {
                return true;
            } else {
                return isInSubtree(root.left, t) || isInSubtree(root.right, t);
            }
        }
    }

    private void dfs(TreeNode t) {
        if (t.left == null && t.right == null) {

        } else if (t.left == null) {
            map.put(t.right.val, new Pair(t.val, 'R'));
            dfs(t.right);
        } else if (t.right == null) {
            map.put(t.left.val, new Pair(t.val, 'L'));
            dfs(t.left);
        } else {
            map.put(t.right.val, new Pair(t.val, 'R'));
            map.put(t.left.val, new Pair(t.val, 'L'));
            dfs(t.right);
            dfs(t.left);
        }
    }
}