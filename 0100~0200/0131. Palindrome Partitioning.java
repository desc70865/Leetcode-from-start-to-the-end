/* 
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */

class Solution {
    List<List<String>> res;
    boolean[][] dp;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        if (s.length() == 0) return res;
        dp = markPalindrome(s.toCharArray());
        backtracking(s, 0, new ArrayDeque<>());
        return res;
    }
    
    // @return start -> end of each palindrome
    private boolean[][] markPalindrome(char[] str) {
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int l = 0; l < r; l++) {
                if (str[l] == str[r] && (r == l + 1 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                }
            }
            dp[r][r] = true;
        }
        return dp;
    }

    private void backtracking(String s, int start, Deque<String> path) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (! dp[start][i]) continue;
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, path);
            path.removeLast();
        }
    }
}