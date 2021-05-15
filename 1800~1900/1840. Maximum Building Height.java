/* 
You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.

However, there are city restrictions on the heights of the new buildings:

The height of each building must be a non-negative integer.
The height of the first building must be 0.
The height difference between any two adjacent buildings cannot exceed 1.
Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.

It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.

Return the maximum possible height of the tallest building.

 

Example 1:


Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
Example 2:


Input: n = 6, restrictions = []
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
Example 3:


Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
 

Constraints:

2 <= n <= 109
0 <= restrictions.length <= min(n - 1, 105)
2 <= idi <= n
idi is unique.
0 <= maxHeighti <= 109
 */

class Solution {
    static final int INF = 1_000_000_007;

    public int maxBuilding(int n, int[][] restrictions) {
        sort(restrictions);
        // Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int len = restrictions.length;
        int level = INF;
        for (int i = len - 1; i >= 0; --i) {
            int pos = restrictions[i][0];
            int height = restrictions[i][1];
            if (level > pos + height) {
                level = pos + height;
            } else {
                restrictions[i][1] = level - pos;
            }
        }
        level = -1;
        int heightL = 0;
        int posL = 1;
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            int posR = restrictions[i][0];
            int heightR = restrictions[i][1];
            level = Math.min(heightR - posR, level);
            heightR = level + posR;
            ans = Math.max(ans, (posR - posL + heightL + heightR) >>> 1);
            heightL = heightR;
            posL = posR;
        }
        return Math.max(ans, n - posL + heightL);
    }

    static final int MOD = 1 << 30;

    private void sort(int[][] arr) {
        int n = arr.length;
        long[] aux = new long[n];
        for (int i = 0; i < n; ++i) {
            aux[i] = ((long) arr[i][0] << 30) + arr[i][1];
        }
        Arrays.sort(aux);
        for (int i = 0; i < n; ++i) {
            arr[i][0] = (int) (aux[i] >> 30);
            arr[i][1] = (int) (aux[i] % MOD);
        }
    }
}