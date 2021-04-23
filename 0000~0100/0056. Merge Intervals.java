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
        // Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        quickSort(intervals, 0, intervals.length - 1);
        int L = intervals[0][0], R = intervals[0][1];
        List<int[]> list= new ArrayList<>();
        for (int[] t: intervals) {
            if (t[0] > R) {
                list.add(new int[] {L, R});
                L = t[0];
            }
            R = Math.max(t[1], R);
        }
        list.add(new int[] {L, R});
        return list.toArray(new int[0][2]);
    }

    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (cmp(arr[r], pivot)) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (cmp(pivot, arr[l])) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }

    private boolean cmp(int[] a, int[] b) {
        return a[0] == b[0] ? a[1] < b[1] : a[0] < b[0];
    }
}