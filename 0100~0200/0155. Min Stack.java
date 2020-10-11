/* 
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

Methods pop, top and getMin operations will always be called on non-empty stacks.
 */

class MinStack {
    Stack<Integer> min, p;
    /** initialize your data structure here. */
    public MinStack() {
        min = new Stack<>();
        p = new Stack<>();
    }
    
    public void push(int x) {
        p.push(x);
        if (min.isEmpty() || x <= getMin()) min.push(x);
    }
    
    public void pop() {
        // if compare two Integer directly
        // you must use equals
        if (top() == getMin()) min.pop();
        p.pop();
    }
    
    public int top() {
        return p.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


class MinStack {

    /** initialize your data structure here. */
    Stack<int[]> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
        } else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}



class MinStack {
    Node head;
    int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        head = new Node();
    }
    
    public void push(int x) {
        Node node = new Node(x);
        min = Math.min(x, min);
        node.min = min;
        node.next = head;
        head = node;
    }
    
    public void pop() {
        head = head.next;
        min = head.min;
    }
    
    public int top() {
        return head.value;
    }
    
    public int getMin() {
        return head.min;
    }
}

class Node {
    int value;
    int min = Integer.MAX_VALUE;
    Node next;
    public Node() {
        
    }
    public Node(int num){
        this.value = num;
    }
}