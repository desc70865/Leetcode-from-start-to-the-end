/* 
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
    int preIndex = 0, inIndex = 0;
    int[] pre, in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder; this.in = inorder;
        return recursive(Integer.MAX_VALUE);
    }
    
    private TreeNode recursive(int rootVal) {
        if (preIndex >= pre.length) return null;
        if (rootVal == in[inIndex]) {
            inIndex++;
            return null;
        }
        int curVal = pre[preIndex++];
        TreeNode cur = new TreeNode(curVal);
        cur.left = recursive(curVal);
        cur.right = recursive(rootVal);
        return cur;
    }
}