/* 
Given an array of strings words, find the longest string in words such that every prefix of it is also in words.

For example, let words = ["a", "app", "ap"]. The string "app" has prefixes "ap" and "a", all of which are in words.
Return the string described above. If there is more than one string with the same length, return the lexicographically smallest one, and if no string exists, return "".

 

Example 1:

Input: words = ["k","ki","kir","kira", "kiran"]
Output: "kiran"
Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
Example 2:

Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: Both "apple" and "apply" have all their prefixes in words.
However, "apple" is lexicographically smaller, so we return that.
Example 3:

Input: words = ["abc", "bc", "ab", "qwe"]
Output: ""
 

Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 105
1 <= sum(words[i].length) <= 105
 */

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        Arrays.sort(words);
        int max = -1;
        String ans = "";
        for (String word: words) {
            if (trie.insert(word)) {
                if (max < word.length()) {
                    max = word.length();
                    ans = word;
                }
            }
        }
        return ans;
    }
}

class TrieNode {
    TrieNode[] next;
    boolean EOF = false;

    public TrieNode() {
        this.next = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean insert(String s) {
        char[] chs = s.toCharArray();
        TrieNode node = this.root;
        boolean valid = true;
        for (int i = 0; i < chs.length; ++i) {
            int next = chs[i] - 'a';
            if (node.next[next] == null) {
                if (i != chs.length - 1) {
                    valid = false;
                }
                node.next[next] = new TrieNode();
            }
            node = node.next[next];
            if (node.EOF == false && i != chs.length - 1) {
                valid = false;
            }
        }
        return node.EOF = valid;
    }
}