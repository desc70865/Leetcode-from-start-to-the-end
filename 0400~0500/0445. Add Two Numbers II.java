/* 
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> p1 = helper(l1);
        Stack<Integer> p2 = helper(l2);
        Stack<Integer> p3 = new Stack<>();
        int c = 0, sum = 0;
        while (! p1.isEmpty() || ! p2.isEmpty()) {
            int a = p1.isEmpty() ? 0 : p1.pop();
            int b = p2.isEmpty() ? 0 : p2.pop();
            sum = a + b + c;
            p3.push(sum % 10);
            c = sum / 10;
        }
        if (c != 0) p3.push(c);
        ListNode p = new ListNode(-1);
        ListNode cur = p;
        while (! p3.isEmpty()) {
            cur.next = new ListNode(p3.pop());
            cur = cur.next;
        }
        return p.next;
    }

    private Stack<Integer> helper(ListNode l) {
        Stack<Integer> res = new Stack<>();
        while (l != null) {
            res.push(l.val);
            l = l.next;
        }
        return res;
    }
}