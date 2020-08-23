/* 
Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.

Return the minimum total distance between each house and its nearest mailbox.

The answer is guaranteed to fit in a 32-bit signed integer.

 

Example 1:



Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 
Example 2:



Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
Example 3:

Input: houses = [7,4,6,1], k = 1
Output: 8
Example 4:

Input: houses = [3,6,14,10], k = 4
Output: 0
 

Constraints:

n == houses.length
1 <= n <= 100
1 <= houses[i] <= 10^4
1 <= k <= n
Array houses contain unique integers.
 */

class Solution {
    public int minDistance(int[] houses, int k) {
        
    }
}

// dp[i][j] = min(dp[k - 1][j - 1] + rec[k][i]) k -> [j-1, i]
// rec[l][r] cost -> [l, r] by preprocess

// time complexity O(n ^ 3 + n ^ 2 * k)
class Solution {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
            Arrays.sort(houses);
            // dp[i][j]表示前i个房子摆j个邮筒时的最短距离
            int[][] dp = new int[n + 1][k + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }
            // dis[i][j] 表示范围[i,j]的房子共用一个邮筒时的最短距离
            int[][] dis = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    for (int x = i, y = j; x < y; x++, y--) {
                        dis[i][j] += houses[y - 1] - houses[x - 1];
                    }
                }
            }
            dp[0][0] = 0;
            // 求解DP，进行状态转移
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i && j <= k; j++) {
                    for (int p = i; p >= 1; p--) {
                        if (dp[p - 1][j - 1] != -1 && (dp[i][j] == -1 || dp[i][j] > dp[p - 1][j - 1] + dis[p][i])) {
                            dp[i][j] = dp[p - 1][j - 1] + dis[p][i];
                        }
                    }
                }
            }
        return dp[n][k];
    }
}



class Solution {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        if (n == k) return 0;
        Arrays.sort(houses);
        int[][] dis = new int[n][n];
        for(int len = 2; len <= n; len++) {
            for(int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                dis[l][r] = houses[r] - houses[l] + dis[l+1][r-1];
            }
        }
        return dfs(0, k, houses, dis, new int[n][k+1]);
    }
    
    private int dfs(int i, int k, int[] houses, int[][] dis, int[][] dp) {
        int n = houses.length;
        if (i == n) return 0;
        if (k == 1) return dis[i][n-1];
        if (dp[i][k] > 0) return dp[i][k]-1;
        
        int min = Integer.MAX_VALUE;
        for(int j = i; j < n && n-j >= k; j++) {
            int result = dfs(j+1, k-1, houses, dis, dp);
            if (result >= 0) min = Math.min(min, dis[i][j] + result);
        }
        return (dp[i][k] = min+1)-1;
    }
}