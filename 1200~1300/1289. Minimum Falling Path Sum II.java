/* 
Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.

 

Example 1:

Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
 

Constraints:

1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99
 */

class Solution {
    public int minFallingPathSum(int[][] arr) {
        int m = arr.length, n = arr[0].length;
        int[] aux = new int[3];
        for (int i = 1; i < m; i++) {
            aux[0] = 0;
            aux[1] = arr[i - 1][0];
            aux[2] = Integer.MAX_VALUE;
            for (int j = 1; j < n; j++) {
                if (arr[i - 1][j] < aux[1]) {
                    aux[2] = aux[1];
                    aux[1] = arr[i - 1][j];
                    aux[0] = j;
                } else if (arr[i - 1][j] < aux[2]) {
                    aux[2] = arr[i - 1][j];
                }
            }
            for (int j = 0; j < n; j++) {
                if (j == aux[0]) arr[i][j] += aux[2];
                else arr[i][j] += aux[1];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int num: arr[m - 1]) res = Math.min(res, num);
        return res;
    }
}