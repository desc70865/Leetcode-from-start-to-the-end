/* 
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)
 

Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */

class Solution {
    private int[][] dungeon, dp;
    private int raw, col;
    
    public int calculateMinimumHP(int[][] dungeon) {
        this.dungeon = dungeon;
        raw = dungeon.length - 1; col = dungeon[0].length - 1;
        dp = new int[raw][col];
        return dfs(1, raw, col);
    }
    
    private int dfs(int health, int x, int y) {
        if (x < 0 || y < 0) {
            return Integer.MAX_VALUE;
        }
        health -= dungeon[x][y];
        if (health < 1) {
            health = 1;
        }
        if (x == 0 && y == 0) {
            return health;
        }
        return Math.min(dfs(health, x-1, y),
                        dfs(health, x, y-1));
    }
}



class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int raw = dungeon.length, col = dungeon[0].length;
        int[][] dp = new int[raw+1][col+1];
        for (int i = 0; i <= raw; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[raw][col-1] = dp[raw-1][col] = 1;
        
        for (int i = raw-1; i >= 0; i--) {
            for (int j = col-1; j >= 0; j--) {
                int min = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        
        return dp[0][0];
    }
}



class Solution {
    private int R, C;
    public int calculateMinimumHP(int[][] dungeon) {
    	R = dungeon.length;
    	C = dungeon[0].length;
    	Integer[][] dp = new Integer[R][C];
    	dp[R - 1][C - 1] = dungeon[R - 1][C - 1] > 0 ? 1 : 1 - dungeon[R - 1][C - 1];
        return helper(dungeon, dp, 0, 0);
    }
    
    public int helper(int[][] dungeon, Integer[][] dp, int r, int c) {
    	if (dp[r][c] != null) return dp[r][c];
        
    	int down = r == R - 1 ? Integer.MAX_VALUE : helper(dungeon, dp, r + 1, c);
        int right = c == C - 1 ? Integer.MAX_VALUE : helper(dungeon, dp, r, c + 1);
        dp[r][c] = Math.max(1, Math.min(down, right) - dungeon[r][c]);
        return dp[r][c];
    }
}