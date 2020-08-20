/* 
You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 

Example 1:

Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4
 

Note:

1 <= K <= 100
1 <= N <= 10000
 */

class Solution {
    public int superEggDrop(int K, int N) {
        
    }
}



class Solution {
    public int superEggDrop(int K, int N) {
        int X = (int) (Math.log(N) / Math.log(2));
        while (N > helper(K, X)) X++;
        return X;
    }

    private int helper(int k, int sum) {
        if (k == 1) return sum;
        int r = sum;
        for (int i = 1; i < r; i++) sum += helper(k-1, i);
        return sum;
    }
}



class Solution {
    public int superEggDrop(int K, int N) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; ++i)
            dp[i] = i;

        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N+1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                    x++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
            }

            dp = dp2;
        }

        return dp[N];
    }
}



class Solution {
    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    Map<Integer, Integer> memo = new HashMap();
    public int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K-1, x-1);
                    int t2 = dp(K, N-x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }

                ans = 1 + Math.min(Math.max(dp(K-1, lo-1), dp(K, N-lo)),
                                   Math.max(dp(K-1, hi-1), dp(K, N-hi)));
            }
            memo.put(N * 100 + K, ans);
        }
        return memo.get(N * 100 + K);
    }
}

/**
Drop eggs is a very classical problem.
Some people may come up with idea O(KN^2)
where dp[K][N] = 1 + max(dp[K - 1][i - 1], dp[K][N - i]) for i in 1...N.
However this idea is very brute force, for the reason that you check all possiblity.

So I consider this problem in a different way:
dp[M][K]means that, given K eggs and M moves,
what is the maximum number of floor that we can check.

The dp equation is:
dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1,
which means we take 1 move to a floor,
if egg breaks, then we can check dp[m - 1][k - 1] floors.
if egg doesn't breaks, then we can check dp[m - 1][k] floors.

dp[m][k] is the number of combinations and it increase exponentially to N
*/

class Solution {
    public int superEggDrop(int K, int N) {
        /* int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        } */
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; ++m)
            for (int k = K; k > 0; --k)
                dp[k] += dp[k - 1] + 1;
        return m;
    }
}



class Solution {
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int m = 0;
        for (m = 0; dp[K] < N; m++) {
            for (int i = K; i > 0; i--) dp[i] += dp[i - 1] + 1;
        }
        return m;
    }
}

// range -> [x - dp[T-1][K-1], x + dp[T-1][K]] sum = dp[T-1][K-1] + 1 + dp[T-1][K];
// dp[T][K] = dp[T-1][K-1] + 1 + dp[T-1][K];
// dp[a][b] b eggs for a times throw -> maximum range
// in which we don't care where to throw.

class Solution {
    int step = 0;
    int[] dp = new int[101];
    public int superEggDrop(int K, int N) {
        for (; dp[K] < N; step++) {
            for (int i = K; i > 0; i--) dp[i] += dp[i-1] + 1;
        }
        return step;
    }
}