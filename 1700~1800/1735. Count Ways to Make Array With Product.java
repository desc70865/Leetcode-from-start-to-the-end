/* 
You are given a 2D integer array, queries. For each queries[i], where queries[i] = [ni, ki], find the number of different ways you can place positive integers into an array of size ni such that the product of the integers is ki. As the number of ways may be too large, the answer to the ith query is the number of ways modulo 109 + 7.

Return an integer array answer where answer.length == queries.length, and answer[i] is the answer to the ith query.

 

Example 1:

Input: queries = [[2,6],[5,1],[73,660]]
Output: [4,1,50734910]
Explanation: Each query is independent.
[2,6]: There are 4 ways to fill an array of size 2 that multiply to 6: [1,6], [2,3], [3,2], [6,1].
[5,1]: There is 1 way to fill an array of size 5 that multiply to 1: [1,1,1,1,1].
[73,660]: There are 1050734917 ways to fill an array of size 73 that multiply to 660. 1050734917 modulo 109 + 7 = 50734910.
Example 2:

Input: queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
Output: [1,2,3,10,5]
 

Constraints:

1 <= queries.length <= 104
1 <= ni, ki <= 104
 */

class Solution {
    static final int MOD = 1_000_000_007;
    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    static long[] f;
    static long[] g;

    static {
        f = new long[10010];
        g = new long[10010];
        f[0] = g[0] = 1;
        for (int i = 1; i < 10010; ++i) {
            f[i] = (f[i - 1] * i) % MOD;
            g[i] = (int) (quickPow(f[i], MOD - 2) % MOD);
        }
    }

    public int[] waysToFillArray(int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int slot = queries[i][0];
            int num = queries[i][1];
            long res = 1;
            for (int prime: primes) {
                if (num % prime == 0) {
                    int ball = 0;
                    while (num % prime == 0) {
                        num /= prime;
                        ++ball;
                    }
                    // empty-free insert
                    res = (res * nCr(ball + slot - 1, slot - 1)) % MOD;
                }
            }
            if (num > 1)
                res = (res * slot) % MOD;
            ans[i] = (int) res;
        }
        return ans;
    }

    public long nCr(int n, int r) {
        return f[n] * g[r] % MOD * g[n - r] % MOD;
    }

    public static long quickPow(long x, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1)
                ans = (ans * x) % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    static final int[][] nCr = new int[10010][14];

    public int[] waysToFillArray(int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int slot = queries[i][0];
            int num = queries[i][1];
            long res = 1;
            for (int prime: primes) {
                if (num % prime == 0) {
                    int ball = 0;
                    while (num % prime == 0) {
                        num /= prime;
                        ++ball;
                    }
                    // empty-free insert
                    res = (res * nCr(ball + slot - 1, ball)) % MOD;
                }
            }
            if (num > 1)
                res = (res * slot) % MOD;
            ans[i] = (int) res;
        }
        return ans;
    }

    public static int nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (r == 1) return n;
        if (nCr[n][r] > 0) return nCr[n][r];
        return nCr[n][r] = (nCr(n - 1, r) + nCr(n - 1, r - 1)) % MOD;
    }
}