/* 
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length, cur = 0;
        while (cur < n && intervals[cur][1] < newInterval[0]) {
            res.add(intervals[cur++]);
        }
        while (cur < n && intervals[cur][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[cur][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[cur][1]);
            ++cur;
        }
        res.add(newInterval);
        while (cur < n) {
            res.add(intervals[cur++]);
        }
        return res.toArray(new int[res.size()][2]);
    }
}