/* 
You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

Return the maximum sum of values that you can receive by attending events.

 

Example 1:



Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
Output: 7
Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
Example 2:



Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
Output: 10
Explanation: Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
Example 3:



Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
Output: 9
Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 

Constraints:

1 <= k <= events.length
1 <= k * events.length <= 106
1 <= startDayi <= endDayi <= 109
1 <= valuei <= 106
 */

class Solution {
    int[][] events, f;

    public int maxValue(int[][] events, int k) {
        if (k == 1) {
            int max = 0;
            for (int[] e: events) {
                max = Math.max(e[2], max);
            }
            return max;
        }
        int n = events.length;
        // Arrays.sort(events, (a, b) -> a[1] - b[1]);
        quickSort(events, 0, n - 1);
        this.events = events;
        this.f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            int[] e = events[i - 1];
            int pre = bs(i - 1, e[0]);
            for (int j = 1; j <= k; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[pre][j - 1] + e[2]);
            }
        }
        return f[n][k];
    }

    private int bs(int R, int start) {
        for (int L = 1; L < R;) {
            int M = L + R + 1 >> 1;
            if (events[M - 1][1] < start) {
                L = M;
            } else {
                R = M - 1;
            }
        }
        return R > 0 && events[R - 1][1] < start ? R : 0;
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
        return a[1] < b[1];
    }
}