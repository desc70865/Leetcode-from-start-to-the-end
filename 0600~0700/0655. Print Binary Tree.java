/* 
Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
Note: The height of binary tree is in the range of [1, 10].
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
    int m = 0, n = 0;
    String[][] aux;
    public List<List<String>> printTree(TreeNode root) {
        dig(root, 1);
        m = (1 << n) - 1;
        aux = new String[n][m];
        fill(root, 0, m / 2);
        String empty = "";

        List<List<String>> res = new ArrayList<>();
        for (String[] s: aux) {
            List<String> p = new ArrayList<>();
            for (String t: s) {
                if (t == null) p.add(empty);
                else p.add(t);
            }
            res.add(new ArrayList<>(p));
        }
        return res;
    }
    
    private void fill(TreeNode node, int p, int idx) {
        if (node == null) return;
        aux[p][idx] = String.valueOf(node.val);
        int x = 1 << n - 2 - p;
        fill(node.left, p + 1, idx - x);
        fill(node.right, p + 1, idx + x);
    }
    
    private void dig(TreeNode node, int depth) {
        if (node == null) return;
        n = Math.max(n, depth);
        dig(node.left, depth + 1);
        dig(node.right, depth + 1);
    }
}