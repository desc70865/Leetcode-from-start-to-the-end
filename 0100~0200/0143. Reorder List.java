/* 
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        int N = 0;
        while (node != null) {
            stack.push(node);
            node = node.next;
            N++;
        }
        ListNode tmp = head;
        for (int i = 0; i < N/2; i++) {
            tmp = head.next;
            head.next = stack.pop();
            head.next.next = tmp;
            head = tmp;
        }
        head.next = null;
    }
}

// 递归-隐式用栈

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        
        helper(null, head, head);
    }
    
    private ListNode helper (ListNode prev, ListNode slow, ListNode fast) {
        if (fast == null) {
            prev.next =  null;
            return slow;
        }
        
        if (fast.next == null) {
            ListNode tmp = slow.next;
            slow.next = null;
            return tmp;
        }
        
        ListNode p = helper(slow, slow.next, fast.next.next);
        ListNode tmp = p.next;
        p.next = slow.next;
        slow.next = p;
        
        return tmp;
    }
}