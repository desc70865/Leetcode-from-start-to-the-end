/* 
Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 

Note:

1 <= words.length <= 2000.
1 <= words[i].length <= 7.
Each word has only lowercase letters.
 */

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int len = words.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int j = i + 1;
            for (; j < len; j++) {
                if (words[j].endsWith(words[i])) break;
            }
            if (j == len) sum += words[i].length() + 1;
        }
        return sum;
    }
}



class Solution {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word);
        }
        return trie.sum;
    }
}

class Trie {
    Trie[] next;
    int sum = 0;

    public Trie() {
        next = new Trie[26];
    }

    public void insert(String word) {
        char[] chs = word.toCharArray();
        int len = chs.length;
        Trie node = this;
        for (int i = len - 1; i >= 0; i--) {
            if (node.next[chs[i] - 97] == null) {
                node.next[chs[i] - 97] = new Trie();
                if (i == 0) {
                    this.sum += len + 1;
                }
            }
            node = node.next[chs[i] - 97];
        }
    }
}