/* 
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 

Constraints:

1 <= A.length, B.length <= 100
1 <= A[i].length, B[i].length <= 100
-100 <= A[i][j], B[i][j] <= 100
 */

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int R = A.length;
        int C = B[0].length;
        int len = B.length;
        int[][] res = new int[R][C];
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                for (int i = 0; i < len; i++) {
                    res[row][col] += A[row][i] * B[i][col];
                }
            }
        }
        return res;
    }
}



class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int R = A.length;
        int T = B.length;
        int C = B[0].length;
        int[][] res = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < T; j++) {
                if (A[i][j] == 0) continue;
                for (int k = 0; k < C; k++) {
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }
}