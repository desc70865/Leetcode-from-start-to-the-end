/* 
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:



Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
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
    Map<Integer, Integer> map = new HashMap<>();
    
    public int maxLevelSum(TreeNode root) {
        dfs(root, 1);
        int max = Integer.MIN_VALUE;
        int level = -1;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                level = entry.getKey();
            }
        }
        return level;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node == null) return ;
        map.merge(depth, node.val, (a, b) -> a + b);
        dfs(node.left, depth+1);
        dfs(node.right, depth+1);
    }
}



class Solution {
    int[] cnt = new int[1001];
    int maxDepth = -1;
    
    public int maxLevelSum(TreeNode root) {
        dfs(root, 1);
        int max = Integer.MIN_VALUE;
        int level = -1;
        for (int i = 1; i <= maxDepth; i++) {
            if (cnt[i] > max) {
                max = cnt[i];
                level = i;
            }
        }
        return level;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node == null) return ;
        cnt[depth] += node.val;
        maxDepth = Math.max(maxDepth, depth);
        dfs(node.left, depth+1);
        dfs(node.right, depth+1);
    }
}