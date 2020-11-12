/* 
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].
 */

class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        if (len < 7) return false;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.computeIfAbsent(preSum[i], z -> new ArrayList<>()).add(i);
        }
        int sum = 0;
        for (int k = len - 2; k >= 5; k--) {
            sum += nums[k + 1];
            if (! map.containsKey(sum)) continue;
            for (int i: map.get(sum)) {
                if (k - i < 4) break;
                int sum2 = sum * 2 + nums[i + 1];
                if (! map.containsKey(sum2)) continue;
                for (int j: map.get(sum2)) {
                    if (j - i < 2 || k - j < 2) continue;
                    if (preSum[k - 1] - preSum[j + 1] == sum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}