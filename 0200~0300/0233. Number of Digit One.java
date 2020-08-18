/* 
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6 
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

class Solution {
    public int countDigitOne(int n) {
        int cnt = 0;
        for (long base = 1; base <= n; base *= 10) {
            long ordinal = n / base, tail = n % base;
            cnt += (ordinal + 8) / 10 * base + (ordinal % 10 == 1 ? tail + 1 : 0);
        }
        return cnt;
    }
}