/* 
We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation: 
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
Note:

N  will be in range [1, 1000].
 */

class Solution {
    static final int MOD = 1_000_000_007;
    static long[][] p = new long[1001][4];
    static {
        p[0][0] = 1;
        for (int i = 1; i <= 1000; i++) {
            p[i][0b00] = (p[i - 1][0b00] + p[i - 1][0b11]) % MOD;
            p[i][0b01] = (p[i - 1][0b00] + p[i - 1][0b10]) % MOD;
            p[i][0b10] = (p[i - 1][0b00] + p[i - 1][0b01]) % MOD;
            p[i][0b11] = (p[i - 1][0b00] + p[i - 1][0b01] + p[i - 1][0b10]) % MOD;
        }
    }

    public int numTilings(int N) {
        return (int) p[N][0];
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int numTilings(int N) {
        long[] p = new long[]{1, 2, 5};
        if (N <= 3) return (int) p[N - 1];
        N -= 3;
        while (N-- > 0) {
            long tmp = (p[2] * 2 + p[0]) % MOD;
            p[0] = p[1];
            p[1] = p[2];
            p[2] = tmp;
        }
        return (int) p[2];
    }
}