/* 
There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.

 

Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 2:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Example 3:

Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation: 
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.
 

Note:

1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
 */

class Solution {
    static final int INF = 0x3f3f3f3f;

    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) return -1;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        int[][][] dp = new int[len + 1][len + 1][K + 1];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= len; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        for (int i = 1; i <= len; i++) {
            dp[i][i][1] = 0;
        }
        for (int n = 2; n <= len; n++) {
            for (int i = 1; i + n - 1 <= len; i++) {
                int j = i + n - 1;
                for (int m = i; m < j; m++) {
                    for (int k = 2; k <= K; k++) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum[j] - sum[i - 1];
            }
        }
        return dp[1][len][1];
    }
}



class Solution {
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) return -1;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        int[][] dp = new int[len + 1][len + 1];
        for (int n = K; n <= len; n++) {
            for (int i = 1, j = n; j <= len; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = i; m < j; m += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) dp[i][j] += sum[j] - sum[i - 1];
            }
        }
        return dp[1][len];
    }
}



class Solution {
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        int[] sum = new int[len + 1];
        if ((len - 1) % (K - 1) != 0) return -1;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        if (len == 1) return 0;
        int[][] dp = new int[len][len];
        for (int i = 0; i <= (len - 1) / (K - 1); i++) {
            for (int left = 0, right = i * (K - 1); right < len; left++, right++) {
                if (i > 0) dp[left][right] += sum[right + 1] - sum[left];
                if (i > 1) dp[left][right] += dfs(dp, left, right, K, K, i - 1);
            }
        }
        return dp[0][len - 1];
    }
    
    private int dfs(int[][] dp, int start, int end, int K, int rem, int count) {
        if (rem == 1) {
            return dp[start][end];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= count; i++) {
            int next = 1 + i * (K - 1);
            ans = Math.min(ans, dp[start][start + next - 1] + dfs(dp, start + next, end, K, rem - 1, count - i));
        }
        return ans;
    }
}