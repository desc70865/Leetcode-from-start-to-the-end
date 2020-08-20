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



class Solution {
    public int minDistance(int[] houses, int K) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] rec = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int mid = i + j >> 1;
                for (int k = i; k <= j; k++) {
                    rec[i][j] += Math.abs(houses[k] - houses[mid]);
                }
            }
        }
        int[][] dp = new int[n][K+1];
        for (int i = 0; i < n; i++) dp[i][1] = rec[0][i];
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < Math.min(i+1, K); j++) {
                for (int k = j - 1; k <= i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k-1][j-1] + rec[k][i]);
                }
            }
        }
        return dp[n-1][K];
    }
}