/* 
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) return 0;
        sort(intervals, 0, len - 1);
        // Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int end = intervals[0][1];
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] >= end) end = intervals[i][1];
            else ans++;
        }
        return ans;
    }

    private void sort(int[][] arr, int l, int r) {
        if (l > r) return;
        int pivot = partition(arr, l, r);
        sort(arr, l, pivot - 1);
        sort(arr, pivot + 1, r);
    }

    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r][1] < pivot[1]) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][1] > pivot[1]) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }
}