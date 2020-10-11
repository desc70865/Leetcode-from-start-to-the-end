/* 
Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

 

Example 1:


Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
Example 2:


Input: root = [5,3,6,2,4,null,7], k = 28
Output: false
Example 3:

Input: root = [2,1,3], k = 4
Output: true
Example 4:

Input: root = [2,1,3], k = 1
Output: false
Example 5:

Input: root = [2,1,3], k = 3
Output: true
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-104 <= Node.val <= 104
root is guaranteed to be a valid binary search tree.
-105 <= k <= 105
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
    public boolean findTarget(TreeNode root, int k) {
        p = new ArrayList<>();
        dfs(root);
        int[] arr = p.stream().mapToInt(Integer::valueOf).toArray();
        int l = 0, r = arr.length - 1;
        while (l < r) {
            if (arr[l] + arr[r] > k) r--;
            else if (arr[l] + arr[r] < k) l++;
            else return true;
        }
        return false;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        p.add(node.val);
        dfs(node.right);
    }
}