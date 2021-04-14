/* 
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */

class Trie {
    Trie[] child;
    boolean end;

    /** Initialize your data structure here. */
    public Trie() {
        this.child = new Trie[26];
        this.end = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        helper(word, true);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = helper(word, false);
        return node != null && node.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return helper(prefix, false) != null;
    }

    private Trie helper(String s, boolean insert) {
        Trie node = this;
        for (char c: s.toCharArray()) {
            int next = c - 97;
            if (node.child[next] == null) {
                if (insert) node.child[next] = new Trie();
                else return null;
            }
            node = node.child[next];
        }
        if (insert) node.end = true;
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */