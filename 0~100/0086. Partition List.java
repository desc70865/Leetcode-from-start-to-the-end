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
        ListNode prehead = new ListNode(0);
        prehead.next = head;
        
        ListNode node = prehead;
        ListNode insnode = null;
        
        while(node.next != null) {
            if (node.next.val >= x && insnode == null) {
                insnode = node;
            } else if (node.next.val < x && insnode != null) {
                ListNode temp = node.next;
                node.next = node.next.next;
                temp.next = insnode.next;
                insnode.next = temp;
                insnode = insnode.next;
                continue;
            }
            node = node.next;            
        }
        return prehead.next;
    }
}


class Solution {
    public ListNode partition(ListNode head, int x) { 
        //小于分区点的链表
        ListNode min_head = new ListNode(0);
        ListNode min = min_head;
        //大于等于分区点的链表
        ListNode max_head = new ListNode(0);
        ListNode max = max_head;
        //遍历整个链表
        while (head != null) {  
            if (head.val < x) {
                min.next = head;
                min = min.next;
            } else { 
                max.next = head;
                max = max.next;
            }
            head = head.next;
        } 
        max.next = null;  //这步不要忘记，不然链表就出现环了
        //两个链表接起来
        min.next = max_head.next;
        return min_head.next;
    }
}