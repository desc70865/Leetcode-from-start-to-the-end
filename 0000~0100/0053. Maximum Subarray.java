/* 
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int cur = 0, max = Integer.MIN_VALUE;
        for (int num: nums) {
            cur = Math.max(cur, 0) + num;
            max = Math.max(cur, max);
        }
        return max;
    }
}

// 1 ms

class Solution {
    public int maxSubArray(int[] nums) {
        int N = nums.length;
        return N == 0 ? 0 : helper(nums, 0, N - 1);
    }

    private int helper(int[] A, int l, int r) {
        if (l >= r) return A[l];
        int mid = l + r >> 1;
        int L = helper(A, l, mid - 1);
        int R = helper(A, mid + 1, r);
        int M = A[mid];
        int t = M;
        for (int i = mid - 1; i >= l; i--) {
            t += A[i];
            M = Math.max(M, t);
        }
        t = M;
        for (int i = mid + 1; i <= r; i++) {
            t += A[i];
            M = Math.max(M, t);
        }
        return Math.max(M, Math.max(L, R));
    }
}

// 3 ms