/* 
Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
 

Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */

class MyCalendar {
    private TreeMap<Integer, Integer> tree;

    public MyCalendar() {
        tree = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if (start >= end) {
            return false;
        }
        Integer low = tree.floorKey(start);
        if (low != null && tree.get(low) > start) {
            return false;
        }
        Integer high = tree.ceilingKey(start);
        if (high != null && high < end) {
            return false;
        }
        tree.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */


class MyCalendar {
    List<Integer> L;
    List<Integer> R;
    int len;

    public MyCalendar() {
        L = new ArrayList<>();
        R = new ArrayList<>();
        len = 0;
    }
    
    public boolean book(int start, int end) {
        if (len == 0 || end <= L.get(0)) {
            return insert(0, start, end);
        }
        if (R.get(len - 1) <= start) {
            return insert(len, start, end);
        }
        int idx = bs(start);
        // System.out.println(idx);
        if (idx > 0 && R.get(idx - 1) > start) return false;
        if (L.get(idx) < end) return false;
        L.add(idx, start);
        R.add(idx, end);
        len++;
        return true;
    }

    private boolean insert(int idx, int start, int end) {
        L.add(idx, start);
        R.add(idx, end);
        len++;
        return true;
    }

    private int bs(int start) {
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (L.get(mid) < start) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}