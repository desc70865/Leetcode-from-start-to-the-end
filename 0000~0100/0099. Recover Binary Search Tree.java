/* 
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
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
    private TreeNode pre = null, first = null, second = null;
    public void recoverTree(TreeNode root) {
        inOrder(root);
        swap(first, second);
    }
    
    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            if (pre == null) pre = node;
            else {
                if (pre.val > node.val) {
                    if (first == null) first = pre;
                    second = node;
                }
                pre = node;
            }
            inOrder(node.right);
        }
    }
    
    private void swap(TreeNode i, TreeNode j) {
        int temp = i.val;
        i.val = j.val;
        j.val = temp;
    }
}

// 中序遍历并重新赋值可解决任意错位
// 从设定出发仅储存两个错位元素并交换可提高效率
// 用pre储存中序遍历的前一个节点与node比较并更新
// 找出第一个错位的pre后储存并找到最后一个错位的node # 无法提前返回
