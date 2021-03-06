/* 
Write a program to find the n-th ugly number.

Ugly numbers are positive integers which are divisible by a or b or c.

 

Example 1:

Input: n = 3, a = 2, b = 3, c = 5
Output: 4
Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
Example 2:

Input: n = 4, a = 2, b = 3, c = 4
Output: 6
Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
Example 3:

Input: n = 5, a = 2, b = 11, c = 13
Output: 10
Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
Example 4:

Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
Output: 1999999984
 

Constraints:

1 <= n, a, b, c <= 10^9
1 <= a * b * c <= 10^18
It's guaranteed that the result will be in range [1, 2 * 10^9]
 */

class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        
    }
}



class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 0, r = 2_000_000_000, res = 0;
        long ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(a, bc);
        while (l < r) {
            long mid = l + r >> 1;
            if (count(mid, a, b, c, ab, ac, bc, abc) < n) l = mid + 1;
            else r = mid;
        }
        return (int) l;
    }
    
    private long count(long num, int a, int b, int c, long ab, long ac, long bc, long abc) {
        return num / a + num / b + num / c 
            - num / ab - num /ac - num / bc
            + num / abc;
    }
    
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
    
    private long gcd(long x, long y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}