/* 
Given the root of a binary tree, return the number of uni-value subtrees.

A uni-value subtree means all nodes of the subtree have the same value.

 

Example 1:


Input: root = [5,1,5,5,5,null,5]
Output: 4
Example 2:

Input: root = []
Output: 0
Example 3:

Input: root = [5,5,5,5,5,null,5]
Output: 6
 

Constraints:

The numbrt of the node in the tree will be in the range [0, 1000].
-1000 <= Node.val <= 1000
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
    int ans = 0;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private boolean dfs(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        boolean l = dfs(node.left, node.val), r = dfs(node.right, node.val);
        if (l && r) {
            ans++;
            return node.val == val;
        }
        return false;
    }
}