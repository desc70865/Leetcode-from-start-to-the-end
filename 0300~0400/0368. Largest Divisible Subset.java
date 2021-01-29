/* 
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        if (len == 1) return Arrays.asList(nums[0]);
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        Arrays.sort(nums);
        int k = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if (dp[i] > dp[k]) {
                k = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        int cur = dp[k];
        for (int i = k; i >= 0; i--) {
            if (dp[i] == cur && nums[k] % nums[i] == 0) {
                res.add(nums[i]);
                cur--;
                k = i;
            }
        }
        return res;
    }
}