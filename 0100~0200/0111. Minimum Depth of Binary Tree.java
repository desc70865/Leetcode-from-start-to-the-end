/* 
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
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
    private int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        return Depth(root, 0);
    }
    
    private int Depth(TreeNode node, int depth) {
        if (node == null) return Math.min(min, depth);
        if (node.left == null) return Depth(node.right, depth+1);
        if (node.right == null) return Depth(node.left, depth+1);
        return Math.min(Depth(node.left, depth+1), Depth(node.right, depth+1));
    }
}



class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int l = minDepth(root.left), r = minDepth(root.right);
        return 1 + (l * r == 0 ? Math.max(l, r) : Math.min(l, r));
    }
}