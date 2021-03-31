/* 
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/er-fen-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int R = 0;
        for (int num: nums) {
            int i = Arrays.binarySearch(dp, 0, R, num);
            if (i < 0) i = - (i + 1);
            dp[i] = num;
            if (i == R) R++;
        }
        return R;
    }
}



class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int R = 0;
        for (int num: nums) {
            int k = num;
            if (R == 0 || k > dp[R - 1]) {
                dp[R++] = k;
            } else {
                dp[binarySearch(dp, 0, R - 1, k)] = k;
            }
        }
        return R;
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        while (l <= r) {
            int m = l + r >> 1;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return l;
    }
}



class Solution {
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] >= nums[j]) continue;
                if (dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(dp[i], max);
                }
                if (dp[i] + i >= N) break;
            }
        }
        // System.out.println(Arrays.toString(dp));
        return max;
    }
}