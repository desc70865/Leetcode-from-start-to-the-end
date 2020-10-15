/* 
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
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
    private int[] pre;
    private Map<Integer, Integer> map;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        map = new HashMap<>();
        for (int i = 0; i < post.length; i++) map.put(post[i], i);
        return build(0, pre.length - 1, 0);
    }
    /**
     * 根据前序后序构建树
     * @param begin 前序的起点下标
     * @param end 前序的终点下标
     * @param postBegin 后序的起点下标
     * @return 返回构建的树
     */
    private TreeNode build(int begin, int end, int postBegin) {
        if (begin > end) return null;
        TreeNode root = new TreeNode(pre[begin]); //前序第一个结点就是当前根结点
        if (begin < end) { // 若还有子结点
            int leftv = pre[begin + 1]; // 默认一定有左子树，左子树根结点下标即begin + 1
            int leftcnt = map.get(leftv) - postBegin + 1; // 计算左子树结点数
            root.left = build(begin + 1, begin + leftcnt, postBegin); // 递归构建子树
            root.right = build(begin + leftcnt + 1, end, postBegin + leftcnt);
        }
        return root;
    }
}