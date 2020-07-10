/* 
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 0; i < N; i++) {
            List<Integer> level = triangle.get(i);
            for (int j = i; j >= 0; j--) {
                dp[j+1] = Math.min(dp[j], dp[j+1]) + level.get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= N; k++) {
            min = Math.min(min, dp[k]);
        }
        return min;
    }
}