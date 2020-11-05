/* 
Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:



Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
Example 2:



Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
Example 3:

Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025
Example 4:

Input: root = [1,1]
Output: 1
 

Constraints:

Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].
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
    public int maxProduct(TreeNode root) {
        list = new ArrayList<>();
        int sum = sum(root);
        Collections.sort(list);
        int x = list.get(bs(list, sum / 2));
        return (int) ((long) x * (sum - x) % 1_000_000_007);
    }

    private int bs(List<Integer> list, int target) {
        int L = 0;
        int R = list.size() - 1;
        while (L < R) {
            int M = L + R >> 1;
            if (list.get(M) < target) L = M + 1;
            else R = M;
        }
        if (L > 0 && target - list.get(L - 1) < list.get(L) - target) return L - 1;
        return L;
    }

    private int sum(TreeNode node) {
        if (node == null) return 0;
        int L = sum(node.left);
        int R = sum(node.right);
        if (L > 0) list.add(L);
        if (R > 0) list.add(R);
        return node.val + L + R;
    }
}



class Solution {
    int sum = 0;
    int max = 0;
    public int maxProduct(TreeNode root) {
        sum = sum(root);
        sum(root);
        // return (int) (max % 1_000_000_007);
        return (int) ((long) max * (sum - max) % 1_000_000_007);
    }
    
    private int sum(TreeNode node) {
        if (node == null) return 0;
        int cur = sum(node.left) + sum(node.right) + node.val;
        if (sum != 0) {
            // max = Math.max(max, (long) cur * (sum - cur));
            if (Math.abs(cur * 2 - sum) < Math.abs(max * 2 - sum)) max = cur;
        }
        return cur;
    }
}