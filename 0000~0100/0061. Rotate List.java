/* 
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        int n = 1;
        while (cur.next != null) {
            ++n;
            cur = cur.next;
        }
        int m = n - k % n;
        cur.next = head;
        for (int i = 0; i < m; ++i) {
            cur = cur.next;
        }
        ListNode newlist = cur.next;
        cur.next = null;
        return newlist;
    }
}

// 找到链接的倒序第k个节点
// 因为只能正向遍历,因此至少需要遍历 l + l - k 个节点
// 应该可以加入 k == n 的判定减少某些情况至 l

class Solution {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int len = 1;// since p is already point to head
        while (p.next != null) {
            len++;
            p = p.next;
        }
        p.next = head; // form a loop
        k = k % len;
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < len - k; i++) {
            p = p.next;
        } // now p points to the prev of the new head
        head = p.next;
        p.next = null;
        return head;
    }
}