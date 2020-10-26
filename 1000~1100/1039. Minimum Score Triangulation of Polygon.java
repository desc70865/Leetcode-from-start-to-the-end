/* 
Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.

Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.

 

Example 1:

Input: [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
Example 2:



Input: [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
Example 3:

Input: [1,3,1,4,1,5]
Output: 13
Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 

Note:

3 <= A.length <= 50
1 <= A[i] <= 100
 */

class Solution {
    public int minScoreTriangulation(int[] A) {
        int N = A.length;
        int[][] dp = new int[N][N];
        for (int[] item : dp)
            Arrays.fill(item, Integer.MAX_VALUE);
        for (int i = 0; i < N; ++i)
            dp[i][(i + 1) % N] = 0;
        for (int len = 2; len < N; ++len) {
            for (int i = 0; i < N; ++i) {
                int j = (i + len) % N;
                for (int k = (i + 1) % N; k != j; k = (k + 1) % N)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
            }
        }
        return dp[0][N - 1];
    }
}



class Solution {
    private int mm[][];
    public int minScoreTriangulation(int[] A) {
        mm = new int[55][55];
        return dfs(A, 0, A.length - 1);
    }

    private int dfs(int[] A, int L, int R) {
        if (mm[L][R] != 0) return mm[L][R];
        if (R - L < 2) return 0;
        if (R - L == 2) return A[L] * A[L + 1] * A[R];
        int min = Integer.MAX_VALUE;
        for (int M = L + 1; M < R; M++) {
            min = Math.min(min, dfs(A, L, M) + A[L] * A[M] * A[R] + dfs(A, M, R));
        }
        return mm[L][R] = min;
    }
}