/* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        int max = 0;
        for (int[] p: intervals) {
            max = Math.max(p[1], max);
        }
        int[] room = new int[max + 1];
        for (int[] p: intervals) {
            room[p[0]]++;
            room[p[1]]--;
        }
        int re = 1;
        for (int i = 1; i < max; i++) {
            room[i] += room[i - 1];
            re = Math.max(room[i], re);
        }
        return re;
    }
}



class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }
}



class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int count = 0;
        int endIdx = 0;
        for (int start: starts) {
            if (start < ends[endIdx])
                count++;
            else
                endIdx++;
        }
        return count;
    }
}