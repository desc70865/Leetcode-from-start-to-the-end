/* 
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int x = 0;
        int y = 0;
        int index = 0;
        while (n > 0) {
            if (n == 1) {
                matrix[x][y++] = ++index;
                return matrix;
            }
            for (int i = 0; i < n-1; i++)
                matrix[x][y++] = ++index;
            for (int i = 0; i < n-1; i++)
                matrix[x++][y] = ++index;
            for (int i = 0; i < n-1; i++)
                matrix[x][y--] = ++index;
            for (int i = 0; i < n-1; i++)
                matrix[x--][y] = ++index;
            x++;
            y++;
            n -= 2;
        }
        return matrix;
    }
}

// ...