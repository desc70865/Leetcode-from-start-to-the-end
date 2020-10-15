/* 
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4

Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
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
    public ListNode sortList(ListNode head) {
        ListNode dummyhead = new ListNode();
        dummyhead.next = head;
        quickSort(dummyhead, null);
        return dummyhead.next;
    }

    public void quickSort(ListNode head, ListNode tail) {
        if (head.next == tail) return;
        if (head.next.next == tail) return;
        ListNode pivot = head.next;
        ListNode pivotptr = pivot;
        ListNode smallhead = new ListNode();
        ListNode smallptr = smallhead;
        ListNode largehead = new ListNode();
        ListNode largeptr = largehead;
        
        ListNode cur = pivot.next;
        while (cur != tail) {
            if (cur.val < pivot.val) {
                smallptr.next = cur;
                smallptr = smallptr.next;
            } else if (cur.val > pivot.val) {
                largeptr.next = cur;
                largeptr = largeptr.next;
            } else {
                pivotptr.next = cur;
                pivotptr = pivotptr.next;
            }
            cur = cur.next;
        }
        
        largeptr.next = tail;
        pivotptr.next = largehead.next;
        smallptr.next = pivot;
        head.next = smallhead.next;
        
        quickSort(head, pivot);
        quickSort(pivotptr, tail);
        return;
    }
}

// 以头节点 pivot 为界,使用快排分治递归

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ListNode ptr = head;
        while (ptr != null) {
            minHeap.add(ptr.val);
            ptr = ptr.next;
        }
        ListNode dummy = new ListNode(0);
        dummy = head;
        while (! minHeap.isEmpty()) {
           head.next = new ListNode(minHeap.remove());
           head = head.next;
        }
        return dummy.next;
    }
}

// 优先队列, 堆排序

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val >= pre.val) {
                pre = pre.next;
                cur = cur.next;
                continue;
            }
            ListNode m = dummy;
            while (cur.val > m.next.val) m = m.next;
            pre.next = cur.next;
            cur.next = m.next;
            m.next = cur;
            cur = pre.next;
        }
        return dummy.next;
    }
}

// 非常辣鸡