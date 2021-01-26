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
        Deque<Node> queue = new LinkedList<>();
        for (String word: words) {
            queue.offer(f(word));
        }
        int cnt = 0;
        for (char c: S.toCharArray()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (cur.val == c) {
                    cur = cur.next;
                    if (cur == null) {
                        cnt++;
                        continue;
                    }
                }
                queue.offer(cur);
            }
            if (queue.isEmpty()) {
                break;
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