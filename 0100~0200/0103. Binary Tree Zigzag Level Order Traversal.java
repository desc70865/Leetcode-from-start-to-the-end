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
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> cur = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int count = 1;
        boolean flag = true;
        while (nodes.size() > 0) {
            TreeNode node = nodes.remove(0);
            count--;
            if (node.left != null) nodes.add(node.left);
            if (node.right != null) nodes.add(node.right);
            if (flag) cur.add(node.val);
            else cur.add(0, node.val);
            if (count == 0) {
                count = nodes.size();
                res.add(new ArrayList<>(cur));
                cur = new ArrayList<>();
                flag = !flag;
            }
        }
        return res;
    }
}

class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) return res;
        levelTraversal(root, 1, true);
        return res;
    }
    private void levelTraversal(TreeNode node, int level, boolean flag) {
        if (node == null) return;
        if (res.size() < level) res.add(new ArrayList<Integer>());
        if (flag) res.get(level-1).add(node.val);
        else res.get(level-1).add(0,node.val);
        levelTraversal(node.left, level+1, !flag);
        levelTraversal(node.right, level+1, !flag);
    }
}