/* 
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = s3.toCharArray();
        boolean[][] dp = new boolean[chs1.length + 1][chs2.length + 1];
        dp[0][0] = true;
        for (int i = 0; i <= chs1.length; ++i) {
            for (int j = 0; j <= chs2.length; ++j) {
                dp[i][j] |= (i > 0 ? dp[i - 1][j] && chs1[i - 1] == chs3[i + j - 1] : false)
                    || (j > 0 ? dp[i][j - 1] && chs2[j - 1] == chs3[i + j - 1] : false);
            }
        }
        return dp[chs1.length][chs2.length];
    }
}



class Solution {
    char[] chs1, chs2, chs3;
    boolean[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        this.chs1 = s1.toCharArray();
        this.chs2 = s2.toCharArray();
        this.chs3 = s3.toCharArray();
        dp = new boolean[chs1.length + 1][chs2.length + 1];
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i > chs1.length || j > chs2.length || dp[i][j]) return false;
        dp[i][j] = true;
        return i < chs1.length && chs1[i] == chs3[i + j] && dfs(i + 1, j)
            || j < chs2.length && chs2[j] == chs3[i + j] && dfs(i, j + 1)
            || i + j == chs3.length;
    }
}



class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = s3.toCharArray();
        boolean[] dp = new boolean[chs2.length + 1];
        for (int i = 0; i <= chs1.length; ++i) {
            for (int j = 0; j <= chs2.length; ++j) {
                if (i == 0 && j == 0) dp[0] = true;
                else dp[j] = (i > 0 ? dp[j] && chs1[i - 1] == chs3[i + j - 1] : false)
                    || (j > 0 ? dp[j - 1] && chs2[j - 1] == chs3[i + j - 1] : false);
            }
        }
        return dp[chs2.length];
    }
}

// ...
// BFS-队列
// 非空-弹出-处理分支-标记去重-加入->循环
// 图搜索

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = s3.toCharArray();
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0));
        boolean[][] visited = new boolean[chs1.length + 1][chs2.length + 1];
        while (! queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == chs1.length && p.y == chs2.length) {
                return true;
            }
            int right = p.x + 1;
            if (right <= chs1.length && chs1[right - 1] == chs3[right + p.y - 1]) {
                if (! visited[right][p.y]) {
                    visited[right][p.y] = true;
                    queue.offer(new Point(right, p.y));
                }
            }
            int down = p.y + 1;
            if (down <= chs2.length && chs2[down - 1] == chs3[down + p.x - 1]) {
                if (! visited[p.x][down]) {
                    visited[p.x][down] = true;
                    queue.offer(new Point(p.x, down));
                }
            }
        }
        return false;
    }

    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}