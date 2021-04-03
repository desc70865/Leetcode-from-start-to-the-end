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
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode node, int sum, List<Integer> tmpList) {
        if (node == null) return;
        int cur = node.val;
        tmpList.add(cur);
        if (sum == cur && node.left == null && node.right == null) {
            ans.add(new ArrayList<>(tmpList));
        } else {
            dfs(node.left, sum - cur, tmpList);
            dfs(node.right, sum - cur, tmpList);
        }
        tmpList.remove(tmpList.size() - 1);
    }
}