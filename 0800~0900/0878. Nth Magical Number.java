/* 
A positive integer is magical if it is divisible by either a or b.

Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: n = 1, a = 2, b = 3
Output: 2
Example 2:

Input: n = 4, a = 2, b = 3
Output: 6
Example 3:

Input: n = 5, a = 2, b = 4
Output: 10
Example 4:

Input: n = 3, a = 6, b = 4
Output: 8
 

Constraints:

1 <= n <= 109
2 <= a, b <= 4 * 104
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int nthMagicalNumber(int n, int a, int b) {
        int c = gcd(a, b);
        if (c == a || c == b) {
            return (int) ((long) c * n % MOD);
        }
        c = a * b / c;
        long L = 0, R = 1L << 46;
        while (L < R) {
            long M = L + R >> 1;
            if (f(M, a, b, c) < n) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        return (int) (L % MOD);
    }

    private long f(long n, int a, int b, int c) {
        return n / a + n / b - n / c;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}