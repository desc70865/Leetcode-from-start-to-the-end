/* 
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91 
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, 
             excluding 11,22,33,44,55,66,77,88,99
 */

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int res = 1;
        for (int i = 1; i <= Math.min(n, 10); i++) {
            int index = 1, tmp = 9;
            while (++index <= i) {
                tmp *= 11 - index;
            }
            res += tmp;
        }
        return res;
    }
}



class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int res = 1;
        for (int i = 1; i <= Math.min(n, 10); i++) {
            res += calc(i);
        }
        return res;
    }
    
    private int calc(int i) {
        int index = 1, res = 9;
        while (++index <= i) {
            res *= 11 - index;
        }
        return res;
    }
}



class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int[] res = new int[]{1, 10, 91, 739, 5275, 32491, 168571, 712891, 2345851, 5611771, 8877691};
        return n > 10 ? res[10] : res[n];
    }
}