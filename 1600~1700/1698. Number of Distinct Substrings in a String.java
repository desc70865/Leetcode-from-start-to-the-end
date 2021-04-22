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
        while (lcp < a.length() && lcp < b.length() && a.charAt(lcp) == b.charAt(lcp)) {
            lcp++;
        }
        return a.length() - lcp;
    }
}

// RadixSort == SuffixArray ?
// 基数排序，后缀数组
// 1044. Longest Duplicate Substring

class Solution {
    static final int MAX_CHAR = 128;
    int CNT_SIZE;
    int len = 0, ans = 0, ranking = MAX_CHAR - 1;
    char[] chs;
    int[] rank, rankCopy; // [1, len], substring [i:]'s rank
    int[] pos, posCopy; // [0, len - 1], ith substring's index

    public int countDistinct(String s) {
        if (s.length() == 1) return 1;
        initial(s);
        for (int offset = 1; offset < len; offset <<= 1) {
            radixSort(offset);
            radixSort(0);
            updateRank(offset);
        }
        for (int i = 0; i < len - 1; i++) {
            ans -= lcp(pos[i], pos[i + 1]);
        }
        return ans;
    }

    private void updateRank(int offset) {
        rankCopy[pos[0]] = ranking = 1;
        for (int i = 1; i < len; i++) {
            if (rank[pos[i]] != rank[pos[i - 1]]
            || rank[pos[i] + offset] != rank[pos[i - 1] + offset]) {
                ranking++;
            }
            rankCopy[pos[i]] = ranking;
        }
        System.arraycopy(rankCopy, 0, rank, 0, rank.length);
    }

    private void radixSort(int offset) {
        int[] bucket = new int[CNT_SIZE];
        for (int i = 0; i < len; i++) {
            bucket[rank[pos[i] + offset]]++;
        }
        for (int i = 1; i <= ranking; i++) {
            bucket[i] += bucket[i - 1];
        }
        for (int i = len - 1; i >= 0; i--) {
            posCopy[--bucket[rank[pos[i] + offset]]] = pos[i];
        }
        System.arraycopy(posCopy, 0, pos, 0, len);
    }

    private int lcp(int a, int b) {
        int k = 0;
        while (a < len && b < len && chs[a++] == chs[b++]) {
            k++;
        }
        return k;
    }

    private void initial(String s) {
        this.len = s.length();
        this.ans = len * (len + 1) / 2;
        this.chs = s.toCharArray();
        CNT_SIZE = Math.max(len << 1, MAX_CHAR);
        rank = new int[len << 1];
        rankCopy = new int[len << 1];
        pos = new int[len];
        posCopy = new int[len];
        for (int i = 0; i < len; i++) {
            rank[i] = chs[i] - 97;
            pos[i] = i;
        }
    }
}



class Solution {
    public int countDistinct(String s) {
        SuffixArray sa = new SuffixArray(s);
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            ans += i - sa.ht[i];
        }
        return ans;
    }
}

class SuffixArray {
    int m, n;
    char[] s;
    int[] sa, rk, ht, c, x, y;

    public SuffixArray(String str) {
        this.s = str.toCharArray();
        this.n = s.length + 1;
        this.m = Math.max(123, n);
        this.sa = new int[n];
        this.rk = new int[n];
        this.ht = new int[n];
        this.c = new int[m];
        this.x = new int[n];
        this.y = new int[n];
        getSA();
        getHT();
    }

    public void getSA() {
        for (int i = 0; i < n - 1; i++) c[rk[i] = s[i]]++;
        for (int i = 1; i < m; i++) c[i] += c[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[c[rk[i]]--] = i;
        for (int k = 1; k < n; k <<= 1) {
            for (int i = n - k; i < n; i++) y[i - n + k] = i;
            int j = 0;
            for (int i = 0; i < n; i++) if (sa[i] >= k) y[k + j++] = sa[i] - k;
            for (int i = 0; i < m; i++) c[i] = 0;
            for (int i = 0; i < n; i++) c[rk[y[i]]]++;
            for (int i = 1; i < m; i++) c[i] += c[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--c[rk[y[i]]]] = y[i];
            int p = 0;
            y[sa[0]] = p++;
            for (int i = 1; i < n; i++) y[sa[i]] = rk[sa[i]] == rk[sa[i - 1]] && rk[sa[i] + k] == rk[sa[i - 1] + k] ? p - 1 : p++;
            m = p;
            x = rk;
            rk = y;
            y = x;
            if (m == n) break;
        }
    }

    public void getHT() {
        for (int i = 0, k = 0; i < n - 1; i++) {
            if (k > 0) k--;
            int j = sa[rk[i] - 1];
            while (i + k < n - 1 && j + k < n - 1 && s[i + k] == s[j + k]) k++;
            ht[rk[i]] = k;
        }
    }
}