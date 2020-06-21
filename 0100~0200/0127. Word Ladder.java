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
        if(!dict.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        return search(beginSet, endSet, dict,  1);
    }
    
    private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt){
        if(beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();
        for(String str : beginSet){
            char[] chs = str.toCharArray();
            for(int i = 0; i < chs.length; i++){
                char c = chs[i];
                for(char j = 'a'; j <= 'z'; j++){
                    chs[i] = j;
                    String tmp = new String(chs);
                    if(!dict.contains(tmp)) continue;
                    if(endSet.contains(tmp)) return cnt;
                    nextSet.add(tmp);
                }
                chs[i] = c;
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict,  cnt) : search(nextSet, endSet, dict, cnt);
    }
}

// index[] = wordList.get(endWord)
// if (!index) return 0;
// foreach index dp[i] ? dp[i-1]

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!words.contains(endWord)) return 0;
        HashSet<String> words = new HashSet<>(wordList);
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Queue<String> test = new LinkedList<>();
        
        map.put(beginWord, 1);
        queue.offer(beginWord);
        
        while(!queue.isEmpty()) {
            String word = queue.poll();
            int level = map.get(word);
            for (String temp : words) {
                if (isSimilar(word, temp)) {
                    if (temp.equals(endWord)) return level+1;
                    test.offer(temp);
                }
            }
            while(!test.isEmpty()) {
            	String temp = test.poll();
                words.remove(temp);
                map.put(temp, level+1);
                queue.offer(temp);
            }
        }
        return 0;
    }
    
    private boolean isSimilar(String a, String b) {
        int count = 0;
        for (int i=0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }
}

// ...

class Solution {

    private int L;
    private Map<String, List<String>> dict;
    private Set<String> todo;
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        todo = new HashSet<>(wordList);
        if(!todo.contains(endWord)) return 0;
        L = beginWord.length();
        dict = new HashMap<>();
        wordList.forEach(
            word -> {
                for (int i=0; i < L; i++) {
                    String wildcard = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    if (!dict.containsKey(wildcard))
                        dict.put(wildcard, new ArrayList<>());
                    dict.get(wildcard).add(word);
                }
            });
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        return search(beginSet, endSet, 1);
    }
    
    private int search(Set<String> beginSet, Set<String> endSet, int cnt) {
        if (beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        todo.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();
        
        for (String str : beginSet) {
            for (int i=0; i < L; i++) {
                String cur = str.substring(0, i) + '*' + str.substring(i + 1, L);
                List<String> remove = new ArrayList<>();
                for (String tmp : dict.getOrDefault(cur, new ArrayList<>())) {
                    if (!todo.contains(tmp)) {
                        remove.add(tmp);
                        continue;
                    }
                    if (endSet.contains(tmp)) return cnt;
                    nextSet.add(tmp);
                }
                if (remove.size() > 0)
                    dict.get(cur).removeAll(remove);
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, cnt) : search(nextSet, endSet, cnt);
    }
}