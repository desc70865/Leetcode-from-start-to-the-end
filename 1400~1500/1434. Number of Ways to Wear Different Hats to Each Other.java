/* 
There are n people and 40 types of hats labeled from 1 to 40.

Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.

Return the number of ways that the n people wear different hats to each other.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions. 
First person choose hat 3, Second person choose hat 4 and last one hat 5.
Example 2:

Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats
(3,5), (5,3), (1,3) and (1,5)
Example 3:

Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
Example 4:

Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
Output: 111
 

Constraints:

n == hats.length
1 <= n <= 10
1 <= hats[i].length <= 40
1 <= hats[i][j] <= 40
hats[i] contains a list of unique integers.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        Set<Integer>[] hat = new HashSet[41];
        for (int i = 0; i <= 40; ++i) {
            hat[i] = new HashSet<>(n);
        }
        for (int i = 0; i < n; ++i) {
            for (int h: hats.get(i)) {
                hat[h].add(i);
            }
        }
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for (int i = 1; i <= 40; ++i) {
            for (int mask = (1 << n) - 1; mask >= 0; --mask) {
                for (int p: hat[i]) {
                    if ((mask & (1 << p)) != 0) {
                        continue;
                    }
                    int k = mask + (1 << p);
                    dp[k] = (dp[k] + dp[mask]) % MOD;
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int[] p = new int[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; ++i) {
            p[i] = p[i - 1] << 1;
        }
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for (int i = 1; i <= 40; ++i) {
            int[] dq = dp.clone();
            for (int j = 0; j < hats.size(); ++j) {
                if (! hats.get(j).contains(i)) {
                    continue;
                }
                for (int k = 0; k < (1 << n); ++k) {
                    if ((k & p[j]) != 0) {
                        continue;
                    }
                    dq[k | p[j]] = (dq[k | p[j]] + dp[k]) % MOD;
                }
            }
            dp = dq;
        }
        return dp[(1 << n) - 1];
    }
}