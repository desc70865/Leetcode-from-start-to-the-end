/* 
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10
 */

class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
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
        for (int i = 0; i < heights.length; i++) 
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        return max;
    }
}

// 计算每个包含 heights[i] 的矩形边界
// 例, 左边界, 绝对递增,当前坐标; 反之, 向前查找首个满足 heights[pre] > heights[i] 的 pre
// left[i]: 前置首个小于(不含等于) heights[i] 的坐标 +1; # 定义中的 +1 在取值运算时需 -1
// 另, 栈