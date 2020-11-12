/* 
You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.

 

Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation: 
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.
 

Constraints:

1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3
 */

class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List[] list = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < colors.length; i++) {
            list[colors[i] - 1].add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] q: queries) {
            res.add(bs(list[q[1] - 1], q[0]));
        }
        return res;
    }

    private int bs(List<Integer> p, int k) {
        int len = p.size();
        if (len == 0) return -1;
        if (k <= p.get(0)) return p.get(0) - k;
        if (k >= p.get(len - 1)) return k - p.get(len - 1);
        int L = 0;
        int R = len;
        while (L < R) {
            int M = L + R >> 1;
            if (p.get(M) < k) L = M + 1;
            else R = M;
        }
        if (k == p.get(L)) return 0;
        return Math.min(k - p.get(L - 1), p.get(L) - k);
    }
}