/* 
Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
 */

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        if (m == 0) {
            return mat;
        }
        int n = mat[0].length;

        for (int i = 0; i < n; i++) {
            int up = 0, left = i;
            int right = Math.min(left + m - 1, n - 1);
            int down = right - left;
            specialQuickSort(mat, up, left, down, right);
        }

        for (int i = 1; i < m; i++) {
            int up = i, left = 0;
            int down = Math.min(up + n - 1, m - 1);
            int right = down - up;
            specialQuickSort(mat, up, left, down, right);
        }
        return mat;
    }

    public void specialQuickSort(int[][] a, int up, int left, int down, int right) {
        //(up, left) 是左上角元素的坐标，(down, right) 是右下角元素的坐标
        if (left > right) {
            return;
        }
        int t = a[up][left];
        int x1 = up, i = left, x2 = down, j = right;
        while (i != j) {
            while (a[x2][j] >= t && j > i) {
                x2--;
                j--;
            }
            while (a[x1][i] <= t && j > i) {
                x1++;
                i++;
            }
            if (i < j) {
                swap(a, x1, i, x2, j);
            }
        }
        swap(a, up, left, x1, i);
        specialQuickSort(a, up, left, x1 - 1, i - 1);
        specialQuickSort(a, x1 + 1, i + 1, down, right);
    }

    public void swap(int[][] a, int up, int left, int down, int right) {
        int t = a[up][left];
        a[up][left] = a[down][right];
        a[down][right] = t;
        return;
    }
}