/* 
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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
    private List<List<Integer>> res;
    public List<List<Integer>> levelOrder(TreeNode root) {
        res = new ArrayList<>();
        levelorder(root, 0);
        return res;
    }
    
    private void levelorder(TreeNode node, int level) {
        if (node == null) return;
        if (res.size() == level) res.add(null);
        // res.get(level).add(node.val); // ???
        if (node.left != null) levelorder(node.left, level + 1);
        if (node.right != null) levelorder(node.right, level + 1);
    }
}

// 队列存储节点,标记层数

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        TreeNode cur;
        while (!q.isEmpty()) {
            List<Integer> subAns = new LinkedList<Integer>();
            for (int i = 0; i < q.size(); ++i) {
                cur = q.poll();
                subAns.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            ans.add(subAns);
        }
        return ans;
    }
}