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
        Set<String> dict = new HashSet<>(wordList);
        if(! dict.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        return bidirectionalBFS(beginSet, endSet, dict, 1);
    }
    
    private int bidirectionalBFS(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt) {
        if (beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();
        for (String str: beginSet) {
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char c = chs[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chs[i] = j;
                    String s = new String(chs);
                    if (! dict.contains(s)) continue;
                    if (endSet.contains(s)) return cnt;
                    nextSet.add(s);
                }
                chs[i] = c;
            }
        }
        return nextSet.size() > endSet.size() ? bidirectionalBFS(endSet, nextSet, dict, cnt) : bidirectionalBFS(nextSet, endSet, dict, cnt);
    }
}

// bi-directional BFS

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (! dict.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int counter = 1;
        while (! queue.isEmpty()) {
            counter++;
            int size = queue.size();
            while (size-- > 0) {
                char[] chs = queue.poll().toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    char c = chs[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (c == j) continue;
                        chs[i] = j;
                        String s = new String(chs);
                        if (dict.contains(s)) {
                            if (s.equals(endWord)) return counter;
                            queue.offer(s);
                            dict.remove(s);
                        }
                    }
                    chs[i] = c;
                }
            }
        }
        return 0;
    }
}

// uni-directional BFS

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int len = wordList.size();
        boolean[] v = new boolean[len];
        int counter = 1;
        while (! queue.isEmpty()) {
            int size = queue.size();
            counter++;
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < len; i++) {
                    if (v[i]) continue;
                    String s = wordList.get(i);
                    if (! isSimilar(cur, s)) continue;
                    if (s.equals(endWord)) return counter;
                    v[i] = true;
                    queue.offer(s);
                }
            }
        }
        return 0;
    }

    private boolean isSimilar(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length() && diff < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}