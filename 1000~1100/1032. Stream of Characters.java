/* 
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 

Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 

Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
 */

class StreamChecker {

    public StreamChecker(String[] words) {
        
    }
    
    public boolean query(char letter) {
        
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */


class StreamChecker {
    
    class Pair {
        TrieNode n1;
        TrieNode n2;
        
        Pair(TrieNode n1, TrieNode n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
    }
    
    class TrieNode {
        TrieNode[] ch;
        boolean isEnd;
        
        TrieNode() {
            ch = new TrieNode[26];
        }
        
        void setEnd() {
            isEnd = true;
        }
    }
    
    TrieNode root;
    
    private void insert(String s) {
        TrieNode c = root;
        for (int i=0; i<s.length(); i++) {
            if (c.ch[s.charAt(i) - 'a'] == null) {
                c.ch[s.charAt(i) - 'a'] = new TrieNode();
            }
            c = c.ch[s.charAt(i) - 'a'];
        }
        c.setEnd();
    }
    
    private void buildAutomaton() {
        Queue<Pair> q = new LinkedList<>();
        for (int i=0; i<26; i++) {
            if (root.ch[i] == null) {
                root.ch[i] = root;
            } else {
                q.add(new Pair(root.ch[i], root));
            }
        }
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            TrieNode n1 = p.n1;
            TrieNode n2 = p.n2;
            for (int i=0; i<26; i++) {
                if(n1.ch[i] == null) {
                    n1.ch[i] = n2.ch[i];
                } else {
                    q.add(new Pair(n1.ch[i], n2.ch[i]));
                }
            }
            if (n2.isEnd) n1.setEnd();
        }
    }
    
    TrieNode curNode;
    
    public StreamChecker(String[] words) {
        root = new TrieNode();
        for (String s: words) insert(s);
        buildAutomaton();
        curNode = root;
    }
    
    public boolean query(char letter) {
        curNode = curNode.ch[letter-'a'];
        return curNode.isEnd;
    }
}