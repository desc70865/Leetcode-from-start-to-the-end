/* 
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
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
class BSTIterator {
    ListNode head, cur;
    public BSTIterator(TreeNode root) {
        head = new ListNode(-1);
        cur = head;
        dfs(root);
        cur = head;
        // cur.print();
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        cur.add(node.val);
        cur = cur.next;
        dfs(node.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        cur = cur.next;
        return cur.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur.next != null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    public void add(int newval) {
        if (this.next == null) this.next = new ListNode(newval);
        else this.next.add(newval);
    }

    public void print() {
        System.out.print(this.val);
        if (this.next != null) {
            System.out.print("->");
            this.next.print();
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */