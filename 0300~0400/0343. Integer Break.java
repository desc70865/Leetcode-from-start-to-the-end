/* 
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:

Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
Note: You may assume that n is not less than 2 and not larger than 58.
 */

class Solution {
    public int integerBreak(int n) {
        if (n <= 3) return n-1;
        int[] aux = new int[] { 3, 4, 6 };
        return pow(3, n / 3 - 1) * (aux[n % 3]);
    }
    
    private int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) != 0) res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }
}



class Solution {
    private int[] aux = new int[] { 3, 4, 6 };
    public int integerBreak(int n) {
        return n <= 3 ? n-1 : (int) Math.pow(3, n / 3 - 1) * aux[n % 3];
    }
}