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
        int N = nums.length;
        int[] dp = new int[N];
        int R = 0;
        for (int num: nums) {
            if (insert(dp, R - 1, num) == R) R++;
            // System.out.println(Arrays.toString(dp));
        }
        return R;
    }

    public int insert(int[] A, int R, int num) {
        if (R >= 0 && A[R] < num) {
            A[R + 1] = num;
            return R + 1;
        }
        int L = 0;
        while (L < R) {
            int mid = L + R >> 1;
            if (A[mid] < num) L = mid + 1;
            else R = mid;
        }
        A[L] = num;
        return L;
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