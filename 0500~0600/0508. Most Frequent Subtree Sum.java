/* 
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
    Map<Integer, Integer> map;
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        map = new HashMap<>();
        dfs(root);
        int max = 0;
        List<Integer> p = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int n = entry.getValue();
            if (n < max) continue;
            if (n > max) {
                max = n;
                p.clear();
            }
            p.add(entry.getKey());
        }
        return p.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int dfs(TreeNode node) {
        int L = node.left == null ? 0 : dfs(node.left);
        int R = node.right == null ? 0 : dfs(node.right);
        int res = node.val + L + R;
        map.merge(res, 1, (a, b) -> a + b);
        return res;
    }
}