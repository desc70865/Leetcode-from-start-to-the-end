/* 
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 

Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]
 

Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
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
    int len;
    int idx = -1;

    public TreeNode str2tree(String s) {
        len = s.length();
        if (len == 0) return null;
        return dfs(s.toCharArray());
    }

    private TreeNode dfs(char[] chs) {
        boolean neg = false;
        if (chs[++idx] == '-') neg = true;
        else idx--;
        int cur = chs[++idx] - '0';
        while (++idx < len && chs[idx] >= '0') {
            cur *= 10;
            cur += chs[idx] - '0';
        }
        if (neg) cur *= -1;
        TreeNode node = new TreeNode(cur);
        if (idx < len && chs[idx] == '(') {
            node.left = dfs(chs);
            if (idx < len && chs[idx] == '(') {
                node.right = dfs(chs);
            }
        }
        idx++;
        return node;
    }
}