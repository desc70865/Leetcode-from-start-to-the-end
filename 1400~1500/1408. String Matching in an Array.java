/* 
Given an array of string words. Return all strings in words which is substring of another word in any order. 

String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].

 

Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:

Input: words = ["blue","green","bu"]
Output: []
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
It's guaranteed that words[i] will be unique.
 */

class Solution {
    char[][] map;
    int[][] nexts;
    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int N = words.length;
        map = new char[N][];
        nexts = new int[N][];
        for (int i = 0; i < N; i++) map[i] = words[i].toCharArray();
        for (int i = 0; i < N - 1; i++) nexts[i] = getNext(map[i]);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (search(j, i) < 0) continue;
                res.add(words[i]);
                break;
            }
        }
        return res;
    }
    
    public int search(int a, int b) {
        char[] str = map[a], model = map[b];
        int N = str.length, M = model.length;
        
        int[] next = nexts[b];
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (str[i] == model[j]) {
                i++;
                j++;
            } else if (next[j] == -1) i++;
            else j = next[j];
        }
        return j == M ? i - j : -1;
    }
    
    public int[] getNext(char[] m) {
        int N = m.length;
        if (N == 1) return new int[] {-1};
        int[] next = new int[N];
        next[0] = -1;
        next[1] = 0;
        int cur = 0;
        int idx = 2;
        while (idx < N) {
            if (m[idx - 1] == m[cur]) next[idx++] = ++cur;
            else if (cur > 0) cur = next[cur];
            else  next[idx++] = 0;
        }
        return next;
    }
}



class Solution {
    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int N = words.length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (words[j].indexOf(words[i]) >= 0) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }
}



class Solution {
    public List<String> stringMatching(String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word: words) sb.append(word).append("#");
        List<String> res = new ArrayList<>();
        for (String word: words) if (sb.indexOf(word) != sb.lastIndexOf(word)) res.add(word);
        return res;
    }
}