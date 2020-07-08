/* 
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.

 

Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false
 

Constraints:

1 <= num <= 2^31 - 1
 */

class Solution {
    public boolean isPerfectSquare(int x) {
        long i = Double.doubleToLongBits((double)x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        double n = 1/Double.longBitsToDouble(i)+1;
        while (Math.abs(n*n - x) > 0.001) {
        	n = (n + x / n) / 2.0;
        }
        return Math.abs(Math.round(n) - n) < 0.01;
    }
}