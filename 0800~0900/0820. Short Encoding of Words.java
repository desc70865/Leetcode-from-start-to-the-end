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
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        Trie trie = new Trie();
        for (String word: words) trie.insert(word);
        return trie.sum;
    }
}

class TrieNode {
    TrieNode[] next;

    public TrieNode() {
        next = new TrieNode[26];
    }
}

class Trie {
    TrieNode node;
    int sum;

    public Trie() {
        this.node = new TrieNode();
        this.sum = 0;
    }

    public void insert(String word) {
        char[] chs = word.toCharArray();
        int len = chs.length;
        TrieNode cur = this.node;
        for (int i = len - 1; i >= 0; i--) {
            int p = chs[i] - 'a';
            if (cur.next[p] == null) {
                cur.next[p] = new TrieNode();
                if (i == 0) this.sum += len + 1;
            }
            cur = cur.next[p];
        }
    }
}



class Solution {
    public int minimumLengthEncoding(String[] words) {
        int len = words.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) strs[i] = reverse(words[i]);
        Arrays.sort(strs);
        int ans = strs[len - 1].length() + 1;
        for (int i = len - 2; i >= 0; i--) {
            if (strs[i + 1].startsWith(strs[i])) continue;
            ans += strs[i].length() + 1;
        }
        return ans;
    }

    private String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }
}



class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        int ans = 0;
        for (String word: words) {
            ans += trie.insert(word);
        }
        return ans;
    }
}

class TrieNode {
    TrieNode[] next;
    boolean end;

    public TrieNode() {
        this.next = new TrieNode[26];
        this.end = false;
    }
}

class Trie {
    TrieNode node;

    public Trie() {
        this.node = new TrieNode();
    }

    public int insert(String word) {
        char[] chs = word.toCharArray();
        int len = chs.length;
        TrieNode cur = this.node;
        int ans = len + 1;
        boolean update = false;
        for (int i = len - 1; i >= 0; i--) {
            int p = chs[i] - 'a';
            if (cur.end) {
                cur.end = false;
                // common suffix
                ans -= len - i;
            }
            if (cur.next[p] == null) {
                cur.next[p] = new TrieNode();
                update = true;
            }
            cur = cur.next[p];
        }
        if (update) {
            cur.end = true;
            return ans;
        } else {
            return 0;
        }
    }
}



class Solution {
    public int minimumLengthEncoding(String[] words) {
        int len = words.length;
        char[][] strs = new char[len][];
        for (int i = 0; i < len; i++) {
            strs[i] = words[i].toCharArray();
        }
        Arrays.sort(strs, new cmp());
        int ans = strs[len - 1].length + 1;
        for (int i = len - 2; i >= 0; i--) {
            if (endsWith(strs[i + 1], strs[i])) continue;
            ans += strs[i].length + 1;
        }
        return ans;
    }

    private boolean endsWith(char[] a, char[] b) {
        if (a.length < b.length) return false;
        for (int i = 0; i < b.length; i++) {
            if (a[a.length - 1 - i] != b[b.length - 1 - i]) return false;
        }
        return true;
    }

    public class cmp implements Comparator<char[]> {
        @Override
        public int compare(char[] a, char[] b) {
            for (int i = 0; i < a.length || i < b.length; i++) {
                if (i == a.length) return -1;
                if (i == b.length) return 1;
                char A = a[a.length - 1 - i];
                char B = b[b.length - 1 - i];
                if (A == B) continue;
                return A < B ? -1 : 1;
            }
            return 0;
        }
    }
}