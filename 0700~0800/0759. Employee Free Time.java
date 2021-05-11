/* 
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
 */

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> list = new ArrayList<>();
        for (List<Interval> employee: schedule) {
            for (Interval e: employee) {
                list.add(new int[] {e.start, 1});
                list.add(new int[] {e.end, -1});
            }
        }
        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<Interval> ans = new ArrayList<>();
        int status = 0, left = -1;
        for (int[] e: list) {
            if (status == 0 && left >= 0 && e[0] > left) {
                ans.add(new Interval(left, e[0]));
            }
            status += e[1];
            left = e[0];
        }
        return ans;
    }
}



class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> list = mergeKInterval(schedule, 0, schedule.size() - 1);
        List<Interval> ans = new ArrayList<>();
        int status = 0, left = -1;
        for (int[] e: list) {
            if (status == 0 && left >= 0 && e[0] > left) {
                ans.add(new Interval(left, e[0]));
            }
            status += e[1];
            left = e[0];
        }
        return ans;
    }

    private List<int[]> mergeKInterval(List<List<Interval>> schedule, int lo, int hi) {
        if (lo == hi) {
            return extract(schedule, lo);
        }
        int mid = lo + hi >> 1;
        List<int[]> left = mergeKInterval(schedule, lo, mid);
        List<int[]> right = mergeKInterval(schedule, mid + 1, hi);
        return mergeTwoList(left, right);
    }

    private List<int[]> mergeTwoList(List<int[]> left, List<int[]> right) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0, j = 0; i < left.size() || j < right.size(); ) {
            if (j == right.size() || i < left.size() && (left.get(i)[0] < right.get(j)[0] ||
                left.get(i)[0] == right.get(j)[0] && left.get(i)[1] < right.get(j)[1])) {
                list.add(left.get(i++));
            } else {
                list.add(right.get(j++));
            }
        }
        return list;
    }

    private List<int[]> extract(List<List<Interval>> schedule, int idx) {
        List<int[]> list = new ArrayList<>();
        for (Interval e: schedule.get(idx)) {
            list.add(new int[] {e.start, 1});
            list.add(new int[] {e.end, -1});
        }
        return list;
    }
}



class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int size = 0;
        for (List<Interval> p: schedule) {
            size += p.size();
        }
        int[] left = new int[size];
        int[] right = new int[size];
        int idx = 0;
        for (List<Interval> p: schedule) {
            for (Interval e: p) {
                left[idx] = e.start;
                right[idx] = e.end;
                ++idx;
            }
        }
        Arrays.sort(left);
        Arrays.sort(right);
        List<Interval> ans = new ArrayList<>();
        for (int i = 1; i < size; ++i) {
            if (right[i - 1] < left[i]) {
                ans.add(new Interval(right[i - 1], left[i]));
            }
        }
        return ans;
    }
}