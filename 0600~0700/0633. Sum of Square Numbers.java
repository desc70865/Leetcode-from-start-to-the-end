/* 
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:

Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
 

Example 2:

Input: 3
Output: False
 */

class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) return true;
        }
        return false;
    }
}



class Solution {
    public boolean judgeSquareSum(int c) {
        for (int factor = 2; factor * factor <= c; factor++) {
            if (c % factor != 0) continue;
            int cnt = 0;
            while (c % factor == 0) {
                cnt++;
                c /= factor;
            }
            if (factor % 4 == 3 && cnt % 2 != 0) return false;
        }
        return c % 4 != 3;
    }
}