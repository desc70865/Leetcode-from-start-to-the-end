/* 
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:

 

Note:

1 <= N <= 20
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
    Map<Integer, List<TreeNode>> memo = new HashMap();
    public List<TreeNode> allPossibleFBT(int N) {
        if (! memo.containsKey(N)) {
            List<TreeNode> p = new LinkedList<>();
            if (N == 1) {
                p.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int x = 1; x < N; x += 2) {
                    int y = N - 1 - x;
                    for (TreeNode left: allPossibleFBT(x)) {
                        for (TreeNode right: allPossibleFBT(y)) {
                            TreeNode t = new TreeNode(0);
                            t.left = left;
                            t.right = right;
                            p.add(t);
                        }
                    }
                }
            }
            memo.put(N, p);
        }
        return memo.get(N);
    }
}