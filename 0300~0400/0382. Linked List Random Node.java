/* 
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
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

    int size = 0;
    ListNode list;
    Random rnd = new Random();
    
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        list = head;
        for (; head != null; size++, head = head.next);
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int idx = rnd.nextInt(size);
        ListNode node = list;
        for (; idx > 0; node = node.next, idx--);
        return node.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */



class Solution {
    ListNode root;
    Random random = new Random();
    public Solution(ListNode head) {
         this.root = head;
    }
    
    public int getRandom() {
        ListNode head = root.next;
        int res = root.val, count = 1;
        while (head != null) {
            count++;
            int i = random.nextInt(count);
            if (i < 1) {
                res = head.val;
            }
            head = head.next;
        }
        return res;
    }
}