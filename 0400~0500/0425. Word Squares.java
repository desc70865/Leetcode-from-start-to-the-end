/* 
Given an array of unique strings words, return all the word squares you can build from words. You can return the answer in any order.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 

Example 1:

Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input: words = ["abat","baba","atan","atal"]
Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 5
All words[i] have the same length.
words[i] consists of only lowercase English letters.
All words[i] are unique.
 */

class Solution {
    List<String> list = new ArrayList<>();
    List<List<String>> ans = new ArrayList<>();
    Trie trie = new Trie();
    int n;
    
    public List<List<String>> wordSquares(String[] words) {
        for (String word: words) {
            trie.insert(word);
        }
        this.n = words[0].length();
        for (String word: words) {
            list.add(word);
            dfs(1);
            list.remove(0);
        }
        return ans;
    }

    private void dfs(int idx) {
        if (idx == n) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (String next: trie.searchAll(getPrefix(list))) {
            list.add(next);
            dfs(idx + 1);
            list.remove(list.size() - 1);
        }
    }

    private char[] getPrefix(List<String> list) {
        int size = list.size();
        char[] ans = new char[size];
        for (int i = 0; i < size; ++i) {
            ans[i] = list.get(i).charAt(size);
        }
        return ans;
    }
}

class TrieNode {
    TrieNode[] next;
    String word = null;

    public TrieNode() {
        this.next = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public List<String> searchAll(char[] prefix) {
        List<String> list = new ArrayList<>();
        TrieNode node = trieHelper(prefix, true);
        if (node != null) {
            dfs(node, list);
        }
        return list;
    }

    public void insert(String s) {
        trieHelper(s.toCharArray(), false);
    }

    private TrieNode trieHelper(char[] s, boolean query) {
        TrieNode node = this.root;
        for (int i = 0; i < s.length; ++i) {
            int next = s[i] - 'a';
            if (node.next[next] == null) {
                if (query) {
                    return null;
                }
                node.next[next] = new TrieNode();
            }
            node = node.next[next];
        }
        if (! query) {
            node.word = new String(s);
        }
        return node;
    }

    private void dfs(TrieNode node, List<String> list) {
        if (node.word != null) {
            list.add(node.word);
        }
        for (TrieNode next: node.next) {
            if (next == null) {
                continue;
            }
            dfs(next, list);
        }
    }
}