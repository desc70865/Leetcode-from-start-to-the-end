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
    
    private Trie[] children = new Trie['z' - 'a' + 1]; // non-magic
    private boolean endOfWord = false;

    public void insert(String word) {
        insertNode(word, 0);
    }

    public boolean search(String word) {
        Trie node = searchNode(word, 0);
        return node != null && node.endOfWord;
    }

    public boolean startsWith(String word) {
        Trie node = searchNode(word, 0);
        return node != null;
    }

    private void insertNode(String word, int pos) {
        if (pos == word.length()) {
            endOfWord = true;
        } else {
            int childInd = childIndex(word, pos);
            if (children[childInd] == null) {
                children[childInd] = new Trie();
            }
            Trie next = children[childInd]; // each Trie contain atmost 26 Trie array
            next.insertNode(word, pos + 1);
        }
    }

    private Trie searchNode(String word, int pos) {
        if (pos == word.length()) {
            return this;
        } else {
            int childInd = childIndex(word, pos);
            return children[childInd] == null ? null : children[childInd].searchNode(word, pos + 1);
        }
    }

    private int childIndex(String word, int pos) {
        return word.charAt(pos) - 'a';
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



// 递归数据结构
// 在定义中引用自身
// 循环递归 -> calss A { B[]; } CLASS B { A[]; }
