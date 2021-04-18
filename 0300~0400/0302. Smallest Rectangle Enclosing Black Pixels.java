/* 
You are given an image that is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.

The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.

Given two integers x and y that represent the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

 

Example 1:


Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
Output: 6
Example 2:

Input: image = [["1"]], x = 0, y = 0
Output: 1
 

Constraints:

m == image.length
n == image[i].length
1 <= m, n <= 100
image[i][j] is either '0' or '1'.
1 <= x < m
1 <= y < n
image[x][y] == '1'.
The black pixels in the image only form one component.
 */

class Solution {
    int[] dirs = {0, 1, 0, -1, 0};
    int xR, xL, yD, yU;

    public int minArea(char[][] image, int x, int y) {
        this.xR = this.xL = x;
        this.yD = this.yU = y;
        dfs(image, x, y, new boolean[image.length][image[0].length]);
        return (xR - xL + 1) * (yD - yU + 1);
    }

    private void dfs(char[][] image, int x, int y, boolean[][] v) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || v[x][y] || image[x][y] != '1') return;
        v[x][y] = true;
        xL = Math.min(xL, x);
        xR = Math.max(xR, x);
        yU = Math.min(yU, y);
        yD = Math.max(yD, y);
        for (int i = 0; i < 4; i++) {
            dfs(image, x + dirs[i], y + dirs[i + 1], v);
        }
    }
}



public class Solution {
    char[][] image;

    public int minArea(char[][] image, int x, int y) {
        this.image = image;
        int m = image.length, n = image[0].length;
        int left = exCols(0, m, 0, y, true);
        int right = exCols(0, m, y + 1, n, false);
        return (right - left) * (exRows(x + 1, m, left, right, false) - exRows(0, x, left, right, true));
    }

    private int exCols(int top, int bottom, int left, int right, boolean order) {
        while (left < right) {
            int row = top, mid = left + right >> 1;
            while (row < bottom && image[row][mid] == '0') row++;
            if (row < bottom == order) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int exRows(int top, int bottom, int left, int right, boolean order) {
        while (top < bottom) {
            int col = left, mid = top + bottom >> 1;
            while (col < right && image[mid][col] == '0') col++;
            if (col < right == order) bottom = mid;
            else top = mid + 1;
        }
        return top;
    }
}