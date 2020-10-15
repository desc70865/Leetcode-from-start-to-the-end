/* 
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.

 

Example 1:


Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.
Example 3:

Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6
Example 4:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix
 

Constraints:

m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.
 */

class Solution {
    int M, N;
    int[][] dirs = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    int[][] A;
    int res;
    int cnt;
    public int minFlips(int[][] mat) {
        A = mat;
        M = mat.length; N = mat[0].length;
        res = 0;
        for (int[] line: mat) for (int num: line) res += num;
        cnt = 0;
        return dfs(0) ? cnt : -1;
    }

	private boolean dfs(int p) {
        if (res == 0) return true;
		if (p == M * N) return false;
		int x = p / N, y = p % N;
		if (x == 0) {
			if (dfs(p + 1)) return true;
			update(x, y, 1);
			if (dfs(p + 1)) return true;
			update(x, y, -1);
			return false;
		}
		if (A[x - 1][y] == 0) {
			return dfs(p + 1);
		}
		update(x, y, 1);
		if (dfs(p + 1)) return true;
		update(x, y, -1);
		return false;
	}
	
	private void update(int x, int y, int add) {
        cnt += add;
		reverse(x, y);
		for (int[] dir: dirs) reverse(x + dir[0], y + dir[1]);
	}
	
	private void reverse(int x, int y) {
		if (x < 0 || y < 0 || x >= M || y >= N) return;
		A[x][y] = 1 - A[x][y];
		res += A[x][y] * 2 - 1;
	}
}