/* 
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321

Example 2:

Input: -123
Output: -321

Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
 
 class Solution {
    public int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans *= 10;
            ans += x % 10;
            x /= 10;
        }
        return ans < Integer.MIN_VALUE || Integer.MAX_VALUE < ans ? 0 : (int) ans;
    }
}



class Solution {
    public int reverse(int x) {
        long ans = Long.valueOf((x < 0 ? "-" : "") + new StringBuilder(Long.toString(Math.abs((long) x))).reverse().toString());
        return Integer.MIN_VALUE <= ans && ans <= Integer.MAX_VALUE ? (int) ans : 0;
    }
}