/* 
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6
 

Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 */

class Solution {
    static final int[] dirs = {0, 1, 0, -1, 0};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        char[][] s = new char[m][n];
        int targetStatus = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][][] v = new boolean[m + 1][n + 1][64];
        for (int i = 0; i < m; ++i) {
            s[i] = grid[i].toCharArray();
            for (int j = 0; j < n; ++j) {
                if (s[i][j] == '@') {
                    queue.offer(new int[] {i, j, 0});
                    v[i][j][0] = true;
                } else if (s[i][j] >= 'a') {
                    targetStatus |= 1 << s[i][j] - 'a';
                }
            }
        }
        for (int step = 1; queue.size() > 0; ++step) {
            for (int size = queue.size(); size > 0; --size) {
                int[] node = queue.poll();
                for (int i = 0; i < 4; ++i) {
                    int nx = node[0] + dirs[i];
                    if (nx < 0 || nx >= m) {
                        continue;
                    }
                    int ny = node[1] + dirs[i + 1];
                    if (ny < 0 || ny >= n) {
                        continue;
                    }
                    int status = node[2];
                    if (s[nx][ny] == '#' || v[nx][ny][status]) {
                        continue;
                    }
                    char c = s[nx][ny];
                    if (c >= 'a') {
                        status |= 1 << c - 'a';
                        if (status == targetStatus) {
                            return step;
                        }
                    } else if ('A' <= c && c <= 'F' && (status & (1 << c - 'A')) == 0) {
                        continue;
                    }
                    v[nx][ny][status] = true;
                    queue.offer(new int[] {nx, ny, status});
                }
            }
        }
        return -1;
    }
}