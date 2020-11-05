/* 
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean order = false;
        List<List<Integer>> res = new ArrayList<>();
        while (! queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                level.add(cur.val);
            }
            if (order) Collections.reverse(level);
            res.add(level);
            order = ! order;
        }
        return res;
    }
}



class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) return res;
        levelTraversal(root, 0, true);
        return res;
    }
    
    private void levelTraversal(TreeNode node, int level, boolean order) {
        if (node == null) return;
        if (res.size() <= level) res.add(new ArrayList<Integer>());
        if (order) res.get(level).add(node.val);
        else res.get(level).add(0, node.val);
        levelTraversal(node.left, level + 1, ! order);
        levelTraversal(node.right, level + 1, ! order);
    }
}