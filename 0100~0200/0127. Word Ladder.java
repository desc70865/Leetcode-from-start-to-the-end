/* 
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
    }
}

// index[] = wordList.get(endWord)
// if (!index) return 0;
// foreach index dp[i] ? dp[i-1]

class Solution {
    //uni-directional BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }
        if (!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        while (!q.isEmpty()) {
            len++;
            int size = q.size(); 
            while (size-- > 0) {
                StringBuilder cur = new StringBuilder(q.poll());
                for (int i = 0; i < cur.length(); i++) {
                    char c = cur.charAt(i);
                    for (int j = 0; j < 26; j++) {
                        if (c == (char)('a' + j)) continue;
                        cur.setCharAt(i, (char)('a' + j));
                        String str = cur.toString();
                        if (set.contains(str)) {
                            if (str.equals(endWord)) {
                                return len + 1;
                            }
                            q.offer(str);
                            set.remove(str);
                        }
                        cur.setCharAt(i, c);
                    }
                }
            }
        }
        return 0;
    }
}