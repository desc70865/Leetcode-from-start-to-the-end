/* 
Given an array nums, we may rotate it by a non-negative integer k so that the array becomes nums[k], nums[k+1], nums[k+2], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1].  Afterward, any entries that are less than or equal to their index are worth 1 point.

For example, if we have [2, 4, 1, 3, 0], and we rotate by k = 2, it becomes [1, 3, 0, 2, 4]. This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].

Over all possible rotations, return the rotation index k that corresponds to the highest score we could receive. If there are multiple answers, return the smallest such index k.

Example 1:
Input: [2, 3, 1, 4, 0]
Output: 3
Explanation:  
Scores for each k are listed below: 
k = 0,  nums = [2,3,1,4,0],    score 2
k = 1,  nums = [3,1,4,0,2],    score 3
k = 2,  nums = [1,4,0,2,3],    score 3
k = 3,  nums = [4,0,2,3,1],    score 4
k = 4,  nums = [0,2,3,1,4],    score 3
So we should choose k = 3, which has the highest score.

 

Example 2:
Input: [1, 3, 0, 2, 4]
Output: 0
Explanation: nums will always have 3 points no matter how it shifts.
So we will choose the smallest k, which is 0.
Note:

nums will have length at most 20000.
nums[i] will be in the range [0, nums.length].
 */

class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] x = new int[n];
        for (int i = 0; i < n; ++i) {
            int l = (i - nums[i] + 1 + n) % n;
            --x[l];
            int r = (i + 1) % n;
            ++x[r];
            if (l > r) {
                --x[0];
            }
        }
        int max = -n;
        int ans = 0;
        for (int i = 0, sum = 0; i < n; ++i) {
            sum += x[i];
            if (max < sum) {
                max = sum;
                ans = i;
            }
        }
        return ans;
    }
}