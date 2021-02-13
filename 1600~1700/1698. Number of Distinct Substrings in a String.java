/* 
Given a string s, return the number of distinct substrings of s.

A substring of a string is obtained by deleting any number of characters (possibly zero) from the front of the string and any number (possibly zero) from the back of the string.

 

Example 1:

Input: s = "aabbaba"
Output: 21
Explanation: The set of distinct strings is ["a","b","aa","bb","ab","ba","aab","abb","bab","bba","aba","aabb","abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]
Example 2:

Input: s = "abcdefg"
Output: 28
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 

Follow up: Can you solve this problem in O(n) time complexity?
 */

class Solution {
    public int countDistinct(String s) {
        int len = s.length();
        Trie root = new Trie();
        char[] chs = s.toCharArray();
        for (int i = 0; i < len; i++) {
            root.insert(chs, i, len);
        }
        return root.cnt;
    }
}

class Trie {
    Trie[] next;
    int cnt = 0;

    public Trie() {
        next = new Trie[26];
    }

    public void insert(char[] chs, int l, int r) {
        Trie node = this;
        for (int i = l; i < r; i++) {
            int cur = chs[i] - 97;
            if (node.next[cur] == null) {
                node.next[cur] = new Trie();
                this.cnt++;
            }
            node = node.next[cur];
        }
    }
}



class Solution {
    /**
     * @description: sort all suffix in dictionary order, the ans is sum of their length minus common prefix with the following one (if exists)
     */
    public int countDistinct(String s) {
        int len = s.length();
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            pq.offer(s.substring(i, len));
        }
        int sum = 0;
        while (pq.size() > 1) {
            sum += f(pq.poll(), pq.peek());
        }
        return sum + pq.peek().length();
    }

    /**
     * @param lcp: length of longest common prefix between a and b
     */
    private int f(String a, String b) {
        int lcp = 0;
        while (lcp < a.length() && lcp < b.length()) {
            if (a.charAt(lcp) == b.charAt(lcp)) lcp++;
            else break;
        }
        return a.length() - lcp;
    }
}