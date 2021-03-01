/* 
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
 */

class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Deque<Node>[] queue = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            queue[i] = new ArrayDeque<Node>();
        }
        for (String word: words) {
            Node node = new Node(word);
            queue[node.chs[0] - 'a'].offer(node);
        }
        int cnt = 0;
        for (char c: S.toCharArray()) {
            Deque<Node> q = queue[c - 'a'];
            int size = q.size();
            while (size-- > 0) {
                Node cur = q.poll();
                if (++cur.idx == cur.chs.length) cnt++;
                else queue[cur.chs[cur.idx] - 'a'].offer(cur);
            }
        }
        return cnt;
    }
}

class Node {
    char[] chs;
    int idx;

    public Node(String s) {
        this.chs = s.toCharArray();
        this.idx = 0;
    }
}



class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Deque<Node>[] queue = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            queue[i] = new ArrayDeque<Node>();
        }
        for (String word: words) {
            Node node = new Node(word).next;
            queue[node.c - 'a'].offer(node);
        }
        int cnt = 0;
        for (char c: S.toCharArray()) {
            Deque<Node> cur = queue[c - 'a'];
            int size = cur.size();
            while (size-- > 0) {
                Node curNode = cur.poll();
                if (curNode.next == null) cnt++;
                else queue[curNode.next.c - 'a'].offer(curNode.next);
            }
        }
        return cnt;
    }
}

class Node {
    char c;
    Node next;

    public Node(char c) {
        this.c = c;
        this.next = null;
    }

    public Node(String s) {
        Node cur = this;
        for (char c: s.toCharArray()) {
            cur.next = new Node(c);
            cur = cur.next;
        }
    }
}