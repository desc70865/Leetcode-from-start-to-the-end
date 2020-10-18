/* 
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */

class MapSum {
    Trie trie;
    /** Initialize your data structure here. */
    public MapSum() {
        trie = new Trie();
    }
    
    public void insert(String key, int val) {
        trie.insert(key.toCharArray(), val);
    }
    
    public int sum(String prefix) {
        return trie.sum(prefix.toCharArray());
    }
}

class Trie {
    Trie[] next;
    int num;

    public Trie() {
        next = new Trie[26];
        num = 0;
    }

    public void insert(char[] s, int val) {
        Trie cur = this;
        for (int i = 0; i < s.length; i++) {
            int idx = s[i] - 97;
            if (cur.next[idx] == null) cur.next[idx] = new Trie();
            cur = cur.next[idx];
        }
        cur.num = val;
    }

    public int sum (char[] s) {
        Trie cur = this;
        for (int i = 0; i < s.length; i++) {
            int idx = s[i] - 97;
            if (cur.next[idx] == null) return 0;
            cur = cur.next[idx];
        }
        return count(cur);
    }

    private int count(Trie node) {
        int sum = node.num;
        for (Trie t: node.next) {
            if (t == null) continue;
            sum += count(t);
        }
        return sum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */