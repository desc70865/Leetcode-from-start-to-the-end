/* 
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

 

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

 

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000. */


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
    int res = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        dfs(root, -1);
        return Math.max(res - 1, 0);
    }
    
    private int dfs(TreeNode node, int val) {
        if (node == null) {
			return 0;
		}
        int l = dfs(node.left, node.val);
        int r = dfs(node.right, node.val);
        res = Math.max(res, l + r + 1);
        return node.val == val ? 1 + Math.max(l, r) : 0;
    }
}