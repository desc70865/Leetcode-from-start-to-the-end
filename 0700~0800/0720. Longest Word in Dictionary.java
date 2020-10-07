/* 
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
 */

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word: words) trie.insert(word);
        int N = trie.dfs();
        char[] str = new char[N];
        for (int i = 0; i < N; i++) {
            str[i] = (char) (trie.idx + 97);
            trie = trie.next[trie.idx];
        }
        return new String(str);
    }
}

class Trie {
    boolean end;
    Trie[] next;
    int idx;

    public Trie() {
        idx = -1;
        end = false;
        next = new Trie[26];
    }

    public void insert(String s) {
        Trie cur = this;
        for (char c: s.toCharArray()) {
            int k = c - 97;
            if (cur.next[k] == null) cur.next[k] = new Trie();
            cur = cur.next[k];
        }
        cur.end = true;
    }

    public int dfs() {
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (next[i] == null || ! next[i].end) continue;
            int k = 1 + next[i].dfs();
            if (k > max) {
                max = k;
                idx = i;
            }
        }
        return max;
    }
}



class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        Arrays.sort(words);
        int idx = 0;
        for (String word: words) trie.insert(word.toCharArray(), idx++);
        return words[trie.res];
    }
}

class Trie {
    Trie[] next;
    int max;
    int res;

    public Trie() {
        next = new Trie[26];
    }

    public void insert(char[] s, int idx) {
        int N = s.length;
        Trie cur = this;
        boolean f = true;
        for (int i = 0; i < N; i++) {
            int k = s[i] - 97;
            if (cur.next[k] == null) {
                if (! f || i == 0 && N != 1) return;
                cur.next[k] = new Trie();
                f = false;
            }
            cur = cur.next[k];
        }
        if (N > max) {
            max = N;
            res = idx;
        }
    }
}