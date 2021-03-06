/* 
Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:

Every song is played at least once
A song can only be played again only if K other songs have been played
Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: N = 3, L = 3, K = 1
Output: 6
Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
Example 2:

Input: N = 2, L = 3, K = 0
Output: 6
Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
Example 3:

Input: N = 2, L = 3, K = 1
Output: 2
Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
 

Note:

0 <= K < N <= L <= 100
 */

class Solution {
    int M = 1_000_000_007;
    public int numMusicPlaylists(int N, int L, int K) {
        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; i ++) {
            for (int j = 1; j <= N; j ++) {
                dp[i][j] = dp[i - 1][j - 1] * (N - j + 1);
                if (j > K) dp[i][j] += dp[i - 1][j] * (j - K);
                dp[i][j] %= M;
            }
        }
        return (int) dp[L][N];
    }
}



class Solution {
    int M = 1_000_000_007;
    public int numMusicPlaylists(int N, int L, int K) {
        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= L; i++) {
            for (int j = Math.min(i, N); j >= Math.min(i, K + 2); j--) {
                dp[j] *= Math.max(0, j - K);
                dp[j] += dp[j - 1] * (N - j + 1);
                dp[j] %= M;
            }
            // System.out.println(Arrays.toString(dp));
        }
        return (int) dp[N];
    }
}



class Solution {
    public int numMusicPlaylists(int N, int L, int K) {
        int M = 1_000_000_007;
        int len = L - N;
        long[] dp = new long[len + 1];
        Arrays.fill(dp, 1);
        for (int p = 2; p <= N - K; p++) {
            for (int i = 1; i <= len; i++) {
                dp[i] += dp[i-1] * p;
                dp[i] %= M;
            }
            // System.out.println(Arrays.toString(dp));
        }
        long ans = dp[len];
        for (int k = 2; k <= N; k++) ans = ans * k % M;
        return (int) ans;
    }
}