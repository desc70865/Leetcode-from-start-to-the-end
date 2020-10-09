/* 
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note:

The tree will have between 1 and 100 nodes.
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
    int[] list;
    int max;
    public boolean isCompleteTree(TreeNode root) {
        list = new int[100];
        max = 0;
        if (! dfs(root, 0, 0)) return false;
        for (int i = 1; i < max; i++) {
            if (list[i] != list[i - 1] * 2) return false;
        }
        return true;
    }

    private boolean dfs(TreeNode node, int level, int idx) {
        if (node == null) return true;
        max = Math.max(max, level);
        return list[level]++ == idx && dfs(node.left, level + 1, idx * 2) && dfs(node.right, level + 1, idx * 2 + 1);
    }
}