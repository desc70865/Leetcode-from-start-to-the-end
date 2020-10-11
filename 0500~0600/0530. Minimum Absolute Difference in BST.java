/* 
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 

Note:

There are at least two nodes in this BST.
This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
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
    private int min = Integer.MAX_VALUE;
    int pre = Integer.MIN_VALUE / 2;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        inPrint(root);
        return min;
    }

    public void inPrint(TreeNode node) {
        if (node == null) return;
        inPrint(node.left);
        min = Math.min(min, node.val - pre);
        pre = node.val;
        inPrint(node.right);
    }
}