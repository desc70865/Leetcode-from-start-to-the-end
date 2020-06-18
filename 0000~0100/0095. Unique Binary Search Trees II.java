/* 
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

0 <= n <= 8
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

// 二叉搜索树-中序遍历
// 全排列的限制形式


class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 0) return ans;
        helper(1, n, ans);
        return ans;
    }
    private void helper(int start, int end, List<TreeNode> ans){
        if (start > end) {
            ans.add(null);
            return;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = new ArrayList<>();
            List<TreeNode> right = new ArrayList<>();
            helper(start, i-1, left);
            helper(i+1, end, right);
            for (int j=0; j < left.size(); j++) {
                for (int k=0; k < right.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    ans.add(root);
                }
            }
        }
    }
}

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) { // add null
        	res.add(null);
        	return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1), right = helper(i + 1, end);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }
}