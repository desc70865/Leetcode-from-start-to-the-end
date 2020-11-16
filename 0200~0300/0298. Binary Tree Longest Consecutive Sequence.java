/* 
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int max = 0;

    public int longestConsecutive(TreeNode root) {
        dfs(root, 0, Integer.MIN_VALUE);
        return max;
    }

    private void dfs(TreeNode node, int curLen, int pre) {
        if (node == null) {
            max = Math.max(max, curLen);
            return;
        }
        if (node.val == pre + 1) curLen++;
        else {
            max = Math.max(max, curLen);
            curLen = 1;
        }
        dfs(node.left, curLen, node.val);
        dfs(node.right, curLen, node.val);
    }
}