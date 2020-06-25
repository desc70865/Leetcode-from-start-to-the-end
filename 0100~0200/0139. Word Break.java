/* 
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String t : wordDict) {
            maxLen = Math.max(maxLen, t.length());
        }
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i=0; i < len; i++) {
            int threshold = Math.max(i - maxLen, 0);
            for (int j = i; j >= threshold; j--) {
                if (dp[j] && wordDict.contains(s.substring(j, i+1))) {
                    dp[i+1] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

// dp[i]: string before i
// dp[0]: ini # true
// dp[i] = true : dp[t] = true && [t, i-1] ∈ dict

public class Solution {
    private String s;
    private List<String> wordDict;
    private boolean[] visited;
    public boolean wordBreak(String s, List<String> wordDict) {
        visited = new boolean[s.length()];
        this.s = s; this.wordDict = wordDict;
        return wordBreak(0);
    }
    private boolean wordBreak(int startIndex) {
        if (startIndex == s.length()) return true;
        if (visited[startIndex]) return false;
        visited[startIndex] = true; // 去重
        for (String word : wordDict) {
            if (s.startsWith(word, startIndex)) { // core
                if (wordBreak(startIndex + word.length())) return true;
            }
        }
        return false;
    }
}