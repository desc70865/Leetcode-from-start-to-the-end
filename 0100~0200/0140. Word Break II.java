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
    List<String> res = new ArrayList<>();
    Set<String> set = new HashSet<>();
    int minSize = Integer.MAX_VALUE, maxSize = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        for (String word: wordDict) {
            // set.add(word);
            int wordSize = word.length();
            if (wordSize > maxSize) maxSize = wordSize;
            if (wordSize < minSize) minSize = wordSize;
        }
        wordBreak(s, 0, new ArrayList<>(), new boolean[s.length() + 1]);
        return res;
    }

    private void wordBreak(String s, int idx, List<String> tmpList, boolean[] v) {
        if (idx == s.length()) {
            res.add(String.join(" ", tmpList));
            return;
        }
        for (int size = minSize; size <= Math.min(maxSize, s.length() - idx); size++) {
            if (v[idx + size]) continue;
            String sub = s.substring(idx, idx + size);
            if (set.contains(sub)) {
                tmpList.add(sub);
                int mem = res.size();
                wordBreak(s, idx + size, tmpList, v);
                // 从 nextIdx = idx + size 处开始不存在新的拆分方法
                // 标记终止后续从此下标开始的递归以降低复杂度
                if (res.size() == mem) v[idx + size] = true;
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }
}