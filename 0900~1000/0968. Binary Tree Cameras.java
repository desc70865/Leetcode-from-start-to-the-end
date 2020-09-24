/* 
Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

 

Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.
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
    public int minCameraCover(TreeNode root) {
        return helper(root)[1];
    }
    
    // @return A[]
    // A[0] 有监控 && 满足
    // A[1] 无监控 && 满足
    // A[2] 无监控 && 未必满足
    
    private int[] helper(TreeNode node) {
        if (node == null) return new int[] { Integer.MAX_VALUE / 2, 0, 0 };
        int[] l = helper(node.left);
        int[] r = helper(node.right);
        int a = l[2] + r[2] + 1;
        int b = Math.min(a, Math.min(l[0] + r[1], l[1] + r[0]));
        int c = Math.min(a, l[1] + r[1]);
        return new int[] { a, b, c };
    }
}