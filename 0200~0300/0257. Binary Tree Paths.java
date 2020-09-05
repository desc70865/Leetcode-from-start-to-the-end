/* 
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        search(root, "");
        return res;
    }

    private boolean search(TreeNode node, String sb) {
        if (node == null) {
            return true;
        }
        if (sb.length() > 0) sb += "->";
        sb += node.val;
        boolean l = search(node.left, sb), r = search(node.right, sb);
        if (l && r) {
            res.add(sb);
        }
        return false;
    }
}



class Solution {
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        search(root, "");
        return res;
    }

    private void search(TreeNode node, String sb) {
        if (node == null) {
            return ;
        }
        sb += node.val;
        if (node.left == null && node.right == null) {
            res.add(sb);
            return ;
        }
        sb += "->";
        search(node.left, sb);
        search(node.right, sb);
    }
}



class Solution {
    List<String> res = new ArrayList<>();
    Stack<String> p = new Stack<String>();
    public List<String> binaryTreePaths(TreeNode root) {
        search(root);
        return res;
    }

    private void search(TreeNode node) {
        if (node == null) {
            return ;
        }
        p.push("" + node.val);
        if (node.left == null && node.right == null) {
            res.add(String.join("->", p));
        } else {
            search(node.left);
            search(node.right);
        }
        p.pop();
    }
}