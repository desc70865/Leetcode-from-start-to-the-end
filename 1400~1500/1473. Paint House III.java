/* 
There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n), some houses that have been painted last summer should not be painted again.

A neighborhood is a maximal group of continuous houses that are painted with the same color.

For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
Given an array houses, an m x n matrix cost and an integer target where:

houses[i]: is the color of the house i, and 0 if the house is not painted yet.
cost[i][j]: is the cost of paint the house i with the color j + 1.
Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods. If it is not possible, return -1.

 

Example 1:

Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
Output: 9
Explanation: Paint houses of this way [1,2,2,1,1]
This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
Example 2:

Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
Output: 11
Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. 
Cost of paint the first and last house (10 + 1) = 11.
Example 3:

Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
Output: 5
Example 4:

Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
Output: -1
Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
 

Constraints:

m == houses.length == cost.length
n == cost[i].length
1 <= m <= 100
1 <= n <= 20
1 <= target <= m
0 <= houses[i] <= n
1 <= cost[i][j] <= 10^4
 */

class Solution {
    static final int INF = 0x7fffffff;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] > 0 && houses[i] - 1 != j) {
                    continue;
                }
                for (int k = 0; k < target; ++k) {
                    for (int j0 = 0; j0 < n; ++j0) {
                        if (j == j0) {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = 0;
                                }
                            } else {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                            }
                        } else if (i > 0 && k > 0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1]);
                        }
                    }
                    if (dp[i][j][k] != INF && houses[i] == 0) {
                        dp[i][j][k] += cost[i][j];
                    }
                }
            }
        }
        int ans = INF;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INF ? -1 : ans;
    }
}



class Solution {
    static final int INF = 0x7fffffff;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < m; ++i) {
            --houses[i];
        }
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        int[][][] best = new int[m][target][3];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < target; ++j) {
                best[i][j][0] = best[i][j][2] = INF;
                best[i][j][1] = -1;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }
                for (int k = 0; k < target; ++k) {
                    if (i == 0) {
                        if (k == 0) {
                            dp[i][j][k] = 0;
                        }
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                        if (k > 0) {
                            // 使用 best(i - 1, k - 1) 直接得到 dp(i, j, k) 的值
                            int first = best[i - 1][k - 1][0];
                            int firstIdx = best[i - 1][k - 1][1];
                            int second = best[i - 1][k - 1][2];
                            dp[i][j][k] = Math.min(dp[i][j][k], (j == firstIdx ? second : first));
                        }
                    }
                    if (dp[i][j][k] != INF && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }
                    // 用 dp(i, j, k) 更新 best(i, k)
                    int first = best[i][k][0];
                    int firstIdx = best[i][k][1];
                    int second = best[i][k][2];
                    if (dp[i][j][k] < first) {
                        best[i][k][2] = first;
                        best[i][k][0] = dp[i][j][k];
                        best[i][k][1] = j;
                    } else if (dp[i][j][k] < second) {
                        best[i][k][2] = dp[i][j][k];
                    }
                }
            }
        }
        int ans = INF;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INF ? -1 : ans;
    }
}