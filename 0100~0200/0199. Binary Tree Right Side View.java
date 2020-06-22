/* 
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
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
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        List<Integer> rightView = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));
        int max_depth = -1;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int depth = pair.getValue();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);
                rightmostValueAtDepth.put(depth, node.val);
                
                queue.add(new Pair<>(node.left, depth+1));
                queue.add(new Pair<>(node.right, depth+1));
            }
        }

        for (int depth=0; depth <= max_depth; depth++)
            rightView.add(rightmostValueAtDepth.get(depth));

        return rightView;
    }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> result) {
        if (level == result.size()) result.add(node.val);
        if (node.right != null) dfs(node.right, level+1, result);
        if (node.left != null) dfs(node.left, level+1, result);
    }
}