/* 
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode backupL = new ListNode(-1);
        ListNode backupR = new ListNode(-1);
        ListNode l = backupL;
        ListNode r = backupR;
        while (head != null) {
            if (head.val < x) { l.next = head; l = l.next; }
            else { r.next = head; r = r.next; }
            head = head.next;
        }
        l.next = backupR.next;
        r.next = null;
        return backupL.next;
    }
}