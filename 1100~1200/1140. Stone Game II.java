/* 
Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
 */

class Solution {
    int N;
    public int stoneGameII(int[] piles) {
        N = piles.length;
        for (int i = N - 2; i >= 0; i--) piles[i] += piles[i + 1];
        return dfs(piles, 1, 0, new int[N][N]);
    }
    // one player start from pos with step: m
    private int dfs(int[] presum, int m, int pos, int[][] memo) {
        if (pos + 2 * m >= N) return presum[pos];
        if (memo[pos][m] > 0) return memo[pos][m];
        int res = 0, take = 0;
        for (int i = 1; i <= 2 * m; i++) {
            take = presum[pos] - presum[pos + i];
            res = Math.max(res, take + presum[pos + i] - dfs(presum, Math.max(i, m), pos + i, memo));
        }
        memo[pos][m] = res;
        return res;
    }
}