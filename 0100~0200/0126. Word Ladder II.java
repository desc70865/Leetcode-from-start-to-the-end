/* 
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

class Solution {
    private int cnt;
    private boolean finish;
    List<List<String>> res = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>(); // core
    Set<String> set = new HashSet<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return res;
        
        Set<String> wordDict = new HashSet<>(wordList);
        if (! wordDict.contains(endWord)) return res;

        Set<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        bfs(startSet, endSet, wordDict, false);

        if (finish) {
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            dfs(beginWord, endWord, list);
        }
        return res;
    }

    private void bfs(Set<String> startSet, Set<String> endSet, Set<String> wordDict, boolean reverse) {
        if (startSet.size() == 0) return;
        Set<String> nextSet = new HashSet<>();
        wordDict.removeAll(startSet);
        for (String s: startSet) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == old) continue;
                    chs[i] = c;
                    String word = new String(chs);
                    if (! wordDict.contains(word)) continue;
                    if (endSet.contains(word)) {
                        finish = true;
                    } else {
                        nextSet.add(word); // start <-> cur <-> end
                    }
                    String key = reverse ? word : s;
                    String val = reverse ? s : word;
                    map.computeIfAbsent(key, z -> new ArrayList<>()).add(val);
                }
                chs[i] = old;
            }
        }
        if (! finish) { // bidirectional - choose
            cnt++;
            if (nextSet.size() < endSet.size())
                bfs(nextSet, endSet, wordDict, reverse);
            else
                bfs(endSet, nextSet, wordDict, ! reverse);
        }
    }

    private void dfs(String word, String endWord, List<String> list) {
        if (word.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (! map.containsKey(word) || cnt < 0) return;
        for (String next: map.get(word)) {
            if (set.contains(next)) continue;
            list.add(next); cnt--;
            set.add(next);
            dfs(next, endWord, list);
            set.remove(next);
            list.remove(list.size() - 1); cnt++;
        }
    }
}