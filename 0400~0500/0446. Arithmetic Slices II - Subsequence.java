/* 
Given an integer array nums, return the number of all the arithmetic subsequences of nums.

A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.

For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The answer is guaranteed to fit in 32-bit integer.

 

Example 1:

Input: nums = [2,4,6,8,10]
Output: 7
Explanation: All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
Example 2:

Input: nums = [7,7,7,7,7]
Output: 16
Explanation: Any subsequence of this array is arithmetic.
 

Constraints:

1  <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
 */

class Solution {
    static final long INF = 1L << 31;

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for (int i = 0; i < n; ++i) {
            cnt[i] = new HashMap<>(i);
            for (int j = 0; j < i; ++j) {
                long delta = (long) nums[i] - nums[j];
                if (delta <= -INF || INF <= delta) {
                    continue;
                }
                int sum = cnt[j].getOrDefault((int) delta, 0);
                cnt[i].merge((int) delta, sum + 1, Integer::sum);
                ans += sum;
            }
        }
        return ans;
    }
}



class Solution {
    static final long INF = 1L << 31;

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (nums == null || n < 3) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            map.computeIfAbsent(nums[i], z -> new ArrayList<>()).add(i);
        }
        int ans = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long next = ((long) nums[j] << 1) - nums[i];
                if (next < -INF || INF <= next || ! map.containsKey((int) next)) {
                    continue;
                }
                for (int k: map.get((int) next)) {
                    if (k < j) {
                        dp[i][j] += dp[j][k] + 1;
                    }
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }
}