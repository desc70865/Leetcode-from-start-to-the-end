/* 
Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.


You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.

 

Example 1:


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
Example 4:

Input: grid = [[1,1,1,1,1,1,3]]
Output: true
Example 5:

Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
Output: true
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
 */

class Solution {
    // state table, -1代表状态转换出错
    int[][] stateTable = new int[][] {
            {0, -1, 1, -1, 3, -1},
            {-1, 1, -1, -1, 2, 0},
            {2, -1, -1, 1, -1, 3},
            {-1, 3, 2, 0, -1, -1},
    };

    int[] dx = new int[] {0, 1, 0, -1};
    int[] dy = new int[] {1, 0, -1, 0};
    int[] initState = new int[] {0, 1, 0, -1, -1, 1};
    int[][] grid;
    int m, n;

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        if (m == 1 && n == 1) return true;

        if (grid[0][0] == 4) {
            return helper(0, 0, 2) || helper(0, 0, 3);
        } else {
            return helper(0, 0, initState[grid[0][0] - 1]);
        }
    }
    
    private boolean helper(int x, int y, int state) {
        if (state == -1) return false;
        while (x >= 0 && x < m && y >= 0 && y < n) {
            state = stateTable[state][grid[x][y] - 1];
            if (state == -1) return false;
            if (x == m - 1 && y == n - 1) return true;
            y += dy[state];
            x += dx[state];
            if (x == 0 && y == 0) return false;
        }
        return false;
    }
}