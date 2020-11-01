/* 
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
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
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        helper(root, sum, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode node, int sum, List<Integer> p) {
        if (node == null) return;
        int k = node.val;
        if (node.left == null && node.right == null) {
            if (sum == k) {
                p.add(k);
                res.add(new ArrayList<>(p));
                p.remove(p.size() - 1);
            }
            return;
        }
        p.add(k);
        helper(node.left, sum - k, p);
        helper(node.right, sum - k, p);
        p.remove(p.size() - 1);
    }
}