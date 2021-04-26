/* 
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
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
    int sum = 0;

    public int rangeSumBST(TreeNode root, int L, int R) {
        add(root, L, R);
        return sum;
    }

    private void add(TreeNode node, int L, int R) {
        if (node == null) return ;
        if (node.val >= L && node.val <= R) sum += node.val;
        if (node.val > L) add(node.left, L, R);
        if (node.val < R) add(node.right, L, R);
    }
}



class Solution {public int rangeSumBST(TreeNode root, int low, int high) {
        return root == null ? 0 : (root.val < low || root.val > high ? 0 : root.val) + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}