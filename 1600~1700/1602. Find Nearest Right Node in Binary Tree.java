/* 
Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.

 

Example 1:



Input: root = [1,2,3,null,4,5,6], u = 4
Output: 5
Explanation: The nearest node on the same level to the right of node 4 is node 5.
Example 2:



Input: root = [3,null,4,2], u = 2
Output: null
Explanation: There are no nodes to the right of 2.
Example 3:

Input: root = [1], u = 1
Output: null
Example 4:

Input: root = [3,4,2,null,null,null,1], u = 4
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
All values in the tree are distinct.
u is a node in the binary tree rooted at root.
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
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == u) {
                    return size == 0 ? null : queue.poll();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return null;
    }
}



class Solution {
    int depth = 0;
    TreeNode ans;

    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        dfs(root, u, 1);
        return ans;
    }

    private void dfs(TreeNode node, TreeNode u, int d) {
        if (node == null || ans != null) {
            return;
        }
        if (d == depth) {
            ans = node;
            return;
        }
        if (node == u) {
            depth = d;
            return;
        }
        dfs(node.left, u, d + 1);
        dfs(node.right, u, d + 1);
    }
}