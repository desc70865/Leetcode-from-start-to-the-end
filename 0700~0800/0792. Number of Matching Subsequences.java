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
   public int numMatchingSubseq(String str, String[] words) {
        Queue<Node>[] waiting = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            waiting[i] = new LinkedList<Node>();
        }
        for (String word: words) {
            waiting[word.charAt(0) - 'a'].add(new Node(word));
        }
        
        int cnt = 0;
        for (char ch: str.toCharArray()) {
            Queue<Node> queue = waiting[ch - 'a'];
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (++cur.idx == cur.chars.length) {
                    cnt++;
                } else {
                    waiting[cur.chars[cur.idx] - 'a'].add(cur);
                }
            }
        }
        return cnt;
    }
}

class Node {
    int idx;
    char[] chars;

    public Node (String word) {
        this.idx = 0;
        this.chars = word.toCharArray();
    }
}



class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Deque<Node>[] list = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new LinkedList<Node>();
        }
        for (String word: words) {
            list[word.charAt(0) - 'a'].offer(f(word));
        }
        int cnt = 0;
        for (char c: S.toCharArray()) {
            Deque<Node> queue = list[c - 'a'];
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                cur = cur.next;
                if (cur == null) {
                    cnt++;
                } else {
                    list[cur.val - 'a'].offer(cur);
                }
            }
        }
        return cnt;
    }

    private Node f(String s) {
        Node dummy = new Node('@');
        Node cur = dummy;
        for (char c: s.toCharArray()) {
            cur.next = new Node(c);
            cur = cur.next;
        }
        return dummy.next;
    }
}

class Node {
    char val;
    Node next;

    public Node(char val) {
        this.val = val;
    }
}