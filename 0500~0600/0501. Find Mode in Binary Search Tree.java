/* 
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2
 

return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    List<Integer> list;
    TreeNode pre = null;
    int cnt = 1, max = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        inOrder(root);
        if (list.size() == 0) return new int[] { pre.val };
        if (pre.val != list.get(list.size() - 1)) {
            if (cnt == max) list.add(pre.val);
            else if (cnt > max) return new int[] { pre.val };
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        
        if (pre != null && pre.val == node.val) cnt++;
        else {
            if (cnt == max) list.add(pre.val);
            else if (cnt > max) {
                max = cnt;
                list = new ArrayList<>();
                if (pre != null) list.add(pre.val);
            }
            cnt = 1;
        }
        pre = node;

        inOrder(node.right);
    }
}