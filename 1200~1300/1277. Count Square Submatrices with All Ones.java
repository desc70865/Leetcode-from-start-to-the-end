/* 
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
 */

class Solution {
    public int countSquares(int[][] M) {
        int m = M.length, n = M[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) res += M[0][i];
        for (int i = 1; i < m; i++) res += M[i][0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (M[i][j] == 0) continue;
                M[i][j] += Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]);
                res += M[i][j];
            }
        }
        return res;
    }
}