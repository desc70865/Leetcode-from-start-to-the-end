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

class Solution {
    Set<String> set;
    int min, max;

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>();
        min = Integer.MAX_VALUE;
        max = 0;
        for (String word: wordDict) {
            set.add(word);
            int k = word.length();
            if (k > max) max = k;
            if (k < min) min = k;
        }
        return wordBreak(s, 0, new boolean[s.length() + 1]);
    }

    private boolean wordBreak(String s, int idx, boolean[] v) {
        if (idx == s.length()) {
            return true;
        }
        v[idx] = true;
        for (int i = min; i <= Math.min(max, s.length() - idx); i++) {
            if (v[idx + i]) continue;
            String m = s.substring(idx, idx + i);
            if (set.contains(m)) {
                if (wordBreak(s, idx + i, v)) return true;
            }
        }
        return false;
    }
}