/* 
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 构建一个头结点，方便连接最后结果
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 指向翻转区间第一个节点的前一个节点
        ListNode pre = dummy;
        // 指向翻转区间的第一个节点
        ListNode first = pre.next;
        // 用于遍历的当前节点指针
        ListNode cur = first;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            if (cnt == k) {
                ListNode last = cur;
                pre.next = reverseOneGroup(first, last);
                // 注意此处的first节点已经是翻转后的最后一个节点了
                pre = first;
                first = pre.next;
                cur = first;
                cnt = 0;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 用于翻转一个区间内的链表
     */
    private static ListNode reverseOneGroup(ListNode first, ListNode last) {
        while (first != last) {
            ListNode cur = first;
            first = first.next;
            cur.next = last.next;
            // last节点作为翻转后的第一个节点
            last.next = cur;
        }
        return last;
    }
/**
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    
     * 打印链表用于调试
    
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 6; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = cur.next;
        }
        printList(head);
        System.out.println("------");
        printList(reverseKGroup(head, 3));
    } */
}

// 抄了一个没用递归的循环,需要持续更新区间
// 所以说链表啊,还是麻烦