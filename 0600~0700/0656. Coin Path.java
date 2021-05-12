/* 
You are given an integer array coins (1-indexed) of length n and an integer maxJump. You can jump to any index i of the array coins if coins[i] != -1 and you have to pay coins[i] when you visit index i. In addition to that, if you are currently at index i, you can only jump to any index i + k where i + k <= n and k is a value in the range [1, maxJump].

You are initially positioned at index 1 (coins[1] is not -1). You want to find the path that reaches index n with the minimum cost.

Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. If there are multiple paths with the same cost, return the lexicographically smallest such path. If it is not possible to reach index n, return an empty array.

A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of length y, if and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no such j exists, then x < y.

 

Example 1:

Input: coins = [1,2,4,-1,2], maxJump = 2
Output: [1,3,5]
Example 2:

Input: coins = [1,2,4,-1,2], maxJump = 1
Output: []
 

Constraints:

1 <= coins.length <= 1000
-1 <= coins[i] <= 100
coins[1] != -1
1 <= maxJump <= 100
 */

class Solution {
    static final int INF = 1_000_000_007;
    int n;
    int[] dp, next, coins;

    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        this.n = coins.length;
        this.dp = new int[n];
        Arrays.fill(dp, -1);
        this.next = new int[n];
        Arrays.fill(next, -1);
        this.coins = coins;
        int min = dfs(0, maxJump);
        // System.out.println(min);
        List<Integer> ans = new ArrayList<>();
        if (min >= INF) {
            return ans;
        }
        for (int p = 0; p < n - 1; p = next[p]) {
            ans.add(p + 1);
        }
        ans.add(n);
        return ans;
    }

    private int dfs(int start, int maxJump) {
        if (start == n - 1) {
            return 0;
        }
        if (dp[start] >= 0) {
            return dp[start];
        }
        int ans = INF;
        for (int i = start + 1; i <= start + maxJump && i < n; ++i) {
            if (coins[i] == -1) {
                continue;
            }
            int cost = dfs(i, maxJump);
            if (cost >= INF) {
                continue;
            }
            if (cost < ans) {
                next[start] = i;
                ans = cost;
            }
        }
        return dp[start] = coins[start] + ans;
    }
}