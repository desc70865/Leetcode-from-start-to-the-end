/* 
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

class Solution {
    static int height;
    static int width;
    static int x = 0;
    static int y = 0;
    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return result;
        height = matrix.length;
        width = matrix[0].length;
        int count = Math.min(height, width);
        helper(matrix, result, height, width, count);
        return result;
    }
    private static void helper (int[][] matrix, ArrayList<Integer> result, int height, int width, int count) {
        if (count == 0) {
            return;
        } else if (count == 1) {
            if (height > width) {
                for (int i = 0; i < height; i++) {
                    result.add(matrix[x++][y]);
                }
            } else {
                for (int i = 0; i < width; i++) {
                    result.add(matrix[x][y++]);
                }
            }
            return;
        }
        for (int i = 0; i < width-1; i++)
            result.add(matrix[x][y++]);
        for (int i = 0; i < height-1; i++)
            result.add(matrix[x++][y]);
        for (int i = 0; i < width-1; i++)
            result.add(matrix[x][y--]);
        for (int i = 0; i < height-1; i++)
            result.add(matrix[x--][y]);
        x++;
        y++;
        helper(matrix, result, height-2, width-2, count-2);
    }
}

// 设置 helper 传 count 参数
// 每次打印一圈

class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return result;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        while (m > 0 && n > 0) {
            //if one row/column left, no circle can be formed
            if (m == 1 || n == 1) {
                if(m == 1){
                    for (int i = 0; i < n; i++) {
                        result.add(matrix[x][y++]);
                    }
                }else if(n == 1){
                    for (int i = 0; i < m; i++) {
                        result.add(matrix[x++][y]);
                    }
                }
                return result;
            }
            //below, process a circle
            //top - move right
            for (int i = 0; i < n-1; i++)
                result.add(matrix[x][y++]);
            //right - move down
            for (int i = 0; i < m-1; i++)
                result.add(matrix[x++][y]);
            //bottom - move left
            for (int i = 0; i < n-1; i++)
                result.add(matrix[x][y--]);
            //left - move up
            for (int i = 0; i < m-1; i++)
                result.add(matrix[x--][y]);
            x++;
            y++;
            m -= 2;
            n -= 2;
        }
        return result;
    }
}