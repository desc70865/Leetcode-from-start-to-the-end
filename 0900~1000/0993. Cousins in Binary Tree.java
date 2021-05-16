/* 
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

 

Example 1:


Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Constraints:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
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
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode dummy = new TreeNode(-1);
        return dfs(dummy, root, x, 0).isCousins(dfs(dummy, root, y, 0));
    }

    private Node dfs(TreeNode parent, TreeNode node, int target, int level) {
        if (node == null) {
            return null;
        }
        if (node.val == target) {
            return new Node(parent, level);
        }
        Node next = dfs(node, node.left, target, level + 1);
        return next != null ? next : dfs(node, node.right, target, level + 1);
    }
}

class Node {
    TreeNode parent;
    int level;

    public Node(TreeNode node, int k) {
        this.parent = node;
        this.level = k;
    }

    public boolean isCousins(Node node) {
        return this.level == node.level && this.parent != node.parent;
    }
}