/* 
There is a 2D grid of size n x n where each cell of this grid has a lamp that is initially turned off.

You are given a 2D array of lamp positions lamps, where lamps[i] = [rowi, coli] indicates that the lamp at grid[rowi][coli] is turned on. Even if the same lamp is listed more than once, it is turned on.

When a lamp is turned on, it illuminates its cell and all other cells in the same row, column, or diagonal.

You are also given another 2D array queries, where queries[j] = [rowj, colj]. For the jth query, determine whether grid[rowj][colj] is illuminated or not. After answering the jth query, turn off the lamp at grid[rowj][colj] and its 8 adjacent lamps if they exist. A lamp is adjacent if its cell shares either a side or corner with grid[rowj][colj].

Return an array of integers ans, where ans[j] should be 1 if the cell in the jth query was illuminated, or 0 if the lamp was not.

 

Example 1:


Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
Output: [1,0]
Explanation: We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
The 0th query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.

The 1st query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.

Example 2:

Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
Output: [1,1]
Example 3:

Input: n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
Output: [1,1,0]
 

Constraints:

1 <= n <= 109
0 <= lamps.length <= 20000
0 <= queries.length <= 20000
lamps[i].length == 2
0 <= rowi, coli < n
queries[j].length == 2
0 <= rowj, colj < n
 */

class Solution {
    static final int m = 1_000_000_007;
    int n;

    Map<Integer, Integer> row = new HashMap<>();
    Map<Integer, Integer> col = new HashMap<>();
    Map<Integer, Integer> slash = new HashMap<>();
    Map<Integer, Integer> backSlash = new HashMap<>();
    Map<Long, Integer> map = new HashMap<>();

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        this.n = n;
        for (int[] L: lamps) {
            row.merge(L[0], 1, Integer::sum);
            col.merge(L[1], 1, Integer::sum);
            slash.merge(L[0] + L[1], 1, Integer::sum);
            backSlash.merge(L[0] - L[1], 1, Integer::sum);
            map.merge((long) L[0] * m + L[1], 1, Integer::sum);
        }
        int[] ans = new int[queries.length];
        int p = -1;
        for (int[] q: queries) {
            ans[++p] = validate(q) ? 1 : 0;
            slake(q);
        }
        return ans;
    }

    private void slake(int[] p) {
        for (int x = p[0] - 1; x <= p[0] + 1; ++x) {
            if (x < 0 || x >= n) {
                continue;
            }
            for (int y = p[1] - 1; y <= p[1] + 1; ++y) {
                if (y < 0 || y >= n) {
                    continue;
                }
                long hash = (long) x * m + y;
                if (! map.containsKey(hash)) {
                    continue;
                }
                int k = map.get(hash);
                row.merge(x, -k, Integer::sum);
                col.merge(y, -k, Integer::sum);
                slash.merge(x + y, -k, Integer::sum);
                backSlash.merge(x - y, -k, Integer::sum);
                map.remove(hash);
            }
        }
    }

    private boolean validate(int[] q) {
        return row.getOrDefault(q[0], 0) > 0
            || col.getOrDefault(q[1], 0) > 0
            || slash.getOrDefault(q[0] + q[1], 0) > 0
            || backSlash.getOrDefault(q[0] - q[1], 0) > 0;
    }
}