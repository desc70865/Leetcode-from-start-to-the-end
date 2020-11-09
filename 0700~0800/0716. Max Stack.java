/* 
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */

class MaxStack {
    private class Node {
        int val;
        Node max;
        Node prev;
        Node next;

        Node(int x) {
            this.val = x;
        }
    }

    private Node head;

    private void remove(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

    /** initialize your data structure here. */
    public MaxStack() {
        
    }
    
    public void push(int x) {
        Node node = new Node(x);
        if (head == null) {
            node.max = node;
            head = node;
        } else {
            node.max = (x >= head.max.val ? node : head.max);
            head.prev = node;
            node.next = head;
            head = node;
        }
    }
    
    public int pop() {
        Node node = head;
        head = node.next;
        remove(node);
        return node.val;
    }
    
    public int top() {
        return head.val;
    }
    
    public int peekMax() {
        return head.max.val;
    }
    
    public int popMax() {
        if (head == head.max)
            return pop();
        
        int max = head.max.val;
        Node node = head.max.prev;
        remove(head.max);
        
        while (node != null) {
            if (node.next == null)
                node.max = node;
            else
                node.max = (node.val >= node.next.max.val ? node : node.next.max);
            node = node.prev;
        }
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */