/* 
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
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
    List<Integer> p;
    public int findBottomLeftValue(TreeNode root) {
        p = new ArrayList<>();
        dfs(root, 0);
        return p.get(p.size() - 1);
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (p.size() == depth) p.add(node.val);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}