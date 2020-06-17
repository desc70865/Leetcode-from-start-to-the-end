/* 
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */

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
    private List<Integer> inOrderList;
    
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrderList = new ArrayList<>();
        if (root == null) return inOrderList;
        inOrder(root);
        return this.inOrderList;
    }
    
    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            inOrderList.add(node.val);
            inOrder(node.right);
        }
    }
}


class Solution {
    private List<Integer> inOrderList;
    
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrderList = new ArrayList<>();
        if (root == null) return inOrderList;
        inOrder(root);
        return this.inOrderList;
    }
    
    private void inOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()) {
                p = stack.pop();
                inOrderList.add(p.val);
                p = p.right;
            }
        }
    }
}