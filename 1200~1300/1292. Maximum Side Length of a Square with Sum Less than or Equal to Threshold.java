/* 
Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

 

Example 1:


Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
Example 2:

Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
Output: 0
Example 3:

Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
Output: 3
Example 4:

Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
Output: 2
 

Constraints:

1 <= m, n <= 300
m == mat.length
n == mat[i].length
0 <= mat[i][j] <= 10000
0 <= threshold <= 10^5
 */

class Solution {
    int m, n;

    public int maxSideLength(int[][] mat, int threshold) {
        m = mat.length;
        n = mat[0].length;
        int[][] area = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                area[i + 1][j + 1] = area[i + 1][j] + area[i][j + 1] - area[i][j] + mat[i][j];
            }
        }
        int L = 1;
        int R = Math.min(m, n);
        while (L <= R) {
            int M = L + R >> 1;
            if (helper(area, M, threshold)) L = M + 1;
            else R = M - 1;
        }
        return L - 1;
    }

    private boolean helper(int[][] area, int side, int threshold) {
        for (int i = 0; i <= m - side; i++) {
            for (int j = 0; j <= n - side; j++) {
                if (sum(area, i, j, side) <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    private int sum(int[][] area, int i, int j, int k) {
        return area[i + k][j + k] - area[i + k][j] - area[i][j + k] + area[i][j];
    }
}



class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] area = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                area[i + 1][j + 1] = area[i + 1][j] + area[i][j + 1] - area[i][j] + mat[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = res + 1; k + i <= m && k + j <= n; k++) {
                    if (sum(area, i, j, k) <= threshold) res++;
                    else break;
                }
            }
        }
        return res;
    }

    private int sum(int[][] area, int i, int j, int k) {
        return area[i + k][j + k] - area[i + k][j] - area[i][j + k] + area[i][j];
    }
}