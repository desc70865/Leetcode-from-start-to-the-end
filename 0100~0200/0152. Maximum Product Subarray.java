/* 
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

class Solution {
    public int maxProduct(int[] nums) {
        int i = 0, j = 0, N = nums.length;
        if (N == 1) return nums[0];
        int res = 0;
        while (j < N) {
            while (i < N && nums[i] == 0) i++;
            j = i;
            while (j < N && nums[j] != 0) j++;
            res = Math.max(res, cal(nums, i, j - 1));
            i = ++j;
        }
        return res;
    }

    private int cal(int[] A, int i, int j) {
        if (i == j) return A[i];
        int k = i, l = j, a = 0, b = 0;
        int p = 1, q = 1, r = 1;
        while (k <= j && A[k] > 0) p *= A[k++];
        if (k > j) return p;
        while (l >= k && A[l] > 0) q *= A[l--];
        if (k == l) return Math.max(p, q);
        for (int m = k + 1; m < l; m++) r *= A[m];
        p *= A[k];
        q *= A[l];
        if (r > 0) return p * q * r;
        else return Math.min(p, q) * r;
    }
}



class Solution {
    int res = 0;
    public int maxProduct(int[] nums) {
        int N = nums.length;
        if (N == 1) return nums[0];
        helper(nums, 0, N - 1, 1);
        helper(nums, N - 1, 0, -1);
        return res;
    }

    private void helper(int[] A, int l, int r, int step) {
        int p = 1;
        while (true) {
            p *= A[l];
            if (p == 0) p = 1;
            else res = Math.max(res, p);
            if (l == r) break;
            l += step;
        }
    }
}