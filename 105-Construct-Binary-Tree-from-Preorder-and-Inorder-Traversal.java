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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { 
        this.val = val; 
    }
}

public class Solution {
    private HashMap<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create a HashMap to store the value -> index relations of the inorder array
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        // Call the recursive helper function
        return arrayToTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    private TreeNode arrayToTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        // Base case: if there are no elements to construct the tree
        if (preorderLeft > preorderRight) {
            return null;
        }
        
        // The first element in preorder is the root for the current subtree
        int rootVal = preorder[preorderLeft];
        TreeNode root = new TreeNode(rootVal);
        
        // Find the index of the root in the inorder traversal
        int rootIndexInorder = inorderIndexMap.get(rootVal);
        
        // Calculate the size of the left subtree
        int leftSubtreeSize = rootIndexInorder - inorderLeft;
        
        // Recursively build the left and right subtrees
        root.left = arrayToTree(preorder, preorderLeft + 1, preorderLeft + leftSubtreeSize, inorderLeft, rootIndexInorder - 1);
        root.right = arrayToTree(preorder, preorderLeft + leftSubtreeSize + 1, preorderRight, rootIndexInorder + 1, inorderRight);
        
        return root;
    }
}
