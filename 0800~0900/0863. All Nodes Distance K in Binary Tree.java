/* 
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) return new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        dfs(root, null, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target.val);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        int distance = -1;
        List<Integer> res = new ArrayList<>();
        while (! queue.isEmpty() && ++distance <= K) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (distance == K) res.add(cur);
                for (int neighbor: graph.get(cur)) {
                    if (visited.add(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }
    
    private void dfs(TreeNode node, TreeNode pre, Map<Integer, List<Integer>> graph) {
        if (node == null) return;
        graph.put(node.val, new ArrayList<>());
        if (pre != null) {
            graph.get(node.val).add(pre.val);
            graph.get(pre.val).add(node.val);
        }
        dfs(node.left, node, graph);
        dfs(node.right, node, graph);
    }
}