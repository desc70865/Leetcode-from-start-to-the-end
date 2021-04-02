/* 
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int ans = 0;
        for (int i = 0; i < height.length;) {
            while (stack.size() > 0 && height[i] > height[stack.peek()]) {
                int cur = stack.pop();
                if (stack.size() == 0) break;
                ans += (Math.min(height[i], height[stack.peek()]) - height[cur]) * (i - stack.peek() - 1);
            }
            stack.push(i++);
        }
        return ans;
    }
}



class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int[] stack = new int[len];
        int idx = -1;
        int ans = 0;
        for (int i = 0; i < len;) {
            while (idx >= 0 && height[i] > height[stack[idx]]) {
                int cur = stack[idx--];
                if (idx < 0) break;
                ans += (Math.min(height[i], height[stack[idx]]) - height[cur]) * (i - stack[idx] - 1);
            }
            stack[++idx] = i++;
        }
        return ans;
    }
}



class Solution {
    public int trap(int[] height) {
        int ans = 0;
        for (int l = 0, r = height.length - 1, level = 0; l < r;) {
            int boundary = height[height[l] < height[r] ? l++ : r--];
            if (level > boundary) ans += level - boundary;
            else level = boundary;
        }
        return ans;
    }
}