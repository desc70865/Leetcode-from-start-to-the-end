/* 
Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)

A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.

Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.

 

Example 1:


Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1

Output: [1,2,3,4,null,null,7,8,9,null,14]
Example 2:


Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22

Output: [5,4,8,11,null,17,4,7,null,null,null,5]
 

Example 3:


Input: root = [1,2,-3,-5,null,4,null], limit = -1

Output: [1,null,-3,4]
 

Note:

The given tree will have between 1 and 5000 nodes.
-10^5 <= node.val <= 10^5
-10^9 <= limit <= 10^9
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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        int[] res = dfs(root, limit);
        if (res[0] == 1) return null;
        return root;
    }

    private int[] dfs(TreeNode node, int rem) {
        if (node == null) {
            return new int[] {0, 1};
        }
        rem -= node.val;
        if (node.left == null && node.right == null) {
            return new int[] {rem > 0 ? 1 : 0, rem};
        }
        int[] L = dfs(node.left, rem);
        int[] R = dfs(node.right, rem);
        if (L[0] == 1) node.left = null;
        if (R[0] == 1) node.right = null;
        if (L[1] > 0 && R[1] > 0) return new int[] {1, 1};
        return new int[] {0, 0};
    }
}