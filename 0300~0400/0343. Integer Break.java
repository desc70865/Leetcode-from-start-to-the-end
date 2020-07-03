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
        if (n <= 3) {
            return n-1;
        }
        int a = n / 3, b = n % 3;
        return pow(3, a-1) * (3 + b * (b+1) / 2);
    }
    
    private int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
}



class Solution {
    public int integerBreak(int n) {
        if (n <= 3) {
            return n-1;
        }
        int a = n / 3, b = n % 3;
        // switch b: ...
        return (int) (Math.pow(3, a-1) * (3 + b * (b+1) / 2));
    }
}



class Solution {
    int[] res;
    public int integerBreak(int n) {
        res = new int[n + 1];
        return getMaxProduct(n);
    }
    
    private int getMaxProduct(int n) {
        if (res[n] != 0) {
            return res[n];
        }
        if (n == 1) {
            return 1;
        }
        
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, Math.max(i * (n - i), i * getMaxProduct(n - i)));
        }
        res[n] = max;
        return max;
    }
}