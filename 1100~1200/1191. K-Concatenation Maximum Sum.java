/* 
Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: arr = [1,2], k = 3
Output: 9
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
Example 3:

Input: arr = [-1,-2], k = 7
Output: 0
 

Constraints:

1 <= arr.length <= 10^5
1 <= k <= 10^5
-10^4 <= arr[i] <= 10^4
 */

class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long sum = 0;
        long max = Integer.MIN_VALUE;
        long min = 0;
        long cur = 0;
        long curMax = 0;
        for (int num: arr) {
            cur = Math.max(cur + num, num);
            curMax = Math.max(curMax, cur);
            sum += num;
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        min = sum - min;
        if (sum <= 0) return (int) Math.max(Math.max(max, 0) + Math.max(min, 0), curMax);
        if (k == 1) return (int) Math.max(Math.max(max, min), 0);
        return (int) ((sum * (k - 2) + max + min) % 1_000_000_007);
    }
}