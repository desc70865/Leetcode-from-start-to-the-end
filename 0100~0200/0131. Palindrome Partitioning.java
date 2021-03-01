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

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        if (s.length() == 0) return res;
        dfs(s, 0, new ArrayDeque<>(), markPalindrome(s.toCharArray()));
        return res;
    }
    
    // @return l -> end of each palindrome
    private boolean[][] markPalindrome(char[] chs) {
        int N = chs.length;
        boolean[][] dp = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int l = 0; l < r; l++) {
                if (chs[l] == chs[r] && (r == l + 1 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                }
            }
            dp[r][r] = true;
        }
        return dp;
    }

    private void dfs(String s, int l, Deque<String> path, boolean[][] dp) {
        if (l == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int r = l; r < s.length(); r++) {
            if (! dp[l][r]) continue;
            path.addLast(s.substring(l, r + 1));
            dfs(s, r + 1, path, dp);
            path.removeLast();
        }
    }
}