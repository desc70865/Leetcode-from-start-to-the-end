/* 
Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..

Students must be placed in seats in good condition.

 

Example 1:


Input: seats = [["#",".","#","#",".","#"],
                [".","#","#","#","#","."],
                ["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam. 
Example 2:

Input: seats = [[".","#"],
                ["#","#"],
                ["#","."],
                ["#","#"],
                [".","#"]]
Output: 3
Explanation: Place all students in available seats. 

Example 3:

Input: seats = [["#",".",".",".","#"],
                [".","#",".","#","."],
                [".",".","#",".","."],
                [".","#",".","#","."],
                ["#",".",".",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.
 

Constraints:

seats contains only characters '.' and'#'.
m == seats.length
n == seats[i].length
1 <= m <= 8
1 <= n <= 8
 */

class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int[][] dp = new int[1 + m][1 << n];
        for (int level = 1; level <= m; level++) {
            for (int mask = getMask(seats[level - 1]), curr = mask; curr != 0; curr = (curr - 1) & mask) {
                if (inconsistent(curr, curr)) continue;
                for (int prev = 0, cnt = Integer.bitCount(curr); prev < 1 << n; prev++) {
                    if (inconsistent(curr, prev)) continue;
                    dp[level][curr] = Math.max(dp[level][curr], dp[level - 1][prev] + cnt);
                }
            }
            dp[level][0] = max(dp[level - 1]);
        }
        return max(dp[m]);
    }

    private boolean inconsistent(int a, int b) {
        return (a & b << 1) != 0 || (a & b >> 1) != 0;
    }

    private int getMask(char[] chs) {
        int ans = 0;
        for (char c: chs) {
            ans <<= 1;
            ans |= c == '.' ? 1 : 0;
        }
        return ans;
    }

    private int max(int[] nums) {
        int ans = 0;
        for (int num: nums) {
            ans = Math.max(ans, num);
        }
        return ans;
    }
}



class Solution {
    char[][] seats;
    int[][] dp;
    int[] mask;
    int m, n;

    public int maxStudents(char[][] seats) {
        this.m = seats.length;
        this.n = seats[0].length;
        this.dp = new int[m][1 << n];
        this.seats = seats;
        this.mask = new int[m];
        Arrays.fill(mask, -1);
        for (int[] e: dp) Arrays.fill(e, -1);
        return arrange(0, 0);
    }

    private int arrange(int level, int last) {
        if (level >= m) return 0;
        int stat = getMask(level);
        stat &= ~(last << 1);
        stat &= ~(last >> 1);
        if (dp[level][stat] != -1) {
            return dp[level][stat];
        }
        int ans = 0;
        for (int k = 0; k <= stat; k++) {
            if ((stat | k) != stat || (k & (k >> 1)) != 0) continue;
            ans = Math.max(ans, Integer.bitCount(k) + arrange(level + 1, k));
        }
        return dp[level][stat] = ans;
    }

    private int getMask(int level) {
        if (mask[level] != -1) return mask[level];
        int ans = 0;
        for (char c: seats[level]) {
            ans <<= 1;
            ans |= c == '.' ? 1 : 0;
        }
        return mask[level] = ans;
    }
}



class Solution {
    int m, n;
    int odd, even;
    boolean clash[][];

    public int maxStudents(char[][] seats) {
        this.m = seats.length;
        this.n = seats[0].length;
        int[][] position = new int[m][n];
        int[] cnt = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.') {
                    position[i][j] = cnt[j % 2]++;
                }
            }
        }
        if (cnt[1] == 0 || cnt[0] == 0) return cnt[1] + cnt[0];
        this.odd = cnt[1];
        this.even = cnt[0];
        this.clash = new boolean[odd][even];
        for (int y = 1; y < n; y += 2) {
            for (int x = 0; x < m; x++) {
                if (seats[x][y] == '.') {
                    addEdge(position, x, y, seats);
                }
            }
        }
        // 匈牙利算法
        // Hungarian algorithm
        int match = 0;
        // mapping[evenIdx] = oddIdx;
        int[] mapping = new int[even];
        Arrays.fill(mapping, -1);
        for (int oddIdx = 0; oddIdx < odd; oddIdx++) {
            if (dfs(mapping, oddIdx, new boolean[even])) {
                match++;
            }
        }
        // U = V - M
        return odd + even - match;
    }
    
    public boolean dfs(int mapping[], int oddIdx, boolean[] used) {
        for (int evenIdx = 0; evenIdx < even; evenIdx++) {
            if (used[evenIdx] || ! clash[oddIdx][evenIdx]) continue;
            used[evenIdx] = true;
            if (mapping[evenIdx] == -1 || dfs(mapping, mapping[evenIdx], used)) {
                mapping[evenIdx] = oddIdx;
                return true;
            }
        }
        return false;
    }

    public void addEdge(int[][] position, int x, int y, char[][] seats) {
        int pos = position[x][y];
        for (int dx = -1; dx <= 1; dx++) {
            if (x + dx < 0 || x + dx >= m) continue;
            for (int dy = -1; dy <= 1; dy += 2) {
                if (y + dy < 0 || y + dy >= n) continue;
                if (seats[x + dx][y + dy] == '.') {
                    clash[pos][position[x + dx][y + dy]] = true;
                }
            }
        }
    }
}