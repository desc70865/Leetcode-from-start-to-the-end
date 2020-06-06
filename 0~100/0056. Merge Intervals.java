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
        if (intervals.length == 0) return new int[0][0];
        List<int[]> ans = new ArrayList<>();
        int[] cur;
        sort(intervals, 0, intervals.length-1);
        
        cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= cur[1]) {
                if (intervals[i][1] > cur[1]) cur[1] = intervals[i][1]; // 半更新
                else continue; // 略过
            } else { // 更新
                ans.add(cur);
                cur = intervals[i];
            }
        }
        ans.add(cur);
        return ans.toArray(new int[ans.size()][]);
    }
    
    public void sort(int[][] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }
    public int partition(int[][] arr, int l, int r) {
        int[] temp = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r][0] < temp[0]) {
                    arr[l] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] > temp[0]) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = temp;
        return l;
    }
}

// 行吧,我愿称之为最强.

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x,y) -> x[0]-y[0] );
        int[][] res = new int[intervals.length][2];
        int idx = -1; // 强行 foreach
        for (int[] interval: intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}


class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, (x,y) -> x[0]-y[0] );
        List<int[]> arr = new ArrayList<int[]>();
        arr.add(intervals[0]);
        int cur;
        for (int i = 1; i < intervals.length; ++i) {
            cur = arr.size()-1; // 上一个插入元素
            if (arr.get(cur)[1] < intervals[i][0]) {
                arr.add(intervals[i]);
            } else {
                arr.get(cur)[1] = Math.max(arr.get(cur)[1], intervals[i][1]);
            }
        }
        return arr.toArray(new int[arr.size()][2]);
    }
}

// ...

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return intervals; // new int[0][0]
        sort(intervals, 0, intervals.length-1);
        List<int[]> arr = new ArrayList<int[]>();
        arr.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int cur = arr.size()-1; // 上一个插入元素
            else (arr.get(cur)[1] < intervals[i][0]) {
                arr.add(intervals[i]);
            } if {
                if (arr.get(cur)[1] < intervals[i][1])
                    arr.get(cur)[1] = intervals[i][1];
            }
        }
        return arr.toArray(new int[arr.size()][2]);
    }
    
    public void sort(int[][] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }
    public int partition(int[][] arr, int l, int r) {
        int[] temp = arr[l]; // 分治:选取一个幸运元素作为基准并最终返回其坐标
        while (l < r) {
            while (l < r) {
                if (arr[r][0] < temp[0]) {
                    arr[l] = arr[r];
                    break; // 跳出一层循环
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] > temp[0]) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = temp;
        return l;
    }
}