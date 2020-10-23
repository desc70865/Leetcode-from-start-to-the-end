/* 
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        Deque<Integer> stack = new LinkedList<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next;
        while (! stack.isEmpty()) {
            if (slow.val != stack.pop()) return false;
            slow = slow.next;
        }
        return true;
    }
}

// stack 储存前半段

class Solution {
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}

// 

class Solution {

    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }        

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // 0092
    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}



class Solution {
    public boolean isPalindrome(ListNode head) {
        //if (head == null || head.next == null) return true;
        
        byte leftSize = 0;
        ListNode cur = head;
        while (cur != null) {
            leftSize++;
            cur = cur.next;
        }
        boolean odd = false;
        if (leftSize % 2 == 1) {
            odd = true;
        } 
        leftSize /= 2;
        
        byte rightSize = odd ? (byte)(leftSize+1) : leftSize;
        
        ListNode right = head;
        for (byte i = 0; i < rightSize; i++) {
            right = right.next;
        }
        
        cur = head;
        ListNode prv = null;
        for (byte i = 0; i < leftSize; i++) {
            ListNode nextTemp = cur.next;
            cur.next = prv;
            prv = cur;
            cur = nextTemp;
        }
        
        
        while (right != null && prv != null) {
            if (right.val != prv.val) return false;
            right = right.next;
            prv = prv.next;
        }
        
        return true;
    }
}