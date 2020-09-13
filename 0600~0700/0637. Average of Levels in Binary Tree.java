/* 
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
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
    double[][] aux;
    int p;
    public List<Double> averageOfLevels(TreeNode root) {
        aux = new double[1000][2];
        p = -1;
        dfs(root, 0);

        List<Double> res = new ArrayList<>();
        for (int i = 0; i <= p; i++) res.add(aux[i][1] / aux[i][0]);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        aux[depth][0] += 1;
        aux[depth][1] += node.val;
        p = Math.max(p, depth);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}