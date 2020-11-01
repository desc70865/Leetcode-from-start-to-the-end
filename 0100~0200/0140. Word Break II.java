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
    List<String> res;
    Set<String> set;
    int min, max;
    boolean v[];

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>();
        min = Integer.MAX_VALUE;
        max = 0;
        for (String word: wordDict) {
            set.add(word);
            int k = word.length();
            if (k > max) max = k;
            if (k < min) min = k;
        }
        res = new ArrayList<>();
        v = new boolean[s.length() + 1];
        wordBreak(s, 0, new ArrayList<>());
        return res;
    }

    private void wordBreak(String s, int idx, List<String> tmp) {
        if (idx == s.length()) {
            res.add(String.join(" ", tmp));
            return;
        }
        if (idx > s.length() - min) return;
        for (int i = min; i <= Math.min(max, s.length() - idx); i++) {
            if (v[idx + i]) continue;
            String m = s.substring(idx, idx + i);
            if (set.contains(m)) {
                tmp.add(m);
                int x = res.size();
                wordBreak(s, idx + i, tmp);
                if (res.size() == x) v[idx + i] = true;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}