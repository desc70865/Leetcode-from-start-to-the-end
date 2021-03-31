/* 
You are given a 0-indexed integer array nums consisting of n non-negative integers.

You are also given an array queries, where queries[i] = [xi, yi]. The answer to the ith query is the sum of all nums[j] where xi <= j < n and (j - xi) is divisible by yi.

Return an array answer where answer.length == queries.length and answer[i] is the answer to the ith query modulo 109 + 7.

 

Example 1:

Input: nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
Output: [9,18,10]
Explanation: The answers of the queries are as follows:
1) The j indices that satisfy this query are 0, 3, and 6. nums[0] + nums[3] + nums[6] = 9
2) The j indices that satisfy this query are 5, 6, and 7. nums[5] + nums[6] + nums[7] = 18
3) The j indices that satisfy this query are 4 and 6. nums[4] + nums[6] = 10
Example 2:

Input: nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
Output: [303]
 

Constraints:

n == nums.length
1 <= n <= 5 * 104
0 <= nums[i] <= 109
1 <= queries.length <= 1.5 * 105
0 <= xi < n
1 <= yi <= 5 * 104
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/sum-of-special-evenly-spaced-elements-in-array/solution/java-100-by-keylol-x9oo/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    static final int MOD = 1_000_000_007;

    public int[] solve(int[] nums, int[][] queries) {
        int len = nums.length;
        int sqrt = (int) (Math.sqrt(len) / 3.5);
        long[][] preSum = new long[len + sqrt][sqrt];
        for (int i = len - 1; i >= 0; i--) {
            for (int step = 1; step < sqrt; step++) {
                preSum[i][step] = preSum[i + step][step] + nums[i];
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int k = queries[i][0], step = queries[i][1];
            if (step >= sqrt) {
                long sum = 0;
                while (k < len) {
                    sum += nums[k];
                    k += step;
                }
                ans[i] = (int) (sum % MOD);
            } else {
                ans[i] = (int) (preSum[k][step] % MOD);
            }
        }
        return ans;
    }
}