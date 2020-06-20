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
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!words.contains(endWord)) return 0;
        int L = beginWord.length();

        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
            word -> {
                for (int i = 0; i < L; i++) {
                    // aac, abc, acc -> a*c;
                    String wildcard = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    List<String> transformations = 
                            allComboDict.getOrDefault(wildcard, new ArrayList<>());
                    transformations.add(word);
                    allComboDict.put(wildcard, transformations);
                }
            });

        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            // read Pair
            Pair<String, Integer> node = Q.poll();
            String word = node.getKey();
            int level = node.getValue();
            
            for (int i = 0; i < L; i++) {
                String wildcard = word.substring(0, i) + '*' + word.substring(i + 1, L);

                for (String adjacentWord : allComboDict.getOrDefault(wildcard, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) return level + 1;
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }
}

// ...

class Solution {

    private int L;
    private Map<String, List<String>> allComboDict;

    Solution() {
        this.L = 0;
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        Pair<String, Integer> node = Q.poll();
        String word = node.getKey();
        int level = node.getValue();

        for (int i = 0; i < this.L; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(adjacentWord))
                    return level + othersVisited.get(adjacentWord);
                
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) return 0;
        this.L = beginWord.length();
        wordList.forEach(
            word -> {
                for (int i = 0; i < L; i++) {
                    String wildcard = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    List<String> transformations =
                            this.allComboDict.getOrDefault(wildcard, new ArrayList<>());
                    transformations.add(word);
                    this.allComboDict.put(wildcard, transformations);
                }
            });

        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) return ans;
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) return ans;
        }
        return 0;
    }
}