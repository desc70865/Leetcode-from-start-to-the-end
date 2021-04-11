/* 
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int l = 0;
        int r = nums[len - 1] - nums[0];
        while (l < r) {
            int m = l + r >> 1;
            int cnt = 0;
            for (int L = 0, R = 0; R < len; R++) {
                while (nums[R] - nums[L] > m) L++;
                cnt += R - L;
            }
            if (cnt >= k) r = m;
            else l = m + 1;
        }
        return l;
    }
}