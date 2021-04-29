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
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}



class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[s2.length()];
    }
}

// ...
// BFS-队列
// 非空-弹出-处理分支-标记去重-加入->循环
// 图搜索

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0, 0));
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == s1.length() && cur.y == s2.length()) {
                return true;
            }
            int right = cur.x + 1;
            if (right <= s1.length() && s1.charAt(right - 1) == s3.charAt(right + cur.y - 1)) {
                if (!visited[right][cur.y]) {
                    visited[right][cur.y] = true;
                    queue.offer(new Point(right, cur.y));
                }
            }
            int down = cur.y + 1;
            if (down <= s2.length() && s2.charAt(down - 1) == s3.charAt(down + cur.x - 1)) {
                if (!visited[cur.x][down]) {
                    visited[cur.x][down] = true;
                    queue.offer(new Point(cur.x, down));
                }
            }
        }
        return false;
    }
}



class Solution {
    private char[] c1;
    private char[] c2;
    private char[] c3;
    private int LEN1;
    private int LEN2;
    private int LEN3;
    private boolean[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();
        c3 = s3.toCharArray();
        LEN1 = c1.length;
        LEN2 = c2.length;
        LEN3 = c3.length;
        dp = new boolean[LEN1 + 1][LEN2 + 1];

        return helper(0, 0, 0);
    }

    private boolean helper(int i, int j, int k) {
        if (i > LEN1 || j > LEN2 || dp[i][j]) {
            return false;
        }
        
        dp[i][j] = true;
        
        return i < LEN1 && c1[i] == c3[k] && helper(i+1, j, k+1) 
            || j < LEN2 && c2[j] == c3[k] && helper(i, j+1, k+1)
            || k == LEN3;
    }
}



class Solution {
    private boolean[][] dp;
    private char[] s1, s2, s3;

    private boolean helper(int i, int j, int k) {
        if (i == s1.length && j == s2.length) return true;
        if (i > s1.length || j > s2.length || dp[i][j]) return false;
        if (i < s1.length && s1[i] == s3[k] && helper(i + 1, j, k + 1)) {
            return true;
        }
        if (j < s2.length && s2[j] == s3[k] && helper(i, j + 1, k + 1)) {
            return true;
        }
        dp[i][j] = true;
        return false; // s1[i] != s3[k] && s2[j] != s3[k]
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        dp = new boolean[s1.length()+1][s2.length()+1];
        this.s1 = s1.toCharArray();
        this.s2 = s2.toCharArray();
        this.s3 = s3.toCharArray();
        return helper(0,0,0);
    }
}



class Solution {
    private boolean[][] dp;
    
    public boolean isInterleave(String s1, String s2, String s3) {
        return s1.length() + s2.length() == s3.length()
            && isInterleave(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }
    
    public boolean isInterleave(char[] s1, char[] s2, char[] s3) {
        dp = new boolean[s1.length + 1][s2.length + 1];
        
        return helper(0, 0, 0, s1, s2, s3);
    }

    private boolean helper(int i, int j, int k, char[] s1, char[] s2, char[] s3) {
        if (i > s1.length || j > s2.length || dp[i][j]) {
            return false;
        }
        
        dp[i][j] = true;
        
        return i < s1.length && s1[i] == s3[k] && helper(i+1, j, k+1, s1, s2, s3) 
            || j < s2.length && s2[j] == s3[k] && helper(i, j+1, k+1, s1, s2, s3)
            || k == s3.length;
    }
}