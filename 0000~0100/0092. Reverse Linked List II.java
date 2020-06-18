/* 
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1.2.3.4.5.NULL, m = 2, n = 4
Output: 1.4.3.2.5.NULL
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        for (int i = 0; i < m - 1; ++i) pre = pre.next;
        ListNode cur = pre.next;
        for (int i = m; i < n; ++i) {
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = pre.next;
            pre.next = t;
        }
        return dummy.next;
    }
}