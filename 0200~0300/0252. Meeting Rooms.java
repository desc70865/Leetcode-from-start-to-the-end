/* 
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106
 */

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }
        return true;
    }
}



class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        int max = 0;
        int[] in = new int[1000001];
        for (int[] p: intervals) {
            in[p[0]]++;
            in[p[1]]--;
            max = Math.max(max, p[1]);
        }
        // System.out.println(Arrays.toString(in));
        for (int i = 1; i <= max; i++) {
            in[i] += in[i - 1];
            if (in[i] > 1) return false;
        }
        return true;
    }
}