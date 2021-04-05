/* 
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

0 <= n <= 8
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
/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/shuang-zhong-di-gui-forxun-huan-by-keylo-n9n0/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int l, int r) {
        List<TreeNode> list = new ArrayList<>();
        if (l > r) {
            list.add(null);
            return list;
        }
        for (int m = l; m <= r; m++) {
            for (TreeNode L: dfs(l, m - 1)) {
                for (TreeNode R: dfs(m + 1, r)) {
                    list.add(new TreeNode(m, L, R));
                }
            }
        }
        return list;
    }
}