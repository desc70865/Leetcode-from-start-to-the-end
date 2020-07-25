/* 
Given a binary tree, return the sum of values of its deepest leaves.
 

Example 1:



Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
 

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
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
    private int maxDepth = 0, sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        dfs(1, root);
        return sum;
    }

    private void dfs(int depth, TreeNode node) {
        if (node == null) return;
        if (depth == maxDepth) {
            sum += node.val;
        } else if (depth > maxDepth) {
            maxDepth = depth;
            sum = node.val;
        }
        dfs(depth + 1, node.left);
        dfs(depth + 1, node.right);
    }
}