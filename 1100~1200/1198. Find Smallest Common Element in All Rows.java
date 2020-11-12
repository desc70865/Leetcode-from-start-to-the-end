/* 
Given a matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.

If there is no common element, return -1.

 

Example 1:

Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
Output: 5
 

Constraints:

1 <= mat.length, mat[i].length <= 500
1 <= mat[i][j] <= 10^4
mat[i] is sorted in strictly increasing order.
 */

class Solution {
    public int smallestCommonElement(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                pos[i] = Arrays.binarySearch(mat[i], pos[i], m, cur_max);
                if (pos[i] < 0) {
                    cnt = 1;
                    pos[i] = -pos[i] - 1;
                    if (pos[i] >= m) {
                        return -1;
                    }
                } else if (++cnt == n) {
                    return cur_max;
                }
                cur_max = mat[i][pos[i]];
            }
        }
    }
}