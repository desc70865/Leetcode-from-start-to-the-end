/* 
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
 */

class Solution {
    int a, b, c;

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num: nums) {
            pq.offer(f(num));
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pq.poll();
        }
        return nums;
    }

    private int f(int x) {
        return a * x * x + b * x + c;
    }
}



class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length;
        int[] ans = new int[len];
        int l = 0;
        int r = len - 1;
        int index1 = len - 1;
        int index2 = 0;
        while (l <= r) {
            int left = a * nums[l] * nums[l] + b * nums[l] + c;
            int right = a * nums[r] * nums[r] + b * nums[r] + c;
            if (a > 0) {
                if (left > right) {
                    ans[index1--] = left;
                    l++;
                } else {
                    ans[index1--] = right;
                    r--;
                }
            } else {
                if (left > right) {
                    ans[index2++] = right;
                    r--;
                } else {
                    ans[index2++] = left;
                    l++;
                }
            }
        }
        return ans;
    }
}