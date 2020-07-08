/* 
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
    private int postIdx = 0;
    int[] post;
    private Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder==null || postorder==null) {
            return null;
        }
        
        this.map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        this.post = postorder;
        this.postIdx = postorder.length-1;
        
        return helper(0, inorder.length-1);
    }
    
    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }
        
        int val = post[this.postIdx];
        --this.postIdx;
        TreeNode root = new TreeNode(val);
        int index = map.get(val);
        root.right = helper(index+1, right);
        root.left = helper(left, index-1);
        return root;
    }
}



class Solution {
    private int cInorder, cPostorder;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {;
        cInorder = inorder.length - 1;
	    cPostorder = postorder.length - 1;
	
	    return buildTree(inorder, postorder, null);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
        if (cPostorder < 0) {
            return null;
        }
        
        TreeNode n = new TreeNode(postorder[cPostorder--]);
        // if right node exist, create right subtree
	    if (inorder[cInorder] != n.val) {
		    n.right = buildTree(inorder, postorder, n);
	    }
        
	    cInorder--;
        
	    // if left node exist, create left subtree
	    if ((end == null) || (inorder[cInorder] != end.val)) {
		    n.left = buildTree(inorder, postorder, end);
	    }
        
	    return n;
    }
}