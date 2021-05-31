/* 
Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.

 

Example 1:



Input: n = 2, m = 3
Output: 3
Explanation: 3 squares are necessary to cover the rectangle.
2 (squares of 1x1)
1 (square of 2x2)
Example 2:



Input: n = 5, m = 8
Output: 5
Example 3:



Input: n = 11, m = 13
Output: 6
 

Constraints:

1 <= n <= 13
1 <= m <= 13
 */

class Solution {
    static int[][] dp;

    static {
        dp = new int[14][14];
        for (int m = 1; m <= 13; ++m) {
            for (int n = 1; n <= m; ++n) {
                if (m == n) {
                    dp[m][n] = 1;
                    continue;
                }
                dp[m][n] = m * n;
                for (int k = 1; k <= n; ++k) {
                    if (k == n) {
                        dp[m][n] = Math.min(dp[m][n], 1 + f(m - k, n));
                        continue;
                    }
                    for (int w = m - k; w <= m; ++w) {
                        for (int h = 1; h <= n - k; ++h) {
                            dp[m][n] = Math.min(dp[m][n], 1 + f(w, h) + f(m - w, n - k) + f(n - h, m - k) + f(w - m + k, n - h - k));
                        }
                    }
                }
            }
        }
    }

    private static int f(int w, int h) {
        return w >= h ? dp[w][h] : dp[h][w];
    }

    public int tilingRectangle(int n, int m) {
        return f(m, n);
    }
}