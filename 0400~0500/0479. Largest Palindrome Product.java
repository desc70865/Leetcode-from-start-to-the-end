/* 
Given an integer n, return the largest palindromic integer that can be represented as the product of two n-digits integers. Since the answer can be very large, return it modulo 1337.

 

Example 1:

Input: n = 2
Output: 987
Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
Example 2:

Input: n = 1
Output: 9
 

Constraints:

1 <= n <= 8
 */

class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        long max = (long) Math.pow(10, n) - 1;
        for (long num = max - 1; num > max / 10; --num) {
            long pal = g(num);
            for (long x = max; x * x >= pal; --x) {
                if (pal % x == 0) {
                    return (int) (pal % 1337);
                }
            }
        }
        return -1;
    }

    private long g(long num) {
        long ans = num;
        while (num != 0) {
            ans *= 10;
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
}