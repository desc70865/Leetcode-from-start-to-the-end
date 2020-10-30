/* 
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, area = 0;
        int[] h = new int[n + 1];
        Stack<Integer> p = new Stack<Integer>();
        for (int i=0; i < m; i++) {
            for(int j=0; j < n; j++) {
                if (matrix[i][j] == '0') h[j] = 0;
                else h[j] += 1;
                while (!p.isEmpty() && h[p.peek()] > h[j])
                    area = Math.max(area, h[p.pop()] * (p.isEmpty() ? j : j - p.peek() - 1));
                p.push(j);
            }
            while (!p.isEmpty()) 
                area = Math.max(area, h[p.pop()] * (p.isEmpty() ? n : n - p.peek() - 1));
        }
        return area;
    }
}



class Solution {
   public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, maxArea = 0;
        int[] heights = new int[n];
        for (int row=0; row < m; row++) {
            for (int col=0; col < n; col++) {
                if (matrix[row][col] == '1') heights[col]++;
                else heights[col] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
     
    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length], right = new int[heights.length];
        for (int i=0; i < heights.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && heights[i] <= heights[pre]) pre = left[pre] - 1; // 核心
            left[i] = pre + 1;
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            int pre = i + 1;
            while (pre < heights.length && heights[i] <= heights[pre]) pre = right[pre] + 1;
            right[i] = pre - 1;
        }
        int max = 0;
        for (int i=0; i < heights.length; i++) 
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        return max;
    }
}