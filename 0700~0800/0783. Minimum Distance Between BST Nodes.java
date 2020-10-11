/* 
Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \    
    1   3  

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
Note:

The size of the BST will be between 2 and 100.
The BST is always valid, each node's value is an integer, and each node's value is different.
This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
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
    public int minDiffInBST(TreeNode root) {
        p = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        dfs(root);
        Collections.sort(p);
        for (int i = 1; i < p.size(); i++) {
            min = Math.min(min, p.get(i) - p.get(i - 1));
        }
        return min;
    }
    
    private void dfs(TreeNode node) {
        if (node == null) return;
        p.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }
}