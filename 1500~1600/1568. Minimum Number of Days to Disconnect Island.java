/* 
Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

 

Example 1:



Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:

Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
Example 3:

Input: grid = [[1,0,1,0]]
Output: 0
Example 4:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,0,1,1]]
Output: 1
Example 5:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[i].length <= 30
grid[i][j] is 0 or 1.
 */

class Solution {
    
    private int rows;
    private int cols;
    private int turni[] = {-1,0,1,0};
    private int turnj[] = {0,-1,0,1};
    private int timer = 1;
    
    private boolean isValidMove(int x,int y)
    {
        if(x>=0 && y>=0 && x<rows && y<cols)
            return true;
        return false;
    }
    
    private void dfs(int srci,int srcj, int[][] visited, int[][] grid)
    {
        visited[srci][srcj] = 1;
        for(int i=0;i<4;i++){
            
            int x = turni[i] + srci;
            int y = turnj[i] + srcj;
            if(isValidMove(x, y) && grid[x][y] == 1 && visited[x][y] == 0){
                dfs(x, y, visited, grid);
            }
        }
    }
    
    private boolean checkTwoSeparatedGroup(int[][] grid,int[][] visited){
        
        int connectedGroups = 0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 1 && (visited[i][j]==0))
                {
                    connectedGroups++;
                    if(connectedGroups >=2)
                        return true;
                    dfs(i,j, visited, grid);
                }
            }
        }
        
        return false;
    }
    
    private boolean detectBridge(int srci,int srcj, int pari, int parj, int[][] visited,int[][] grid,int[][] low,int[][] in){
         
        visited[srci][srcj] = 1;
        low[srci][srcj]=timer;
        in[srci][srcj]=timer;
        timer++;
        for(int i=0;i<4;i++)
        {
            int x = srci + turni[i];
            int y = srcj + turnj[i];
            if(isValidMove(x, y) && grid[x][y] == 1){
                
            if((x==pari) && (y==parj))
                continue;
                
            if(visited[x][y] == 1)
              low[srci][srcj] = Math.min(low[srci][srcj], in[x][y]);
            else
            {
                if(detectBridge(x, y, srci, srcj, visited, grid, low, in))
                    return true;
                
                if(low[x][y] > in[srci][srcj])
                    return true;
                
                low[srci][srcj] = Math.min(low[srci][srcj], low[x][y]);
            }
                
            }
        }
        
        return false;
    }
    
    public int minDays(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[][] visited = new int[rows][cols];
        
        if(checkTwoSeparatedGroup(grid, visited))
            return 0;
        
        int low[][] = new int[rows][cols];
        int in[][] = new int[rows][cols];
        
        int connectedNodes = 0;
        
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                visited[i][j] = 0;
        
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 1)
                {
                    connectedNodes++;
                    if(connectedNodes >= 3)
                    {
                        if(detectBridge(i, j, -1, -1, visited, grid, low, in))
                            return 1;
                        return 2;
                    }
                }
            }
        
       return connectedNodes;
    }
}