/* 
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int L = intervals[0][0];
        int R = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        for (int[] p: intervals) {
            if (p[0] > R) {
                res.add(new int[] {L, R});
                L = p[0];
            }
            R = Math.max(R, p[1]);
        }
        res.add(new int[] {L, R});
        return res.toArray(new int[0][2]);
    }
}



class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return intervals; 
        sort(intervals, 0, intervals.length - 1);
        List<int[]> arr = new ArrayList<int[]>();
        arr.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int pre = arr.size() - 1;
            if (arr.get(pre)[1] < intervals[i][0]) {
                arr.add(intervals[i]);
            } else if (arr.get(pre)[1] < intervals[i][1]) {
                arr.get(pre)[1] = intervals[i][1];
            }
        }
        return arr.toArray(new int[0][2]);
    }
    
    public void sort(int[][] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }
    
    public int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r][0] < pivot[0]) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] > pivot[0]) {
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