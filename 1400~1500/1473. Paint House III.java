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
        int[][][] dp = new int[m + 1][target + 1][n];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= target; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int newColor = 0; newColor < n; ++newColor) {
                if (houses[i - 1] > 0 && houses[i - 1] != newColor + 1) {
                    continue;
                }
                for (int j = 1; j <= target; ++j) {
                    for (int oldColor = 0; oldColor < n; ++oldColor) {
                        if (newColor == oldColor && i == 1 && j == 1) {
                            dp[i][j][newColor] = 0;
                        } else {
                            dp[i][j][newColor] = Math.min(dp[i][j][newColor], 
                                dp[i - 1][j - (newColor == oldColor ? 0 : 1)][oldColor]);
                        }
                    }
                    if (dp[i][j][newColor] != INF && houses[i - 1] == 0) {
                        dp[i][j][newColor] += cost[i - 1][newColor];
                    }
                }
            }
        }
        int ans = INF;
        for (int num: dp[m][target]) {
            ans = Math.min(ans, num);
        }
        return ans == INF ? -1 : ans;
    }
}



class Solution {
    static final int INF = 0x7fffffff;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][] dp = auto(target + 1, n);
        for (int i = 1; i <= m; ++i) {
            int[][] dq = auto(target + 1, n);
            for (int newColor = 0; newColor < n; ++newColor) {
                if (houses[i - 1] > 0 && houses[i - 1] != newColor + 1) {
                    continue;
                }
                for (int j = 1; j <= target; ++j) {
                    for (int oldColor = 0; oldColor < n; ++oldColor) {
                        if (newColor == oldColor && i == 1 && j == 1) {
                            dq[j][newColor] = 0;
                        } else {
                            dq[j][newColor] = Math.min(dq[j][newColor], 
                                dp[j - (newColor == oldColor ? 0 : 1)][oldColor]);
                        }
                    }
                    if (dq[j][newColor] != INF && houses[i - 1] == 0) {
                        dq[j][newColor] += cost[i - 1][newColor];
                    }
                }
            }
            dp = dq;
        }
        int ans = INF;
        for (int num: dp[target]) {
            ans = Math.min(ans, num);
        }
        return ans == INF ? -1 : ans;
    }

    private int[][] auto(int x, int y) {
        int[][] ans = new int[x][y];
        for (int i = 0; i < x; ++i) {
            Arrays.fill(ans[i], INF);
        }
        return ans;
    }
}



class Solution {
    static final int INF = 0x7fffffff;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][target][n];
        int[][][] optimal = new int[m][target][3];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < target; ++j) {
                Arrays.fill(dp[i][j], INF);
                optimal[i][j] = new int[] {-1, INF, INF};
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int color = 0; color < n; ++color) {
                if (houses[i] != 0 && houses[i] - 1 != color) {
                    continue;
                }
                for (int j = 0; j < target; ++j) {
                    if (i == 0) {
                        if (j == 0) {
                            dp[i][j][color] = 0;
                        }
                    } else {
                        dp[i][j][color] = dp[i - 1][j][color];
                        if (j > 0) {
                            int[] p = optimal[i - 1][j - 1];
                            dp[i][j][color] = Math.min(dp[i][j][color], 
                                (color == p[0] ? p[2] : p[1]));
                        }
                    }
                    if (dp[i][j][color] != INF && houses[i] == 0) {
                        dp[i][j][color] += cost[i][color];
                    }
                    int[] p = optimal[i][j];
                    if (dp[i][j][color] < p[1]) {
                        optimal[i][j] = new int[] {color, dp[i][j][color], p[1]};
                    } else if (dp[i][j][color] < p[2]) {
                        optimal[i][j][2] = dp[i][j][color];
                    }
                }
            }
        }
        int ans = INF;
        for (int num: dp[m - 1][target - 1]) {
            ans = Math.min(ans, num);
        }
        return ans == INF ? -1 : ans;
    }
}



class Solution {
    static final int INF = 0x7fffffff;
    int[][][] dp;
    int[] houses;
    int[][] cost;
    int m, n;
    
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.dp = new int[m][n + 1][target + 1];
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        return dfs(0, 0, target);
    }
    
    private int dfs(int pos, int lastColor, int target) {
        if (target < 0 || target > m - pos) return -1;
        if (pos == m && target == 0) return 0;
        if (dp[pos][lastColor][target] != 0) return dp[pos][lastColor][target];
        if (houses[pos] > 0) {
            return dp[pos][lastColor][target] = dfs(pos + 1, houses[pos], target - (houses[pos] == lastColor ? 0 : 1));
        }
        int ans = INF;
        for (int color = 1; color <= n; ++color) {
            int val = dfs(pos + 1, color, target - (color == lastColor ? 0 : 1));
            if (val >= 0) ans = Math.min(ans, cost[pos][color - 1] + val);
        }
        return dp[pos][lastColor][target] = ans == INF ? -1 : ans;
    }
}