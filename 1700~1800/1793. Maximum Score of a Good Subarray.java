/* 
You are given an array of integers nums (0-indexed) and an integer k.

The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

Return the maximum possible score of a good subarray.

 

Example 1:

Input: nums = [1,4,3,7,4,5], k = 3
Output: 15
Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15. 
Example 2:

Input: nums = [5,5,4,5,4,1,1,1], k = 0
Output: 20
Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 2 * 104
0 <= k < nums.length
 */

class Solution {
    public int maximumScore(int[] nums, int k) {
        int min = nums[k];
        for (int num: nums) min = Math.min(min, num);
        int max = 0;
        for (int l = k, r = k, h = nums[k]; h >= min; h--) {
            while (l - 1 >= 0 && nums[l - 1] >= h) l--;
            while (r + 1 < nums.length && nums[r + 1] >= h) r++;
            max = Math.max(max, (r - l + 1) * h);
        }
        return max;
    }
}

// 同 0084. Largest Rectangle in Histogram

class Solution {
    public int maximumScore(int[] heights, int k) {
        int N = heights.length;
        int[] L = new int[N], R = new int[N];
        for (int i = 0; i < N; i++) {
            int p = i - 1;
            while (p >= 0 && heights[i] <= heights[p]) p = L[p] - 1;
            L[i] = p + 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            int p = i + 1;
            while (p < N && heights[i] <= heights[p]) p = R[p] + 1;
            R[i] = p - 1;
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (L[i] > k || R[i] < k) continue;
            max = Math.max(max, (R[i] - L[i] + 1) * heights[i]);
        }
        return max;
    }
}