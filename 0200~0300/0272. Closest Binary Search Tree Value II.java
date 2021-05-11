/* 
Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.

You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

 

Example 1:


Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]
Example 2:

Input: root = [1], target = 0.000000, k = 1
Output: [1]
 

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104.
0 <= Node.val <= 109
-109 <= target <= 109
 

Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
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
    Deque<Integer> queue = new ArrayDeque<>();
    double target;
    int k;
    boolean EOF = false;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        this.target = target;
        this.k = k;
        dfs(root);
        return new ArrayList<>(queue);
    }

    private void dfs(TreeNode node) {
        if (node == null || EOF) {
            return;
        }
        dfs(node.left);
        if (queue.size() < k) {
            queue.offer(node.val);
        } else if (choose(node.val)) {
            queue.poll();
            queue.offer(node.val);
        } else {
            EOF = true;
            return;
        }
        dfs(node.right);
    }

    private boolean choose(int v) {
        return Math.abs(target - v) < Math.abs(target - queue.peek());
    }
}