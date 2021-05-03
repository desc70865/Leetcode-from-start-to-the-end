/* 
There are m boys and n girls in a class attending an upcoming party.

You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.

Return the maximum possible number of accepted invitations.

 

Example 1:

Input: grid = [[1,1,1],
               [1,0,1],
               [0,0,1]]
Output: 3
Explanation: The invitations are sent as follows:
- The 1st boy invites the 2nd girl.
- The 2nd boy invites the 1st girl.
- The 3rd boy invites the 3rd girl.
Example 2:

Input: grid = [[1,0,1,0],
               [1,0,0,0],
               [0,0,1,0],
               [1,1,1,0]]
Output: 3
Explanation: The invitations are sent as follows:
-The 1st boy invites the 3rd girl.
-The 2nd boy invites the 1st girl.
-The 3rd boy invites no one.
-The 4th boy invites the 2nd girl.
 

Constraints:

grid.length == m
grid[i].length == n
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
 */

class Solution {
    int m, n;
    int[][] grid;
    int[] invite;

    public int maximumInvitations(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.invite = new int[n];
        Arrays.fill(invite, -1);
        int match = 0;
        for (int boy = 0; boy < m; ++boy) {
            if (dfs(boy, new boolean[n])) {
                ++match;
            }
        }
        return match;
    }

    private boolean dfs(int boy, boolean[] match) {
        for (int girl = 0; girl < n; ++girl) {
            if (match[girl] || grid[boy][girl] == 0) continue;
            match[girl] = true;
            if (invite[girl] == -1 || dfs(invite[girl], match)) {
                invite[girl] = boy;
                return true;
            }
        }
        return false;
    }
}

// 匈牙利算法
// Hungarian algorithm