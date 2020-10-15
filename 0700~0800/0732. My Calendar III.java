/* 
Implement a MyCalendarThree class to store your events. A new event can always be added.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)

For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.

Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
Example 1:

MyCalendarThree();
MyCalendarThree.book(10, 20); // returns 1
MyCalendarThree.book(50, 60); // returns 1
MyCalendarThree.book(10, 40); // returns 2
MyCalendarThree.book(5, 15); // returns 3
MyCalendarThree.book(5, 10); // returns 3
MyCalendarThree.book(25, 55); // returns 3
Explanation: 
The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
The remaining events cause the maximum K-booking to be only a 3-booking.
Note that the last event locally causes a 2-booking, but the answer is still 3 because
eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 

Note:

The number of calls to MyCalendarThree.book per test case will be at most 400.
In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].
 */

class MyCalendarThree {
    TreeNode root;

    public MyCalendarThree() {
        this.root = new TreeNode(0, 1_000_000_000);
    }

    public int book(int start, int end) {
        return root.insert(start, end);
    }
}

class TreeNode {
    int start, end;
    int maxTime;
    int delayTime;
    TreeNode left, right;

    TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private int getMid() {
        return (start + end) / 2;
    }

    private TreeNode left() {
        if (this.left == null) this.left = new TreeNode(start, getMid());
        return this.left;
    }

    private TreeNode right() {
        if (this.right == null) this.right = new TreeNode(getMid(), end);
        return this.right;
    }

    public int insert(int s, int e) {
        // 包含，更新区间
        if (this.start >= s && this.end <= e) {
            this.delayTime++;
            this.maxTime++;
        }
        // 相交
        else if (this.end > s && this.start < e) {
            // 自上向下延迟更新
            this.left().maxTime += this.delayTime;
            this.left().delayTime += this.delayTime;
            this.right().maxTime += this.delayTime;
            this.right().delayTime += this.delayTime;
            // 延迟更新完成，清空
            this.delayTime = 0;

            // 自下向上更新最大次数
            this.maxTime = Math.max(this.maxTime, Math.max(this.left().insert(s, e), this.right().insert(s, e)));
        }
        return this.maxTime;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */

// nonesense

class MyCalendarThree {
    TreeMap<Integer, Integer> delta;

    public MyCalendarThree() {
        delta = new TreeMap();
    }

    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);
        int active = 0, ans = 0;
        for (int d: delta.values()) {
            active += d;
            if (active > ans) ans = active;
        }
        return ans;
    }
}