/* 
We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)

 

Example 1:

Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (r0, c0) to other cells are: [0,1]
Example 2:

Input: R = 2, C = 2, r0 = 0, c0 = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
Example 3:

Input: R = 2, C = 3, r0 = 1, c0 = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 

Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C
 */

class Solution {
    int r0, c0;
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        this.r0 = r0;
        this.c0 = c0;
        int[][] res = new int[R * C][2];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[idx++] = new int[] {i, j};
            }
        }
        Arrays.sort(res, (a, b) -> f(a) - f(b));
        return res;
    }

    private int f(int[] A) {
        return Math.abs(A[0] - r0) + Math.abs(A[1] - c0);
    }
}



class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int [] dis = new int[R * C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dis[i * C + j] = (Math.abs(i - r0) + Math.abs(j - c0) + 1) * 10000 + i * 100 + j;
            }
        }
        Arrays.sort(dis);
        int[][] ans = new int[R * C][2];
        for (int i = 0; i < R * C; i++) {
            ans[i][0] = dis[i] % 10000 / 100;
            ans[i][1] = dis[i] % 10000 % 100;
        }
        return ans;
    }
}



class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] re = new int[R * C][2];
        int dist = 0;
        int cnt = 0;
        int[] factor = {-1, 1};
        while (cnt < R * C) {
            for (int rowDist = 0; rowDist <= dist; rowDist++) {
                int colDist = dist - rowDist;
                for (int i = 0; i < 2; i++) {
                    int row = r0 + factor[i] * rowDist;
                    for (int j = 0; j < 2; j++) {
                        int col = c0 + factor[j] * colDist;
                        if (row >= 0 && row < R && col >= 0 && col < C) {
                            re[cnt][0] = row;
                            re[cnt][1] = col;
                            cnt++;
                        }
                        if (colDist == 0) break;
                    }
                    if (rowDist == 0) break;
                }
            }
            dist++;
        }
        return re;
    }
}



class Solution {
    int[][] res;
    int indx;
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        res = new int[R * C][2];
        
        int[][] arr = new int[R][C];
        arr[r0][c0] = 1;
        res[indx][0] = r0;
        res[indx++][1] = c0;
        for(int i = 0; i < res.length; i++)
            goThroughWholeArr(arr, res[i][0], res[i][1]);
        
        return res;
    }
    
    private void goThroughWholeArr(int[][] arr, int r, int c){        
        if(r > 0 && arr[r - 1][c] == 0){
            arr[r - 1][c] = 1;
            res[indx][0] = r - 1;
            res[indx++][1] = c;
        }
        
        if(c > 0 && arr[r][c - 1] == 0){
            arr[r][c - 1] = 1;
            res[indx][0] = r;
            res[indx++][1] = c - 1;
        }
        
        if(r < arr.length - 1 && arr[r + 1][c] == 0){
            arr[r + 1][c] = 1;
            res[indx][0] = r + 1;
            res[indx++][1] = c;
        }
        
        if(c < arr[0].length - 1 && arr[r][c + 1] == 0){
            arr[r][c + 1] = 1;
            res[indx][0] = r;
            res[indx++][1] = c + 1;
        }
    }
}