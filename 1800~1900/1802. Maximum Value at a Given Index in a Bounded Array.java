/* 
You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

 

Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3
 

Constraints:

1 <= n <= maxSum <= 109
0 <= index < n
 */

class Solution {
    public int maxValue(int n, int index, int maxSum) {
        maxSum -= n;
        int ans = 1;
        for (int l = index, r = index; 0 < l || r < n - 1; ans++) {
            int cur = r - l + 1;
            if (maxSum >= cur) {
                maxSum -= cur;
                if (l > 0) l--;
                if (r < n - 1) r++;
            } else {
                break;
            }
        }
        return ans + maxSum / n;
    }
}