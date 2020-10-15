/* 
We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
 */

class Solution {
    public int matrixScore(int[][] A) {
        for (int[] line: A) {
            if (line[0] == 0) reverse(line);
        }
        int m = A.length, n = A[0].length;
        int sum = m * (1 << n - 1);
        for (int j = 1; j < n; j++) {
            int level = 0;
            for (int i = 0; i < m; i++) {
                level += A[i][j];
            }
            level = Math.max(level, m - level);
            sum += level * (1 << n - 1 - j);
        }
        return sum;
    }

    private void reverse(int[] line) {
        for (int i = 0; i < line.length; i++) line[i] = 1 - line[i];
    }
}