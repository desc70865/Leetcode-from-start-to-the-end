/* 
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
    public int pathSum(TreeNode root, int sum) {
        
    }
}



class Solution {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        helper(root, sum);
        return res;
    }
    
    private int[] helper(TreeNode node, int p) {
        if (node == null) return new int[0];
        int[] a = helper(node.left, p), b = helper(node.right, p), c = new int[a.length + b.length + 1];
        int i = 1, k = node.val;
        c[0] = k;
        for (int n: a) c[i++] = n + k;
        for (int n: b) c[i++] = n + k;
        for (int n: c) if (p == n) res++;
        return c;
    }
}



class Solution {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> presum = new HashMap<>();
        presum.put(0, 1);
        return calcSum(0, sum, root, presum);
    }
    
    private int calcSum(int currSum, int targetSum, TreeNode root, HashMap<Integer, Integer> presum){
        if (root == null) return 0;
        currSum += root.val;
        int res = presum.getOrDefault(currSum-targetSum, 0);
        presum.put(currSum, presum.getOrDefault(currSum ,0)+1);
        res += calcSum(currSum, targetSum, root.left, presum) + calcSum(currSum, targetSum, root.right, presum);
        presum.put(currSum, presum.get(currSum)-1);
        return res;
    }
}