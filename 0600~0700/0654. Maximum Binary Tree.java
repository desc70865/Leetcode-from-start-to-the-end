/* 
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
    }
}



class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int l, int r) {
        if (l == r) return new TreeNode(nums[l]);
        else if (l > r) return null;
        
        int max = Integer.MIN_VALUE;
        int maxi = l;
        
        for(int i = l; i <= r; ++i) {
            if (nums[i] > max) {
                maxi = i; 
                max = nums[i];  
            }
        }
        
        TreeNode newRoot = new TreeNode(max);
        TreeNode left = helper(nums, l, maxi - 1);
        TreeNode right = helper(nums, maxi + 1, r);
        newRoot.left = left;
        newRoot.right = right;
        
        return newRoot;
    }
}