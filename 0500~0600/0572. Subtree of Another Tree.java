/* 
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
 

Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
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
     public boolean isSubtree(TreeNode s, TreeNode t) {
        if (null == t) return true;
        if (null == s) return false;
        String sStr = serialByInOrder(s);
        String tStr = serialByInOrder(t);
        return sStr.contains(tStr);
    }

    private String serialByInOrder(TreeNode node) {
        if (null == node) return "N";
        StringBuilder sb = new StringBuilder();
        sb.append("S").append(node.val).append("E");
        sb.append(serialByInOrder(node.left));
        sb.append(serialByInOrder(node.right));
        return sb.toString();
    }
}



class Solution {
    public boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null || t1 == null) return t2 == null;
        return t1.val == t2.val && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right)
            || isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}