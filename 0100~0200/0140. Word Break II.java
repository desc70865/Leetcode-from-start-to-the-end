/* 
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

class Solution {
    private String s;
    private List<String> wordDict;
    private boolean[] dp;
    private List<String> res = new ArrayList<>();
    private List<String> tmp = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        dp = new boolean[s.length()];
        this.s = s; this.wordDict = wordDict;
        wordBreak(0);
        return res;
    }
    private void wordBreak(int startIndex) {
        if (startIndex == s.length()) {
            res.add(String.join(" ", tmp));
            return;
        }
        /*
        * 穷举
        if (dp[startIndex]) return;
        dp[startIndex] = true;
        */
        for (String word : wordDict) {
            if (s.startsWith(word, startIndex)) { // core
                tmp.add(word);
                wordBreak(startIndex + word.length());
                tmp.remove(tmp.size()-1);
            }
        }
    }
}



class Solution {
    private int maxLen; // longest String in wordDict
    private boolean[] dp; // visited mark
    private String s;
    private List<String> wordDict;
    private List<String> res;
    private Set<String> dict;
    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null) return res;
        dict = new HashSet<>();
        maxLen = -1;
        this.s = s; this.wordDict = wordDict;
        for (String word : wordDict) {
            dict.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        
        dp = new boolean[s.length()+1];
        Arrays.fill(dp, false);
        
        helper(0, new StringBuilder());
        return res;
    }
    
    private void helper(int idx, StringBuilder sb) {
        if (s.length() == idx) {
            res.add(sb.toString());
            return;
        }
        
        for(int i = idx+1; i <= Math.min(s.length(), idx+maxLen); ++i) {
            if (!dp[i]) {
                String temp = s.substring(idx, i);
                if (dict.contains(temp)) {
                    int oldSize = sb.length();
                    if (oldSize != 0)
                        sb.append(" ");
                    sb.append(temp);
                    
                    int oldLen = res.size();
                    helper(i, sb);
                    if (res.size() == oldLen)
                        dp[i] = true; // core # i 之后无解; dfs去重
                    
                    sb.delete(oldSize, sb.length());
                }
            }
        }
    }
}