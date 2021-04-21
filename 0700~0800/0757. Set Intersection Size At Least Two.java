/* 
An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has a size of at least two.

 

Example 1:

Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
Output: 3
Explanation: Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
Also, there isn't a smaller size set that fulfills the above condition.
Thus, we output the size of this set, which is 3.
Example 2:

Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
Output: 5
Explanation: An example of a minimum sized set is {1, 2, 3, 4, 5}.
 

Constraints:

1 <= intervals.length <= 3000
intervals[i].length == 2
0 <= ai < bi <= 108
 */

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        quickSort(intervals, 0, intervals.length - 1);
        int R = intervals[0][1], L = R - 1;
        int ans = 2;
        for (int[] t: intervals) {
            if (t[0] <= L) {
                continue;
            } else if (t[0] <= R) {
                ans++;
                L = R;
                R = t[1];
            } else {
                ans += 2;
                R = t[1];
                L = R - 1;
            }
        }
        return ans;
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
        return a[1] == b[1] ? b[0] < a[0] : a[1] < b[1];
    }
}

// 打气球的升级版，每个区间需要命中两次
// 维护两次命中的坐标，结合排序，实现贪心