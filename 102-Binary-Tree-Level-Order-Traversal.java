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
import java.util.HashMap;
import java.util.Stack;
class Solution {

    HashMap<Integer, Stack<Integer>> level = new HashMap<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        levelOrder(root, 0);
        int start = 0;
        List<List<Integer>> res = new LinkedList<>();
        Stack<LinkedList<Integer>> resStack = new Stack<>();
        while (level.containsKey(start)) {
            LinkedList<Integer> ll = new LinkedList<>();
            Stack<Integer> ss = level.get(start);
            while (!ss.empty()) {
                ll.addFirst(ss.pop());
            }
            resStack.push(ll);
            start++;
        }
        while (!resStack.empty()) {
            LinkedList<Integer> ll = resStack.pop();
            res.addFirst(ll);
        }
        return res;
    }

    private void levelOrder(TreeNode t, int l) {
        if (t != null) {
            if (!level.containsKey(l)) {
                Stack<Integer> s = new Stack<>();
                s.push(t.val);
                level.put(l, s);
            } else {
                Stack<Integer> s = level.get(l);
                s.push(t.val);
            }
            levelOrder(t.left, l + 1);
            levelOrder(t.right, l + 1);
        }
    }
}