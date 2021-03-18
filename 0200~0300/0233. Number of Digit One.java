/* 
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6 
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

class Solution {
    public int countDigitOne(int n) {
        return countDigitiK(n, 1);
    }

    private int countDigitiK(int n, int k) {
        int cnt = 0;
        for (long base = 1; base <= n; base *= 10) {
            long ordinal = n / base;
            cnt += (ordinal + (9 - k)) / 10 * base + (ordinal % 10 == k ? n % base + 1 : 0);
        }
        return cnt;
    }
}

// 逐位计算出现次数

// https://leetcode-cn.com/problems/number-of-2s-in-range-lcci/