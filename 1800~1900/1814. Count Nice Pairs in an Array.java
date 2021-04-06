/* 
You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
Example 2:

Input: nums = [13,10,35,24,76]
Output: 4
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/count-nice-pairs-in-an-array/solution/reverse-hash-sum-by-keylol-cwqy/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.merge(num - reverse(num), 1, Integer::sum);
        }
        long ans = 0;
        for (int val: map.values()) {
            ans += (long) val * (val - 1) / 2;
        }
        return (int) (ans % 1_000_000_007);
    }

    private int reverse(int num) {
        int ans = 0;
        while (num != 0) {
            ans *= 10;
            ans += num % 10;
            num /= 10;
        }
        return ans;
        // return Integer.parseInt(new StringBuffer(Integer.toString(num)).reverse().toString());
    }
}