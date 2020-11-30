/* 
Design a queue that supports push and pop operations in the front, middle, and back.

Implement the FrontMiddleBack class:

FrontMiddleBack() Initializes the queue.
void pushFront(int val) Adds val to the front of the queue.
void pushMiddle(int val) Adds val to the middle of the queue.
void pushBack(int val) Adds val to the back of the queue.
int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:

Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 

Example 1:

Input:
["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
[[], [1], [2], [3], [4], [], [], [], [], []]
Output:
[null, null, null, null, null, 1, 3, 4, 2, -1]

Explanation:
FrontMiddleBackQueue q = new FrontMiddleBackQueue();
q.pushFront(1);   // [1]
q.pushBack(2);    // [1, 2]
q.pushMiddle(3);  // [1, 3, 2]
q.pushMiddle(4);  // [1, 4, 3, 2]
q.popFront();     // return 1 -> [4, 3, 2]
q.popMiddle();    // return 3 -> [4, 2]
q.popMiddle();    // return 4 -> [2]
q.popBack();      // return 2 -> []
q.popFront();     // return -1 -> [] (The queue is empty)
 

Constraints:

1 <= val <= 109
At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
 */

class FrontMiddleBackQueue {
    Deque<Integer> head, tail;
    
    private void print() {
        print(head);
        print(tail);
    }
    
    private void print(Deque<Integer> q) {
        for (int v: q) {
            System.out.print(v + " ");
        }
    }

    public FrontMiddleBackQueue() {
        head = new LinkedList<>();
        tail = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        head.offerFirst(val);
    }
    
    public void pushMiddle(int val) {
        this.getBalance();
        head.offerLast(val);
        // System.out.println(head.size());
        // System.out.println(tail.size());
        // print();
        // System.out.println();
    }
    
    public void pushBack(int val) {
        tail.offerLast(val);
    }
    
    public int popFront() {
        if (isEmpty()) return -1;
        if (head.isEmpty()) {
            return tail.pollFirst();
        } else {
            return head.pollFirst();
        }
    }
    
    public int popMiddle() {
        if (isEmpty()) return -1;
        if (this.getBalance()) {
            return head.pollLast();
        } else {
            return tail.pollFirst();
        }
    }
    
    public int popBack() {
        if (isEmpty()) return -1;
        if (tail.isEmpty()) {
            return head.pollLast();
        } else {
            return tail.pollLast();
        }
    }
    
    private boolean isEmpty() {
        return head.isEmpty() && tail.isEmpty();
    }
    
    private boolean getBalance() {
        int diff = head.size() - tail.size();
        while (diff > 0) {
            tail.offerFirst(head.pollLast());
            diff -= 2;
        }
        while (diff < -1) {
            head.offerLast(tail.pollFirst());
            diff += 2;
        }
        return diff == 0;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */