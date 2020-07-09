/* 
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
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
    private List<List<Integer>> list;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.list = new ArrayList<>();
        dfs(new ArrayList<Integer>(), root, sum);
        return list;
    }
    
    private void dfs(List<Integer> tmp, TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        
        tmp.add(node.val);
        sum -= node.val;
        
        if (node.left == null && node.right == null && 0 == sum) {
            list.add(new ArrayList<>(tmp));
        } else {
            dfs(tmp, node.left, sum);
            dfs(tmp, node.right, sum);
        }
        
        tmp.remove(tmp.size()-1);
        return;
    }
}

// ???

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return addPath(root, sum, new ArrayList<>(), new int[height(root)], 0);
    }
    
    int height(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(height(root.left), height(root.right));
    }
    
    List<List<Integer>> addPath(TreeNode root, int sum, List<List<Integer>> result, int[] path, int level) {
        if (root == null) {
            return result;
        }
        
        sum -= root.val;
        path[level++] = root.val;
        
        if (root.left == null && root.right == null && sum == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                list.add(path[i]);
            }
            result.add(list);
        }
        addPath(root.left, sum, result, path, level);
        addPath(root.right, sum, result, path, level);
        
        return result;
    }
}