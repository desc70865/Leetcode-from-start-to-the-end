/* 
You are given m arrays, where each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|. Your task is to find the maximum distance.

 

Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:

Input: arrays = [[1],[1]]
Output: 0
Example 3:

Input: arrays = [[1],[2]]
Output: 1
Example 4:

Input: arrays = [[1,4],[0,5]]
Output: 4
 

Constraints:

m == arrays.length
2 <= m <= 104
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
 */

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int len = arrays.size();
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            cnt[i][0] = arrays.get(i).get(0);
            int curLen = arrays.get(i).size();
            cnt[i][1] = arrays.get(i).get(curLen - 1);
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                max = Math.max(max, abs(cnt[i][0], cnt[j][1]));
            }
        }
        return max;
    }

    private int abs(int a, int b) {
        return Math.abs(a - b);
    }
}



class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0;
        for (int i = 0, minv = 10000, maxv = -10000; i < arrays.size(); i++) {
            List<Integer> cur = arrays.get(i);
            int n = cur.size();
            res = Math.max(res, cur.get(n - 1) - minv);
            res = Math.max(res, maxv - cur.get(0));
            minv = Math.min(minv, cur.get(0));
            maxv = Math.max(maxv, cur.get(n - 1));
        }
        return res;
    }
}