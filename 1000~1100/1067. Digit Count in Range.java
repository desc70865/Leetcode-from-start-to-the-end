/* 
Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.
 

Example 1:

Input: d = 1, low = 1, high = 13
Output: 6
Explanation: 
The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
Example 2:

Input: d = 3, low = 100, high = 250
Output: 35
Explanation: 
The digit d=3 occurs 35 times in 103,113,123,130,131,...,238,239,243.
 

Note:

0 <= d <= 9
1 <= low <= high <= 2×10^8
 */

class Solution {
    public int digitsCount(int d, int low, int high) {
        return countDigitiK(high, d) - countDigitiK(low - 1, d);
    }

    private int countDigitiK(int n, int k) {
        int cnt = 0;
        for (long base = 1; base <= n; base *= 10) {
            long ordinal = n / base;
            cnt += (ordinal + (9 - k)) / 10 * base + (ordinal % 10 == k ? n % base + 1 : 0);
            // core ↑ 需去除当前位前缀全部为零的一个排列
            if (k == 0) cnt -= base;
        }
        return cnt;
    }
}