/* 
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

 



 

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
 

Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
 */

class Solution {
    int[][] dirs = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    Double[][][] dp;

    public double knightProbability(int N, int K, int r, int c) {
        dp = new Double[N / 2 + 1][N / 2 + 1][K + 1];
        return helper(N, K, r, c);
    }

    private double helper(int N, int K, int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) return 0.0;
        if (K == 0) return 1.0;
        r = Math.min(r, N - r - 1);
        c = Math.min(c, N - c - 1);
        if (dp[r][c][K] != null) return dp[r][c][K];
        double sum = 0.0;
        for (int[] dir: dirs) {
            sum += helper(N, K - 1, r + dir[0], c + dir[1]);
        }
        return dp[r][c][K] = sum * 0.125;
    }
}